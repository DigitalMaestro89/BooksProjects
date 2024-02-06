package fr.eilco.booksprojects.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import fr.eilco.booksprojects.model.Book;

@Database(entities = {Book.class}, version = 4)
@TypeConverters(ClassConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "favoriteDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
