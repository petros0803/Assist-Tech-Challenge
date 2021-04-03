package com.example.myhotel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.myhotel.api.model.Login;
import com.example.myhotel.R;
import com.example.myhotel.api.ApiClient;
import com.example.myhotel.api.models.Login;
import com.example.myhotel.data.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView new_account = findViewById(R.id.txt_newacc);
        email = findViewById(R.id.email_input);
        pass = findViewById(R.id.pass_input);
        Button btn_login = findViewById(R.id.lgn_btn);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateCredentials()) {
                    login();

                }
            }
        });
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                        finish();
            }
        });
    }


    private boolean validateEmail() {
        String textemailInput = email.getEditableText().toString().trim();
        if (textemailInput.isEmpty()) {
            email.setError("Please insert a email address");
            email.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(textemailInput).matches()) {
            email.setError("Email address invalid");
            email.requestFocus();
            return false;
        } else
            return true;
    }

    private boolean validatePass() {
        String Textpassinput = pass.getEditableText().toString().trim();
        if (Textpassinput.isEmpty()) {
            pass.setError("Insert a password");
            pass.requestFocus();
            return false;

        } else {
            return true;
        }
    }

    private boolean validateCredentials() {
        if (!validateEmail()) {
            return false;
        }

        if (!validatePass()) {
            Toast.makeText(LoginActivity.this, "LogIn Failed", LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void login() {
        Login login = new Login(email.getEditableText().toString().trim(), pass.getEditableText().toString().trim());
        Call<Login> call = ApiClient.getApiInterface().performLOGIN(login);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getToken() != null) {
                            Singleton.getInstance().setName(response.body().getName() + " " + response.body().getSurname());
                            Singleton.getInstance().setPhoneNumber(response.body().getPhoneNumber());
                            Singleton.getInstance().setEmail(response.body().getEmail());
                            Singleton.getInstance().setToken(response.body().getToken());
                            startActivity(new Intent(getApplicationContext(), MainMenu.class));
                            finish();
                        }
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.w("onFailureResponse-->", t);
                Toast.makeText(LoginActivity.this, "Connection failed", LENGTH_SHORT).show();
            }
        });


    }

}