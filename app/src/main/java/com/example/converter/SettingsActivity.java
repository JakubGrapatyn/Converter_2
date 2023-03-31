package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private Switch decison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        decison = findViewById(R.id.switchTheme);
        decison.setChecked(readPreferences());
        if (decison != null)
        {
            decison.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    SharedPreferences prefs = getSharedPreferences("Set", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("theme",decison.isChecked());
                    editor.apply();
                }
            });

        }
    }
    Boolean readPreferences(){
        SharedPreferences prefs=getSharedPreferences("Set",MODE_PRIVATE);
        return prefs.getBoolean("theme",false);
    }
}
