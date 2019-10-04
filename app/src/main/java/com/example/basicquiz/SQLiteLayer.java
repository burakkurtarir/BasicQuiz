package com.example.basicquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteLayer extends SQLiteOpenHelper {

    public SQLiteLayer(Context context) {
        super(context, "BasicQuiz", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Question (Id integer primary key autoincrement, Name text, Answer integer, " +
                "OptionA text, OptionB text, OptionC text, OptionD text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Question";
        db.execSQL(sql);
    }
}
