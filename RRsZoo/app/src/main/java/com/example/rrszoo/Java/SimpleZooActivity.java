package com.example.rrszoo.Java;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rrszoo.R;

import java.util.HashMap;

public class SimpleZooActivity extends AppCompatActivity {
    // false equals hebrew
    boolean isEnglish = true;
    protected HashMap<Integer, Tuple<String, String>> textViewTextMap = new HashMap<Integer, Tuple<String, String>>();

    SimpleZooActivity() {
        isEnglish = true;
    }

    @SuppressLint("NewApi")
    protected void changeLanguage() {
        textViewTextMap.keySet().forEach(textViewId ->  {
            TextView textView = (TextView) findViewById(textViewId);
            textView.setText(" \u200F" + getTextViewText(textViewId));
            textView.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            //textView.setTextDirection(isEnglish ? View.TEXT_DIRECTION_LTR : View.TEXT_DIRECTION_ANY_RTL);
        });
    }

    protected void setEnglish() {
        isEnglish = true;
    }

    protected void setHebrew() {
        isEnglish = false;
    }

    protected void setTextViewText(int textViewId, String englishText, String hebrewText) {
        this.textViewTextMap.put(textViewId, new Tuple(englishText, hebrewText));
    }

    protected String getTextViewText(int textViewId) {
        Tuple<String, String> textViewTexts = textViewTextMap.get(textViewId);
        if (textViewTexts == null) {
            return "";
        }
        return isEnglish ? textViewTexts.x : textViewTexts.y;
    }
}
