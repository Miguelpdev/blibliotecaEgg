package com.egg.biblioteca.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.biblioteca.model.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, UUID> {

}
