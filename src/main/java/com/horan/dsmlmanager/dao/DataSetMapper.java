package com.horan.dsmlmanager.dao;

import com.horan.dsmlmanager.entity.DataSet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataSetMapper {
     List<DataSet> getPageDataSetByProId(Map<String,Object> data);
     void addDataSet(DataSet dataSet);
     int getCount();
     void deleteDataSetById(int id);
     void updateDataSetById(DataSet dataSet);
     DataSet selectDataSetById(int id);
}
