package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.LinearLayout;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Memento> mementos;

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
    }

    public static ArrayList<Memento> getMementos()
    {
        return mementos;
    }
}