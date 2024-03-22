##----------------------------------------------------------
##
## creare proceduri
##
##----------------------------------------------------------

DROP PROCEDURE IF EXISTS cautareDupaNume;
DELIMITER //
CREATE PROCEDURE cautareDupaNume (nume1 varchar(30), prenume1 varchar(30))
BEGIN
	SELECT* FROM utilizator WHERE nume = nume1 AND prenume = prenume1;
END;//

CALL cautareDupaNume('Pop', 'Andreea');

DROP PROCEDURE IF EXISTS filtrareDupaTip;
DELIMITER //
CREATE PROCEDURE filtrareDupaTip (tip1 varchar(30)) #tipul de utilizator
BEGIN
	SELECT * FROM utilizator WHERE tip = tip1;
END; //

CALL filtrareDupaTip('student');

DROP PROCEDURE IF EXISTS cautareCursDupaNume;
DELIMITER //
CREATE PROCEDURE cautareCursDupaNume (denumire_materie1 varchar(20)) #din tabela materie
BEGIN
	SELECT * FROM materie WHERE denumire_materie = denumire_materie1 AND curs = TRUE;
END; //

CALL cautareCursDupaNume('Matematica');

DROP PROCEDURE IF EXISTS studentiInscrisiCurs;
DELIMITER //
CREATE PROCEDURE studentiInscrisiCurs (tip_activitate1 varchar(20)) #din tabela activitate + inscriere
BEGIN
	SELECT u.nume, u.prenume
	FROM utilizator u
	INNER JOIN student s ON u.id_user = s.id_student
	INNER JOIN inscriere i ON s.id_student = i.student_id
	INNER JOIN materie m ON i.materie_id = m.id_materie
	INNER JOIN activitate a ON m.id_materie = a.materie_id
	WHERE a.tip_activitate = tip_activitate1;
END; //

#CALL studentiInscrisiCurs('curs');

DROP PROCEDURE IF EXISTS noteStudent;
DELIMITER //
CREATE PROCEDURE noteStudent (nume1 varchar(30), prenume1 varchar(30))
BEGIN
	SELECT note_activitate.*, materie.denumire_materie FROM note_activitate
	INNER JOIN materie ON note_activitate.materie_id = materie.id_materie
	WHERE note_activitate.nume = nume1 AND note_activitate.prenume = prenume1;
END; //

#CALL noteStudent('Buda', 'Andreea');

DROP PROCEDURE IF EXISTS noteStudentMaterie;
DELIMITER //
CREATE PROCEDURE noteStudentMaterie (nume1 varchar(30), prenume1 varchar(30), denumire_materie1 varchar(20))
BEGIN
	SELECT nota_finala FROM note_finale WHERE nume = nume1 AND prenume = prenume1 AND denumire_materie = denumire_materie1;
END; //

#CALL noteStudentMaterie('Buda', 'Andreea', 'Matematica');

DROP PROCEDURE IF EXISTS noteFinaleMaterie;  
DELIMITER //
CREATE PROCEDURE noteFinaleMaterie (nume_prof1 varchar(30), prenume_prof1 varchar(20), denumire_materie1 varchar(20))
BEGIN
	SELECT student.nume, student.prenume, note_activitate.nota FROM student
	INNER JOIN inscriere ON student.id_student = inscriere.student_id
	INNER JOIN materie ON inscriere.materie_id = materie.id_materie
	INNER JOIN profesor ON materie.profesor_id = profesor.id_profesor
	INNER JOIN note_activitate ON student.id_student = note_activitate.student_id
	WHERE profesor.nume = nume_prof1 AND profesor.prenume = prenume_prof1 AND materie.denumire_materie = denumire_materie1;
END; //

#CALL noteFinaleMaterie('Buda', 'Andreea', 'Matematica');

DROP PROCEDURE IF EXISTS  nrCursuriProfesori;
DELIMITER //
CREATE PROCEDURE nrCursuriProfesori (nume_prof1 varchar(30), prenume_prof1 varchar(20))
BEGIN
	SELECT COUNT(*) as nr_cursuri FROM materie
	INNER JOIN profesor ON materie.profesor_id = profesor.id_profesor
	WHERE profesor.nume = nume_prof1 AND profesor.prenume = prenume_prof1;
END; //

#CALL nrCursuriProfesori('Buda', 'Andreea');

DROP PROCEDURE IF EXISTS nrActivitatiProgramateGrup;
DELIMITER //
CREATE PROCEDURE nrActivitatiProgramateGrup (denumire_materie1 varchar(20))
BEGIN
	SELECT COUNT(*) as nr_activitati FROM grup_studiu
	INNER JOIN activitate_grup ON grup_studiu.id_grup = activitate_grup.grup_id
	WHERE materie.denumire_materie=denumire_materie1;
END; //

