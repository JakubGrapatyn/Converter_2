package com.example.converter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner conversion;
    private EditText number;
    private TextView result;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversion = findViewById(R.id.spinner_conversion);
        number = findViewById(R.id.editText_input);
        result = findViewById(R.id.textView_result);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("score",result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState((saveInstanceState));
        result.setText(saveInstanceState.getString("score",""));
    }
    public void onClickConvertButton(View view) {
        int method;
        int inBase;
        int outBase;
        String outcome;

        method = (int)conversion.getSelectedItemId();
        switch(method){
            case 0:
            default:
                inBase = 10;
                outBase = 2;
                break;
            case 1:
                inBase = 10;
                outBase = 16;
                break;
            case 2:
                inBase = 2;
                outBase = 10;
                break;
            case 3:
                inBase = 2;
                outBase = 16;
                break;
            case 4:
                inBase = 16;
                outBase = 10;
                break;
            case 5:
                inBase = 16;
                outBase = 2;
                break;
        }
        outcome= convert(number.getText().toString(),inBase,outBase);
        if (TextUtils.isEmpty(outcome)){
            statement();
        }
        else {
            result.setText(outcome.toUpperCase());
        }
    }
    public  String convert(String number, int inBase, int outBase){
        try {
            return Integer.toString(Integer.parseInt(number, inBase), outBase);
        }catch (Exception e){
            return "";
        }
    }

    private  void  statement(){
        Vibrator v;
        final AlertDialog.Builder adb= new AlertDialog.Builder(this);
        adb.setTitle(R.string.statementTitle);
        adb.setMessage(R.string.statementMessage);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
        AlertDialog ad =adb.create();
        ad.show();
    }
}