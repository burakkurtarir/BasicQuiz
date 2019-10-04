package com.example.basicquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddQuestionActivity extends AppCompatActivity {

    QuestionDal questionDal;

    RadioButton optionA, optionB, optionC, optionD;
    EditText txtQuestion, txtOptionA, txtOptionB, txtOptionC, txtOptionD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        questionDal = new QuestionDal(this);
        questionDal.openConnection();

        optionA = findViewById(R.id.rbOptionA);
        optionB = findViewById(R.id.rbOptionB);
        optionC = findViewById(R.id.rbOptionC);
        optionD = findViewById(R.id.rbOptionD);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtOptionA = findViewById(R.id.txtOptionA);
        txtOptionB = findViewById(R.id.txtOptionB);
        txtOptionC = findViewById(R.id.txtOptionC);
        txtOptionD = findViewById(R.id.txtOptionD);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

    }

    public void addQuestion(View view) {

        int answer = optionA.isChecked() ? 1 : optionB.isChecked() ? 2 : optionC.isChecked() ? 3 : optionD.isChecked() ? 4 : 0;
        String name = txtQuestion.getText().toString();
        String optionA = txtOptionA.getText().toString();
        String optionB = txtOptionB.getText().toString();
        String optionC = txtOptionC.getText().toString();
        String optionD = txtOptionD.getText().toString();

        if(name.isEmpty()) return;
        if(optionA.isEmpty()) return;
        if(optionB.isEmpty()) return;
        if(optionC.isEmpty()) return;
        if(optionD.isEmpty()) return;


        Question question = new Question();
        question.setName(name);
        question.setAnswer(answer);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);

        boolean isInserted =  questionDal.addQuestion(question);
        String result = isInserted ? "Added Succesfully" : "Error while inserting";
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        txtQuestion.setText("");
        txtOptionA.setText("");
        txtOptionB.setText("");
        txtOptionC.setText("");
        txtOptionD.setText("");

        //firebaseHelper.addQuestion(question);
        //Toast.makeText(this, "Added succesfully", Toast.LENGTH_SHORT).show();

        /*int answer = optionA.isChecked() ? 1 : optionB.isChecked() ? 2 : optionC.isChecked() ? 3 : optionD.isChecked() ? 4 : 0;
        String question = txtQuestion.getText().toString();
        String optionA = txtOptionA.getText().toString();
        String optionB = txtOptionB.getText().toString();
        String optionC = txtOptionC.getText().toString();
        String optionD = txtOptionD.getText().toString();

        boolean isInserted = myDb.insertQuestion(question, answer, optionA, optionB, optionC, optionD);
        String result = isInserted ? "Added Successfully" : "Error while inserting";
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();*/
    }
}
