package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Werkstoff;
import com.dhbw.wwi18.b2.repositories.WerkstoffRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WerkstoffIntegrationTest {
    private WerkstoffRepository werkstoffRepository;

    @BeforeAll
    public void setup() {
        werkstoffRepository = new WerkstoffRepository();
    }

    @AfterAll
    public void afterAll() {
        werkstoffRepository.closeConnection();
    }

    @Test
    public void createWerkstoff(){
        createNewWerkstoff();
    }

    @Test
    public void getWerkstoff(){
        //man muss zunächst einen Werkstoff erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Werkstoff werkstoff = createNewWerkstoff();
        Werkstoff savedWerkstoff = werkstoffRepository.findById(werkstoff.getWerkstoffId());
        assertThat(savedWerkstoff, is(werkstoff));
    }

    @Test
    public void updateWerkstoff(){
        Werkstoff werkstoff = createNewWerkstoff();
        werkstoff.setMenge(100);
        Werkstoff updatedWerkstoff = werkstoffRepository.updateWithMerge(werkstoff);

        assertThat(updatedWerkstoff, is(werkstoff));
    }

    @Test
    public void deleteWerkstoff(){
        Werkstoff werkstoff = createNewWerkstoff();
        werkstoffRepository.deleteEntity(werkstoff);

        Werkstoff deletedWerkstoff = werkstoffRepository.findById(werkstoff.getWerkstoffId());
        assertNull(deletedWerkstoff);

    }

    private Werkstoff createNewWerkstoff(){
        Werkstoff werkstoff = new Werkstoff();
        werkstoff.setArt("Holzbalken");
        werkstoff.setGewicht(30);
        werkstoff.setKilopreis(5);
        werkstoff.setMenge(75);
        Werkstoff savedWerkstoff = werkstoffRepository.createEntity(werkstoff);

        assertNotNull(savedWerkstoff.getWerkstoffId());
        assertThat(werkstoff.getArt(), is("Holzbalken"));

        return savedWerkstoff;
    }
}
