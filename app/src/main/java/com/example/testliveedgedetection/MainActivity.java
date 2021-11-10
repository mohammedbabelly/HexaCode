package com.example.testliveedgedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.adityaarora.liveedgedetection.activity.ScanActivity;
import com.adityaarora.liveedgedetection.constants.ScanConstants;
import com.adityaarora.liveedgedetection.util.ScanUtils;
import com.example.testliveedgedetection.GeneratedCode.Result_Generate;
import com.example.testliveedgedetection.hexacode.Code;
import com.example.testliveedgedetection.hexacode.CodeCountDigite;
import com.example.testliveedgedetection.hexacode.Functionality;
import com.example.testliveedgedetection.hexacode.Range;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    private ImageView scannedImageView;
    LinearLayout parentLinear;
    Button generateButton;
    Button scanButton;

    ArrayList<EditText> editTexts = new ArrayList<>();
    ArrayList<Range> ranges = new ArrayList<>();
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
        scannedImageView = findViewById(R.id.scanned_image);
        parentLinear = findViewById(R.id.parentLinear);
        generateButton = findViewById(R.id.btnGenerate);
        scanButton = findViewById(R.id.btnScan);
        getAllById();

        generateButton.setOnClickListener(view -> {
            setValidationRanges();
            boolean validated = true;
            for (int i = 0; i < editTexts.size(); ++i) {
                int value = 0;
                try {
                    value = Integer.valueOf(editTexts.get(i).getText().toString());
                } catch (NumberFormatException e) {
                }
                if (!(value >= ranges.get(i).start && value <= ranges.get(i).end)) {
                    validated = false;
                    editTexts.get(i).setError(ranges.get(i).start + " ~ " + ranges.get(i).end);
//                    break;
                }
            }
            if (validated) {
                Functionality functionality = new Functionality(parentLinear, this, new TextView(this));
                String std = functionality.ConvertDCtoHX(Integer.valueOf(decStandardization.getText().toString()), new ArrayList<>(), CodeCountDigite.Standardization).getHXValue();
                String sector = functionality.ConvertDCtoHX(Integer.valueOf(decSector.getText().toString()), new ArrayList<>(), CodeCountDigite.Sector).getHXValue();
                String entity = functionality.ConvertDCtoHX(Integer.valueOf(decEntity.getText().toString()), new ArrayList<>(), CodeCountDigite.Entity).getHXValue();
                String time = functionality.ConvertDCtoHX(Integer.valueOf(decTimeStamp.getText().toString()), new ArrayList<>(), CodeCountDigite.TimeStump).getHXValue();
                String section = functionality.ConvertDCtoHX(Integer.valueOf(decSection.getText().toString()), new ArrayList<>(), CodeCountDigite.Section).getHXValue();
                String item = functionality.ConvertDCtoHX(Integer.valueOf(decItem.getText().toString()), new ArrayList<>(), CodeCountDigite.Item).getHXValue();
                String quality = functionality.ConvertDCtoHX(Integer.valueOf(decQuality.getText().toString()), new ArrayList<>(), CodeCountDigite.Quality).getHXValue();
                String unit = functionality.ConvertDCtoHX(Integer.valueOf(decUnit.getText().toString()), new ArrayList<>(), CodeCountDigite.Unit).getHXValue();
                String validity = functionality.ConvertDCtoHX(Integer.valueOf(decValidity.getText().toString()), new ArrayList<>(), CodeCountDigite.Validty).getHXValue();
                Intent resultGenerateIntent = new Intent(this, Result_Generate.class);
                resultGenerateIntent.putExtra("std", std);
                resultGenerateIntent.putExtra("sector", sector);
                resultGenerateIntent.putExtra("entity", entity);
                resultGenerateIntent.putExtra("time", time);
                resultGenerateIntent.putExtra("section", section);
                resultGenerateIntent.putExtra("item", item);
                resultGenerateIntent.putExtra("quality", quality);
                resultGenerateIntent.putExtra("unit", unit);
                resultGenerateIntent.putExtra("validity", validity);

                startActivity(resultGenerateIntent);
            } else {
                makeToast("One or more validations failed");
            }
        });
        scanButton.setOnClickListener(view ->
                startScan()
        );
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
        editTexts.clear();
        editTexts.add(width);
        editTexts.add(height);
        editTexts.add(decStandardization);
        editTexts.add(decSector);
        editTexts.add(decTimeStamp);
        editTexts.add(decSection);
        editTexts.add(decItem);
        editTexts.add(decQuality);
        editTexts.add(decUnit);
        editTexts.add(decValidity);
        editTexts.add(decEntity);
        editTexts.add(hexStandardization);
        editTexts.add(hexSector);
        editTexts.add(hexTimeStamp);
        editTexts.add(hexSection);
        editTexts.add(hexItem);
        editTexts.add(hexQuality);
        editTexts.add(hexUnit);
        editTexts.add(hexValidity);
        editTexts.add(hexEntity);

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
                    Functionality functionality = new Functionality(parentLinear, this, new TextView(this));
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

    private void setValidationRanges() {
        ranges.clear();
        //width;
        ranges.add(new Range(300, 1920));
        //height;
        ranges.add(new Range(300, 1920));
        // decStandardization;
        ranges.add(new Range(0, 262143));
        // decSector;
        ranges.add(new Range(0, 262143));
        // decTimeStamp;
        ranges.add(new Range(0, 687194761/*735*/));
        // decSection;
        ranges.add(new Range(0, 4095));
        // decItem;
        ranges.add(new Range(0, 262143));
        // decQuality;
        ranges.add(new Range(0, 63));
        // decUnit;
        ranges.add(new Range(0, 4095));
        // decValidity;
        ranges.add(new Range(0, 262143));
        // decEntity;
        ranges.add(new Range(0, 1073741823));
        // hexStandardization;
        ranges.add(new Range(0, 262143));
        // hexSector;
        ranges.add(new Range(0, 262143));
        // hexTimeStamp;
        ranges.add(new Range(0, 687194761/*735*/));
        // hexSection;
        ranges.add(new Range(0, 4095));
        // hexItem;
        ranges.add(new Range(0, 262143));
        // hexQuality;
        ranges.add(new Range(0, 63));
        // hexUnit;
        ranges.add(new Range(0, 4095));
        // hexValidity;
        ranges.add(new Range(0, 262143));
        // hexEntity;
        ranges.add(new Range(0, 1073741823));
    }
}
