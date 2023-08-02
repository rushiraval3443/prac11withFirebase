package com.example.prac6;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    Button btn1p,btn1m,btn2p,btn2m,btn3p,btn3m,btn4p,btn4m;
    TextView tv_total;
    EditText eta,etb,etc,etd;
    int x,counta=0,countb=0, countc=0,countd=0;
    public void calculate(int a,int b, int c, int d)
    {
        x= (a*200) + (b*300) + (c*100) + (d*400);
        tv_total.setText(""+x+"Rs");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1p =  findViewById(R.id.btn1plus);
        btn1m =  findViewById(R.id.btn1minus);
        btn2p =  findViewById(R.id.btn2plus);
        btn2m =  findViewById(R.id.btn2minus);
        btn3p =  findViewById(R.id.btn3plus);
        btn3m =  findViewById(R.id.btn3minus);
        btn4p =  findViewById(R.id.btn4plus);
        btn4m =  findViewById(R.id.btn4minus);
        eta =  findViewById(R.id.et_1);
        etb =  findViewById(R.id.et_2);
        etc =  findViewById(R.id.et_3);
        etd =  findViewById(R.id.et_4);
        tv_total= findViewById(R.id.tv_total);
        btn1p.setOnClickListener(v -> {
            if(counta>=0) {
                counta++;
                eta.setText("" + counta);
                calculate(counta,countb,countc,countd);
            }
        });
        btn1m.setOnClickListener(v -> {
            if(counta>0) {
                counta--;
                eta.setText("" + counta);
                calculate(counta,countb,countc,countd);
            }
        });
        btn2p.setOnClickListener(v -> {
            if(countb>=0) {
                countb++;
                etb.setText("" + countb);
                calculate(counta,countb,countc,countd);
            }
        });
        btn2m.setOnClickListener(v -> {
            if(countb>0) {
                countb--;
                etb.setText("" + countb);
                calculate(counta,countb,countc,countd);
            }
        });
        btn3p.setOnClickListener(v -> {
            if(countc>=0) {
                countc++;
                etc.setText("" + countc);
                calculate(counta,countb,countc,countd);
            }
        });
        btn3m.setOnClickListener(v -> {
            if(countc>0) {
                countc--;
                etc.setText("" + countc);
                calculate(counta,countb,countc,countd);
            }
        });
        btn4p.setOnClickListener(v -> {
            if(countd>=0) {
                countd++;
                etd.setText("" + countd);
                calculate(counta,countb,countc,countd);
            }
        });
        btn4m.setOnClickListener(v -> {
            if(countd>0) {
                countd--;
                etd.setText("" + countd);
                calculate(counta,countb,countc,countd);
            }
        });
    }
}