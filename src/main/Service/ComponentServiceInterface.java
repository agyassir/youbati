package main.Service;

import main.Entity.Component;
import main.Entity.Labor;

public interface ComponentServiceInterface<T extends Component> extends GenericService<T>{
    double calculateCost(T component);

    double CostWTVA(T component);
}
