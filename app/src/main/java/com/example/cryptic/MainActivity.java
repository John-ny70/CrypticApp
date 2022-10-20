package com.example.cryptic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String ALPHABET;
    String CODE;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ALPHABET="abcdefghijklmnopqrstuvwxyz";
        CODE="{'}/?><)(*&-_=+!$%^#`~@.,";
        msg="";
        Button btnConvert=findViewById(R.id.encrypt);
        btnConvert.setOnClickListener(this::check);
    }
    public void check(View view)
    {
        EditText input=findViewById(R.id.input);
        String message=input.getText().toString().toLowerCase().trim();
        char ch;
        if(!message.equals("")) {
            ch = message.charAt(0);
            if(ALPHABET.indexOf(ch)>=0)
                encryptText(message);
            else if(CODE.indexOf(ch)>=0)
                decryptText(message);
            else
                notFound(input);
        }
        else
            Toast.makeText(MainActivity.this,
                    "Blank Input", Toast.LENGTH_LONG).show();

    }

    public void encryptText(String message)
    {
        int size=message.length();
        for(int i=0;i<size;i++)
        {
            char ch=message.charAt(i);
            if(!Character.isWhitespace(ch)&&Character.isLetter(ch))
            {String s= String.valueOf(CODE.charAt(ALPHABET.indexOf(ch)));
            msg=msg.concat(s);}
            else
            { String st=Character.toString(ch);
                msg=msg.concat(st);}
        }

        Intent intent=new Intent(MainActivity.this,outputActivity.class);
        intent.putExtra("printable",msg);
        intent.putExtra("crypt",'e');
        startActivity(intent);
        msg="";
    }

    public void decryptText(String message)
    {
        int size=message.length();
        for(int i=0;i<size;i++)
        {
            char ch=message.charAt(i);
            if(!(Character.isWhitespace(ch)||Character.isLetterOrDigit(ch)))
            { String s= String.valueOf(ALPHABET.charAt(CODE.indexOf(ch)));
            msg=msg.concat(s);}
            else
            { String st=Character.toString(ch);
                msg=msg.concat(st);}
        }

       Intent intent=new Intent(MainActivity.this,outputActivity.class);
        intent.putExtra("printable",msg);
        intent.putExtra("crypt",'d');
        startActivity(intent);
        msg="";
    }
    private void notFound(EditText ed) {
        Toast.makeText(MainActivity.this,
                "The input is neither text nor code", Toast.LENGTH_LONG).show();
        ed.setHint(R.string.Input);
    }
}