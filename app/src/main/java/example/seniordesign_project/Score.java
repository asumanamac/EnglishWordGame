package example.seniordesign_project;

/**
 * Created by - on 25.1.2017.
 */

public class Score {

    public static int score=0;



    public  static int increaseScore()
    {
        score+=10;
        return score;
    }

    public static int decreaseScore()
    {
        score-=10;
        if(score<0)
            score=0;

        return score;
    }

    public static int resetScore()
    {
        score=0;
        return score;
    }



}
