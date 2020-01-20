package com.example.subiect2e;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceCardDao {
    @Insert
    public void insert(ServiceCard serviceCard);

    @Query("SELECT * FROM serviceCards")
    List<ServiceCard> getServiceCards();

    @Query("DELETE FROM serviceCards")
    public void deleteAll();
}
