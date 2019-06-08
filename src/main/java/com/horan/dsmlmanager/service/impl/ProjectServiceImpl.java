package com.horan.dsmlmanager.service.impl;

import com.horan.dsmlmanager.dao.ProjectMapper;
import com.horan.dsmlmanager.entity.Project;
import com.horan.dsmlmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectDao;
    @Override
    public List<Project> getProjectList(int currentPage, int pageSize,int userId) {
        Map<String, Object> data = new HashMap();
        data.put("currIndex", (currentPage-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("userId",userId);
        return projectDao.getPageProjectByUserId(data);
    }

    @Override
    public int getTotal(int userId) {
        return projectDao.getCount(userId);
    }

    @Override
    public void addProject(Project project) throws DataAccessException{
        projectDao.addProject(project);
    }

    @Override
    public Project getSimpleProject(int proId) {
        return projectDao.selectProjectById(proId);
    }

    @Override
    public void updateProject(Project project) throws DataAccessException {
        projectDao.updateProjectById(project);
    }
}
