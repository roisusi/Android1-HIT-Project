package com.example.rrszoo.Java;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rrszoo.R;

import java.util.List;

public class AnimalPage extends AppCompatActivity {
    private static final String TAG = "AnimalPage" ;
    private List<Animal> animals;
    private TextView name;
    private TextView location;
    private TextView lifetime;
    private TextView food;
    private TextView numOfChildres;
    private String gettingExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalpage);

        gettingExtra = getIntent().getStringExtra("Animal");
        Log.e(TAG, "onCreate: Animal " + gettingExtra);
        getFromDB();
        Button backToAnimalSelection = (Button)findViewById(R.id.backAnimal);
        backToAnimalSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                startActivity(intent);
            }
        });

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void getFromDB(){
        name = (TextView) findViewById(R.id.name);
        name.setText(name.getText().toString() + "\nHorse");
        location = (TextView) findViewById(R.id.location);
        location.setText(location.getText().toString() + "\nAfrica or North America");
        lifetime = (TextView) findViewById(R.id.lifeTime);
        lifetime.setText(lifetime.getText().toString() + "\n20-30");
        food = (TextView) findViewById(R.id.food);
        food.setText(food.getText().toString() + "\nShit");
        numOfChildres = (TextView) findViewById(R.id.numOfChilds);
        numOfChildres.setText(numOfChildres.getText().toString() + "\n15-19");
    }
}