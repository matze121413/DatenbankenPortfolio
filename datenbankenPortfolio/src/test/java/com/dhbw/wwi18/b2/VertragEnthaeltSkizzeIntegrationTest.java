package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Skizze;
import com.dhbw.wwi18.b2.model.Vertrag;
import com.dhbw.wwi18.b2.repositories.SkizzeRepository;
import com.dhbw.wwi18.b2.repositories.VertragRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VertragEnthaeltSkizzeIntegrationTest {

    private static VertragRepository vertragRepository;
    private static SkizzeRepository skizzeRepository;

    @BeforeAll
    public static void setup() {
        vertragRepository = new VertragRepository();
        skizzeRepository = new SkizzeRepository();
    }

    @AfterAll
    public static void done() {
        vertragRepository.closeConnection();
        skizzeRepository.closeConnection();
    }

    @Test
    public void setVertragInSkizze() {
        Vertrag vertrag = createNewVertrag();

        Skizze skizze1 = createNewSkizze();
        Skizze skizze2 = createNewSkizze();

        skizze1.setVertrag(vertrag);
        skizze2.setVertrag(vertrag);

        Skizze savedSkizze1 = skizzeRepository.updateWithMerge(skizze1);
        Skizze savedSkizze2 = skizzeRepository.updateWithMerge(skizze2);

        assertThat(savedSkizze1.getVertrag(), is(vertrag));
        assertThat(savedSkizze2.getVertrag(), is(vertrag));
    }
    private Skizze createNewSkizze(){
        Skizze skizze = new Skizze();
        skizze.setDetailgrad("sehr hoch");
        skizze.setFlaeche(500);
        skizze.setRaum("Raum");

        Skizze savedSkizze = skizzeRepository.createEntity(skizze);

        assertNotNull(savedSkizze.getSkizzeId());
        assertThat(skizze.getDetailgrad(), is("sehr hoch"));
        return savedSkizze;
    }
    private Vertrag createNewVertrag(){
        Vertrag vertrag = new Vertrag();
        vertrag.setPreis(200000);
        vertrag.setUnterschrift(true);
        vertrag.setLaufzeit(30);
        vertrag.setGegenstand("Haus");

        Vertrag savedVertrag = vertragRepository.createEntity(vertrag);

        assertNotNull(savedVertrag.getVertragId());
        assertThat(vertrag.getPreis(), is(200000));
        return savedVertrag;
    }
}
