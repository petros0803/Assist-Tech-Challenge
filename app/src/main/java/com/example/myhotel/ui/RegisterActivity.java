package com.example.myhotel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhotel.R;
import com.example.myhotel.api.ApiClient;
import com.example.myhotel.api.RestApiInterface;

import com.example.myhotel.api.models.Register;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText useremail;
    private EditText inputpass;
    private EditText confirmpass;
    private EditText phone;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,16}" +               //at least 6 characters
                    "$");
    public static final Pattern IDENTITY_PATTERN =
            Pattern.compile("^" +
                    "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)" +
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.name_input);
        useremail = findViewById(R.id.email_input_create);
        inputpass = findViewById(R.id.pass_input_create);
        confirmpass = findViewById(R.id.confirm_pass);
        phone = findViewById(R.id.phone_create);
        Button btn_register = findViewById(R.id.btn_register);
        TextView bck=findViewById(R.id.txt_bk_to_login);

        bck.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        btn_register.setOnClickListener(v -> {

            register();
            Toast.makeText(RegisterActivity.this, "Proceeding to login", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });


    }

    private boolean validateName() {
        String name = username.getEditableText().toString().trim();
        if (name.isEmpty()) {
            username.setError("Insert name and last name");
            username.requestFocus();
            return false;
        } else if (name.length() > 40 & !IDENTITY_PATTERN.matcher(name).matches()) {
            username.setError("Invalid name\tNo more than 30 characters\tNo special characters");
            username.requestFocus();
            return false;
        } else
            return true;
    }

    private boolean validateEmail() {
        String email = useremail.getEditableText().toString().trim();
        if (email.isEmpty()) {
            useremail.setError("Please insert a email address");
            useremail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            useremail.setError("Insert a valid mail adress");
            useremail.requestFocus();
            return false;
        } else
            return true;
    }

    private boolean validatePass() {
        String pass = inputpass.getEditableText().toString().trim();
        if (pass.isEmpty()) {
            inputpass.setError("Insert a password");
            inputpass.requestFocus();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
            inputpass.setError("Password must be between 6-16 charachters\tMust contain at least 1 digit");
            inputpass.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateConfirm() {
        String pass = inputpass.getEditableText().toString().trim();
        String cpass = confirmpass.getEditableText().toString().trim();
        if (cpass.isEmpty()) {
            confirmpass.setError("Re-type your password");
            confirmpass.requestFocus();
            return false;
        } else if (!(cpass.equals(pass))) {
            confirmpass.setError("Does not match with password");
            confirmpass.requestFocus();
            return false;
        } else return validatePass();
    }

    private boolean validateNumber() {
        String number = phone.getEditableText().toString().trim();
        if (number.isEmpty()) {
            phone.setError("Phone number required");
            phone.requestFocus();
            return false;
        } else if (number.length() > 14) {
            phone.setError("Number is too long");
            return false;
        } else return true;

    }

    private boolean validateCredintials() {
        if (!validateName())
            return false;
        if (!validateEmail())
            return false;
        if (!validatePass())
            return false;
        if (!validateConfirm())
            return false;
        return validateNumber();
    }

    public void register() {
        if (validateCredintials()) {
            Register reg = new Register();
            String[] parts = username.getEditableText().toString().trim().split(" ", 2);parts[1].trim();
            reg.setName(parts[0].trim());
            reg.setSurname(parts[1].trim());
            reg.setPassword(inputpass.getEditableText().toString().trim());
            reg.setEmail(useremail.getEditableText().toString().trim());
            reg.setPhoneNumber(phone.getEditableText().toString().trim());
            reg.setRole("user");

            Call<Register> call = ApiClient.getApiClient().create(RestApiInterface.class).performREGISTER(reg);

            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(@NotNull Call<Register> call, @NotNull Response<Register> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Succesfully Register", Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegisterActivity.this, "Proceeding to login", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        if (response.body() != null) {


                         } else {
                            Toast.makeText(RegisterActivity.this, "Nothing has been returned", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Register> call, @NotNull Throwable t) {
                    Toast.makeText(RegisterActivity.this, "FCKED UP", Toast.LENGTH_LONG).show();
                    Log.w("Connection Problems", t);

                }
            });
        }
    }
}