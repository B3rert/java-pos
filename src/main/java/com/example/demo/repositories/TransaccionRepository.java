package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.TransaccionModel;

@Repository
public interface TransaccionRepository extends JpaRepository<TransaccionModel, Integer> {

    List<TransaccionModel> findByDocumento_Id(Integer documentoId);
    
}
