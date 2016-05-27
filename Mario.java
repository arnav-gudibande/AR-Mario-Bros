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
    public double x,y,d;
    Ellipse2D.Double ball;
    ImageIcon mario;
    boolean onLine = false;
    double gravity = 1;
    double dx,dy = 0;

    public Mario(int x, int y, int o, int d) throws IOException
    {
        switch(o){
            case 1:
            this.x = x;
            this.y = y;

            break;
        }
        this.d = d;
        mario = new ImageIcon(this.getClass()
            .getResource("mario.gif"));
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        ball = new Ellipse2D.Double(x, y, d, d);
        g2.setColor(Color.RED);
        int realx = (int) x;
        int realy = (int) y;
        mario.paintIcon(this, g2, realx, realy);
    }

    public void actionPerformed(ActionEvent e)
    {
        try{
            move();
        } catch(IOException bb){
            bb.printStackTrace();
        }
    }

    
    
    public void move() throws IOException {
        //checks to see if mario hits boundries to keep him inside the frame
        if(y<=.5) dy = -.00000000001;
        if(y>=390) dy = 20;
        
        
        if(!onLine){
            dy-=gravity;//subtracts the value of gravity from dy (velocity) if mario is not on a line when move method is called
        } 
        
        
        y-=dy;//subtracts dy (velocity) to y each time move method is called
        x+=dx;//subtracts dy (velocity) to y each time move method is called
        
        
        repaint();
        
        

        if(Engine.getColorPixel(x+17,y+54,Engine.edges)!=255){//checks if one pixel below the bottom center pixel of Mario is not white
            onLine = false;
        }
        
        
        for(int i = 0; i<=15; i++)//loop needed for mario to move up a slope
        {
            /*
             * the x coordinate is the right edge of mario, and the y coordinate starts at his foot
             * by adding i to the y coordinate each time it loops through, it is checking each pixel up from the starting pixel up to 15 times
             * adds i value to y value to adjust y coordinate
             */
            if(Engine.getColorPixel(x+(36/2)+1, y+54+i, Engine.edges)==255){//checks if pixel is white
                y+=i;
            }
        }

        for(int i = 0; i>=-15; i--)//loop needed for mario to move down a slope
        {
            //same as previous for loop, but instead checks for pixels below mario so he can move down a slope
            if(Engine.getColorPixel(x+(36/2)+1, y+54+i, Engine.edges)==255){
                y+=i;
            }
        }

        for(int i=0; i<37; i++)//loops through Mario's entire bottom row of pixels
        {
            if(Engine.getColorPixel(x+i, y+54, Engine.edges)==255) {//checks if current pixel is white
                onLine=true;//assgins true value to onLine
                dy=0;//resets vertical velocity to zero
            }
        }

        for(int i=0; i<GameLogic.aC.size(); i++)//loops through array of coins
        {
            if(((x>=(GameLogic.aC.get(i).getXX())-15) && (x<=(GameLogic.aC.get(i).getXX())-5)) && ((y>=(GameLogic.aC.get(i).getYY())-35) && (y<=(GameLogic.aC.get(i).getYY())-25)))//checks if position of mario is the same as the position of any coin in the array aC 
            {
                playNoise();//invokes playNoise method of Mario class
                GameLogic.score++;//increments score variable by 1
                GameLogic.aC.get(i).setVisible(false);
                GameLogic.aC.remove(i);//removes that coin object from array aC using remove() method of arraylist class
            }
        }
        
        for(int i=0; i<GameLogic.gC.size(); i++)
        {
            if(((x>=(GameLogic.gC.get(i).getXX())-15) && (x<=(GameLogic.gC.get(i).getXX())-5)) && ((y>=(GameLogic.gC.get(i).getYY())-35) && (y<=(GameLogic.gC.get(i).getYY())-25))) {
                playGNoise();
                Engine.t.stop();//stops the timer for Mario
                Engine.g.stop();//stops the timer for the goombas
            }
        }

    }

    public void moveUp()
    {
        dy=15.5;
    }

    public void moveDown()
    {
        //y += 1;
        dy=1;
    }

    public void moveLeft()
    {
        //x-=5;
        if(x>=20) dx=-1.5;
    }

    public void moveRight()
    {
        //x+=5;
        if(x<=630) dx=1.5;

    }

    public void stop()
    {
        dx=0;
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
    
    public void playGNoise(){
        try{
            AudioInputStream audioInputStream =
                AudioSystem.getAudioInputStream(
                    this.getClass().getResource("die.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {
        }
    }
}