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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VertragEnthaeltSkizzeIntegrationTest {

    private VertragRepository vertragRepository;
    private SkizzeRepository skizzeRepository;

    @BeforeAll
    public void setup() {
        vertragRepository = new VertragRepository();
        skizzeRepository = new SkizzeRepository();
    }

    @AfterAll
    public void done() {
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

        Skizze savedSkizze1 = skizzeRepository.update(skizze1);
        Skizze savedSkizze2 = skizzeRepository.update(skizze2);

        assertThat(savedSkizze1.getVertrag(), is(vertrag));
        assertThat(savedSkizze2.getVertrag(), is(vertrag));
    }
    private Skizze createNewSkizze(){
        Skizze skizze = new Skizze();
        skizze.setDetailgrad("sehr hoch");
        skizze.setFlaeche(500);
        skizze.setRaum("Raum");

        return skizzeRepository.save(skizze);
    }
    private Vertrag createNewVertrag(){
        Vertrag vertrag = new Vertrag();
        vertrag.setPreis(200000);
        vertrag.setUnterschrift(true);
        vertrag.setLaufzeit(30);
        vertrag.setGegenstand("Haus");

        return vertragRepository.save(vertrag);
    }
}
