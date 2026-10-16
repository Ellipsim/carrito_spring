# Carrito de la compra — Spring Boot

Aplicación web desarrollada con Spring Boot para gestionar un catálogo de productos y un carrito de la compra. Incluye funcionalidades de consulta y edición de productos, gestión del carrito, administración y control de acceso mediante autenticación y autorización.

Github: https://github.com/Ellipsim/carrito_spring

## Estructura del proyecto

El código Java se encuentra en `src/main/java` y contiene los componentes responsables de procesar las peticiones, aplicar las reglas de negocio y gestionar los datos. Las vistas HTML se encuentran en `src/main/resources/templates`, mientras que los recursos estáticos, como hojas de estilo y archivos JavaScript, se ubican en `src/main/resources/static`. La configuración general de la aplicación se define en los archivos de configuración de Spring Boot. Las pruebas automatizadas se organizan en `src/test`.

Los controladores reciben las peticiones HTTP y coordinan las operaciones solicitadas por el usuario. La lógica de negocio se encarga de gestionar el catálogo y el carrito, mientras que la capa de acceso a datos permite consultar y modificar la información almacenada.

Las plantillas Thymeleaf presentan los datos al usuario y proporcionan los formularios necesarios para ejecutar las operaciones disponibles. Spring Security aplica las restricciones de autenticación y autorización, evitando que los usuarios accedan a funcionalidades para las que no tienen permisos.

La aplicación utiliza Spring Security para controlar el acceso a las distintas funcionalidades según el estado de autenticación y los permisos del usuario. Las operaciones que modifican datos están protegidas mediante tokens CSRF, de acuerdo con la configuración de seguridad del proyecto.

```text
carrito_spring/
├── .mvn/
├── data/                       # Base de datos local
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...             # Controladores, servicios, modelos y seguridad
│   │   └── resources/
│   │       ├── templates/      # Vistas Thymeleaf
│   │       ├── static/         # CSS, JavaScript e imágenes
│   │       └── application.properties
│   └── test/                   
├── pom.xml
├── mvnw
├── mvnw.cmd
└── LICENSE
```

## Funcionalidades

### Catálogo de productos

La aplicación permite consultar el catálogo y acceder a la información de los productos. Los usuarios pueden explorar los artículos disponibles y añadirlos al carrito. También existe una funcionalidad de edición de productos para modificar su información.

### Carrito de la compra

El carrito permite consultar los productos seleccionados y retirar artículos cuando sea necesario. De este modo, el usuario puede gestionar su selección desde la interfaz web.

### Administración

La aplicación incluye un área de administración para gestionar el catálogo y acceder a funcionalidades restringidas. Entre ellas se encuentran la creación, edición y eliminación de productos, así como la exportación de los datos a un archivo SQL.

## Rutas principales

| Método HTTP | Ruta | Funcionalidad |
|---|---|---|
| `GET` | `/` | Acceso a la página principal, si está configurada. |
| `GET` | `/login` | Mostrar el formulario de inicio de sesión. |
| `GET` | `/products` | Consultar el catálogo de productos. |
| `GET` | `/products/edit/{id}` | Mostrar el formulario de edición de un producto. |
| `POST` | `/products/save` | Guardar un producto nuevo o actualizar uno existente. |
| `POST` | `/products/delete/{id}` | Eliminar un producto del catálogo. |
| `GET` | `/cart` | Mostrar el carrito de la compra. |
| `POST` | `/cart/add/{id}` | Añadir un producto al carrito. |
| `POST` | `/cart/remove/{id}` | Retirar un producto del carrito. |
| `GET` | `/admin` | Acceder al área de administración. |
| `GET` | `/admin/export` | Exportar los productos a un archivo SQL. |



