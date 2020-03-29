package org.example.service;

import org.example.domain.Student;
import org.example.domain.Teme;
import org.example.repository.StudentRepo;
import org.example.repository.TemeRepo;
import org.example.validator.StudentValidator;
import org.example.validator.TemeValidator;
import org.example.validator.ValidationException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ServiceTemeTest_WBT {

    private ServiceTeme serviceTeme;

    private TemeRepo temeRepo;

    @Before
    public void setUp() {
        temeRepo = new TemeRepo(new TemeValidator(),"teme1.xml");
        serviceTeme = new ServiceTeme(temeRepo);
    }

    @Test
    public void test_tc_invalidAssignment_addAssignment() {
        Teme t1 = new Teme(0, "", -10, -11);
        try {
            serviceTeme.add(t1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nID invalid\nDeadline invalid\nSaptamana in care tema a fost primita este invalida"));
        }
    }

    @Test
    public void test_tc_validAssignment_addAssignment() {
        Teme t1 = new Teme(1, "Tema4", 11, 12);
        try {
            serviceTeme.add(t1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nID invalid\nDeadline invalid\nSaptamana in care tema a fost primita este invalida"));
        }
    }
}
