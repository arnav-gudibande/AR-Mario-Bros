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
    static BufferedImage edges = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);//the buffered image that the rendered image will go on top of 
    static JPanel temp = new JPanel();//the temporary jpanel that is put ontop of the frame
    static JFrame x;//the main jframe that contains all the elements
    static Coin c1;//coin
    static JLabel counter = new JLabel("Score: 0");//initial score
    static JPanel score = new JPanel();
    static Timer t;//timer for the grumpy's and the coins
    static Timer g;
    /*
     * The Engine class initializes the Webcam, spawns characters for the game and runs the algorithm to detect the edges on an image
     * Depending on the speed of the CPU's processor, the speed of the game may vary
     * Also, make sure to download and install the jar file so that the sarxos library can access your webcam
    */
    
    public static void main(String[] args) throws Exception {
        System.out.print('\u000C');//clears screen
        System.out.println("Initializing Webcam..."); 
        BufferedImage in = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);//initializes new BufferedImage
        Webcam webcam = Webcam.getDefault();//gets the default webcam and stores to default webcam object
        webcam.setViewSize(new Dimension(640, 480));//sets the size of the picture to 640x480
        webcam.open();//open the webcam and takes a picture
        System.out.println("Taking picture..."); 
        BufferedImage image = webcam.getImage();
        ImageIO.write(image, "JPG", new File("cx.jpg"));//saves it as a JPG image to a file named cx.jpg (this updates every time you play the game)
        webcam.close();//closes the webcam for convention

        try {//tries to open the file and sets it to the BufferedImage for rendering
            File img = new File("cx.jpg");
            in = ImageIO.read(img);
        } catch (IOException e) { 
            e.printStackTrace(); //returns an exception if the file is not found
        }

        System.out.println("Running Edge Detection Algorithm");//algorithm that detects the edges on an image
        CannyEdgeDetector detector = new CannyEdgeDetector();//canny edge detector instance creation
        detector.setLowThreshold(7.5f);//sets the thresholds (how detailed 
        detector.setHighThreshold(7.75f);
        detector.setSourceImage(in);
        detector.process();
        edges = detector.getEdgesImage();

        x = new JFrame("A.R. Mario Bros");
        x.setSize(640,480);
        x.setResizable(false);
        x.setLayout(new BorderLayout());
        x.add(counter, BorderLayout.NORTH);
        x.setVisible(true);
        
        
        GameLogic gL = new GameLogic();
        t = new Timer(1, gL.s);
        g = new Timer(1, gL.g1);

        class bListener implements KeyListener {//new blistener class - implements the interface keylistener, therfore it needs to override three methods
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {//using the getkeycode method on object e
                    case KeyEvent.VK_SPACE: gL.s.moveUp();//if up arrow key, then it moves up
                    break;
                    //case KeyEvent.VK_DOWN: s.moveDown();//if down arrow key, then it moves down
                    //break;
                    case KeyEvent.VK_LEFT: gL.s.moveLeft();//if left arrow key, .....
                    break;
                    case KeyEvent.VK_RIGHT: gL.s.moveRight();//,..
                    break;
                    default: break;
                }
            }

            public void keyReleased(KeyEvent e){
                switch(e.getKeyCode()) {//using the getkeycode method on object e
                    case KeyEvent.VK_LEFT: gL.s.stop();//if left arrow key, .....
                    break;
                    case KeyEvent.VK_RIGHT: gL.s.stop();//,..
                    break;
                    default: break;
                }
                
            }//overriding two methods, needs to be done if you implement an interface
            public void keyTyped(KeyEvent e){}
        }
        
        x.addKeyListener(new bListener());
        
        x.add(gL.s);
        x.setVisible(true);
        t.start();
        
        for(int i=0; i<gL.aC.size(); i++){
            x.add(gL.aC.get(i));
            x.setVisible(true);
            Timer c = new Timer(1, gL.aC.get(i));
            c.start();
        }
        
        
        for(int i=0; i<gL.gC.size(); i++){
            x.add(gL.gC.get(i));
            x.setVisible(true);
            Timer d = new Timer(1, gL.gC.get(i));
            d.start();
        }
        
        Timer sco = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String xc = Integer.toString(GameLogic.score);
                counter.setText("Score: "+xc);
            }
        });
        
        sco.start();
        
        System.out.println("Game Ready");
        x.add(new JLabel(new ImageIcon(edges)));
        x.setVisible(true);
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