package com.visioplanserver.service;

import com.visioplanserver.model.view.ProjectViewModel;

import java.util.List;

public interface BuildingService {
    List<ProjectViewModel> getAllBuildings();
}
