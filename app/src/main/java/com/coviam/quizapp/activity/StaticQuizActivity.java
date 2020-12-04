package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.coviam.quizapp.R;
import com.coviam.quizapp.api.APIInterface;
import com.coviam.quizapp.api.App2;
import com.coviam.quizapp.api.App4;
import com.coviam.quizapp.api.App5;
import com.coviam.quizapp.api.App7;
import com.coviam.quizapp.pojo.Analytics;
import com.coviam.quizapp.pojo.AnswerDTO;
import com.coviam.quizapp.pojo.QuestionAnsDTOListItem;
import com.coviam.quizapp.pojo.QuestionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaticQuizActivity extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 45000;

    List<QuestionDTO> questionDTOS;
    ProgressBar loadbar;
    TextView contestName;
    TextView qusetionName;
    ImageView questionImage;
    VideoView questionView;
    Button skip;
    Button next;
    TextView answer;
    TextView optionA;
    TextView optionB;
    TextView optionC;
    CheckBox checkA;
    CheckBox checkB;
    CheckBox checkC;
    TextView textViewCountDown;
    int numberOfQuestions;
    int correctAnswers;
    CountDownTimer countDownTimer;
    long timeLeftInMillis;
    int counter=0;
    int skipLimit=3;
    int skipStart=0;
    ImageButton refresh;
    Intent intent;
    SharedPreferences sharedPreferences;

    List<QuestionAnsDTOListItem> ansDTOListItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        intent=getIntent();
        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        views();
        contestName.setText(intent.getStringExtra("contestName"));
        final APIInterface apiInterface= App2.getClient().create(APIInterface.class);
        questionDTOS=new ArrayList<QuestionDTO>();
        ansDTOListItems=new ArrayList<QuestionAnsDTOListItem>();

        checkA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    answer.setText(answer.getText().toString()+"A");
                }
            }
        });

        checkB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    answer.setText(answer.getText().toString()+"B");
                }
            }
        });

        checkC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    answer.setText(answer.getText().toString()+"C");
                }
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });


        intent.getStringExtra("contestId");
        apiInterface.getQuestions(intent.getStringExtra("contestId")).enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(final Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {

                loadbar.setVisibility(View.GONE);
                timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                startCountDown();
                questionDTOS=response.body();

                // set
                correctAnswers=0;
                numberOfQuestions = questionDTOS.size();
                showNextQuestion();

                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(skipStart<skipLimit) {
                            skipStart++;
//                            QuestionAnsDTOListItem questionAnsDTOListItem = new QuestionAnsDTOListItem();
//                            questionAnsDTOListItem.setTimetaken(COUNTDOWN_IN_MILLIS - timeLeftInMillis);
//                            ansDTOListItems.add(questionAnsDTOListItem);
//                            submitanswer(ansDTOListItems);
//                            ansDTOListItems.remove(questionAnsDTOListItem);
//                            counter++;
//                            showNextQuestion();
                            if((counter+1)%3!=0) {
                                QuestionAnsDTOListItem questionAnsDTOListItem = new QuestionAnsDTOListItem();
                                questionAnsDTOListItem.setTimetaken(COUNTDOWN_IN_MILLIS - timeLeftInMillis);
                                ansDTOListItems.add(questionAnsDTOListItem);
                                counter++;
                                showNextQuestion();
                            }
                            else{
                                QuestionAnsDTOListItem questionAnsDTOListItem = new QuestionAnsDTOListItem();
                                questionAnsDTOListItem.setTimetaken(COUNTDOWN_IN_MILLIS - timeLeftInMillis);
                                ansDTOListItems.add(questionAnsDTOListItem);
                                counter++;
                                callAdd();
                                showNextQuestion();

                            }
                        }
                        else {
                            Toast.makeText(StaticQuizActivity.this,"Can't skip more than three questions",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        QuestionAnsDTOListItem questionAnsDTOListItem = new QuestionAnsDTOListItem();
//                        questionAnsDTOListItem.setAnswer(answer.getText().toString());
//                        questionAnsDTOListItem.setTimetaken(COUNTDOWN_IN_MILLIS - timeLeftInMillis);
//                        questionAnsDTOListItem.setQuestionId(questionDTOS.get(counter).getQuestionId());
//                        ansDTOListItems.add(questionAnsDTOListItem);
//                        submitanswer(ansDTOListItems);
//                        ansDTOListItems.remove(questionAnsDTOListItem);
//                        counter++;
//                        showNextQuestion();
                        //save answer in answer dto
                        if((counter+1)%3!=0) {
                            QuestionAnsDTOListItem questionAnsDTOListItem = new QuestionAnsDTOListItem();
                            questionAnsDTOListItem.setAnswer(answer.getText().toString());
                            questionAnsDTOListItem.setTimetaken(COUNTDOWN_IN_MILLIS - timeLeftInMillis);
                            questionAnsDTOListItem.setQuestionId(questionDTOS.get(counter).getQuestionId());
                            ansDTOListItems.add(questionAnsDTOListItem);
                            if(check(answer.getText().toString(),questionDTOS.get(counter).getAnswers()))
                                correctAnswers++;
                            counter++;
                            showNextQuestion();
                        }
                        else {
                            QuestionAnsDTOListItem questionAnsDTOListItem = new QuestionAnsDTOListItem();
                            questionAnsDTOListItem.setAnswer(answer.getText().toString());
                            questionAnsDTOListItem.setTimetaken(COUNTDOWN_IN_MILLIS - timeLeftInMillis);
                            questionAnsDTOListItem.setQuestionId(questionDTOS.get(counter).getQuestionId());
                            ansDTOListItems.add(questionAnsDTOListItem);
                            if(check(answer.getText().toString(),questionDTOS.get(counter).getAnswers()))
                                correctAnswers++;
                            counter++;
                            callAdd();
                            showNextQuestion();
                        }

                    }
                });

            }

            @Override
            public void onFailure(Call<List<QuestionDTO>> call, Throwable t) {
                loadbar.setVisibility(View.GONE);
                Toast.makeText(StaticQuizActivity.this,"server error",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void submitanswer(final List<QuestionAnsDTOListItem> ansDTOListItems) {

        APIInterface apiInterface= App5.getClient().create(APIInterface.class);
        AnswerDTO answerDTO=new AnswerDTO();
        answerDTO.setQuestionAnsDTOList(ansDTOListItems);
        answerDTO.setContestId(intent.getStringExtra("contestId"));
        answerDTO.setUserID(String.valueOf(sharedPreferences.getInt("userId",0)));
        answerDTO.setNoOfskips(skipStart);
        apiInterface.submitAnswers(answerDTO).enqueue(new Callback<com.coviam.quizapp.pojo.Response>() {
            @Override
            public void onResponse(Call<com.coviam.quizapp.pojo.Response> call, Response<com.coviam.quizapp.pojo.Response> response) {
                if(response.body().isStatus()){
                    Toast.makeText(StaticQuizActivity.this,"success",Toast.LENGTH_LONG).show();
                    APIInterface apiInterface1= App7.getClient().create(APIInterface.class);
                    Analytics analytics=new Analytics();
                    analytics.setAction("quiz");
                    analytics.setUserId(String.valueOf(sharedPreferences.getInt("userId",0)));
                    analytics.setContestId(intent.getStringExtra("contestId"));
                    long time=0;
                    for(int cnt=0;cnt<ansDTOListItems.size();cnt++){
                        time+=ansDTOListItems.get(cnt).getTimetaken();

                    }
                    analytics.setContestTime(time);
                    analytics.setContestName(contestName.toString());
                    apiInterface1.analytics(analytics).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(StaticQuizActivity.this,"analytics success",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(StaticQuizActivity.this,"analytics fail",Toast.LENGTH_SHORT).show();

                        }
                    });


                }
                else {
                    Toast.makeText(StaticQuizActivity.this,"questions not sub",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<com.coviam.quizapp.pojo.Response> call, Throwable t) {

                Toast.makeText(StaticQuizActivity.this,"fuck!!",Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void showNextQuestion() {
        if(counter<numberOfQuestions){
            QuestionDTO questionDTO=questionDTOS.get(counter);
            qusetionName.setText(questionDTO.getQuestionText());
            setValues(questionDTO);
            resetValues();
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            countDownTimer.cancel();
            startCountDown();
        }
        else{
            //api call to send answer
             Toast.makeText(StaticQuizActivity.this,"quiz completed",Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(StaticQuizActivity.this,DummyActivity.class);
            submitanswer(ansDTOListItems);
            countDownTimer.cancel();
            intent.putExtra("points",correctAnswers);
            intent.putExtra("noOfQuestions",numberOfQuestions);
            startActivity(intent);
            finish();
        }
    }

    void views(){
        loadbar=findViewById(R.id.progressBar);
        contestName=findViewById(R.id.contestName);
        qusetionName=findViewById(R.id.questionName);
        questionImage=findViewById(R.id.questionImage);
        questionView=findViewById(R.id.questionVideo);
        skip=findViewById(R.id.skip);
        next=findViewById(R.id.next);
        answer=findViewById(R.id.answer);
        optionA=findViewById(R.id.optionA);
        optionB=findViewById(R.id.optionB);
        optionC=findViewById(R.id.optionC);
        textViewCountDown=findViewById(R.id.text_view_countdown);
        checkA=findViewById(R.id.checkBoxA);
        checkB=findViewById(R.id.checkBoxB);
        checkC=findViewById(R.id.checkBoxC);
        refresh=findViewById(R.id.refresh);

    }

    void startCountDown(){
        countDownTimer=new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                QuestionAnsDTOListItem questionAnsDTOListItem=new QuestionAnsDTOListItem();
                questionAnsDTOListItem.setTimetaken(COUNTDOWN_IN_MILLIS-timeLeftInMillis);
                questionAnsDTOListItem.setQuestionId(questionDTOS.get(counter).getQuestionId());
                ansDTOListItems.add(questionAnsDTOListItem);
                if(counter<numberOfQuestions) {
                    if((counter+1)%3!=0) {
                        counter++;
                        showNextQuestion();
                    }
                    else {
                        counter++;
                        callAdd();
                        showNextQuestion();
                    }
                }
                else{
                    Toast.makeText(StaticQuizActivity.this,"quiz completed",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(StaticQuizActivity.this,DummyActivity.class);

                    submitanswer(ansDTOListItems);
                    intent.putExtra("points",correctAnswers);
                    intent.putExtra("noOfQuestions",numberOfQuestions);
                    startActivity(intent);
                    finish();
                }

            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(Color.BLACK);
        }

    }

    void setValues(QuestionDTO questionDTO){

        //1
        switch (questionDTO.getQuestionFormat()){
            case "text":
                optionA.setVisibility(View.VISIBLE);
                optionB.setVisibility(View.VISIBLE);
                optionC.setVisibility(View.VISIBLE);
                questionImage.setVisibility(View.GONE);
                questionView.setVisibility(View.GONE);
                break;
            case "image":
                questionImage.setVisibility(View.VISIBLE);
                questionView.setVisibility(View.GONE);
                Glide.with(StaticQuizActivity.this).load(questionDTO.getUrlAttachment()).into(questionImage);
                break;
            case "video":
                questionImage.setVisibility(View.GONE);
                questionView.setVisibility(View.VISIBLE);
                //image insertion:
                break;
            case "audio":
                //audio insertion:
                break;
        }
        //2

        switch (questionDTO.getQuestionType()){
            case 0:
                answer.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
                break;
            case 1:
                answer.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});
                break;
            case 2:
                answer.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});
        }

        //3
        optionA.setText(questionDTO.getOptions().getA());
        optionB.setText(questionDTO.getOptions().getB());
        optionC.setText(questionDTO.getOptions().getC());


    }
    void resetValues(){
        if(checkA.isChecked()){
            checkA.setChecked(false);
        }
        if(checkB.isChecked()){
            checkB.setChecked(false);
        }
        if(checkC.isChecked()){
            checkC.setChecked(false);
        }
        answer.setText("");
    }

    void callAdd(){
//        Intent intent=new Intent(StaticQuizActivity.this,AdActivity.class);
//        intent.putExtra("counter",counter+1);
//        startActivity(intent);

    }

    Boolean check(String answer,String verAnswer){
        if(answer.toUpperCase().equals(verAnswer.toUpperCase()))
            return true;
        else{
            return false;
        }

    }


}
