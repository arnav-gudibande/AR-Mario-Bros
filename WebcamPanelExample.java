import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;
import java.awt.*;
import javax.swing.*;

public class WebcamPanelExample {
    public static void main(String[] args) throws IOException {
        BufferedImage in = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
        
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
        detector.setLowThreshold(4f);
        detector.setHighThreshold(4.25f);

        detector.setSourceImage(in);
        detector.process();
        BufferedImage edges = detector.getEdgesImage();
        
        JFrame x = new JFrame();
        x.add(new JLabel(new ImageIcon(edges)));
        x.setSize(500,500);
        x.setVisible(true);
        
        
    }
}