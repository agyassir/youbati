package main.Repository;

import main.Entity.Project;

import java.util.List;

public interface ProjectRepo extends GenericsRepo<Project>{
    List<Project> findMyProjectByid(int id);
}
