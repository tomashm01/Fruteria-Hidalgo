# Frutería Hidalgo
Aplicación que se encarga de gestionar una frutería para dar de alta a clientes y poder gestionar en forma de tickets la entrada y salida de cada fruta de la tienda.

## Base de datos

En la siguiente imagen se muestra el diagrama entidad-relación de la base de datos usada en esta aplicación:
![Modelo entidad-relacional](/src/img/Diagrama.png)

Para ello voy a comentar a continuación acerca de cada entidad creada:
* Fruta: tipo de fruta disponible para la venta
* Persona: según el tipo de rol tiene permiso para realizar una tarea determinada. Si es comprador solo puede comprar fruta, si es vendedor solamente puede vender fruta al propietario y si es propietario puede realizar la alta y baja tanto de propietarios como vendedores y compradores.
* Ticket: ticket generado en una transacción de compra-venta de fruta.
