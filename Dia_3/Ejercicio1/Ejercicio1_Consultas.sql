-- ###################################
-- ########### Consultas #############
-- ###################################


-- 1. Devuelve un listado con el código de oficina y la ciudad donde hay oficinas.

select codigo_oficina, ciudad from oficina;

-- 2. Devuelve un listado con la ciudad y el teléfono de las oficinas de España.

select ciudad, telefono from oficina where pais = 'España';

-- 3. Devuelve un listado con el nombre, apellidos y email de los empleados cuyo
-- jefe tiene un código de jefe igual a 7.

select nombre, apellido1, apellido2, email from empleado where codigo_jefe = 7;

-- 4. Devuelve el nombre del puesto, nombre, apellidos y email del jefe de la
-- empresa.

select nombre, apellido1, apellido2, email, puesto  from empleado where codigo_jefe is null ;

-- 5. Devuelve un listado con el nombre, apellidos y puesto de aquellos empleados que no sean representantes de ventas.

select nombre, apellido1, apellido2, puesto  from empleado where puesto != 'Representante Ventas';

-- 6. Devuelve un listado con el nombre de los todos los clientes españoles.

select nombre_cliente as Nombre, pais from cliente where pais = 'Spain';

-- 7. Devuelve un listado con los distintos estados por los que puede pasar un pedido.

select distinct estado from pedido ;

-- 8. Devuelve un listado con el código de cliente de aquellos clientes que
--    realizaron algún pago en 2008. Tenga en cuenta que deberá eliminar
--    aquellos códigos de cliente que aparezcan repetidos. Resuelva la consulta:

select distinct codigo_cliente, fecha_pago from pago WHERE EXTRACT(YEAR FROM fecha_pago) = 2008;

-- 9. Devuelve un listado con el código de pedido, código de cliente, fecha
--    esperada y fecha de entrega de los pedidos que no han sido entregados a
--    tiempo.

select codigo_pedido, codigo_cliente, fecha_esperada, fecha_entrega from pedido where fecha_entrega is null or fecha_entrega > fecha_esperada;

-- 10. Devuelve un listado con el código de pedido, código de cliente, fecha
--     esperada y fecha de entrega de los pedidos cuya fecha de entrega ha sido al
--     menos dos días antes de la fecha esperada.


select codigo_pedido, codigo_cliente, fecha_esperada, fecha_entrega
from pedido
where fecha_entrega <= fecha_esperada - INTERVAL '2 days';

-- 11. Devuelve un listado de todos los pedidos que fueron rechazados en 2009.

SELECT codigo_pedido, fecha_pedido
FROM pedido
WHERE estado = 'Rechazado'
AND EXTRACT(YEAR FROM fecha_pedido) = 2009;



-- 12. Devuelve un listado de todos los pedidos que han sido entregados en el mes de enero de cualquier año.

SELECT codigo_pedido, codigo_cliente, fecha_pedido, fecha_esperada, fecha_entrega
FROM pedido
WHERE EXTRACT(MONTH FROM fecha_entrega) = 1;


-- 13. Devuelve un listado con todos los pagos que se realizaron en el año 2008 mediante Paypal. Ordene el resultado de mayor a menor.

SELECT codigo_cliente, forma_pago, fecha_pago, total
FROM pago
WHERE EXTRACT(YEAR FROM fecha_pago) = 2008 AND forma_pago = 'PayPal'
ORDER BY total DESC;


-- 14. Devuelve un listado con todas las formas de pago que aparecen en la tabla pago. Tenga en cuenta que no deben aparecer formas de pago repetidas.

SELECT DISTINCT forma_pago
FROM pago;


-- 15. Devuelve un listado con todos los productos que pertenecen a la gama Ornamentales y que tienen más de 100 unidades en stock. El listado deberá estar ordenado por su precio de venta, mostrando en primer lugar los de mayor precio.

SELECT codigo_producto, nombre, cantidad_en_stock, precio_venta
FROM producto
WHERE gama = 'Ornamentales' AND cantidad_en_stock > 100
ORDER BY precio_venta DESC;


-- 16. Devuelve un listado con todos los clientes que sean de la ciudad de Madrid y cuyo representante de ventas tenga el código de empleado 11 o 30.

SELECT codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto
FROM cliente
WHERE ciudad = 'Madrid' AND codigo_empleado_rep_ventas IN (11, 30);