#CALL nrActivitatiProgramateGrup ('Matematica');

DROP PROCEDURE IF EXISTS studentiDinGrup;
DELIMITER //
CREATE PROCEDURE studentiDinGrup (denumire_materie1 varchar(20))
BEGIN
	SELECT nume, prenume FROM student 
    INNER JOIN grup_studiu ON student.grup_id = grup_studiu.id_grup
    WHERE materie.denumire_materie=denumire_materie1;
END; //

#CALL studentiDinGrup ('Matematica');

DROP PROCEDURE IF EXISTS mesajeGrup;
DELIMITER //
CREATE PROCEDURE mesajeGrup (denumire_materie1 varchar(20))
BEGIN
	SELECT continut FROM mesaj 
    INNER JOIN grup_studiu ON mesaj.grup_id = grup_studiu.id_grup
    WHERE materie.denumire_materie=denumire_materie1;
END; //

#CALL mesajeGrup ('Matematica');


##----------------------------------------------------------
##
## creare triggere
##
##----------------------------------------------------------

###########################################################################################################################DELETE

DROP TRIGGER IF EXISTS user_delete;
DELIMITER //
CREATE TRIGGER user_delete AFTER DELETE ON utilizator
FOR EACH ROW BEGIN
    DELETE FROM utilizator WHERE id_user = OLD.id_user;
    DELETE FROM autentificare WHERE id_user_autentificare = OLD.id_user;
    DELETE FROM profesor WHERE id_profesor = OLD.id_user;
    DELETE FROM student WHERE id_student = OLD.id_user;
    DELETE FROM administrator WHERE id_administrator = OLD.id_user;
    DELETE FROM inscriere WHERE student_id = OLD.id_user;
    DELETE FROM inscriere_grup WHERE student_id = OLD.id_user;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS profesor_delete;
DELIMITER //
CREATE TRIGGER profesor_delete AFTER DELETE ON utilizator
FOR EACH ROW BEGIN
  DELETE FROM profesor WHERE id_profesor = OLD.id_user;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS materie_delete;
DELIMITER //
CREATE TRIGGER  materie_delete AFTER DELETE ON materie
FOR EACH ROW BEGIN
	DELETE FROM materie WHERE id_materie = OLD.id_materie;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS grup_studiu_delete;
DELIMITER //
CREATE TRIGGER  grup_studiu_delete AFTER DELETE ON materie
FOR EACH ROW BEGIN
  DELETE FROM grup_studiu WHERE materie_id = OLD.id_materie;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS student_delete;
DELIMITER //
CREATE TRIGGER student_delete AFTER DELETE ON student
FOR EACH ROW BEGIN
    DELETE FROM student WHERE id_student = OLD.id_student;
END; //
DELIMITER ;


DROP TRIGGER IF EXISTS administrator_delete;
DELIMITER //
CREATE TRIGGER  administrator_delete AFTER DELETE ON utilizator
FOR EACH ROW BEGIN
    DELETE FROM administrator WHERE id_administrator = OLD.id_user;
END; //

DROP TRIGGER IF EXISTS inscriere_delete;
DELIMITER //
CREATE TRIGGER  inscriere_delete AFTER DELETE ON student
FOR EACH ROW BEGIN
  DELETE FROM inscriere WHERE student_id = OLD.id_student;
END; //

DROP TRIGGER IF EXISTS activitate_delete;
DELIMITER //
CREATE TRIGGER  activitate_delete AFTER DELETE ON materie
FOR EACH ROW BEGIN
  DELETE FROM activitate WHERE materie_id = OLD.id_materie;
END; //

DROP TRIGGER IF EXISTS inscriere_grup_delete;
DELIMITER //
CREATE TRIGGER inscriere_grup_delete AFTER DELETE ON student
FOR EACH ROW BEGIN
    DELETE FROM inscriere_grup WHERE student_id = OLD.id_student;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS mesaj_delete;
DELIMITER //
CREATE TRIGGER mesaj_delete AFTER DELETE ON mesaj
FOR EACH ROW BEGIN
  DELETE FROM mesaj WHERE id_mesaj = OLD.id_mesaj;
END; //

DROP TRIGGER IF EXISTS activitate_grup_delete;
DELIMITER //
CREATE TRIGGER  activitate_grup_delete AFTER DELETE ON activitate_grup
FOR EACH ROW BEGIN
	DELETE FROM activitate_grup WHERE id_activitate_grup = OLD.id_activitate_grup;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS note_activitate_delete;
DELIMITER //
CREATE TRIGGER  note_activitate_delete AFTER DELETE ON note_activitate
FOR EACH ROW BEGIN
  DELETE FROM note_activitate WHERE student_id = OLD.student_id AND activitate_id = OLD.activitate_id;
END; //

