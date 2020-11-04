package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Skizze;
import com.dhbw.wwi18.b2.repositories.SkizzeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SkizzeIntegrationTest {

    private static SkizzeRepository skizzeRepository;

    @BeforeAll
    public static void setup() {skizzeRepository = new SkizzeRepository();}

    @AfterAll
    public static void afterAll() {
        skizzeRepository.closeConnection();
    }

    @Test
    public void createSkizze(){
        createNewSkizze();
    }

    @Test
    public void getSkizze(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Skizze skizze = createNewSkizze();
        Skizze savedSkizze = skizzeRepository.findById(skizze.getSkizzeId());
        assertThat(savedSkizze, is(skizze));
    }

    @Test
    public void updateSkizze(){
        Skizze skizze = createNewSkizze();
        skizze.setDetailgrad("hoch");
        Skizze updatedSkizze = skizzeRepository.update(skizze);

        assertThat(updatedSkizze, is(skizze));
    }

    @Test
    public void deleteSkizze(){
        Skizze skizze = createNewSkizze();
        skizzeRepository.delete(skizze);

        Skizze deletedSkizze = skizzeRepository.findById(skizze.getSkizzeId());
        assertNull(deletedSkizze);

    }

    private Skizze createNewSkizze(){
        Skizze skizze = new Skizze();
        skizze.setDetailgrad("sehr hoch");
        skizze.setFlaeche(500);
        skizze.setRaum("Raum");

        Skizze savedSkizze = skizzeRepository.save(skizze);

        assertNotNull(savedSkizze.getSkizzeId());
        assertThat(skizze.getDetailgrad(), is("sehr hoch"));
        return savedSkizze;
    }
}
