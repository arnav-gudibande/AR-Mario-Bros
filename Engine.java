import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;


public class Engine {

	public static void main(String[] args) throws IOException {

		Webcam webcam = Webcam.getDefault();
		webcam.open();

		System.out.println("Taking picture");
		BufferedImage image = webcam.getImage();

		// save image to PNG file
		ImageIO.write(image, "PNG", new File("test.png"));
		
		System.out.println("Picture taken to test.png");
	}
}