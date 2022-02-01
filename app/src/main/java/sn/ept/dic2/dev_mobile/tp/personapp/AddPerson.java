package sn.ept.dic2.dev_mobile.tp.personapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.dic2.dev_mobile.tp.personapp.api.ApiClient;
import sn.ept.dic2.dev_mobile.tp.personapp.models.NewPerson;
import sn.ept.dic2.dev_mobile.tp.personapp.models.Person;

public class AddPerson extends AppCompatActivity {

    EditText firstNameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText dateNaissanceInput;
    TextView titleText;
    Button submitBtn;
    String emailID;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        submitBtn = findViewById(R.id.add_person_button_submit);
        firstNameInput = findViewById(R.id.add_person_first_name);
        lastNameInput = findViewById(R.id.add_person_last_name);
        emailInput = findViewById(R.id.add_person_email);
        dateNaissanceInput = findViewById(R.id.add_person_dateNais);
        titleText = findViewById(R.id.add_person_title);

        Intent intent = getIntent();
        emailID = intent.getStringExtra("EMAIL_ID");

        if(!(emailID == null || emailID.equals(""))){
            titleText.setText("Modification");
            submitBtn.setText("Modifier");
            getPersonToUpdate();
        }

        submitBtn.setOnClickListener(view -> {
            if(!(emailID == null || emailID.equals(""))){
                updatePerson();
            }else{
                addPerson();
            }
        });

    }

    void addPerson() {
        String dateNaissanceFormated = this.dateNaissanceInput.getText().toString() + "T00:00:00.880Z";
        String key = "dkhadim";
        NewPerson person = new NewPerson(this.firstNameInput.getText().toString(), this.lastNameInput.getText().toString(), this.emailInput.getText().toString(), dateNaissanceFormated, key);
        ApiClient.getApiClient().createPerson(key, person).enqueue(new Callback<NewPerson>() {
            @Override
            public void onResponse(Call<NewPerson> call, Response<NewPerson> response) {
                if(response.isSuccessful()){
                    showToask("Vous avez ajouté un contact avec succès");
                    moveTo(MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<NewPerson> call, Throwable t) {
                Log.e("Creating person", t.getMessage());
            }
        });
    }

    void updatePerson() {
        String key = "dkhadim";
        Person newPerson = new Person(this.firstNameInput.getText().toString(), this.lastNameInput.getText().toString(), this.emailInput.getText().toString(),key,person.getDateEnregistrement(), person.getDateModification(), this.dateNaissanceInput.getText().toString());
        ApiClient.getApiClient().updatePerson(key,emailID,newPerson).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful()){
                    Log.i("Updating person", emailID);
                    showToask("Vous avez modifié le contact avec succès");
                    moveTo(MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("Creating person", t.getMessage());
            }
        });
    }


    void getPersonToUpdate(){
        ApiClient.getApiClient().getPersonByID("dkhadim",emailID).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful()){
                    person = response.body();
                    firstNameInput.setText(person.getFirstName());
                    lastNameInput.setText(person.getLastName());
                    emailInput.setText(person.getEmail());
                    dateNaissanceInput.setText(person.getDateNaissance());
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("Getting person", t.getMessage());
                showToask("Une erreur s'est produite");
            }
        });
    }

    void moveTo(Class context) {
        Intent intent = new Intent(getApplicationContext(), context);
        startActivity(intent);
    }

    void showToask(String message){
        Toast toast = Toast.makeText(AddPerson.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}