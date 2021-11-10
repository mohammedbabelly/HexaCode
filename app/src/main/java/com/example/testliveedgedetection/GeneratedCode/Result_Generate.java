package com.example.testliveedgedetection.GeneratedCode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testliveedgedetection.R;
import com.example.testliveedgedetection.hexacode.Area;
import com.example.testliveedgedetection.hexacode.Functionality;
import com.google.gson.Gson;

import java.util.ArrayList;


public class Result_Generate extends AppCompatActivity {
    ViewGroup constrantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_generate);
        getSupportActionBar().setTitle("Generated Code");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        constrantId = findViewById(R.id.constrantId);
        Functionality functionality = new Functionality(constrantId, this, new TextView(this));
        String std = getIntent().getStringExtra("std");
        String sector = getIntent().getStringExtra("sector");
        String entity = getIntent().getStringExtra("entity");
        String time = getIntent().getStringExtra("time");
        String section = getIntent().getStringExtra("section");
        String item = getIntent().getStringExtra("item");
        String quality = getIntent().getStringExtra("quality");
        String unit = getIntent().getStringExtra("unit");
        String validity = getIntent().getStringExtra("validity");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        ArrayList<Area> arrayList = functionality.ConvertCode(width, width - width / 3, std, item, quality, sector, section, unit, time, entity, validity);
        functionality.DrawCode(arrayList);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}