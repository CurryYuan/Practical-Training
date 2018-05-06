package ImageProcessing;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class ImageProcessorTest {
	ImplementImageIO imageio=new ImplementImageIO();
	ImplementImageProcessor imageProcessor=new ImplementImageProcessor();
	Image a,b,temp1,temp2;

	@Before
	public void init() {
		try {
			a=imageio.myRead(ClassLoader.getSystemResource("bmptest/1.bmp").toString().substring(5));
			b=imageio.myRead(ClassLoader.getSystemResource("bmptest/2.bmp").toString().substring(5));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testShowChanelB() {
		Image cImage=imageProcessor.showChanelB(a);
		Image dImage=imageProcessor.showChanelB(b);

		try {
			temp1 = imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/1_blue_goal.bmp").toString().substring(5));
			temp2=imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/2_blue_goal.bmp").toString().substring(5));
			isEqual(cImage, temp1);
			isEqual(dImage, temp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

	@Test
	public void testShowChanelG() {
		Image cImage=imageProcessor.showChanelG(a);
		Image dImage=imageProcessor.showChanelG(b);

		try {
			temp1 = imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/1_green_goal.bmp").toString().substring(5));
			temp2=imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/2_green_goal.bmp").toString().substring(5));
			isEqual(cImage, temp1);
			isEqual(dImage, temp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testShowChanelR() {
		Image cImage=imageProcessor.showChanelR(a);
		Image dImage=imageProcessor.showChanelR(b);

		try {
			temp1 = imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/1_red_goal.bmp").toString().substring(5));
			temp2=imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/2_red_goal.bmp").toString().substring(5));
			isEqual(cImage, temp1);
			isEqual(dImage, temp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testShowGray() {
		Image cImage=imageProcessor.showGray(a);
		Image dImage=imageProcessor.showGray(b);

		try {
			temp1 = imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/1_gray_goal.bmp").toString().substring(5));
			temp2=imageio.myRead(ClassLoader.getSystemResource("bmptest/goal/2_gray_goal.bmp").toString().substring(5));
			isEqual(cImage, temp1);
			isEqual(dImage, temp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void isEqual(Image tmp1,Image tmp2) {
		BufferedImage temp1=ImplementImageProcessor.toBufferedImage(tmp1);
		BufferedImage temp2=ImplementImageProcessor.toBufferedImage(tmp2);

		assertEquals(temp1.getWidth(), temp2.getWidth());
		assertEquals(temp1.getHeight(), temp2.getHeight());
		
		assertArrayEquals(getPixels(temp1), getPixels(temp2));
	}
	
	public int[] getPixels(BufferedImage img) {
		int width = img.getWidth();// »ñÈ¡ÍŒÆ¬¿í¶È    
        int height = img.getHeight();// »ñÈ¡ÍŒÆ¬žß¶È    
        int startX = 0;// ¿ªÊŒµÄºá×ø±ê    
        int startY = 0;// ¿ªÊŒµÄ×Ý×ø±ê    
        int offset = 0;// Æ«ÒÆÁ¿    
        int scansize = width;// ÉšÃèŒäŸà    
        int dd = width - startX;// ±»±éÀúµÄ¿í¶ÈŒäŸà    
        int hh = height - startY;// ±»±éÀúµÄžß¶ÈŒäŸà    
        
        int[] rgbArray = new int[offset + hh * scansize + dd];
        img.getRGB(startX, startY, width, height, rgbArray, offset, scansize); // °ÑÏñËØŽæµœ¹Ì¶šµÄÊý×éÀïÃæÈ¥    
        return rgbArray;
	}
}