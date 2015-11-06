package com.lyricaloriginal.okhttpsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.get_sample_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetSampleActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.post_json_sample_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostJsonSampleActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.auth_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthenticateActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.logging_sample_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoggingSampleActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.disk_cache_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiskCacheActivity.class);
                startActivity(intent);
            }
        });
    }
}
