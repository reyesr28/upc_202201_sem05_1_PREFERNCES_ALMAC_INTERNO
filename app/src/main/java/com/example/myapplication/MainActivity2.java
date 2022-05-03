package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity2 extends AppCompatActivity {

    EditText txtTexto;
    Button btnGrabar,btnRecuperar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtTexto=findViewById(R.id.txtTexto);
        btnGrabar=findViewById(R.id.btnGrabar);
        btnRecuperar=findViewById(R.id.btnRecuperar);

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String str=txtTexto.getText().toString();
               try{
                   OutputStreamWriter fout=new OutputStreamWriter
                           (openFileOutput("archivo.txt",
                                   Context.MODE_APPEND));
                   fout.write(str+"\n");
                   fout.close();
                   txtTexto.setText(null);
                   txtTexto.requestFocus();
               }catch (Exception e){}


            }
        });

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStreamReader archivo = new InputStreamReader
                            (openFileInput("archivo.txt"));
                    BufferedReader br=new BufferedReader(archivo);
                    String linea=br.readLine();

                    String general="";

                    while(linea!=null){
                        general+=linea+"\n";
                        linea=br.readLine();
                    }
                    br.close();
                    txtTexto.setText(general);

                }catch (Exception e){}
            }
        });

    }
}