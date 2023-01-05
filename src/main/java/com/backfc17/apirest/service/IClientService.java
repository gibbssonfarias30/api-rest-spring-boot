package com.backfc17.apirest.service;


import com.backfc17.apirest.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<Client> findAll();
    Optional<Client> findById (Integer id);
    Client save (Client client);
    void delete(Integer id);


}
