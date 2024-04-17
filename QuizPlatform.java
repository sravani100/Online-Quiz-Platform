import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizPlatform extends JFrame {

    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JRadioButton[] answerOptions;
    private ButtonGroup buttonGroup;
    private JButton submitButton;

    private String[][] questions = {
        {"What is the capital of France?", "Berlin", "Paris", "Madrid", "Rome", "Paris"},
        {"Which programming language is known for its use in web development?", "Java", "Python", "HTML", "CSS", "HTML"},
        {"What is the largest planet in our solar system?", "Earth", "Jupiter", "Mars", "Venus", "Jupiter"},
        {"Which of the following is a primary color?", "Green", "Orange", "Blue", "Purple", "Blue"},
        {"What is the result of 2 + 2 * 2?", "4", "6", "8", "10", "6"}
    };

    public QuizPlatform() {
        setTitle("Simple Quiz Platform");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createComponents();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void createComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
    
        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        JScrollPane questionScrollPane = new JScrollPane(questionLabel);
        panel.add(questionScrollPane);
    
        answerOptions = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
    
        for (int i = 0; i < 4; i++) {
            answerOptions[i] = new JRadioButton();
            buttonGroup.add(answerOptions[i]);
            panel.add(answerOptions[i]);
        }
    
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
    
        add(panel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    
        setNextQuestion();
    }
    

    private void setNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex][0]);

            for (int i = 0; i < 4; i++) {
                answerOptions[i].setText(questions[currentQuestionIndex][i + 1]);
                answerOptions[i].setSelected(false);
            }

            submitButton.setEnabled(true);
        } else {
            showResults();
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (answerOptions[i].isSelected()) {
                if (answerOptions[i].getText().equals(questions[currentQuestionIndex][5])) {
                    score++;
                }
                break;
            }
        }

        currentQuestionIndex++;
        setNextQuestion();
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this, "Quiz completed!\nYour score: " + score + " out of " + questions.length,
                "Results", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizPlatform();
            }
        });
    }
}