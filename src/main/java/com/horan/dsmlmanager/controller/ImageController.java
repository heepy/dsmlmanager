package com.horan.dsmlmanager.controller;

import com.horan.dsmlmanager.config.BaseConfig;
import com.horan.dsmlmanager.entity.Image;
import com.horan.dsmlmanager.entity.Label;
import com.horan.dsmlmanager.entity.LabelModel;
import com.horan.dsmlmanager.service.ImageService;
import com.horan.dsmlmanager.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

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
    public Map<String,Object> uploadImage(@PathVariable(required = true,name = "id") int id,@RequestParam(value = "fileList",required = false) MultipartFile[] fileList){
        String folderName=String.valueOf(id);
        Map<String,Object> result=new HashMap<>();

        imageSetService.addImageList(id,fileList);
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
       List<LinkedHashMap<String,Double>> labels  =(ArrayList<LinkedHashMap<String,Double>>)param.get("labels");
       List<LabelModel> labelList=new ArrayList<>();
       for (int i=0;i<labels.size();i++){
          LabelModel labelModel=new LabelModel();
          labelModel.setX(labels.get(i).get("x"));
           labelModel.setEx(labels.get(i).get("ex"));
           labelModel.setY(labels.get(i).get("y"));
           labelModel.setEy(labels.get(i).get("ey"));
           labelList.add(labelModel);
         }
        try {

            imageSetService.saveLabelXml(labelList,id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "/getimglist")
    public Map<String,Object> getImageList(@RequestParam(name = "dataSetId") int dataSetId,@RequestParam(name = "pageCurrent") int currentPage, @RequestParam(name = "pageSize") int page ){
        Map<String,Object> result=new HashMap<>();
        List<Image> imageList=imageSetService.getPageImageSet(currentPage,12,dataSetId);

        if (imageList!=null&&imageList.size()>0){
           for(Image image:imageList){
               String src= image.getSrc();
               String  thumbnail=MyFileUtils.getThumbnailPath(src);
               String srcNew=  thumbnail.replaceAll("\\\\","/");
               image.setSrc(srcNew);
           }
        }
        int total=imageSetService.getCount(dataSetId);
        result.put("imgList",imageList);
        result.put("total",total);

        return result;
    }
    @GetMapping(value = "/gettotal")
    public int getTotal(@RequestParam(name = "dataSetId") int dataSetId){
        return getTotal(dataSetId);
    }
    @DeleteMapping (value="/delete/{id}")
    public void deleteImage(@PathVariable(name = "id") int id){
        try {
            imageSetService.deleteImage(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "getimgbyid")
    public     Map<String,Object>  getImageById(@RequestParam(value = "id",required = true) int id){

        String src=imageSetService.getSrcById(id);
        String srcNew=  src.replaceAll("\\\\","/");
        Map<String,Object> result=new HashMap<>();
        result.put("src",srcNew);
        return result;
    }
}
