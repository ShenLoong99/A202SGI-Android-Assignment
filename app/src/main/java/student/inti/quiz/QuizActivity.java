package student.inti.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private int correct = 1;
    private int wrong = 1;
    private int cheat = 1;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private Question mQuestion = new Question();

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_earth, true),
            new Question(R.string.question_river, false),
            new Question(R.string.question_lake, true),
            new Question(R.string.question_coast, false),
            new Question(R.string.question_dry, true),
            new Question(R.string.question_pyramid, false),
            new Question(R.string.question_oldest, true),
            new Question(R.string.question_flat, false),
            new Question(R.string.question_mount, true),
            new Question(R.string.question_moon, false)
    };

    private void checkAnswer (boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(mIsCheater) {
            messageResId = R.string.judgement_toast;
            mIsCheater = false;
            mQuestion.setCheat(cheat++);
            mCurrentIndex = mCurrentIndex + 1;
            updateQuestion();
        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                mQuestion.setCorrect(correct++);
                mCurrentIndex = mCurrentIndex + 1;
                updateQuestion();
            }
            else {
                messageResId = R.string.incorrect_toast;
                mQuestion.setWrong(wrong++);
                mCurrentIndex = mCurrentIndex + 1;
                updateQuestion();
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    private void updateQuestion () {
        if (mCurrentIndex == mQuestionBank.length) {
            Intent res = new Intent(QuizActivity.this, ResultActivity.class);
            startActivity(res);
        }
        else {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start Cheat Activity
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
}
