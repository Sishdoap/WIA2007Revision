package com.example.mapstesting.RoomTesting;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Dao
public interface WaterTrackingDAO {

    @Insert
    void insert(WaterTracking waterTracking);

    @Query("SELECT * FROM water_tracking")
    List<WaterTracking> getAll();

    @Query("SELECT * FROM water_tracking WHERE category = :category")
    List<WaterTracking> getByCategory(String category);

    @Query("SELECT * FROM water_tracking WHERE date = :date")
    List<WaterTracking> getByDate(LocalDate date);

    @Query("SELECT SUM(water_intake) FROM water_tracking")
    int getTotalWaterIntake();

    @Query("SELECT COALESCE(SUM(water_intake), 0) FROM water_tracking WHERE date = :date")
    int getTotalWaterIntakeByDate(LocalDate date);

    @Query("SELECT COALESCE(SUM(water_intake), 0) FROM water_tracking WHERE category = :category AND date BETWEEN :startDate AND :endDate")
    int getTotalWaterIntakeByCategoryAndDateRange(String category, LocalDate startDate, LocalDate endDate);




}
