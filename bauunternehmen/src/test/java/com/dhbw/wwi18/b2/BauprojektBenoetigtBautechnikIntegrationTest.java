package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauprojekt;
import com.dhbw.wwi18.b2.model.Bautechnik;
import com.dhbw.wwi18.b2.repositories.BauprojektRepository;
import com.dhbw.wwi18.b2.repositories.BautechnikRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauprojektBenoetigtBautechnikIntegrationTest {
    private BauprojektRepository bauprojektRepository;
    private BautechnikRepository bautechnikRepository;

    @BeforeAll
    public void setup() {
        bauprojektRepository = new BauprojektRepository();
        bautechnikRepository = new BautechnikRepository();
    }


    @AfterAll
    public void done() {
        bauprojektRepository.closeConnection();
        bautechnikRepository.closeConnection();
    }

    @Test
    public void saveBautechnikList() {
        Bauprojekt bauprojekt = createNewBauprojekt();

        Bautechnik bautechnik1 = createNewBautechnik();
        Bautechnik bautechnik2 = createNewBautechnik();

        List<Bautechnik> bautechnikList = Arrays.asList(bautechnik1,bautechnik2);
        bauprojekt.setBautechnikList(bautechnikList);

        Bauprojekt savedBauprojekt = bauprojektRepository.update(bauprojekt);
        Bautechnik savedBautechnik1 = bautechnikRepository.findById(bautechnik1.getBautechnikId());
        Bautechnik savedBautechnik2 = bautechnikRepository.findById(bautechnik2.getBautechnikId());

        assertThat(savedBauprojekt.getBautechnikList().size(), is(2));
        assertThat(savedBautechnik1.getBauprojektList().size(), is(1));
        assertThat(savedBautechnik2.getBauprojektList().size(), is(1));
    }

    @Test
    public void saveBauprojektList(){
        Bauprojekt bauprojekt1 = createNewBauprojekt();
        Bauprojekt bauprojekt2 = createNewBauprojekt();

        Bautechnik bautechnik = createNewBautechnik();

        List<Bauprojekt> bauprojektList = Arrays.asList(bauprojekt1, bauprojekt2);
        bautechnik.setBauprojektList(bauprojektList);

        Bautechnik savedBautechnik = bautechnikRepository.update(bautechnik);
        Bauprojekt savedBauprojekt1 = bauprojektRepository.findById(bauprojekt1.getBauprojektId());
        Bauprojekt savedBauprojekt2 = bauprojektRepository.findById(bauprojekt2.getBauprojektId());

        assertThat(savedBautechnik.getBauprojektList().size(), is(2));
        assertThat(savedBauprojekt1.getBautechnikList().size(), is(1));
        assertThat(savedBauprojekt2.getBautechnikList().size(), is(1));
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

    private Bautechnik createNewBautechnik(){
        Bautechnik bautechnik = new Bautechnik();
        bautechnik.setZustand("gut");
        bautechnik.setTagespreis(200);
        bautechnik.setMarke("CAT");
        bautechnik.setLeihdauer(20);
        bautechnik.setArt("Kleinbagger");

        return bautechnikRepository.save(bautechnik);
    }
}
