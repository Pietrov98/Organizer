package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditMementoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memento);

        Intent intent = getIntent();
        final ArrayList<String> dataTable = intent.getStringArrayListExtra("info");

        EditText title = findViewById(R.id.titleEditText);
        title.setText(dataTable.get(2));

        EditText content = findViewById(R.id.contentEditText);
        content.setText(dataTable.get(3));

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        Button saveButton = findViewById(R.id.saveChangesButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void back()
    {
        Intent intent = new Intent(this, MementoActivity.class);
        startActivity(intent);
    }

    private void save()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
