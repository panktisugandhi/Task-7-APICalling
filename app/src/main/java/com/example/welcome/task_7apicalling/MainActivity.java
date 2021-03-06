package com.example.welcome.task_7apicalling;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


//    private ArrayList<Post> row = new ArrayList<Post>();
//    private ListView list;
//    private CustomRows  adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Myclass().execute("https://jsonplaceholder.typicode.com/posts");
        //        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//
//        list = (ListView) findViewById(R.id.list_item);
//        adapter = new CustomRows(this,row);
//        list.setAdapter(adapter);
//
//        HttpURLConnection connection;
//        URL url = null;
//        try {
//            url = new URL("https://jsonplaceholder.typicode.com/posts");
//            try {
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//            InputStream stream = connection.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            try {
//                while ((line = reader.readLine()) != null) {
//
//                    buffer.append(line);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String bufferString = buffer.toString();
//
//
//            try {
//                JSONArray rootarray = new JSONArray(bufferString);
//                for (int i = 0; i < rootarray.length(); i++) {
//
//                    JSONObject obj = rootarray.optJSONObject(i);
//                    int userId = obj.getInt("userId");
//                    int id = obj.getInt("id");
//                    String title = obj.getString("title");
//                    String body = obj.getString("body");
//
//                    Post p = new Post();
//                    p.setId(id);
//                    p.setUid(userId);
//                    p.setTitle(title);
//                    p.setBody(body);
//                    row.add(p);
//
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }


    }
    class Myclass extends AsyncTask<String,Void,String>{

        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection;
        URL url = null;
       try {
            url = new URL("https://jsonplaceholder.typicode.com/posts");
           try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);
                }

                String bufferString = buffer.toString();
               return bufferString;
                }catch (IOException e) {
                    e.printStackTrace();
                }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                        return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                    if(dialog.isShowing()){

                        dialog.dismiss();
                    }
                 ArrayList<Post> row = new ArrayList<Post>();

                try {

                JSONArray rootarray = new JSONArray(s);
                for (int i = 0; i < rootarray.length(); i++) {
                    JSONObject obj = rootarray.optJSONObject(i);
                    int userId = obj.getInt("userId");
                    int id = obj.getInt("id");
                    String title = obj.getString("title");
                    String body = obj.getString("body");

                    Post p = new Post();
                    p.setId(id);
                    p.setUid(userId);
                    p.setTitle(title);
                    p.setBody(body);
                    row.add(p);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

                ListView list = (ListView) findViewById(R.id.list_item);
                CustomRows adapter = new CustomRows(MainActivity.this,row);
                list.setAdapter(adapter);

            }
        }
}
