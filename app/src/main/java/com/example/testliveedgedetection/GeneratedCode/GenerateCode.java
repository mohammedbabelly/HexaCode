package com.example.testliveedgedetection.GeneratedCode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.testliveedgedetection.R;


public class GenerateCode extends AppCompatActivity {
    private EditText[] editTexts;
    private EditText width;
    private EditText height;
    RadioGroup radioGroup;
    Button generate;

    private EditText ETstanderdzation;

    private final String[] ids = {
            "standerdzation",
            "sector",
            "time_stamp",
            "section",
            "item",
            "quality",
            "unit",
            "validty",
            "entity"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        radioGroup = findViewById(R.id.radioGroup);
        generate = findViewById(R.id.generate);

        for (int i = 0; i < ids.length; ++i)
            editTexts[i] = findViewById(Integer.parseInt("R.id." + ids[i]));
        final Intent ResultGenerateIntent = new Intent(this, Result_Generate.class);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] a = new String[ids.length];
                for (int i = 0; i < a.length; ++i) {
                    a[i] = editTexts[i].getText().toString();
                }
                boolean s = Validation(a);
                if (s) {
                    startActivity(ResultGenerateIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));

                } else {
                    makeToast("Make Sure Your Feilds is Correct");
                }

            }
        });


    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    boolean Validation(String[] s) {
        for (int i = 0; i < s.length; ++i) {
            if (s[i].isEmpty()) return false;
        }
        return true;
    }
}