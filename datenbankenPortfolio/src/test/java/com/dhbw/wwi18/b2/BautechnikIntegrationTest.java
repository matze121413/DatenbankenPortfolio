package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bautechnik;
import com.dhbw.wwi18.b2.repositories.BautechnikRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BautechnikIntegrationTest {

    private static BautechnikRepository bautechnikRepository;

    @BeforeAll
    public static void setup() {
        bautechnikRepository = new BautechnikRepository();
    }


    @AfterAll
    public static void afterAll() {
        bautechnikRepository.closeConnection();
    }

    @Test
    public void createBautechnik(){
        createNewBautechnik();
    }

    @Test
    public void getBautechnik(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Bautechnik bautechnik = createNewBautechnik();
        Bautechnik savedBautechnik = bautechnikRepository.findById(bautechnik.getBautechnikId());
        assertThat(savedBautechnik, is(bautechnik));
    }

    @Test
    public void updateBautechnik(){
        Bautechnik bautechnik = createNewBautechnik();
        bautechnik.setLeihdauer(50);
        Bautechnik updatedBautechnik = bautechnikRepository.updateWithMerge(bautechnik);

        assertThat(updatedBautechnik, is(bautechnik));
    }

    @Test
    public void deleteBautechnik(){
        Bautechnik bautechnik = createNewBautechnik();
        bautechnikRepository.deleteEntity(bautechnik);

        Bautechnik deletedBautechnik = bautechnikRepository.findById(bautechnik.getBautechnikId());
        assertNull(deletedBautechnik);

    }


    private Bautechnik createNewBautechnik(){
        Bautechnik bautechnik = new Bautechnik();
        bautechnik.setZustand("gut");
        bautechnik.setTagespreis(200);
        bautechnik.setMarke("CAT");
        bautechnik.setLeihdauer(20);
        bautechnik.setArt("Kleinbagger");

        Bautechnik savedBautechnik = bautechnikRepository.createEntity(bautechnik);

        assertNotNull(savedBautechnik.getBautechnikId());
        assertThat(bautechnik.getArt(), is("Kleinbagger"));
        return savedBautechnik;
    }

}
