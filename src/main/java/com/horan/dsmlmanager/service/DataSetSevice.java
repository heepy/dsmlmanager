package com.horan.dsmlmanager.service;

import com.horan.dsmlmanager.entity.DataSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataSetSevice {
    List<DataSet> getPageDataSet(int currentPage,int pageSize,int proId);
    boolean addDataSet(DataSet dataSet);
    int getTotal(int proId);
    boolean deleteDataSetById(int id);
    DataSet getDataSetById(int dataSetId);
}
