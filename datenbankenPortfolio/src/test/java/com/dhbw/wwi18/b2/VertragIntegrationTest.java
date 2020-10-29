package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Vertrag;
import com.dhbw.wwi18.b2.repositories.VertragRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VertragIntegrationTest {

    private static VertragRepository vertragRepository;

    @BeforeAll
    public static void setup() {vertragRepository = new VertragRepository();}

    @AfterAll
    public static void afterAll() {
        vertragRepository.closeConnection();
    }

    @Test
    public void createVertrag(){
        createNewVertrag();
    }

    @Test
    public void getVertrag(){
        //man muss zunächst einen Bauarbeiter erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        Vertrag vertrag = createNewVertrag();
        Vertrag savedVertrag = vertragRepository.findById(vertrag.getVertragId());
        assertThat(savedVertrag, is(vertrag));
    }

    @Test
    public void updateVertrag(){
        Vertrag vertrag = createNewVertrag();
        vertrag.setPreis(500000);
        Vertrag updatedVertrag = vertragRepository.updateWithMerge(vertrag);

        assertThat(updatedVertrag, is(vertrag));
    }

    @Test
    public void deleteVertrag(){
        Vertrag vertrag = createNewVertrag();
        vertragRepository.deleteEntity(vertrag);

        Vertrag deletedVertrag = vertragRepository.findById(vertrag.getVertragId());
        assertNull(deletedVertrag);

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
