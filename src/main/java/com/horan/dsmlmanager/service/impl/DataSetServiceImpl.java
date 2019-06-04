package com.horan.dsmlmanager.service.impl;

import com.horan.dsmlmanager.dao.DataSetMapper;
import com.horan.dsmlmanager.entity.DataSet;
import com.horan.dsmlmanager.service.DataSetSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataSetServiceImpl implements DataSetSevice {

    @Autowired
    private DataSetMapper dataSetDao;
    @Override
    public List<DataSet> getPageDataSet(int currentPage, int pageSize,int proId) {
        Map<String, Object> data = new HashMap();
        data.put("currIndex", (currentPage-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("proId", proId);
        List<DataSet> dataSetList=dataSetDao.getPageDataSet(data);
        return dataSetList;
    }

    @Override
    public boolean addDataSet(DataSet dataSet) {
        dataSetDao.addDataSet(dataSet);
        return true;
    }

    @Override
    public int getTotal() {
        return dataSetDao.getCount();
    }

    @Override
    public boolean deleteDataSetById(int id) {
        dataSetDao.deleteDataSetById(id);
        return false;
    }


}
