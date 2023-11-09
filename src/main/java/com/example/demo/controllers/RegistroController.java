package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistroController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/registro")
    @Transactional
    public ResponseEntity<Map<String, Object>> registrarUsuario(@RequestBody Map<String, String> datosUsuario) {
        String usuario = datosUsuario.get("usuario");
        String clave = datosUsuario.get("clave");

        // Verificar si el usuario ya existe
        if (usuarioExistente(usuario)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Usuario ya existe");
            return ResponseEntity.ok(response);
        }

        // Si no existe, crear el nuevo usuario
        String sqlInsert = "INSERT INTO usuarios (usuario, clave) VALUES (:usuario, :clave)";
        Query insertQuery = entityManager.createNativeQuery(sqlInsert);
        insertQuery.setParameter("usuario", usuario);
        insertQuery.setParameter("clave", clave);
        insertQuery.executeUpdate();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Usuario creado correctamente");
        return ResponseEntity.ok(response);
    }

    private boolean usuarioExistente(String usuario) {
        String sqlQuery = "SELECT * FROM usuarios WHERE usuario = :usuario";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("usuario", usuario);
        List<Object[]> resultados = query.getResultList();
        return !resultados.isEmpty();
    }
}
