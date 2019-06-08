package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.Project;
import com.horan.dsmlmanager.service.ProjectService;
import com.horan.dsmlmanager.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/table")
    public Map<String, Object> getProjectList(@RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize, @RequestParam(name = "userId", required = true) int userId) {
        Map<String, Object> result = new HashMap<>();
        List<Project> projectList = projectService.getProjectList(currentPage, pageSize, userId);
        int total = projectService.getTotal(userId);
        result.put("rows", projectList);
        result.put("total", total);
        return result;
    }

    @GetMapping(value = "/table/total")
    public int getTotal() {
        return 5;
    }

    @PostMapping(value = "/{userId}/addproject")
    public @ResponseBody
    Map<String, Object> submit(String proName, String proDec, MultipartFile file, @PathVariable(value = "userId") int userId)
            {
        Map<String, Object> result = new HashMap<>();
        //这里就可以获取里面的上传过来的数据了
        Project project = new Project();
        project.setProDec(proDec);
        project.setProName(proName);
        Date createTime = new Date();
        project.setProTime(createTime);
        String src = File.separator + userId + File.separator + proName + File.separator;
        String proModel=file.getOriginalFilename();
        project.setProModel(proModel);
        project.setSrc(src);
        project.setUserId(userId);
        try {
            projectService.addProject(project);
        }catch ( DataAccessException e){
            result.put("message","项目名不能重复！！");
        }
        //做一些存库操作，以及返回的数据
                try {
                    MyFileUtils.saveModelFile(src, file);
                } catch (IOException e) {
                    result.put("message","系统错误，目录不能创建");
                }
                return result;
    }


}
