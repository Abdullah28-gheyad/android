package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    int question_count = 1 , score_count = 0 ;
    RadioButton radioButton1 , radioButton2  ;
    RadioGroup radioGroup ;
    TextView question ;
    Button next ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation) ;
        radioButton1 = (RadioButton)findViewById(R.id.radioButton) ;
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2) ;
        question = (TextView)findViewById(R.id.question) ;
        next = (Button)findViewById(R.id.next) ;
        radioGroup = (RadioGroup)findViewById(R.id.radio_group) ;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioButton1.isChecked()&&!radioButton2.isChecked())
                {
                    Toast.makeText(Home.this, "من فضلك أدخل اجابه", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (radioButton1.isChecked())
                    {
                        score_count+=1 ;
                    }
                    if (question_count==1)
                    {
                        question.setText("هل لديك سعال جاف ؟");
                        radioButton1.setText("نعم");
                        radioButton2.setText("لا");
                    }
                    else if(question_count==2)
                    {
                        question.setText("هل تشعر بتعب في المفاصل  ؟");
                        radioButton1.setText("نعم");
                        radioButton2.setText("لا");
                    }
                    else if (question_count==3)
                    {
                        question.setText("هل تشعر بضيق في التنفس؟");
                        radioButton1.setText("نعم");
                        radioButton2.setText("لا");
                    }
                    else if (question_count==4)
                    {
                        question.setText("هل تشعر بالتهاب الحلق؟");
                        radioButton1.setText("نعم");
                        radioButton2.setText("لا");
                    }
                    else if (question_count==5)
                    {
                        question.setText("هل لديك أسهال؟ ");
                        radioButton1.setText("نعم ");
                        radioButton2.setText("لا ");
                    }
                    if (question_count>=6)
                    {
                        radioGroup.setVisibility(View.GONE);
                        if (question_count==6)
                        {
                            if (score_count>=3)
                            {
                                question.setText("للأسف هناك أحتمال كبير ان تكون مصاب بكورونا اذهب لاقرب طبيب في أسرع وقت!! ");
                            }
                            else
                            {
                                question.setText("لا داعي للقلق انها نزله برد موسميه ");
                            }
                        }
                        else
                        {
                            Intent intent = new Intent(Home.this , Check_covid.class) ;
                            startActivity(intent);
                        }

                    }
                    question_count+=1 ;
                }
            }
        });










        bottomNavigationView.setSelectedItemId(R.id.Home_);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.phone_important:
                        startActivity(new Intent(Home.this , Important_phone.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Articles_:
                        startActivity(new Intent(Home.this , Articles.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.dashboard:
                        startActivity(new Intent(Home.this , Dashboard.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.about:
                        startActivity(new Intent(Home.this , About.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true ;
                    case R.id.Home_:
                        return true ;
                }
                return false;
            }
        });



    }
}