package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.Project;
import com.horan.dsmlmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project")
public class ProjectController  {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/table")
    public List<Project> getProjectList(@RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize){

        List<Project> result=projectService.getProjectList(currentPage,pageSize);
        return result;
    }

}
