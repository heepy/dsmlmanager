package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.ImageSet;
import com.horan.dsmlmanager.service.ImageSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/image")
public class ImageSetController {

    @Autowired
    private ImageSetService imageSetService;
    @GetMapping(value = "/table/{projectID}")
    public List<ImageSet> getImageTable(@PathVariable(name = "projectID") int projectID , @RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize){
        System.out.println(projectID);

     return imageSetService.getPageImageSet(currentPage,pageSize,projectID);
    }
}
