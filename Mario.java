import javax.swing.JFrame;
import java.io.IOException;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.Random;//imports all neccessary components
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Mario extends JComponent implements ActionListener
{
    // instance variables - replace the example below with your own
    public double x,y,d;
    Ellipse2D.Double ball;
    ImageIcon mario;
    boolean onLine;

    public Mario(int x, int y, int o, int d) throws IOException//the explicit parameters for the BAll constructor
    {
        switch(o){//switch stamenment
            case 1:
            this.x = x;
            this.y = y;//sets all the instance field variables to whatever was specified
            break;
        }
        this.d = d;//sets the dimensions of the ball
        mario = new ImageIcon(this.getClass()
            .getResource("mario.gif"));
    }

    public void paintComponent(Graphics g)//necessary overriden method, the result of extending JFrame
    {
        Graphics2D g2 = (Graphics2D) g;//creates a new Graphics2D object, and casts it b/c u are going from higher to lower dependency
        ball = new Ellipse2D.Double(x, y, d, d);//creates a new ball, uses the instance field variables specified by the user
        g2.setColor(Color.RED);//sets the ball to a random color, RAINBOW 
        int realx = (int) x;
        int realy = (int) y;
        mario.paintIcon(this, g2, realx, realy);
    }

    public void actionPerformed(ActionEvent e)
    {
        try{
            move();//when the timer calls itself, move the ball
        } catch(IOException bb){
            bb.printStackTrace();
        }
    }

    public void move() throws IOException {
        repaint();

        if(Engine.getColorPixel(x+17,y+54,Engine.edges)!=255){
            moveDown();
            onLine = false;
        }

        for(int i = 0; i<=15; i++)
        {
            if(Engine.getColorPixel(x+(36/2)+1, y+54+i, Engine.edges)==255){
                y+=i;
            }
        }

        for(int i = 0; i>=-15; i--)
        {
            if(Engine.getColorPixel(x+(36/2)+1, y+54+i, Engine.edges)==255){
                y+=i;
            }
        }

        for(int i=0; i<37; i++)
        {
            if(Engine.getColorPixel(x+i, y+54, Engine.edges)==255) onLine=true;
        }

        for(int i=0; i<GameLogic.aC.size(); i++)
        {
            if(((x>=(GameLogic.aC.get(i).getXX())-15) && (x<=(GameLogic.aC.get(i).getXX())-5)) && ((y>=(GameLogic.aC.get(i).getYY())-35) && (y<=(GameLogic.aC.get(i).getYY())-25))) {
                GameLogic.aC.get(i).setVisible(false);
                GameLogic.aC.remove(i);
                playNoise();
            }
        }
    }

    public void moveUp()
    {
        if(true) y += -35;//make the dx go down to move up in the frame
    }

    public void moveDown()
    {
        y += 1;//to move up, vice versa
    }

    public void moveLeft()
    {
        //dx += -0.1;//to move left, dx is subtracted from
        x-=5;
    }

    public void moveRight()
    {
        //dx += 0.1;//to move right, dx is added to 
        x+=5;
    }

    public void playNoise(){
        try{
            AudioInputStream audioInputStream =
                AudioSystem.getAudioInputStream(
                    this.getClass().getResource("cnoise.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {
        }
    }
}