CREATE DATABASE proyectodb;

DROP DATABASE proyectodb;

USE proyectodb;

CREATE TABLE alumno (
    id INT AUTO_INCREMENT,
    tipoDoc VARCHAR(255) NOT NULL,
    numDocumento VARCHAR(255) NOT NULL,
    pNombre VARCHAR(255),
    sNombre VARCHAR(50),
    pApellido VARCHAR(50),
    sApellido VARCHAR(50),
    ciudadResidencia VARCHAR(50),
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    fNacimiento DATE NOT NULL,
    sexo VARCHAR(50),
    PRIMARY KEY(id),
    INDEX idx_alumno_documento (numDocumento)
);

CREATE TABLE profesor (
    id INT AUTO_INCREMENT,
    tipoDoc VARCHAR(255) NOT NULL,
    numDocumento VARCHAR(255) NOT NULL,
    pNombre VARCHAR(255),
    sNombre VARCHAR(50),
    pApellido VARCHAR(50),
    sApellido VARCHAR(50),
    ciudadResidencia VARCHAR(50),
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    fNacimiento DATE NOT NULL,
    sexo VARCHAR(50),
    PRIMARY KEY(id),
    INDEX idx_profesor_documento (numDocumento)
);

CREATE TABLE curso (
    id INT AUTO_INCREMENT,
    nomCurso VARCHAR(255) NOT NULL,
    guiaCatedra VARCHAR(255),
    PRIMARY KEY(id),
    INDEX idx_curso_nomCurso (nomCurso)
);

CREATE TABLE departamento (
    id INT AUTO_INCREMENT,
    nomDepartamento VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    INDEX idx_departamento_nomDepartamento (nomDepartamento)
);


CREATE TABLE asignatura(
    id INT AUTO_INCREMENT,
    nomAsignatura VARCHAR(255) NOT NULL,
    cupoAsignatrua VARCHAR(255) NOT NULL,
    creditos VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    INDEX idx_asignatura_nomAsignatura(nomAsignatura)
);

CREATE TABLE horario(
    id INT AUTO_INCREMENT,
    dia VARCHAR(255),
    horaIni VARCHAR(255),
    horaFin VARCHAR(255),
    PRIMARY KEY(id),
    INDEX idx_horario_dia(dia)
);

CREATE TABLE periodo(
    id INT AUTO_INCREMENT,
    codPeriodo VARCHAR(255),
    año VARCHAR(255),
    semestre VARCHAR(255),
    PRIMARY KEY(id),
    INDEX idx_periodo_codPeriodo(codPeriodo)
);

CREATE TABLE programa(
    id INT AUTO_INCREMENT,
    nomPrograma VARCHAR(255),
    nivel VARCHAR(255),
    PRIMARY KEY(id),
    INDEX idx_programa_nomPrograma(nomPrograma)
);

CREATE TABLE salon(
    id INT AUTO_INCREMENT,
    referenciaSalon VARCHAR(255),
    cupoSalon VARCHAR(255),
    ubicacion VARCHAR(255),
    PRIMARY KEY(id),
    INDEX idx_salon_referenciaSalon(referenciaSalon)
);

CREATE TABLE tarifa(
    id INT AUTO_INCREMENT,
    valorCredito VARCHAR(255),
    PRIMARY KEY(id),
    INDEX idx_tarifa_valorCredito(valorCredito)
);

SELECT * FROM alumno;