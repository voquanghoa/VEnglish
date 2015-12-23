package com.qhvv.englishforalllevel.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qhvv.englishforalllevel.R;
import com.qhvv.englishforalllevel.controller.QuestionHelper;
import com.qhvv.englishforalllevel.model.Question;
import com.qhvv.englishforalllevel.model.TestContent;
import com.qhvv.englishforalllevel.util.ViewUtils;

/**
 * Created by Vo Quang Hoa on 12/22/2015.
 */
public class QuestionAnswerAdapter extends BaseAdapter {
    private Context context;
    private TestContent test;
    private boolean showAnswer = false;

    public QuestionAnswerAdapter(Context context, TestContent test) {
        this.context = context;
        this.test = test;
    }

    public void setShowAnswer(boolean showAnser){
        this.showAnswer = showAnser;
        this.notifyDataSetChanged();
    }

    public int getCount() {
        return test.getQuestions().size();
    }

    public Object getItem(int position) {
        return test.getQuestions().get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Question question = test.getQuestions().get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.question_answer_layout, parent, false);
        }

        Button categoryButton = (Button)convertView.findViewById(R.id.category_button);
        TextView tvQuestion = (TextView)convertView.findViewById(R.id.question);

        RadioGroup radioGroup = (RadioGroup)convertView.findViewById(R.id.answer_group);
        RadioButton rdAnswerA = (RadioButton)convertView.findViewById(R.id.answer_a);
        RadioButton rdAnswerB = (RadioButton)convertView.findViewById(R.id.answer_b);
        RadioButton rdAnswerC = (RadioButton)convertView.findViewById(R.id.answer_c);
        RadioButton rdAnswerD = (RadioButton)convertView.findViewById(R.id.answer_d);

        if(question.getCategory()==null || question.getCategory().length()==0){
            categoryButton.setVisibility(View.GONE);
        }else{
            categoryButton.setVisibility(View.VISIBLE);
            categoryButton.setText(question.getCategory());
        }

        radioGroup.setEnabled(!showAnswer);
        rdAnswerA.setEnabled(!showAnswer);
        rdAnswerB.setEnabled(!showAnswer);
        rdAnswerC.setEnabled(!showAnswer);
        rdAnswerD.setEnabled(!showAnswer);

        tvQuestion.setText(Html.fromHtml(question.getQuestion().replace("<u>", "").replace("</u>", "")
                .trim()));


        ViewUtils.setViewVisibility(rdAnswerA, question.getAnswerA().length() > 0);
        ViewUtils.setViewVisibility(rdAnswerB, question.getAnswerB().length() > 0);
        ViewUtils.setViewVisibility(rdAnswerC, question.getAnswerC().length() > 0);
        ViewUtils.setViewVisibility(rdAnswerD, question.getAnswerD().length() > 0);

        if(showAnswer){
            rdAnswerA.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerA(), "black")));
            rdAnswerB.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerB(), "black")));
            rdAnswerC.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerC(), "black")));
            rdAnswerD.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerD(), "black")));

            switch (question.getCorrectAnswer()){
                case 0:
                    rdAnswerA.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerA(), "blue")));
                    break;
                case 1:
                    rdAnswerB.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerB(), "blue")));
                    break;
                case 2:
                    rdAnswerC.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerC(), "blue")));
                    break;
                case 3:
                    rdAnswerD.setText(Html.fromHtml(QuestionHelper.convertToColor(question.getAnswerD(), "blue")));
                    break;
            }
        }else{
            rdAnswerA.setText(Html.fromHtml(question.getAnswerA()));
            rdAnswerB.setText(Html.fromHtml(question.getAnswerB()));
            rdAnswerC.setText(Html.fromHtml(question.getAnswerC()));
            rdAnswerD.setText(Html.fromHtml(question.getAnswerD()));
        }


        return convertView;
    }
}
