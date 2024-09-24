package main.Service.Implementation;

import main.Entity. Estimate;
import main.Entity.Project;
import main.Repository.GenericsRepo;
import main.Service.EstimateServiceInterface;
import main.Service.GenericService;

import java.util.List;
import java.util.Optional;

public class  EstimateServiceImpl implements EstimateServiceInterface {


    private final GenericsRepo<Estimate>   EstimateRepository;

    public  EstimateServiceImpl(GenericsRepo<Estimate>   EstimateRepository) {
        this.  EstimateRepository =   EstimateRepository;
    }

    @Override
    public   Estimate create(Estimate estimate) {
        return   EstimateRepository.create(estimate);
    }

    @Override
    public Optional<Estimate> getById(int id) {
        return   EstimateRepository.findById(id);
    }

    @Override
    public List<Estimate> getAll() {
        return EstimateRepository.findAll();
    }

    @Override
    public Estimate update(Estimate estimate) {
        return   EstimateRepository.update(estimate);
    }

    @Override
    public void delete(int id) {
        EstimateRepository.deleteById(id);
    }


    @Override
    public void calculateEstimatecout(Project project,Estimate estimate) {
        project.getCoutTotal();
        if (project.getClient().isProfessionnel()){
            estimate.setMontantEstime((project.getCoutTotal()*9)/100);
        }else{
            estimate.setMontantEstime(project.getCoutTotal());
        }


    }
}



