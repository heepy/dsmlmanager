package com.horan.dsmlmanager.service.impl;

import com.horan.dsmlmanager.dao.ImageSetMapper;
import com.horan.dsmlmanager.entity.ImageSet;
import com.horan.dsmlmanager.service.ImageSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageSetServiceImpl implements ImageSetService {

    @Autowired
    private ImageSetMapper imageSetDao;
    @Override
    public List<ImageSet> getPageImageSet(int currentPage,int pageSize,int projectID) {
        Map<String, Object> data = new HashMap();
        data.put("currIndex", (currentPage-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("projectID",projectID);
        return imageSetDao.getPageImage(data);
    }
}
