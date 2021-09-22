package FirstLab.billiard;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {
    private Component canvas;

    private static final int BilliardPocketSIZE = 36;

    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int  y= 0;
    private int dx = 2;
    private int dy = 2;

    private int id;
    private JLabel resultLabel;
    private Color ballColor;

    public Ball(Component c, int id, JLabel resultLabel, Color ballColor, boolean sameStart){
        this.ballColor = ballColor;
        this.canvas = c;
        this.id = id;
        this.resultLabel = resultLabel;
        if (sameStart) {
            x = 20;
            y = 20;
        } else {
            if (Math.random() < 0.5) {
                x = new Random().nextInt(this.canvas.getWidth());
                y = 0;
            } else {
                x = 0;
                y = new Random().nextInt(this.canvas.getHeight());
            }
        }
    }

    public void draw (Graphics2D g2){
        g2.setColor(ballColor);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }

    public boolean move(){
        x+=dx;
        y+=dy;

        if (checkIsInBilliardPocket()) {
            ((BallCanvas) this.canvas).remove(this);
            this.canvas.repaint();
            Result.ballCounter++;
            resultLabel.setText("RESULT: " + Result.ballCounter);
            return true;
        }

        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y = 0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
        return false;
    }

    public boolean checkIsInBilliardPocket() {
        if (x <= (BilliardPocketSIZE - XSIZE) && y <= (BilliardPocketSIZE - YSIZE)) {
            return true;
        }
        if (x <= (BilliardPocketSIZE - XSIZE) && y >= (this.canvas.getHeight() - BilliardPocketSIZE)) {
            return true;
        }
        if (x >= (this.canvas.getWidth() - BilliardPocketSIZE) && y >= (this.canvas.getHeight() - BilliardPocketSIZE)) {
            return true;
        }
        if (x >= (this.canvas.getWidth() - BilliardPocketSIZE) && y <= (BilliardPocketSIZE - YSIZE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ball)) return false;
        Ball ball = (Ball) o;
        return id == ball.id;
    }
}

