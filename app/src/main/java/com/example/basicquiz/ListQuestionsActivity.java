package com.example.basicquiz;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ListQuestionsActivity extends AppCompatActivity {

    QuestionDal questionDal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        questionDal = new QuestionDal(this);
        questionDal.openConnection();
    }

    public void getAllQuestions(View view){

        List<Question> questionList = questionDal.getAllQuestions();

        if (questionList.isEmpty()){
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for(Question question : questionList) {
            buffer.append("Id: " + question.id + "\n");
            buffer.append("Name: " + question.name + "\n");
            buffer.append("Answer: " + question.answer + "\n");
            buffer.append("Option A) " + question.optionA + "\n");
            buffer.append("Option B) " + question.optionB + "\n");
            buffer.append("Option C) " + question.optionC + "\n");
            buffer.append("Option D) " + question.optionD + "\n");
        }
        showMessage("Data", buffer.toString());

       /* Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            //no data
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Id: " + res.getInt(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Answer: " + res.getString(2) + "\n");
            buffer.append("Option A: " + res.getString(3) + "\n");
            buffer.append("Option B: " + res.getString(4) + "\n");
            buffer.append("Option C: " + res.getString(5) + "\n");
            buffer.append("Option D: " + res.getString(6) + "\n");
        }
        //Show all data
        showMessage("Data", buffer.toString());*/
    }

    public void showMessage(String Name, String Answer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Name);
        builder.setMessage(Answer);
        builder.show();
    }
}
