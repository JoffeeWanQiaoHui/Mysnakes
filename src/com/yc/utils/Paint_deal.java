package com.yc.utils; 
  
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon; 
//没有图片需要处理,请不要运行,运行请改为你要处理图片的路径
 //去掉图片的白,设为透明 
public class Paint_deal { 
  public static void main(String[] args) { 
    transferAlpha(); 
  } 
  public static byte[] transferAlpha() { 
  
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 
    //为了避免本游戏贪吃蛇的图片再次被修改,路径设置另外一个
    File file = new File("D:/垃圾文件/Lajicheshi/src/com/yc/img/body_1.png"); 
    InputStream is; 
    try { 
      is = new FileInputStream(file); 
      // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream(); 
      BufferedImage bi = ImageIO.read(is); 
      Image image = (Image) bi; 
      ImageIcon imageIcon = new ImageIcon(image); 
      BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), 
          BufferedImage.TYPE_4BYTE_ABGR); 
      Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics(); 
      g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver()); 
      int alpha = 0; 
      for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) { 
        for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) { 
          int rgb = bufferedImage.getRGB(j2, j1); 
  
          int R = (rgb & 0xff0000) >> 16; 
          int G = (rgb & 0xff00) >> 8; 
          int B = (rgb & 0xff); 
          if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) { 
            rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff); 
          } 
  
          bufferedImage.setRGB(j2, j1, rgb); 
  
        } 
      } 
  
      g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver()); 
      ImageIO.write(bufferedImage, "png", new File("D:/垃圾文件/Lajicheshi/src/com/yc/img/body_2.png"));// 直接输出文件 
    } catch (Exception e) { 
      e.printStackTrace(); 
    } finally { 
  
    } 
    return byteArrayOutputStream.toByteArray(); 
  } 
} 