package com.horan.dsmlmanager.utils;

import com.horan.dsmlmanager.entity.Size;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class ImageUtils {
    public static Size getSize(File picture) throws IOException {
        Size result=new Size();
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
        ColorModel color = sourceImg.getColorModel();
        result.setWidth(sourceImg.getWidth());
        result.setHeight(sourceImg.getHeight());
         result.setDepth(color.getPixelSize());
        return result;
    }
    public static void getThumbnailatorImg(File file) throws IOException {
        File realpath=new File(file.getParent()+File.separator+"thumbnailator");
        if(!realpath.exists()){
            realpath.mkdirs();
        }
        Thumbnails.of(file.getPath())
                .size(140, 148)
                .toFile(file.getParent()+File.separator+"thumbnailator"+File.separator+file.getName());
    }
    public static void main(String[] args) throws IOException {
    File file=new File( "D:/dsml/10007/10007_1559453050147100.jpg");
     Size size= ImageUtils.getSize(file);
     getThumbnailatorImg(file);
     System.out.println(size.getHeight());
        System.out.println(size.getWidth());
    }
}
