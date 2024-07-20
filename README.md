# FoodTracker

Aplicación de Sistema de Gestión de Alimentos

![Estado actual del proyecto](https://imgur.com/AUvmuHP)

## Descripción

FoodTracker es una aplicación diseñada para analizar alimentos , nos proporciona un buscador de alimentos con cierta información sobre ellos, también un calendario para poder hacer listas de alimentos para la compra y crear recetas.

## Funcionalidades

- **Autenticación de usuarios**: Registro, inicio de sesión y gestión de sesiones.
- **Área de usuarios**: Gestionar datos del perfil de los usuarios y visualizar historial de alimentos.
- **Gestión de alimentos**: Crear, actualizar, borrar y listar alimentos.
- **Búsqueda de alimentos**: Búsqueda de alimentos por categorías y valores nutricionales.
- **Sistema de recetas**: Descubrir y guardar recetas basadas en los alimentos disponibles.
- **Listas de compras**: Crear y gestionar listas de compras sincronizadas con tu inventario.

## Tecnologías Utilizadas

- **Backend**: Spring Boot
- **Base de Datos**: MySQL
- **Frontend**: Thymeleaf
- **Gestión de dependencias**: Maven
- **Estilos**: Bootstrap

## Versiones Utilizadas

- **Java**: 17
- **Maven**: 3.9.7
- **Spring Boot**: 3.3.1
- **Bootstrap**: 4.0.0

## Instalación

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/pbl99/FoodTracker.git
    cd FoodTracker
    ```

2. Compilar el proyecto:

    ```bash
    mvn clean install
    ```

3. Ejecutar la aplicación:

    ```bash
    mvn spring-boot:run
    ```

4. Acceder a la aplicación:

    Abre tu navegador web y navega a [http://localhost:8080](http://localhost:8080).

## Configuración

### Base de Datos

Por defecto, la aplicación usa una base de datos MySQL. Puedes cambiar la configuración de la base de datos en el archivo `application.properties` ubicado en `src/main/resources/`.

Ejemplo de configuración para MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foodtracker
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

## Contribuir
Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Fork el repositorio
2. Crea una nueva rama (git checkout -b feature/nueva-funcionalidad)
3. Realiza tus cambios y haz commit (git commit -am 'Añadir nueva funcionalidad')
4. Push a la rama (git push origin feature/nueva-funcionalidad)
5. Crea un Pull Request
   
## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
