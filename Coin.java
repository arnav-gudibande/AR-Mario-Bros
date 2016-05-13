import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Coin extends JComponent implements ActionListener
{
    ImageIcon cx;
    double x;
    double y;
    
    public Coin(int x, int y){
        cx = new ImageIcon(this.getClass()
                .getResource("coin.gif"));
        this.x = x;
        this.y = y;
    }
    
    public void actionPerformed(ActionEvent e){
        try{
            move();
        } catch(IOException bb){
            bb.printStackTrace();
        }
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        int realx = (int) x;
        int realy = (int) y;
        cx.paintIcon(this, g2, realx, realy);
    }
    
    public void move() throws IOException {
        repaint();
        if(Engine.getColorPixel(x+10,y+25,Engine.edges)!=255 && (y<450)) {
            y+=1.75;
        }
    }
    
    public double getXX(){
        return (double) x;
    }
    
    public double getYY(){
        return (double) y;
    }
}

