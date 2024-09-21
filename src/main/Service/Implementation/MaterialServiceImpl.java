package main.Service.Implementation;

import main.Entity.Material;
import main.Repository.GenericsRepo;
import main.Service.ComponentServiceInterface;

import java.util.List;
import java.util.Optional;

public class MaterialServiceImpl implements ComponentServiceInterface<Material> {

    private final GenericsRepo<Material> materialRepository;

    public MaterialServiceImpl(GenericsRepo<Material> materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material create(Material material) {
        return materialRepository.create(material);
    }

    @Override
    public Optional<Material> getById(int id) {
        return materialRepository.findById(id);
    }

    @Override
    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material update(Material material) {
        return materialRepository.update(material);
    }

    @Override
    public void delete(int id) {
        materialRepository.deleteById(id);
    }

    @Override
    public double calculateCost(Material material) {
        Double coutUnitaire=material.getCoutUnitaire();
        double costWithoutTax = (coutUnitaire * material.getQuantite() * material.getCoefficientQualite()) + material.getCoutTransport();
        return costWithoutTax + (costWithoutTax * material.getTauxTVA() / 100);
    }

    @Override
    public double CostWTVA(Material material){
        Double coutUnitaire=material.getCoutUnitaire();
        double costWithoutTax = (coutUnitaire * material.getQuantite() * material.getCoefficientQualite()) + material.getCoutTransport();
        return costWithoutTax;
    }
}

