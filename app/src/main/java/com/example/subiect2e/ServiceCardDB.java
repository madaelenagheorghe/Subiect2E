package com.example.subiect2e;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = ServiceCard.class, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ServiceCardDB extends RoomDatabase {
    private final static String DBNAME="serviceCards";
    private static ServiceCardDB instance;

    public static ServiceCardDB getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context, ServiceCardDB.class, DBNAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract ServiceCardDao getServicesDao();
}
