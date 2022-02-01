package sn.ept.dic2.dev_mobile.tp.personapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.dic2.dev_mobile.tp.personapp.api.ApiClient;
import sn.ept.dic2.dev_mobile.tp.personapp.models.Person;

public class PersonDetails extends AppCompatActivity {

    TextView firstName;
    TextView lastName;
    TextView email;
    TextView dateNaissance;
    TextView dateEnregistrement;
    TextView dateModification;
    ProgressBar progressBar;
    Button deletePersonBtn;
    Button editPersonBtn;
    String emailID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        firstName = findViewById(R.id.details_person_first_name);
        lastName = findViewById(R.id.details_person_last_name);
        email = findViewById(R.id.details_person_email);
        dateNaissance = findViewById(R.id.details_person_date_naissance);
        dateEnregistrement = findViewById(R.id.details_person_date_enregistrement);
        dateModification = findViewById(R.id.details_person_date_modification);
        deletePersonBtn = findViewById(R.id.deletePersonBtn);
        editPersonBtn = findViewById(R.id.editPersonBtn);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        emailID = intent.getStringExtra("EMAIL_ID");

        getPerson();

        editPersonBtn.setOnClickListener(view -> {
            moveToAddPerson(AddPerson.class);
        });

        deletePersonBtn.setOnClickListener(view -> {
            deletePerson();
        });

    }

    void getPerson(){
        ApiClient.getApiClient().getPersonByID("dkhadim",emailID).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Person person = response.body();
                    firstName.setText(person.getFirstName());
                    lastName.setText(person.getLastName());
                    email.setText(person.getEmail());
                    dateNaissance.setText(person.getDateNaissance());
                    dateModification.setText(person.getDateModification());
                    dateEnregistrement.setText(person.getDateEnregistrement());
                    Log.i("Getting Details Person", person.getEmail());
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                showToask("Une erreur s'est produite");
                Log.e("Getting Details Person", t.getMessage());
            }
        });
    }

    void deletePerson(){
        ApiClient.getApiClient().deletePerson("dkhadim", emailID).enqueue(new Callback<Response<String>>() {
            @Override
            public void onResponse(Call<Response<String>> call, Response<Response<String>> response) {
                if(response.isSuccessful()){
                    showToask("Vous avez supprimé ce contact avec succès");
                    moveTo(MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<Response<String>> call, Throwable t) {
                Log.e("Deleting Person", t.getMessage());
                showToask("Une erreur s'est produite");
            }
        });
    }

    void moveTo(Class context) {
        Intent intent = new Intent(getApplicationContext(), context);
        startActivity(intent);
    }

    void moveToAddPerson(Class context){
        Intent intent = new Intent(getApplicationContext(), context);
        if(emailID != null){
            intent.putExtra("EMAIL_ID", emailID);
        }
        startActivity(intent);
    }

    void showToask(String message){
        Toast toast = Toast.makeText(PersonDetails.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}