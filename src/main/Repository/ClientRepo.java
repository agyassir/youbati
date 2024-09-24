package main.Repository;

import main.Entity.Client;
import main.Entity.Project;

import java.util.List;

public interface ClientRepo extends GenericsRepo<Client>{
    List<Client> findByName(String name,String lname);

}
