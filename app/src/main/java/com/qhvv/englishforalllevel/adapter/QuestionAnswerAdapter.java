package com.qhvv.englishforalllevel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
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
public class QuestionAnswerAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private Context context;
    private TestContent test;
    private boolean showAnswer = false;
    private int[] userSelection;
    private int[] radioButtonId = new int[]{
            R.id.answer_a,
            R.id.answer_b,
            R.id.answer_c,
            R.id.answer_d
    };

    public QuestionAnswerAdapter(Context context, TestContent test) {
        this.context = context;
        this.test = test;
        initUserSelect();
    }

    private void initUserSelect(){
        this.userSelection = new int[test.getQuestions().size()];
        for(int i=0;i<this.userSelection.length;i++){
            this.userSelection[i] = -1;
        }
    }

    public void setShowAnswer(boolean showAnswer){
        this.showAnswer = showAnswer;
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
        RadioButton[] radioButtons = getRadioButtons(convertView);

        setRadioOnChanged(radioButtons, false);
        setRadioValue(userSelection[position], radioGroup);
        setRadioTag(radioGroup, radioButtons, position);

        radioGroup.setEnabled(!showAnswer);
        setRadioButtonEnable(radioButtons);
        setRadioButtonShow(radioButtons, question);
        showCategoryQuestion(question, categoryButton);
        setQuestionText(question, tvQuestion);
        setAnswerText(radioButtons, question);
        setRadioOnChanged(radioButtons, true);
        return convertView;
    }

    @NonNull
    private RadioButton[] getRadioButtons(View convertView) {
        return new RadioButton[]{
                    (RadioButton)convertView.findViewById(R.id.answer_a),
                    (RadioButton)convertView.findViewById(R.id.answer_b),
                    (RadioButton)convertView.findViewById(R.id.answer_c),
                    (RadioButton)convertView.findViewById(R.id.answer_d)
            };
    }

    private void setRadioValue(int i, RadioGroup radioGroup) {
        if(i ==-1){
            radioGroup.check(-1);
        }else{
            radioGroup.check(radioButtonId[i]);
        }
    }

    private void setRadioTag(RadioGroup radioGroups, RadioButton[] radioButtons, int questionId){
        radioGroups.setTag(questionId);
        for(int i=0;i<radioButtons.length;i++) {
            radioButtons[i].setTag(questionId);
        }
    }

    private void setRadioOnChanged(RadioButton[] radioButtons, boolean isSet){
        for(int i=0;i<radioButtons.length;i++) {
            radioButtons[i].setOnCheckedChangeListener(isSet? this: null);
        }
    }


    private void setAnswerText(RadioButton[] radioButtons,Question question){
        for(int i=0;i<radioButtons.length;i++){
            RadioButton radioButton = radioButtons[i];
            String answer = question.getAnswer(i);
            if(showAnswer){
                radioButton.setText(Html.fromHtml(QuestionHelper.convertToColor(answer,
                        question.checkCorrectAnswer(i)?"blue":"black")));
            }else {
                radioButton.setText(Html.fromHtml(answer));
            }
        }
    }

    private void setQuestionText(Question question, TextView tvQuestion) {
        tvQuestion.setText(Html.fromHtml(question.getQuestion()
                .replace("<u>", "").replace("</u>", "")
                .trim()));
    }

    private void showCategoryQuestion(Question question, Button categoryButton) {
        if(question.getCategory()==null || question.getCategory().length()==0){
            categoryButton.setVisibility(View.GONE);
        }else{
            categoryButton.setVisibility(View.VISIBLE);
            categoryButton.setText(question.getCategory());
        }
    }

    private void setRadioButtonEnable(RadioButton[] radioButtons){
        for(RadioButton radioButton: radioButtons){
            radioButton.setEnabled(!showAnswer);
        }
    }

    private void setRadioButtonShow(RadioButton[] radioButtons, Question question){
        for(int i=0;i<radioButtons.length;i++){
            ViewUtils.setViewVisibility(radioButtons[i], question.getAnswer(i).length() > 0);
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.isChecked()){
            int questionIndex = (int)buttonView.getTag();
            int viewId = buttonView.getId();

            for(int id=0;id<radioButtonId.length;id++) {
                if (viewId == radioButtonId[id]) {
                    userSelection[questionIndex] = viewId;
                }
            }
        }
    }
}
