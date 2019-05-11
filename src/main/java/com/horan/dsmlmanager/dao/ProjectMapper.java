package com.horan.dsmlmanager.dao;

import com.horan.dsmlmanager.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {
    List<Project> getPageProject(Map<String,Object> data);
}
