package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.DataSet;
import com.horan.dsmlmanager.entity.Image;
import com.horan.dsmlmanager.service.DataSetSevice;
import com.horan.dsmlmanager.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/dataset")
public class DataSetController {
    @Autowired
    private DataSetSevice dataSetSevice;

    @GetMapping(value = "/getlist")
    public Map<String, Object> getPageDataSet(@RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize, @RequestParam(name = "proId") int proId) {
        Map<String, Object> result = new HashMap<>();
        List<DataSet> dataSetList = dataSetSevice.getPageDataSet(currentPage, pageSize,proId);
        result.put("rows", dataSetList);
        int total = dataSetSevice.getTotal();
        result.put("total", total);
        return result;
    }

    @PostMapping(value = "/addset")
    public Map<String, Object> addDataSet(@RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        DataSet dataSet = new DataSet();
        String dataSetName = String.valueOf(param.get("dataSetName"));
        Path path = MyFileUtils.addFolder(dataSetName);
        if (path == null) {
            result.put("message", "创建失败！数据集名称不能相同！");
            return result;
        }
        Date date = new Date();
        dataSet.setDataSetName(dataSetName);
        dataSet.setCreateTime(date);
        dataSetSevice.addDataSet(dataSet);
        result.put("message", "success");
        return result;
    }
    @PostMapping(value = "/delete")
    public boolean deleteDataSetById(@RequestParam(value = "id",required = true) int id){

        dataSetSevice.deleteDataSetById(id);
        return true;
    }
}
