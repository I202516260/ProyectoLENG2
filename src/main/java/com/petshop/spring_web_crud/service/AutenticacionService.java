package com.petshop.spring_web_crud.service;

import org.springframework.stereotype.Service;

import com.petshop.spring_web_crud.dto.AutenticacionFilter;
import com.petshop.spring_web_crud.model.Usuario;
import com.petshop.spring_web_crud.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacionService {
    
    private final UsuarioRepository usuarioRepository;
    
    public Usuario autenticathe(AutenticacionFilter filter) {
        return usuarioRepository.findByEmailAndPasswordHash(filter.email(), filter.passwordHash());
    }
}