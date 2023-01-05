package com.backfc17.apirest.controller;

import com.backfc17.apirest.model.Client;
import com.backfc17.apirest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private IClientService service;

    @GetMapping
    List<Client> listClients(){
        return service.findAll();
    }

    @PostMapping
    ResponseEntity<?> createClient(@RequestBody Client client){
        Client clientNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            clientNew = service.save(client);
        }catch (DataAccessException e){
            response.put("message", "Error when performing the insert to the database");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client created successfully");
        response.put("client", clientNew);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findClientById(@PathVariable Integer id){
        Optional<Client> client;
        Map<String, Object> response = new HashMap<>();

        try {
            client = service.findById(id);
        }catch (DataAccessException e){
            response.put("message", "Error when performing the insert to the database");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(client.isEmpty()){
            response.put("message", "Client with ID: " + id +" does not exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateClient(@RequestBody Client client, @PathVariable Integer id){
        Client clientCurrent = service.findById(id).orElseThrow();
        Client clientUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if(clientCurrent == null){
            response.put("message", "Client with ID: " + id +" does not exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            clientCurrent.setName(client.getName());
            clientCurrent.setLastname(client.getLastname());

            clientUpdate = service.save(clientCurrent);
        }catch (DataAccessException e){
            response.put("message", "Error when performing the insert to the database");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client updated successfully");
        response.put("client", clientUpdate);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteClientById(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();

        try {
            service.delete(id);
        }catch (DataAccessException e){
            response.put("message", "Error when performing the elimination to the database");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        response.put("message", "Client successfully removed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
