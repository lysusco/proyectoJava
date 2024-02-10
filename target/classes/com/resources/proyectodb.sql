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

DROP TABLE alumno;

DROP TABLE profesor;

SELECT * FROM alumno;