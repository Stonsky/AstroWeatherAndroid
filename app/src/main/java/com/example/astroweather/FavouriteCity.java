package com.example.astroweather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_city")
public class FavouriteCity {
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "fav_name")
    private String name;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
