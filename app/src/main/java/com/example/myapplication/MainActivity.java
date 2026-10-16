package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_NAME = "name";
    public static final String KEY_JOB = "job";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_SITE = "site";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etName = findViewById(R.id.etName);
        EditText etJob = findViewById(R.id.etJob);
        EditText etPhone = findViewById(R.id.etPhone);
        EditText etSite = findViewById(R.id.etSite);

        findViewById(R.id.btnCreate).setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String job = etJob.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String site = etSite.getText().toString().trim();



            if (name.isEmpty()) {
                Toast.makeText(this, "נא להזין שם", Toast.LENGTH_SHORT).show();
                return;
            }
            if (job.isEmpty()) {
                Toast.makeText(this, "נא להזין עבודה", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.isEmpty()) {
                Toast.makeText(this, "נא להזין מספר טלפון", Toast.LENGTH_SHORT).show();
                return;
            }
            if (site.isEmpty()) {
                Toast.makeText(this, "נא להזין שם אתר", Toast.LENGTH_SHORT).show();
                return;
            }


            if (phone.length() != 10 ) {
                Toast.makeText(this, "מספר הטלפון חייב להכיל בדיוק 10 ספרות", Toast.LENGTH_SHORT).show();
                return;
            }




            Intent intent = new Intent(MainActivity.this, CardActivity.class);
            intent.putExtra(KEY_NAME, name);
            intent.putExtra(KEY_JOB, job);
            intent.putExtra(KEY_PHONE, phone);
            intent.putExtra(KEY_SITE, site);
            startActivity(intent);
        });


        findViewById(R.id.btnAbout).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AboutActivity.class)));
    }
}