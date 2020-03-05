package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

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
                back();
            }
        });
    }

    private void saveMemento(ArrayList<String> dataTable, TimePicker timePicker)
    {

        String time = timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute().toString();
//        Memento memento = new Memento();
//        memento.setTime(time);
//        memento.setTitle(dataTable.get(0));
//        memento.setContent(dataTable.get(1));
          dataTable.add(time);
//        try {
//            XMLWriter.writeToXML(memento);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putStringArrayListExtra("info", dataTable);
        startActivity(intent);
    }

    private void back()
    {
        Intent intent = new Intent(this, AddMemento.class);
        startActivity(intent);
    }
}
