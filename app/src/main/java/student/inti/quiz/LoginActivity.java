package student.inti.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mTextUsername;
    private EditText mTextPassword;
    private Button mButtonLogin;
    private TextView mTextViewRegister;
    private DatabaseHelper db;
    private static String user = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();

                if (user.equals("")) {
                    Toast.makeText(LoginActivity.this, "Error! Please enter your username", Toast.LENGTH_SHORT).show();
                }
                else if (pwd.equals("")) {
                    Toast.makeText(LoginActivity.this, "Error! Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean res = db.checkUser(user, pwd);
                    if (res == true) {
                        Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(LoginActivity.this, QuizActivity.class);
                        startActivity(loginIntent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
