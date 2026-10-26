package pl.zabrze.zs10.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NauczycielDAO {

    @Insert
    void wstawNAuczycielaDoBazy(Nauczyciel nauczyciel);

    @Insert
    void wstawListeNauczycieli(Nauczyciel ... nauczyciele);

    @Delete
    void usunNauczycielaZBazy(Nauczyciel nauczyciel);

    @Update
    void zaktualizujDaneNAuczyciela(Nauczyciel nauczyciel);

    @Query("SELECT * from nauczyciele")
    List<Nauczyciel> zwrocWszystkichNauczycieliZBazy();

    @Query("SELECT imie from nauczyciele WHERE przedmiot = :przedmiotArgument")
    List<String> zwrocImionaNauczycieliZPrzedmiotu(String przedmiotArgument);
}
