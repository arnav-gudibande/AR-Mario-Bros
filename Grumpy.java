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
    
    public Grumpy(int x, int y){
        bx = new ImageIcon(this.getClass()
                .getResource("grumpy.gif"));
        this.x = x;
        this.y = y;
    }
    
    public void paintComponent(Graphics g)//necessary overriden method, the result of extending JFrame
    {
        Graphics2D g2 = (Graphics2D) g;//creates a new Graphics2D object, and casts it b/c u are going from higher to lower dependency
        int realx = (int) x;
        int realy = (int) y;
        bx.paintIcon(this, g2, realx, realy);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        try{
            drop();
        } catch(IOException bb){
            bb.printStackTrace();
        }
    }
    
    public void drop() throws IOException {
        repaint();
        if(Engine.getColorPixel(x+10,y+25,Engine.edges)!=255 && (y<460)) {
            y+=1.75;
        } 
        
    }
    
    public void patrol() throws IOException {
        repaint();
        for(int i = 0; i<=15; i++)
        {
            if(Engine.getColorPixel(x+(36/2)+1, y+54+i, Engine.edges)==255){
                moveLeft();
                y+=i;
            }
        }

        for(int i = 0; i>=-15; i--)
        {
            if(Engine.getColorPixel(x+(36/2)+1, y+54+i, Engine.edges)==255){
                moveLeft();
                y+=i;
            }
        }
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
}

