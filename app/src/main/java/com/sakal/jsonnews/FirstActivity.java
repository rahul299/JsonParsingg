package com.sakal.jsonnews;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sakal.jsonnews.adapter.CustomAdapter;
import com.sakal.jsonnews.modul.Modul;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity  {

    private String TAG = FirstActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private List<Modul> contactList = new ArrayList<>();

    // URL to get contacts JSON
//    private static String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=35f2ae92ec3d4b0cbce3cc8e0da0a580";
    String url,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        contactList = new ArrayList<>();
         url = getIntent().getStringExtra("url");
        name = getIntent().getStringExtra("Name");
        this.setTitle(name+"  NEWS");

        recyclerView = (RecyclerView) findViewById(R.id.list_item);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        new GetContacts().execute();
    }



    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(FirstActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("articles");
//                    Log.i("hhh","contacts---"+contacts);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        JSONObject  source = c.getJSONObject("source");
//                        Log.i("hhh","source---"+source);
                        String id = source.getString("id");
                        String name = source.getString("name");
                        String title = c.getString("title");
                        String description = c.getString("description");
                        String url = c.getString("url");
                        String urlToImage = c.getString("urlToImage");
                        String publishedAt = c.getString("publishedAt");
                        Log.i("hhh","publishedAt---"+publishedAt);
                        String content = c.getString("content");
                        Log.i("hhh","content---"+content);

                        Modul movie = new Modul(id ,name,title,description,url,urlToImage,publishedAt,content);
                        contactList.add(movie);



                                         }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            CustomAdapter adapter = new CustomAdapter(contactList,FirstActivity.this);

            recyclerView.setAdapter(adapter);
        }

    }
}
