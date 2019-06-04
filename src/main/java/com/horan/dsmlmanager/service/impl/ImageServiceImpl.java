package com.horan.dsmlmanager.service.impl;

import com.horan.dsmlmanager.config.BaseConfig;
import com.horan.dsmlmanager.dao.DataSetMapper;
import com.horan.dsmlmanager.dao.ImageMapper;
import com.horan.dsmlmanager.entity.*;
import com.horan.dsmlmanager.service.ImageService;
import com.horan.dsmlmanager.utils.ImageUtils;
import com.horan.dsmlmanager.utils.JavaBeanToXml;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private BaseConfig baseConfig;
    @Override
    public List<Image> getPageImageSet(int currentPage, int pageSize, int dataSetId) {
        Map<String, Object> data = new HashMap();
        data.put("currIndex", (currentPage-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("dataSetId",dataSetId);
        return imageSetDao.getPageImage(data);
    }

    @Override
    public void addImageList(List<String> imgNameList,int dataSetId) {
        List<Image> imageList=new ArrayList<>();

        if(imgNameList!=null){
            for (String imagName:imgNameList){
                Image image=new Image(imagName);
                image.setSign(0);
                image.setDataSetId(dataSetId);
                String src=File.separator+dataSetId+File.separator+imagName;
                image.setSrc(src);
                imageList.add(image);
            }
        }
        imageSetDao.addImageList(imageList);
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
    public void saveLabelXml(List<Label> labels,int id) throws IOException, JAXBException {
     String src=imageSetDao.getSrcById(id);
     if(!StringUtils.isEmpty(src)){
         File file=new File(baseConfig.getPath()+File.separator+src);
         Size size=ImageUtils.getSize(file);
         Annotation annotation=new Annotation();
         annotation.setFolder(file.getParent());
         annotation.setFileName(file.getName());
         annotation.setLabel(labels);
         annotation.setSize(size);
        String xml=JavaBeanToXml.beanToXml(annotation,Annotation.class);
         String xmlPath = file.getParent()+File.separator+"label";
         BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(xmlPath)));
         bfw.write(xml);
         bfw.close();
     }
    }
}
