package com.example.mapstesting.RoomTesting;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "water_tracking")
public class WaterTracking {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "date")
    public LocalDate date;

    @ColumnInfo(name = "water_intake")
    public int waterIntake;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "description")
    public String description;

    public WaterTracking() {
    }

    // Constructor for initialization
    public WaterTracking(LocalDate date, int waterIntake, String category, String description) {
        this.date = date;
        this.waterIntake = waterIntake;
        this.category = category;
        this.description = description;
    }

}
