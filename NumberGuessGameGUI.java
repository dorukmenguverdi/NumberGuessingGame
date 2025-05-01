import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessGameGUI extends JFrame {
    private JTextField guessField;
    private JLabel resultLabel, attemptsLabel, instructionLabel;
    private NumberGuessGame game;
    private JComboBox<String> difficultyBox;

    public NumberGuessGameGUI() {

        game = new NumberGuessGame();

        // Label showing the guessing range
        instructionLabel = new JLabel("Guess a number between 1-" + game.getMaxNumber() + ":");
        instructionLabel.setBounds(20, 20, 250, 25);

        // Input field for the user's guess
        guessField = new JTextField();
        guessField.setBounds(250,20,100,25);

        // Button to submit the guess
        JButton guessButton = new JButton();
        guessButton.setText("Guess");
        guessButton.setBounds(140,60,120,30);

        // Button to restart the game
        JButton resetButton = new JButton();
        resetButton.setText("Start Over");
        resetButton.setBounds(140,100,120,30);

        // Dropdown menu for difficulty selection
        String[] difficulties = {"easy", "medium", "hard"};
        difficultyBox = new JComboBox<>(difficulties);
        difficultyBox.setBounds(270,60,100,25);

        // Label to display result messages
        resultLabel = new JLabel();
        resultLabel.setText("");
        resultLabel.setBounds(20,140,350,25);

        // Label to display the number of attempts
        attemptsLabel = new JLabel();
        attemptsLabel.setText("Attempts: 0");
        attemptsLabel.setBounds(20,160,200,25);

        // Action when the "Guess" button is clicked
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessField.getText());

                    String result = game.makeGuess(guess);

                    resultLabel.setText(result);

                    attemptsLabel.setText("Attempts: " + game.getAttempts());

                    // If the guess is correct, disable the input field
                    if (result.contains("Congratulations")) {
                        guessField.setEnabled(false);

                        long elapsedTime = game.getElapsedSeconds();
                        int score = game.calculateScore();

                        resultLabel.setText(result + " time: " + elapsedTime + "s | Score: " + score);

                    }
                }
                catch(NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
            }
        });

        // Action when the "Start Over" button is clicked
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String difficulty = (String) difficultyBox.getSelectedItem();
                int max = switch (difficulty) {
                    case "easy" -> 50;
                    case "hard" -> 500;
                    default -> 100;
                };

                game.setMaxNumber(max);
                game.reset(); // Generate a new number and reset attempts

                instructionLabel.setText("Guess a number between 1-" + max + ":");
                resultLabel.setText(""); // Clear the result message
                guessField.setText(""); // Clear the input field
                guessField.setEnabled(true); // Enable the input field again
                attemptsLabel.setText("Attempts: 0"); // Reset attempts display
            }
        });

        // Reset the game immediately when the difficulty is changed
        difficultyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String difficulty = (String) difficultyBox.getSelectedItem();
                int max = switch (difficulty) {
                    case "easy" -> 50;
                    case "hard" -> 500;
                    default -> 100;
                };

                game.setMaxNumber(max);
                game.reset();  // Start a new game with new max

                instructionLabel.setText("Guess a number between 1-" + max + ":");
                resultLabel.setText("");
                guessField.setText("");
                guessField.setEnabled(true);
                attemptsLabel.setText("Attempts: 0");
            }
        });


        // Window setup
        // Create and configure the main game window
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Number Guessing Game");
        frame.setSize(400,200);
        frame.setLayout(null);
        frame.setVisible(true);

        // Add components to the window
        frame.add(instructionLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(resetButton);
        frame.add(resultLabel);
        frame.add(attemptsLabel);
        frame.add(difficultyBox);


    }
}
