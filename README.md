# Apis Rest con JAVA

Apis Rest para app POS con Spring Boot y MySQl. 

## Endpoints

### Login (POST)

Inicio de sesion con credenciales.

```txt 
http://localhost:8080/api/login
```

**Body**
```json
{
"usuario":"admin",
"clave":"123"
}
```

**Respuesta**

```json
{
    "success": true,
    "message": "Usuario correcto"
}
```


### Register User (POST)

Registro de un nuevo usuario 

```txt 
http://localhost:8080/api/registro
```

**Body**
```json
{
"usuario":"admin",
"clave":"123"
}
```

**Respuesta**

```json
{
    "success": true,
    "message": "Usuario correcto"
}
```

### Obtener productos (GET)

```txt
http://localhost:8080/api/productos
```

**Respuesta**

```json
[
    {
        "precioUnitario": 4.5,
        "codigoProducto": "001",
        "cantidadProducto": 77,
        "nombreProducto": "Manzana roja"
    }
]
```


### Crear productp (POST)

Registro de un nuevo usuario 

```txt 
http://localhost:8080/api/productos
```
**Body**
```json
{
    "codigoProducto": "10001",
    "nombreProducto": "producto prueba",
    "precioUnitario": 9.5,
    "cantidadProducto": 1
}
```

**Respuesta**

```json
{
    "success": true,
    "message": "Producto creado correctamente"
}
```



## Notas

* Recuerde cambiar el puerto en las peticiones segun su ambiente.
* La base de datos no se incluye en el proyecto.
