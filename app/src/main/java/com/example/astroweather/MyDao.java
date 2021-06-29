package com.example.astroweather;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addCity(FavouriteCity... favouriteCity);

    @Query("SELECT * FROM favourite_city")
    List<FavouriteCity> getAll();

    @Delete
    void delete(FavouriteCity favouriteCity);

    @Query("DELETE FROM favourite_city WHERE fav_name == :name")
    public void deletecity(String name);
}
