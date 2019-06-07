package com.horan.dsmlmanager.utils;

import com.horan.dsmlmanager.entity.Size;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
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
    public static void setThumbnailatorImg(InputStream inputStream,String parentPath,String fileName) throws IOException {
        File realpath=new File(parentPath+"thumbnail");
        if(!realpath.exists()){
            realpath.mkdirs();
        }
        Thumbnails.of(inputStream)
                .size(140, 180)
                .toFile(parentPath+File.separator+"thumbnail"+File.separator+fileName);
    }

    public static void main(String[] args) throws IOException {

    }
}
