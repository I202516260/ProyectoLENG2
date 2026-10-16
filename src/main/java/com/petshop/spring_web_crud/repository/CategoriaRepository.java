package com.petshop.spring_web_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petshop.spring_web_crud.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}