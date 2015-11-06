package com.lyricaloriginal.okhttpsample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class LoggingSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging_sample);

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String result = null;
                try {
                    downloadText();
                } catch (Exception ex) {
                    Log.d(getClass().getName(), ex.getMessage(), ex);
                }
                return null;
            }
        };
        task.execute();
    }

    public void downloadText() throws Exception {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new Interceptor() {
            @Override public Response intercept(Interceptor.Chain chain) throws IOException {
                long t1 = System.nanoTime();
                Request request = chain.request();
                appendText(String.format("Sending request %s on %s%n%s",
                        request.httpUrl(), chain.connection(), request.headers()));
                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
                appendText(String.format("Received response for %s in %.1fms%n%s",
                        request.httpUrl(), (t2 - t1) / 1e6d, response.headers()));
                return response;
            }
        });

        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        Response response = client.newCall(request).execute();
        response.body().close();
        // string()などでデータを取得しない場合は必ずクローズしないといけない。
        // string()などでは最後にclose処理を行っているので。
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
