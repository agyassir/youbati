package main.Service;

import main.Entity.Project;

public interface ProjectServiceInterface extends GenericService<Project>{
    double calculateTotalCost(Project project);
}
