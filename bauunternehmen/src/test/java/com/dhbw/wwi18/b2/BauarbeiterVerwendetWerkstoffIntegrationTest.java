package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauarbeiter;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.model.Werkstoff;
import com.dhbw.wwi18.b2.repositories.BauarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.WerkstoffRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BauarbeiterVerwendetWerkstoffIntegrationTest {
    private MitarbeiterRepository mitarbeiterRepository;
    private BauarbeiterRepository bauarbeiterRepository;
    private WerkstoffRepository werkstoffRepository;

    @BeforeAll
    public void setup() {
        bauarbeiterRepository = new BauarbeiterRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
        werkstoffRepository = new WerkstoffRepository();
    }

    @AfterAll
    public void done() {
        mitarbeiterRepository.closeConnection();
        bauarbeiterRepository.closeConnection();
        werkstoffRepository.closeConnection();
    }

    @Test
    public void saveBauarbeiterList() {
        Mitarbeiter mitarbeiter1 = createNewMitarbeiter();
        Bauarbeiter bauarbeiter1 = createNewBauarbeiter(mitarbeiter1);

        Mitarbeiter mitarbeiter2 = createNewMitarbeiter();
        Bauarbeiter bauarbeiter2 = createNewBauarbeiter(mitarbeiter2);

        Werkstoff werkstoff = createNewWerkstoff();

        List<Bauarbeiter> bauarbeiterList = Arrays.asList(bauarbeiter1, bauarbeiter2);
        werkstoff.setBauarbeiterList(bauarbeiterList);

        Werkstoff savedWerkstoff = werkstoffRepository.update(werkstoff);
        Bauarbeiter savedBauarbeiter1 = bauarbeiterRepository.findById(bauarbeiter1.getMitarbeiterId());
        Bauarbeiter savedBauarbeiter2 = bauarbeiterRepository.findById(bauarbeiter2.getMitarbeiterId());

        assertThat(savedWerkstoff.getBauarbeiterList().size(), is(2));
        assertThat(savedBauarbeiter1.getWerkstoffList().size(), is(1));
        assertThat(savedBauarbeiter2.getWerkstoffList().size(), is(1));
    }

    @Test
    public void saveBautechnikList(){
        Mitarbeiter mitarbeiter1 = createNewMitarbeiter();
        Bauarbeiter bauarbeiter1 = createNewBauarbeiter(mitarbeiter1);

        Werkstoff werkstoff1 = createNewWerkstoff();
        Werkstoff werkstoff2 = createNewWerkstoff();

        List<Werkstoff> werkstoffList = Arrays.asList(werkstoff1, werkstoff2);
        bauarbeiter1.setWerkstoffList(werkstoffList);

        Bauarbeiter savedBauarbeiter = bauarbeiterRepository.update(bauarbeiter1);
        Werkstoff savedWerkstoff1 = werkstoffRepository.findById(werkstoff1.getWerkstoffId());
        Werkstoff savedWerkstoff2 = werkstoffRepository.findById(werkstoff2.getWerkstoffId());

        assertThat(savedBauarbeiter.getWerkstoffList().size(), is(2));
        assertThat(savedWerkstoff1.getBauarbeiterList().size(), is(1));
        assertThat(savedWerkstoff2.getBauarbeiterList().size(), is(1));
    }

    private Mitarbeiter createNewMitarbeiter(){
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        return mitarbeiterRepository.save(mitarbeiter);
    }

    private Bauarbeiter createNewBauarbeiter(Mitarbeiter savedMitarbeiter){
        Bauarbeiter bauarbeiter= new Bauarbeiter();
        bauarbeiter.setMitarbeiter(savedMitarbeiter);
        bauarbeiter.setMitarbeiterId(savedMitarbeiter.getMitarbeiterId());
        bauarbeiter.setArbeitsschicht("Frühschicht");
        bauarbeiter.setFachgebiet("Dachdecker");
        bauarbeiter.setGewerkschaft(true);
        bauarbeiter.setSchichtleiter(false);
        bauarbeiter.setTarif(5000);

        return bauarbeiterRepository.save(bauarbeiter);
    }

    private Werkstoff createNewWerkstoff(){
        Werkstoff werkstoff = new Werkstoff();
        werkstoff.setArt("Holzbalken");
        werkstoff.setGewicht(30);
        werkstoff.setKilopreis(5);
        werkstoff.setMenge(75);
        return werkstoffRepository.save(werkstoff);
    }
}
