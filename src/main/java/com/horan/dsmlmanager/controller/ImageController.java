package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.config.BaseConfig;
import com.horan.dsmlmanager.entity.Image;
import com.horan.dsmlmanager.entity.Label;
import com.horan.dsmlmanager.service.ImageService;
import com.horan.dsmlmanager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageSetService;
    @Autowired
    private BaseConfig baseConfig;
    @GetMapping(value = "/table/{projectID}")
    public List<Image> getImageTable(@PathVariable(name = "projectID") int projectID , @RequestParam(name = "currentpage") int currentPage, @RequestParam(name = "pagesize") int pageSize){
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
        imageSetService.addImageList(result,id);
        return result;
    }
    @GetMapping(value = "/{dataSetId}/getlist")
    public List<Image> getImageNameList(@PathVariable(name = "dataSetId") int dataSetId){
        List<Image> result=imageSetService.getAllNoSignImage(dataSetId);

        return result;
    }
    @PostMapping(value = "/addlable")
    public void addLabelForImage(@RequestBody Map<String,Object> param){
       int id=(int)param.get("id");
       List<Label> labelList=(ArrayList<Label>)param.get("labels");
        try {
            imageSetService.saveLabelXml(labelList,id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(id);
    }
    @GetMapping(value = "/getimglist")
    public Map<String,Object> getImageList(@RequestParam(name = "dataSetId") int dataSetId,@RequestParam(name = "pageCurrent") int currentPage, @RequestParam(name = "pageSize") int page ){
        Map<String,Object> result=new HashMap<>();
        List<Image> imageList=new ArrayList<>();
        Image image=new Image();
        String src="test.jpg";
        image.setSrc(src);
        image.setImgName(src);
        image.setId(0);
        image.setSign(1);
        image.setDataSetId(10007);
        imageList.add(image);
        result.put("imgList",imageList);
        result.put("total",50);
          System.out.println(image.getSrc());
        System.out.println(image.getImgName());

        return result;
    }
}
