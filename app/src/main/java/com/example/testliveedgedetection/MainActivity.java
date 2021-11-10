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
    private EditText[] editTexts;
    private EditText width;
    private EditText height;
    RadioGroup radioGroup;
    Button generate;
    Button scanButton;
    TextView generatedText;

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

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Generate Code");
        constrantId = findViewById(R.id.constrantId);
        scannedImageView = (ImageView) findViewById(R.id.scanned_image);

        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        radioGroup = findViewById(R.id.radioGroup);
        generate = findViewById(R.id.generate);
        scanButton = findViewById(R.id.scan);
        generatedText = (TextView) findViewById(R.id.generatedText);

//        for (int i = 0; i < ids.length; ++i)
//            editTexts[i] = findViewById(Integer.parseInt("R.id." + ids[i]));
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
                    makeToast("Make Sure Your Fields is Correct");
                }

            }
        });
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });
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

//                    scannedImageView.setVisibility(View.GONE);

                    File file = new File(filePath);
                    Code code = null;
                    try {
                        code = functionality.ConvertHXtoDC(baseBitmap);
                        makeToast("Done scanning: " + code.Item);
                        generatedText.setText("The generated Text is: " + code.toString());
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
