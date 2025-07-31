package com.client.management.oriontek_client_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.client.management.oriontek_client_management.domain.models.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

}
