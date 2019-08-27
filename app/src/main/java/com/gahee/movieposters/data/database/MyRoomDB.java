package com.gahee.movieposters.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LikedMovie.class}, version = 1, exportSchema = false)
public abstract class MyRoomDB extends RoomDatabase {

    private static final String TAG = "MyRoomDB";

    public abstract MyRoomDaos daos();
    private static final String DATABASE_NAME = "likedMoviesDB";
    private static volatile MyRoomDB INSTANCE;

    public static MyRoomDB getDatabase(Context context){
        Log.d(TAG, "getDatabase running ... ");

        if(INSTANCE == null){
            synchronized (MyRoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MyRoomDB.class, DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
