package com.example.basicquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class QuestionDal {

    SQLiteDatabase database;
    SQLiteLayer layer;

    public QuestionDal(Context context) {
        layer = new SQLiteLayer(context);
    }

    public void openConnection() {
        database = layer.getWritableDatabase();
    }

    public void closeConnection() {
        layer.close();
    }

    public boolean addQuestion(Question question) {
        ContentValues values = new ContentValues();
        values.put("Name", question.getName());
        values.put("Answer", question.getAnswer());
        values.put("OptionA", question.getOptionA());
        values.put("OptionB", question.getOptionB());
        values.put("OptionC", question.getOptionC());
        values.put("OptionD", question.getOptionD());

        long result = database.insert("Question", null, values);
        if (result == -1) return false;
        return true;
    }

    public void addAllQuestions(List<Question> questions){
        for(Question question : questions){
            ContentValues values = new ContentValues();
            values.put("Name", question.getName());
            values.put("Answer", question.getAnswer());
            values.put("OptionA", question.getOptionA());
            values.put("OptionB", question.getOptionB());
            values.put("OptionC", question.getOptionC());
            values.put("OptionD", question.getOptionD());

            long result = database.insert("Question", null, values);
        }
    }

    public boolean isQuestionExists(int id){
        Cursor cursor = database.rawQuery("select * from Question where Id = " + id, null);
        if (cursor != null){
            return false;
        }
        return true;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<Question>();

        String columns[] = {"Id", "Name", "Answer", "OptionA", "OptionB", "OptionC", "OptionD"};
        Cursor cursor = database.query("Question", columns, null, null, null, null, null);
        if (cursor == null){
            return questionList;
        }
        while(cursor.moveToNext()){
            Question question = new Question();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int answer = cursor.getInt(2);
            String optionA = cursor.getString(3);
            String optionB = cursor.getString(4);
            String optionC = cursor.getString(5);
            String optionD = cursor.getString(6);

            question.setId(id);
            question.setName(name);
            question.setAnswer(answer);
            question.setOptionA(optionA);
            question.setOptionB(optionB);
            question.setOptionC(optionC);
            question.setOptionD(optionD);

            questionList.add(question);
        }

        return questionList;
    }

    public void deleteQuestion(Question question) {
        int id = question.getId();
        database.delete("Question", "Id = " + id, null);
    }
}
