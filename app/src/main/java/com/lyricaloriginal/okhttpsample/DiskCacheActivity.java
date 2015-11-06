package com.lyricaloriginal.okhttpsample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

public class DiskCacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_cache);

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String result = null;
                try {
                    run();
                } catch (Exception ex) {
                    Log.d(getClass().getName(), ex.getMessage(), ex);
                }
                return null;
            }
        };
        task.execute();
    }

    public void run() throws Exception {
        File dir = new File(getFilesDir(), System.currentTimeMillis() + "dir");
        OkHttpClient client = new OkHttpClient();
        client.setCache(new Cache(dir, 10 * 1024 * 1024));

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response1 = client.newCall(request).execute();
        if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);

        String response1Body = response1.body().string();
        appendText("Response 1 response:          " + response1);
        appendText("Response 1 cache response:    " + response1.cacheResponse());
        appendText("Response 1 network response:  " + response1.networkResponse());

        Response response2 = client.newCall(request).execute();
        if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);

        String response2Body = response2.body().string();
        appendText("Response 2 response:          " + response2);
        appendText("Response 2 cache response:    " + response2.cacheResponse());
        appendText("Response 2 network response:  " + response2.networkResponse());

        appendText("Response 2 equals Response 1? " + response1Body.equals(response2Body));
    }

    private void appendText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView txtView = (TextView) findViewById(android.R.id.text1);
                txtView.append(text + "\r\n");
            }
        });
    }
}
