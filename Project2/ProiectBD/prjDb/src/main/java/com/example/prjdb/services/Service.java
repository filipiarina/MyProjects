package com.example.prjdb.services;

import com.example.prjdb.domain.*;
import com.example.prjdb.repository.Repo;
import com.example.prjdb.repository.dbRepo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

public class Service {
    private Repo<Integer, Activitate_grup> activitateGrupRepo;
    private Repo<Integer, Activitate> activitateRepo;
    private Repo<Integer, Administrator> administratorRepo;
    private Repo<Integer,Grup_studiu> grupStudiuRepo;
    private Repo<Tuple<Integer,Integer>,Inscriere_grup> inscriereGrupRepo;
    private Repo<Tuple<Integer,Integer>,Inscriere> inscriereRepo;
    private Repo<Integer,Materie> materieRepo;
    private Repo<Integer,Mesaj> mesajRepo;
    private Repo<Tuple<Integer,Integer>,Note_activitate> noteActivitateRepo;
    private Repo<Tuple<Integer,Integer>,Note_finale> noteFinaleRepo;
    private Repo<Integer,Profesor> profesorRepo;
    private Repo<Integer,Student> studentRepo;
    private Repo<Integer,Utilizator> utilizatorRepo;


    public Service(Repo<Integer, Activitate_grup> activitateGrupRepo, Repo<Integer, Activitate> activitateRepo,Repo<Integer, Administrator> administratorRepo, Repo<Integer, Grup_studiu> grupStudiuRepo, Repo<Tuple<Integer, Integer>, Inscriere_grup> inscriereGrupRepo, Repo<Tuple<Integer, Integer>, Inscriere> inscriereRepo, Repo<Integer, Materie> materieRepo, Repo<Integer, Mesaj> mesajRepo, Repo<Tuple<Integer, Integer>, Note_activitate> noteActivitateRepo, Repo<Tuple<Integer, Integer>, Note_finale> noteFinaleRepo, Repo<Integer, Profesor> profesorRepo, Repo<Integer, Student> studentRepo, Repo<Integer, Utilizator> utilizatorRepo) {
        this.activitateGrupRepo = activitateGrupRepo;
        this.activitateRepo = activitateRepo;
        this.administratorRepo = administratorRepo;
        this.grupStudiuRepo = grupStudiuRepo;
        this.inscriereGrupRepo = inscriereGrupRepo;
        this.inscriereRepo = inscriereRepo;
        this.materieRepo = materieRepo;
        this.mesajRepo = mesajRepo;
        this.noteActivitateRepo = noteActivitateRepo;
        this.noteFinaleRepo = noteFinaleRepo;
        this.profesorRepo = profesorRepo;
        this.studentRepo = studentRepo;
        this.utilizatorRepo = utilizatorRepo;
    }

    public int Login(String mail, String password) throws ClassNotFoundException {
        Iterable<Utilizator> users = this.utilizatorRepo.findAll();
        List<Utilizator> user= StreamSupport.stream(users.spliterator(), false).toList();
        List<Utilizator> result = user.stream().filter(c -> Objects.equals(c.email, mail)&&(Objects.equals(c.parola,password))).toList();
        if(!result.isEmpty())
            return result.get(0).id_user;
        else return 0;
    }

    public Repo<Integer, Activitate_grup> getActivitateGrupRepo() {
        return activitateGrupRepo;
    }

    public Repo<Integer, Activitate> getActivitateRepo() {
        return activitateRepo;
    }

    public Repo<Integer, Administrator> getAdministratorRepo() {
        return administratorRepo;
    }

    public Repo<Integer, Grup_studiu> getGrupStudiuRepo() {
        return grupStudiuRepo;
    }

    public Repo<Tuple<Integer, Integer>, Inscriere_grup> getInscriereGrupRepo() {
        return inscriereGrupRepo;
    }

    public Repo<Tuple<Integer, Integer>, Inscriere> getInscriereRepo() {
        return inscriereRepo;
    }

    public Repo<Integer, Materie> getMaterieRepo() {
        return materieRepo;
    }

    public Repo<Integer, Mesaj> getMesajRepo() {
        return mesajRepo;
    }

    public Repo<Tuple<Integer, Integer>, Note_activitate> getNoteActivitateRepo() {
        return noteActivitateRepo;
    }

    public Repo<Tuple<Integer, Integer>, Note_finale> getNoteFinaleRepo() {
        return noteFinaleRepo;
    }

    public Repo<Integer, Profesor> getProfesorRepo() {
        return profesorRepo;
    }

