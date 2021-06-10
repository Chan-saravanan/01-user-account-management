package com.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, String>{

}
