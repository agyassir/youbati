package main.Service.Implementation;

import main.Entity. Client;
import main.Entity.Project;
import main.Repository.ClientRepo;
import main.Repository.GenericsRepo;
import main.Service.ClientServiceInterface;
import main.Service.GenericService;

import java.util.List;
import java.util.Optional;

public class  ClientServiceImpl implements ClientServiceInterface {


        private final ClientRepo ClientRepository;

        public  ClientServiceImpl(ClientRepo   ClientRepository) {
            this.  ClientRepository =   ClientRepository;
        }

        @Override
        public   Client create(Client Client) {
            return   ClientRepository.create(Client);
        }

        @Override
        public Optional<Client> getById(int id) {
            return   ClientRepository.findById(id);
        }

        @Override
        public List<Client> getAll() {
            return ClientRepository.findAll();
        }

        @Override
        public Client update(Client client) {
            return   ClientRepository.update(client);
        }

        @Override
        public void delete(int id) {
              ClientRepository.deleteById(id);
        }

    @Override
    public List<Client> findByName(String name,String lname) {
        return   ClientRepository.findByName(name,lname);
    }


}


