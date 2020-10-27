
-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: mysql-db
-- Erstellungszeit: 17. Okt 2020 um 14:37
-- Server-Version: 8.0.21
-- PHP-Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Anfrage`
--

CREATE TABLE `Anfrage` (
  `anfrage_id` bigint NOT NULL,
  `anzRaeume` int,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL,
  `flaeche` int NOT NULL,
  `farbe` varchar(10) ,
  `preisvorstellung` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Anfrage`
--

INSERT INTO `Anfrage` (`anfrage_id`, `anzRaeume`, `straße`, `ort`, `plz`, `flaeche`, `farbe`, `preisvorstellung`) VALUES
(135874, 10, 'Waldstr. 10', 'Karlsruhe', '76133', 155, 'weiß', 450),
(135876, 5, 'Moltkestr. 5', 'Karlsruhe', '76137', 250, 'rot', 850),
(135877, 2, 'Lärchenweg 23', 'Neureut', '76227', 40, 'blau', 250);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Architekt`
--

CREATE TABLE `Architekt` (
  `mitarbeiter_id` bigint NOT NULL,
  `selbststaendig` BOOLEAN NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL,
  `fachrichtung` varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Architekt`
--

INSERT INTO `Architekt` (`mitarbeiter_id`, `selbststaendig`, `straße`, `ort`, `plz`, `fachrichtung`) VALUES
(1, 1, 'Lärchenweg 52', 'Neureut', '76227', 'Innenarchitekt'),
(3, 0, 'Goethestr. 190', 'Freiburg', '79098', 'Stadtplaner');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Architekt_entwirft_Skizze`
--

CREATE TABLE `Architekt_entwirft_Skizze` (
  `skizze_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Architekt_entwirft_Skizze`
--

INSERT INTO `Architekt_entwirft_Skizze` (`skizze_id`, `mitarbeiter_id`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauarbeiter`
--

