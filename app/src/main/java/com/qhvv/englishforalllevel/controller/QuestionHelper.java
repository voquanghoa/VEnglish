package com.qhvv.englishforalllevel.controller;

import com.qhvv.englishforalllevel.model.Question;
import com.qhvv.englishforalllevel.model.TestContent;
import com.qhvv.englishforalllevel.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        makeRandomQuestions(testContent);
        return testContent;
    }

    public static String convertToColor(String originalString, String colorCode){
        String clearColor = originalString.replace("color='red'", "color='" + colorCode + "'");
        String result = "<font color='"+colorCode+"'>" + clearColor +"</font>";
        return result;
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

    private static void makeRandomQuestions(TestContent testContent){
        int firstIndex = 0;
        int count = 1;
        List<Question> questions = testContent.getQuestions();
        for(int i=1;i<=questions.size();i++){
            if(i==questions.size() || questions.get(i).isCategory()){
                makeRandomQuestionsSegment(testContent, firstIndex, count);
                firstIndex = i;
                count=1;
            }else{
                count++;
            }
        }
    }

    private static void makeRandomQuestionsSegment(TestContent testContent, int firstId, int count){
        Random random = new Random();
        List<Question> questions = testContent.getQuestions();
        String category = questions.get(firstId).getCategory();
        questions.get(firstId).setCategory("");
        for(int i=0;i<count;i++){
            int random1 = firstId + random.nextInt(count);
            int random2 = firstId + random.nextInt(count);
            Question question1 = questions.get(random1);
            Question question2 = questions.get(random2);
            questions.set(random1, question2);
            questions.set(random2, question1);
        }
        questions.get(firstId).setCategory(category);
    }


    private static void setAnswers(Question question, ArrayList<String> lineGroup, int first){
        question.setQuestion(getQuestionString(lineGroup, first++));

        question.setAnswerA(getAnswerString(lineGroup, first++));
        question.setAnswerB(getAnswerString(lineGroup, first++));
        question.setAnswerC(getAnswerString(lineGroup, first++));
        question.setAnswerD(getAnswerString(lineGroup, first++));
    }

    private static String getQuestionString(ArrayList<String> lineGroup, int lineIndex){
        String removeRegex = "^(\\d+)(\\.|\\)) ";

        if(lineIndex < lineGroup.size()) {
            String question = lineGroup.get(lineIndex);
            if(question.startsWith("\uFEFF")){
                question = question.substring(1);
            }

            return question.replaceAll(removeRegex,"").replaceAll("\r","");
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

            return answer.replaceAll("\r","");
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
