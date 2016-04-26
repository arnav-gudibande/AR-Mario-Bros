import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Coin extends JComponent
{
    ImageIcon cx;
    int x;
    int y;
    
    public Coin(int x, int y){
        cx = new ImageIcon(this.getClass()
                .getResource("coin.gif"));
        this.x = x;
        this.y = y;
    }
    
    public void paintComponent(Graphics g)//necessary overriden method, the result of extending JFrame
    {
        Graphics2D g2 = (Graphics2D) g;//creates a new Graphics2D object, and casts it b/c u are going from higher to lower dependency
        int realx = (int) x;
        int realy = (int) y;
        cx.paintIcon(this, g2, realx, realy);
    }
}

