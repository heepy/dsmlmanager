package com.horan.dsmlmanager.utils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


public class MyFileUtils {
    public static String path = "D:/dsml/";
    public static int code = 100;

    public static Path addFolder(String foldname) {
        String realPath = path + File.separator + foldname;//兼容Linux和windows
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            //不存在
            filePath.mkdirs();
            Path getPath = Paths.get(realPath);
            return getPath;
        }

        return null;

    }

    /**
     * 存储文件并返回文件名
     *
     * @param file
     * @param module
     * @return
     */
    public static String writeUploadFile(MultipartFile file, String module) {
        if (code > 200) {
            code = 100;
        }
        String filename = file.getOriginalFilename();
        String realpath = path + File.separator + module + File.separator+"src"+File.separator;
        File fileDir = new File(realpath);
        if (!fileDir.exists())
            fileDir.mkdirs();

        String extname = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if (!allowImgFormat.contains(extname.toLowerCase())) {
            return "NOT_IMAGE";
        }

        filename = module + "_" + new Date().getTime() + code + "." + extname;
        code++;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            ByteArrayOutputStream baos = cloneInputStream(input);
            InputStream streamThumb = new ByteArrayInputStream(baos.toByteArray());//转化成两个流
            InputStream streamoriginal = new ByteArrayInputStream(baos.toByteArray());
            ImageUtils.setThumbnailatorImg(streamThumb,realpath,filename);  //同时生成缩略图
            fos = new FileOutputStream(realpath +File.separator+ filename);
            IOUtils.copy(streamoriginal, fos);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return filename;
    }
    private static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void  deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileUtils.forceDelete(file);
    }
  public static String getThumbnailPath(String realPath){
      int index=realPath.lastIndexOf(File.separator);

      String content=realPath.substring(index);

      String result= realPath.replace(content,File.separator+"thumbnail"+content);
     return result;
  }
    public static void main(String[] args) {

    }

}
