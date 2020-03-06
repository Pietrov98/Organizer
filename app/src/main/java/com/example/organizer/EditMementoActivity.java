package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
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
                back(dataTable);
            }
        });

        Button saveButton = findViewById(R.id.saveChangesButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMemento(dataTable);
            }
        });
    }

    private void back(ArrayList<String> dataTable)
    {
        Intent intent = new Intent(this, MementoActivity.class);
        intent.putStringArrayListExtra("info", dataTable);
        startActivity(intent);
    }

    private void saveMemento(ArrayList<String> dataTable)
    {
        EditText title = findViewById(R.id.titleEditText);
        EditText content = findViewById(R.id.contentEditText);

        ArrayList<Memento> mementos = MainActivity.getMementos();
        System.out.println("do zmiany" + dataTable.get(0));
        for(final Memento memento : mementos)
        {
            System.out.println(memento.getTitle() + " " + memento.getId());
        }
        for(final Memento memento: mementos)
        {
            if(memento.getId().equals(dataTable.get(0)))
            {
                memento.setTitle(title.getText().toString());
                memento.setContent(content.getText().toString());
                break;
            }
        }
        for(final Memento memento : mementos)
        {
            System.out.println(memento.getTitle() + " " + memento.getId());
        }
        try {
            FileOutputStream fileOutputStream = openFileOutput("test_file.txt", MODE_PRIVATE);
            XMLWriter.writeToXML(fileOutputStream, mementos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
