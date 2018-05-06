package ImageProcessing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor {

	@Override
	public Image showChanelB(Image paramImage) {
		// TODO Auto-generated method stub
		return analyseRGB(toBufferedImage(paramImage), 3);
	}

	@Override
	public Image showChanelG(Image paramImage) {
		// TODO Auto-generated method stub
		return analyseRGB(toBufferedImage(paramImage), 2);
	}

	@Override
	public Image showChanelR(Image paramImage) {
		// TODO Auto-generated method stub
		return analyseRGB(toBufferedImage(paramImage), 1);
	}

	@Override
	public Image showGray(Image paramImage) {
		// TODO Auto-generated method stub
		return analyseRGB(toBufferedImage(paramImage), 4);
	}
	
	 public static Image analyseRGB(BufferedImage img, int type) {      
		 //int imageType = img.getType();// ��ȡͼƬ����    
         int width = img.getWidth();// ��ȡͼƬ���    
         int height = img.getHeight();// ��ȡͼƬ�߶�    
         int startX = 0;// ��ʼ�ĺ�����    
         int startY = 0;// ��ʼ��������    
         int offset = 0;// ƫ����    
         int scansize = width;// ɨ����    
         int dd = width - startX;// �������Ŀ�ȼ��    
         int hh = height - startY;// �������ĸ߶ȼ��    
  
         // rgb�����飬�������أ���һά�����ʾ��λͼ����������    
         int[] rgbArray = new int[offset + hh * scansize + dd];// ƫ����+����ʼ����*ɨ����+����ʼ����    
         //�����Ҷ��о������Ϊʲô��������һ���㷨�أ�Ϊʲô��֪����width*height�͹����ˣ���������Ҳ�㲻������ֻҪĬ�ϼ�ס���������    
         //Ȼ��ȡ���ʱ���������ȥȡ�Ϳ�����    
         // newArray ���洦��������    
         int[] newArray = new int[offset + hh * scansize + dd];// ƫ����+����ʼ����*ɨ����+����ʼ����    
         /**   
          * getRGB public int[] getRGB(int startX, int startY, int w, int h,   
          * int[] rgbArray, int offset, int scansize)��ͼ�����ݵ�ĳһ���ַ���Ĭ�� RGB ��ɫģ��   
          * (TYPE_INT_ARGB) ��Ĭ�� sRGB ��ɫ�ռ��������������顣�����Ĭ��ģ�����ͼ��� ColorModel   
          * ��ƥ�䣬������ɫת������ʹ�ô˷��������ص������У�ÿ����ɫ����ֻ�� 8 λ���ȡ�ͨ��ͼ����ָ�������� (x, y)��ARGB   
          * ���ؿ��԰����·�ʽ���ʣ�   
          *    
          * pixel = rgbArray[offset + (y-startY)*scansize + (x-startX)];   
          * ����������ڱ߽��ڲ������׳� ArrayOutOfBoundsException�����ǣ�����֤������ʽ�ı߽��顣   
          *    
          *    
          * ������   
          *  startX - ��ʼ X ����    
          *  startY - ��ʼ Y ����    
          *  w - ����Ŀ��    
          *  h - ����ĸ߶�   
          * rgbArray - �����Ϊ null�����ڴ�д�� rgb ����    
          * offset - rgbArray �е�ƫ����   
          * scansize - rgbArray ��ɨ���м��    
          * ���أ� RGB �������顣   
          */    
         img.getRGB(startX, startY, width, height, rgbArray, offset, scansize); // �����ش浽�̶�����������ȥ    

         for (int i = 0; i < height - startY; i++) {//����ÿһ��    
             for (int j = 0; j < width - startX; j++) {//����ÿһ��    
                 Color c = new Color(rgbArray[offset+i * scansize + j]);    
                 switch (type) {    
                 case 1://��ɫ�Ҷ�ͼƬ    
                     newArray[i*dd + j] = new Color(c.getRed(), 0, 0).getRGB();    
                     break;    
                 case 2://��ɫ�Ҷ�ͼƬ    
                      newArray[i*dd + j] = new Color(0, c.getGreen(), 0).getRGB();     
                     break;    
                 case 3://��ɫ�Ҷ�ͼƬ    
                     newArray[i * dd + j] = new Color(0, 0, c.getBlue()).getRGB();    
                     break;    
                 case 4://�ڰ�    
                     int hb=(int) (c.getRed()*0.299+ c.getGreen()*0.587 +c.getBlue()*0.114);  

                     newArray[i * dd + j] = new Color(hb, hb, hb).getRGB();    
                     break;   
 
                 default:    
                     break;    
                 }                       
             }    
         }    

     return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height,newArray, offset, scansize));  
	            
	 }
	 
	 public static BufferedImage toBufferedImage(Image image) {  
	        if (image instanceof BufferedImage) {  
	             return (BufferedImage)image;  
	        }                   
	        // ������������ 
	        image = new ImageIcon(image).getImage();                    
	        BufferedImage bimage = null;  
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
	        try {                        
	             int transparency = Transparency.OPAQUE;                        
	             // ����bufferͼ��  
	             GraphicsDevice gs = ge.getDefaultScreenDevice();  
	             GraphicsConfiguration gc = gs.getDefaultConfiguration();  
	             bimage = gc.createCompatibleImage(  
	             image.getWidth(null), image.getHeight(null), transparency);  
	        } catch (HeadlessException e) {  
	              
	        }                   
	        if (bimage == null) {                         
	            int type = BufferedImage.TYPE_INT_RGB;  
	            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);  
	        }                   
	        // ����
	        Graphics g = bimage.createGraphics();                   
	        // ��ֵ  
	        g.drawImage(image, 0, 0, null);  
	        g.dispose();                    
	        return bimage;
	} 
}
