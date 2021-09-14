package FirstLab.billiard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {

    private BallCanvas canvas;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 350;
    public static int idCounter = 0;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStartRed = new JButton("Start RED");
        JButton buttonStartBlue = new JButton("Start BLUE");
        JButton buttonStop = new JButton("Stop");
        JButton buttonCompare = new JButton("Compare");
        JButton buttonJoin = new JButton("Join");

        JLabel resultLabel = new JLabel("RESULT: " + Result.ballCounter);

        buttonStartRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, ++idCounter, resultLabel, Color.RED, false);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.setName(thread.getName() + " (RED)");
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });
        buttonStartBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, ++idCounter, resultLabel, Color.BLUE, false);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.setName(thread.getName() + " (BLUE)");
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonCompare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 20; i++) {
                    Ball b = new Ball(canvas, ++idCounter, resultLabel, Color.BLUE, true);
                    canvas.add(b);

                    BallThread thread = new BallThread(b);
                    thread.setPriority(Thread.MIN_PRIORITY);
                    thread.setName(thread.getName() + " (BLUE)");
                    thread.start();
                    System.out.println("Thread name = " + thread.getName());
                }
                Ball b = new Ball(canvas, ++idCounter, resultLabel, Color.RED, true);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.setName(thread.getName() + " (RED)");
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });
        buttonJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b1 = new Ball(canvas, ++idCounter, resultLabel, Color.RED, false);
                canvas.add(b1);

                BallThread thread1 = new BallThread(b1);
                thread1.setName(thread1.getName() + " (RED)");
                thread1.start();
                System.out.println("Thread name = " + thread1.getName());

                Ball b2 = new Ball(canvas, ++idCounter, resultLabel, Color.BLACK, false);
                canvas.add(b2);

                BallThreadWithJoin thread2 = new BallThreadWithJoin(b2, thread1);
                thread2.setName(thread2.getName() + " (BLACK)");
                thread2.start();
                System.out.println("Thread name = " + thread2.getName());
            }
        });

        buttonPanel.add(buttonJoin);
        buttonPanel.add(buttonCompare);
        buttonPanel.add(buttonStartRed);
        buttonPanel.add(buttonStartBlue);
        buttonPanel.add(buttonStop);
        buttonPanel.add(resultLabel);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}

