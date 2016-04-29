package com.manojprabhakar.marketprices;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Spinner vegetables;
    TextView FinalName;
    TextView Form;
    TextView Finalprices1;
    TextView Finalprices2;
    TextView FinalDate;
    StringBuilder ret = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SearchActivity(View v) {

        vegetables = (Spinner) findViewById(R.id.veggie);
        FinalName = (TextView) findViewById(R.id.VegName);
        Finalprices1 = (TextView) findViewById(R.id.price1);
        Finalprices2 = (TextView) findViewById(R.id.price2);
        Form = (TextView) findViewById(R.id.form);
        FinalDate = (TextView) findViewById(R.id.LastUpdated);
        String veg = vegetables.getSelectedItem().toString();
        if (!(veg.equals("Select Vegetable")))

        {


            String url = "http://192.168.0.14:8071/MongoRestServiceExample/Market";
            FourSquAPI fourSquAPI = new FourSquAPI(
                    new VenueSearchListner() {
                        @Override
                        public void service(String result) {
                            try {

                                JSONArray venues = new JSONArray(result);

                                for (int i = 0; i < venues.length(); i++) {
                                    JSONObject obj = venues.getJSONObject(i);
                                    String name = obj.getString("Type");

                                    JSONArray unit = obj.getJSONArray("prices");
                                    for (int j = 0; j < unit.length(); j++) {
                                        JSONObject obj1 = unit.getJSONObject(j);
                                        String name2 = obj1.getString("Name");
                                        String PRC1 = obj1.getString("Price1");
                                        String PRC2 = obj1.getString("Price2");
                                        String date = obj1.getString("lastupdated");
                                        String name1 = obj1.getString("Form");
                                        String veg = vegetables.getSelectedItem().toString();
                                        if (name2.equals(veg))

                                        {
                                            FinalName.setText(name2);
                                            Form.setText(name1);
                                            Finalprices1.setText(PRC1);
                                            Finalprices2.setText(PRC2);
                                            FinalDate.setText(date);
                                            break;
                                        }
                                    }


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
            );
            fourSquAPI.execute(url);

        }
          else
        {
            Toast toast=Toast.makeText(this,"Please Select a Vegetable from the drop down",Toast.LENGTH_LONG);
            toast.show();
        }
    }


}

class FourSquAPI extends AsyncTask<String, String, String> {

    private VenueSearchListner listner;
    public FourSquAPI(VenueSearchListner listner){
        this.listner = listner;
    }


    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        InputStream in;
        StringBuilder result = null;

        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return result.toString();


    }

    protected void onPostExecute(String result)
    {
        listner.service(result);
    }
}


