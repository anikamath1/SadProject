package com.example.android.restuarantfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class StartUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView TextViewSignIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        TextViewSignIn=(TextView)findViewById(R.id.textViewSignIn);
        buttonRegister.setOnClickListener(this);
        TextViewSignIn.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
        }

    }

    private void registerUser()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {

            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {

            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            Toast.makeText(StartUpActivity.this,"Registration Suceesful",Toast.LENGTH_SHORT).show();

                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                        }
                        else
                        {
                            Toast.makeText(StartUpActivity.this,"Could Not Register",Toast.LENGTH_SHORT).show();

                        }
                    }
                });



    }


    @Override
    public void onClick(View view) {
        if(view==buttonRegister)
            registerUser();
        if(view==TextViewSignIn)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }

    }
}