INSERT INTO departamento (nomDepartamento) 
VALUES 
    ('Departamento de Ingeniería Civil'),
    ('Departamento de Ingeniería Eléctrica'),
    ('Departamento de Ingeniería de Sistemas'),
    ('Departamento de Ingeniería Mecánica'),
    ('Departamento de Ingeniería Química'),
    ('Departamento de Ciencias Sociales'),
    ('Departamento de Ciencias Naturales'),
    ('Departamento de Ciencias Económicas'),
    ('Departamento de Artes y Humanidades'),
    ('Departamento de Ciencias de la Salud');


INSERT INTO profesor (tipoDoc, numDocumento, pNombre, sNombre, pApellido, sApellido, ciudadResidencia, direccion, telefono, fNacimiento, sexo, id_departamento)
VALUES 
    ('CC', '123456789', 'Juan', '', 'Pérez', 'Gómez', 'Ciudad A', 'Calle 123', '123456789', '1990-01-01', 'Masculino', 1),
    ('CC', '987654321', 'María', '', 'González', 'López', 'Ciudad B', 'Carrera 456', '987654321', '1995-05-05', 'Femenino', 2),
    ('TI', '456789123', 'Pedro', 'José', 'Martínez', 'Díaz', 'Ciudad C', 'Avenida 789', '456789123', '1985-10-15', 'Masculino', 3),
    ('CE', '789123456', 'Ana', 'Isabel', 'Rodríguez', 'Sánchez', 'Ciudad D', 'Calle 987', '789123456', '1980-07-20', 'Femenino', 4),
    ('CC', '159753468', 'Carlos', '', 'Fernández', 'Gutiérrez', 'Ciudad E', 'Calle 654', '159753468', '1988-03-30', 'Masculino', 5);

INSERT INTO alumno (tipoDoc, numDocumento, pNombre, sNombre, pApellido, sApellido, ciudadResidencia, direccion, telefono, fNacimiento, sexo)
VALUES 
    ('CC', '111111111', 'Pedro', '', 'Sánchez', 'García', 'Ciudad A', 'Carrera 789', '111111111', '2000-10-10', 'Masculino'),
    ('CC', '222222222', 'Ana', '', 'Martínez', 'López', 'Ciudad B', 'Calle 123', '222222222', '2001-12-12', 'Femenino'),
    ('TI', '333333333', 'Carlos', '', 'González', 'Pérez', 'Ciudad C', 'Avenida 456', '333333333', '2002-05-15', 'Masculino'),
    ('CE', '444444444', 'María', 'Isabel', 'Hernández', 'Fernández', 'Ciudad D', 'Calle 789', '444444444', '2003-08-20', 'Femenino'),
    ('CC', '555555555', 'Jorge', 'Andrés', 'Rodríguez', 'Sánchez', 'Ciudad E', 'Carrera 321', '555555555', '2004-03-30', 'Masculino');

INSERT INTO curso (nomCurso, guiaCatedra) 
VALUES 
    ('Curso 1', 'Guía 1'), 
    ('Curso 2', 'Guía 2'), 
    ('Curso 3', 'Guía 3'),
    ('Curso 4', 'Guía 4'),
    ('Curso 5', 'Guía 5');


INSERT INTO periodo (codPeriodo, año, semestre) 
VALUES 
    ('P001', '2023', 'Semestre 1'), 
    ('P002', '2023', 'Semestre 2'), 
    ('P003', '2024', 'Semestre 1'),
    ('P004', '2024', 'Semestre 2'),
    ('P005', '2025', 'Semestre 1');

INSERT INTO programa (nomPrograma, nivel) 
VALUES 
    ('Programa 1', 'Nivel 1'), 
    ('Programa 2', 'Nivel 2'), 
    ('Programa 3', 'Nivel 3'),
    ('Programa 4', 'Nivel 1'),
    ('Programa 5', 'Nivel 2');

INSERT INTO asignatura (nomAsignatura, cupoAsignatura, creditos, id_periodo, id_curso, id_programa, id_profesor)
VALUES 
    ('Asignatura 1', '25', '3', 1, 1, 1, 1),
    ('Asignatura 2', '30', '4', 2, 2, 2, 2),
    ('Asignatura 3', '20', '5', 3, 3, 3, 2),
    ('Asignatura 4', '22', '4', 1, 1, 1, 2),
    ('Asignatura 5', '28', '3', 2, 2, 2, 1);


INSERT INTO tarifa (valorCredito, id_programa, id_periodo) 
VALUES 
    ('10000', 1, 1),
    ('15000', 2, 2),
    ('20000', 3, 3),
    ('12000', 1, 2),
    ('18000', 2, 3);

INSERT INTO edificio (referencia) 
VALUES 
    ('Edificio 1'),
    ('Edificio 2'),
    ('Edificio 3'),
    ('Edificio 4'),
    ('Edificio 5');

INSERT INTO piso (id_edificio) 
VALUES 
    (1),
    (2),
    (3),
    (4),
    (5);

INSERT INTO salon (referenciaSalon, cupoSalon, ubicacion, id_piso) 
VALUES 
    ('Salon 101', '30', 'Piso 1', 1), 
    ('Salon 201', '35', 'Piso 2', 2), 
    ('Salon 301', '40', 'Piso 3', 3),
    ('Salon 401', '45', 'Piso 4', 4),
    ('Salon 501', '50', 'Piso 5', 5);

INSERT INTO salon (referenciaSalon, cupoSalon, ubicacion, id_piso) 
VALUES 
    ('Salon 102', '25', 'Piso 1', 1), 
    ('Salon 202', '30', 'Piso 2', 2), 
    ('Salon 302', '35', 'Piso 3', 3),
    ('Salon 402', '40', 'Piso 4', 4),
    ('Salon 502', '45', 'Piso 5', 5),
    ('Salon 103', '30', 'Piso 1', 1),
    ('Salon 203', '35', 'Piso 2', 2),
    ('Salon 303', '40', 'Piso 3', 3),
    ('Salon 403', '45', 'Piso 4', 4),
    ('Salon 503', '50', 'Piso 5', 5);

-- Asumiendo que los IDs de salon y asignatura ya existen en sus respectivas tablas
INSERT INTO horario (dia, horaIni, horaFin, id_salon, id_asignatura)
VALUES 
    ('Jueves', '08:00', '10:00', 4, 1),
    ('Viernes', '14:00', '16:00', 5, 2),
    ('Sábado', '10:00', '12:00', 3, 3);


INSERT INTO matricula (id_horario, id_alumno) 
VALUES 
    (1, 2), 
    (3, 3), 
    (2, 1),
    (1, 3),
    (3, 1),
    (2, 2),
    (1, 3);
