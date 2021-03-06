package org.example.service;


import org.example.domain.Nota;
import org.example.repository.NoteRepo;

public class ServiceNote {
    private NoteRepo rep;
    public ServiceNote(NoteRepo rep){this.rep=rep;}
    /**
     * Adauga Nota
     * Returneaza Nota adaugata*/
    public Nota add(Nota s, String fd){ return rep.save(s,fd);}

    public Iterable<Nota> all(){
        return rep.findAll();
    }

    public int getSize(){
        return rep.size();
    }
}

