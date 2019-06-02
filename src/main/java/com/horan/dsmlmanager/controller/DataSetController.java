package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.DataSet;
import com.horan.dsmlmanager.service.DataSetSevice;
import com.horan.dsmlmanager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.OAEPParameterSpec;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.text.SimpleDateFormat;
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
    public Map<String, Object> getPageDataSet(@RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        List<DataSet> dataSetList = dataSetSevice.getPageDataSet(currentPage, pageSize);
        result.put("rows", dataSetList);
        System.out.println(dataSetList.get(0).getCreateTime());
        int total = dataSetSevice.getTotal();
        result.put("total", total);
        return result;
    }

    @PostMapping(value = "/addset")
    public Map<String, Object> addDataSet(@RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        DataSet dataSet = new DataSet();
        String dataSetName = String.valueOf(param.get("dataSetName"));
        Path path = FileUtils.addFolder(dataSetName);
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
