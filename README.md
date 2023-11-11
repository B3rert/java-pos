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


## Notas

* Recuerde cambiar el puerto en las peticiones segun su ambiente.
* La base de datos no se incluye en el proyecto.
