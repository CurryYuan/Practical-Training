package ImageProcessing;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {

	@Override
	public Image myRead(String paramString) throws IOException {
		File file = new File(paramString);  
        FileInputStream fis = new FileInputStream(file);  
        byte[] bytes = new byte[54];  
        fis.read(bytes);  
        int width = byteChangeToInt(bytes[21], bytes[20], bytes[19], bytes[18]);  
        int height = byteChangeToInt(bytes[25], bytes[24], bytes[23], bytes[22]);  


        int startX = 0;// ��ʼ�ĺ�����    
        int startY = 0;// ��ʼ��������    
        int offset = 0;// ƫ����    
        int scansize = width;// ɨ����  
        int dd = width - startX;// �������Ŀ�ȼ��    
        int hh = height - startY;// �������ĸ߶ȼ��  
        int[] pix=new int[offset + hh * scansize + dd];
        int skipnum = 0;
        if(width*3%4!=0)
        {
            skipnum=4-width*3%4;
        }
        for (int i = height-1; i >=0; i--) {//����ÿһ��    
            for (int j = 0; j < width - startX; j++) {//����ÿһ��
            	int blue=fis.read();
            	int green=fis.read();
            	int red=fis.read();
            	Color color=new Color(red, green, blue);
        		pix[i*dd + j]=color.getRGB();
        	}
        	if(skipnum!=0) {
        		fis.skip(skipnum);
        	}
        }
        
        fis.close();
        Image img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pix, 0, width));

		return img;
	}

	@Override
	public Image myWrite(Image paramImage, String paramString) throws IOException {
		File out = new File(paramString);    
        if (!out.exists()) {    
            out.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(out);
        
        BufferedImage imgOut = ImplementImageProcessor.toBufferedImage(paramImage);    
        ImageIO.write(imgOut, "bmp", output);
        return paramImage;
	}
	
	private int byteChangeToInt(byte byte1,byte byte2,byte byte3,byte byte4) {  
        int value1 = ((int)byte1&0xff)<<24;  
        int value2 = ((int)byte2&0xff)<<16;  
        int value3 = ((int)byte3&0xff)<<8;  
        int value4 = (int)byte4&0xff;  
        return value1|value2|value3|value4; 
	}
}
