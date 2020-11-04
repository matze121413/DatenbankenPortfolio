package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Kunde;
import com.dhbw.wwi18.b2.model.Vertrag;
import com.dhbw.wwi18.b2.repositories.KundeRepository;
import com.dhbw.wwi18.b2.repositories.VertragRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KundeErhaeltVertragIntegrationTest {

    private static KundeRepository kundeRepository;
    private static VertragRepository vertragRepository;

    @BeforeAll
    public static void setup() {
        kundeRepository = new KundeRepository();
        vertragRepository = new VertragRepository();
    }

    @AfterAll
    public static void done() {
        kundeRepository.closeConnection();
        vertragRepository.closeConnection();
    }

    @Test
    public void setKundeInVertrag() {
        Kunde kunde = createNewKunde();

        Vertrag vertrag1 = createNewVertrag();
        Vertrag vertrag2 = createNewVertrag();

        vertrag1.setKunde(kunde);
        vertrag2.setKunde(kunde);

        Vertrag savedVertrag1 = vertragRepository.updateWithMerge(vertrag1);
        Vertrag savedVertrag2 = vertragRepository.updateWithMerge(vertrag2);

        assertThat(savedVertrag1.getKunde(), is(kunde));
        assertThat(savedVertrag2.getKunde(), is(kunde));
    }

    private Kunde createNewKunde(){
        Kunde kunde = new Kunde();
        kunde.setVorname("Bert");
        kunde.setNachname("Schmitz");
        kunde.setStrasse("Berliner Weg");
        kunde.setOrt("Berlin");
        kunde.setPlz("53343");

        Kunde savedKunde = kundeRepository.createEntity(kunde);

        assertNotNull(savedKunde.getKundeId());
        assertThat(kunde.getStrasse(), is("Berliner Weg"));
        return savedKunde;
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
