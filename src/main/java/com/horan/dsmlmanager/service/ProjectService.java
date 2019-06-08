package com.horan.dsmlmanager.service;

import com.horan.dsmlmanager.entity.Project;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService  {
   List<Project> getProjectList(int currentPage,int pageSize,int userId);
   int getTotal(int userId);
   void addProject(Project project) ;
   Project getSimpleProject(int proId);
   void updateProject(Project project) throws DataAccessException;
}
