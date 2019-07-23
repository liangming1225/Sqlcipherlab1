package com.cht.sqlcipherlab1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        button1.setOnClickListener(view -> createPlainDB());
    }

    private static final String DDL1 = "create table table1(a,b)";
    private static final String DML1 = "insert into table1(a,b) values(?,?)";

    private void createPlainDB() {
        File databaseFile = getDatabasePath("plain.sqlite");
        databaseFile.mkdirs();
        databaseFile.delete();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databaseFile, null);
        db.execSQL(DDL1);
        db.execSQL(DML1, new Object[]{"ABCDE", "abcde"});
        db.close();
        Toast.makeText(this, "db create successfully.", Toast.LENGTH_SHORT).show();

    }
}
