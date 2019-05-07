package bimal.softwarica.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import helper.MyHelper;
import model.Word;

public class MeaningActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMeaning, tvWord1;
    private Button btnDelete, btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);

        tvMeaning = findViewById(R.id.tvMeaning);
        tvWord1 = findViewById(R.id.tvWord1);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String word = bundle.getString("word");
            String meaning = bundle.getString("meaning");
            tvMeaning.setText(meaning);
            tvWord1.setText(word);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDelete) {
            MyHelper myHelper = new MyHelper(this);
            myHelper.DeleteMeaning(tvMeaning.getText().toString());
            Intent intent = new Intent(MeaningActivity.this, DisplayWordActivity.class);
            startActivity(intent);
        }
    }
}