DROP TRIGGER IF EXISTS note_finale_delete;
DELIMITER //
CREATE TRIGGER note_finale_delete AFTER DELETE ON note_finale
FOR EACH ROW BEGIN
  DELETE FROM note_finale WHERE student_id = OLD.student_id AND materie_id = OLD.materie_id;
END; //
DELIMITER ;


############################################################################################################################UPDATE


DROP TRIGGER IF EXISTS user_update;
DELIMITER //
CREATE TRIGGER user_update AFTER UPDATE ON utilizator
FOR EACH ROW BEGIN
    UPDATE autentificare SET id_user_autentificare = NEW.id_user WHERE id_user_autentificare = OLD.id_user;
    UPDATE profesor SET id_profesor = NEW.id_user WHERE id_profesor = OLD.id_user;
    UPDATE student SET id_student = NEW.id_user WHERE id_student = OLD.id_user;
    UPDATE administrator SET id_administrator = NEW.id_user WHERE id_administrator = OLD.id_user;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS profesor_update;
DELIMITER //
CREATE TRIGGER profesor_update AFTER UPDATE ON utilizator
FOR EACH ROW BEGIN
  SET @id_profesor := (SELECT id_profesor FROM profesor WHERE id_profesor = NEW.id_user);
    SET @nr_min_ore := (SELECT nr_min_ore FROM profesor WHERE id_profesor = @id_profesor);
    SET @nr_maxim_ore := (SELECT nr_maxim_ore FROM profesor WHERE id_profesor = @id_profesor);
    SET @cursuri := (SELECT cursuri FROM profesor WHERE id_profesor = @id_profesor);
    SET @departament := (SELECT departament FROM profesor WHERE id_profesor = @id_profesor);

    UPDATE profesor SET 
        id_profesor = @id_profesor,
        nr_min_ore = @nr_min_ore,
        nr_maxim_ore = @nr_maxim_ore,
        cursuri = @cursuri,
        departament = @departament
    WHERE id_profesor = @id_profesor;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS materie_update;
DELIMITER //
CREATE TRIGGER materie_update AFTER UPDATE ON materie
FOR EACH ROW BEGIN
    SET @curs := (SELECT curs FROM materie WHERE id_materie = NEW.id_materie);
    SET @seminar := (SELECT seminar FROM materie WHERE id_materie = NEW.id_materie);
    SET @laborator := (SELECT laborator FROM materie WHERE id_materie = NEW.id_materie);

    IF @curs = 0 THEN
        SET @seminar := 0;
        SET @laborator := 0;
    END IF;

    UPDATE materie SET 
        curs = @curs,
        seminar = @seminar,
        laborator = @laborator
    WHERE id_materie = NEW.id_materie;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS grup_studiu_update;
DELIMITER //
CREATE TRIGGER  grup_studiu_update AFTER UPDATE ON materie
FOR EACH ROW BEGIN
    SET @materie_id := (SELECT materie_id FROM grup_studiu WHERE id_grup = NEW.id_grup);

    UPDATE grup_studiu SET 
        materie_id = @materie_id
    WHERE id_grup = NEW.id_grup;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS student_update;
DELIMITER //
CREATE TRIGGER student_update AFTER UPDATE ON student
FOR EACH ROW BEGIN
     SET @an_studiu := (SELECT an_studiu FROM student WHERE id_student = NEW.id_student);
    SET @grup_id := (SELECT grup_id FROM student WHERE id_student = NEW.id_student);

    UPDATE student SET 
        an_studiu = @an_studiu,
        grup_id = @grup_id
    WHERE id_student = NEW.id_student;
END; //
DELIMITER ;


DROP TRIGGER IF EXISTS administrator_update;
DELIMITER //
CREATE TRIGGER  administrator_update AFTER UPDATE ON utilizator
FOR EACH ROW BEGIN
    SET @tip := (SELECT tip FROM administrator WHERE id_administrator = NEW.id_administrator);

    UPDATE administrator SET 
        tip = @tip
    WHERE id_administrator = NEW.id_administrator;
END; //

DROP TRIGGER IF EXISTS inscriere_update;
DELIMITER //
CREATE TRIGGER  inscriere_update AFTER UPDATE ON student
FOR EACH ROW BEGIN
  SET @student_id := (SELECT id_student FROM student WHERE id_student = NEW.id_student);
    SET @materie_id := (SELECT materie_id FROM inscriere WHERE student_id = @student_id);

    UPDATE inscriere SET 
        student_id = @student_id,
        materie_id = @materie_id
    WHERE student_id = @student_id;
END; //

