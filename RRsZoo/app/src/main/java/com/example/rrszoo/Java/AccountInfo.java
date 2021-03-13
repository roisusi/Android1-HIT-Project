package com.example.rrszoo.Java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rrszoo.R;

import java.util.ArrayList;
import java.util.List;

public class AccountInfo extends AppCompatActivity {

    private GetInformation getInformation;
    private SendInformation sendInformation;
    private List<String> messageToServer;
    private List<String> stringFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
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


}