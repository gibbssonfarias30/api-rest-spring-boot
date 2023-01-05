package com.backfc17.apirest.service;

import com.backfc17.apirest.model.Client;
import com.backfc17.apirest.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository repository;

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
