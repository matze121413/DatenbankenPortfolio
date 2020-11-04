package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauprojekt;
import com.dhbw.wwi18.b2.model.Bauschutt;
import com.dhbw.wwi18.b2.repositories.BauprojektRepository;
import com.dhbw.wwi18.b2.repositories.BauschuttRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauprojektBringtHervorBauschuttIntegrationTest {

    private BauprojektRepository bauprojektRepository;
    private BauschuttRepository bauschuttRepository;

    @BeforeAll
    public void setup() {
        bauprojektRepository = new BauprojektRepository();
        bauschuttRepository = new BauschuttRepository();
    }


    @AfterAll
    public void done() {
        bauprojektRepository.closeConnection();
        bauschuttRepository.closeConnection();
    }

    @Test
    public void saveBauschuttList() {
        Bauprojekt bauprojekt = createNewBauprojekt();

        Bauschutt bauschutt1 = createNewBauschutt();
        Bauschutt bauschutt2 = createNewBauschutt();


        List<Bauschutt> bauschuttList = Arrays.asList(bauschutt1, bauschutt2);
        bauprojekt.setBauschuttList(bauschuttList);

        Bauprojekt savedBauschutt = bauprojektRepository.update(bauprojekt);
        Bauschutt savedBauschutt1 = bauschuttRepository.findById(bauschutt1.getBauschuttId());
        Bauschutt savedBauschutt2 = bauschuttRepository.findById(bauschutt2.getBauschuttId());

        assertThat(savedBauschutt.getBauschuttList().size(), is(2));
        assertThat(savedBauschutt1.getBauprojektList().size(), is(1));
        assertThat(savedBauschutt2.getBauprojektList().size(), is(1));
    }

    @Test
    public void saveBauprojektList(){
        Bauprojekt bauprojekt1 = createNewBauprojekt();
        Bauprojekt bauprojekt2 = createNewBauprojekt();

        Bauschutt bauschutt = createNewBauschutt();

        List<Bauprojekt> bauprojektList = Arrays.asList(bauprojekt1, bauprojekt2);
        bauschutt.setBauprojektList(bauprojektList);

        Bauschutt savedBauschutt = bauschuttRepository.update(bauschutt);
        Bauprojekt savedBauprojekt1 = bauprojektRepository.findById(bauprojekt1.getBauprojektId());
        Bauprojekt savedBauprojekt2 = bauprojektRepository.findById(bauprojekt2.getBauprojektId());

        assertThat(savedBauschutt.getBauprojektList().size(), is(2));
        assertThat(savedBauprojekt1.getBauschuttList().size(), is(1));
        assertThat(savedBauprojekt2.getBauschuttList().size(), is(1));
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

    private Bauschutt createNewBauschutt(){
        Bauschutt bauschutt = new Bauschutt();
        bauschutt.setArt("Ziegel");
        bauschutt.setGewicht(200);
        bauschutt.setKilopreis(3);
        bauschutt.setMenge(7);

        return bauschuttRepository.save(bauschutt);
    }
}
