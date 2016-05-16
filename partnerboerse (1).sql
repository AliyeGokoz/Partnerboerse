-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 16. Mai 2016 um 12:57
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 5.5.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `partnerboerse`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `blockings`
--

CREATE TABLE `blockings` (
  `id` int(11) NOT NULL,
  `fromProfile` int(11) NOT NULL,
  `toProfile` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `blockings`
--

INSERT INTO `blockings` (`id`, `fromProfile`, `toProfile`) VALUES
(1, 1, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `descriptions`
--

CREATE TABLE `descriptions` (
  `id` int(11) NOT NULL,
  `textualDescription` text COLLATE utf8_bin NOT NULL,
  `propertyName` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `descriptions`
--

INSERT INTO `descriptions` (`id`, `textualDescription`, `propertyName`) VALUES
(1, 'Ich mache gern:', 'Hobbys'),
(2, 'Mein/e Lieblingsband/Lieblingskünstler/in sind/ist:', 'Musik'),
(3, 'Mein/e Lieblingsfilm/e ist/sind:', 'Filme');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `favorites`
--

CREATE TABLE `favorites` (
  `id` int(11) NOT NULL,
  `fromProfile` int(11) NOT NULL,
  `toProfile` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `favorites`
--

INSERT INTO `favorites` (`id`, `fromProfile`, `toProfile`) VALUES
(1, 7, 4),
(2, 1, 6),
(3, 6, 1),
(4, 3, 5),
(5, 2, 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `infos`
--

CREATE TABLE `infos` (
  `id` int(11) NOT NULL,
  `informationValue` varchar(255) COLLATE utf8_bin NOT NULL,
  `profileId` int(11) NOT NULL,
  `selectionId` int(11) DEFAULT NULL,
  `descriptionId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `infos`
--

INSERT INTO `infos` (`id`, `informationValue`, `profileId`, `selectionId`, `descriptionId`) VALUES
(1, 'Rock', 1, 1, NULL),
(2, 'John Zorn', 2, NULL, 2),
(3, 'Meditieren', 3, NULL, 1),
(4, 'Volkstanz', 4, 2, NULL),
(5, 'Hayat Sarkisi', 5, NULL, 3),
(6, 'Komödie', 6, 3, NULL),
(7, 'Griechische Filme', 7, NULL, 3),
(8, 'Reisen', 7, NULL, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `profiles`
--

CREATE TABLE `profiles` (
  `id` int(11) NOT NULL,
  `firstName` varchar(255) COLLATE utf8_bin NOT NULL,
  `lastName` varchar(255) COLLATE utf8_bin NOT NULL,
  `dateOfBirth` date NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `height` int(11) NOT NULL,
  `confession` enum('DEFAULT','PROTESTANT','CATHOLIC','BUDDHISTIC','HINDU','MUSLIM','JEWISH','NO_CONFESSION','OTHERS') COLLATE utf8_bin NOT NULL,
  `smoker` tinyint(1) NOT NULL,
  `hairColor` enum('DEFAULT','BROWN','BLOND','BLACK','RED','GREY','OTHERS') COLLATE utf8_bin NOT NULL,
  `gender` enum('FEMALE','MALE','OTHERS','') COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `profiles`
--

INSERT INTO `profiles` (`id`, `firstName`, `lastName`, `dateOfBirth`, `email`, `height`, `confession`, `smoker`, `hairColor`, `gender`) VALUES
(1, 'Peter', 'Müller', '1968-02-15', 'peter.mueller@gmail.de', 178, 'CATHOLIC', 1, 'BROWN', 'MALE'),
(2, 'Tina', 'Goldberg', '1977-05-15', 'tina.goldberg@gmail.de', 168, 'JEWISH', 0, 'BLACK', 'FEMALE'),
(3, 'Shaniqua', 'Abdallah', '1990-10-27', 'quaqua_love@gmail.de', 155, 'BUDDHISTIC', 0, 'RED', 'FEMALE'),
(4, 'Cinthu', 'Jeyakumar', '1987-08-31', 'c.jeyakumar@gmail.de', 171, 'HINDU', 0, 'BLACK', 'FEMALE'),
(5, 'Hakan', 'Yilmaz', '1984-01-19', 'yili_hakan@gmail.de', 189, 'MUSLIM', 1, 'BROWN', 'MALE'),
(6, 'Cindy', 'Schmalze', '1955-12-24', 'schmalzlocke@gmail.de', 169, 'NO_CONFESSION', 1, 'BLOND', 'FEMALE'),
(7, 'Jorgo', 'Titatidou', '1977-02-03', 'kefderak.jorgo@gmail.de', 166, 'OTHERS', 1, 'BLACK', 'MALE');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `searchprofiles`
--

CREATE TABLE `searchprofiles` (
  `id` int(11) NOT NULL,
  `fromAge` int(11) NOT NULL,
  `toAge` int(11) NOT NULL,
  `hairColor` enum('DEFAULT','BROWN','BLOND','BLACK','RED','GREY','OTHERS') COLLATE utf8_bin NOT NULL,
  `gender` enum('FEMALE','MALE','OTHERS') COLLATE utf8_bin NOT NULL,
  `fromHeight` int(11) NOT NULL,
  `toHeight` int(11) NOT NULL,
  `confession` enum('DEFAULT','PROTESTANT','CATHOLIC','BUDDHISTIC','HINDU','MUSLIM','JEWISH','NO_CONFESSION','OTHERS') COLLATE utf8_bin NOT NULL,
  `profileId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `searchprofiles`
--

INSERT INTO `searchprofiles` (`id`, `fromAge`, `toAge`, `hairColor`, `gender`, `fromHeight`, `toHeight`, `confession`, `profileId`) VALUES
(1, 20, 70, 'DEFAULT', 'MALE', 170, 190, 'DEFAULT', 6),
(2, 20, 40, 'BLACK', 'FEMALE', 150, 175, 'DEFAULT', 5),
(3, 30, 50, 'BLOND', 'MALE', 170, 185, 'PROTESTANT', 2),
(4, 20, 40, 'DEFAULT', 'FEMALE', 160, 170, 'MUSLIM', 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `selections`
--

CREATE TABLE `selections` (
  `id` int(11) NOT NULL,
  `textualDescription` text COLLATE utf8_bin NOT NULL,
  `propertyName` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `selections`
--

INSERT INTO `selections` (`id`, `textualDescription`, `propertyName`) VALUES
(1, 'Ich höre gern:', 'Musik'),
(2, 'Ich betreibe gerne folgende Sportart/en:', 'Sport'),
(3, 'Ich schaue gerne:', 'Filme');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `similarities`
--

CREATE TABLE `similarities` (
  `id` int(11) NOT NULL,
  `fromProfile` int(11) NOT NULL,
  `toProfile` int(11) NOT NULL,
  `similarityValue` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `similarities`
--

INSERT INTO `similarities` (`id`, `fromProfile`, `toProfile`, `similarityValue`) VALUES
(1, 1, 6, 0.89),
(2, 3, 5, 0.68),
(3, 4, 7, 0.72),
(4, 2, 7, 0.35);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `visits`
--

CREATE TABLE `visits` (
  `id` int(11) NOT NULL,
  `fromProfile` int(11) NOT NULL,
  `toProfile` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `visits`
--

INSERT INTO `visits` (`id`, `fromProfile`, `toProfile`) VALUES
(1, 1, 6),
(2, 2, 5),
(3, 3, 5),
(4, 7, 2),
(5, 4, 3),
(6, 5, 4);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `blockings`
--
ALTER TABLE `blockings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `toProfile` (`toProfile`),
  ADD KEY `fromProfile` (`fromProfile`);

--
-- Indizes für die Tabelle `descriptions`
--
ALTER TABLE `descriptions`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fromProfile` (`fromProfile`),
  ADD KEY `toProfile` (`toProfile`);

--
-- Indizes für die Tabelle `infos`
--
ALTER TABLE `infos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `profileId` (`profileId`),
  ADD KEY `selection` (`selectionId`),
  ADD KEY `descriptionId` (`descriptionId`);

--
-- Indizes für die Tabelle `profiles`
--
ALTER TABLE `profiles`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `searchprofiles`
--
ALTER TABLE `searchprofiles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `profileId` (`profileId`);

--
-- Indizes für die Tabelle `selections`
--
ALTER TABLE `selections`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `similarities`
--
ALTER TABLE `similarities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fromProfile` (`fromProfile`),
  ADD KEY `toProfile` (`toProfile`);

--
-- Indizes für die Tabelle `visits`
--
ALTER TABLE `visits`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fromProfile` (`fromProfile`),
  ADD KEY `toProfile` (`toProfile`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `blockings`
--
ALTER TABLE `blockings`
  ADD CONSTRAINT `blockings_ibfk_1` FOREIGN KEY (`fromProfile`) REFERENCES `profiles` (`id`),
  ADD CONSTRAINT `blockings_ibfk_2` FOREIGN KEY (`toProfile`) REFERENCES `profiles` (`id`);

--
-- Constraints der Tabelle `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`fromProfile`) REFERENCES `profiles` (`id`),
  ADD CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`toProfile`) REFERENCES `profiles` (`id`);

--
-- Constraints der Tabelle `infos`
--
ALTER TABLE `infos`
  ADD CONSTRAINT `infos_ibfk_1` FOREIGN KEY (`profileId`) REFERENCES `profiles` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `infos_ibfk_2` FOREIGN KEY (`selectionId`) REFERENCES `selections` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `infos_ibfk_3` FOREIGN KEY (`descriptionId`) REFERENCES `descriptions` (`id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `searchprofiles`
--
ALTER TABLE `searchprofiles`
  ADD CONSTRAINT `searchprofiles_ibfk_1` FOREIGN KEY (`profileId`) REFERENCES `profiles` (`id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `similarities`
--
ALTER TABLE `similarities`
  ADD CONSTRAINT `similarities_ibfk_1` FOREIGN KEY (`fromProfile`) REFERENCES `profiles` (`id`),
  ADD CONSTRAINT `similarities_ibfk_2` FOREIGN KEY (`toProfile`) REFERENCES `profiles` (`id`);

--
-- Constraints der Tabelle `visits`
--
ALTER TABLE `visits`
  ADD CONSTRAINT `visits_ibfk_1` FOREIGN KEY (`fromProfile`) REFERENCES `profiles` (`id`),
  ADD CONSTRAINT `visits_ibfk_2` FOREIGN KEY (`toProfile`) REFERENCES `profiles` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
