package com.cht.sqlcipherlab1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CipherActivity extends Activity {
    private static final String TAG = CipherActivity.class.getSimpleName();

    @BindView(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlcipher);
        ButterKnife.bind(this);
        button1.setOnClickListener(view -> createCipherDB());
    }

    private static final String DDL1 = "create table table1(a,b)";
    private static final String DML1 = "insert into table1(a,b) values(?,?)";

    private void createCipherDB() {
        SQLiteDatabase.loadLibs(this);
        File dbFile = getDatabasePath("cipher.db");
        dbFile.mkdirs();
        dbFile.delete();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, "chtti2", null);
        db.execSQL(DDL1);
        db.execSQL(DML1, new Object[]{"ABCDE", "abcde"});
        db.close();
        Toast.makeText(this, "cipher db create successfully.", Toast.LENGTH_SHORT).show();

    }
}
