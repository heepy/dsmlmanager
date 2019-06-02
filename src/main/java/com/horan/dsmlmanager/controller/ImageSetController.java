package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.entity.ImageSet;
import com.horan.dsmlmanager.service.ImageSetService;
import com.horan.dsmlmanager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    @PostMapping(value = "/{id}/upload")
    public List<String> uploadImage(@PathVariable(required = true,name = "id") int id,@RequestParam(value = "fileList",required = false) MultipartFile[] fileList){
        String folderName=String.valueOf(id);
        List<String> result=new ArrayList<>();
        for (MultipartFile file:fileList){
           String name= FileUtils.writeUploadFile(file,folderName);
           result.add(name);
        }
        return result;
    }
}
