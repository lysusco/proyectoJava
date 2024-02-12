CREATE DATABASE proyectodb;

DROP DATABASE proyectodb;

USE proyectodb;
CREATE TABLE departamento (
    id_departamento INT AUTO_INCREMENT,
    nomDepartamento VARCHAR(255) NOT NULL,
    PRIMARY KEY(id_departamento), 
    INDEX idx_departamento_nomDepartamento (nomDepartamento)
);

CREATE TABLE profesor (
    id_profesor INT AUTO_INCREMENT,
    tipoDoc VARCHAR(255),
    numDocumento VARCHAR(255),
    pNombre VARCHAR(255),
    sNombre VARCHAR(50),
    pApellido VARCHAR(50),
    sApellido VARCHAR(50),
    ciudadResidencia VARCHAR(50),
    direccion VARCHAR(255),
    telefono VARCHAR(255),
    fNacimiento DATE,
    sexo VARCHAR(50),
    id_departamento INT, 
    PRIMARY KEY(id_profesor),
    FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento),  
    INDEX idx_profesor_documento (numDocumento)
);

CREATE TABLE alumno (
    id_alumno INT AUTO_INCREMENT,
    tipoDoc VARCHAR(255),
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
    PRIMARY KEY(id_alumno),
    INDEX idx_alumno_documento (numDocumento)
);

CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT,
    nomCurso VARCHAR(255) NOT NULL,
    guiaCatedra VARCHAR(255),
    PRIMARY KEY(id_curso),
    INDEX idx_curso_nomCurso (nomCurso)
);


CREATE TABLE periodo(
    id_periodo INT AUTO_INCREMENT,
    codPeriodo VARCHAR(255),
    año VARCHAR(255),
    semestre VARCHAR(255),
    PRIMARY KEY(id_periodo),
    INDEX idx_periodo_codPeriodo(codPeriodo)
);

CREATE TABLE programa(
    id_programa INT AUTO_INCREMENT,
    nomPrograma VARCHAR(255),
    nivel VARCHAR(255),
    PRIMARY KEY(id_programa),
    INDEX idx_programa_nomPrograma(nomPrograma)
);

CREATE TABLE asignatura(
    id_asignatura INT AUTO_INCREMENT,
    nomAsignatura VARCHAR(255) NOT NULL,
    cupoAsignatura VARCHAR(255) NOT NULL,
    creditos VARCHAR(255) NOT NULL,
    id_periodo INT,
    id_curso INT,
    id_programa INT,
    id_profesor INT,
    PRIMARY KEY(id_asignatura),
    FOREIGN KEY(id_periodo) REFERENCES periodo(id_periodo),
    FOREIGN KEY(id_curso) REFERENCES curso(id_curso),
    FOREIGN KEY(id_programa) REFERENCES programa(id_programa),
    FOREIGN KEY(id_profesor) REFERENCES profesor(id_profesor)
);



CREATE TABLE tarifa(
    id_tarifa INT AUTO_INCREMENT,
    valorCredito VARCHAR(255),
    id_programa INT,
    id_periodo INT,
    PRIMARY KEY(id_tarifa),
    FOREIGN KEY(id_programa)REFERENCES programa(id_programa),
    FOREIGN KEY(id_periodo)REFERENCES periodo(id_periodo),
    INDEX idx_tarifa_valorCredito(valorCredito)
);


CREATE TABLE edificio(
    id_edificio INT AUTO_INCREMENT,
    referencia VARCHAR(255),
    PRIMARY KEY(id_edificio),
    INDEX idx_edificio_referencia(referencia)
);

CREATE TABLE piso(
    id_piso INT AUTO_INCREMENT,
    id_edificio INT,
    PRIMARY KEY(id_piso),
    FOREIGN KEY(id_edificio)REFERENCES edificio(id_edificio)
);

CREATE TABLE salon(
    id_salon INT AUTO_INCREMENT,
    referenciaSalon VARCHAR(255),
    cupoSalon VARCHAR(255),
    ubicacion VARCHAR(255),
    id_piso INT,
    PRIMARY KEY(id_salon),
    FOREIGN KEY(id_piso)REFERENCES piso(id_piso),
    INDEX idx_salon_referenciaSalon(referenciaSalon)
);


CREATE TABLE horario(
    id_horario INT AUTO_INCREMENT,
    dia VARCHAR(255),
    horaIni VARCHAR(255),
    horaFin VARCHAR(255),
    id_salon INT,
    id_asignatura INT,
    PRIMARY KEY(id_horario),
    FOREIGN KEY(id_salon)REFERENCES salon(id_salon),
    FOREIGN KEY(id_asignatura)REFERENCES asignatura(id_asignatura),
    INDEX idx_horario_dia(dia)
);

CREATE TABLE matricula(
    id_horario INT,
    id_alumno INT,
    FOREIGN KEY(id_horario)REFERENCES horario(id_horario),
    FOREIGN KEY(id_alumno)REFERENCES alumno(id_alumno)
);
