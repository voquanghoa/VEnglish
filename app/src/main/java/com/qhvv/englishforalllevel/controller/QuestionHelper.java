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
        setAnswers(question, lineGroup, first);

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



    private static void setAnswers(Question question, ArrayList<String> lineGroup, int first){
        question.setQuestion(getQuestionString(lineGroup, first++));

        question.setAnswerA(getAnswerString(lineGroup, first++));
        question.setAnswerB(getAnswerString(lineGroup, first++));
        question.setAnswerC(getAnswerString(lineGroup, first++));
        question.setAnswerD(getAnswerString(lineGroup, first++));
    }

    private static String getQuestionString(ArrayList<String> lineGroup, int lineIndex){
        if(lineIndex < lineGroup.size()) {
            String question = lineGroup.get(lineIndex);

            if(question.length()>0){
                char firstChar = question.charAt(0);

                if(Character.isDigit(firstChar) || (firstChar=='﻿' && question.charAt(1)=='1')){
                    if(question.charAt(1)=='.' || firstChar=='('){
                        question=question.substring(2).trim();
                    }else {
                        if(question.charAt(2)=='.' || question.charAt(0)=='('){
                            question=question.substring(3).trim();
                        }else if(question.charAt(3)=='.' || question.charAt(0)=='('){
                            question=question.substring(4).trim();
                        }
                    }
                }
            }
            return question;
        }
        return "";
    }

    private static String getAnswerString(ArrayList<String> lineGroup, int lineIndex){
        if(lineIndex < lineGroup.size()) {
            String answer = lineGroup.get(lineIndex);

            if(answer.length()>0){
                char firstChar = answer.charAt(0);

                if(firstChar=='('){
                    if(answer.charAt(2)==')' && answer.length()>=5) {
                        answer = answer.substring(4);
                    }
                }else if(isCharBetweenValue(firstChar, 'A', 'D') && answer.length()>=3){
                    if(isCharInSet(answer.charAt(1), '.', ')')){
                        answer=answer.substring(3);
                    }
                }else if(answer.length()>=4 && isCharBetweenValue(answer.charAt(1), 'A', 'D')){
                    if(answer.charAt(2)=='.' && answer.charAt(0)==' '){
                        answer=answer.substring(4);
                    }
                }
            }

            return answer;
        }
        return "";
    }

    private static boolean isCharInSet(char ch, char ... set){
        for(int i=0; i<set.length; i++){
            if(ch == set[i]){
                return true;
            }
        }
        return false;
    }

    private static boolean isCharBetweenValue(char ch, char minimal, char maximum){
        ch = Character.toUpperCase(ch);
        minimal = Character.toUpperCase(minimal);
        maximum = Character.toUpperCase(maximum);
        return ch >= minimal && ch <= maximum;
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
