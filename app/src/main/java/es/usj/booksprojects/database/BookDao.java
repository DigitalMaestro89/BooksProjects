package es.usj.booksprojects.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.usj.booksprojects.model.Book;

@Dao
public interface BookDao {
    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Query("SELECT * FROM Books")
    List<Book> getAllBooks();

    @Query("SELECT EXISTS (SELECT 1 FROM Books WHERE `key` = :key LIMIT 1)")
    boolean containsBook(String key);

    @Query("DELETE FROM Books WHERE `key` = :key")
    void deleteBookByKey(String key);

}
