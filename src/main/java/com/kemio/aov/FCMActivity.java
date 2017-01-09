package com.kemio.aov;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FCMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);

        final EditText edtID = (EditText) findViewById(R.id.edtID);
        final EditText edtTitle = (EditText) findViewById(R.id.edtTitulo);
        final EditText edtContent = (EditText) findViewById(R.id.edtContenido);
        final Spinner dropdown = (Spinner) findViewById(R.id.spnActivityType);
        Button btnSend = (Button) findViewById(R.id.btnSend);

        String[] items = new String[]{"Actividad", "Cobertura", "Capacitación", "Difusión"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpAsyncTask().execute("https://fcm.googleapis.com/fcm/send", edtID.getText().toString(), edtTitle.getText().toString(), edtContent.getText().toString(), Integer.toString(dropdown.getSelectedItemPosition()));
            }
        });
    }

    public static String POST(String url, int id, String title, String content, int activityType) {
        InputStream inputStream;
        String result = null;
        try {


            HttpClient httpclient = new DefaultHttpClient();


            HttpPost httpPost = new HttpPost(url);

            String json = "{\"to\":\"/topics/testTopic\",\"data\":{\"id\":\"" + id + "\",\"title\":\"" + title + "\",\"content\":\"" + content + "\",\"activityType\":\"" + activityType + "\"}}";

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Authorization", "key=AAAA5fPdBb8:APA91bF-uSnU4ZsRpt7St5llndRKW0Aj04CMdKAfmjUbiISj7vfBXHZVMcvpqAh7loE2tNw8kdzzBZZs761-WJJ9XU95K7BD5bI7_SIVRG8iornhre1WFbVLrtge4lbg73rxfnFXV_Kw");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }


        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... data) {
            return POST(data[0], Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[4]));
        }


        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
            System.out.println(result);
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
