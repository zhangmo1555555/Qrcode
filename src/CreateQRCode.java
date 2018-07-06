

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
public class CreateQRCode {
	public static void main(String[] args) throws IOException {
		int ver =14;		
		int logosize=30;		
		Qrcode qrcdoe = new Qrcode();
		qrcdoe.setQrcodeVersion(ver);	

		int  imageSize = 67+12*(ver-1);
		BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);		
		Graphics2D gs = bufferedImage.createGraphics();
		gs.setBackground(Color.WHITE);	
		gs.setColor(Color.BLACK);	
		gs.clearRect(0, 0, imageSize, imageSize);
		qrcdoe.setQrcodeEncodeMode('B');
		String str = "BEGIN:VCARD\n" + 
		  "PHOTO;VALUE=uri:http://img4.imgtn.bdimg.com/it/u=3630352509,3120025421&fm=27&gp=0.jpg\n" + 
		  "N:张\n"+
		  "FN:茉\n" + 
		  "ORG:河北科技师范学院\n" + 
		  "TITLE:java学生\n" + 
		  "ADR;WORK:秦皇岛河北科技师范学院\n" +  
		  "TEL;CELL,VOICE:18732082661\n" + 
		  "TEL;WORK,VOICE:18732082661\r\n" + 
		  "URL;WORK;:http://www.hevttc.edu.cn\n" + 
		  "EMAIL;INTERNET,HOME:1483705249@qq.com\n" + 
		  "END:VCARD";	
		
		
	

		int startR = 255;
		int startG = 69;
		int startB = 86;
		
		int endR = 86;
		int endG = 69;
		int endB = 255;
		
		boolean[][] calQrcode = qrcdoe.calQrcode(str.getBytes("UTF-8"));
		System.out.println(calQrcode.length);
		int x = 2;
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode.length; j++) {
				if(calQrcode[i][j]){
					int num1 = startR + (endR - startR) * (i+1)/calQrcode.length;
					int num2 = startG + (endG - startG) * (i+1)/calQrcode.length;
					int num3 = startB + (endB - startB) * (i+1)/calQrcode.length;
					
					Color color = new Color(num1, num2, num3);
					
					gs.setColor(color);
					gs.fillRect(i*3+x, j*3+x, 3, 3);
				}
			}
		}
		Image logo=ImageIO.read(new File("d:/12.jpg"));
		int logox=(imageSize-logosize)/2;
		gs.drawImage(logo, logox,logox, 20, 20, null);

		gs.dispose();
		bufferedImage.flush();
		ImageIO.write(bufferedImage, "png", new File("d:/qrcode_1.png"));
	}
}

