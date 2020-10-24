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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BauarbeiterVerwendetBautechnikIntegrationTest {
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
    public void saveBauarbeiterList() {
        Mitarbeiter mitarbeiter1 = createNewMitarbeiter();
        Bauarbeiter bauarbeiter1 = createNewBauarbeiter(mitarbeiter1);

        Mitarbeiter mitarbeiter2 = createNewMitarbeiter();
        Bauarbeiter bauarbeiter2 = createNewBauarbeiter(mitarbeiter2);

        Bautechnik bautechnik = createNewBautechnik();

        List<Bauarbeiter> bauarbeiterList = Arrays.asList(bauarbeiter1, bauarbeiter2);
        bautechnik.setBauarbeiterList(bauarbeiterList);

        Bautechnik savedBautechnik = bautechnikRepository.updateWithMerge(bautechnik);
        Bauarbeiter savedBauarbeiter1 = bauarbeiterRepository.findById(bauarbeiter1.getMitarbeiterId());
        Bauarbeiter savedBauarbeiter2 = bauarbeiterRepository.findById(bauarbeiter2.getMitarbeiterId());

        assertThat(savedBautechnik.getBauarbeiterList().size(), is(2));
        assertThat(savedBauarbeiter1.getBautechnikList().size(), is(1));
        assertThat(savedBauarbeiter2.getBautechnikList().size(), is(1));
    }

    @Test
    public void saveBautechnikList(){
        Mitarbeiter mitarbeiter1 = createNewMitarbeiter();
        Bauarbeiter bauarbeiter1 = createNewBauarbeiter(mitarbeiter1);

        Bautechnik bautechnik1 = createNewBautechnik();
        Bautechnik bautechnik2 = createNewBautechnik();

        List<Bautechnik> bautechnikList = Arrays.asList(bautechnik1,bautechnik2);
        bauarbeiter1.setBautechnikList(bautechnikList);

        Bauarbeiter savedBauarbeiter = bauarbeiterRepository.updateWithMerge(bauarbeiter1);
        Bautechnik savedBautechnik1 = bautechnikRepository.findById(bautechnik1.getBautechnikId());
        Bautechnik savedBautechnik2 = bautechnikRepository.findById(bautechnik2.getBautechnikId());

        assertThat(savedBauarbeiter.getBautechnikList().size(), is(2));
        assertThat(savedBautechnik1.getBauarbeiterList().size(), is(1));
        assertThat(savedBautechnik2.getBauarbeiterList().size(), is(1));
    }

    private Mitarbeiter createNewMitarbeiter(){
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname("Horst");
        mitarbeiter.setNachname("Seehofer");
        mitarbeiter.setBerufsbezeichnung("Laufbursche");
        mitarbeiter.setGehalt(3000);
        mitarbeiter.setBerufserfahrung(20);

        return mitarbeiterRepository.createEntity(mitarbeiter);
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

        return bauarbeiterRepository.createEntity(bauarbeiter);
    }

    private Bautechnik createNewBautechnik(){
        Bautechnik bautechnik = new Bautechnik();
        bautechnik.setZustand("gut");
        bautechnik.setTagespreis(200);
        bautechnik.setMarke("CAT");
        bautechnik.setLeihdauer(20);
        bautechnik.setArt("Kleinbagger");

        return bautechnikRepository.createEntity(bautechnik);
    }
}