CREATE TABLE `Bauarbeiter` (
  `mitarbeiter_id` bigint NOT NULL,
  `schichtleiter` BOOLEAN NOT NULL,
  `arbeitsschicht` varchar(255) NOT NULL,
  `tarif` int NOT NULL,
  `fachgebiet` varchar(255),
  `gewerkschaft` BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauarbeiter`
--

INSERT INTO `Bauarbeiter` (`mitarbeiter_id`, `schichtleiter`, `arbeitsschicht`, `tarif`, `fachgebiet`, `gewerkschaft`) VALUES
(4, 1, 'Vormittag', 4, 'Denkmale, historische Bauten', 1),
(5, 0, 'abends', 1, 'Metallbau', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauarbeiter_verwendet_Bautechnik`
--

CREATE TABLE `Bauarbeiter_verwendet_Bautechnik` (
  `mitarbeiter_id` bigint NOT NULL,
  `bautechnik_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauarbeiter_verwendet_Bautechnik`
--

INSERT INTO `Bauarbeiter_verwendet_Bautechnik` (`mitarbeiter_id`, `bautechnik_id`) VALUES
(4, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauarbeiter_verwendet_Werkstoff`
--

CREATE TABLE `Bauarbeiter_verwendet_Werkstoff` (
  `mitarbeiter_id` bigint NOT NULL,
  `werkstoff_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauarbeiter_verwendet_Werkstoff`
--

INSERT INTO `Bauarbeiter_verwendet_Werkstoff` (`mitarbeiter_id`, `werkstoff_id`) VALUES
(5, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt`
--

CREATE TABLE `Bauprojekt` (
  `bauprojekt_id` bigint NOT NULL,
  `startDatum` int NOT NULL,
  `endDatum` int NOT NULL,
  `gewinn` int NOT NULL,
  `kosten` int NOT NULL,
  `frist` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauprojekt`
--

INSERT INTO `Bauprojekt` (`bauprojekt_id`, `startDatum`, `endDatum`, `gewinn`, `kosten`, `frist`) VALUES
(1, 1012020, 31122023, 80000, 500000, 5),
(2, 1062021, 31122026, 120000, 900000, 10),
(3, 1082017, 31122020, 70000, 45000, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_benoetigt_Bautechnik`
--

CREATE TABLE `Bauprojekt_benoetigt_Bautechnik` (
  `bauprojekt_id` bigint NOT NULL,
  `bautechnik_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauprojekt_benoetigt_Bautechnik`
--

INSERT INTO `Bauprojekt_benoetigt_Bautechnik` (`bauprojekt_id`, `bautechnik_id`) VALUES
(1, 1),
(3, 1),
(1, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_benoetigt_Werkstoff`
--

CREATE TABLE `Bauprojekt_benoetigt_Werkstoff` (
  `bauprojekt_id` bigint NOT NULL,
  `werkstoff_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauprojekt_benoetigt_Werkstoff`
--

INSERT INTO `Bauprojekt_benoetigt_Werkstoff` (`bauprojekt_id`, `werkstoff_id`) VALUES
(1, 2),
(1, 3),
(2, 4),
(3, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_bringtHervor_Bauschutt`
--

CREATE TABLE `Bauprojekt_bringtHervor_Bauschutt` (
  `bauprojekt_id` bigint NOT NULL,
  `bauschutt_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauprojekt_bringtHervor_Bauschutt`
--

INSERT INTO `Bauprojekt_bringtHervor_Bauschutt` (`bauprojekt_id`, `bauschutt_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauprojekt_stelltFertig_Immobilie`
--

CREATE TABLE `Bauprojekt_stelltFertig_Immobilie` (
  `immobilie_id` bigint NOT NULL,
  `bauprojekt_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauprojekt_stelltFertig_Immobilie`
--

INSERT INTO `Bauprojekt_stelltFertig_Immobilie` (`immobilie_id`, `bauprojekt_id`) VALUES
(1, 1),
(3, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauschutt`
--

CREATE TABLE `Bauschutt` (
  `bauschutt_id` bigint NOT NULL,
  `art` varchar(255) NOT NULL,
  `gewicht` int NOT NULL,
  `kilopreis` int NOT NULL,
  `menge` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauschutt`
--

INSERT INTO `Bauschutt` (`bauschutt_id`, `art`, `gewicht`, `kilopreis`, `menge`) VALUES
(1, 'Backsteine', 1000, 10, 1000),
(2, 'Fliesen', 2000, 15, 2000),
(3, 'Ziegel', 500, 20, 500),
(4, 'Keramiken', 3000, 25, 3000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bautechnik`
--

CREATE TABLE `Bautechnik` (
  `bautechnik_id` bigint NOT NULL,
  `leihdauer` int NOT NULL,
  `art` varchar(255) NOT NULL,
  `marke` varchar(255),
  `zustand` varchar(255) NOT NULL,
  `tagespreis` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bautechnik`
--

INSERT INTO `Bautechnik` (`bautechnik_id`, `leihdauer`, `art`, `marke`, `zustand`, `tagespreis`) VALUES
(1, 5, 'Asphaltiergerät', 'Asphaltco', 'wie neu', 500),
(2, 3, 'Betonmischer', 'Beton4U', 'gebraucht', 300),
(3, 2, 'Walze', 'Bosch', 'sehr gut', 100);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauunternehmen`
--

CREATE TABLE `Bauunternehmen` (
  `unternehmen_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `telefonnummer` varchar(25) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauunternehmen`
--

INSERT INTO `Bauunternehmen` (`unternehmen_id`, `name`, `telefonnummer`, `straße`, `ort`, `plz`) VALUES
(1, 'Müller GmbH & Co. KG', '072198634788', 'Maurerweg 9', 'Bauhausen', '76612'),
(2, 'Mustermann KG', '0165333987', 'Bäckerstr. 10', 'Neumühlheim', '33567'),
(3, 'Bauer & Söhne ', '06213574711', 'Berliner Allee', 'Frankfurt', '60306');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bauunternehmen_erhaelt_Anfrage`
--

CREATE TABLE `Bauunternehmen_erhaelt_Anfrage` (
  `anfrage_id` bigint NOT NULL,
  `unternehmen_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Bauunternehmen_erhaelt_Anfrage`
--

INSERT INTO `Bauunternehmen_erhaelt_Anfrage` (`anfrage_id`, `unternehmen_id`) VALUES
(135876, 1),
(135874, 2),
(135877, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Entsorgungsunternehmen`
--

CREATE TABLE `Entsorgungsunternehmen` (
  `entsorgung_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `telefonnummer` varchar(25) NOT NULL,
  `abholzeit` varchar(5) NOT NULL,
  `abholtag` int NOT NULL,
  `erfahrung` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Entsorgungsunternehmen`
--

INSERT INTO `Entsorgungsunternehmen` (`entsorgung_id`, `name`, `telefonnummer`, `abholzeit`, `abholtag`, `erfahrung`) VALUES
(1, 'RecyclePartners GmbH & Co. KG', '0621770815', '8:00', 20102020, 10),
(2, 'Stadtwerke Karlsruhe', '0721333456', '9:15', 1112020, 30),
(3, 'Meyer & Wolfram Partners', '0175345987', '16:00', 15112020, 15);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Entsorgungsunternehmen_entsorgt_Bauschutt`
--

CREATE TABLE `Entsorgungsunternehmen_entsorgt_Bauschutt` (
  `bauschutt_id` bigint NOT NULL,
  `entsorgung_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Entsorgungsunternehmen_entsorgt_Bauschutt`
--

INSERT INTO `Entsorgungsunternehmen_entsorgt_Bauschutt` (`bauschutt_id`, `entsorgung_id`) VALUES
(1, 1),
(4, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Immobilie`
--

CREATE TABLE `Immobilie` (
  `immobilie_id` bigint NOT NULL,
  `farbe` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `flaeche` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Immobilie`
--

INSERT INTO `Immobilie` (`immobilie_id`, `farbe`, `status`, `flaeche`) VALUES
(1, 'rot', 'fertiggestellt', 450),
(2, 'weiß', 'noch nicht begonnen', 200),
(3, 'blau', 'in Arbeit', 300);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ingenieur`
--

CREATE TABLE `Ingenieur` (
  `mitarbeiter_id` bigint NOT NULL,
  `selbststaendig` BOOLEAN NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Ingenieur`
--

INSERT INTO `Ingenieur` (`mitarbeiter_id`, `selbststaendig`, `straße`, `ort`, `plz`) VALUES
(6, 1, 'Rotkehlchenstr. 66', 'Ettlingen', '76199');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ingenieur_gibtFrei_Immobilie`
--

CREATE TABLE `Ingenieur_gibtFrei_Immobilie` (
  `immobilie_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Ingenieur_gibtFrei_Immobilie`
--

INSERT INTO `Ingenieur_gibtFrei_Immobilie` (`immobilie_id`, `mitarbeiter_id`) VALUES
(3, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ingenieur_prueft_Skizze`
--

CREATE TABLE `Ingenieur_prueft_Skizze` (
  `skizze_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Ingenieur_prueft_Skizze`
--

INSERT INTO `Ingenieur_prueft_Skizze` (`skizze_id`, `mitarbeiter_id`) VALUES
(2, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde`
--

CREATE TABLE `Kunde` (
  `kunde_id` bigint NOT NULL,
  `vorname` varchar(255) NOT NULL,
  `nachname` varchar(255) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Kunde`
--

INSERT INTO `Kunde` (`kunde_id`, `vorname`, `nachname`, `straße`, `ort`, `plz`) VALUES
(1, 'Max', 'Mustermann', 'Mozartstr. 99', 'Neuburgweiher', '86213'),
(2, 'Bertha', 'Benz', 'Daimlerstr. 8', 'Stuttgart', '70173'),
(3, 'Claudia', 'Neuer', 'Tannenweg 19', 'Stutensee', '76297');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_bezahlt_Rechnung`
--

CREATE TABLE `Kunde_bezahlt_Rechnung` (
  `rechnung_id` bigint NOT NULL,
  `kunde_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_erhaelt_Immobilie`
--

CREATE TABLE `Kunde_erhaelt_Immobilie` (
  `immobilie_id` bigint NOT NULL,
  `kunde_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Kunde_erhaelt_Immobilie`
--

INSERT INTO `Kunde_erhaelt_Immobilie` (`immobilie_id`, `kunde_id`) VALUES
(1, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_erhaelt_Vertrag`
--

CREATE TABLE `Kunde_erhaelt_Vertrag` (
  `vertrag_id` bigint NOT NULL,
  `kunde_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Kunde_erhaelt_Vertrag`
--

INSERT INTO `Kunde_erhaelt_Vertrag` (`vertrag_id`, `kunde_id`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Kunde_erstellt_Anfrage`
--

CREATE TABLE `Kunde_erstellt_Anfrage` (
  `anfrage_id` bigint NOT NULL,
  `kunde_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Kunde_erstellt_Anfrage`
--

INSERT INTO `Kunde_erstellt_Anfrage` (`anfrage_id`, `kunde_id`) VALUES
(135876, 1),
(135877, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Mitarbeiter`
--

CREATE TABLE `Mitarbeiter` (
  `mitarbeiter_id` bigint NOT NULL,
  `vorname` varchar(255) NOT NULL,
  `nachname` varchar(255) NOT NULL,
  `berufsbezeichnung` varchar(255),
  `berufserfahrung` int,
  `gehalt` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Mitarbeiter`
--

INSERT INTO `Mitarbeiter` (`mitarbeiter_id`, `vorname`, `nachname`, `berufsbezeichnung`, `berufserfahrung`, `gehalt`) VALUES
(1, 'Hubert', 'Müller', 'Architekt', 10, 4),
(2, 'Klaus', 'Meyer', 'Sachbearbeiter', 25, 2000),
(3, 'Sandra', 'Lehmann', 'Architekt', 2, 3000),
(4, 'Herbert', 'Schmidt', 'Bauarbeiter', 30, 4000),
(5, 'Lena', 'Neubauer', 'Bauarbeiter', 2, 2000),
(6, 'Wolfgang', 'Müller', 'Ingenieur', 5, 4500),
(7, 'Cornelia', 'Funke', 'Projektleiter', 5, 6000),
(8, 'Hans', 'Lebwohl', 'Projektleiter', 5, 5200),
(9, 'Rebecca', 'Müller', 'Sachbearbeiter', 4, 3500),
(10, 'Peter', 'Leblang', 'Sachbearbeiter', 25, 5000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Mitarbeiter_arbeitetBei_Bauunternehmen`
--

CREATE TABLE `Mitarbeiter_arbeitetBei_Bauunternehmen` (
  `mitarbeiter_id` bigint NOT NULL,
  `unternehmen_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Mitarbeiter_arbeitetBei_Bauunternehmen`
--

INSERT INTO `Mitarbeiter_arbeitetBei_Bauunternehmen` (`mitarbeiter_id`, `unternehmen_id`) VALUES
(2, 1),
(5, 1),
(3, 2),
(1, 3),
(4, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Mitarbeiter_beteiligtAn_Bauprojekt`
--

CREATE TABLE `Mitarbeiter_beteiligtAn_Bauprojekt` (
  `bauprojekt_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Mitarbeiter_beteiligtAn_Bauprojekt`
--

INSERT INTO `Mitarbeiter_beteiligtAn_Bauprojekt` (`bauprojekt_id`, `mitarbeiter_id`) VALUES
(3, 2),
(2, 3),
(1, 4),
(3, 4),
(2, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter`
--

CREATE TABLE `Projektleiter` (
  `mitarbeiter_id` bigint NOT NULL,
  `gesamtProjektanzahl` int NOT NULL,
  `aktProjektanzahl` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Projektleiter`
--

INSERT INTO `Projektleiter` (`mitarbeiter_id`, `gesamtProjektanzahl`, `aktProjektanzahl`) VALUES
(7, 5, 2),
(8, 3, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_bearbeitet_Anfrage`
--

CREATE TABLE `Projektleiter_bearbeitet_Anfrage` (
  `anfrage_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Projektleiter_bearbeitet_Anfrage`
--

INSERT INTO `Projektleiter_bearbeitet_Anfrage` (`anfrage_id`, `mitarbeiter_id`) VALUES
(135877, 7),
(135874, 8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_erstellt_Vertrag`
--

CREATE TABLE `Projektleiter_erstellt_Vertrag` (
  `vertrag_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Projektleiter_erstellt_Vertrag`
--

INSERT INTO `Projektleiter_erstellt_Vertrag` (`vertrag_id`, `mitarbeiter_id`) VALUES
(2, 7),
(1, 8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_kontaktiert_Architekt`
--

CREATE TABLE `Projektleiter_kontaktiert_Architekt` (
  `arch_mitarbeiter_id` bigint NOT NULL,
  `proj_mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Projektleiter_kontaktiert_Architekt`
--

INSERT INTO `Projektleiter_kontaktiert_Architekt` (`arch_mitarbeiter_id`, `proj_mitarbeiter_id`) VALUES
(1, 7),
(3, 8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_kontaktiert_Werkstofflieferant`
--

CREATE TABLE `Projektleiter_kontaktiert_Werkstofflieferant` (
  `lieferant_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Projektleiter_kontaktiert_Werkstofflieferant`
--

INSERT INTO `Projektleiter_kontaktiert_Werkstofflieferant` (`lieferant_id`, `mitarbeiter_id`) VALUES
(1, 8),
(2, 8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih`
--

CREATE TABLE `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih` (
  `verleih_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih`
--

INSERT INTO `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih` (`verleih_id`, `mitarbeiter_id`) VALUES
(1, 8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Rechnung`
--

CREATE TABLE `Rechnung` (
  `rechnung_id` bigint NOT NULL,
  `preis` int NOT NULL,
  `zahlungsart` varchar(255),
  `status` varchar(255) NOT NULL,
  `frist` int NOT NULL,
  `vertrag_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Rechnung`
--

INSERT INTO `Rechnung` (`rechnung_id`, `preis`, `zahlungsart`, `status`, `frist`, `vertrag_id`) VALUES
(1, 450000, 'bar', 'offen', 4, 1),
(2, 500000, 'in Raten', 'offen', 15, 2),
(3, 800000, 'ÜBerweisung', 'beglichen', 0, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Sachbearbeiter`
--

CREATE TABLE `Sachbearbeiter` (
  `mitarbeiter_id` bigint NOT NULL,
  `tarif` int NOT NULL,
  `anzRechnungen` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Sachbearbeiter`
--

INSERT INTO `Sachbearbeiter` (`mitarbeiter_id`, `tarif`, `anzRechnungen`) VALUES
(2, 4, 5000),
(9, 2, 2500),
(10, 6, 10000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Sachbearbeiter_stelltAus_Rechnung`
--

CREATE TABLE `Sachbearbeiter_stelltAus_Rechnung` (
  `rechnung_id` bigint NOT NULL,
  `mitarbeiter_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Sachbearbeiter_stelltAus_Rechnung`
--

INSERT INTO `Sachbearbeiter_stelltAus_Rechnung` (`rechnung_id`, `mitarbeiter_id`) VALUES
(3, 2),
(2, 9),
(1, 10);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Skizze`
--

CREATE TABLE `Skizze` (
  `skizze_id` bigint NOT NULL,
  `detailgrad` varchar(255),
  `flaeche` int,
  `raum` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Skizze`
--

INSERT INTO `Skizze` (`skizze_id`, `detailgrad`, `flaeche`, `raum`) VALUES
(1, 'grob', 250, 'Wohnzimmer'),
(2, 'detailliert', 250, 'Wohnzimmer');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Vertrag`
--

CREATE TABLE `Vertrag` (
  `vertrag_id` bigint NOT NULL,
  `preis` int NOT NULL,
  `unterschrift` BOOLEAN NOT NULL,
  `laufzeit` int NOT NULL,
  `gegenstand` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Vertrag`
--

INSERT INTO `Vertrag` (`vertrag_id`, `preis`, `unterschrift`, `laufzeit`, `gegenstand`) VALUES
(1, 450000, 1, 5, 'Immobilie'),
(2, 500000, 1, 10, 'Einfamilienhaus'),
(3, 800000, 1, 10, 'Penthouse-Wohnung');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Vertrag_enthaelt_Skizze`
--

CREATE TABLE `Vertrag_enthaelt_Skizze` (
  `skizze_id` bigint NOT NULL,
  `vertrag_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Vertrag_enthaelt_Skizze`
--

INSERT INTO `Vertrag_enthaelt_Skizze` (`skizze_id`, `vertrag_id`) VALUES
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Werkstoff`
--

CREATE TABLE `Werkstoff` (
  `werkstoff_id` bigint NOT NULL,
  `art` varchar(255) NOT NULL,
  `gewicht` int NOT NULL,
  `kilopreis` int NOT NULL,
  `menge` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Werkstoff`
--

INSERT INTO `Werkstoff` (`werkstoff_id`, `art`, `gewicht`, `kilopreis`, `menge`) VALUES
(1, 'Kupfer', 100, 200, 100),
(2, 'Beton', 1000, 50, 1000),
(3, 'Asphalt', 500, 35, 500),
(4, 'Eisen', 500, 50, 500);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Werkstofflieferant`
--

CREATE TABLE `Werkstofflieferant` (
  `lieferant_id` bigint NOT NULL,
  `erfahrung` int,
  `telefonnummer` varchar(25) NOT NULL,
  `name` varchar(255) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Werkstofflieferant`
--

INSERT INTO `Werkstofflieferant` (`lieferant_id`, `erfahrung`, `telefonnummer`, `name`, `straße`, `ort`, `plz`) VALUES
(1, 10, '0721888567', 'Lieferwerkstatt24', 'Ostring 13', 'Karlsruhe', '76133'),
(2, 5, '0175435678', 'MisterWerkstoff GmbH & Co. KG', 'Bergstr. 291', 'Karlsruhe', '76137');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Werkstofflieferant_liefert_Werkstoff`
--

CREATE TABLE `Werkstofflieferant_liefert_Werkstoff` (
  `lieferant_id` bigint NOT NULL,
  `werkstoff_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `Werkstofflieferant_liefert_Werkstoff`
--

INSERT INTO `Werkstofflieferant_liefert_Werkstoff` (`lieferant_id`, `werkstoff_id`) VALUES
(1, 2),
(2, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `WerkzeugUndMaschinenverleih`
--

CREATE TABLE `WerkzeugUndMaschinenverleih` (
  `verleih_id` bigint NOT NULL,
  `erfahrung` int,
  `name` varchar(255) NOT NULL,
  `telefonnummer` varchar(25) NOT NULL,
  `straße` varchar(255) NOT NULL,
  `ort` varchar(255) NOT NULL,
  `plz` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `WerkzeugUndMaschinenverleih`
--

INSERT INTO `WerkzeugUndMaschinenverleih` (`verleih_id`, `erfahrung`, `name`, `telefonnummer`, `straße`, `ort`, `plz`) VALUES
(1, 15, 'LeihdeinWerkzeug GmbH & Co. KG', '0813457883', 'Buchenweg 13', 'Neuburgweiher', '33187');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik`
--

CREATE TABLE `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik` (
  `verleih_id` bigint NOT NULL,
  `bautechnik_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik`
--

INSERT INTO `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik` (`verleih_id`, `bautechnik_id`) VALUES
(1, 1),
(1, 2),
(1, 3);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `Anfrage`
--
ALTER TABLE `Anfrage`
  ADD PRIMARY KEY (`anfrage_id`);

--
-- Indizes für die Tabelle `Architekt`
--
ALTER TABLE `Architekt`
  ADD PRIMARY KEY (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Architekt_entwirft_Skizze`
--
ALTER TABLE `Architekt_entwirft_Skizze`
  ADD PRIMARY KEY (`skizze_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Bauarbeiter`
--
ALTER TABLE `Bauarbeiter`
  ADD PRIMARY KEY (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Bauarbeiter_verwendet_Bautechnik`
--
ALTER TABLE `Bauarbeiter_verwendet_Bautechnik`
  ADD PRIMARY KEY (`mitarbeiter_id`,`bautechnik_id`),
  ADD KEY `bautechnik_id` (`bautechnik_id`);

--
-- Indizes für die Tabelle `Bauarbeiter_verwendet_Werkstoff`
--
ALTER TABLE `Bauarbeiter_verwendet_Werkstoff`
  ADD PRIMARY KEY (`mitarbeiter_id`,`werkstoff_id`),
  ADD KEY `werkstoff_id` (`werkstoff_id`);

--
-- Indizes für die Tabelle `Bauprojekt`
--
ALTER TABLE `Bauprojekt`
  ADD PRIMARY KEY (`bauprojekt_id`);

--
-- Indizes für die Tabelle `Bauprojekt_benoetigt_Bautechnik`
--
ALTER TABLE `Bauprojekt_benoetigt_Bautechnik`
  ADD PRIMARY KEY (`bauprojekt_id`,`bautechnik_id`),
  ADD KEY `bautechnik_id` (`bautechnik_id`);

--
-- Indizes für die Tabelle `Bauprojekt_benoetigt_Werkstoff`
--
ALTER TABLE `Bauprojekt_benoetigt_Werkstoff`
  ADD PRIMARY KEY (`bauprojekt_id`,`werkstoff_id`),
  ADD KEY `werkstoff_id` (`werkstoff_id`);

--
-- Indizes für die Tabelle `Bauprojekt_bringtHervor_Bauschutt`
--
ALTER TABLE `Bauprojekt_bringtHervor_Bauschutt`
  ADD PRIMARY KEY (`bauprojekt_id`,`bauschutt_id`),
  ADD KEY `bauschutt_id` (`bauschutt_id`);

--
-- Indizes für die Tabelle `Bauprojekt_stelltFertig_Immobilie`
--
ALTER TABLE `Bauprojekt_stelltFertig_Immobilie`
  ADD PRIMARY KEY (`immobilie_id`),
  ADD KEY `bauprojekt_id` (`bauprojekt_id`);

--
-- Indizes für die Tabelle `Bauschutt`
--
ALTER TABLE `Bauschutt`
  ADD PRIMARY KEY (`bauschutt_id`);

--
-- Indizes für die Tabelle `Bautechnik`
--
ALTER TABLE `Bautechnik`
  ADD PRIMARY KEY (`bautechnik_id`);

--
-- Indizes für die Tabelle `Bauunternehmen`
--
ALTER TABLE `Bauunternehmen`
  ADD PRIMARY KEY (`unternehmen_id`);

--
-- Indizes für die Tabelle `Bauunternehmen_erhaelt_Anfrage`
--
ALTER TABLE `Bauunternehmen_erhaelt_Anfrage`
  ADD PRIMARY KEY (`anfrage_id`),
  ADD KEY `unternehmen_id` (`unternehmen_id`);

--
-- Indizes für die Tabelle `Entsorgungsunternehmen`
--
ALTER TABLE `Entsorgungsunternehmen`
  ADD PRIMARY KEY (`entsorgung_id`);

--
-- Indizes für die Tabelle `Entsorgungsunternehmen_entsorgt_Bauschutt`
--
ALTER TABLE `Entsorgungsunternehmen_entsorgt_Bauschutt`
  ADD PRIMARY KEY (`bauschutt_id`),
  ADD KEY `entsorgung_id` (`entsorgung_id`);

--
-- Indizes für die Tabelle `Immobilie`
--
ALTER TABLE `Immobilie`
  ADD PRIMARY KEY (`immobilie_id`);

--
-- Indizes für die Tabelle `Ingenieur`
--
ALTER TABLE `Ingenieur`
  ADD PRIMARY KEY (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Ingenieur_gibtFrei_Immobilie`
--
ALTER TABLE `Ingenieur_gibtFrei_Immobilie`
  ADD PRIMARY KEY (`immobilie_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Ingenieur_prueft_Skizze`
--
ALTER TABLE `Ingenieur_prueft_Skizze`
  ADD PRIMARY KEY (`skizze_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Kunde`
--
ALTER TABLE `Kunde`
  ADD PRIMARY KEY (`kunde_id`);

--
-- Indizes für die Tabelle `Kunde_bezahlt_Rechnung`
--
ALTER TABLE `Kunde_bezahlt_Rechnung`
  ADD PRIMARY KEY (`rechnung_id`),
  ADD KEY `kunde_id` (`kunde_id`);

--
-- Indizes für die Tabelle `Kunde_erhaelt_Immobilie`
--
ALTER TABLE `Kunde_erhaelt_Immobilie`
  ADD PRIMARY KEY (`immobilie_id`),
  ADD KEY `kunde_id` (`kunde_id`);

--
-- Indizes für die Tabelle `Kunde_erhaelt_Vertrag`
--
ALTER TABLE `Kunde_erhaelt_Vertrag`
  ADD PRIMARY KEY (`vertrag_id`),
  ADD KEY `kunde_id` (`kunde_id`);

--
-- Indizes für die Tabelle `Kunde_erstellt_Anfrage`
--
ALTER TABLE `Kunde_erstellt_Anfrage`
  ADD PRIMARY KEY (`anfrage_id`),
  ADD KEY `kunde_id` (`kunde_id`);

--
-- Indizes für die Tabelle `Mitarbeiter`
--
ALTER TABLE `Mitarbeiter`
  ADD PRIMARY KEY (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Mitarbeiter_arbeitetBei_Bauunternehmen`
--
ALTER TABLE `Mitarbeiter_arbeitetBei_Bauunternehmen`
  ADD PRIMARY KEY (`mitarbeiter_id`),
  ADD KEY `unternehmen_id` (`unternehmen_id`);

--
-- Indizes für die Tabelle `Mitarbeiter_beteiligtAn_Bauprojekt`
--
ALTER TABLE `Mitarbeiter_beteiligtAn_Bauprojekt`
  ADD PRIMARY KEY (`bauprojekt_id`,`mitarbeiter_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Projektleiter`
--
ALTER TABLE `Projektleiter`
  ADD PRIMARY KEY (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Projektleiter_bearbeitet_Anfrage`
--
ALTER TABLE `Projektleiter_bearbeitet_Anfrage`
  ADD PRIMARY KEY (`anfrage_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Projektleiter_erstellt_Vertrag`
--
ALTER TABLE `Projektleiter_erstellt_Vertrag`
  ADD PRIMARY KEY (`vertrag_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Projektleiter_kontaktiert_Architekt`
--
ALTER TABLE `Projektleiter_kontaktiert_Architekt`
  ADD PRIMARY KEY (`arch_mitarbeiter_id`),
  ADD KEY `proj_mitarbeiter_id` (`proj_mitarbeiter_id`);

--
-- Indizes für die Tabelle `Projektleiter_kontaktiert_Werkstofflieferant`
--
ALTER TABLE `Projektleiter_kontaktiert_Werkstofflieferant`
  ADD PRIMARY KEY (`lieferant_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih`
--
ALTER TABLE `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih`
  ADD PRIMARY KEY (`verleih_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Rechnung`
--
ALTER TABLE `Rechnung`
  ADD PRIMARY KEY (`rechnung_id`),
  ADD KEY `vertrag_id` (`vertrag_id`);

--
-- Indizes für die Tabelle `Sachbearbeiter`
--
ALTER TABLE `Sachbearbeiter`
  ADD PRIMARY KEY (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Sachbearbeiter_stelltAus_Rechnung`
--
ALTER TABLE `Sachbearbeiter_stelltAus_Rechnung`
  ADD PRIMARY KEY (`rechnung_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `Skizze`
--
ALTER TABLE `Skizze`
  ADD PRIMARY KEY (`skizze_id`);

--
-- Indizes für die Tabelle `Vertrag`
--
ALTER TABLE `Vertrag`
  ADD PRIMARY KEY (`vertrag_id`);

--
-- Indizes für die Tabelle `Vertrag_enthaelt_Skizze`
--
ALTER TABLE `Vertrag_enthaelt_Skizze`
  ADD PRIMARY KEY (`skizze_id`),
  ADD KEY `vertrag_id` (`vertrag_id`);

--
-- Indizes für die Tabelle `Werkstoff`
--
ALTER TABLE `Werkstoff`
  ADD PRIMARY KEY (`werkstoff_id`);

--
-- Indizes für die Tabelle `Werkstofflieferant`
--
ALTER TABLE `Werkstofflieferant`
  ADD PRIMARY KEY (`lieferant_id`);

--
-- Indizes für die Tabelle `Werkstofflieferant_liefert_Werkstoff`
--
ALTER TABLE `Werkstofflieferant_liefert_Werkstoff`
  ADD PRIMARY KEY (`lieferant_id`,`werkstoff_id`),
  ADD KEY `werkstoff_id` (`werkstoff_id`);

--
-- Indizes für die Tabelle `WerkzeugUndMaschinenverleih`
--
ALTER TABLE `WerkzeugUndMaschinenverleih`
  ADD PRIMARY KEY (`verleih_id`);

--
-- Indizes für die Tabelle `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik`
--
ALTER TABLE `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik`
  ADD PRIMARY KEY (`verleih_id`,`bautechnik_id`),
  ADD KEY `bautechnik_id` (`bautechnik_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `Anfrage`
--
ALTER TABLE `Anfrage`
  MODIFY `anfrage_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135878;

--
-- AUTO_INCREMENT für Tabelle `Architekt`
--
ALTER TABLE `Mitarbeiter`
  MODIFY `mitarbeiter_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--

--
-- AUTO_INCREMENT für Tabelle `Bauprojekt`
--
ALTER TABLE `Bauprojekt`
  MODIFY `bauprojekt_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `Bauschutt`
--
ALTER TABLE `Bauschutt`
  MODIFY `bauschutt_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `Bautechnik`
--
ALTER TABLE `Bautechnik`
  MODIFY `bautechnik_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `Bauunternehmen`
--
ALTER TABLE `Bauunternehmen`
  MODIFY `unternehmen_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `Entsorgungsunternehmen`
--
ALTER TABLE `Entsorgungsunternehmen`
  MODIFY `entsorgung_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `Immobilie`
--
ALTER TABLE `Immobilie`
  MODIFY `immobilie_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;


--
-- AUTO_INCREMENT für Tabelle `Kunde`
--
ALTER TABLE `Kunde`
  MODIFY `kunde_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;


--
-- AUTO_INCREMENT für Tabelle `Rechnung`
--
ALTER TABLE `Rechnung`
  MODIFY `rechnung_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;


--
-- AUTO_INCREMENT für Tabelle `Skizze`
--
ALTER TABLE `Skizze`
  MODIFY `skizze_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `Vertrag`
--
ALTER TABLE `Vertrag`
  MODIFY `vertrag_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `Werkstoff`
--
ALTER TABLE `Werkstoff`
  MODIFY `werkstoff_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `Werkstofflieferant`
--
ALTER TABLE `Werkstofflieferant`
  MODIFY `lieferant_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `WerkzeugUndMaschinenverleih`
--
ALTER TABLE `WerkzeugUndMaschinenverleih`
  MODIFY `verleih_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `Architekt`
--
ALTER TABLE `Architekt`
  ADD CONSTRAINT `Architekt_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Mitarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Architekt_entwirft_Skizze`
--
ALTER TABLE `Architekt_entwirft_Skizze`
  ADD CONSTRAINT `Architekt_entwirft_Skizze_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Architekt` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Architekt_entwirft_Skizze_ibfk_2` FOREIGN KEY (`skizze_id`) REFERENCES `Skizze` (`skizze_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauarbeiter`
--
ALTER TABLE `Bauarbeiter`
  ADD CONSTRAINT `Bauarbeiter_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Mitarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauarbeiter_verwendet_Bautechnik`
--
ALTER TABLE `Bauarbeiter_verwendet_Bautechnik`
  ADD CONSTRAINT `Bauarbeiter_verwendet_Bautechnik_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Bauarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Bauarbeiter_verwendet_Bautechnik_ibfk_2` FOREIGN KEY (`bautechnik_id`) REFERENCES `Bautechnik` (`bautechnik_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauarbeiter_verwendet_Werkstoff`
--
ALTER TABLE `Bauarbeiter_verwendet_Werkstoff`
  ADD CONSTRAINT `Bauarbeiter_verwendet_Werkstoff_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Bauarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Bauarbeiter_verwendet_Werkstoff_ibfk_2` FOREIGN KEY (`werkstoff_id`) REFERENCES `Werkstoff` (`werkstoff_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauprojekt_benoetigt_Bautechnik`
--
ALTER TABLE `Bauprojekt_benoetigt_Bautechnik`
  ADD CONSTRAINT `Bauprojekt_benoetigt_Bautechnik_ibfk_1` FOREIGN KEY (`bauprojekt_id`) REFERENCES `Bauprojekt` (`bauprojekt_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Bauprojekt_benoetigt_Bautechnik_ibfk_2` FOREIGN KEY (`bautechnik_id`) REFERENCES `Bautechnik` (`bautechnik_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauprojekt_benoetigt_Werkstoff`
--
ALTER TABLE `Bauprojekt_benoetigt_Werkstoff`
  ADD CONSTRAINT `Bauprojekt_benoetigt_Werkstoff_ibfk_1` FOREIGN KEY (`bauprojekt_id`) REFERENCES `Bauprojekt` (`bauprojekt_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Bauprojekt_benoetigt_Werkstoff_ibfk_2` FOREIGN KEY (`werkstoff_id`) REFERENCES `Werkstoff` (`werkstoff_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauprojekt_bringtHervor_Bauschutt`
--
ALTER TABLE `Bauprojekt_bringtHervor_Bauschutt`
  ADD CONSTRAINT `Bauprojekt_bringtHervor_Bauschutt_ibfk_1` FOREIGN KEY (`bauprojekt_id`) REFERENCES `Bauprojekt` (`bauprojekt_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Bauprojekt_bringtHervor_Bauschutt_ibfk_2` FOREIGN KEY (`bauschutt_id`) REFERENCES `Bauschutt` (`bauschutt_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauprojekt_stelltFertig_Immobilie`
--
ALTER TABLE `Bauprojekt_stelltFertig_Immobilie`
  ADD CONSTRAINT `Bauprojekt_stelltFertig_Immobilie_ibfk_1` FOREIGN KEY (`bauprojekt_id`) REFERENCES `Bauprojekt` (`bauprojekt_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Bauprojekt_stelltFertig_Immobilie_ibfk_2` FOREIGN KEY (`immobilie_id`) REFERENCES `Immobilie` (`immobilie_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Bauunternehmen_erhaelt_Anfrage`
--
ALTER TABLE `Bauunternehmen_erhaelt_Anfrage`
  ADD CONSTRAINT `Bauunternehmen_erhaelt_Anfrage_ibfk_1` FOREIGN KEY (`anfrage_id`) REFERENCES `Anfrage` (`anfrage_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Bauunternehmen_erhaelt_Anfrage_ibfk_2` FOREIGN KEY (`unternehmen_id`) REFERENCES `Bauunternehmen` (`unternehmen_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Entsorgungsunternehmen_entsorgt_Bauschutt`
--
ALTER TABLE `Entsorgungsunternehmen_entsorgt_Bauschutt`
  ADD CONSTRAINT `Entsorgungsunternehmen_entsorgt_Bauschutt_ibfk_1` FOREIGN KEY (`bauschutt_id`) REFERENCES `Bauschutt` (`bauschutt_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Entsorgungsunternehmen_entsorgt_Bauschutt_ibfk_2` FOREIGN KEY (`entsorgung_id`) REFERENCES `Entsorgungsunternehmen` (`entsorgung_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Ingenieur`
--
ALTER TABLE `Ingenieur`
  ADD CONSTRAINT `Ingenieur_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Mitarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Ingenieur_gibtFrei_Immobilie`
--
ALTER TABLE `Ingenieur_gibtFrei_Immobilie`
  ADD CONSTRAINT `Ingenieur_gibtFrei_Immobilie_ibfk_1` FOREIGN KEY (`immobilie_id`) REFERENCES `Immobilie` (`immobilie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Ingenieur_gibtFrei_Immobilie_ibfk_2` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Ingenieur` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Ingenieur_prueft_Skizze`
--
ALTER TABLE `Ingenieur_prueft_Skizze`
  ADD CONSTRAINT `Ingenieur_prueft_Skizze_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Ingenieur` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Ingenieur_prueft_Skizze_ibfk_2` FOREIGN KEY (`skizze_id`) REFERENCES `Skizze` (`skizze_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Kunde_bezahlt_Rechnung`
--
ALTER TABLE `Kunde_bezahlt_Rechnung`
  ADD CONSTRAINT `Kunde_bezahlt_Rechnung_ibfk_1` FOREIGN KEY (`kunde_id`) REFERENCES `Kunde` (`kunde_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Kunde_bezahlt_Rechnung_ibfk_2` FOREIGN KEY (`rechnung_id`) REFERENCES `Rechnung` (`rechnung_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Kunde_erhaelt_Immobilie`
--
ALTER TABLE `Kunde_erhaelt_Immobilie`
  ADD CONSTRAINT `Kunde_erhaelt_Immobilie_ibfk_1` FOREIGN KEY (`kunde_id`) REFERENCES `Kunde` (`kunde_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Kunde_erhaelt_Immobilie_ibfk_2` FOREIGN KEY (`immobilie_id`) REFERENCES `Immobilie` (`immobilie_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Kunde_erhaelt_Vertrag`
--
ALTER TABLE `Kunde_erhaelt_Vertrag`
  ADD CONSTRAINT `Kunde_erhaelt_Vertrag_ibfk_1` FOREIGN KEY (`kunde_id`) REFERENCES `Kunde` (`kunde_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Kunde_erhaelt_Vertrag_ibfk_2` FOREIGN KEY (`vertrag_id`) REFERENCES `Vertrag` (`vertrag_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Kunde_erstellt_Anfrage`
--
ALTER TABLE `Kunde_erstellt_Anfrage`
  ADD CONSTRAINT `Kunde_erstellt_Anfrage_ibfk_1` FOREIGN KEY (`kunde_id`) REFERENCES `Kunde` (`kunde_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Kunde_erstellt_Anfrage_ibfk_2` FOREIGN KEY (`anfrage_id`) REFERENCES `Anfrage` (`anfrage_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Mitarbeiter_arbeitetBei_Bauunternehmen`
--
ALTER TABLE `Mitarbeiter_arbeitetBei_Bauunternehmen`
  ADD CONSTRAINT `Mitarbeiter_arbeitetBei_Bauunternehmen_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Mitarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Mitarbeiter_arbeitetBei_Bauunternehmen_ibfk_2` FOREIGN KEY (`unternehmen_id`) REFERENCES `Bauunternehmen` (`unternehmen_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Mitarbeiter_beteiligtAn_Bauprojekt`
--
ALTER TABLE `Mitarbeiter_beteiligtAn_Bauprojekt`
  ADD CONSTRAINT `Mitarbeiter_beteiligtAn_Bauprojekt_ibfk_1` FOREIGN KEY (`bauprojekt_id`) REFERENCES `Bauprojekt` (`bauprojekt_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Mitarbeiter_beteiligtAn_Bauprojekt_ibfk_2` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Mitarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Projektleiter`
--
ALTER TABLE `Projektleiter`
  ADD CONSTRAINT `Projektleiter_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Mitarbeiter` (`mitarbeiter_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints der Tabelle `Projektleiter_bearbeitet_Anfrage`
--
ALTER TABLE `Projektleiter_bearbeitet_Anfrage`
  ADD CONSTRAINT `Projektleiter_bearbeitet_Anfrage_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Projektleiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Projektleiter_bearbeitet_Anfrage_ibfk_2` FOREIGN KEY (`anfrage_id`) REFERENCES `Anfrage` (`anfrage_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Projektleiter_erstellt_Vertrag`
--
ALTER TABLE `Projektleiter_erstellt_Vertrag`
  ADD CONSTRAINT `Projektleiter_erstellt_Vertrag_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Projektleiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Projektleiter_erstellt_Vertrag_ibfk_2` FOREIGN KEY (`vertrag_id`) REFERENCES `Vertrag` (`vertrag_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Projektleiter_kontaktiert_Architekt`
--
ALTER TABLE `Projektleiter_kontaktiert_Architekt`
  ADD CONSTRAINT `Projektleiter_kontaktiert_Architekt_ibfk_1` FOREIGN KEY (`arch_mitarbeiter_id`) REFERENCES `Architekt` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Projektleiter_kontaktiert_Architekt_ibfk_2` FOREIGN KEY (`proj_mitarbeiter_id`) REFERENCES `Projektleiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Projektleiter_kontaktiert_Werkstofflieferant`
--
ALTER TABLE `Projektleiter_kontaktiert_Werkstofflieferant`
  ADD CONSTRAINT `Projektleiter_kontaktiert_Werkstofflieferant_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Projektleiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Projektleiter_kontaktiert_Werkstofflieferant_ibfk_2` FOREIGN KEY (`lieferant_id`) REFERENCES `Werkstofflieferant` (`lieferant_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih`
--
ALTER TABLE `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih`
  ADD CONSTRAINT `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Projektleiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Projektleiter_kontaktiert_WerkzeugUndMaschinenverleih_ibfk_2` FOREIGN KEY (`verleih_id`) REFERENCES `WerkzeugUndMaschinenverleih` (`verleih_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Rechnung`
--
ALTER TABLE `Rechnung`
  ADD CONSTRAINT `Rechnung_ibfk_1` FOREIGN KEY (`vertrag_id`) REFERENCES `Vertrag` (`vertrag_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Sachbearbeiter`
--
ALTER TABLE `Sachbearbeiter`
  ADD CONSTRAINT `Sachbearbeiter_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Mitarbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Sachbearbeiter_stelltAus_Rechnung`
--
ALTER TABLE `Sachbearbeiter_stelltAus_Rechnung`
  ADD CONSTRAINT `Sachbearbeiter_stelltAus_Rechnung_ibfk_1` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `Sachbearbeiter` (`mitarbeiter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Sachbearbeiter_stelltAus_Rechnung_ibfk_2` FOREIGN KEY (`rechnung_id`) REFERENCES `Rechnung` (`rechnung_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Vertrag_enthaelt_Skizze`
--
ALTER TABLE `Vertrag_enthaelt_Skizze`
  ADD CONSTRAINT `Vertrag_enthaelt_Skizze_ibfk_1` FOREIGN KEY (`skizze_id`) REFERENCES `Skizze` (`skizze_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Vertrag_enthaelt_Skizze_ibfk_2` FOREIGN KEY (`vertrag_id`) REFERENCES `Vertrag` (`vertrag_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `Werkstofflieferant_liefert_Werkstoff`
--
ALTER TABLE `Werkstofflieferant_liefert_Werkstoff`
  ADD CONSTRAINT `Werkstofflieferant_liefert_Werkstoff_ibfk_1` FOREIGN KEY (`lieferant_id`) REFERENCES `Werkstofflieferant` (`lieferant_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Werkstofflieferant_liefert_Werkstoff_ibfk_2` FOREIGN KEY (`werkstoff_id`) REFERENCES `Werkstoff` (`werkstoff_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik`
--
ALTER TABLE `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik`
  ADD CONSTRAINT `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik_ibfk_1` FOREIGN KEY (`bautechnik_id`) REFERENCES `Bautechnik` (`bautechnik_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `WerkzeugUndMaschinenverleih_stelltBereit_Bautechnik_ibfk_2` FOREIGN KEY (`verleih_id`) REFERENCES `WerkzeugUndMaschinenverleih` (`verleih_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
