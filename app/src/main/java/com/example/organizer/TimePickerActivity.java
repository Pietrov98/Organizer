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

        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        Intent intent = getIntent();
        ArrayList<String> dataTable = intent.getStringArrayListExtra("info");
        System.out.println(dataTable.get(0) + " " + dataTable.get(1));

        Button makeMementoButton = findViewById(R.id.saveActivityButton);
        makeMementoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMemento();
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

    private void saveMemento()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void back()
    {
        Intent intent = new Intent(this, AddMemento.class);
        startActivity(intent);
    }
}