package com.example.subiect2e;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonRead extends AsyncTask<URL, Void, String> {
    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection conn=null;
        StringBuilder sb=new StringBuilder();
        try{
            conn=(HttpURLConnection)urls[0].openConnection();
            conn.setRequestMethod("GET");
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            String line="";
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (conn!=null)
                conn.disconnect();
        }
        return sb.toString();
    }

    public List<ServiceCard> parseJson(String json){
        List<ServiceCard> serviceCardsJSON=new ArrayList<>();
        if(json!=null){
            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONArray servicesArray=jsonObject.getJSONArray("services");
                for(int i=0;i<servicesArray.length();i++){
                    JSONObject serviceCardJSON=servicesArray.getJSONObject(i);
                    int number=serviceCardJSON.getInt("regNo");
                    String department=serviceCardJSON.getString("department");
                    double costs=serviceCardJSON.getDouble("costs");
                    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
                    Date date=new Date();
                    try{
                        date=format.parse(serviceCardJSON.getString("date"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ServiceCard sc=new ServiceCard(number,department,costs,date);
                    serviceCardsJSON.add(sc);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.e("JSON", "Json not ok");
        return serviceCardsJSON;
    }
}
