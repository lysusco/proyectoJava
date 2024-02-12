CREATE TRIGGER C_CODE_PERIOD
BEFORE INSERT ON periodo
FOR EACH ROW
BEGIN
    -- Validate if exist period
    DECLARE VAL_DUP INT;

    SELECT COUNT(id_periodo) INTO VAL_DUP FROM periodo WHERE año = NEW.año AND semestre = NEW.semestre;

    IF VAL_DUP > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "The inserted period already exist!";
    END IF;

    -- Create period code
    SET NEW.codPeriodo = CONCAT(NEW.semestre, "-", LPAD(NEW.semestre,2,"0"));


END;


CREATE TRIGGER VAL_PERIOD
BEFORE INSERT ON tarifa
FOR EACH ROW

BEGIN
    DECLARE PERIOD_TODAY INT;
    DECLARE VALIDAT INT;
    DECLARE DATE_TODAY DATE;
    DECLARE DATE_MONTH DATE;
    DECLARE amount_found INT;
    SET DATE_TODAY = NOW();

    -- Validate current period

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;

    -- Validate if repeat fees

    SELECT DISTINCT COUNT(id_periodo) INTO amount_found FROM FEES WHERE id_periodo = NEW.id_periodo AND id_programa = NEW.id_programa;

    -- Validate that fees is on current date

    SELECT COUNT(id_periodo) INTO VALIDAT FROM periodo WHERE id_periodo = NEW.id_periodo AND año != DATE_FORMAT(DATE_TODAY, "%Y") OR id_periodo = NEW.id_periodo AND año = DATE_FORMAT(DATE_TODAY, "%Y") AND semestre != PERIOD_TODAY;

    IF amount_found > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't repeat fees per program in same period!";
    
    ELSEIF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't establish fees out up date";
    END IF;

END;


CREATE TRIGGER VAL_PERIOD_UPDT
BEFORE UPDATE ON tarifa
FOR EACH ROW

BEGIN
    DECLARE PERIOD_TODAY INT;
    DECLARE VALIDAT INT;
    DECLARE DATE_TODAY DATE;
    DECLARE DATE_MONTH DATE;
    DECLARE amount_found INT;
    SET DATE_TODAY = NOW();

    -- Validate current period

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;

    -- Validate if repeat fees

    SELECT DISTINCT COUNT(id_periodo) INTO amount_found FROM tarifa WHERE id_periodo = NEW.id_periodo AND id_programa = NEW.id_programa;

    -- Validate that fees is on current date

    SELECT COUNT(id_periodo) INTO VALIDAT FROM periodo WHERE id_periodo = NEW.id_periodo AND año != DATE_FORMAT(DATE_TODAY, "%Y") OR id_periodo = NEW.id_periodo AND año = DATE_FORMAT(DATE_TODAY, "%Y") AND semestre != PERIOD_TODAY;

    IF amount_found > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't repeat fees per program in same period!";
    
    ELSEIF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't establish fees out up date";
    END IF;

END;

CREATE TRIGGER VAL_SUBJECT
BEFORE INSERT ON asignatura
FOR EACH ROW
BEGIN

    DECLARE COD_SUB TEXT;
    DECLARE NAME_COURS TEXT;
    DECLARE COD_PERIOD TEXT;
    DECLARE VALIDAT INT;
    DECLARE DATE_TODAY DATE;
    DECLARE PERIOD_TODAY INT;
    SET DATE_TODAY = NOW();

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;

    SELECT nomAsignatura INTO NAME_COURS FROM curso WHERE id_curso = NEW.id_curso;
    SELECT codPeriodo INTO COD_PERIOD FROM periodo WHERE id_periodo = NEW.id_periodo AND semestre = PERIOD_TODAY AND año = DATE_FORMAT(DATE_TODAY, "%Y");
    
    IF COD_PERIOD IS NULL THEN
        SIGNAL SQLSTATE "46000" SET MESSAGE_TEXT = "No se puede crear una asignatura fuera de la fecha actual";
    END IF;

    SET COD_SUB = CONCAT("PR",LPAD(NEW.id_programa,2,"0"),"-CU",LPAD(NEW.id_curso,2,"0"),"-PE",COD_PERIOD,"-",NAME_COURS);

    SELECT COUNT(id_asignatura) INTO VALIDAT FROM SUBJECTS WHERE nomAsignatura = COD_SUB;

    IF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "46000" SET MESSAGE_TEXT = "Ya existe!";
    
    ELSE
        SET NEW.nomAsignatura = COD_SUB;
    END IF;

