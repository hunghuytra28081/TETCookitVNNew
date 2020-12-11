package com.example.tetcookitvn.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.tetcookitvn.R;
import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {

    TextInputLayout regFullName, regUsername, regEmail, regPassword, regRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_signup);

        regFullName = findViewById(R.id.regFullName);
        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regRePassword = findViewById(R.id.regRePassword);
    }

    public void regNext(View view) {
        if (!validateFullName() || !validateUserName() || !validateEmail() || !validatePassword() || !validateRePassword()){
            return;
        }

        Intent intent = new Intent(this, Signup2.class);
        startActivity(intent);
    }

    public void regLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    public void regBack(View view) {
        Intent intent = new Intent(this, RetailerStartUpScreen.class);
        startActivity(intent);
        finish();
    }

    private Boolean validateFullName() {
        String fullname = regFullName.getEditText().getText().toString().trim();

        if (fullname.isEmpty()) {
            regFullName.setError("Chưa nhập Họ Tên");
            return false;
        } else {
            regFullName.setError(null);
            regFullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUserName() {
        String username = regUsername.getEditText().getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (username.isEmpty()) {
            regUsername.setError("Chưa nhập Tên Đăng Nhập ");
            return false;
        } else if (username.length() > 15) {
            regUsername.setError("Không được quá 15 từ");
            return false;
        } else if (!username.matches(noWhiteSpace)) {
            regUsername.setError("Không được có khoảng trắng ");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String email = regEmail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            regEmail.setError("Chưa nhập Email");
            return false;
        } else if (!email.matches(emailPattern)) {
            regEmail.setError("Email không hợp lệ ");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String password = regPassword.getEditText().getText().toString().trim();
//        String passwordHoa = "(?=.*[A-Z])";
        String passwordNumber = "(?=.*[0-9])";
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        String pass4Number = ".{4,}";
        if (password.isEmpty()) {
            regPassword.setError("Chưa nhập Mật Khẩu");
            return false;
        } else if (!password.matches(noWhiteSpace)) {
            regPassword.setError("Không được có khoảng trắng");
            return false;
        }
//        else if (!password.matches(passwordHoa)) {
//            regPassword.setError("Có ít nhất một kí tự in hoa");
//            return false;
//        }
        else if (!password.matches(passwordNumber)) {
            regPassword.setError("Có ít nhất một số");
            return false;
        } else if (!password.matches(pass4Number)) {
            regPassword.setError("Mật khẩu quá ngắn");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateRePassword(){
       String rePassword = regRePassword.getEditText().getText().toString().trim();
       String password = regPassword.getEditText().getText().toString().trim();
       if (!(rePassword == password)){
           regRePassword.setError("Kiểm tra lại mật khẩu");
           return false;
       }
       else {
           regRePassword.setError(null);
           regRePassword.setErrorEnabled(false);
           return true;
       }
    }
}