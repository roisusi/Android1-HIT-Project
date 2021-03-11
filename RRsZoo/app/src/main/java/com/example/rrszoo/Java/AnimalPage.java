package com.example.rrszoo.Java;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rrszoo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import androidx.appcompat.app.AppCompatActivity;


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
    private myTask mt;



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
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        getFromDB();
    }

    private void getFromDB(){

        messageToServer.add("Animal");
        messageToServer.add(gettingExtraAnimal);
        mt = new myTask(messageToServer,this);
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

        //ImageView imageView =(ImageView)findViewById(R.id.animalImageView);
        //imageView.setImageDrawable(LoadImageFromWebOperations("D:\\OneDrive - Holon Institute of Technology\\Android1Project\\AnimalImages\\Lion.png"));

        File imgFile = new  File("/sdcard/Pictures/Lion.png");

        if(imgFile.exists()){
            String s = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Lion.png";
            Bitmap myBitmap = BitmapFactory.decodeFile(s);


            ImageView myImage = (ImageView) findViewById(R.id.animalImageView);

            myImage.setImageBitmap(myBitmap);
            //System.IO.Path.Combine(android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString (), "MyAppName");

        }

//        BitmapFactory.Options options;
//
//        try {
//            String imageInSD = "/sdcard/Pictures/" + "Lion.png";
//            Bitmap bitmap = BitmapFactory.decodeFile(imageInSD);
//        } catch (OutOfMemoryError e) {
//            try {
//                options = new BitmapFactory.Options();
//                options.inSampleSize = 2;
//                Bitmap bitmap = BitmapFactory.decodeFile(imageInSD, null, options);
//                return bitmap;
//            } catch(Exception excepetion) {
//                Log.e(excepetion);
//            }
//        }

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream .toByteArray();
//        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
//
//        byte[] decodedString = Base64.decode(encoded, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        imageView.setImageBitmap(decodedByte);




//        Bitmap decodedByte;
//        byte[] decodedString;
//
//        try {
//
//            String encoded = animalMessage.get(6);
//            Process p = Runtime.getRuntime().exec(encoded);
//            p.waitFor();
//            decodedString = Base64.decode(encoded, Base64.DEFAULT);
//
//            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            imageView.setImageBitmap(decodedByte);
//
//        } catch (InterruptedException | IOException e) {
//            e.printStackTrace();
//        }

    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}