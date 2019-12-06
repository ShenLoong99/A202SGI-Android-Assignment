package student.inti.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText mTextUsername;
    private EditText mTextPassword;
    private EditText mTextCnfPassword;
    private Button mButtonRegister;
    private TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText) findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if (user.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Error, please enter your username!", Toast.LENGTH_SHORT).show();
                }
                else if (pwd.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Error, please enter your password!", Toast.LENGTH_SHORT).show();
                }
                else if (cnf_pwd.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Error, please enter confirm password!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pwd.equals(cnf_pwd)) {
                        long val = db.addUser(user, pwd);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "You have Registered", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(moveToLogin);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
