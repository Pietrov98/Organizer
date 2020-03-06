package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.FileOutputStream;
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
                editMemento(dataTable);
            }
        });

        Button deleteButton = findViewById(R.id.deleteMementoButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMemento(dataTable);
            }
        });
    }

    private void back()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void editMemento(ArrayList<String> dataTable)
    {
        Intent intent = new Intent(this, EditMementoActivity.class);
        intent.putStringArrayListExtra("info", dataTable);
        startActivity(intent);
    }

    private void deleteMemento(ArrayList<String> dataTable)
    {
        ArrayList<Memento> mementos = MainActivity.getMementos();
        System.out.println("do usuniecia" + dataTable.get(0));
        for(final Memento memento : mementos)
        {
            System.out.println(memento.getTitle() + " " + memento.getId());
        }
        for(final Memento memento: mementos)
        {
            if(memento.getId().equals(dataTable.get(0)))
            {
                mementos.remove(memento);
                break;
            }
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
