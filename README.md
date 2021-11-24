# Frutería Hidalgo
Aplicación que se encarga de gestionar una frutería para dar de alta a clientes y poder gestionar en forma de tickets la entrada y salida de cada fruta de la tienda.

## Base de datos

En la siguiente imagen se muestra el diagrama entidad-relación de la base de datos usada en esta aplicación:
![Modelo entidad-relacional](/src/img/Diagrama.png)

Para ello voy a comentar a continuación acerca de cada entidad creada:
* Fruta: tipo de fruta disponible para la venta
* Persona: según el tipo de rol puede ser administrador(puede incluir fruta nueva) o comprador(solo puede retirar fruta)
* Ticket: ticket generado en una transacción de compra de fruta.
* FrutasTicket: es una tabla débil, generada principalmente para gestionar la cantidad de frutas que se han comprado en un pedido
