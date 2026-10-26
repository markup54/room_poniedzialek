package pl.zabrze.zs10.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SzkolaDataBase szkolaDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szkolaDataBase = Room.databaseBuilder(
                getApplicationContext(),SzkolaDataBase.class,"szkola_db")
                .allowMainThreadQueries()
                .build();

        szkolaDataBase.getNauczycielDao().wstawNAuczycielaDoBazy(
                new Nauczyciel("Anna","matematyka"));


    }
}