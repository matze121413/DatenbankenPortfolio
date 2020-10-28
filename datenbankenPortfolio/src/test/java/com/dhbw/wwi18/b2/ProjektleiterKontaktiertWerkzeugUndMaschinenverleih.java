package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Projektleiter;
import com.dhbw.wwi18.b2.model.WerkzeugUndMaschinenverleih;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.ProjektleiterRepository;
import com.dhbw.wwi18.b2.repositories.WerkzeugUndMaschinenverleihRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjektleiterKontaktiertWerkzeugUndMaschinenverleih {

    private ProjektleiterRepository projektleiterRepository;
    private WerkzeugUndMaschinenverleihRepository verleihRepository;
    private MitarbeiterRepository mitarbeiterRepository;

    @BeforeAll
    public void setup() {
        projektleiterRepository = new ProjektleiterRepository();
        verleihRepository = new WerkzeugUndMaschinenverleihRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
    }


    @AfterAll
    public void done() {
        projektleiterRepository.closeConnection();
        verleihRepository.closeConnection();
        mitarbeiterRepository.closeConnection();
    }

    @Test
    public void setEntsorgungsunternehmenInBauschutt() {
        Mitarbeiter mitarbeiter = createNewMitarbeiter();
        Projektleiter projektleiter = createNewProjektleiter(mitarbeiter);

        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih1 = createNewWerkzeugUndMaschinenverleih();
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih2 = createNewWerkzeugUndMaschinenverleih();

        werkzeugUndMaschinenverleih1.setProjektleiter(projektleiter);
        werkzeugUndMaschinenverleih2.setProjektleiter(projektleiter);

        WerkzeugUndMaschinenverleih savedWerkzeugUndMaschinenverleih1 =
                verleihRepository.updateWithMerge(werkzeugUndMaschinenverleih1);
        WerkzeugUndMaschinenverleih savedWerkzeugUndMaschinenverleih2 =
                verleihRepository.updateWithMerge(werkzeugUndMaschinenverleih2);

        assertThat(savedWerkzeugUndMaschinenverleih1.getProjektleiter(), is(projektleiter));
        assertThat(savedWerkzeugUndMaschinenverleih2.getProjektleiter(), is(projektleiter));
    }

    private Mitarbeiter createNewMitarbeiter() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        return mitarbeiterRepository.createEntity(mitarbeiter);
    }

    private Projektleiter createNewProjektleiter(Mitarbeiter savedMitarbeiter) {
        Projektleiter projektleiter = new Projektleiter();
        projektleiter.setAktProjektanzahl(1);
        projektleiter.setGesamtProjektanzahl(42);
        projektleiter.setMitarbeiter(savedMitarbeiter);
        projektleiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());

        return projektleiterRepository.createEntity(projektleiter);
    }

    private WerkzeugUndMaschinenverleih createNewWerkzeugUndMaschinenverleih() {
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih = new WerkzeugUndMaschinenverleih();
        werkzeugUndMaschinenverleih.setErfahrung(12);
        werkzeugUndMaschinenverleih.setName("Mike's Werkzeug Express Maschinenvermietung");
        werkzeugUndMaschinenverleih.setOrt("Mannheim");
        werkzeugUndMaschinenverleih.setPlz("68305");
        werkzeugUndMaschinenverleih.setStrasse("Carl-Reuther-Straße");
        werkzeugUndMaschinenverleih.setTelefonnummer("0621-43715-500");
        return verleihRepository.createEntity(werkzeugUndMaschinenverleih);
    }
}
