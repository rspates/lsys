package drawing

import static org.junit.jupiter.api.Assertions.*

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.image.BufferedImage

import javax.imageio.ImageIO

class Test {

	@org.junit.jupiter.api.Test
	void test() {
	try {
	  int width = 800, height = 800;

	  // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
	  // into integer pixels
	  BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	  Graphics2D ig2 = bi.createGraphics();

	  for (int i=0;i<10;i++) {
		  sleep(300)
		  
	  ig2.setPaint(Color.white);
	  ig2.fillRect(0,
		  0,
		  width,
		  height)

	  ig2.setPaint(Color.black);
//	  ig2.drawRect(0,
//            0,
//            width-1,
//            height-1)
//	  
	  ig2.setStroke(new BasicStroke(10))
	  

		  if (i%2) ig2.drawLine(100,100,500,500)
			  else ig2.drawLine(500,100,100,500)
	  

//      ImageIO.write(bi, "PNG", new File("c:\\yourImageName.PNG"));
	  ImageIO.write(bi, "JPEG", new File("C:\\temp\\images\\line.JPG"));
//      ImageIO.write(bi, "gif", new File("c:\\yourImageName.GIF"));
//      ImageIO.write(bi, "BMP", new File("c:\\yourImageName.BMP"));
	  
	  }
	} catch (IOException ie) {
	  ie.printStackTrace();
	}

	}

	@org.junit.jupiter.api.Test
	void test0() {
	try {
	  int width = 200, height = 200;

	  // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
	  // into integer pixels
	  BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	  Graphics2D ig2 = bi.createGraphics();


	  Font font = new Font("TimesRoman", Font.BOLD, 20);
	  ig2.setFont(font);
	  String message = "www.java2s.com!";
	  FontMetrics fontMetrics = ig2.getFontMetrics();
	  int stringWidth = fontMetrics.stringWidth(message);
	  int stringHeight = fontMetrics.getAscent();
	  ig2.setPaint(Color.black);
	  ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

//      ImageIO.write(bi, "PNG", new File("c:\\yourImageName.PNG"));
	  ImageIO.write(bi, "JPEG", new File("yourImageName.JPG"));
//      ImageIO.write(bi, "gif", new File("c:\\yourImageName.GIF"));
//      ImageIO.write(bi, "BMP", new File("c:\\yourImageName.BMP"));
	  
	} catch (IOException ie) {
	  ie.printStackTrace();
	}

	}

}
