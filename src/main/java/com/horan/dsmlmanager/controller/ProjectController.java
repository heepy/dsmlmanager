package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.Project;
import com.horan.dsmlmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/table")
    public Map<String, Object> getProjectList(@RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        List<Project> projectList = projectService.getProjectList(currentPage, pageSize);
        result.put("rows",projectList);
        result.put("total",projectList.size()+100);
        return result;
    }
    @GetMapping(value = "/table/total")
    public int getTotal(){
        return 5;
    }

}
