package com.horan.dsmlmanager.service;

import com.horan.dsmlmanager.entity.Image;
import com.horan.dsmlmanager.entity.Label;
import com.horan.dsmlmanager.entity.LabelModel;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {

    List<Image> getPageImageSet(int currentPage, int pageSize, int dataSetId);
    void  addImageList(List<String> imgNameList,int dataSetId);
    List<Image> getAllNoSignImage(int dataSetId);
    String getSrcById(int id);
    void saveLabelXml(List<LabelModel> labels, int id) throws IOException, JAXBException;
    int getCount(int dataSetId);
    void deleteImage(int id) throws IOException;

}