END;

CREATE TRIGGER VAL_horario
BEFORE INSERT ON horario
FOR EACH ROW
BEGIN

    DECLARE VALIDAT INT;
    DECLARE CAPACITY_SUBJ INT;
    DECLARE CAPACITY_ROOM INT;
    DECLARE MESSAGE_CAPACITY TEXT;

    IF NEW.horaIni < "00:00" OR NEW.horaFin >= "24:00" THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Rango de hora entre 00:00:00 y 23:59:59";
    END IF;

    SELECT cupoAsigantura INTO CAPACITY_SUBJ FROM asignatura WHERE id_asignatura = NEW.id_asignatura;
    SELECT cupoSalon INTO CAPACITY_ROOM FROM salon WHERE id_salon = NEW.id_salon;

    IF CAPACITY_SUBJ > CAPACITY_ROOM THEN
        SET MESSAGE_CAPACITY = CONCAT("La cantidad de estudiantes superan al cupo del salon en ", (CAPACITY_SUBJ - CAPACITY_ROOM), " estudiantes.");
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = MESSAGE_CAPACITY;
    END IF;

    SELECT COUNT(id_horario) INTO VALIDAT FROM horario WHERE id_asignatura = NEW.id_asignatura AND id_salon = NEW.id_salon AND dia = NEW.dia AND (NEW.horaIni BETWEEN horaIni AND SUBTIME(horaFin,"00:00:01")) OR id_asignatura = NEW.id_asignatura AND id_salon = NEW.id_salon AND dia = NEW.dia AND (NEW.horaFin BETWEEN horaIni AND SUBTIME(horaFin,"00:00:01"));

    IF NEW.horaFin < NEW.horaIni THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "La hora inicial no puede ser menor a la final, use hora militar.";
    END IF;
    IF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Éste horario no está disponible";
    END IF;

END;



CREATE TRIGGER VAL_TEACH
BEFORE INSERT ON horario
FOR EACH ROW
BEGIN

    DECLARE ID_TEACH INT;
    DECLARE CANT INT;
    DECLARE CANT_MPMH INT;
    DECLARE CANT_DPMHMS INT;
    DECLARE DATE_TODAY DATE;
    DECLARE PERIOD_TODAY INT;
    SET DATE_TODAY = NOW();

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;


    SELECT id_profesor INTO ID_TEACH FROM asignatura WHERE id_asignatura = new.id_asignatura;

    SELECT COUNT(SB.id_profesor) INTO CANT_MPMH FROM horario S
    INNER JOIN asignatura SB ON (S.id_asignatura = SB.id_asignatura)
    WHERE SB.id_profesor = ID_TEACH AND S.dia = NEW.dia AND (NEW.horaIni BETWEEN S.horaIni AND SUBTIME(S.horaFin,"00:00:01")) OR SB.id_profesor = ID_TEACH AND dia = NEW.dia AND (NEW.horaFin BETWEEN S.horaIni AND SUBTIME(S.horaFin,"00:00:01"));

    SELECT id_profesor INTO ID_TEACH FROM asignatura WHERE id_asignatura = new.id_asignatura;

    SELECT COUNT(SB.id_profesor) INTO CANT FROM `horario` S
    INNER JOIN `asignatura` SB ON (S.id_asignatura = SB.id_asignatura)
    WHERE SB.id_profesor = ID_TEACH AND S.dia = NEW.dia AND (SB.nomAsignatura LIKE CONCAT("%-PE",DATE_FORMAT(DATE_TODAY,"%Y"),"-",LPAD(PERIOD_TODAY,2,"0"))) AND (NEW.horaIni BETWEEN S.horaIni AND SUBTIME(S.horaFin,"00:00:01")) OR dia = NEW.dia AND (SB.nomAsignatura LIKE CONCAT("%-PE",DATE_FORMAT(DATE_TODAY,"%Y"),"-",LPAD(PERIOD_TODAY,2,"0"))) AND (NEW.horaFin BETWEEN S.horaIni AND SUBTIME(S.horaFin,"00:00:01"));

    IF CANT >= 1 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "COJA OFICIO QUE ESTE YA TIENE, SE CRUZAN LOS HORARIOS";
    END IF;

END;
