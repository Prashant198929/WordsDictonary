package com.example.psawarwadkar.wordsdictonary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DefinitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        String word = getIntent().getStringExtra("WORD");
        String definition = getIntent().getStringExtra("DEFINITION");

        TextView wordText = findViewById(R.id.wordText);
        TextView definitionText = findViewById(R.id.definitionText);

        wordText.setText(word);
        definitionText.setText(definition);
    }
}
