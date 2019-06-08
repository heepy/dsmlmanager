package com.horan.dsmlmanager.utils;


import com.horan.dsmlmanager.config.BaseConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

import java.util.Date;


public class MyFileUtils {


    public static String path = "D:/dsml";
    public static int code = 100;


    /**
     * 上传图片并同时保存缩略图
     *
     * @param file
     * @param module
     * @return
     */
    public static String writeUploadFile(MultipartFile file, String module) { //上传图片
        if (code > 200) {
            code = 100;
        }
        String filename = file.getOriginalFilename();
        String realpath = path + File.separator + module + File.separator + "src" + File.separator;
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
            ImageUtils.setThumbnailatorImg(streamThumb, realpath, filename);  //同时生成缩略图
            fos = new FileOutputStream(realpath + File.separator + filename);
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

    /**
     * 转化文件流用
     * @param input
     * @return
     */
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

    /**
     * 删除文件
     * @param fileName
     * @throws IOException
     */
    public static void deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileUtils.forceDelete(file);
    }

    /**
     * 获取文件缩略图路径
     * @param realPath
     * @return
     */
    public static String getThumbnailPath(String realPath) {
        String result = realPath.replace("src", "thumbnail");
        return result;
    }

    /**
     * 创建用户文件夹
     * @param folder
     * @return
     */
    public static String mkUserdir(String folder) {
        String realPath = path + File.separator + folder;
        File fileDir = new File(realPath);
        if (!fileDir.exists())
            fileDir.mkdirs();
        return folder;
    }

    /**
     * @dec 创建项目问价夹
     * @param proSrc
     * @return
     */
    public static String mkProdir(String proSrc) {
        String realPath = path + proSrc;
        File fileDir = new File(realPath);
        if (!fileDir.exists())
            fileDir.mkdirs();
        return realPath;
    }

    /**
     * 创建项目文件夹并保存模型文件
     * @param src
     * @param file
     * @return
     * @throws IOException
     */
    public static String saveModelFile(String src, MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        FileOutputStream fos = null;
        String filename = file.getOriginalFilename();
        String srcPro=mkProdir(src);
        String realPath =  srcPro+ "model";
        File fileDir = new File(realPath);
        if (!fileDir.exists())
            fileDir.mkdirs();
        fos = new FileOutputStream(realPath + File.separator + filename);
        IOUtils.copy(inputStream, fos);
        return src;
    }
    public static void mkDataSetDir(String dataSetSrc){
        String realPath = path + dataSetSrc;
        File fileDir = new File(realPath);
        if (!fileDir.exists())
            fileDir.mkdirs();
        File fileDirThu = new File(realPath+"thumbnail"+File.separator);//缩略图路径
        if (!fileDirThu.exists())
            fileDirThu.mkdirs();
        File fileDirSrc = new File(realPath+"src"+File.separator);//图片路径
        if (!fileDirSrc.exists())
            fileDirSrc.mkdirs();
        File fileDirMark = new File(realPath+"mark"+File.separator);//标记文件路径
        if (!fileDirMark.exists())
            fileDirMark.mkdirs();



    }

    public static void main(String[] args) {

    }

}
