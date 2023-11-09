package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.TransaccionModel;
import com.example.demo.repositories.TransaccionRepository;

import java.util.List;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionRepository transaccionRepository;

      @GetMapping("/documento/{documentoId}")
    public ResponseEntity<List<TransaccionModel>> obtenerTransaccionesPorDocumento(@PathVariable Integer documentoId) {
        List<TransaccionModel> transacciones = transaccionRepository.findByDocumento_Id(documentoId);
        return ResponseEntity.ok(transacciones);
    }
    

     @PostMapping
    public ResponseEntity<TransaccionModel> crearNuevaTransaccion(@RequestBody TransaccionModel nuevaTransaccion) throws URISyntaxException {
        try {
            TransaccionModel transaccionCreada = transaccionRepository.save(nuevaTransaccion);
            URI location = new URI("/api/transacciones/" + transaccionCreada.getId()); // Establece la ubicación del nuevo recurso
            return ResponseEntity.created(location).body(transaccionCreada);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
