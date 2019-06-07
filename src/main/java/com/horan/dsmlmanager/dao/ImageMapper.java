package com.horan.dsmlmanager.dao;

import com.horan.dsmlmanager.entity.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ImageMapper {
    List<Image> getPageImage(Map<String,Object> data);
    void addImageList(List<Image> imageList);
    List<Image> getAllNoSignImage(int dataSetId);
    String getSrcById(int id);
    int getCount(int dataSetId);
    Image selectImageById(int id);
    void deleteImageById(int id);
    void updateImageById(Image image);

}
