package com.example.tony.weatherapp.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface  ImageDao {

    // Добавление картинок в бд
    @Insert
    void insertAll(Image...image);

    // Удаление Image из бд
    @Delete
    void delete(Image image);

    // Получение всех Image из бд
    @Query("SELECT * FROM image")
    List<Image> getAllImage();

}
