package com.example.rrszoo.Java;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rrszoo.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;




public class AnimalPage extends AppCompatActivity {
    Button btnShareLink;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    String shareText = "";
    String imageUri = "";


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

    Target imageContentTarget = new Target() {

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .build();
            if (shareDialog.canShow(SharePhotoContent.class)) {
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();
                shareDialog.show(content);
            } else {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote(shareText)
                        .setShareHashtag(new ShareHashtag.Builder().setHashtag("#RRsZoo").build())
                        .setContentUrl(Uri.parse(imageUri)).
                                build();
                shareDialog.show(linkContent);
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            Log.e(TAG, errorDrawable.toString());
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            Log.e(TAG, "PrepareLoad");
        }
    };


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

        imageUri = animalMessage.get(6);
        ImageView ivBasicImage = (ImageView) findViewById(R.id.animalImageView);
        Picasso.with(this).load(imageUri).into(ivBasicImage);


        shareAnimal();
    }

    public void shareAnimal() {

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        btnShareLink = (Button)findViewById(R.id.fbshare);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.e(TAG, "Shared successfully");
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "Shared canceled!");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, error.getMessage());

            }
        });

        btnShareLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView facebookText = (TextView) findViewById(R.id.facebookText);
                shareText = facebookText.getText().toString();
                Picasso.with(getBaseContext()).load("https://cdn1-www.superherohype.com/assets/uploads/2013/11/batmane3-1.jpg").into(imageContentTarget);
            }
        });
    }

}