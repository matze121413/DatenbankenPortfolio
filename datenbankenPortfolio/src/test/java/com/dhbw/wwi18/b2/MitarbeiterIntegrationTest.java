package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MitarbeiterIntegrationTest {

    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        mitarbeiterRepository = new MitarbeiterRepository();
    }

    @AfterAll
    public void done() {
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void createMitarbeiter() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Matthias");
        mitarbeiter.setNachname("Nachname");
        mitarbeiter.setBerufsbezeichnung("IT");
        mitarbeiter.setGehalt(50000);
        mitarbeiter.setBerufserfahrung(20);

        Mitarbeiter returnedMitarbeiter = mitarbeiterRepository.createEntity(mitarbeiter);

        assertThat(returnedMitarbeiter.getVorname(), is("Matthias"));
        assertThat(returnedMitarbeiter.getGehalt(), is(50000));
        assertNotNull(returnedMitarbeiter.getMitarbeiterId());
    }
}
