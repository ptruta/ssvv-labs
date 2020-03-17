package org.example.repository;

import org.example.domain.Student;
import org.example.validator.StudentValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentRepoTest_BBT {

    StudentRepo studentRepo;

    @Before
    public void setUp() {
        studentRepo = new StudentRepo(new StudentValidator(),"studenti.xml");
    }

    @Test
    public void test_tc_1_addStudent(){
        Student s = new Student("3", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        studentRepo.save(s);
        Assert.assertEquals(java.util.Optional.of(studentRepo.size()), java.util.Optional.of(3));
    }

    @Test
    public void test_tc_2_addStudent() {
        Student s1 = new Student("4", "Cardas Andra", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        Student s2 = new Student("5", "Cardas Andra", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        studentRepo.save(s1);
        studentRepo.save(s2);
        Assert.assertEquals(java.util.Optional.ofNullable(studentRepo.size()), java.util.Optional.ofNullable(3));
    }
}
