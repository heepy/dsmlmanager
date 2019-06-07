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
    public void addImageList(List<String> imgNameList, int dataSetId) {
        List<Image> imageList = new ArrayList<>();

        if (imgNameList != null) {
            for (String imgName : imgNameList) {
                Image image = new Image(imgName);
                image.setSign(0);
                image.setDataSetId(dataSetId);
                String src = File.separator + dataSetId + File.separator + imgName;
                image.setSrc(src);
                image.setImgName(imgName);
                imageList.add(image);
            }
        }
       DataSet dataSet= dataSetDao.selectDataSetById(dataSetId);
        if(dataSet!=null){
           int annTotal= dataSet.getAnnotationTotal();
           annTotal+=imageList.size();
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
            String xmlPath = file.getParent() + File.separator + "mark"+File.separator+filName.substring(0,filName.indexOf('.'))+".xml";
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
           int annatationTatol= dataSet.getAnnotationTotal();
           annatationTatol+=1;
           dataSet.setAnnotationTotal(annatationTatol);
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
        String path = MyFileUtils.getThumbnailPath(File.separator + src);
        MyFileUtils.deleteFile(baseConfig.getPath() + src);
        MyFileUtils.deleteFile(baseConfig.getPath() + path);
        imageSetDao.deleteImageById(id);
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
