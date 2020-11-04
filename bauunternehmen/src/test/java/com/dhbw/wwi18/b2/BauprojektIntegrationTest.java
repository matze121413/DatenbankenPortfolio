package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauprojekt;
import com.dhbw.wwi18.b2.repositories.BauprojektRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauprojektIntegrationTest {

    private BauprojektRepository bauprojektRepository;

    @BeforeAll
    public void setup() {
        bauprojektRepository = new BauprojektRepository();
    }


    @AfterAll
    public void afterAll() {
        bauprojektRepository.closeConnection();
    }

    @Test
    public void createBauprojekt() {
        createNewBauprojekt();
    }

    @Test
    public void getBauprojekt() {
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Bauprojekt bauprojekt = createNewBauprojekt();
        Bauprojekt savedBauprojekt = bauprojektRepository.findById(bauprojekt.getBauprojektId());
        assertThat(savedBauprojekt, is(bauprojekt));
    }

    @Test
    public void updateBauprojekt(){
        Bauprojekt bauprojekt = createNewBauprojekt();
        bauprojekt.setEndDatum(20201013);
        Bauprojekt updatedBauprojekt = bauprojektRepository.update(bauprojekt);

        assertThat(updatedBauprojekt, is(bauprojekt));
    }

    @Test
    public void deleteBauprojekt(){
        Bauprojekt bauprojekt = createNewBauprojekt();
        bauprojektRepository.delete(bauprojekt);

        Bauprojekt deletedBauprojekt = bauprojektRepository.findById(bauprojekt.getBauprojektId());
        assertNull(deletedBauprojekt);

    }


    private Bauprojekt createNewBauprojekt(){
        Bauprojekt bauprojekt = new Bauprojekt();
        bauprojekt.setStartDatum(20200315);
        bauprojekt.setEndDatum(20230514);
        bauprojekt.setFrist(20231230);
        bauprojekt.setGewinn(500000);
        bauprojekt.setKosten(250000);

        Bauprojekt savedBauprojekt = bauprojektRepository.save(bauprojekt);
        assertNotNull(savedBauprojekt.getBauprojektId());
        assertThat(savedBauprojekt.getEndDatum(), is(20230514));
        return savedBauprojekt;
    }
}
