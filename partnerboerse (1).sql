-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 05. Jun 2016 um 11:00
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
(3, 'Mein/e Lieblingsfilm/e ist/sind:', 'Filme'),
(4, 'Ich habe folgendes Gedicht selber geschrieben:', 'Gedichte');

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
-- Tabellenstruktur für Tabelle `options`
--

CREATE TABLE `options` (
  `id` int(11) NOT NULL,
  `option` varchar(255) COLLATE utf8_bin NOT NULL,
  `selectionId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `options`
--

INSERT INTO `options` (`id`, `option`, `selectionId`) VALUES
(1, 'Rock ', 1),
(2, 'Pop', 1),
(3, 'Electro', 1),
(4, 'House', 1),
(5, 'Volksmusik', 1),
(6, 'andere', 1),
(7, 'Fußball', 2),
(8, 'Basketball', 2),
(9, 'Wandern', 2),
(10, 'Volleyball', 2),
(11, 'Volkstanz', 2),
(12, 'Tennis', 2),
(13, 'Tauchen', 2),
(14, 'Taekwondo', 2),
(15, 'Seilspringen', 2),
(16, 'Schwimmen', 2),
(17, 'Rugby', 2),
(18, 'andere', 2),
(19, 'Horror', 3),
(20, 'Drama', 3),
(21, 'Komödie', 3),
(22, 'Action', 3),
(23, 'Mistery', 3),
(24, 'Fantasy', 3),
(25, 'Romanik', 3),
(26, 'Erotik', 3),
(27, 'Science Fiction', 3),
(28, 'Thriller', 3),
(29, 'Western', 3),
(30, 'Sport', 3),
(31, 'Cartoon', 3),
(32, 'andere', 3),
(33, 'Krimi', 4),
(34, 'Thriller', 4),
(35, 'Fantasy', 4),
(36, 'Romane', 4),
(37, 'Liebesromane', 4),
(38, 'Historische Romane', 4),
(39, 'Science Fiction', 4),
(40, 'Comic', 4),
(41, 'Komödie', 4),
(42, 'Gedichte', 4),
(43, 'Drama', 4),
(44, 'Erotik', 4);

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
(0, 'Dieter', 'djgjskdg', '2016-05-09', 'gsdgsdgs', 46, 'CATHOLIC', 1, 'BROWN', 'MALE'),
(1, 'Peter', 'Müller', '1968-02-15', 'peter.mueller@gmail.de', 178, 'CATHOLIC', 1, 'BROWN', 'MALE'),
(2, 'Tina', 'Goldberg', '1977-05-15', 'tina.goldberg@gmail.de', 168, 'JEWISH', 0, 'BLACK', 'FEMALE'),
(3, 'Shaniqua', 'Abdallah', '1990-10-27', 'quaqua_love@gmail.de', 155, 'BUDDHISTIC', 0, 'RED', 'FEMALE'),
(4, 'Cinthu', 'Jeyakumar', '1987-08-31', 'c.jeyakumar@gmail.de', 171, 'HINDU', 0, 'BLACK', 'FEMALE'),
(5, 'Hakan', 'Yilmaz', '1984-01-19', 'yili_hakan@gmail.de', 189, 'MUSLIM', 1, 'BROWN', 'MALE'),
(6, 'Cindy', 'Schmalze', '1955-12-24', 'schmalzlocke@gmail.de', 169, 'NO_CONFESSION', 1, 'BLOND', 'FEMALE'),
(7, 'Jorgo', 'Titatidou', '1977-02-03', 'kefderak.jorgo@gmail.de', 166, 'OTHERS', 1, 'BLACK', 'MALE'),
(8, 'Jolanta', 'Jolanta', '2016-05-16', 'Jolanta', 155, 'BUDDHISTIC', 1, 'BLOND', 'FEMALE'),
(9, 'Jolanta', 'Jolanta', '2016-05-16', 'updated', 155, 'BUDDHISTIC', 1, 'BLOND', 'FEMALE'),
(10, 'Jolanta', 'Jolanta', '2000-01-01', 'updated', 155, 'BUDDHISTIC', 1, 'BLOND', 'FEMALE');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `searchprofiles`
--

CREATE TABLE `searchprofiles` (
  `id` int(11) NOT NULL,
  `fromAge` int(11) NOT NULL,
  `hairColor` enum('DEFAULT','BROWN','BLOND','BLACK','RED','GREY','OTHERS') COLLATE utf8_bin NOT NULL,
  `gender` enum('FEMALE','MALE','OTHERS','') COLLATE utf8_bin NOT NULL,
  `fromHeight` int(11) NOT NULL,
  `confession` enum('DEFAULT','PROTESTANT','CATHOLIC','BUDDHISTIC','HINDU','MUSLIM','JEWISH','NO_CONFESSION','OTHERS') COLLATE utf8_bin NOT NULL,
  `profileId` int(11) NOT NULL,
  `toHeight` int(11) NOT NULL,
  `toAge` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `searchprofiles`
--

INSERT INTO `searchprofiles` (`id`, `fromAge`, `hairColor`, `gender`, `fromHeight`, `confession`, `profileId`, `toHeight`, `toAge`) VALUES
(1, 20, 'DEFAULT', 'MALE', 170, 'DEFAULT', 6, 190, 70),
(2, 20, 'BLACK', 'FEMALE', 150, 'DEFAULT', 5, 175, 40),
(3, 30, 'BLOND', 'MALE', 170, 'PROTESTANT', 2, 185, 50),
(4, 20, 'DEFAULT', 'FEMALE', 160, 'MUSLIM', 5, 170, 40);

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
(3, 'Ich schaue gerne:', 'Filme'),
(4, 'Ich lese gerne:', 'Bücher');

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
(1, 3, 2, 0.45454545454545453),
(2, 3, 4, 0.5),
(3, 3, 8, 0.6),
(4, 3, 9, 0.6),
(5, 3, 10, 0.6),
(6, 3, 1, 0.5),
(7, 3, 6, 0.4),
(8, 3, 7, 0.38461538461538464),
(9, 3, 5, 0.45454545454545453),
(10, 3, 0, 0.5),
(11, 2, 3, 0.45454545454545453),
(12, 2, 4, 0.7),
(13, 2, 8, 0.4),
(14, 2, 9, 0.4),
(15, 2, 10, 0.4),
(16, 2, 1, 0.5),
(17, 2, 6, 0.5),
(18, 2, 7, 0.6666666666666666),
(19, 2, 5, 0.45454545454545453),
(20, 2, 0, 0.5),
(21, 4, 3, 0.5),
(22, 4, 2, 0.7),
(23, 4, 8, 0.4),
(24, 4, 9, 0.4),
(25, 4, 10, 0.4),
(26, 4, 1, 0.5454545454545454),
(27, 4, 6, 0.45454545454545453),
(28, 4, 7, 0.7),
(29, 4, 5, 0.5),
(30, 4, 0, 0.5),
(31, 8, 3, 0.6),
(32, 8, 2, 0.4),
(33, 8, 4, 0.4),
(34, 8, 9, 0.9),
(35, 8, 10, 0.8),
(36, 8, 1, 0.6),
(37, 8, 6, 0.6),
(38, 8, 7, 0.6),
(39, 8, 5, 0.6),
(40, 8, 0, 0.7),
(41, 9, 3, 0.6),
(42, 9, 2, 0.4),
(43, 9, 4, 0.4),
(44, 9, 8, 0.9),
(45, 9, 10, 0.8),
(46, 9, 1, 0.6),
(47, 9, 6, 0.6),
(48, 9, 7, 0.6),
(49, 9, 5, 0.6),
(50, 9, 0, 0.7),
(51, 10, 3, 0.6),
(52, 10, 2, 0.4),
(53, 10, 4, 0.4),
(54, 10, 8, 0.8),
(55, 10, 9, 0.8),
(56, 10, 1, 0.6),
(57, 10, 6, 0.6),
(58, 10, 7, 0.6),
(59, 10, 5, 0.6),
(60, 10, 0, 0.6),
(61, 1, 3, 0.5),
(62, 1, 2, 0.5),
(63, 1, 4, 0.5454545454545454),
(64, 1, 8, 0.6),
(65, 1, 9, 0.6),
(66, 1, 10, 0.6),
(67, 1, 6, 0.6363636363636364),
(68, 1, 7, 0.5),
(69, 1, 5, 0.6),
(70, 1, 0, 0.7),
(71, 6, 3, 0.4),
(72, 6, 2, 0.5),
(73, 6, 4, 0.45454545454545453),
(74, 6, 8, 0.6),
(75, 6, 9, 0.6),
(76, 6, 10, 0.6),
(77, 6, 1, 0.6363636363636364),
(78, 6, 7, 0.7),
(79, 6, 5, 0.6),
(80, 6, 0, 0.6),
(81, 7, 3, 0.38461538461538464),
(82, 7, 2, 0.6666666666666666),
(83, 7, 4, 0.7),
(84, 7, 8, 0.6),
(85, 7, 9, 0.6),
(86, 7, 10, 0.6),
(87, 7, 1, 0.5),
(88, 7, 6, 0.7),
(89, 7, 5, 0.38461538461538464),
(90, 7, 0, 0.5),
(91, 5, 3, 0.45454545454545453),
(92, 5, 2, 0.45454545454545453),
(93, 5, 4, 0.5),
(94, 5, 8, 0.6),
(95, 5, 9, 0.6),
(96, 5, 10, 0.6),
(97, 5, 1, 0.6),
(98, 5, 6, 0.6),
(99, 5, 7, 0.38461538461538464),
(100, 5, 0, 0.6),
(101, 0, 3, 0.5),
(102, 0, 2, 0.5),
(103, 0, 4, 0.5),
(104, 0, 8, 0.7),
(105, 0, 9, 0.7),
(106, 0, 10, 0.6),
(107, 0, 1, 0.7),
(108, 0, 6, 0.6),
(109, 0, 7, 0.5),
(110, 0, 5, 0.6);

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
  ADD KEY `fromProfile` (`fromProfile`),
  ADD KEY `toProfile` (`toProfile`);

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
  ADD KEY `selectionId` (`selectionId`),
  ADD KEY `descriptionId` (`descriptionId`);

--
-- Indizes für die Tabelle `options`
--
ALTER TABLE `options`
  ADD PRIMARY KEY (`id`),
  ADD KEY `selectionId` (`selectionId`);

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
  ADD CONSTRAINT `infos_ibfk_1` FOREIGN KEY (`profileId`) REFERENCES `profiles` (`id`),
  ADD CONSTRAINT `infos_ibfk_2` FOREIGN KEY (`selectionId`) REFERENCES `selections` (`id`),
  ADD CONSTRAINT `infos_ibfk_3` FOREIGN KEY (`descriptionId`) REFERENCES `descriptions` (`id`);

--
-- Constraints der Tabelle `options`
--
ALTER TABLE `options`
  ADD CONSTRAINT `options_ibfk_1` FOREIGN KEY (`selectionId`) REFERENCES `selections` (`id`);

--
-- Constraints der Tabelle `searchprofiles`
--
ALTER TABLE `searchprofiles`
  ADD CONSTRAINT `searchprofiles_ibfk_1` FOREIGN KEY (`profileId`) REFERENCES `profiles` (`id`);

--
-- Constraints der Tabelle `similarities`
--
ALTER TABLE `similarities`
  ADD CONSTRAINT `similarities_ibfk_1` FOREIGN KEY (`toProfile`) REFERENCES `profiles` (`id`),
  ADD CONSTRAINT `similarities_ibfk_2` FOREIGN KEY (`fromProfile`) REFERENCES `profiles` (`id`);

--
-- Constraints der Tabelle `visits`
--
ALTER TABLE `visits`
  ADD CONSTRAINT `visits_ibfk_1` FOREIGN KEY (`fromProfile`) REFERENCES `profiles` (`id`),
  ADD CONSTRAINT `visits_ibfk_2` FOREIGN KEY (`toProfile`) REFERENCES `profiles` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
