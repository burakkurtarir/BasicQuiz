package com.example.basicquiz;

public class Question {

    int id;
    String name;
    int answer;
    String optionA;
    String optionB;
    String optionC;
    String optionD;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(final int answer) {
        this.answer = answer;
    }

    public String getOptionA() {
        return this.optionA;
    }

    public void setOptionA(final String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return this.optionB;
    }

    public void setOptionB(final String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return this.optionC;
    }

    public void setOptionC(final String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return this.optionD;
    }

    public void setOptionD(final String optionD) {
        this.optionD = optionD;
    }
}
