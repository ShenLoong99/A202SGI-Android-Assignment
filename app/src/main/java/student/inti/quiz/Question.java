package student.inti.quiz;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private int mqID = 0;
    private static int correct = 0;
    private static int wrong = 0;
    private static int cheat = 0;

    public Question() {

    }

    public Question (int textResId, boolean answerTrue) {
        mqID++;
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getMqID() {
        return mqID;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getCheat() {
        return cheat;
    }

    public void setCheat(int cheat) {
        this.cheat = cheat;
    }
}
