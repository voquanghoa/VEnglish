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
import com.qhvv.englishforalllevel.model.Question;
import com.qhvv.englishforalllevel.model.TestContent;
import com.qhvv.englishforalllevel.util.ViewUtils;

/**
 * Created by Vo Quang Hoa on 12/22/2015.
 */
public class QuestionAnswerAdapter extends BaseAdapter {
    private Context context;
    private TestContent test;
    private boolean showAnswer;
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
        tvQuestion.setText(Html.fromHtml(question.getQuestion().replace("<u>", "").replace("</u>", "")
                .trim()));
        rdAnswerA.setText(Html.fromHtml(question.getAnswerA()));
        rdAnswerB.setText(Html.fromHtml(question.getAnswerB()));
        rdAnswerC.setText(Html.fromHtml(question.getAnswerC()));
        rdAnswerD.setText(Html.fromHtml(question.getAnswerD()));

        ViewUtils.setViewVisibility(rdAnswerA, question.getAnswerA().length() > 0);
        ViewUtils.setViewVisibility(rdAnswerB, question.getAnswerB().length() > 0);
        ViewUtils.setViewVisibility(rdAnswerC, question.getAnswerC().length() > 0);
        ViewUtils.setViewVisibility(rdAnswerD, question.getAnswerD().length() > 0);


        return convertView;
    }
}
