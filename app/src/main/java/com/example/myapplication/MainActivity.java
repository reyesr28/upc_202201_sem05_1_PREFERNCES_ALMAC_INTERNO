package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    EditText txtUsu,txtPass;
    CheckBox chkRecordar;

    SharedPreferences pref;
    String valor="",usu="",pas="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar=findViewById(R.id.btnIniciar);
        txtUsu=findViewById(R.id.txtUsuario);
        txtPass=findViewById(R.id.txtPassword);
        chkRecordar=findViewById(R.id.chkRecordar);

        pref=getSharedPreferences("preferencia1",MODE_PRIVATE);

        boolean check=Boolean.parseBoolean(pref.getString("val","false"));
        String usu=pref.getString("usuario","");
        String pass=pref.getString("pass","");

        if(check==true){
            txtUsu.setText(usu);
            txtPass.setText(pass);
            chkRecordar.setChecked(true);
        }else{
            txtUsu.setText(null);
            txtPass.setText(null);
            chkRecordar.setChecked(false);
        }


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtUsu.getText().toString().equalsIgnoreCase("Astunio") &&
                txtPass.getText().toString().equalsIgnoreCase("123")){

                    pref=getSharedPreferences("preferencia1",MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();

                    if(chkRecordar.isChecked()){
                        editor.putString("val","true");
                        editor.putString("usuario",txtUsu.getText().toString());
                        editor.putString("pass",txtPass.getText().toString());
                    }else{
                        editor.putString("val","");
                        editor.putString("usuario","");
                        editor.putString("pass","");
                    }

                    editor.commit();

                    Intent i=new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(), "Usuario o pass incorrectos",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}