package main.Service;

import main.Entity.Client;
import main.Entity.Project;

import java.util.List;

public interface ClientServiceInterface extends GenericService<Client>{
    List<Client> findByName(String name,String Lname);

}
