# Frutería Hidalgo
Aplicación que se encarga de gestionar una frutería para dar de alta a clientes y poder gestionar en forma de tickets la entrada y salida de cada fruta de la tienda.

## Requerimientos para el despliegue de la app

Se necesita tener instalado **JAVA**, **MYSQL** y un **SERVIDOR WEB** y se recomienda **GIT** en el sistema operativo que se desee usar la aplicación. 

Para usar mysql y un servidor web a la vez, se recomienda instalar [XAMPP](https://www.apachefriends.org/es/download.html), que integra MYSQL y APACHE (servidor web) en una app a la vez. 

Para instalar java se recomienda instalar la última versión en 
su [web oficial](https://www.java.com/es/download/ie_manual.jsp). 

Se recomienda la instalación de [git](https://git-scm.com/book/es/v2/Inicio---Sobre-el-Control-de-Versiones-Instalaci%C3%B3n-de-Git) para poder actualizar la app más facilmente y descargarsela con el comando:
```
git clone https://github.com/tomashm01/Fruteria-Hidalgo.git
```

## Despliegue de la app 

Una vez instalado todo, se recomienda crearse una carpeta en algun directorio de su disco local y ejecutar el comando para clonar el repositorio(o pegar el contenido de este aquí).

Dentro de la carpeta hay dos archivos que son importantes usar:

- Fruteria_Hidalgo.sql: script que debe ser importado a nuestro gestor de BBDD. Para ello hay que activar previamente XAMPP e importar dicho script en [este enlace](localhost/phpmyadmin).
- fruteriaHidalgo.jar: ejecutable java que contiene todo el código fuente listo para ejecutar. Para ello nos dirigimos al directorio de este archivo y ejecutamos:
```
java -jar fruteriaHidalgo.jar
```

## Base de datos

En la siguiente imagen se muestra el diagrama entidad-relación de la base de datos usada en esta aplicación:

![Modelo entidad-relacional](/src/img/Diagrama.png)

Para ello voy a comentar a continuación acerca de cada entidad creada:
* Fruta: tipo de fruta disponible para la venta
* Persona: según el tipo de rol puede ser administrador(puede incluir fruta nueva) o comprador(solo puede retirar fruta)
* Ticket: ticket generado en una transacción de compra de fruta.
* FrutasTicket: es una tabla débil, generada principalmente para gestionar la cantidad de frutas que se han comprado en un pedido

## Funcionamiento de la app

La app contiene un menu de identificación de usuario(podemos iniciar sesión o registrarnos en la BBDD).

Una vez registrados, según el rol que tengamos(Administrador o comprador) tendremos acceso a un menu determinado:

- Menu comprador: aquí el usuario solo puede añadir frutas al carrito y mostrar las frutas disponibles con su cantidad y precio.

- Menu administrador: aquí el usuario administrador puede realizar diversas operaciones que no puede realizar el comprador(como crear usuarios administrador, modificar frutas,eliminar usuarios...)
