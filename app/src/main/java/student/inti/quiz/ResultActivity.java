package student.inti.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private TextView mCorrectQuestion;
    private TextView mWrongQuestion;
    private TextView mCheatQuestion;
    private TextView mUserTitle;
    private Button mTryAgain;
    private Button mLogout;
    private DatabaseHelper db;
    private static String user = "username";
    private Question mQuestion = new Question();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db = new DatabaseHelper(this);
        mUserTitle = (TextView) findViewById(R.id.user);
        mUserTitle.setText(db.getUser());
        mCorrectQuestion = (TextView) findViewById(R.id.correct_result_value);
        mCorrectQuestion.setText(String.valueOf(mQuestion.getCorrect()));
        mWrongQuestion = (TextView) findViewById(R.id.wrong_result_value);
        mWrongQuestion.setText(String.valueOf(mQuestion.getWrong()));
        mCheatQuestion = (TextView) findViewById(R.id.cheat_result_value);
        mCheatQuestion.setText(String.valueOf(mQuestion.getCheat()));
        mTryAgain = (Button) findViewById(R.id.try_again_btn);
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuestion.setCorrect(0);
                mQuestion.setWrong(0);
                mQuestion.setCheat(0);
                Intent i = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(i);
            }
        });
        mLogout = (Button) findViewById(R.id.btn_logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuestion.setCorrect(0);
                mQuestion.setWrong(0);
                mQuestion.setCheat(0);
                Intent i = new Intent(ResultActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(ResultActivity.this, "Successfully Logged out!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
