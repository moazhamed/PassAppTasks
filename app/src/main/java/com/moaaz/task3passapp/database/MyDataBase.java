package com.moaaz.task3passapp.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.moaaz.task3passapp.database.dao.LocationDao;
import com.moaaz.task3passapp.model.LocationItem;

@Database(entities = {LocationItem.class} , version = 1 ,exportSchema = false)
public abstract class MyDataBase  extends RoomDatabase {

    public abstract LocationDao LocationDao() ;

    private static MyDataBase myDataBase;


    public static MyDataBase getInstance(Context context) {
        if (myDataBase == null) {
            synchronized (MyDataBase.class) {
                myDataBase =
                        Room.databaseBuilder(context.getApplicationContext(),
                                MyDataBase.class, "Locations-DataBase")
                                // allow queries on the main thread.
                                // Don't do this on a real app! See PersistenceBasicSample for an example.
                                .allowMainThreadQueries()
                                .build();
            }
        }
        return myDataBase;

    }

}
