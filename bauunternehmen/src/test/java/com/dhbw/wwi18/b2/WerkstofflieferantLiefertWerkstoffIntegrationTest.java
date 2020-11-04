package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Werkstoff;
import com.dhbw.wwi18.b2.model.Werkstofflieferant;
import com.dhbw.wwi18.b2.repositories.WerkstoffRepository;
import com.dhbw.wwi18.b2.repositories.WerkstofflieferantRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WerkstofflieferantLiefertWerkstoffIntegrationTest {

    private WerkstofflieferantRepository werkstofflieferantRepository;
    private WerkstoffRepository werkstoffRepository;

    @BeforeAll
    public void setup() {
        werkstofflieferantRepository = new WerkstofflieferantRepository();
        werkstoffRepository = new WerkstoffRepository();
    }


    @AfterAll
    public void done() {
        werkstofflieferantRepository.closeConnection();
        werkstoffRepository.closeConnection();
    }

    @Test
    public void saveWerkstoffList() {
        Werkstofflieferant werkstofflieferant = createNewWerkstofflieferant();

        Werkstoff werkstoff1 = createNewWerkstoff();
        Werkstoff werkstoff2 = createNewWerkstoff();


        List<Werkstoff> werkstoffList = Arrays.asList(werkstoff1, werkstoff2);
        werkstofflieferant.setWerkstoffList(werkstoffList);

        Werkstofflieferant savedWerkstofflieferant = werkstofflieferantRepository.update(werkstofflieferant);
        Werkstoff savedWerkstoff1 = werkstoffRepository.findById(werkstoff1.getWerkstoffId());
        Werkstoff savedWerkstoff2 = werkstoffRepository.findById(werkstoff2.getWerkstoffId());

        assertThat(savedWerkstofflieferant.getWerkstoffList().size(), is(2));
        assertThat(savedWerkstoff1.getWerkstofflieferantList().size(), is(1));
        assertThat(savedWerkstoff2.getWerkstofflieferantList().size(), is(1));
    }

    @Test
    public void saveWerkstofflieferantList() {
        Werkstofflieferant werkstofflieferant1 = createNewWerkstofflieferant();
        Werkstofflieferant werkstofflieferant2 = createNewWerkstofflieferant();

        Werkstoff werkstoff = createNewWerkstoff();


        List<Werkstofflieferant> werkstofflieferantList = Arrays.asList(werkstofflieferant1, werkstofflieferant2);
        werkstoff.setWerkstofflieferantList(werkstofflieferantList);

        Werkstoff savedWerkstoff = werkstoffRepository.update(werkstoff);
        Werkstofflieferant savedWerkstoffLieferant1 = werkstofflieferantRepository.findById(werkstofflieferant1.getLieferantId());
        Werkstofflieferant savedWerkstofflieferant2 = werkstofflieferantRepository.findById(werkstofflieferant2.getLieferantId());

        assertThat(savedWerkstoff.getWerkstofflieferantList().size(), is(2));
        assertThat(savedWerkstoffLieferant1.getWerkstoffList().size(), is(1));
        assertThat(savedWerkstofflieferant2.getWerkstoffList().size(), is(1));
    }

    private Werkstofflieferant createNewWerkstofflieferant() {
        Werkstofflieferant werkstofflieferant = new Werkstofflieferant();
        werkstofflieferant.setName("Eichsfeld Holz GmbH");
        werkstofflieferant.setErfahrung(62);
        werkstofflieferant.setOrt("Leinefelde");
        werkstofflieferant.setPlz("37327");
        werkstofflieferant.setStrasse("Stammweg");
        werkstofflieferant.setTelefonnummer("0360555970");

        return werkstofflieferantRepository.save(werkstofflieferant);
    }

    private Werkstoff createNewWerkstoff() {
        Werkstoff werkstoff = new Werkstoff();
        werkstoff.setArt("Holzbalken");
        werkstoff.setGewicht(30);
        werkstoff.setKilopreis(5);
        werkstoff.setMenge(75);
        return werkstoffRepository.save(werkstoff);
    }
}
