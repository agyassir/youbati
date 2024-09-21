package main.Service;

import main.Entity.Labor;

public interface LaborServiceInterface extends GenericService<Labor>{

    double calculateCost(Labor labor);

}
