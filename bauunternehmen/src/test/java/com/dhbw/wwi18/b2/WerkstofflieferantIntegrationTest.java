package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Werkstofflieferant;
import com.dhbw.wwi18.b2.repositories.WerkstofflieferantRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WerkstofflieferantIntegrationTest {

    private WerkstofflieferantRepository werkstofflieferantRepository;

    @BeforeAll
    public void setup() {
        werkstofflieferantRepository = new WerkstofflieferantRepository();
    }

    @AfterAll
    public void afterAll() {
        werkstofflieferantRepository.closeConnection();
    }

    @Test
    public void createWerkstofflieferant() {
        createNewWerkstofflieferant();
    }

    @Test
    public void getWerkstofflieferant() {
        //man muss zunächst einen Werkstoff erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Werkstofflieferant werkstofflieferant = createNewWerkstofflieferant();
        Werkstofflieferant savedWerkstofflieferant =
                werkstofflieferantRepository.findById(werkstofflieferant.getLieferantId());
        assertThat(savedWerkstofflieferant, is(werkstofflieferant));
    }

    @Test
    public void updateWerkstofflieferant() {
        Werkstofflieferant werkstofflieferant = createNewWerkstofflieferant();
        werkstofflieferant.setErfahrung(63);
        Werkstofflieferant updatedWerkstofflieferant = werkstofflieferantRepository.update(werkstofflieferant);

        assertThat(updatedWerkstofflieferant, is(werkstofflieferant));
    }

    @Test
    public void deleteWerkstofflieferant() {
        Werkstofflieferant werkstofflieferant = createNewWerkstofflieferant();
        werkstofflieferantRepository.delete(werkstofflieferant);

        Werkstofflieferant deletedWerkstofflieferant =
                werkstofflieferantRepository.findById(werkstofflieferant.getLieferantId());
        assertNull(deletedWerkstofflieferant);

    }

    private Werkstofflieferant createNewWerkstofflieferant() {
        Werkstofflieferant werkstofflieferant = new Werkstofflieferant();
        werkstofflieferant.setName("Eichsfeld Holz GmbH");
        werkstofflieferant.setErfahrung(62);
        werkstofflieferant.setOrt("Leinefelde");
        werkstofflieferant.setPlz("37327");
        werkstofflieferant.setStrasse("Stammweg");
        werkstofflieferant.setTelefonnummer("0360555970");

        Werkstofflieferant savedWerkstofflieferant = werkstofflieferantRepository.save(werkstofflieferant);

        assertNotNull(savedWerkstofflieferant.getLieferantId());
        assertThat(werkstofflieferant.getName(), is("Eichsfeld Holz GmbH"));

        return savedWerkstofflieferant;
    }
}
