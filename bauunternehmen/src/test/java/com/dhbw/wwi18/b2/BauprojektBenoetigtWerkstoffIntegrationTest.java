package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauprojekt;
import com.dhbw.wwi18.b2.model.Werkstoff;
import com.dhbw.wwi18.b2.repositories.BauprojektRepository;
import com.dhbw.wwi18.b2.repositories.WerkstoffRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauprojektBenoetigtWerkstoffIntegrationTest {

    private BauprojektRepository bauprojektRepository;
    private WerkstoffRepository werkstoffRepository;

    @BeforeAll
    public  void setup() {
        bauprojektRepository = new BauprojektRepository();
        werkstoffRepository = new WerkstoffRepository();
    }

    @AfterAll
    public void done() {
        bauprojektRepository.closeConnection();
        werkstoffRepository.closeConnection();
    }

    @Test
    public void saveBauschuttList() {
        Bauprojekt bauprojekt = createNewBauprojekt();

        Werkstoff werkstoff1 = createNewWerkstoff();
        Werkstoff werkstoff2 = createNewWerkstoff();


        List<Werkstoff> werkstoffList = Arrays.asList(werkstoff1, werkstoff2);
        bauprojekt.setWerkstoffList(werkstoffList);

        Bauprojekt savedBauprojekt = bauprojektRepository.update(bauprojekt);
        Werkstoff savedWerkstoff1 = werkstoffRepository.findById(werkstoff1.getWerkstoffId());
        Werkstoff savedWerkstoff2 = werkstoffRepository.findById(werkstoff2.getWerkstoffId());

        assertThat(savedBauprojekt.getWerkstoffList().size(), is(2));
        assertThat(savedWerkstoff1.getBauprojektList().size(), is(1));
        assertThat(savedWerkstoff2.getBauprojektList().size(), is(1));
    }

    @Test
    public void saveBauprojektList(){
        Bauprojekt bauprojekt1 = createNewBauprojekt();
        Bauprojekt bauprojekt2 = createNewBauprojekt();

       Werkstoff werkstoff = createNewWerkstoff();

        List<Bauprojekt> bauprojektList = Arrays.asList(bauprojekt1, bauprojekt2);
        werkstoff.setBauprojektList(bauprojektList);

        Werkstoff savedWerkstoff = werkstoffRepository.update(werkstoff);
        Bauprojekt savedBauprojekt1 = bauprojektRepository.findById(bauprojekt1.getBauprojektId());
        Bauprojekt savedBauprojekt2 = bauprojektRepository.findById(bauprojekt2.getBauprojektId());

        assertThat(savedWerkstoff.getBauprojektList().size(), is(2));
        assertThat(savedBauprojekt1.getWerkstoffList().size(), is(1));
        assertThat(savedBauprojekt2.getWerkstoffList().size(), is(1));
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

    private Werkstoff createNewWerkstoff(){
        Werkstoff werkstoff = new Werkstoff();
        werkstoff.setArt("Holzbalken");
        werkstoff.setGewicht(30);
        werkstoff.setKilopreis(5);
        werkstoff.setMenge(75);
        return werkstoffRepository.save(werkstoff);
    }
}
