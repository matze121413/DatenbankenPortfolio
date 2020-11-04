package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauschutt;
import com.dhbw.wwi18.b2.repositories.BauschuttRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauschuttIntegrationTest {
    private BauschuttRepository bauschuttRepository;

    @BeforeAll
    public void setup() {
        bauschuttRepository = new BauschuttRepository();
    }


    @AfterAll
    public void afterAll() {
        bauschuttRepository.closeConnection();
    }

    @Test
    public void createBauschutt() {
        createNewBauschutt();
    }

    @Test
    public void getBauschutt() {
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Bauschutt bauschutt = createNewBauschutt();
        Bauschutt savedBauschut = bauschuttRepository.findById(bauschutt.getBauschuttId());
        assertThat(savedBauschut, is(bauschutt));
    }

    @Test
    public void updateBauschutt(){
        Bauschutt bauschutt = createNewBauschutt();
        bauschutt.setMenge(100);
        Bauschutt updatedBauschutt = bauschuttRepository.update(bauschutt);

        assertThat(updatedBauschutt, is(bauschutt));
    }

    @Test
    public void deleteBauschutt(){
        Bauschutt bauschutt = createNewBauschutt();
        bauschuttRepository.delete(bauschutt);

        Bauschutt deletedBauschutt = bauschuttRepository.findById(bauschutt.getBauschuttId());
        assertNull(deletedBauschutt);

    }


    private Bauschutt createNewBauschutt(){
        Bauschutt bauschutt = new Bauschutt();
        bauschutt.setArt("Ziegel");
        bauschutt.setGewicht(200);
        bauschutt.setKilopreis(3);
        bauschutt.setMenge(7);

        Bauschutt savedBauschutt = bauschuttRepository.save(bauschutt);
        assertNotNull(savedBauschutt.getBauschuttId());
        assertThat(savedBauschutt.getArt(), is("Ziegel"));
        return savedBauschutt;
    }
}
