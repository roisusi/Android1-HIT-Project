package com.example.rrszoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button seaAnimal;
    private Button mammals;
    private Button reptalis;
    private Button birds;
    private Button artth;
    private FragmentManager fragmentManager;
    private Spinner spinner;

    private String checkTypeOfAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

    }

    public void animalSelection (View view){

       fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FragmentAnimals();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragAnimal, fragment).commit();
        fragmentManager.executePendingTransactions();


        Button randomSelection = findViewById(view.getId());

        switch (view.getId()){
            case R.id.seaAnimals:
                checkTypeOfAnimal = "seaAnimals";
                break;

        }

        openSpinner(checkTypeOfAnimal);
    }

    private void openSpinner (String options){

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        seaAnimal = (Button)findViewById(R.id.seaAnimals);
        seaAnimal.setVisibility(View.INVISIBLE);
        mammals = (Button)findViewById(R.id.mammals);
        mammals.setVisibility(View.INVISIBLE);
        reptalis = (Button)findViewById(R.id.reptiles);
        reptalis.setVisibility(View.INVISIBLE);
        birds = (Button)findViewById(R.id.birds);
        birds.setVisibility(View.INVISIBLE);
        artth = (Button)findViewById(R.id.arthropoda);
        artth.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}