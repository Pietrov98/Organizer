package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.io.FileOutputStream;
import java.util.Calendar;

import java.util.ArrayList;

public class TimePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        final TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        Intent intent = getIntent();
        final ArrayList<String> dataTable = intent.getStringArrayListExtra("info");

        Button makeMementoButton = findViewById(R.id.saveActivityButton);
        makeMementoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMemento(dataTable, timePicker);
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back(dataTable);
            }
        });
    }

    private void saveMemento(ArrayList<String> dataTable, TimePicker timePicker)
    {
        String minutes = timePicker.getCurrentMinute().toString();
        String hours = timePicker.getCurrentHour().toString();
        if(minutes.length() == 1)
            minutes = "0" + minutes;
        if(hours.length() == 1)
            hours = "0" + hours;
        String time =  hours + ":" + minutes ;
        Memento memento = new Memento();
        memento.setTime(time);
        memento.setTitle(dataTable.get(0));
        memento.setContent(dataTable.get(1));

        int idMemento;
        if(MainActivity.getMementos().size() != 0)
            idMemento = Integer.parseInt(MainActivity.getMementos().get(MainActivity.getMementos().size() - 1).getId()) + 1;
        else
            idMemento = 0;

        memento.setId(Integer.toString(idMemento));

        ArrayList<Memento> mementos = new ArrayList<>();
        mementos.add(memento);
        try {
            FileOutputStream fileOutputStream = openFileOutput("test_file.txt", MODE_APPEND);
        XMLWriter.writeToXML(fileOutputStream, mementos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void back(ArrayList<String> dataTable)
    {
        Intent intent = new Intent(this, AddMemento.class);
        intent.putStringArrayListExtra("info", dataTable);
        startActivity(intent);
    }
}
