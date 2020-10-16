SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Datenbank: `bauunternehmen`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Anfrage`
--

CREATE TABLE `Anfrage` (
  `anfrage_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `anzRaeume` int NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL,
  `flaeche` int NOT NULL,
  `farbe` varchar(10) NOT NULL,
  `preisvorstellung` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Architekt`
--

CREATE TABLE `Architekt` (
  `mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `selbststaendig` BOOLEAN NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL,
  `fachrichtung` varchar(255) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Architekt_entwirft_Skizze`
--

CREATE TABLE `Architekt_entwirft_Skizze` (
  `skizze_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauarbeiter`
--

CREATE TABLE `Bauarbeiter` (
  `mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY,
  `schichtleiter` BOOLEAN NOT NULL,
  `arbeitsschicht` varchar(255) NOT NULL,
  `tarif` int NOT NULL,
  `fachgebiet` varchar(255) NOT NULL,
  `gewerkschaft` BOOLEAN NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauarbeiter_verwendet_Bautechnik`
--

CREATE TABLE `Bauarbeiter_verwendet_Bautechnik` (
  `mitarbeiter_id` BIGINT NOT NULL,
  `bautechnik_id` BIGINT NOT NULL,
  PRIMARY KEY(mitarbeiter_id, bautechnik_id)

);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauarbeiter_verwendet_Werkstoff`
--

