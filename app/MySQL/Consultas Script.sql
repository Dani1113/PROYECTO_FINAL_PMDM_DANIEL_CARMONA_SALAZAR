use tienda_videojuegos_android;

SELECT * FROM tienda_videojuegos_android.empleado;
SELECT * FROM tienda_videojuegos_android.venta;
SELECT * FROM tienda_videojuegos_android.videojuego;

SELECT ve.id_venta, ve.numero_venta, e.nombre_empleado, e.apellidos_empleado, e.domicilio_empleado, e.telefono_empleado, vi.título_videojuego, vi.pegi_videojuego, vi.genero_videojuego, vi.logo_videojuego 
FROM venta ve 
INNER JOIN empleado e 
INNER JOIN videojuego vi 
ON (e.id_empleado = ve.EMPLEADO_id_empleado AND vi.id_videojuego = ve.VIDEOJUEGO_id_videojuego);