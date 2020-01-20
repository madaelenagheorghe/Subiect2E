package com.example.subiect2e;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ServiceCard> serviceCards=new ArrayList<>();
    private ServiceCardDB database;
    int ok=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_add(View view) {
        Intent intent=new Intent(this, AddServiceCard.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            ServiceCard serviceCardR=(ServiceCard)data.getSerializableExtra("serviceCard");
            serviceCards.add(serviceCardR);
            Toast.makeText(this, serviceCardR.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void button_list(View view) {
        Intent intent=new Intent(this, ServiceCardList.class);
        intent.putExtra("serviceCards", (Serializable) serviceCards);
        startActivity(intent);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for(int i=0;i<serviceCards.size();i++){
            outState.putSerializable("key"+i, serviceCards.get(i));
        }
        outState.putInt("listSize", serviceCards.size());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int nr=savedInstanceState.getInt("listSize");
        for(int i=0;i<nr;i++)
            serviceCards.add((ServiceCard)savedInstanceState.getSerializable("key"+i));
    }

    public void button_save(View view) {

            database = ServiceCardDB.getInstance(this);
            database.getServicesDao().deleteAll();
            for (ServiceCard s : serviceCards) {
                database.getServicesDao().insert(s);
            }
            List<ServiceCard> serviceCardsDB = database.getServicesDao().getServiceCards();
            for (ServiceCard s : serviceCardsDB) {
                Log.e("SERVICECARD", s.toString());
            }

    }

    public void button_download(View view) {
        if(ok==1){
            JsonRead jsonRead=new JsonRead(){
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    List<ServiceCard> cardsJSON=parseJson(s);
                    for(ServiceCard s1:cardsJSON){
                        serviceCards.add(s1);
                    }
                }
            }; ok=1;
            try{
                jsonRead.execute(new URL("http://pdm.ase.ro/examen/services.json.txt"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else
            Toast.makeText(this, "JSON already parsed", Toast.LENGTH_LONG).show();
    }
}
