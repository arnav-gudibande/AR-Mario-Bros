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
public class Mario extends JComponent implements ActionListener
{
    // instance variables - replace the example below with your own
    public double dx=0, dy=0;
    public double x,y,d;
    Ellipse2D.Double ball;
    ImageIcon mario;

    public Mario(int x, int y, int dx, int dy, int o, int d) throws IOException//the explicit parameters for the BAll constructor
    {
        switch(o){//switch stamenment
            case 1:
                this.x = x;
                this.y = y;//sets all the instance field variables to whatever was specified
                this.dx = dx;
                this.dy = dy;
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
        repaint();//repaints the frame and also adds to the speed that the ball
        x+=dx;//speed is added to the dx
        y+=dy;//speed is added to dy
        if(y<=0) moveDown();//if any of the bounds are breached, then make the ball move accordingly
        if(y>=380)moveUp();
        if(x<=0) moveRight();
        if(x>=590) moveLeft();
        if(Engine.getColorPixel(x+17,y+54,Engine.edges)==255){
            //dx=0;
            //dy=0;
            moveUp();
        }
        
        if(Engine.b1.getX()==x && Engine.b1.getY()==y){
            moveUp();
        }
    }

    public void moveUp()
    {
        dy += -0.1;//make the dx go down to move up in the frame
    }

    public void moveDown()
    {
        dy += 0.1;//to move up, vice versa
    }

    public void moveLeft()
    {
        dx += -0.1;//to move left, dx is subtracted from
    }

    public void moveRight()
    {
        dx += 0.1;//to move right, dx is added to 
    }
    
    
}