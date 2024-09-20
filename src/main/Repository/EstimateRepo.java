package main.Repository;

import main.Entity.Estimate;

import java.util.List;

public interface EstimateRepo extends GenericsRepo<Estimate>{
    List<Estimate> findByProjectId(int projectId);
}
