package com.example.subiect2e;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.security.spec.ECField;
import java.util.Calendar;
import java.util.Date;

public class AddServiceCard extends AppCompatActivity {
    EditText serviceNumber;
    Spinner serviceDepartment;
    EditText serviceCost;
    DatePicker serviceDate;
    int s_serviceNumber;
    String s_serviceDepartment;
    double s_serviceCost;
    int ok=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_card);
        serviceNumber=findViewById(R.id.et_serviceNumber);
        serviceDepartment=findViewById(R.id.spinner_department);
        serviceCost=findViewById(R.id.et_serviceCost);
        serviceDate=findViewById(R.id.datePicker);

        Intent intent=getIntent();
        if(intent.hasExtra("serviceCard")){
            ServiceCard serviceCard=(ServiceCard)intent.getSerializableExtra("serviceCard");
            serviceNumber.setText(String.valueOf(serviceCard.getServiceNumber()));
            serviceNumber.setFocusable(false);
            if(serviceCard.getServiceDepartment().equals("electrical"))
                serviceDepartment.setSelection(0);
            else
                serviceDepartment.setSelection(1);
            serviceCost.setText(String.valueOf(serviceCard.getServiceCost()));
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(serviceCard.getServiceDate().getTime());
            serviceDate.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        }
    }

    public void button_add(View view) {
        ok=1;
        try{
            s_serviceNumber=Integer.parseInt(serviceNumber.getText().toString());
        } catch (Exception ex){
            ex.printStackTrace();
            ok=0;
            Toast.makeText(this, "Please put a number for service.", Toast.LENGTH_LONG).show();
        }

        try{
            s_serviceDepartment=String.valueOf(serviceDepartment.getSelectedItem().toString());
        } catch(Exception e){
            e.printStackTrace();
            ok=0;
            Toast.makeText(this, "Please select a service department.", Toast.LENGTH_LONG).show();
        }

        try{
            s_serviceCost=Double.parseDouble(serviceCost.getText().toString());
        } catch(Exception e){
            e.printStackTrace();
            ok=0;
            Toast.makeText(this, "Please put a numerica service cost.", Toast.LENGTH_LONG).show();
        }
        Calendar s_serviceDate=Calendar.getInstance();
        s_serviceDate.set(serviceDate.getYear(), serviceDate.getMonth(), serviceDate.getDayOfMonth());

        if(ok==1){
            ServiceCard serviceCard=new ServiceCard(s_serviceNumber, s_serviceDepartment, s_serviceCost, s_serviceDate.getTime());
            Intent intent=new Intent();
            intent.putExtra("serviceCard", serviceCard);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
