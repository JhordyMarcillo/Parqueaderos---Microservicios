-- Natural 1: Juan Perez
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('11111111-1111-1111-1111-111111111111', 'NATURAL', '1710000001', 'Juan Perez', '0991111111', 'juan.perez@mail.com', 'Av. 6 de Diciembre', true, NOW());

INSERT INTO persona_natural (persona_id, apellido, genero, fecha_nacimiento)
VALUES ('11111111-1111-1111-1111-111111111111', 'Perez', 'M', '1990-01-15');


-- Natural 2: Maria Gomez
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('11111111-1111-1111-1111-111111111112', 'NATURAL', '1710000002', 'Maria Gomez', '0991111112', 'maria.gomez@mail.com', 'Calle Los Pinos', true, NOW());

INSERT INTO persona_natural (persona_id, apellido, genero, fecha_nacimiento)
VALUES ('11111111-1111-1111-1111-111111111112', 'Gomez', 'F', '1995-03-20');


-- Natural 3: Carlos Lopez
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('11111111-1111-1111-1111-111111111113', 'NATURAL', '1710000003', 'Carlos Lopez', '0991111113', 'carlos.lopez@mail.com', 'Av. Eloy Alfaro', true, NOW());

INSERT INTO persona_natural (persona_id, apellido, genero, fecha_nacimiento)
VALUES ('11111111-1111-1111-1111-111111111113', 'Lopez', 'M', '1988-11-05');


-- Natural 4: Ana Torres
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('11111111-1111-1111-1111-111111111114', 'NATURAL', '1710000004', 'Ana Torres', '0991111114', 'ana.torres@mail.com', 'Calle La Niña', true, NOW());

INSERT INTO persona_natural (persona_id, apellido, genero, fecha_nacimiento)
VALUES ('11111111-1111-1111-1111-111111111114', 'Torres', 'F', '2000-07-12');


-- Natural 5: Luis Diaz
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('11111111-1111-1111-1111-111111111115', 'NATURAL', '1710000005', 'Luis Diaz', '0991111115', 'luis.diaz@mail.com', 'Av. Republica', true, NOW());

INSERT INTO persona_natural (persona_id, apellido, genero, fecha_nacimiento)
VALUES ('11111111-1111-1111-1111-111111111115', 'Diaz', 'O', '1999-02-28');


-- Juridica 1: Tech Solutions
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('22222222-2222-2222-2222-222222222221', 'JURIDICA', '1790000001001', 'Tech Solutions S.A.', '022000001', 'info@tech.com', 'Parque Tecnologico', true, NOW());

INSERT INTO persona_juridica (persona_id, razon_social, actividad_economica, representante_legal, fecha_constitucion)
VALUES ('22222222-2222-2222-2222-222222222221', 'Tech Solutions S.A.', 'COMERCIO', 'Ing. Roberto Tech', '2010-01-01');


-- Juridica 2: Constructora Build
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('22222222-2222-2222-2222-222222222222', 'JURIDICA', '1790000002001', 'Constructora Build Cia. Ltda.', '022000002', 'ventas@build.com', 'Via a la Costa', true, NOW());

INSERT INTO persona_juridica (persona_id, razon_social, actividad_economica, representante_legal, fecha_constitucion)
VALUES ('22222222-2222-2222-2222-222222222222', 'Constructora Build Cia. Ltda.', 'CONSTRUCCION', 'Arq. Juana Muros', '2015-06-15');


-- Juridica 3: Agro Export
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('22222222-2222-2222-2222-222222222223', 'JURIDICA', '1790000003001', 'Agro Exportadora', '022000003', 'export@agro.com', 'Tabacundo', true, NOW());

INSERT INTO persona_juridica (persona_id, razon_social, actividad_economica, representante_legal, fecha_constitucion)
VALUES ('22222222-2222-2222-2222-222222222223', 'Agro Exportadora', 'AGRICULTURA', 'Sr. Pedro Campo', '2005-11-30');

-- Juridica 4: Transportes Rapidos
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('22222222-2222-2222-2222-222222222224', 'JURIDICA', '1790000004001', 'Transportes Rapidos S.A.', '022000004', 'logistica@rapidos.com', 'Panamericana Sur', true, NOW());

INSERT INTO persona_juridica (persona_id, razon_social, actividad_economica, representante_legal, fecha_constitucion)
VALUES ('22222222-2222-2222-2222-222222222224', 'Transportes Rapidos S.A.', 'TRANSPORTE', 'Sr. Luis Volante', '2018-02-14');

-- Juridica 5: Clinica Salud
INSERT INTO persona (id, tipo_persona, identificacion, nombre, telefono, email, direccion, activo, fecha_creación)
VALUES ('22222222-2222-2222-2222-222222222225', 'JURIDICA', '1790000005001', 'Clinica Salud Vital', '022000005', 'citas@salud.com', 'Av. Mariana de Jesus', true, NOW());

INSERT INTO persona_juridica (persona_id, razon_social, actividad_economica, representante_legal, fecha_constitucion)
VALUES ('22222222-2222-2222-2222-222222222225', 'Clinica Salud Vital', 'SANIDAD', 'Dra. Elena Cura', '2012-09-09');

-- Moto 1 (Dueño: Juan Perez - Natural)
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('33333333-3333-3333-3333-333333333331', 'MOTO', 150, 'MAA-1111', 'Yamaha', 'Azul', 'FZ-16', '2020', true, NOW(), '11111111-1111-1111-1111-111111111111');

