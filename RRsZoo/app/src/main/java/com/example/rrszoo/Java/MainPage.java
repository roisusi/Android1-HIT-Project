package com.example.rrszoo.Java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.rrszoo.Fragments.FragmentAddAnimal;
import com.example.rrszoo.Fragments.FragmentAnimals;
import com.example.rrszoo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainPage";
    private Button seaAnimal;
    private Button mammals;
    private Button reptalis;
    private Button birds;
    private Button artth;
    private FragmentManager fragmentManager;
    private Spinner spinnerAnimals;
    private Spinner spinnerTypes;
    private Fragment fragment;
    private FragmentAddAnimal fragmentAddAnimalal;
    private ImageView imageView;
    private String gettingExtra;
    private List<String> animal;
    private List<String> messageToServer;
    private GetInformation getInformation;
    private SendInformation sendInformation;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        fab = findViewById(R.id.fab);

        imageView = (ImageView) findViewById(R.id.titleBar3);
        seaAnimal = (Button) findViewById(R.id.seaAnimals);
        mammals = (Button) findViewById(R.id.mammals);
        reptalis = (Button) findViewById(R.id.reptiles);
        birds = (Button) findViewById(R.id.birds);
        artth = (Button) findViewById(R.id.arthropoda);
        animal = new ArrayList<>();
        messageToServer = new ArrayList<>();

        //For Admin Add new Animal to DataBase
        gettingExtra = getIntent().getStringExtra("Admin");
        Log.e(TAG, "onCreate: Login Admin " + gettingExtra);
        if (gettingExtra.equals("true")) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                    fabFunc();

                }
            });
        } else {
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
                getDataBaseTypes("Sea Animals");
                break;
            case R.id.arthropoda:
                getDataBaseTypes("Arthropoda");
                break;
            case R.id.mammals:
                getDataBaseTypes("Mammals");
                break;
            case R.id.reptiles:
                getDataBaseTypes("Reptiles");
                break;
            case R.id.birds:
                getDataBaseTypes("Birds");
                break;

        }


    }

    public void backToAnimalMenu(View view) {
        fragmentManager.popBackStack();
        imageView.setVisibility(view.VISIBLE);
        seaAnimal.setVisibility(View.VISIBLE);
        mammals.setVisibility(View.VISIBLE);
        reptalis.setVisibility(View.VISIBLE);
        birds.setVisibility(View.VISIBLE);
        artth.setVisibility(View.VISIBLE);
        showFab();

    }

    private void getDataBaseTypes(String animal) {
        messageToServer.add("Type");
        messageToServer.add(animal);
        getInformation = new GetInformation(messageToServer, this);
        getInformation.execute();
    }

    public void fillArrayToSpinner(List<String> list) {
        animal = list;
        openSpinner(animal);
    }

    private void openSpinner(List<String> types) {

        spinnerAnimals = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, types);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerAnimals.setAdapter(adapter);
        spinnerAnimals.setOnItemSelectedListener(this);

        seaAnimal.setVisibility(View.INVISIBLE);
        mammals.setVisibility(View.INVISIBLE);
        reptalis.setVisibility(View.INVISIBLE);
        birds.setVisibility(View.INVISIBLE);
        artth.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);

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
                showFab();
            }
        });

    }

    public void fabFunc() {
        fragmentManager = getSupportFragmentManager();
        fragmentAddAnimalal = new FragmentAddAnimal();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.addAnimalFrag, fragmentAddAnimalal).addToBackStack(null).commit();

        fab.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        seaAnimal.setVisibility(View.INVISIBLE);
        mammals.setVisibility(View.INVISIBLE);
        reptalis.setVisibility(View.INVISIBLE);
        birds.setVisibility(View.INVISIBLE);
        artth.setVisibility(View.INVISIBLE);

    }


    public void addAnimal(View view) {

        EditText name = (EditText) findViewById(R.id.addAnimalName);
        EditText location = (EditText) findViewById(R.id.addLocation);
        EditText lifeTime = (EditText) findViewById(R.id.addLifeTime);
        EditText food = (EditText) findViewById(R.id.addFood);
        EditText childrens = (EditText) findViewById(R.id.addChildrens);
        EditText img = (EditText) findViewById(R.id.addImageLink);

        messageToServer.clear();

        spinnerTypes = fragmentAddAnimalal.getSpinner();
        messageToServer.add("AddAnimal");
        messageToServer.add(spinnerTypes.getSelectedItem().toString());
        messageToServer.add(name.getText().toString());
        messageToServer.add(location.getText().toString());
        messageToServer.add(lifeTime.getText().toString());
        messageToServer.add(food.getText().toString());
        messageToServer.add(childrens.getText().toString());
        messageToServer.add(img.getText().toString());

        sendInformation = new SendInformation(messageToServer, MainPage.this);
        sendInformation.execute();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String animal = parent.getItemAtPosition(position).toString();

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

    private void showFab() {
        if (gettingExtra.equals("true"))
            fab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.account:
                intent= new Intent(getApplicationContext(), AccountInfo.class);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item); //react to many chooses

        }
        return true;
    }

}