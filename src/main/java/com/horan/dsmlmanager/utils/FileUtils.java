package com.horan.dsmlmanager.utils;




import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


public class FileUtils {
    public static String path="D:\\dsml";
    public static int code=100;
    public static Path addFolder(String foldname){
       String realPath=path+File.separator+foldname;//兼容Linux和windows

        File filePath = new File(realPath);

        if(!filePath.exists()){
            //不存在
            filePath.mkdirs();
            Path getPath=Paths.get(realPath);
           return getPath;
        }

      return  null;

    }

    /**
     * 存储文件并返回文件名
     * @param file
     * @param module
     * @return
     */
    public static String writeUploadFile(MultipartFile file, String module) {
        if(code>200){
            code=100;
        }
        String filename = file.getOriginalFilename();
        String realpath = path+File.separator+module +File.separator;
        File fileDir = new File(realpath);
        if (!fileDir.exists())
            fileDir.mkdirs();

        String extname = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if (!allowImgFormat.contains(extname.toLowerCase())) {
            return "NOT_IMAGE";
        }

        filename = module+"_"+ new Date().getTime()+code+"." + extname;
        code++;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(realpath + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return  filename;
    }

    public static void main(String[] args){


    }

}
