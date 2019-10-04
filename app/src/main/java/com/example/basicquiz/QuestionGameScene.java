package com.example.basicquiz;

import android.content.Context;

import java.util.Collections;
import java.util.List;

public class QuestionGameScene {

    QuestionDal questionDal;
    int currentQuestionNumber;
    List<Question> questionList;
    boolean isGameOver;
    int trueAnswers;
    int falseAnswers;
    int questionNumber;

    public QuestionGameScene(Context context) {
        questionDal = new QuestionDal(context);
        questionDal.openConnection();
        getAllQuestions();
    }


    //Bu fonksiyon baslangicta calisacak ve butun sorulari bir listeye kaydedecek
    public void getAllQuestions() {
        questionList = questionDal.getAllQuestions();
        Collections.shuffle(questionList);

        if (questionList.isEmpty()) {
            //Sorulacak bir soru yok
            isGameOver = true;
         }

        //Oyun baslasin :)
        currentQuestionNumber = 0;
        trueAnswers = 0;
        falseAnswers = 0;
        isGameOver = false;
        questionNumber = questionList.size();
    }

    public Question getCurrentQuestion () {
        Question currentQuestion = questionList.get(currentQuestionNumber);
        return currentQuestion;
    }

    public boolean goToNextQuestion () {
        if (currentQuestionNumber + 1 < questionList.size()) {
            currentQuestionNumber++;
            return true;
        }
        return false;
    }

    public boolean isAnswerRight(int answer) {
        if (getCurrentQuestion().answer == answer) {
            trueAnswers++;
            return true;
        }
        falseAnswers++;
        return false;
    }

    public int getQuestionNumber(){
        return questionNumber;
    }

    public int getTrueAnswers(){
        return trueAnswers;
    }

    public int getFalseAnswers(){
        return falseAnswers;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public int getCurrentQuestionNumber(){
        return currentQuestionNumber;
    }

    public void setIsGameOver(boolean isGameOver) {
        isGameOver = isGameOver;
    }
}
