package com.example.testliveedgedetection;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adityaarora.liveedgedetection.activity.ScanActivity;
import com.adityaarora.liveedgedetection.constants.ScanConstants;
import com.adityaarora.liveedgedetection.util.ScanUtils;
import com.adityaarora.liveedgedetection.view.TouchImageView;
import com.example.testliveedgedetection.GeneratedCode.Result_Generate;
import com.example.testliveedgedetection.hexacode.Area;
import com.example.testliveedgedetection.hexacode.Code;
import com.example.testliveedgedetection.hexacode.CodeCountDigite;
import com.example.testliveedgedetection.hexacode.Functionality;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    private ImageView scannedImageView;
    ViewGroup constrantId;
    Button generateButton;
    Button scanButton;

    EditText[] editTexts;
    EditText width;
    EditText height;
    EditText decStandardization;
    EditText decSector;
    EditText decTimeStamp;
    EditText decSection;
    EditText decItem;
    EditText decQuality;
    EditText decUnit;
    EditText decValidity;
    EditText decEntity;
    EditText hexStandardization;
    EditText hexSector;
    EditText hexTimeStamp;
    EditText hexSection;
    EditText hexItem;
    EditText hexQuality;
    EditText hexUnit;
    EditText hexValidity;
    EditText hexEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Generate Code");
        constrantId = findViewById(R.id.constrantId);
        scannedImageView = findViewById(R.id.scanned_image);

        generateButton = findViewById(R.id.btnGenerate);
        scanButton = findViewById(R.id.btnScan);
        getAllById();

        final Intent ResultGenerateIntent = new Intent(this, Result_Generate.class);
        generateButton.setOnClickListener(view -> {
            String[] a = new String[editTexts.length];
            for (int i = 0; i < a.length; ++i) {
                a[i] = editTexts[i].getText().toString();
            }
            boolean s = Validation(a);
            if (s) {
                startActivity(ResultGenerateIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));

            } else {
                makeToast("Make Sure Your Fields is Correct");
            }

        });
        scanButton.setOnClickListener(view -> startScan());
    }

    private void getAllById() {
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        decStandardization = findViewById(R.id.decStandardization);
        decSector = findViewById(R.id.decSector);
        decTimeStamp = findViewById(R.id.decTime);
        decSection = findViewById(R.id.decSection);
        decItem = findViewById(R.id.decItem);
        decQuality = findViewById(R.id.decQuality);
        decUnit = findViewById(R.id.decUnit);
        decValidity = findViewById(R.id.decValidty);
        decEntity = findViewById(R.id.decEntity);
        hexStandardization = findViewById(R.id.hexStandardization);
        hexSector = findViewById(R.id.hexSector);
        hexTimeStamp = findViewById(R.id.hexTime);
        hexSection = findViewById(R.id.hexSection);
        hexItem = findViewById(R.id.hexItem);
        hexQuality = findViewById(R.id.hexQuality);
        hexUnit = findViewById(R.id.hexUnit);
        hexValidity = findViewById(R.id.hexValidty);
        hexEntity = findViewById(R.id.hexEntity);
    }

    private void startScan() {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (null != data && null != data.getExtras()) {
                    String filePath = data.getExtras().getString(ScanConstants.SCANNED_RESULT);
                    Bitmap baseBitmap = ScanUtils.decodeBitmapFromFile(filePath, ScanConstants.IMAGE_NAME);
                    scannedImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    scannedImageView.setImageBitmap(baseBitmap);


                    Functionality functionality = new Functionality(constrantId, this);
                    /*String std = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Standardization).getHXValue();
                    String sector = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Sector).getHXValue();
                    String entity = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Entity).getHXValue();
                    String time = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.TimeStump).getHXValue();
                    String section = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Section).getHXValue();
                    String item = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Item).getHXValue();
                    String quality = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Quality).getHXValue();
                    String unit = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Unit).getHXValue();
                    String validity = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Validty).getHXValue();
                    ArrayList<Area> arrayList = functionality.ConvertCode(900, 600, std, item, quality, sector, section, unit, time, entity, validity);
                    functionality.DrawCode(arrayList);*/


                    File file = new File(filePath);
                    Code code = null;
                    try {
                        code = functionality.ConvertHXtoDC(baseBitmap);
                        makeToast("Done scanning: " + code.Item);
                        setGeneratedValues(code);
                    } catch (IOException e) {
                        e.printStackTrace();
                        makeToast("Error");
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                finish();
            }
        }
    }

    private void setGeneratedValues(Code code) {
        decStandardization.setText(String.valueOf(code.Standardization));
        decSector.setText(String.valueOf(code.Sector));
        decTimeStamp.setText(String.valueOf(code.TimeStump));
        decSection.setText(String.valueOf(code.Section));
        decItem.setText(String.valueOf(code.Item));
        decQuality.setText(String.valueOf(code.Quality));
        decUnit.setText(String.valueOf(code.Unit));
        decValidity.setText(String.valueOf(code.Validty));
        decEntity.setText(String.valueOf(code.Entity));
        hexStandardization.setText(String.valueOf(code.StandardizationHX));
        hexSector.setText(String.valueOf(code.SectorHX));
        hexTimeStamp.setText(String.valueOf(code.TimeStumpHX));
        hexSection.setText(String.valueOf(code.SectionHX));
        hexItem.setText(String.valueOf(code.ItemHX));
        hexQuality.setText(String.valueOf(code.QualityHX));
        hexUnit.setText(String.valueOf(code.UnitHX));
        hexValidity.setText(String.valueOf(code.ValidtyHX));
        hexEntity.setText(String.valueOf(code.EntityHX));
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
