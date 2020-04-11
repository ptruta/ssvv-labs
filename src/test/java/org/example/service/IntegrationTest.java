package org.example.service;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Teme;
import org.example.repository.NoteRepo;
import org.example.repository.StudentRepo;
import org.example.repository.TemeRepo;
import org.example.validator.NotaValidator;
import org.example.validator.StudentValidator;
import org.example.validator.TemeValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;

public class IntegrationTest {
    private ServiceNote serviceNote;
    private ServiceStudent serviceStudent;
    private ServiceTeme serviceTeme;

    @Before
    public void setUp() {
        //initiate proper validators
        StudentValidator studentValidator = new StudentValidator();
        TemeValidator assignmentValidator = new TemeValidator();
        NotaValidator gradeValidator = new NotaValidator();

        //use in memory repositories to avoid the need for clean-up and the possibility for conflicts
        StudentRepo studentRepo = new StudentRepo(studentValidator,"studenti.xml");
        TemeRepo assignmentRepo = new TemeRepo(assignmentValidator,"teme1.xml");
        NoteRepo gradeRepo = new NoteRepo(gradeValidator);

        serviceStudent = new ServiceStudent(studentRepo);
        serviceTeme = new ServiceTeme(assignmentRepo);
        serviceNote = new ServiceNote(gradeRepo);
    }

    // BIG BANG APPROACH

    @Test
    public void bigBang() {
        serviceNote.add(new Nota(new AbstractMap.SimpleEntry<String, Integer>("Dada", 1),new Student("11", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"),
                new Teme(1, "tema 4", 3, 4),10, 10),"23");
        serviceStudent.add(new Student("11", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
        serviceTeme.add(new Teme(1, "tema5", 3, 4));
    }

    @Test
    public void addValidStudentCheckReturnValueTest() {
        serviceStudent.add(new Student("12", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
        Assert.assertEquals(java.util.Optional.of(serviceStudent.getSize()), java.util.Optional.of(5));
    }

    @Test
    public void addValidAssignmentCheckReturnValueTest() {
        serviceTeme.add(new Teme(1, "tema5", 3, 4));
        Assert.assertEquals(java.util.Optional.of(serviceTeme.getSize()), java.util.Optional.of(2));
    }

    @Test
    public void addValidGradeCheckReturnValueTest() {
        serviceNote.add(new Nota(new AbstractMap.SimpleEntry<String, Integer>("Dada", 1),new Student("11", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"),
                new Teme(1, "tema 4", 3, 4),10, 10),"23");
        Assert.assertEquals(java.util.Optional.of(serviceNote.getSize()), java.util.Optional.of(1));
    }
}
