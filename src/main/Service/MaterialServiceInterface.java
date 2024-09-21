package main.Service;

import main.Entity.Material;

public interface MaterialServiceInterface extends GenericService<Material> {
    double calculateCost(Material material);
}
