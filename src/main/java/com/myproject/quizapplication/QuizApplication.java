package com.myproject.quizapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class QuizApplication extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5];
    JButton btnNext, btnResult;
    ButtonGroup bg;
    int count = 0, current = 0;
    String fileName = "src/resources/quiz3.txt";
    List<QuizData> quizData = readQuizData(fileName);

    // create jFrame with radioButton and JButton
    QuizApplication(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();
        for(int i = 0; i <5; i++) {
            radioButton[i] = new JRadioButton();
            add(radioButton[i]);
            bg.add(radioButton[i]);
        }
        btnNext = new JButton("Next");
        btnResult = new JButton("Result");
        btnResult.setVisible(false);
        btnNext.addActionListener(this);
        btnResult.addActionListener(this);
        add(btnNext);
        add(btnResult);
        set();
        label.setBounds(30, 40, 450, 20);
        radioButton[0].setBounds(50, 80, 450, 20);
        radioButton[1].setBounds(50, 110, 200, 20);
        radioButton[2].setBounds(50, 140, 200, 20);
        radioButton[3].setBounds(50, 170, 200, 20);
        btnNext.setBounds(100, 240, 100, 30);
        btnResult.setBounds(270, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }



    // handle all actions based on event
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNext) {
            if(check())
                count = count +1;
            current++;
            set();
            if(current == quizData.size() - 1) {
                btnNext.setEnabled(false);
                btnResult.setVisible(true);
                btnResult.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "Correct Answers= " + count);
            System.exit(0);
        }
    }

    public static List<QuizData> readQuizData(String fileName) {
        List<QuizData> quizData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String question = line;
                List<String> answers = new ArrayList<>();
                for (int i = 0; i <4; i ++) {
                    line = reader.readLine();
                    answers.add(line);
                }
                int correctAnswerIndex = Integer.parseInt(reader.readLine());

                QuizData data = new QuizData(question, answers, correctAnswerIndex);
                quizData.add(data);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return quizData;
    }

    // SET Questions with options
    void set() {
        radioButton[4].setSelected(true);
        for(int i = 0; i < quizData.size(); i++) {
            if(current == i) {
                String question = quizData.get(i).getQuestion();
                List<String> answers = quizData.get(i).getAnswers();
                label.setText(question);
                for(int j = 0; j < 4; j ++) {
                    radioButton[j].setText(answers.get(j));
                }
            }
        }
        label.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            radioButton[j].setBounds(50, 80 + i, 200, 20);
    }

    // declare right answers.
    boolean check() {
        for (int i = 0; i < quizData.size(); i++) {
            if (current == i) {
                int correctAnswerIndex = quizData.get(i).getCorrectAnswerIndex();
                return (radioButton[correctAnswerIndex].isSelected());
            }
        }
        return false;
    }

    public static void main(String s[]) {new QuizApplication("Simple Quiz Application");}
}
