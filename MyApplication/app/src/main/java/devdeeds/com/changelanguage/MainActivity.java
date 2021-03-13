package devdeeds.com.changelanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * Created by devdeeds.com on 18/4/17.
 * by Jayakrishnan P.M
 */

public class MainActivity extends AppCompatActivity {

    private String mLanguageCode = "fr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Change English to French when user clicked the button.
        findViewById(R.id.btnChangeLangView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change Application level locale
                LocaleHelper.setLocale(MainActivity.this, mLanguageCode);

                //It is required to recreate the activity to reflect the change in UI.
                recreate();
            }
        });
    }
}