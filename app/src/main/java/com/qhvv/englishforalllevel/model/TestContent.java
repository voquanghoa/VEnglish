package com.qhvv.englishforalllevel.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vo Quang Hoa on 12/22/2015.
 */
public class TestContent {
    public TestContent(){
        questions = new ArrayList<>();
    }

    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
