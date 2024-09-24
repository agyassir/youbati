package main.Service;

import main.Entity.Estimate;
import main.Entity.Project;

public interface EstimateServiceInterface extends GenericService<Estimate>{
void calculateEstimatecout(Project project,Estimate estime);
}
