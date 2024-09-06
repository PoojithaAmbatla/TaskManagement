package com.tac.taskmanagement.service;

import com.tac.taskmanagement.entity.ClientEntity;
import com.tac.taskmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientEntity saveClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientEntity getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ClientEntity updateClient(Long id, ClientEntity client) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setName(client.getName());
            return clientRepository.save(existingClient);
        }).orElse(null);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
