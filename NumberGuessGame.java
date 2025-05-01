public class NumberGuessGame {
    private int targetNumber;
    private int attempts;
    private int maxNumber;
    private long startTime;
    private long endTime;
    private boolean gameEnded;

    public NumberGuessGame() {
        this.maxNumber = 100;
        reset();
    }

    public String makeGuess(int guess) {
        if (gameEnded){
            return "Game is already over. Please start a new game.";
        }

        attempts++;
        if (guess < targetNumber)
            return "Enter a larger number\n";
        else if (guess > targetNumber)
            return "Enter a smaller number";
        else {
            endTime = System.currentTimeMillis();
            gameEnded = true;
            return "Congratulations! You guessed right!";
        }
    }

    public int getAttempts() {
        return attempts;
    }

    public void reset() {
        targetNumber = (int)(Math.random() * maxNumber)+ 1;
        attempts = 0;
        startTime = System.currentTimeMillis();
        endTime = 0;
        gameEnded = false;
    }

    public long getElapsedSeconds() {
        if (!gameEnded) {
            return (System.currentTimeMillis() - startTime) / 1000;
        }
        return (endTime - startTime) / 1000;
    }

    public  int calculateScore() {
        long time = getElapsedSeconds();
        return (int) Math.max(0,1000 - (time * 10 + attempts * 5));
    }

    public int getMaxNumber(){
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

}
