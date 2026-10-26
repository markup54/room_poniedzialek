package pl.zabrze.zs10.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    SzkolaDataBase szkolaDataBase;
    Button button;
    EditText editText;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szkolaDataBase = Room.databaseBuilder(
                getApplicationContext(),SzkolaDataBase.class,"szkola_db")
                .allowMainThreadQueries()
                .build();

        /*szkolaDataBase.getNauczycielDao().wstawNAuczycielaDoBazy(
                new Nauczyciel("Anna","matematyka"));*/
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);
        spinner = findViewById(R.id.spinner);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Nauczyciel nauczyciel = new Nauczyciel(
                                editText.getText().toString(),
                                spinner.getSelectedItem().toString()
                        );
                        szkolaDataBase.getNauczycielDao().wstawNAuczycielaDoBazy(nauczyciel);
                    }
                }
        );
    }
}