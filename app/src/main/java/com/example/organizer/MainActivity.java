package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

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

    private void createMemento()
    {
        Intent intent = new Intent(this, AddMemento.class);
        startActivity(intent);
    }

    private void createActivitiesButtons() {

        for (int i = 0; i < 10; i++)
        {
            Button myButton = (Button)getLayoutInflater().inflate(R.layout.activity_buttons, null);
            myButton.setText("Button :" + i);
            myButton.setId(i);
            final int id_ = myButton.getId();

            LinearLayout layout = (LinearLayout) findViewById(R.id.activityButtons);
            layout.addView(myButton);
        }
    }
}