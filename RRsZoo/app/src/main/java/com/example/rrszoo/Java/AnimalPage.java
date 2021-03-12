package com.example.rrszoo.Java;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rrszoo.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;




public class AnimalPage extends AppCompatActivity {
    private static final String TAG = "AnimalPage" ;
    private TextView name;
    private TextView location;
    private TextView lifetime;
    private TextView food;
    private TextView numOfChildres;
    private String gettingExtraAnimal;
    private String gettingExtraAdmin;

    private List<String> messageToServer;
    private List<String> AnimalMessage;
    private GetInformation mt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalpage);
        messageToServer = new ArrayList<>();

        gettingExtraAnimal = getIntent().getStringExtra("Animal");
        Log.e(TAG, "onCreate: Animal " + gettingExtraAnimal);
        gettingExtraAdmin = getIntent().getStringExtra("Admin");
        Log.e(TAG, "onCreate: Admin " + gettingExtraAdmin);
        Button backToAnimalSelection = (Button)findViewById(R.id.backAnimal);
        backToAnimalSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                intent.putExtra("Admin",gettingExtraAdmin);
                startActivity(intent);
            }
        });

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//

        getFromDB();
    }

    private void getFromDB(){

        messageToServer.add("Animal");
        messageToServer.add(gettingExtraAnimal);
        mt = new GetInformation(messageToServer,this);
        mt.execute();

    }

    @SuppressLint("WrongThread")
    public void SetAnimalFromDataBase(List<String> animalMessage) {


        ///Animal animal = new Animal(animalMessage);
        Log.e(TAG, "SetAnimalFromDataBase: [0]=" + animalMessage.get(0) );
        Log.e(TAG, "SetAnimalFromDataBase: [1]=" + animalMessage.get(1) );

        name = (TextView) findViewById(R.id.name);
        name.append(animalMessage.get(1));
        location = (TextView) findViewById(R.id.location);
        location.append(animalMessage.get(2));
        lifetime = (TextView) findViewById(R.id.lifeTime);
        lifetime.append(animalMessage.get(3));
        food = (TextView) findViewById(R.id.food);
        food.append(animalMessage.get(4));
        numOfChildres = (TextView) findViewById(R.id.numOfChilds);
        numOfChildres.append(animalMessage.get(5));

        String imageUri = animalMessage.get(6);
        ImageView ivBasicImage = (ImageView) findViewById(R.id.animalImageView);
        Picasso.with(this).load(imageUri).into(ivBasicImage);



    }

}