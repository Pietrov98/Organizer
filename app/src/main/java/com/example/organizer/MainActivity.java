package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button makeMementoButton = findViewById(R.id.makeMementoButton);
        makeMementoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                createMemento();
            }
        });

        createActivitiesButtons();

    }

    private void createActivitiesButtons()
    {
        try {
            InputStream is = getAssets().open("mementos.xml");
            ArrayList<Activity> activities = XMLReader.parseXML(is);
            if (activities.size() > 0) {
                Collections.sort(activities, new Comparator<Activity>() {
                    @Override
                    public int compare(final Activity object1, final Activity object2) {
                        return object1.getTime().compareTo(object2.getTime());
                    }
                });
            }
            int i = 0;
            LinearLayout layout = (LinearLayout) findViewById(R.id.activityButtons);

            for(final Activity activity : activities)
            {
                Button myButton = (Button)getLayoutInflater().inflate(R.layout.activity_buttons, null);
                myButton.setText("Button :" + i);
                myButton.setId(i);
                final int id_ = myButton.getId();

                myButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view)
                    {
                        checkMemento(activity.getId(), activity.getTime(), activity.getTitle(), activity.getContent());
                    }
                });
                String memento = activity.getTitle() + " " + activity.getTime();
                myButton.setText(memento);
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
}