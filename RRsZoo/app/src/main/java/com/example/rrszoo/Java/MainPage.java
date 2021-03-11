package com.example.rrszoo.Java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.rrszoo.Fragments.FragmentAddAnimal;
import com.example.rrszoo.Fragments.FragmentAnimals;
import com.example.rrszoo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainPage" ;
    private Button seaAnimal;
    private Button mammals;
    private Button reptalis;
    private Button birds;
    private Button artth;
    private FragmentManager fragmentManager;
    private Spinner spinner;
    private Fragment fragment;
    private ImageView imageView;
    private String gettingExtra;
    private int checkTypeOfAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        FloatingActionButton fab = findViewById(R.id.fab);


        imageView = (ImageView) findViewById(R.id.titleBar3);
        seaAnimal = (Button) findViewById(R.id.seaAnimals);
        mammals = (Button) findViewById(R.id.mammals);
        reptalis = (Button) findViewById(R.id.reptiles);
        birds = (Button) findViewById(R.id.birds);
        artth = (Button) findViewById(R.id.arthropoda);

        //For Admin Add new Animal to DataBase
        gettingExtra = getIntent().getStringExtra("Admin");
        Log.e(TAG, "onCreate: Login Admin " + gettingExtra);
        if(gettingExtra.equals("true")) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                    fabFunc();

                }
            });
        }
        else {
            fab.setVisibility(View.INVISIBLE);
        }
    }

    public void animalSelection(View view) {
        fragmentManager = getSupportFragmentManager();
        fragment = new FragmentAnimals();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.animalFrag, fragment).addToBackStack(null).commit();
        fragmentManager.executePendingTransactions();

        switch (view.getId()) {
            case R.id.seaAnimals:
                checkTypeOfAnimal = R.array.SeaAnimals;
                break;
            case R.id.arthropoda:
                checkTypeOfAnimal = R.array.Arthropoda;
                break;
            case R.id.mammals:
                checkTypeOfAnimal = R.array.Mammals;
                break;
            case R.id.reptiles:
                checkTypeOfAnimal = R.array.Reptiles;
                break;
            case R.id.birds:
                checkTypeOfAnimal = R.array.Birds;
                break;

        }

        openSpinner(checkTypeOfAnimal);
    }

    public void backToAnimalMenu(View view){
        fragmentManager.popBackStack();
        imageView.setVisibility(view.VISIBLE);
        seaAnimal.setVisibility(View.VISIBLE);
        mammals.setVisibility(View.VISIBLE);
        reptalis.setVisibility(View.VISIBLE);
        birds.setVisibility(View.VISIBLE);
        artth.setVisibility(View.VISIBLE);
    }

    private void openSpinner(int rArray) {


        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, rArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        seaAnimal.setVisibility(View.INVISIBLE);
        mammals.setVisibility(View.INVISIBLE);
        reptalis.setVisibility(View.INVISIBLE);
        birds.setVisibility(View.INVISIBLE);
        artth.setVisibility(View.INVISIBLE);

        Button back = (Button) findViewById(R.id.backToMainPage);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStack();
                seaAnimal.setVisibility(View.VISIBLE);
                mammals.setVisibility(View.VISIBLE);
                reptalis.setVisibility(View.VISIBLE);
                birds.setVisibility(View.VISIBLE);
                artth.setVisibility(View.VISIBLE);
            }
        });

    }

    public void fabFunc (){
        fragmentManager = getSupportFragmentManager();
        fragment = new FragmentAddAnimal();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.addAnimalFrag, fragment).addToBackStack(null).commit();

        imageView.setVisibility(View.INVISIBLE);
        seaAnimal.setVisibility(View.INVISIBLE);
        mammals.setVisibility(View.INVISIBLE);
        reptalis.setVisibility(View.INVISIBLE);
        birds.setVisibility(View.INVISIBLE);
        artth.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        String animal = parent.getItemAtPosition(position).toString();

        //

        Button select = (Button) findViewById(R.id.selectAnimal);
        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnimalPage.class);
                intent.putExtra("Animal", animal);
                intent.putExtra("Admin", gettingExtra);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}