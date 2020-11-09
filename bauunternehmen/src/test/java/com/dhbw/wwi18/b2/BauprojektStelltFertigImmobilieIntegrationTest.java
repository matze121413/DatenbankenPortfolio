package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Immobilie;
import com.dhbw.wwi18.b2.model.Bauprojekt;
import com.dhbw.wwi18.b2.repositories.ImmobilieRepository;
import com.dhbw.wwi18.b2.repositories.BauprojektRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauprojektStelltFertigImmobilieIntegrationTest {


    private BauprojektRepository bauprojektRepository;
    private ImmobilieRepository immobilieRepository;

    @BeforeAll
    public void setup() {
        bauprojektRepository = new BauprojektRepository();
        immobilieRepository = new ImmobilieRepository();
    }

    @AfterAll
    public void done() {
        bauprojektRepository.closeConnection();
        immobilieRepository.closeConnection();
    }

    @Test
    public void setBauprojektInImmobilie() {
        Bauprojekt bauprojekt = createNewBauprojekt();

        Immobilie immobilie1 = createNewImmobilie();
        Immobilie immobilie2 = createNewImmobilie();

        immobilie1.setBauprojekt(bauprojekt);
        immobilie2.setBauprojekt(bauprojekt);

        Immobilie savedImmobilie1 = immobilieRepository.update(immobilie1);
        Immobilie savedImmobilie2 = immobilieRepository.update(immobilie2);

        assertThat(savedImmobilie1.getBauprojekt(), is(bauprojekt));
        assertThat(savedImmobilie2.getBauprojekt(), is(bauprojekt));
    }
    private Immobilie createNewImmobilie(){
        Immobilie immobilie = new Immobilie();
        immobilie.setFarbe("gelb");
        immobilie.setStatus("fertig");
        immobilie.setFlaeche(150);

        return immobilieRepository.save(immobilie);
    }

    private Bauprojekt createNewBauprojekt(){
        Bauprojekt bauprojekt = new Bauprojekt();
        bauprojekt.setStartDatum(20200315);
        bauprojekt.setEndDatum(20230514);
        bauprojekt.setFrist(20231230);
        bauprojekt.setGewinn(500000);
        bauprojekt.setKosten(250000);

        return bauprojektRepository.save(bauprojekt);
    }
}
