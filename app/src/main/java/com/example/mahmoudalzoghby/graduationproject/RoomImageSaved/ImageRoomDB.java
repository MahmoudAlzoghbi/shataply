/*
package com.example.mahmoudalzoghby.graduationproject.RoomImageSaved;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {SaveImage.class} , version = 1)
public abstract class ImageRoomDB extends RoomDatabase {

    public abstract ImageDao imageDao();

    private static volatile ImageRoomDB INSTANCE;

    static ImageRoomDB getDatabase(final Context context){

        if (INSTANCE == null){
            synchronized (ImageRoomDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext() , ImageRoomDB.class , "image_db").build();
                }
            }
        }
        return INSTANCE;

    }


}
*/
