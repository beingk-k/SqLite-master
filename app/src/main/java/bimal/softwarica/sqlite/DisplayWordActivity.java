package bimal.softwarica.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class DisplayWordActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lstWord;
    private EditText etSearch;
    private Button btnBack, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);

        lstWord = findViewById(R.id.lstWord);
        etSearch = findViewById(R.id.etSearch);
        btnBack = findViewById(R.id.btnBack);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        LoadWord();

    }

    private void LoadWord(){
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.GetAllWords(sqLiteDatabase);

        final HashMap<String, String> hashMap = new HashMap<>();

        for (int i=0; i<wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<String>(hashMap.keySet())

        );
        lstWord.setAdapter(stringArrayAdapter);

        lstWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = hashMap.get(key);

                Intent intent = new Intent(DisplayWordActivity.this, MeaningActivity.class);
                intent.putExtra("meaning", meaning);
                intent.putExtra("word", key);
                startActivity(intent);
            }
        });

    }

    private void SearchWord(){
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.GetWordByName(etSearch.getText().toString(), sqLiteDatabase);

        final HashMap<String, String> hashMap = new HashMap<>();

        for (int i=0; i<wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<String>(hashMap.keySet())

        );
        lstWord.setAdapter(stringArrayAdapter);

        lstWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();

                String meaning = hashMap.get(key);
                Intent intent = new Intent(DisplayWordActivity.this, MeaningActivity.class);
                intent.putExtra("meaning", meaning);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnSearch){
        SearchWord();
        }
        else if (v.getId()==R.id.btnBack){
            Intent intent = new Intent(DisplayWordActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


}
