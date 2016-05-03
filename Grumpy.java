import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Grumpy extends JComponent
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
}

