package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.DataSet;
import com.horan.dsmlmanager.entity.Image;
import com.horan.dsmlmanager.entity.Project;
import com.horan.dsmlmanager.service.DataSetSevice;
import com.horan.dsmlmanager.service.ProjectService;
import com.horan.dsmlmanager.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/getlist")
    public Map<String, Object> getPageDataSet(@RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize, @RequestParam(name = "proId") int proId) {
        Map<String, Object> result = new HashMap<>();
        List<DataSet> dataSetList = dataSetSevice.getPageDataSet(currentPage, pageSize,proId);
        result.put("rows", dataSetList);
        int total = dataSetSevice.getTotal(proId);
        result.put("total", total);
        return result;
    }

    @PostMapping(value = "/addDataset")
    public @ResponseBody
    Map<String, Object> submit(@RequestBody DataSet dataSet)
    {
        Map<String, Object> result = new HashMap<>();
        int proId=dataSet.getProId();
        Project project=projectService.getSimpleProject(proId);
        if(project==null){
            result.put("message","项目不存在");
            return result;
        }
        String projectSrc= project.getSrc();
        String dataSetSrc=projectSrc+dataSet.getDataSetName()+File.separator;
        dataSet.setCreateTime(new Date());
        dataSet.setSrc(dataSetSrc);
        try{
        dataSetSevice.addDataSet(dataSet);
        }catch (DataAccessException e){
            e.printStackTrace();
           result.put("message","数据集名不能重复");
        }//这里就可以获取里面的上传过来的数据了

        MyFileUtils.mkDataSetDir(dataSetSrc);
        return result;
    }
    @PostMapping(value = "/delete")
    public boolean deleteDataSetById(@RequestParam(value = "id",required = true) int id){

        dataSetSevice.deleteDataSetById(id);
        return true;
    }
}
