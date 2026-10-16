package com.example.myapplication; // ודא שזה תואם לשם החבילה שלך

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvJob = findViewById(R.id.tvJob);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvSite = findViewById(R.id.tvSite);

        // קבלת הנתונים (השתמשנו בקבועים שהגדרנו ב-MainActivity)
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.KEY_NAME);
        String job = intent.getStringExtra(MainActivity.KEY_JOB);
        String phone = intent.getStringExtra(MainActivity.KEY_PHONE);
        String site = intent.getStringExtra(MainActivity.KEY_SITE);

        // הצגת הנתונים
        tvName.setText(name);
        tvJob.setText(job);
        tvPhone.setText(phone);
        tvSite.setText(site);

        // כפתור חיוג (מסר מרומז)
        findViewById(R.id.btnDial).setOnClickListener(v -> {
            try {
                Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(dial);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "אין אפליקציה מתאימה לחיוג", Toast.LENGTH_SHORT).show();
            }
        });

        // כפתור דפדפן (מסר מרומז)
        findViewById(R.id.btnWeb).setOnClickListener(v -> {
            try {
                String finalSite = site;
                if (!finalSite.startsWith("http://") && !finalSite.startsWith("https://")) {
                    finalSite = "https://" + finalSite;
                }
                Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(finalSite));
                startActivity(web);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "אין דפדפן מותקן", Toast.LENGTH_SHORT).show();
            }
        });

        // כפתור שיתוף (מסר מרומז)
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            try {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, "כרטיס ביקור:\n" + name + "\n" + job + "\n" + phone + "\n" + site);
                startActivity(Intent.createChooser(share, "שתף כרטיס עם…"));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "אין אפליקציית שיתוף", Toast.LENGTH_SHORT).show();
            }
        });

        // כפתור חזרה
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}