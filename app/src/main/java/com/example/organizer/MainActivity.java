package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaMetadata;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ArrayList<Memento> mementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createActivitiesButtons();

        Button makeMementoButton = findViewById(R.id.makeMementoButton);
        makeMementoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                createMemento();
            }
        });

        Intent intent = getIntent();
        final ArrayList<String> dataTable = intent.getStringArrayListExtra("info");
        System.out.println(dataTable);
        if(dataTable != null)
        {
            saveMemento(dataTable);
        }

        for(final Memento memento : this.mementos)
        {
            System.out.println(memento.getTitle() + " " + memento.getId());
        }
    }

    private void createActivitiesButtons()
    {
        try {
            FileInputStream fileInputStream = openFileInput("test_file.txt");
            this.mementos = XMLReader.parseXML(fileInputStream);
            if (this.mementos.size() > 0) {
                Collections.sort(mementos, new Comparator<Memento>() {
                    @Override
                    public int compare(final Memento object1, final Memento object2) {
                        return object1.getTime().compareTo(object2.getTime());
                    }
                });
            }
            int i = 0;
            LinearLayout layout = (LinearLayout) findViewById(R.id.activityButtons);

            for(final Memento memento : this.mementos)
            {
                Button myButton = (Button)getLayoutInflater().inflate(R.layout.activity_buttons, null);
                myButton.setText("Button :" + i);
                myButton.setId(i);
                final int id_ = myButton.getId();

                myButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view)
                    {
                        checkMemento(memento.getId(), memento.getTime(), memento.getTitle(), memento.getContent());
                    }
                });
                String mementoTitle = memento.getTitle() + " " + memento.getTime();
                myButton.setText(mementoTitle);
                layout.addView(myButton);

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createMemento()
    {
        Intent intent = new Intent(this, AddMemento.class);
        startActivity(intent);
    }

    private void checkMemento(String id, String time, String title, String content)
    {
        ArrayList<String> dataTable = new ArrayList<String>();

        dataTable.add(id);
        dataTable.add(time);
        dataTable.add(title);
        dataTable.add(content);

        Intent intent = new Intent(this, MementoActivity.class);
        intent.putStringArrayListExtra("info", dataTable);
        startActivity(intent);

//        Intent intent = new Intent(this, MementoActivity.class);
//        startActivity(intent);
    }

    private void saveMemento(ArrayList<String> dataTable)
    {
        try {
            int idMemento = Integer.parseInt(this.mementos.get(this.mementos.size() - 1).getId()) + 1;

            Memento memento = new Memento();
            memento.setId(Integer.toString(idMemento));
            memento.setTitle(dataTable.get(0));
            memento.setContent(dataTable.get(1));
            memento.setTime(dataTable.get(2));

            FileOutputStream fileOutputStream = openFileOutput("test_file.txt", MODE_APPEND);
            XMLWriter.writeToXML(fileOutputStream, memento);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}