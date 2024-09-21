package main.Service.Implementation;

import main.Entity.Component;
import main.Entity.Labor;
import main.Entity.Material;
import main.Entity.Project;
import main.Repository.GenericsRepo;
import main.Service.ComponentServiceInterface;
import main.Service.ProjectServiceInterface;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectServiceInterface {

    private final GenericsRepo<Project> projectRepository;
    private final ComponentServiceInterface<Material> materialService;
    private final ComponentServiceInterface<Labor> LaborService;

    public ProjectServiceImpl(GenericsRepo<Project> projectRepository, ComponentServiceInterface<Material> materialService, ComponentServiceInterface<Labor> laborService) {
        this.projectRepository = projectRepository;
        this.materialService = materialService;
        this.LaborService = laborService;
    }

    @Override
    public Project create(Project project) {
        return projectRepository.create(project);
    }

    @Override
    public Optional<Project> getById(int id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project update(Project project) {
        return projectRepository.update(project);
    }

    @Override
    public void delete(int id) {
        projectRepository.deleteById(id);
    }

    @Override
    public double calculateTotalCost(Project project) {
        double TotalCost=0.0;
        double ComponentTotalCost=0.0;


    for (Component component: project.getComponents()){
        if (component instanceof Material){
            ComponentTotalCost += materialService.calculateCost((Material) component);
        }else {
            ComponentTotalCost+= LaborService.calculateCost((Labor) component);
        }
    }

    double CoutMarge=(ComponentTotalCost* project.getMargeBeneficiaire())/100;

    TotalCost=ComponentTotalCost+CoutMarge;

        return TotalCost;
    }
}

