package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.Bautechnik;
import com.dhbw.wwi18.b2.model.WerkzeugUndMaschinenverleih;
import com.dhbw.wwi18.b2.repositories.BautechnikRepository;
import com.dhbw.wwi18.b2.repositories.WerkzeugUndMaschinenverleihRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WerkzeugUndMaschinenverleihStelltBereitBautechnikIntegrationTest {
    private WerkzeugUndMaschinenverleihRepository verleihRepository;
    private BautechnikRepository bautechnikRepository;

    @BeforeAll
    public void setup() {
        verleihRepository = new WerkzeugUndMaschinenverleihRepository();
        bautechnikRepository = new BautechnikRepository();
    }


    @AfterAll
    public void done() {
        verleihRepository.closeConnection();
        bautechnikRepository.closeConnection();
    }

    @Test
    public void saveBautechnikList() {
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih = createNewWerkzeugUndMaschinenverleih();

        Bautechnik bautechnik1 = createNewBautechnik();
        Bautechnik bautechnik2 = createNewBautechnik();

        List<Bautechnik> bautechnikList = Arrays.asList(bautechnik1, bautechnik2);
        werkzeugUndMaschinenverleih.setBautechnikList(bautechnikList);

        WerkzeugUndMaschinenverleih savedWerkzeugUndMaschinenverleih =
                verleihRepository.updateWithMerge(werkzeugUndMaschinenverleih);
        Bautechnik savedBautechnik1 = bautechnikRepository.findById(bautechnik1.getBautechnikId());
        Bautechnik savedBautechnik2 = bautechnikRepository.findById(bautechnik2.getBautechnikId());

        assertThat(savedWerkzeugUndMaschinenverleih.getBautechnikList().size(), is(2));
        assertThat(savedBautechnik1.getWerkzeugUndMaschinenverleihList().size(), is(1));
        assertThat(savedBautechnik2.getWerkzeugUndMaschinenverleihList().size(), is(1));
    }

    @Test
    public void saveBauprojektList() {
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih1 = createNewWerkzeugUndMaschinenverleih();
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih2 = createNewWerkzeugUndMaschinenverleih();

        Bautechnik bautechnik = createNewBautechnik();

        List<WerkzeugUndMaschinenverleih> werkzeugUndMaschinenverleihList =
                Arrays.asList(werkzeugUndMaschinenverleih1, werkzeugUndMaschinenverleih2);
        bautechnik.setWerkzeugUndMaschinenverleihList(werkzeugUndMaschinenverleihList);

        Bautechnik savedBautechnik = bautechnikRepository.updateWithMerge(bautechnik);
        WerkzeugUndMaschinenverleih savedWerkzeugUndMaschinenverleih1 =
                verleihRepository.findById(werkzeugUndMaschinenverleih1.getVerleihId());
        WerkzeugUndMaschinenverleih savedWerkzeugUndMaschinenverleih2 =
                verleihRepository.findById(werkzeugUndMaschinenverleih2.getVerleihId());

        assertThat(savedBautechnik.getWerkzeugUndMaschinenverleihList().size(), is(2));
        assertThat(savedWerkzeugUndMaschinenverleih1.getBautechnikList().size(), is(1));
        assertThat(savedWerkzeugUndMaschinenverleih2.getBautechnikList().size(), is(1));
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

    private Bautechnik createNewBautechnik() {
        Bautechnik bautechnik = new Bautechnik();
        bautechnik.setZustand("gut");
        bautechnik.setTagespreis(200);
        bautechnik.setMarke("CAT");
        bautechnik.setLeihdauer(20);
        bautechnik.setArt("Kleinbagger");

        return bautechnikRepository.createEntity(bautechnik);
    }
}
