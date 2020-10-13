package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class MitarbeiterIntegrationTest {

    private static Validator validator;
    private static MitarbeiterRepository mitarbeiterRepository;

    @BeforeClass
    public static void setup() {
        mitarbeiterRepository = new MitarbeiterRepository();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }



    @AfterClass
    public static void done() {
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void createMitarbeiter(){
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setName("Matthias");
        mitarbeiter.setAlter(20);

        Mitarbeiter returnedMitarbeiter = mitarbeiterRepository.createEntity(mitarbeiter);

        assertThat(returnedMitarbeiter.getAlter(), is(20));
        assertThat(returnedMitarbeiter.getName(), is("Matthias"));
        assertNotNull(returnedMitarbeiter.getMitarbeiterId());
    }
}
