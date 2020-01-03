package com.example.pedapp.Classes;

public class Test {
    private String question;
    private String answear1;
    private String answear2;
    private String answear3;
    private String answear4;
    private String correctanswear;

    public Test(String question, String answear1, String answear2, String answear3, String answear4, String correctanswear) {
        this.question = question;
        this.answear1 = answear1;
        this.answear2 = answear2;
        this.answear3 = answear3;
        this.answear4 = answear4;
        this.correctanswear = correctanswear;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswear1(String answear1) {
        this.answear1 = answear1;
    }

    public void setAnswear2(String answear2) {
        this.answear2 = answear2;
    }

    public void setAnswear3(String answear3) {
        this.answear3 = answear3;
    }

    public void setAnswear4(String answear4) {
        this.answear4 = answear4;
    }

    public void setCorrectanswear(String correctanswear) {
        this.correctanswear = correctanswear;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswear1() {
        return answear1;
    }

    public String getAnswear2() {
        return answear2;
    }

    public String getAnswear3() {
        return answear3;
    }

    public String getAnswear4() {
        return answear4;
    }

    public String getCorrectanswear() {
        return correctanswear;
    }
}
