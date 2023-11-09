package com.example.demo.repositories;

import com.example.demo.models.DocumentoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Integer> {

    List<DocumentoModel> findByUsuario(String nombreUsuario);
    // Puedes agregar métodos personalizados aquí si es necesario
}