CREATE TABLE `Bauarbeiter_verwendet_Werkstoff` (
  `mitarbeiter_id` BIGINT NOT NULL,
  `werkstoff_id` BIGINT NOT NULL,
  PRIMARY KEY(mitarbeiter_id, werkstoff_id)
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt`
--

CREATE TABLE `Bauprojekt` (
  `bauprojekt_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `startDatum` int NOT NULL,
  `endDatum` int NOT NULL,
  `gewinn` int NOT NULL,
  `kosten` int NOT NULL,
  `frist` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_benoetigt_Bautechnik`
--

CREATE TABLE `Bauprojekt_benoetigt_Bautechnik` (
  `bauprojekt_id` BIGINT NOT NULL,
  `bautechnik_id` BIGINT NOT NULL,
  PRIMARY KEY(bauprojekt_id, bautechnik_id)
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_benoetigt_Werkstoff`
--

CREATE TABLE `Bauprojekt_benoetigt_Werkstoff` (
  `bauprojekt_id` BIGINT NOT NULL,
  `werkstoff_id` BIGINT NOT NULL,
  PRIMARY KEY(bauprojekt_id, werkstoff_id)
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_bringtHervor_Bauschutt`
--

CREATE TABLE `Bauprojekt_bringtHervor_Bauschutt` (
  `bauprojekt_id` BIGINT NOT NULL,
  `bauschutt_id` BIGINT NOT NULL,
  PRIMARY KEY(bauprojekt_id, bauschutt_id)
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_stelltFertig_Immobilie`
--

CREATE TABLE `Bauprojekt_stelltFertig_Immobilie` (
  `immobilie_id` BIGINT NOT NULL PRIMARY KEY,
  `bauprojekt_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauschutt`
--

CREATE TABLE `Bauschutt` (
  `bauschutt_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `art` varchar(255) NOT NULL,
  `gewicht` int NOT NULL,
  `kilopreis` int NOT NULL,
  `menge` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bautechnik`
--

CREATE TABLE `Bautechnik` (
  `bautechnik_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `leihdauer` int NOT NULL,
  `art` varchar(255) NOT NULL,
  `marke` varchar(255) NOT NULL,
  `zustand` varchar(255) NOT NULL,
  `tagespreis` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauunternehmen`
--

CREATE TABLE `Bauunternehmen` (
  `unternehmen_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `telefonnummer` varchar(25) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauunternehmen_erhaelt_Anfrage`
--

CREATE TABLE `Bauunternehmen_erhaelt_Anfrage` (
  `anfrage_id` BIGINT NOT NULL PRIMARY KEY,
  `unternehmen_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Entsorgungsunternehmen`
--

CREATE TABLE `Entsorgungsunternehmen` (
  `entsorgung_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `telefonnummer` varchar(25) NOT NULL,
  `abholzeit` varchar(5) NOT NULL,
  `abholtag` int NOT NULL,
  `erfahrung` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Entsorgungsunternehmen_entsorgt_Bauschutt`
--

CREATE TABLE `Entsorgungsunternehmen_entsorgt_Bauschutt` (
  `bauschutt_id` BIGINT NOT NULL PRIMARY KEY,
  `entsorgung_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Immobilie`
--

CREATE TABLE `Immobilie` (
  `immobilie_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `farbe` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `flaeche` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ingenieur`
--

CREATE TABLE `Ingenieur` (
  `mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `selbststaendig` BOOLEAN NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ingenieur_gibtFrei_Immobilie`
--

CREATE TABLE `Ingenieur_gibtFrei_Immobilie` (
  `immobilie_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ingenieur_prueft_Skizze`
--

CREATE TABLE `Ingenieur_prueft_Skizze` (
  `skizze_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde`
--

CREATE TABLE `Kunde` (
  `kunde_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `vorname` varchar(255) NOT NULL,
  `nachname` varchar(255) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_bezahlt_Rechnung`
--

CREATE TABLE `Kunde_bezahlt_Rechnung` (
  `rechnung_id` BIGINT NOT NULL PRIMARY KEY,
  `kunde_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_erhaelt_Immobilie`
--

CREATE TABLE `Kunde_erhaelt_Immobilie` (
  `immobilie_id` BIGINT NOT NULL PRIMARY KEY,
  `kunde_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_erhaelt_Vertrag`
--

CREATE TABLE `Kunde_erhaelt_Vertrag` (
  `vertrag_id` BIGINT NOT NULL PRIMARY KEY,
  `kunde_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_erstellt_Anfrage`
--

CREATE TABLE `Kunde_erstellt_Anfrage` (
  `anfrage_id` BIGINT NOT NULL PRIMARY KEY,
  `kunde_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Mitarbeiter`
--

CREATE TABLE `Mitarbeiter` (
  `mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `vorname` varchar(255) NOT NULL,
  `nachname` varchar(255) NOT NULL,
  `berufsbezeichnung` varchar(255) NOT NULL,
  `berufserfahrung` int NOT NULL,
  `gehalt` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Mitarbeiter_arbeitetBei_Bauunternehmen`
--

CREATE TABLE `Mitarbeiter_arbeitetBei_Bauunternehmen` (
  `mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY,
  `unternehmen_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Mitarbeiter_beteiligtAn_Bauprojekt`
--

CREATE TABLE `Mitarbeiter_beteiligtAn_Bauprojekt` (
  `bauprojekt_id` BIGINT NOT NULL,
  `mitarbeiter_id` BIGINT NOT NULL,
  PRIMARY KEY(bauprojekt_id, mitarbeiter_id)
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter`
--

CREATE TABLE `Projektleiter` (
  `mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `gesamtProjektanzahl` int NOT NULL,
  `aktProjektanzahl` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_bearbeitet_Anfrage`
--

CREATE TABLE `Projektleiter_bearbeitet_Anfrage` (
  `anfrage_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_erstellt_Vertrag`
--

CREATE TABLE `Projektleiter_erstellt_Vertrag` (
  `vertrag_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_kontaktiert_Architekt`
--

CREATE TABLE `Projektleiter_kontaktiert_Architekt` (
  `arch_mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY,
  `proj_mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_kontaktiert_Werkstofflieferant`
--

CREATE TABLE `Projektleiter_kontaktiert_Werkstofflieferant` (
  `lieferant_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih`
--

CREATE TABLE `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih` (
  `verleih_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Rechnung`
--

CREATE TABLE `Rechnung` (
  `rechnung_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `preis` int NOT NULL,
  `zahlungsart` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `frist` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Sachbearbeiter`
--

CREATE TABLE `Sachbearbeiter` (
  `mitarbeiter_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `tarif` int NOT NULL,
  `anzRechnungen` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Sachbearbeiter_stelltAus_Rechnung`
--

CREATE TABLE `Sachbearbeiter_stelltAus_Rechnung` (
  `rechnung_id` BIGINT NOT NULL PRIMARY KEY,
  `mitarbeiter_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Skizze`
--

CREATE TABLE `Skizze` (
  `skizze_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `detailgrad` varchar(255) NOT NULL,
  `flaeche` int NOT NULL,
  `raum` varchar(255) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Vertrag`
--

CREATE TABLE `Vertrag` (
  `vertrag_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `preis` int NOT NULL,
  `unterschrift` BOOLEAN NOT NULL,
  `laufzeit` int NOT NULL,
  `gegenstand` varchar(255) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Vertrag_enthaelt_Skizze`
--

CREATE TABLE `Vertrag_enthaelt_Skizze` (
  `skizze_id` BIGINT NOT NULL PRIMARY KEY,
  `vertrag_id` BIGINT NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Werkstoff`
--

CREATE TABLE `Werkstoff` (
  `werkstoff_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `art` varchar(255) NOT NULL,
  `gewicht` int NOT NULL,
  `kilopreis` int NOT NULL,
  `menge` int NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Werkstofflieferant`
--

CREATE TABLE `Werkstofflieferant` (
  `lieferant_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `erfahrung` int NOT NULL,
  `telefonnummer` varchar(25) NOT NULL,
  `name` varchar(255) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Werkstofflieferant_liefert_Werkstoff`
--

CREATE TABLE `Werkstofflieferant_liefert_Werkstoff` (
  `lieferant_id` BIGINT NOT NULL,
  `werkstoff_id` BIGINT NOT NULL,
  PRIMARY KEY(lieferant_id, werkstoff_id)
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `WerkzeugUndMaschinenverleih`
--

CREATE TABLE `WerkzeugUndMaschinenverleih` (
  `verleih_id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `erfahrung` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `telefonnummer` varchar(25) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik`
--

CREATE TABLE `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik` (
  `verleih_id` BIGINT NOT NULL,
  `bautechnik_id` BIGINT NOT NULL,
  PRIMARY KEY(verleih_id, bautechnik_id)
);


COMMIT;
