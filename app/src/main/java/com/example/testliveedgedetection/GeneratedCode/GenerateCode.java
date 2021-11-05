package com.example.testliveedgedetection.GeneratedCode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.testliveedgedetection.R;
import com.example.testliveedgedetection.hexacode.CodeCountDigite;

import java.util.ArrayList;

public class GenerateCode extends AppCompatActivity {
    private EditText[] editTexts;
    private EditText width;
    private EditText height;
    RadioGroup radioGroup;
    Button generate;

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


        generate.setOnClickListener(v -> {
            String[] a = new String[ids.length];
            for (int i = 0; i < a.length; ++i) {
                a[i] = editTexts[i].getText().toString();
            }
            boolean s = Validation(a);
            if (s) {
                startActivity(new Intent(this, Result_Generate.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));

            } else {
                Toast.makeText(this, "Make Sure Your Feilds is Correct", Toast.LENGTH_LONG).show();
            }

        });


    }

    boolean Validation(String[] s) {
        for (int i = 0; i < s.length; ++i) {
            if (s[i].isEmpty()) return false;
        }
        return true;
    }
}