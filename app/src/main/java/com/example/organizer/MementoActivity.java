package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MementoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memento);

        Intent intent = getIntent();
        final ArrayList<String> dataTable = intent.getStringArrayListExtra("info");

        TextView content = findViewById(R.id.contentTextView);
        content.setText(dataTable.get(3));

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        Button editButton = findViewById(R.id.editMementoButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(dataTable);
            }
        });
    }

    private void back()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void edit(ArrayList<String> dataTable)
    {
        Intent intent = new Intent(this, EditMementoActivity.class);
        intent.putStringArrayListExtra("info", dataTable);
        startActivity(intent);

//        Intent intent = new Intent(this, EditMementoActivity.class);
//        startActivity(intent);
    }
}
