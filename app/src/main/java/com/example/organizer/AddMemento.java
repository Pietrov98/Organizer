package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.util.ArrayList;

public class AddMemento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memento);

        Intent intent = getIntent();
        final ArrayList<String> dataTable = intent.getStringArrayListExtra("info");
        System.out.println(dataTable);
        if(dataTable != null)
        {
            EditText title = findViewById(R.id.titleEditTextView);
            EditText content = findViewById(R.id.contentEditTextView);

            title.setText(dataTable.get(0));
            content.setText(dataTable.get(1));
        }

        Button makeMementoButton = findViewById(R.id.saveTimeButton);
        makeMementoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTime();
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

    private void getTime()
    {
        EditText title = findViewById(R.id.titleEditTextView);
        EditText content = findViewById(R.id.contentEditTextView);

        ArrayList<String> dataTable = new ArrayList<String>();
        dataTable.add(title.getText().toString());
        dataTable.add(content.getText().toString());

        Intent intent = new Intent(this, TimePickerActivity.class);
        intent.putStringArrayListExtra("info", dataTable);
        startActivity(intent);
    }

    private void back()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
