import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grumpy extends JComponent implements ActionListener
{
    ImageIcon bx;
    int x;
    int y;
    double rNum = Math.random();
    int dx;
    boolean onLine = false;
    int constx;
    int counttt = 0;
    int init = 0;

    public Grumpy(int x, int y){
        bx = new ImageIcon(this.getClass()
            .getResource("grumpy.gif"));
        this.x = x;
        this.y = y;
        System.out.println(rNum);
        if(rNum < .5) dx = 1;
        else dx = -1;
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        int realx = (int) x;
        int realy = (int) y;
        bx.paintIcon(this, g2, realx, realy);
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
        repaint();
        if(onLine) {
            counttt++;
            if(counttt==1){init = x;}
            patrol();
        }

        if(Engine.getColorPixel(x+17,y+54,Engine.edges)!=255){
            moveDown();
            onLine = false;
        }

        for(int i=0; i<37; i++)
        {
            if(Engine.getColorPixel(x+i, y+54, Engine.edges)==255) onLine=true;
        }
        
    }

    public void patrol() throws IOException {
        x += dx;
        
        if(Math.abs(init-x)>=50){
            dx = -1 * dx;
            init = x;
        }        

        for(int i = 0; i<=15; i++)
        {
            if(Engine.getColorPixel(x+(16/2)+dx, y+24+i, Engine.edges)==255){
                y+=i;
            }
        }

        for(int i = 0; i>=-15; i--)
        {
            if(Engine.getColorPixel(x+(16/2)+dx, y+24+i, Engine.edges)==255){
                y+=i;
            }
        }

    }

    public void moveLeft()
    {
        x-=1;
    }

    public void moveRight()
    {
        x+=1;
    }

    public void moveDown()
    {
        y+=1;
    }

    public double getXX(){
        return (double) x;
    }

    public double getYY(){
        return (double) y;
    }
}

