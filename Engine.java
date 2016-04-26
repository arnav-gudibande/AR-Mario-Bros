import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;
import java.awt.geom.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;


public class Engine {
    
    static BufferedImage edges = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
    static JPanel temp = new JPanel();
    static JFrame x;
    static Coin c1;
    static Box b1;
    public static void main(String[] args) throws IOException {
        BufferedImage in = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();

        BufferedImage image = webcam.getImage();

        ImageIO.write(image, "JPG", new File("cx.jpg"));

        webcam.close();

        try {
            File img = new File("cx.jpg");
            in = ImageIO.read(img);
        } catch (IOException e) { 
            e.printStackTrace(); 
        }

        CannyEdgeDetector detector = new CannyEdgeDetector();
        detector.setLowThreshold(7.5f);
        detector.setHighThreshold(7.75f);

        detector.setSourceImage(in);
        detector.process();
        edges = detector.getEdgesImage();

        x = new JFrame("A.R. Mario Bros");
        x.setSize(640,480);
        x.setResizable(false);

        Mario s = new Mario(0,0,1,10);
        c1 = new Coin(100,100);
        b1 = new Box(100,130);

        Timer t = new Timer(1, s);
        class bListener implements KeyListener {//new blistener class - implements the interface keylistener, therfore it needs to override three methods
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {//using the getkeycode method on object e
                    case KeyEvent.VK_UP: s.moveUp();//if up arrow key, then it moves up
                    break;
                    //case KeyEvent.VK_DOWN: s.moveDown();//if down arrow key, then it moves down
                    //break;
                    case KeyEvent.VK_LEFT: s.moveLeft();//if left arrow key, .....
                    break;
                    case KeyEvent.VK_RIGHT: s.moveRight();//,..
                    break;
                    default: break;
                }
            }

            public void keyReleased(KeyEvent e){}//overriding two methods, needs to be done if you implement an interface
            public void keyTyped(KeyEvent e){}
        }

        x.addKeyListener(new bListener());
        
        //
        x.add(s);
        x.setVisible(true);
        t.start();
        
        x.add(c1);
        x.setVisible(true);
        
        x.add(b1);
        x.setVisible(true);
        
        //
        
        
        x.add(new JLabel(new ImageIcon(edges)));
        x.setVisible(true);
        
        //
        
        
        
        x.setDefaultCloseOperation(x.EXIT_ON_CLOSE);

    }

    public static int getColorPixel(double x, double y, BufferedImage image) throws IOException {
        int xx = (int) x;
        int yy = (int) y;
        int clr =  image.getRGB(xx,yy); 
        int  blue  =  clr & 0x000000ff;
        return blue;
    }
}