package com.horan.dsmlmanager.service;

import com.horan.dsmlmanager.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService  {
   List<Project> getProjectList(int currentPage,int pageSize,int userId);
}
