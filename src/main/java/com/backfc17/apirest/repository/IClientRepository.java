package com.backfc17.apirest.repository;

import com.backfc17.apirest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Integer> {
}
