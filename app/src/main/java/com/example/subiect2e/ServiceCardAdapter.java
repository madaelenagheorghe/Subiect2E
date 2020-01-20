package com.example.subiect2e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ServiceCardAdapter extends ArrayAdapter<ServiceCard> {
    TextView serviceNumber;
    TextView serviceDepartment;
    TextView serviceCost;
    TextView serviceDate;

    private int resId;


    public ServiceCardAdapter(@NonNull Context context, int resource, @NonNull List<ServiceCard> objects) {
        super(context, resource, objects);
        resId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ServiceCard serviceCard=getItem(position);
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(resId,null);
        serviceNumber=view.findViewById(R.id.tv_serviceNumber);
        serviceDepartment=view.findViewById(R.id.tv_serviceDepartment);
        serviceCost=view.findViewById(R.id.tv_serviceCost);
        serviceDate=view.findViewById(R.id.tv_serviceDate);
        serviceNumber.setText(String.valueOf(serviceCard.getServiceNumber()));
        serviceDepartment.setText(String.valueOf(serviceCard.getServiceDepartment()));
        serviceCost.setText(String.valueOf(serviceCard.getServiceCost()));
        serviceDate.setText(String.valueOf(serviceCard.getServiceDate()));
        return view;
    }
}
