use tienda_videojuegos_android;

SELECT * FROM tienda_videojuegos_android.empleado;
SELECT * FROM tienda_videojuegos_android.venta;
SELECT * FROM tienda_videojuegos_android.videojuego;

SELECT ve.id_venta, ve.numero_venta, e.nombre_empleado, e.apellidos_empleado, e.domicilio_empleado, e.telefono_empleado, vi.título_videojuego, vi.pegi_videojuego, vi.genero_videojuego, vi.logo_videojuego 
FROM venta ve 
INNER JOIN empleado e 
INNER JOIN videojuego vi 
ON (e.id_empleado = ve.EMPLEADO_id_empleado AND vi.id_videojuego = ve.VIDEOJUEGO_id_videojuego);

# INSERT INTO empleado (nombre_empleado, apellidos_empleado, domicilio_empleado, telefono_empleado) VALUES ("Jose Luis", "Jiménez González", "C/PC, Nº7", "984631152");
# INSERT INTO videojuego (título_videojuego, pegi_videojuego, genero_videojuego, logo_videojuego) VALUES ("FF7", 12, "RPG", 0);
# INSERT INTO venta (numero_venta, EMPLEADO_id_empleado, VIDEOJUEGO_id_videojuego) VALUES (80, , );

# SELECT DISTINCT * FROM empleado e INNER JOIN videojuego ve ON (e.id_empleado AND ve.id_videojuego);