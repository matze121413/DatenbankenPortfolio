package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bauarbeiter;
import com.dhbw.wwi18.b2.model.Bautechnik;
import com.dhbw.wwi18.b2.model.Mitarbeiter;
import com.dhbw.wwi18.b2.repositories.BauarbeiterRepository;
import com.dhbw.wwi18.b2.repositories.BautechnikRepository;
import com.dhbw.wwi18.b2.repositories.MitarbeiterRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BautechnikIntegrationTest {
    private static MitarbeiterRepository mitarbeiterRepository;
    private static BauarbeiterRepository bauarbeiterRepository;
    private static BautechnikRepository bautechnikRepository;
    @BeforeClass
    public static void setup() {
        bauarbeiterRepository = new BauarbeiterRepository();
        mitarbeiterRepository = new MitarbeiterRepository();
        bautechnikRepository = new BautechnikRepository();
    }



    @AfterClass
    public static void done() {
        mitarbeiterRepository.closeConnection();
        bauarbeiterRepository.closeConnection();
        bautechnikRepository.closeConnection();
    }

    @Test
    public void createBautechnik() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Matthias");
        mitarbeiter.setNachname("Nachname");
        mitarbeiter.setBerufsbezeichnung("IT");
        mitarbeiter.setGehalt(50000);
        mitarbeiter.setBerufserfahrung(20);

        mitarbeiter = mitarbeiterRepository.createEntity(mitarbeiter);

        assertThat(mitarbeiter.getVorname(), is("Matthias"));
        assertThat(mitarbeiter.getGehalt(), is(50000));
        assertNotNull(mitarbeiter.getMitarbeiterId());

        Bauarbeiter bauarbeiter= new Bauarbeiter();
        bauarbeiter.setMitarbeiter(mitarbeiter);
        bauarbeiter.setMitarbeiterId(mitarbeiter.getMitarbeiterId());
        bauarbeiter.setArbeitsschicht("Frühschicht");
        bauarbeiter.setFachgebiet("Dachdecker");
        bauarbeiter.setGewerkschaft(true);
        bauarbeiter.setSchichtleiter(false);
        bauarbeiter.setTarif(5000);

        bauarbeiter = bauarbeiterRepository.createEntity(bauarbeiter);

        assertThat(bauarbeiter.getArbeitsschicht(), is("Frühschicht"));
        assertThat(bauarbeiter.getMitarbeiterId(), is(mitarbeiter.getMitarbeiterId()));

        Bautechnik bautechnik = new Bautechnik();
        bautechnik.setArt("Bagger");
        bautechnik.setLeihdauer(10);
        bautechnik.setMarke("Porsche");
        bautechnik.setTagespreis(200);
        bautechnik.setZustand("gut");
//        List<Bauarbeiter> bauarbeiterList = new ArrayList<>();
//        bauarbeiterList.add(bauarbeiter);
//        bautechnik.setBauarbeiterList(bauarbeiterList);

        bautechnik = bautechnikRepository.createEntity(bautechnik);

        List<Bautechnik> bautechnikList = new ArrayList<>();
        bautechnikList.add(bautechnik);
        bauarbeiter.setBautechnikList(bautechnikList);

        bauarbeiter = bauarbeiterRepository.updateWithMerge(bauarbeiter);


        bauarbeiter = bauarbeiterRepository.findById(bauarbeiter.getMitarbeiterId());
        bautechnik = bautechnikRepository.findById(bautechnik.getBautechnikId());
        assertNotNull(bautechnik.getBautechnikId());
        assertNotNull(bauarbeiter.getMitarbeiterId());
    }
    @Test
    public void getBautechnik(){
        Bautechnik bautechnik = bautechnikRepository.findById(12L);
        Bauarbeiter bauarbeiter = bauarbeiterRepository.findById(14L);
    }
}
