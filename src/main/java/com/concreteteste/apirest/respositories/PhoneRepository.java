package com.concreteteste.apirest.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concreteteste.apirest.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
