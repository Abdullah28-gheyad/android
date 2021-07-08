package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText phone , social ;
    Button login , test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize() ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login() ;
            }
        });


    }

    private void Login() {
        String num , soc ;
        num = phone.getText().toString() ;
        soc = social.getText().toString() ;
        if (num.equals("")||soc.equals(""))
        {
            Toast.makeText(this, "من فضلك ادخل البيانات الناقصه", Toast.LENGTH_SHORT).show();
        }
        else if (num.length()!=11||soc.length()!=14)
        {
            Toast.makeText(this, "من فضلك تأكد من البيانات وادخلها بشكل صحيح ثم اعد المحاوله مره اخري", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "أهلا بك", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this , Home.class) ;
            finish();
            startActivity(intent);
        }
    }

    private void initialize() {
        phone = (EditText)findViewById(R.id.phone_number) ;
        social = (EditText)findViewById(R.id.social_number) ;
        login = (Button)findViewById(R.id.Login) ;
    }


}