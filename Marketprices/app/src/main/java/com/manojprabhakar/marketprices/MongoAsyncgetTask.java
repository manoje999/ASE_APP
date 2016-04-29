package com.manojprabhakar.marketprices;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ManojPrabhakar on 4/12/2016.
 */
public  class MongoAsyncgetTask extends AsyncTask<searchdetails, Void, String>{

    @Override
    protected String doInBackground(searchdetails... params) {
        searchdetails finddetails = params[0];
       // String urlString ="http://localhost:8071/MongoRestServiceExample/Market";
        InputStream inputStream;
        StringBuilder result = null;

        try
        {
            URL url = new URL("http://10.0.2.2:8071/MongoRestServiceExample/Market");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
               //result=null;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //return e.getMessage();
        }



  //      HttpResponse response = httpClient.execute(url);
        return result.toString();
    }

             }
