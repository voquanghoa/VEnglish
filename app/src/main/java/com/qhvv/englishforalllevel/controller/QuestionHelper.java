package com.qhvv.englishforalllevel.controller;

import com.qhvv.englishforalllevel.model.Question;
import com.qhvv.englishforalllevel.model.TestContent;
import com.qhvv.englishforalllevel.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vo Quang Hoa on 12/22/2015.
 */
public class QuestionHelper {
    public static TestContent readQuestion(ArrayList<String> lines){
        ArrayList<ArrayList<String>> lineGroups = analystLines(lines);
        TestContent testContent = new TestContent();
        List<Question> questions = new ArrayList<>();
        for(ArrayList<String> group : lineGroups){
            questions.add(createQuestion(group));
        }
        testContent.setQuestions(questions);
        return testContent;
    }

    private static Question createQuestion(ArrayList<String> lineGroup){
        int first = 0;
        Question question = new Question();
        if(lineGroup.size()==6){
            question.setCategory(lineGroup.get(first++));
        }

        question.setQuestion(lineGroup.get(first++));
        question.setAnswerA(lineGroup.get(first++));
        question.setAnswerB(lineGroup.get(first++));
        question.setAnswerC(lineGroup.get(first++));
        question.setAnswerD(lineGroup.get(first++));

        if(question.getAnswerA().startsWith(" ")){
            question.setCorrectAnswer(0);
        }else if(question.getAnswerB().startsWith(" ")){
            question.setCorrectAnswer(1);
        }else if(question.getAnswerC().startsWith(" ")){
            question.setCorrectAnswer(2);
        }else if(question.getAnswerD().startsWith(" ")){
            question.setCorrectAnswer(3);
        }else {
            Utils.Log("Error, the correct answer is not found !!!" +question.getQuestion());
        }
        return question;
    }

    private static ArrayList<ArrayList<String>> analystLines(ArrayList<String> lines){
        int firstLine = 0;
        ArrayList<ArrayList<String>> lineGroup = new ArrayList<ArrayList<String>>();
        while(firstLine < lines.size()){
            ArrayList<String> group = new ArrayList<>();

            while(firstLine < lines.size() && lines.get(firstLine).length()==0) {
                firstLine++;
            }

            while(firstLine < lines.size() && lines.get(firstLine).length()>0) {
                group.add(lines.get(firstLine));
                firstLine++;
            }
            if(group.size()>0){
                lineGroup.add(group);
            }
        }
        return lineGroup;
    }
}