    public Repo<Integer, Student> getStudentRepo() {
        return studentRepo;
    }

    public Repo<Integer, Utilizator> getUtilizatorRepo() {
        return utilizatorRepo;
    }

    public Iterable<String> activitatiStudent(Integer id) throws ClassNotFoundException {
        Iterable<Inscriere> act = this.inscriereRepo.findAll();
        Iterable<Activitate> actt = this.activitateRepo.findAll();

        List<Inscriere> act2= StreamSupport.stream(act.spliterator(), false).toList();
        List<Inscriere> rez = act2.stream().filter(c -> Objects.equals(c.id.getLeft(), id)).toList();

        List<String> result = new ArrayList<String>();
        result.add("Discipline la care sunteti inscris:");
        rez.forEach(inscriere -> {
            try {
                result.add(
                        this.materieRepo.findOne(inscriere.getId().getRight()).get().denumire_materie);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    public Iterable<String> studentiGrup(Integer idGrupa) throws ClassNotFoundException {
        Iterable<Inscriere_grup> act = this.inscriereGrupRepo.findAll();

        List<Inscriere_grup> act2= StreamSupport.stream(act.spliterator(), false).toList();
        List<Inscriere_grup> rez = act2.stream().filter(c -> Objects.equals(c.id.getRight(), idGrupa)).toList();
        List<String> result = new ArrayList<String>();
        rez.forEach(inscriere -> {
            try {
                result.add(
                        this.utilizatorRepo.findOne(inscriere.getId().getLeft()).get().getNume() +" "+
                                this.utilizatorRepo.findOne(inscriere.getId().getLeft()).get().getPrenume());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    public Iterable<String> studentiNuGrup(Integer idGrupa) throws ClassNotFoundException {
        Iterable<Inscriere> act = this.inscriereRepo.findAll();
        Grup_studiu gr = this.grupStudiuRepo.findOne(idGrupa).get();

        List<Inscriere> act2= StreamSupport.stream(act.spliterator(), false).toList();

        List<String> rez = new ArrayList<String>();
        act2.forEach(inscriere -> {
            int id = inscriere.getId().getRight();
            if(Objects.equals(id, gr.getMaterie_id())) {
                try {
                    rez.add(this.utilizatorRepo.findOne(inscriere.getId().getLeft()).get().getNume()+" "+
                            this.utilizatorRepo.findOne(inscriere.getId().getLeft()).get().getPrenume());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Iterable<String> comparat = studentiGrup(idGrupa);
        List<String> comp = StreamSupport.stream(comparat.spliterator(), false).toList();

        List<String> result = new ArrayList<String>();
        result.add("Sugestii:");
        rez.forEach(inscriere -> {
            if(!comp.contains(inscriere))
                result.add(inscriere);
        });
        return result;
    }



    public Iterable<String> grupuriStudent(Integer id) throws ClassNotFoundException {
        Iterable<Inscriere_grup> act = this.inscriereGrupRepo.findAll();
        //Iterable<Grup_studiu> actt = this.grupStudiuRepo.findAll();

        List<Inscriere_grup> act2= StreamSupport.stream(act.spliterator(), false).toList();
        List<Inscriere_grup> rez = act2.stream().filter(c -> Objects.equals(c.id.getLeft(), id)).toList();

        List<String> result = new ArrayList<String>();
        rez.forEach(inscriere -> {
            try {
                result.add(
                        this.materieRepo.findOne(this.grupStudiuRepo.findOne(inscriere.getId().getRight()).get().materie_id).get().denumire_materie);
                result.add("Particianti:");
                Iterable<String> u = studentiGrup(inscriere.getId().getRight());
                u.forEach(result::add);

                Iterable<String> v = studentiNuGrup(inscriere.getId().getRight());
                v.forEach(result::add);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    public Iterable<String> noteStudent(Integer id) throws ClassNotFoundException {
        Iterable<Note_activitate> act = this.noteActivitateRepo.findAll();
        //Iterable<Grup_studiu> actt = this.grupStudiuRepo.findAll();

        List<Note_activitate> act2= StreamSupport.stream(act.spliterator(), false).toList();
        List<Note_activitate> rez = act2.stream().filter(c -> Objects.equals(c.id.getLeft(), id)).toList();

        List<String> result = new ArrayList<String>();
        result.add("Note activitati: ");
        rez.forEach(inscriere -> {
            try {
                result.add(
                        this.materieRepo.findOne(this.activitateRepo.findOne(inscriere.getId().getRight()).get().materie_id).get().denumire_materie+
                        "  "+this.activitateRepo.findOne(inscriere.getId().getRight()).get().tip_activitate + "  "+
                        Float.toString(inscriere.getNota()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        result.add("Note finale: ");

        Iterable<Note_finale> actt = this.noteFinaleRepo.findAll();
        //Iterable<Grup_studiu> actt = this.grupStudiuRepo.findAll();

        List<Note_finale> act22= StreamSupport.stream(actt.spliterator(), false).toList();
        List<Note_finale> rezz = act22.stream().filter(c -> Objects.equals(c.id.getLeft(), id)).toList();

        rezz.forEach(inscriere -> {
            try {
                result.add(
                        this.materieRepo.findOne(inscriere.getId().getRight()).get().denumire_materie+ "  "+
                                Float.toString(inscriere.getNota_finala()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return result;
    }

    public Iterable<String> Catalog(Integer id) throws ClassNotFoundException {
        Iterable<Note_finale> actt = this.noteFinaleRepo.findAll();
        Iterable<Materie> aaa = this.materieRepo.findAll();
        List<Materie> a= StreamSupport.stream(aaa.spliterator(), false).toList();
        List<Materie> aa = a.stream().filter(c -> Objects.equals(c.getProfesor_id(),id )).toList();


        List<Note_finale> act22= StreamSupport.stream(actt.spliterator(), false).toList();
        List<Note_finale> rezz = act22.stream().filter(c -> Objects.equals(c.id.getRight(),aa.get(0).getId_materie() )).toList();
        List<String> result = new ArrayList<String>();

        rezz.forEach(inscriere -> {
            try {
                result.add(
                        this.utilizatorRepo.findOne(inscriere.getId().getLeft()).get().getNume()+ " "+
                                this.utilizatorRepo.findOne(inscriere.getId().getLeft()).get().getPrenume()+ " "+
                                inscriere.getNota_finala());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    public Iterable<String> DatePersonaleProf(Integer id) throws ClassNotFoundException {
        List<String> result = new ArrayList<String>();
        Utilizator u = this.utilizatorRepo.findOne(id).get();
        Profesor p = this.profesorRepo.findOne(id).get();
        result.add("Nume: "+u.getNume()+" "+u.getPrenume());
        result.add("CNP: "+ u.getCNP());
        result.add("Adresa: "+u.getAdresa());
        result.add("E-mail: "+u.getEmail());
        result.add("Numar de telefon: "+u.getNr_telefon());
        result.add("Cont bancar: "+u.getCont_iban());
        result.add("Departament: "+p.getDepartament());
        result.add("Materii: "+ p.cursuri);

        return result;
    }

    public Iterable<String> getUsers(Integer id) throws ClassNotFoundException {
        Iterable<Utilizator> act = this.utilizatorRepo.findAll();

        List<Utilizator> act2= StreamSupport.stream(act.spliterator(), false).toList();
        List<Utilizator> rez = act2.stream().filter(c -> !Objects.equals(c.getTip(), "administrator")&&!Objects.equals(c.tip,"super administrator")).toList();

        List<String> result = new ArrayList<String>();
        rez.forEach(inscriere -> {
            result.add(
                        inscriere.getNume()+" "+inscriere.getPrenume()+" "+inscriere.getTip()
                );
        });
        return result;
    }

    public Iterable<String> getCursuri(Integer id) throws ClassNotFoundException {
        Iterable<Activitate> act = this.getActivitateRepo().findAll();

        List<Activitate> act2= StreamSupport.stream(act.spliterator(), false).toList();

        List<String> result = new ArrayList<String>();
        act2.forEach(inscriere -> {
            try {
                result.add(
                        this.materieRepo.findOne(inscriere.materie_id).get().denumire_materie+
                        "  "+inscriere.getTip_activitate()+"  "+inscriere.getMaterie_id()+"  "+inscriere.getProcent()+"  "+
                                "Numar maxim participanti: "+inscriere.getNr_maxim_participanti()
                );
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    public Iterable<String> getAllUsers(Integer id) throws ClassNotFoundException {
        Iterable<Utilizator> act = this.utilizatorRepo.findAll();

        List<Utilizator> act2= StreamSupport.stream(act.spliterator(), false).toList();
        List<Utilizator> rez = act2.stream().filter(c -> !Objects.equals(c.id_user, id)).toList();

        List<String> result = new ArrayList<String>();
        rez.forEach(inscriere -> {
            result.add(
                    inscriere.getNume()+" "+inscriere.getPrenume()+" "+inscriere.getTip()
            );
        });
        return result;
    }
}
