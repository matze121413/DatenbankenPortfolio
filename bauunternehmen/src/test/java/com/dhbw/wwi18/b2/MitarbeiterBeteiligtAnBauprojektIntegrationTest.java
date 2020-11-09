package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauprojekt;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.BauprojektRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MitarbeiterBeteiligtAnBauprojektIntegrationTest {

    private BauprojektRepository bauprojektRepository;
    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        bauprojektRepository = new BauprojektRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public void done() {
        bauprojektRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void saveMitarbeiterList() {
        Bauprojekt bauprojekt1 = createNewBauprojekt();
        Bauprojekt bauprojekt2 = createNewBauprojekt();

        Mitarbeiter mitarbeiter = createNewMitarbeiter();


        List<Bauprojekt> bauprojektList = Arrays.asList(bauprojekt1, bauprojekt2);
        mitarbeiter.setBauprojektList(bauprojektList);

        Mitarbeiter savedMitarbeiter = mitarbeiterRepository.update(mitarbeiter);
        Bauprojekt savedBauprojekt1 = bauprojektRepository.findById(bauprojekt1.getBauprojektId());
        Bauprojekt savedBauprojekt2 = bauprojektRepository.findById(bauprojekt2.getBauprojektId());

        assertThat(savedMitarbeiter.getBauprojektList().size(), is(2));
        assertThat(savedBauprojekt1.getMitarbeiterList().size(), is(1));
        assertThat(savedBauprojekt2.getMitarbeiterList().size(), is(1));
    }

    @Test
    public void saveBauprojektList(){
        Bauprojekt bauprojekt = createNewBauprojekt();

        Mitarbeiter mitarbeiter1 = createNewMitarbeiter();
        Mitarbeiter mitarbeiter2 = createNewMitarbeiter();


        List<Mitarbeiter> mitarbeiterList = Arrays.asList(mitarbeiter1, mitarbeiter2);
        bauprojekt.setMitarbeiterList(mitarbeiterList);

        Bauprojekt savedBauprojekt = bauprojektRepository.update(bauprojekt);
        Mitarbeiter savedMitarbeiter1 = mitarbeiterRepository.findById(mitarbeiter1.getMitarbeiterId());
        Mitarbeiter savedMitarbeiter2 = mitarbeiterRepository.findById(mitarbeiter2.getMitarbeiterId());

        assertThat(savedBauprojekt.getMitarbeiterList().size(), is(2));
        assertThat(savedMitarbeiter1.getBauprojektList().size(), is(1));
        assertThat(savedMitarbeiter2.getBauprojektList().size(), is(1));
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

    private Mitarbeiter createNewMitarbeiter(){
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        return  mitarbeiterRepository.save(mitarbeiter);
    }
}
