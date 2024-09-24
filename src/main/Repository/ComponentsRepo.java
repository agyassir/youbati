package main.Repository;

import main.Entity.Component;
import main.Entity.Project;

import java.util.List;

public interface ComponentsRepo<T extends Component> extends GenericsRepo<T> {
     void updateMaterials(List<T> components, Project prjt);

}
