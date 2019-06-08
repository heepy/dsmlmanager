package com.horan.dsmlmanager.service.impl;

import com.horan.dsmlmanager.config.BaseConfig;
import com.horan.dsmlmanager.dao.DataSetMapper;
import com.horan.dsmlmanager.dao.ImageMapper;
import com.horan.dsmlmanager.entity.*;
import com.horan.dsmlmanager.service.ImageService;
import com.horan.dsmlmanager.utils.ImageUtils;
import com.horan.dsmlmanager.utils.JavaBeanToXml;
import com.horan.dsmlmanager.utils.MyFileUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageSetDao;
    @Autowired
    private DataSetMapper dataSetDao;

    @Autowired
    private BaseConfig baseConfig;

    @Override
    public List<Image> getPageImageSet(int currentPage, int pageSize, int dataSetId) {
        Map<String, Object> data = new HashMap();
        data.put("currIndex", (currentPage - 1) * pageSize);
        data.put("pageSize", pageSize);
        data.put("dataSetId", dataSetId);
        return imageSetDao.getPageImage(data);
    }

    @Override
    @Transactional
    public void addImageList( int dataSetId,List<String> imgNameList) {
        List<Image> imageList = new ArrayList<>();
        DataSet dataSet= dataSetDao.selectDataSetById(dataSetId);
        String dataSetSrc=dataSet.getSrc();
        if (imgNameList != null) {
            for (String imgName : imgNameList) {
                Image image = new Image(imgName);
                image.setSign(0);
                image.setDataSetId(dataSetId);
                String src =dataSetSrc +"src"+ File.separator + imgName;
                image.setSrc(src);
                image.setImgName(imgName);
                imageList.add(image);
            }
        }

        if(dataSet!=null){
            int annTotal= dataSet.getAnnotationTotal();
            int imageTotal= dataSet.getImageTotal();
            imageTotal+=imageList.size();
            if (imageTotal==annTotal)dataSet.setStatus(2);
            else if(imageTotal>annTotal)dataSet.setStatus(1);
            dataSet.setImageTotal(imageTotal);
        }
        imageSetDao.addImageList(imageList);
        dataSetDao.updateDataSetById(dataSet);
    }

    @Override
    public List<Image> getAllNoSignImage(int dataSetId) {
        return imageSetDao.getAllNoSignImage(dataSetId);
    }

    @Override
    public String getSrcById(int id) {
        return imageSetDao.getSrcById(id);
    }

    @Override
    @Transactional
    public void saveLabelXml(List<LabelModel> labels, int id) throws IOException, JAXBException {

        Image image = imageSetDao.selectImageById(id);
        String src = image.getSrc();
        if (!StringUtils.isEmpty(src)) {
            File file = new File(baseConfig.getPath() + File.separator + src);
            Size size = ImageUtils.getSize(file);
            Annotation annotation = new Annotation();
            annotation.setFolder(file.getParent());
            annotation.setFileName(file.getName());
            List<Label> labelList=getLabelListByModel(labels,size);
           annotation.setLabel(labelList);
            annotation.setSize(size);
            String xml = JavaBeanToXml.beanToXml(annotation, Annotation.class);
            String filName=file.getName();
            String parentPath= file.getParent().replaceAll("src","mark");
            String xmlPath = parentPath+File.separator+filName.substring(0,filName.indexOf('.'))+".xml";
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(xmlPath)));//输出到XML文件
            bfw.write(xml);
            bfw.close();
        }
        image.setSign(1);
        int dataSetId = image.getDataSetId();
        DataSet dataSet=null;
        if (dataSetId != 0) {
           dataSet=dataSetDao.selectDataSetById(dataSetId);
        }
        if(dataSet!=null){
            int annationTotal=dataSet.getAnnotationTotal();
           int imageTotal= dataSet.getImageTotal();
               annationTotal+=1;
            if (imageTotal==annationTotal)dataSet.setStatus(2);
           dataSet.setAnnotationTotal(annationTotal);

        }
        imageSetDao.updateImageById(image);
        dataSetDao.updateDataSetById(dataSet);
    }

    @Override
    public int getCount(int dataSetId) {
        return imageSetDao.getCount(dataSetId);
    }

    @Override
    public void deleteImage(int id) throws IOException {
        Image image = imageSetDao.selectImageById(id);
        String src = image.getSrc();
        String path = MyFileUtils.getThumbnailPath(src);
        MyFileUtils.deleteFile(baseConfig.getPath() + src);
        MyFileUtils.deleteFile(baseConfig.getPath() + path);
        imageSetDao.deleteImageById(id);
    }

    @Override
    public List<Image>  getAllSignImage(int dataSetId) {
        return imageSetDao.getAllSignImage(dataSetId);
    }

    private List<Label> getLabelListByModel(List<LabelModel> labelModels,Size size){
     List<Label> labelList=new ArrayList<>();
     for(LabelModel labelModel:labelModels){
         int xMin=(int)Math.round(labelModel.getX()*size.getWidth()) ;
         int xMax=(int)Math.round(labelModel.getEx()*size.getWidth()) ;
         int yMin=(int)Math.round(labelModel.getY()*size.getHeight()) ;
         int yMax=(int)Math.round(labelModel.getEy()*size.getHeight()) ;
         Label label=new Label();
         label.setyMax(yMax);
         label.setyMin(yMin);
         label.setxMin(xMin);
         label.setxMax(xMax);
         labelList.add(label);
     }
     return labelList;
    }

}
