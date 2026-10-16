package com.petshop.spring_web_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petshop.spring_web_crud.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    List<Producto> findAllByOrderByIdProductoDesc();
    
    @Query("""
            select p from Producto p
            where 
                (p.categoria.idCategoria = :idCategoria or :idCategoria is null)
            order by
                p.idProducto desc
            """)
    List<Producto> findByFilters(
            @Param("idCategoria") Integer idCategoria
    );
}