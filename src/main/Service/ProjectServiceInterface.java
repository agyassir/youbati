package main.Service;

import main.Entity.Project;

import java.util.List;

public interface ProjectServiceInterface extends GenericService<Project>{
    double calculateTotalCost(Project project);
    List<Project> MyprojectsbyID(int id);
}
