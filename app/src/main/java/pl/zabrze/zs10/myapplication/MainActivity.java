package pl.zabrze.zs10.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SzkolaDataBase szkolaDataBase;
    Button button, buttonAktualizuj;
    EditText editText;
    Spinner spinner;
    ListView listView;
    List<Nauczyciel> nauczycielList;
    ArrayAdapter<Nauczyciel> arrayAdapter;
    Nauczyciel kliknietyNauczyciel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szkolaDataBase = Room.databaseBuilder(
                        getApplicationContext(), SzkolaDataBase.class, "szkola_db")
                .allowMainThreadQueries()
                .build();

        /*szkolaDataBase.getNauczycielDao().wstawNAuczycielaDoBazy(
                new Nauczyciel("Anna","matematyka"));*/
        button = findViewById(R.id.button);
        buttonAktualizuj = findViewById(R.id.buttonAktualizacja);
        editText = findViewById(R.id.editTextTextPersonName);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        nauczycielList = szkolaDataBase.getNauczycielDao().zwrocWszystkichNauczycieliZBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                nauczycielList
        );
        listView.setAdapter(arrayAdapter);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Nauczyciel nauczyciel = new Nauczyciel(
                                editText.getText().toString(),
                                spinner.getSelectedItem().toString()
                        );
                        szkolaDataBase.getNauczycielDao().wstawNAuczycielaDoBazy(nauczyciel);
                        // nauczycielList = szkolaDataBase.getNauczycielDao().zwrocWszystkichNauczycieliZBazy();
                        nauczycielList.add(nauczyciel);
                        arrayAdapter.notifyDataSetChanged();
                    }

                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        szkolaDataBase.getNauczycielDao().usunNauczycielaZBazy(nauczycielList.get(i));
                        nauczycielList.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        kliknietyNauczyciel = nauczycielList.get(i);
                        //wypelniony formularz
                        editText.setText(kliknietyNauczyciel.imie);
                        ArrayList<String> przedmioty = new ArrayList<>(
                                Arrays.asList("wf", "plastyka", "informatyka", "programowanie",
                                        "fizyka", "język angielski", "język chiński","matematyka"));
                        spinner.setSelection(przedmioty.indexOf(kliknietyNauczyciel.przedmiot));
                        buttonAktualizuj.setVisibility(View.VISIBLE);
                    }
                }
        );


    }
}