package com.horan.dsmlmanager.dao;

import com.horan.dsmlmanager.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {
       List<Project> getPageProjectByUserId(Map<String,Object> data);
       int getCount(int userId);
       void addProject(Project project) throws DataAccessException;
       Project selectProjectById(int proId);
       void updateProjectById(Project project)throws  DataAccessException ;
}
