package com.horan.dsmlmanager.service;

import com.horan.dsmlmanager.dao.ImageSetMapper;
import com.horan.dsmlmanager.entity.ImageSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ImageSetService {

    public List<ImageSet> getPageImageSet(int currentPage,int pageSize,int projectID);


}
