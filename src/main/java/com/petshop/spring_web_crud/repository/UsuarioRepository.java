package com.petshop.spring_web_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petshop.spring_web_crud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmailAndPasswordHash(String email, String passwordHash);
}