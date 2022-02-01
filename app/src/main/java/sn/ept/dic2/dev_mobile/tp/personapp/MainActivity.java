package sn.ept.dic2.dev_mobile.tp.personapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.dic2.dev_mobile.tp.personapp.adapters.RecyclePersonAdapter;
import sn.ept.dic2.dev_mobile.tp.personapp.api.ApiClient;
import sn.ept.dic2.dev_mobile.tp.personapp.models.Person;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addNewPersonBtn;
    TextView subTitle;
    RecyclePersonAdapter recyclePersonAdapter;
    List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.home_page_recycle);
        addNewPersonBtn = findViewById(R.id.floatingActionButton);

        addNewPersonBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddPerson.class);
            startActivity(intent);
        });

        subTitle = findViewById(R.id.home_page_subtitle);

        getPersons();


    }

    public void getPersons(){
        ApiClient.getApiClient().getAllPersons("dkhadim").enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if(response.isSuccessful()){
                    Log.i("Getting Persons", String.valueOf(response.code()));
                    persons = response.body();
                    setRecyclerView();
                    subTitle.setText(subTitle.getText() + " (" + persons.size()   + ")");
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.e("Getting Persons", t.getMessage());
            }
        });
    }

    public void setRecyclerView(){
        recyclePersonAdapter = new RecyclePersonAdapter(persons);
        recyclerView.setAdapter(recyclePersonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclePersonAdapter.setOnItemClickListener(person -> {
            Intent intent = new Intent(getApplicationContext(), PersonDetails.class);
            intent.putExtra("EMAIL_ID", person.getEmail());
            startActivity(intent);
        });
    }

}