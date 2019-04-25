package com.example.nepalitoenglishdictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView lvdictionary;
    private Map<String, String> dictionary;
    public static final String words[] = {
            "yeta Aunus", "Come here",
            "Kaha Chau", "where are you",
            "Namaste", "Hello",
            "kaha bata aunuvayo", "where you come from",
            "kata jadai", "where are you going"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvdictionary = findViewById(R.id.lvdictionary);
        dictionary = new HashMap<>();

//        for (int i = 0; i < words.length; i += 2) {
//            dictionary.put(words[i], words[i + 1]);
//        }
        readFromFile();
        ArrayAdapter adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        lvdictionary.setAdapter(adapter);
        lvdictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = dictionary.get(key);

                Intent intent = new Intent(MainActivity.this, MeaningActivity.class);
                intent.putExtra("meaning", meaning);
                startActivity(intent);

            }
        });

    }

    private void readFromFile() {

            try {
                FileInputStream fos = openFileInput("word.txt");
                InputStreamReader isr = new InputStreamReader(fos);
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("->");
                    dictionary.put(parts[0], parts[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}
