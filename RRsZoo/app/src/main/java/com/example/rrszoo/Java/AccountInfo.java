package com.example.rrszoo.Java;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rrszoo.R;

import java.util.ArrayList;
import java.util.List;

public class AccountInfo extends AppCompatActivity {

    private GetInformation getInformation;
    private SendInformation sendInformation;
    private List<String> messageToServer;
    private List<String> stringFromServer;
    ZooLanguage zooLanguage;
    private Menu menu;
    private MenuInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zooLanguage = new ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE));
        setContentView(zooLanguage.isEnglish() ?  R.layout.activity_account_info : R.layout.activity_account_info_heb);
        getAccountInfo();

        Button back = (Button) findViewById(R.id.backAcc);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                intent.putExtra("Admin", stringFromServer.get(2));
                startActivity(intent);
            }
        });
    }

    private void getAccountInfo() {
        messageToServer = new ArrayList<>();
        if (zooLanguage.isEnglish()) {
            messageToServer.add("En");
        }
        else {
            messageToServer.add("He");
        }
        messageToServer.add("Account");
        getInformation = new GetInformation(messageToServer, AccountInfo.this);
        getInformation.execute();

    }

    public void setInfo(List<String> info) {
        stringFromServer = new ArrayList<>();
        stringFromServer = info;

        TextView name = (TextView) findViewById(R.id.nameAccDB);
        name.setText(stringFromServer.get(0));
        TextView email = (TextView) findViewById(R.id.emailAccDB);
        email.setText(stringFromServer.get(3));
        TextView admin = (TextView) findViewById(R.id.adminAccDB);

        if (stringFromServer.get(2).equals("true"))
            admin.setText("Yes");
        else
            admin.setText("No");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        this.menu = menu;
        inflater.inflate(R.menu.change_language, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Hebrew:
                zooLanguage.setHebrew();
                setContentView(R.layout.activity_account_info_heb);
                item.setChecked(true);
                break;
            case R.id.English:
                zooLanguage.setEnglish();
                setContentView(R.layout.activity_account_info);
                item.setChecked(true);
                break;
            default:
                return super.onOptionsItemSelected(item); //react to many chooses

        }
        return true;
    }


}