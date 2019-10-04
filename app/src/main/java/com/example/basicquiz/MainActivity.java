package com.example.basicquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addFromFirebaseToDatabase();
    }

    public void play(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    public void addQuestion(View view) {
        Intent intent = new Intent(this, AddQuestionActivity.class);
        startActivity(intent);
    }

    public void listQuestions(View view) {
        Intent intent = new Intent(this, ListQuestionsActivity.class);
        startActivity(intent);
        /*QuestionDal questionDal = new QuestionDal(this);
        questionDal.openConnection();
        questionDal.deleteAll();
        Toast.makeText(this, "Her şey silindi", Toast.LENGTH_SHORT).show();*/
    }

    public void addFromFirebaseToDatabase(){
     /*FirebaseHelper firebaseHelper = new FirebaseHelper(this);
     firebaseHelper.getAllQuestions();*/
    }
}
