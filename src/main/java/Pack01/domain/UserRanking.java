package Pack01.domain;

public class UserRanking {
    private long userId;
    private String name;
    private int correct;
    private int tried;
    private int score;
    private double solveRatio;

    private int ranking;

    public UserRanking(long userId, String name, int correct, int tried, int score, double solveRatio, int ranking) {
        this.userId = userId;
        this.name = name;
        this.correct = correct;
        this.tried = tried;
        this.score = score;
        this.solveRatio = solveRatio;
        this.ranking = ranking;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getCorrect() {
        return correct;
    }

    public int getTried() {return tried; }

    public int getScore() {
        return score;
    }

    public double getSolveRatio() {
        return solveRatio;
    }

    public int getRanking(){
        return this.ranking;
    }
}
