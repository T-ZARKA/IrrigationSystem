package com.example.android.irrigationsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView i,l,t,h,p,d;
Button on,off;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l=findViewById(R.id.l_tv);
        t=findViewById(R.id.t_tv);
        h=findViewById(R.id.h_tv);
        p=findViewById(R.id.pumb_tv);
        d=findViewById(R.id.d_tv);
        on=findViewById(R.id.on);
        off=findViewById(R.id.off);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setText("1");

            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setText("0");

            }
        });
    }
}
