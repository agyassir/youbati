package main.Service;

import java.util.List;
import java.util.Optional;

public interface GenericService<t> {
    
    public t create(t entity);

     
    public Optional<t> getById(int id);

     
    public List<t> getAll() ;

     
    public t update(t entity);

     
    public void delete(int id);
}
