package com.dhbw.wwi18.b2;

import com.dhbw.wwi18.b2.model.WerkzeugUndMaschinenverleih;
import com.dhbw.wwi18.b2.repositories.WerkzeugUndMaschinenverleihRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WerkzeugUndMaschinenverleihIntegrationTest {

    private WerkzeugUndMaschinenverleihRepository verleihRepository;

    @BeforeAll
    public void setup() {
        verleihRepository = new WerkzeugUndMaschinenverleihRepository();
    }

    @AfterAll
    public void afterAll() {
        verleihRepository.closeConnection();
    }

    @Test
    public void createWerkzeugUndMaschinenverleih() {
        createNewWerkzeugUndMaschinenverleih();
    }

    @Test
    public void getWerkzeugUndMaschinenverleih() {
        //man muss zunächst einen Werkstoff erstellen, bevor man ihn auslesen kann
        //alles andere würde Wiederholbarkeit verletzen
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih = createNewWerkzeugUndMaschinenverleih();
        WerkzeugUndMaschinenverleih savedWerkzeugUndMaschinenverleih =
                verleihRepository.findById(werkzeugUndMaschinenverleih.getVerleihId());
        assertThat(savedWerkzeugUndMaschinenverleih, is(werkzeugUndMaschinenverleih));
    }

    @Test
    public void updateWerkzeugUndMaschinenverleih() {
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih = createNewWerkzeugUndMaschinenverleih();
        werkzeugUndMaschinenverleih.setErfahrung(13);
        WerkzeugUndMaschinenverleih updatedWerkzeugUndMaschinenverleih =
                verleihRepository.update(werkzeugUndMaschinenverleih);

        assertThat(updatedWerkzeugUndMaschinenverleih, is(werkzeugUndMaschinenverleih));
    }

    @Test
    public void deleteWerkzeugUndMaschinenverleih() {
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih = createNewWerkzeugUndMaschinenverleih();
        verleihRepository.delete(werkzeugUndMaschinenverleih);

        WerkzeugUndMaschinenverleih deletedWerkzeugUndMaschinenverleih =
                verleihRepository.findById(werkzeugUndMaschinenverleih.getVerleihId());

        assertNull(deletedWerkzeugUndMaschinenverleih);
    }

    private WerkzeugUndMaschinenverleih createNewWerkzeugUndMaschinenverleih() {
        WerkzeugUndMaschinenverleih werkzeugUndMaschinenverleih = new WerkzeugUndMaschinenverleih();
        werkzeugUndMaschinenverleih.setErfahrung(12);
        werkzeugUndMaschinenverleih.setName("Mike's Werkzeug Express Maschinenvermietung");
        werkzeugUndMaschinenverleih.setOrt("Mannheim");
        werkzeugUndMaschinenverleih.setPlz("68305");
        werkzeugUndMaschinenverleih.setStrasse("Carl-Reuther-Straße");
        werkzeugUndMaschinenverleih.setTelefonnummer("0621-43715-500");
        WerkzeugUndMaschinenverleih savedWerkzeugUndMaschinenverleih =
                verleihRepository.save(werkzeugUndMaschinenverleih);

        assertNotNull(savedWerkzeugUndMaschinenverleih.getVerleihId());
        assertThat(savedWerkzeugUndMaschinenverleih.getName(), is("Mike's Werkzeug Express Maschinenvermietung"));

        return werkzeugUndMaschinenverleih;
    }
}
