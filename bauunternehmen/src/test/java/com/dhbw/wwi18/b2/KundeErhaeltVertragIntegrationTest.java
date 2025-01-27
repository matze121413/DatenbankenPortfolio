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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KundeErhaeltVertragIntegrationTest {

    private KundeRepository kundeRepository;
    private VertragRepository vertragRepository;

    @BeforeAll
    public void setup() {
        kundeRepository = new KundeRepository();
        vertragRepository = new VertragRepository();
    }

    @AfterAll
    public void done() {
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

        Vertrag savedVertrag1 = vertragRepository.update(vertrag1);
        Vertrag savedVertrag2 = vertragRepository.update(vertrag2);

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

        return kundeRepository.save(kunde);
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
