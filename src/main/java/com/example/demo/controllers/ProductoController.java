package com.example.demo.controllers;

import org.hibernate.engine.internal.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> crearProducto(@RequestBody Map<String, Object> nuevoProducto) {
        String codigoProducto = (String) nuevoProducto.get("codigoProducto");

        if (productoExistente(codigoProducto)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "El código ya existe");
            return ResponseEntity.ok(response);
        }

        String sqlInsert = "INSERT INTO productos (codigoProducto, nombreProducto, precioUnitario, cantidadProducto) " +
                "VALUES (:codigoProducto, :nombreProducto, :precioUnitario, :cantidadProducto)";

        Query insertQuery = entityManager.createNativeQuery(sqlInsert);
        insertQuery.setParameter("codigoProducto", codigoProducto);
        insertQuery.setParameter("nombreProducto", nuevoProducto.get("nombreProducto"));
        insertQuery.setParameter("precioUnitario", nuevoProducto.get("precioUnitario"));
        insertQuery.setParameter("cantidadProducto", nuevoProducto.get("cantidadProducto"));
        insertQuery.executeUpdate();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Producto creado correctamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerTodosLosProductos() {
        String sqlQuery = "SELECT * FROM productos";
        Query query = entityManager.createNativeQuery(sqlQuery);

        try {
            List<Object[]> productos = query.getResultList();
            List<Map<String, Object>> productosMap = new ArrayList<>();

            for (Object[] row : productos) {
                Map<String, Object> productoMap = new HashMap<>();
                productoMap.put("codigoProducto", row[0]);
                productoMap.put("nombreProducto", row[1]);
                productoMap.put("precioUnitario", row[2]);
                productoMap.put("cantidadProducto", row[3]);
                productosMap.add(productoMap);
            }

            return ResponseEntity.ok(productosMap);
        } catch (Exception e) {

            e.printStackTrace();
            List<Map<String, Object>> productosMap = new ArrayList<>();
            return ResponseEntity.ok(productosMap);

            // Map<String, Object> errorResponse = new HashMap<>();
            // errorResponse.put("error", "Error al obtener productos");
            // return
            // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(errorResponse));
        }
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<Map<String, Object>> actualizarProducto(
            @RequestBody Map<String, Object> productoActualizado) {

        // Actualizar el producto
        String sqlUpdate = "UPDATE productos SET codigoProducto = :codigoProducto, " +
                "nombreProducto = :nombreProducto, precioUnitario = :precioUnitario, cantidadProducto = :cantidadProducto "
                +
                "WHERE codigoProducto = :codigoProducto";

        Query updateQuery = entityManager.createNativeQuery(sqlUpdate);
        updateQuery.setParameter("codigoProducto", productoActualizado.get("codigoProducto"));
        updateQuery.setParameter("nombreProducto", productoActualizado.get("nombreProducto"));
        updateQuery.setParameter("precioUnitario", productoActualizado.get("precioUnitario"));
        updateQuery.setParameter("cantidadProducto", productoActualizado.get("cantidadProducto"));
        updateQuery.executeUpdate();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Producto actualizado correctamente");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{codigoProducto}")
    @Transactional
    public ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable String codigoProducto) {

        String sqlDelete = "DELETE FROM productos WHERE codigoProducto = :codigoProducto";
        Query deleteQuery = entityManager.createNativeQuery(sqlDelete);
        deleteQuery.setParameter("codigoProducto", codigoProducto);
        deleteQuery.executeUpdate();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Producto eliminado correctamente");
        return ResponseEntity.ok(response);
    }

    private boolean productoExistente(String codigoProducto) {
        String sqlQuery = "SELECT * FROM productos WHERE codigoProducto = :codigoProducto";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("codigoProducto", codigoProducto);
        List<Object[]> resultados = query.getResultList();
        return !resultados.isEmpty();
    }

}