INSERT INTO moto (vehiculo_id, tipo, tiene_casco)
VALUES ('33333333-3333-3333-3333-333333333331', 'NAKED', true);

-- Moto 2 (Dueño: Maria Gomez - Natural)
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('33333333-3333-3333-3333-333333333332', 'MOTO', 125, 'MBB-2222', 'Honda', 'Rojo', 'Elite', '2021', true, NOW(), '11111111-1111-1111-1111-111111111112');

INSERT INTO moto (vehiculo_id, tipo, tiene_casco)
VALUES ('33333333-3333-3333-3333-333333333332', 'SCOOTER', true);

-- Moto 3 (Dueño: Tech Solutions - Juridica)
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('33333333-3333-3333-3333-333333333333', 'MOTO', 200, 'MCC-3333', 'Suzuki', 'Blanco', 'DR200', '2023', true, NOW(), '22222222-2222-2222-2222-222222222221');

INSERT INTO moto (vehiculo_id, tipo, tiene_casco)
VALUES ('33333333-3333-3333-3333-333333333333', 'ENDURO', false);

-- Moto 4 (Dueño: Carlos Lopez - Natural)
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('33333333-3333-3333-3333-333333333334', 'MOTO', 1000, 'MDD-4444', 'BMW', 'Negro', 'S1000RR', '2024', true, NOW(), '11111111-1111-1111-1111-111111111113');

INSERT INTO moto (vehiculo_id, tipo, tiene_casco)
VALUES ('33333333-3333-3333-3333-333333333334', 'DEPORTIVA', true);

-- Moto 5 (Dueño: Transportes Rapidos - Juridica)
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('33333333-3333-3333-3333-333333333335', 'MOTO', 250, 'MEE-5555', 'Kawasaki', 'Verde', 'Versys', '2019', true, NOW(), '22222222-2222-2222-2222-222222222224');

INSERT INTO moto (vehiculo_id, tipo, tiene_casco)
VALUES ('33333333-3333-3333-3333-333333333335', 'TOURING', true);

-- Auto 1 (Dueño: Maria Gomez - Natural)
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('44444444-4444-4444-4444-444444444441', 'AUTO', 1600, 'PAA-1111', 'Kia', 'Gris', 'Rio', '2022', true, NOW(), '11111111-1111-1111-1111-111111111112');

INSERT INTO auto (vehiculo_id, tipo_auto, tipo_combustible, numero_puertas, capacidad_maletero_litros, capacidad_ocupantes, transmision)
VALUES ('44444444-4444-4444-4444-444444444441', 'SEDAN', 'GASOLINA', 4, 350.0, 5, 'MANUAL');

-- Auto 2 (Dueño: Constructora Build - Juridica) - CAMIONETA
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('44444444-4444-4444-4444-444444444442', 'AUTO', 3000, 'PBB-2222', 'Toyota', 'Blanco', 'Hilux', '2023', true, NOW(), '22222222-2222-2222-2222-222222222222');

INSERT INTO auto (vehiculo_id, tipo_auto, tipo_combustible, numero_puertas, capacidad_maletero_litros, capacidad_ocupantes, transmision)
VALUES ('44444444-4444-4444-4444-444444444442', 'CAMIONETA', 'DIESEL', 4, 1000.0, 5, 'MANUAL');

-- Auto 3 (Dueño: Ana Torres - Natural) - SUV
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('44444444-4444-4444-4444-444444444443', 'AUTO', 2000, 'PCC-3333', 'Mazda', 'Rojo', 'CX-5', '2024', true, NOW(), '11111111-1111-1111-1111-111111111114');

INSERT INTO auto (vehiculo_id, tipo_auto, tipo_combustible, numero_puertas, capacidad_maletero_litros, capacidad_ocupantes, transmision)
VALUES ('44444444-4444-4444-4444-444444444443', 'SUV', 'GASOLINA', 5, 500.0, 5, 'AUTOMATICA');

-- Auto 4 (Dueño: Agro Export - Juridica) - CAMIONETA
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('44444444-4444-4444-4444-444444444444', 'AUTO', 2500, 'PDD-4444', 'Chevrolet', 'Plata', 'D-Max', '2021', true, NOW(), '22222222-2222-2222-2222-222222222223');

INSERT INTO auto (vehiculo_id, tipo_auto, tipo_combustible, numero_puertas, capacidad_maletero_litros, capacidad_ocupantes, transmision)
VALUES ('44444444-4444-4444-4444-444444444444', 'CAMIONETA', 'DIESEL', 2, 1200.0, 2, 'MANUAL');

-- Auto 5 (Dueño: Juan Perez - Natural) - COMPACTO
INSERT INTO vehiculo (id, tipo_vehiculo, cilindraje, placa, marca, color, modelo, anio_fabricacion, activo, fecha_creacion, id_persona)
VALUES ('44444444-4444-4444-4444-444444444445', 'AUTO', 1200, 'PEE-5555', 'Hyundai', 'Azul', 'Grand i10', '2019', true, NOW(), '11111111-1111-1111-1111-111111111111');

INSERT INTO auto (vehiculo_id, tipo_auto, tipo_combustible, numero_puertas, capacidad_maletero_litros, capacidad_ocupantes, transmision)
VALUES ('44444444-4444-4444-4444-444444444445', 'HATCHBACK', 'GASOLINA', 5, 250.0, 5, 'MANUAL');