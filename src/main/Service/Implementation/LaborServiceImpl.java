package main.Service.Implementation;

import main.Entity. Labor;
import main.Repository.GenericsRepo;
import main.Service.GenericService;
import main.Service.LaborServiceInterface;

import java.util.List;
import java.util.Optional;

public class  LaborServiceImpl implements LaborServiceInterface {


    private final GenericsRepo<Labor> LaborRepository;

    public  LaborServiceImpl(GenericsRepo<Labor> LaborRepository) {
        this.  LaborRepository =   LaborRepository;
    }

    @Override
    public Labor create(Labor labor) {
        return   LaborRepository.create(labor);
    }

    @Override
    public Optional<Labor> getById(int id) {
        return   LaborRepository.findById(id);
    }

    @Override
    public List<Labor> getAll() {
        return LaborRepository.findAll();
    }

    @Override
    public Labor update(Labor Labor) {
        return   LaborRepository.update(Labor);
    }

    @Override
    public void delete(int id) {
        LaborRepository.deleteById(id);
    }

    @Override
    public double calculateCost(Labor labor){
        double costWithoutTax = labor.getTauxHoraire() * labor.getHeuresTravail() * labor.getProductiviteOuvrier();
        return costWithoutTax + (costWithoutTax * labor.getTauxTVA() / 100);
    }
}


