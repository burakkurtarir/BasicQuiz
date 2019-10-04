package com.example.basicquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    QuestionDal questionDal;
    List<Question> questionList;
    int currentQuestionNumber;
    List<TextView> textViewList;

    QuestionGameScene questionGameScene;

    TextView txtOptionA, txtOptionB, txtOptionC, txtOptionD, txtQuestion, currentTextView, txtScore;
    Button btnNextQuestion;
    LinearLayout lyScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionDal = new QuestionDal(this);
        questionDal.openConnection();

        txtOptionA = findViewById(R.id.txtOptionA);
        txtOptionB = findViewById(R.id.txtOptionB);
        txtOptionC = findViewById(R.id.txtOptionC);
        txtOptionD = findViewById(R.id.txtOptionD);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        lyScore = findViewById(R.id.lyScore);
        txtScore = findViewById(R.id.txtScore);

        txtOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //answerQuestion(v);
                answerTheQuestion(1, v);
                currentTextView = (TextView) v;
            }
        });
        txtOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //answerQuestion(v);
                answerTheQuestion(2, v);
                currentTextView = (TextView) v;
            }
        });
        txtOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //answerQuestion(v);
                answerTheQuestion(3, v);
                currentTextView = (TextView) v;
            }
        });
        txtOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //answerQuestion(v);
                answerTheQuestion(4, v);
                currentTextView = (TextView) v;
            }
        });
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuestion();
                v.setVisibility(View.GONE);
            }
        });

        textViewList = new ArrayList<TextView>();
        textViewList.add(txtOptionA);
        textViewList.add(txtOptionB);
        textViewList.add(txtOptionC);
        textViewList.add(txtOptionD);

        //getAllQuestions();
        startGame();
    }

    public void goToMainMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //Şıkların tıklanabilirliğini değiştiriyor(Tüm şıkların)
    public void setAllTextViewsClickable(boolean isClickable){
        for(TextView myTextView : textViewList) {
            myTextView.setClickable(isClickable);
        }
    }

    public void startGame() {
        questionGameScene = new QuestionGameScene(this);
        if (questionGameScene.getIsGameOver()) {
            //Yani sorulacak bir soru yoksa(Soru sayısı 0 ise)
            //Game is over
            setAllTextViewsClickable(false);
            Toast.makeText(this, "There is no question, sorry :(", Toast.LENGTH_LONG).show();
        }
        //Game is on
        showCurrentQuestion();
    }

    public void endGame() {
        questionGameScene.setIsGameOver(true);
    }

    public void showCurrentQuestion() {
        Question currentQuestion = questionGameScene.getCurrentQuestion();
        //Soru textlere yazılacak
        txtQuestion.setText((questionGameScene.getCurrentQuestionNumber() + 1) + ") " + currentQuestion.name);
        txtOptionA.setText("A) " + currentQuestion.optionA);
        txtOptionB.setText("B) " + currentQuestion.optionB);
        txtOptionC.setText("C) " + currentQuestion.optionC);
        txtOptionD.setText("D) " + currentQuestion.optionD);
    }

    public void answerTheQuestion(final int answer, View view){
        setAllTextViewsClickable(false);
        final TextView textView = ((TextView) view);
        textView.setBackgroundResource(R.drawable.textview_white_border);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isAnswerRight = questionGameScene.isAnswerRight(answer);
                if (isAnswerRight){
                    //Textler yeşil yanacak
                    textView.setBackgroundResource(R.drawable.textview_green_border);
                }
                else {
                    //Textler kırmızı yanacak
                    textView.setBackgroundResource(R.drawable.textview_red_border);
                }
                btnNextQuestion.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    public void goToNextQuestion(){
        boolean isNextQuestionExists = questionGameScene.goToNextQuestion();
        if (isNextQuestionExists){
            //Yeni soruyu textlere yaz
            currentTextView.setBackgroundResource(R.drawable.textview_border);
            showCurrentQuestion();
            setAllTextViewsClickable(true);
        }
        else{
            //Oyunu bitir
            Toast.makeText(this,"Test is over", Toast.LENGTH_LONG).show();
            //Score gözükecek
            txtScore.setText("You have " + questionGameScene.getTrueAnswers() + " true and " + questionGameScene.getFalseAnswers() + " false answers\n" +
                    "Your score is " + questionGameScene.getTrueAnswers() + " / " + questionGameScene.getQuestionNumber());
            lyScore.setVisibility(View.VISIBLE);
        }
    }

    /*public void getAllQuestions() {
        questionList = questionDal.getAllQuestions();
        if (questionList.isEmpty()) {
            //We have no question
            return;
        }

        currentQuestionNumber = 0;
        showNextQuestion();
    }

    public void showNextQuestion() {
        Question currentQuestion = questionList.get(currentQuestionNumber);
        txtQuestion.setText(currentQuestion.name);
        txtOptionA.setText(currentQuestion.optionA);
        txtOptionB.setText(currentQuestion.optionB);
        txtOptionC.setText(currentQuestion.optionC);
        txtOptionD.setText(currentQuestion.optionD);
    }*/

    /*public void makeTextBorderBlack(TextView textView) {
        textView.setBackgroundResource(R.drawable.textview_border);
        for(TextView myTextView : textViewList) {
            myTextView.setClickable(true);
        }
    }*/

    /*public void answerQuestion(View view) {
        final TextView textView = ((TextView) view);
        textView.setBackgroundResource(R.drawable.textview_white_border);

        //1 kere seçtikten sonra 1 daha seçilemesin
        for(TextView myTextView : textViewList) {
            myTextView.setClickable(false);
        }

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               makeTextBorderBlack(textView);
            }
        }, 1000);
    }*/

}
