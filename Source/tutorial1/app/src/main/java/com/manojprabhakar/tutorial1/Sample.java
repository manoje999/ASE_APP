package com.manojprabhakar.tutorial1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Sample extends AppCompatActivity {

//    List<Market> itemlist= new ArrayList<Market>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void OnButtnClick(View view){
        EditText text = (EditText)findViewById(R.id.editVenue);
        String text1 = text.getText().toString();
        text1 = text1.replace(" ", "%20");
        String url = "https://api.foursquare.com/v2/venues/search?near=" + text1 + "&categoryId=4bf58dd8d48988d1fa941735&client_id=EKGH2L4JAIZMXZGGRTN3KV5PH0ZQYL3VO4N3OOSU3HKYZWQF&client_secret=RAET0NWZREI3Y5NUPQUSRMECY514AYHEOUU03BFX14SFMVXT&v=20160202";



        FourSquAPI fourSquAPI  = new FourSquAPI(
                new VenueSearchListner() {
                    @Override
                    public void service(String result) {
                        try {

                            JSONObject jsonObject = new JSONObject(result);
                            Results(jsonObject);
                            JSONObject rsponse = jsonObject.optJSONObject("response");

                            JSONArray venues = rsponse.optJSONArray("venues");
                            for(int i=0; i<venues.length(); i++){
                                JSONObject obj = venues.getJSONObject(i);
                                String name = obj.getString("name");
                                String id1 = obj.getString("id");
                                Market market1 = new Market();
                                market1.setName(name);
                                market1.setMarketID(id1);
                               // itemlist.add(market1);
                              //  listView.setAdapter(customListAdapter);



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );


        fourSquAPI.execute(url);
    }

    public void Results(JSONObject jsonObject)
    {
        List<Market> itemlist= new ArrayList<Market>();
        ListView listView = (ListView)findViewById(R.id.listview);
        CustomListAdapter customListAdapter;
        try{
        JSONObject rsponse = jsonObject.optJSONObject("response");

        JSONArray venues = rsponse.optJSONArray("venues");
        for(int i=0; i<venues.length(); i++){
            JSONObject obj = venues.getJSONObject(i);
            String name = obj.getString("name");
            JSONObject loc = obj.getJSONObject("location");
            String id1 = loc.getString("formattedAddress");
            id1 = id1.replace("[","");
            id1 = id1.replace("]","");
            Market market1 = new Market();
            market1.setName(name);
            market1.setMarketID(id1);
            itemlist.add(market1);

        }}

     catch (JSONException e) {
        e.printStackTrace();
    }

        customListAdapter = new CustomListAdapter(this, itemlist);
        listView.setAdapter(customListAdapter);
    }


}

class FourSquAPI extends AsyncTask<String, String, String>{

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
        return  result.toString();

    }

    protected void onPostExecute(String result)
    {
        listner.service(result);
    }
}
