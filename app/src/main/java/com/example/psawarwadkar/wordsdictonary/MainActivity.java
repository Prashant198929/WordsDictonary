package com.example.psawarwadkar.wordsdictonary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.example.psawarwadkar.wordsdictonary.adapters.WordAdapter;
import com.example.psawarwadkar.wordsdictonary.utilise.DictionaryModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String fileDictionary = "dictionary.txt";
    private List<DictionaryModel> data;
    private RecyclerView rvWord;
    private WordAdapter wordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvWord = findViewById(R.id.rvWord);
        rvWord.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        data = new ArrayList<>();
        readFromAssets(getApplicationContext(), fileDictionary);

        wordAdapter = new WordAdapter();
        wordAdapter.setData(data);
        rvWord.setAdapter(wordAdapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchWord(newText);
                return false;
            }
        });
    }

    private void readFromAssets(Context context, String fileName){

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));
            String mLine;

            int i=0;

            while ((mLine=reader.readLine()) != null){
                String[] lineData = mLine.split("-");
                data.add(new DictionaryModel(""+i, lineData[0], lineData[1]));
                Log.d("word","" +lineData[0]);
                i++;

            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    

    private void searchWord(String wordSearch){
        data.clear();
        readFromAssets(getApplicationContext(), fileDictionary);
        List<DictionaryModel> temp = new ArrayList<>();
        for(DictionaryModel dictionaryModel : data){
            if(dictionaryModel.getWord().contains(wordSearch))

            {
                temp.add(dictionaryModel);
            }
        }
        data=temp;
        wordAdapter.setData(data);
    }
}
