package com.example.demo.controllers;

import com.example.demo.models.DocumentoModel;
import com.example.demo.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin(origins = "*")
public class DocumentoController {

    @Autowired
    private DocumentoRepository documentoRepository;

    @GetMapping
    public ResponseEntity<List<DocumentoModel>> obtenerTodosLosDocumentos() {
        List<DocumentoModel> documentos = documentoRepository.findAll();
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<List<DocumentoModel>> obtenerDocumentosPorUsuario(@PathVariable String usuario) {
        List<DocumentoModel> documentos = documentoRepository.findByUsuario(usuario);
        return ResponseEntity.ok(documentos);
    }

    @PostMapping
    public ResponseEntity<DocumentoModel> crearNuevoDocumento(@RequestBody DocumentoModel nuevoDocumento) throws URISyntaxException {
        try {
            DocumentoModel documentoCreado = documentoRepository.save(nuevoDocumento);
            return ResponseEntity.ok(documentoCreado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
