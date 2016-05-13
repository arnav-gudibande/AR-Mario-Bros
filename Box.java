import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Box extends JComponent
{
    ImageIcon bx;
    int x;
    int y;
    
    public Box(int x, int y){
        bx = new ImageIcon(this.getClass()
                .getResource("box.gif"));
        this.x = x;
        this.y = y;
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
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
}

