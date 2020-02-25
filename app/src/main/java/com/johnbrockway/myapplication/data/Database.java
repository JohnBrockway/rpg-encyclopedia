package com.johnbrockway.myapplication.data;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(
        entities = {
                Category.class,
                World.class,
                Entry.class,
                Note.class
        },
        version = 1)
public abstract class Database extends RoomDatabase {
    public abstract DataAccessObject dataAccessObject();
    private static volatile Database INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "rpg_encyclopedia")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