DROP TRIGGER IF EXISTS activitate_update;
DELIMITER //
CREATE TRIGGER  activitate_update AFTER UPDATE ON materie
FOR EACH ROW BEGIN
  SET @materie_id := (SELECT id_materie FROM materie WHERE id_materie = NEW.id_materie);
    SET @tip_activitate := (SELECT tip_activitate FROM activitate WHERE materie_id = @materie_id);
    SET @cologviu := (SELECT cologviu FROM activitate WHERE materie_id = @materie_id);
    SET @examen := (SELECT examen FROM activitate WHERE materie_id = @materie_id);
    SET @procent := (SELECT procent FROM activitate WHERE materie_id = @materie_id);
    SET @nr_maxim_participanti := (SELECT nr_maxim_participanti FROM activitate WHERE materie_id = @materie_id);

    UPDATE activitate SET 
        materie_id = @materie_id,
        tip_activitate = @tip_activitate,
        cologviu = @cologviu,
        examen = @examen,
        procent = @procent,
        nr_maxim_participanti = @nr_maxim_participanti
    WHERE materie_id = @materie_id;
END; //

DROP TRIGGER IF EXISTS inscriere_grup_update;
DELIMITER //
CREATE TRIGGER inscriere_grup_update AFTER UPDATE ON student
FOR EACH ROW BEGIN
    SET @student_id := (SELECT id_student FROM student WHERE id_student = NEW.id_student);
    SET @grup_id := (SELECT grup_id FROM inscriere_grup WHERE student_id = @student_id);

    UPDATE inscriere_grup SET 
        student_id = @student_id,
        grup_id = @grup_id
    WHERE student_id = @student_id;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS mesaj_update;
DELIMITER //
CREATE TRIGGER mesaj_update AFTER UPDATE ON mesaj
FOR EACH ROW BEGIN
	SET @mesaj_id := (SELECT mesaj_id FROM mesaj WHERE id_mesaj = NEW.id_mesaj);
    SET @expeditor_id := (SELECT expeditor_id FROM mesaj WHERE mesaj_id = @mesaj_id);
    SET @destinatar_id := (SELECT destinatar_id FROM mesaj WHERE mesaj_id = @mesaj_id);
    SET @mesaj_text := (SELECT mesaj_text FROM mesaj WHERE mesaj_id = @mesaj_id);
    SET @data_trimiterii := (SELECT data_trimiterii FROM mesaj WHERE mesaj_id = @mesaj_id);

    UPDATE mesaj SET 
        expeditor_id = @expeditor_id,
        destinatar_id = @destinatar_id,
        mesaj_text = @mesaj_text,
        data_trimiterii = @data_trimiterii
    WHERE id_mesaj = @mesaj_id;
END; //

DROP TRIGGER IF EXISTS activitate_grup_update;
DELIMITER //
CREATE TRIGGER  activitate_grup_update AFTER UPDATE ON activitate_grup
FOR EACH ROW BEGIN
	  SET @id_activitate_grup := (SELECT id_activitate_grup FROM activitate_grup WHERE id_activitate_grup = NEW.id_activitate_grup);
    SET @grup_id := (SELECT grup_id FROM activitate_grup WHERE id_activitate_grup = @id_activitate_grup);
    SET @activitate_id := (SELECT activitate_id FROM activitate_grup WHERE id_activitate_grup = @id_activitate_grup);
    SET @data_start := (SELECT data_start FROM activitate_grup WHERE id_activitate_grup = @id_activitate_grup);
    SET @data_stop := (SELECT data_stop FROM activitate_grup WHERE id_activitate_grup = @id_activitate_grup);

    UPDATE activitate_grup SET 
        id_activitate_grup = @id_activitate_grup,
        grup_id = @grup_id,
        activitate_id = @activitate_id,
        data_start = @data_start,
        data_stop = @data_stop
    WHERE id_activitate_grup = @id_activitate_grup;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS note_activitate_update;
DELIMITER //
CREATE TRIGGER  note_activitate_update AFTER UPDATE ON note_activitate
FOR EACH ROW BEGIN
    SET @student_id := (SELECT student_id FROM note_activitate WHERE id_note_activitate = @id_note_activitate);
    SET @activitate_id := (SELECT activitate_id FROM note_activitate WHERE id_note_activitate = @id_note_activitate);
    SET @nota := (SELECT nota FROM note_activitate WHERE id_note_activitate = @id_note_activitate);
    SET @observatii := (SELECT observatii FROM note_activitate WHERE id_note_activitate = @id_note_activitate);

    UPDATE note_activitate SET 
        student_id = @student_id,
        activitate_id = @activitate_id,
        nota = @nota,
        observatii = @observatii
    WHERE id_note_activitate = @id_note_activitate;
END; //
DELIMITER ;

DROP TRIGGER IF EXISTS note_finale_update;
DELIMITER //
CREATE TRIGGER note_finale_update AFTER UPDATE ON note_finale
FOR EACH ROW BEGIN
	UPDATE note_finale SET nota_finala = NEW.nota_finala WHERE student_id = NEW.student_id AND materie_id = NEW.materie_id;
END; //
DELIMITER ;