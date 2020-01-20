package com.example.subiect2e;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceCardList extends AppCompatActivity {
    ListView listView;
    int prevPos;
    List<ServiceCard> serviceCards=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_card_list);
        listView=findViewById(R.id.lv);
        serviceCards=(List<ServiceCard>)getIntent().getSerializableExtra("serviceCards");
        ServiceCardAdapter adaptor=new ServiceCardAdapter(getApplicationContext(), R.layout.item_layout, serviceCards);
        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), AddServiceCard.class);
                intent.putExtra("serviceCard", serviceCards.get(position));
                prevPos=position;
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            ServiceCard sv=(ServiceCard)data.getSerializableExtra("serviceCard");
            serviceCards.get(prevPos).setServiceNumber(sv.getServiceNumber());
            serviceCards.get(prevPos).setServiceCost(sv.getServiceCost());
            serviceCards.get(prevPos).setServiceDepartment(sv.getServiceDepartment());
            serviceCards.get(prevPos).setServiceDate(sv.getServiceDate());
            ServiceCardAdapter adaptor=new ServiceCardAdapter(getApplicationContext(), R.layout.item_layout, serviceCards);
            listView.setAdapter(adaptor);
        }
    }

    public void button_see(View view) {
        DatePicker dp=findViewById(R.id.dp_list);
        Calendar serviceDateList=Calendar.getInstance();
        serviceDateList.set( dp.getYear(),  dp.getMonth(),  dp.getDayOfMonth());
        List<ServiceCard> serviceCardsDate=new ArrayList<>();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        Date dateList=new Date();
        Date dateComp=new Date();
        try {
            dateList=format.parse(serviceDateList.getTime().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(ServiceCard s:serviceCards){
            try {
                Log.e("PARSE", format.parse(s.getServiceDate().toString()).toString());
                Log.e("PARSE2", dateList.toString());
                if(s.getServiceDate().compareTo(dateList)==0)
                    serviceCardsDate.add(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ServiceCardAdapter adaptor=new ServiceCardAdapter(getApplicationContext(), R.layout.item_layout, serviceCardsDate);
            listView.setAdapter(adaptor);
        }
    }
}
