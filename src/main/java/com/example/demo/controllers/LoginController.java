package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> realizarLogin(@RequestBody Map<String, String> credenciales) {
        String usuario = credenciales.get("usuario");
        String clave = credenciales.get("clave");

        String sqlQuery = "SELECT * FROM usuarios WHERE usuario = :usuario AND clave = :clave";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("usuario", usuario);
        query.setParameter("clave", clave);

        List<Object[]> resultados = query.getResultList();

        if (!resultados.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Usuario correcto");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Usuario incorrecto");
            return ResponseEntity.ok(response);
        }
    }
}
