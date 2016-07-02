-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 173.194.81.182
-- Erstellungszeit: 02. Jul 2016 um 19:30
-- Server-Version: 5.6.30
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
(1, 19, 9);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `descriptions`
--

CREATE TABLE `descriptions` (
  `id` int(11) NOT NULL,
  `textualDescriptionForProfile` text COLLATE utf8_bin NOT NULL,
  `textualDescriptionForSearchProfile` text COLLATE utf8_bin NOT NULL,
  `propertyName` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `descriptions`
--

INSERT INTO `descriptions` (`id`, `textualDescriptionForProfile`, `textualDescriptionForSearchProfile`, `propertyName`) VALUES
(1, 'Ich mache gerne:', 'Mein große Liebe macht gerne:', 'Hobbys'),
(2, 'Mein/e Lieblingsband/Lieblingskünstler/in sind/ist:', 'Meine große Liebe hat folgende Lieblingsband/Lieblingskünstler/in:', 'Musik'),
(3, 'Mein/e Lieblingsfilm/e ist/sind:', 'Meine große Liebe hat folgenden Lieblingsfilm/e:', 'Filme');

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
(5, 2, 7),
(6, 16, 11);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `infos`
--

CREATE TABLE `infos` (
  `id` int(11) NOT NULL,
  `informationValue` varchar(255) COLLATE utf8_bin NOT NULL,
  `profileId` int(11) DEFAULT NULL,
  `searchprofileId` int(11) DEFAULT NULL,
  `selectionId` int(11) DEFAULT NULL,
  `descriptionId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `infos`
--

INSERT INTO `infos` (`id`, `informationValue`, `profileId`, `searchprofileId`, `selectionId`, `descriptionId`) VALUES
(1, 'Rock', NULL, 1, 1, NULL),
(2, 'John Zorn', NULL, 2, NULL, 2),
(3, 'Meditieren', NULL, 3, NULL, 1),
(4, 'Volkstanz', 4, NULL, 2, NULL),
(5, 'Hayat Sarkisi', 5, NULL, NULL, 3),
(6, 'Komödie', 6, NULL, 3, NULL),
(9, 'Thriller', 1, NULL, 4, NULL),
(10, 'Horror', 1, NULL, 3, NULL),
(11, 'Paranormal Activity', 1, NULL, NULL, 3),
(12, 'Rock ', 1, NULL, 1, NULL),
(13, 'Rammstein', 1, NULL, NULL, 2),
(14, 'Tennis', 1, NULL, 2, NULL),
(15, 'kochen', 1, NULL, NULL, 1),
(16, 'mit Freunden treffen', 1, NULL, NULL, 1),
(17, 'Komödie', 2, NULL, 4, NULL),
(18, 'Romanik', 2, NULL, 3, NULL),
(19, 'Titanic', 2, NULL, NULL, 3),
(20, 'Dirty Dancing', 2, NULL, NULL, 3),
(21, 'Adele', 2, NULL, NULL, 2),
(22, 'Volleyball', 2, NULL, 2, NULL),
(23, 'Thriller', 3, NULL, 4, NULL),
(24, 'Horror', 3, NULL, 3, NULL),
(25, 'Saw', 3, NULL, NULL, 3),
(26, 'House', 3, NULL, 1, NULL),
(27, 'Avici', 3, NULL, NULL, 2),
(28, 'Taekwondo', 3, NULL, 2, NULL),
(29, 'mit Freunden treffen', 3, NULL, NULL, 1),
(30, 'Fantasy', 4, NULL, 4, NULL),
(31, 'Drama', 4, NULL, 3, NULL),
(32, 'The Prisoners', 4, NULL, NULL, 3),
(33, 'Rammstein', 4, NULL, NULL, 2),
(34, 'Schwimmen', 4, NULL, 2, NULL),
(35, 'Feiern gehen', 4, NULL, NULL, 1),
(36, 'Maniküre', 4, NULL, NULL, 1),
(37, 'Romane', 5, NULL, 4, NULL),
(38, 'Drama', 5, NULL, 3, NULL),
(39, 'The Prisoners', 5, NULL, NULL, 3),
(40, 'Pop', 5, NULL, 1, NULL),
(41, 'Chris Brown', 5, NULL, NULL, 2),
(42, 'Drake', 5, NULL, NULL, 2),
(43, 'Fußball', 5, NULL, 2, NULL),
(44, 'mit Freunden treffen', 5, NULL, NULL, 1),
(45, 'Essen gehen', 5, NULL, NULL, 1),
(46, 'Action', 6, NULL, 3, NULL),
(47, 'Fast and Furious', 6, NULL, NULL, 3),
(48, 'Volksmusik', 6, NULL, 1, NULL),
(49, 'Florian Silbereisen', 6, NULL, NULL, 2),
(50, 'Wandern', 6, NULL, 2, NULL),
(51, 'Tennis', 6, NULL, 2, NULL),
(52, 'Krimi', 7, NULL, 4, NULL),
(53, 'Horror', 7, NULL, 3, NULL),
(54, 'Saw', 7, NULL, NULL, 3),
(55, 'House', 7, NULL, 1, NULL),
(56, 'Avici', 7, NULL, NULL, 2),
(57, 'Schwimmen', 7, NULL, 2, NULL),
(58, 'Reisen', 7, NULL, NULL, 1),
(59, 'Romane', 8, NULL, 4, NULL),
(60, 'Cartoon', 8, NULL, 3, NULL),
(61, 'Science Fiction', 8, NULL, 3, NULL),
(62, 'Avatar', 8, NULL, NULL, 3),
(63, 'Drake', 8, NULL, NULL, 2),
(64, 'Tauchen', 8, NULL, 2, NULL),
(65, 'Reisen', 8, NULL, NULL, 1),
(66, 'Urlaub', 8, NULL, NULL, 1),
(67, 'Romane', 9, NULL, 4, NULL),
(68, 'Mistery', 9, NULL, 3, NULL),
(69, 'Shutter Island', 9, NULL, NULL, 3),
(70, 'Volksmusik', 9, NULL, 1, NULL),
(71, 'Beatrice Egli', 9, NULL, NULL, 2),
(72, 'Florian Silbereisen', 9, NULL, NULL, 2),
(73, 'Reiten', 9, NULL, NULL, 1),
(74, 'Töpfern', 9, NULL, NULL, 1),
(75, 'Liebesromane', 10, NULL, 4, NULL),
(76, 'Drama', 10, NULL, 3, NULL),
(77, 'Deliha', 10, NULL, NULL, 3),
(78, 'Pop', 10, NULL, 1, NULL),
(79, 'Essen gehen', 10, NULL, NULL, 1),
(80, 'mit Freunden treffen', 10, NULL, NULL, 1),
(81, 'Romanik', 10, NULL, 3, NULL),
(82, 'Gripin', 10, NULL, NULL, 2),
(83, 'Krimi', 11, NULL, 4, NULL),
(84, 'Horror', 11, NULL, 3, NULL),
(85, 'Paranormal Activity', 11, NULL, NULL, 3),
(86, 'Saw', 11, NULL, NULL, 3),
(87, 'Rock ', 11, NULL, 1, NULL),
(88, 'Rammstein', 11, NULL, NULL, 2),
(89, 'Linkin Park', 11, NULL, NULL, 2),
(90, 'Pop', 11, NULL, 1, NULL),
(91, 'Chris Brown', 11, NULL, NULL, 2),
(92, 'Urlaub', 11, NULL, NULL, 1),
(93, 'Fantasy', 12, NULL, 4, NULL),
(94, 'Science Fiction', 12, NULL, 3, NULL),
(95, 'Matrix', 12, NULL, NULL, 3),
(96, 'Electro', 12, NULL, 1, NULL),
(97, 'Deadmau5', 12, NULL, NULL, 2),
(98, 'Fußball', 12, NULL, 2, NULL),
(99, 'Feiern gehen', 12, NULL, NULL, 1),
(100, 'Electro', 13, NULL, 1, NULL),
(101, 'Deadmau5', 13, NULL, NULL, 2),
(102, 'Thriller', 13, NULL, 3, NULL),
(103, 'andere', 13, NULL, 2, NULL),
(104, 'Dirty Dancing', 13, NULL, NULL, 3),
(106, 'The Prisoners', 13, NULL, NULL, 3),
(107, 'Conjuring', 13, NULL, NULL, 3),
(108, 'Liebesromane', 13, NULL, 4, NULL),
(109, 'Historische Romane', 14, NULL, 4, NULL),
(110, 'Western', 14, NULL, 3, NULL),
(111, 'Der mit dem Wolf tanzt', 14, NULL, NULL, 3),
(112, 'Rock ', 14, NULL, 1, NULL),
(113, 'Volksmusik', 14, NULL, 1, NULL),
(114, 'The Beatles', 14, NULL, NULL, 2),
(115, 'Reisen', 14, NULL, NULL, 1),
(116, 'Malen nach Zahlen', 14, NULL, NULL, 1),
(117, 'Drama', 15, NULL, 4, NULL),
(118, 'Fantasy', 15, NULL, 3, NULL),
(119, 'Horror', 15, NULL, 3, NULL),
(120, 'Der Herr der Ringe', 15, NULL, NULL, 3),
(121, 'Pop', 15, NULL, 1, NULL),
(122, 'Rihanna', 15, NULL, NULL, 2),
(123, 'Jennifer Lopez', 15, NULL, NULL, 2),
(124, 'Basketball', 15, NULL, 2, NULL),
(125, 'Zeichnen', 15, NULL, NULL, 1),
(126, 'Chillen', 15, NULL, NULL, 1),
(129, 'The Beach', 17, NULL, NULL, 3),
(133, 'Saw', 18, NULL, NULL, 3),
(134, 'Paranormal Activity', 18, NULL, NULL, 3),
(135, 'Urlaub', 18, NULL, NULL, 1),
(136, 'Reisen', 18, NULL, NULL, 1),
(137, 'Drake', 18, NULL, NULL, 2),
(139, 'Inception', 16, NULL, NULL, 3),
(140, 'Thriller', 16, NULL, 4, NULL),
(141, 'Horror', 19, NULL, 3, NULL),
(142, 'Hayat Sarkisi', 19, NULL, NULL, 3),
(143, 'Saw', NULL, 10, NULL, 3),
(144, 'House', NULL, 10, 1, NULL),
(145, 'Avici', NULL, 10, NULL, 2),
(146, 'Horror', NULL, 5, 3, NULL);

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
  `gender` enum('FEMALE','MALE','OTHERS','') COLLATE utf8_bin NOT NULL,
  `orientation` enum('HETERO','BI','HOMO','') COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `profiles`
--

INSERT INTO `profiles` (`id`, `firstName`, `lastName`, `dateOfBirth`, `email`, `height`, `confession`, `smoker`, `hairColor`, `gender`, `orientation`) VALUES
(1, 'Peter', 'Müller', '1968-02-15', 'peter.mueller@gmail.de', 178, 'CATHOLIC', 1, 'BROWN', 'MALE', 'HETERO'),
(2, 'Tina', 'Goldberg', '1977-05-15', 'tina.goldberg@gmail.de', 168, 'JEWISH', 0, 'BLACK', 'FEMALE', 'HETERO'),
(3, 'Shaniqua', 'Abdallah', '1990-10-27', 'quaqua_love@gmail.de', 155, 'BUDDHISTIC', 0, 'RED', 'FEMALE', 'HETERO'),
(4, 'Cinthu', 'Jeyakumar', '1987-08-31', 'c.jeyakumar@gmail.de', 171, 'HINDU', 0, 'BLACK', 'FEMALE', 'HETERO'),
(5, 'Hakan', 'Yilmaz', '1984-01-19', 'yili_hakan@gmail.de', 189, 'MUSLIM', 1, 'BROWN', 'MALE', 'HETERO'),
(6, 'Cindy', 'Schmalze', '1955-12-24', 'schmalzlocke@gmail.de', 169, 'NO_CONFESSION', 1, 'BLOND', 'FEMALE', 'HETERO'),
(7, 'Jorgo', 'Titatidou', '1977-02-03', 'kefderak.jorgo@gmail.de', 166, 'OTHERS', 1, 'BLACK', 'MALE', 'HETERO'),
(8, 'Sarah', 'Raimondo', '1989-07-13', 'sarah.raimondo@gmail.de', 165, 'PROTESTANT', 1, 'BROWN', 'FEMALE', 'HETERO'),
(9, 'Charlotte', 'von Harburg', '1978-04-20', 'c.v.Harburg@gmail.de', 172, 'CATHOLIC', 0, 'BLOND', 'FEMALE', 'HETERO'),
(10, 'Emine', 'Kücük', '1990-11-01', 'k.emine@gmail.de', 159, 'MUSLIM', 0, 'BLACK', 'FEMALE', 'HETERO'),
(11, 'Tom', 'Willer', '1990-03-19', 'tommiller@gmail.de', 186, 'PROTESTANT', 1, 'BLACK', 'MALE', 'BI'),
(12, 'John', 'Schnee', '1987-06-26', 'schnee@gmail.de', 183, 'NO_CONFESSION', 0, 'BLACK', 'MALE', 'BI'),
(13, 'Fernando', 'Dikloeten', '1981-10-06', 'dodo@gmail.de', 174, 'NO_CONFESSION', 0, 'RED', 'MALE', 'HOMO'),
(14, 'Hartmut', 'Wildenstängel', '1969-08-21', 'hartmut.w.@gmail.de', 170, 'NO_CONFESSION', 1, 'GREY', 'MALE', 'HETERO'),
(15, 'Sina', 'Lennister', '1989-07-07', 'Sina.Lennister@gmail.de', 168, 'CATHOLIC', 1, 'BLOND', 'FEMALE', 'BI'),
(16, 'Claudia', 'Ryniak', '1991-12-29', 'ClaudiaRyniak1@gmail.com', 160, 'NO_CONFESSION', 1, 'BLOND', 'FEMALE', 'HETERO'),
(17, 'Alena', 'Gerlinskaja', '1992-07-07', 'Alena871992@gmail.com', 172, 'DEFAULT', 1, 'DEFAULT', 'FEMALE', 'HETERO'),
(18, 'Roxana', 'Dinus', '1993-08-24', 'roxana.dinus@gmx.de', 175, 'NO_CONFESSION', 1, 'BLOND', 'FEMALE', 'HETERO'),
(19, 'Aliye', 'Gököz', '1994-02-17', 'A.Goekoez94@gmail.com', 170, 'OTHERS', 0, 'OTHERS', 'FEMALE', 'HOMO');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `searchprofiles`
--

CREATE TABLE `searchprofiles` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `fromAge` int(11) NOT NULL,
  `hairColor` enum('DEFAULT','BROWN','BLOND','BLACK','RED','GREY','OTHERS') COLLATE utf8_bin DEFAULT NULL,
  `gender` enum('FEMALE','MALE','OTHERS','') COLLATE utf8_bin DEFAULT NULL,
  `fromHeight` int(11) NOT NULL,
  `confession` enum('DEFAULT','PROTESTANT','CATHOLIC','BUDDHISTIC','HINDU','MUSLIM','JEWISH','NO_CONFESSION','OTHERS') COLLATE utf8_bin DEFAULT NULL,
  `profileId` int(11) NOT NULL,
  `toHeight` int(11) NOT NULL,
  `toAge` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `searchprofiles`
--

INSERT INTO `searchprofiles` (`id`, `name`, `fromAge`, `hairColor`, `gender`, `fromHeight`, `confession`, `profileId`, `toHeight`, `toAge`) VALUES
(1, 'Große Liebe', 20, 'DEFAULT', 'MALE', 170, 'DEFAULT', 6, 190, 70),
(2, 'Traumpartner', 20, 'BLACK', 'FEMALE', 150, 'DEFAULT', 5, 175, 40),
(3, 'Erster Versuch', 30, 'BLOND', 'MALE', 170, 'PROTESTANT', 2, 185, 50),
(4, 'Suchprofil 1', 20, 'DEFAULT', NULL, 160, 'MUSLIM', 5, 170, 40),
(5, 'Lets Try', 20, 'BROWN', 'MALE', 160, 'DEFAULT', 16, 190, 60),
(6, 'Reich und gutaussehend', 25, 'BROWN', 'MALE', 180, 'CATHOLIC', 17, 190, 30),
(8, 'Perfekte Vorstellung', 0, 'BROWN', 'MALE', 170, 'DEFAULT', 18, 190, 0),
(9, 'Auf der Suche', 23, 'OTHERS', 'FEMALE', 130, 'DEFAULT', 19, 180, 30),
(10, 'Jorgo', 0, 'DEFAULT', 'MALE', 130, 'DEFAULT', 19, 170, 0),
(11, 'Test', 0, 'DEFAULT', NULL, 0, 'DEFAULT', 19, 0, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `selections`
--

CREATE TABLE `selections` (
  `id` int(11) NOT NULL,
  `textualDescriptionForProfile` text COLLATE utf8_bin NOT NULL,
  `textualDescriptionForSearchProfile` text COLLATE utf8_bin NOT NULL,
  `propertyName` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `selections`
--

INSERT INTO `selections` (`id`, `textualDescriptionForProfile`, `textualDescriptionForSearchProfile`, `propertyName`) VALUES
(1, 'Ich höre gerne:', 'Meine große Liebe hört gerne:', 'Musik'),
(2, 'Ich betreibe gerne folgende Sportart/en:', 'Meine große Liebe betreibt gerne folgende Sportart/en:', 'Sport'),
(3, 'Ich schaue gerne:', 'Meine große Liebe schaut gerne:', 'Filme'),
(4, 'Ich lese gerne:', 'Meine große Liebe liest gerne:', 'Bücher');

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
(1, 3, 2, 0.0625),
(2, 3, 4, 0.05555555555555555),
(6, 3, 1, 0.2222222222222222),
(7, 3, 6, 0),
(8, 3, 7, 0.29411764705882354),
(9, 3, 5, 0.1),
(11, 2, 3, 0.0625),
(12, 2, 4, 0.17647058823529413),
(16, 2, 1, 0.0625),
(17, 2, 6, 0.058823529411764705),
(18, 2, 7, 0.25),
(19, 2, 5, 0.05263157894736842),
(21, 4, 3, 0.05555555555555555),
(22, 4, 2, 0.17647058823529413),
(26, 4, 1, 0.15),
(27, 4, 6, 0.05555555555555555),
(28, 4, 7, 0.2222222222222222),
(29, 4, 5, 0.13636363636363635),
(61, 1, 3, 0.2222222222222222),
(62, 1, 2, 0.0625),
(63, 1, 4, 0.15),
(67, 1, 6, 0.23529411764705882),
(68, 1, 7, 0.1111111111111111),
(69, 1, 5, 0.13636363636363635),
(71, 6, 3, 0),
(72, 6, 2, 0.058823529411764705),
(73, 6, 4, 0.05555555555555555),
(77, 6, 1, 0.23529411764705882),
(78, 6, 7, 0.17647058823529413),
(79, 6, 5, 0.10526315789473684),
(81, 7, 3, 0.29411764705882354),
(82, 7, 2, 0.25),
(83, 7, 4, 0.2222222222222222),
(87, 7, 1, 0.1111111111111111),
(88, 7, 6, 0.17647058823529413),
(89, 7, 5, 0.05),
(91, 5, 3, 0.1),
(92, 5, 2, 0.05263157894736842),
(93, 5, 4, 0.13636363636363635),
(97, 5, 1, 0.13636363636363635),
(98, 5, 6, 0.10526315789473684),
(99, 5, 7, 0.05),
(100, 1, 13, 0.05555555555555555),
(101, 13, 1, 0.05555555555555555),
(102, 1, 10, 0.1),
(103, 10, 1, 0.1),
(104, 1, 15, 0.18181818181818182),
(105, 15, 1, 0.18181818181818182),
(106, 1, 1, 0.6086956521739131),
(107, 1, 1, 0.5454545454545454),
(108, 1, 8, 0.15),
(109, 8, 1, 0.15),
(110, 1, 12, 0.05555555555555555),
(111, 12, 1, 0.05555555555555555),
(112, 1, 14, 0.2),
(113, 14, 1, 0.2),
(114, 1, 11, 0.2857142857142857),
(115, 11, 1, 0.2857142857142857),
(116, 1, 9, 0.15),
(117, 9, 1, 0.15),
(118, 2, 13, 0.2),
(119, 13, 2, 0.2),
(120, 2, 2, 0.6111111111111112),
(121, 2, 2, 0.5454545454545454),
(122, 2, 10, 0.25),
(123, 10, 2, 0.25),
(124, 2, 15, 0.05555555555555555),
(125, 15, 2, 0.05555555555555555),
(126, 2, 8, 0.058823529411764705),
(127, 8, 2, 0.058823529411764705),
(128, 2, 12, 0.1875),
(129, 12, 2, 0.1875),
(130, 2, 14, 0.13333333333333333),
(131, 14, 2, 0.13333333333333333),
(132, 2, 11, 0.10526315789473684),
(133, 11, 2, 0.10526315789473684),
(134, 2, 9, 0.1875),
(135, 9, 2, 0.1875),
(136, 3, 3, 0.7058823529411765),
(137, 3, 3, 0.5454545454545454),
(138, 3, 13, 0.16666666666666666),
(139, 13, 3, 0.16666666666666666),
(140, 3, 10, 0.2222222222222222),
(141, 10, 3, 0.2222222222222222),
(142, 3, 15, 0.1),
(143, 15, 3, 0.1),
(144, 3, 8, 0.05555555555555555),
(145, 8, 3, 0.05555555555555555),
(146, 3, 12, 0.11764705882352941),
(147, 12, 3, 0.11764705882352941),
(148, 3, 14, 0.05555555555555555),
(149, 14, 3, 0.05555555555555555),
(150, 3, 11, 0.2),
(151, 11, 3, 0.2),
(152, 3, 9, 0.05555555555555555),
(153, 9, 3, 0.05555555555555555),
(154, 4, 13, 0.2222222222222222),
(155, 13, 4, 0.2222222222222222),
(156, 4, 4, 0.5909090909090909),
(157, 4, 4, 0.5833333333333334),
(158, 4, 10, 0.15789473684210525),
(159, 10, 4, 0.15789473684210525),
(160, 4, 15, 0.09090909090909091),
(161, 15, 4, 0.09090909090909091),
(162, 4, 8, 0.09523809523809523),
(163, 8, 4, 0.09523809523809523),
(164, 4, 12, 0.3333333333333333),
(165, 12, 4, 0.3333333333333333),
(166, 4, 14, 0.1111111111111111),
(167, 14, 4, 0.1111111111111111),
(168, 4, 11, 0.21052631578947367),
(169, 11, 4, 0.21052631578947367),
(170, 4, 9, 0.10526315789473684),
(171, 9, 4, 0.10526315789473684),
(172, 5, 13, 0.09090909090909091),
(173, 13, 5, 0.09090909090909091),
(174, 5, 10, 0.2727272727272727),
(175, 10, 5, 0.2727272727272727),
(176, 5, 15, 0.12),
(177, 15, 5, 0.12),
(178, 5, 8, 0.22727272727272727),
(179, 8, 5, 0.22727272727272727),
(180, 5, 12, 0.1),
(181, 12, 5, 0.1),
(182, 5, 14, 0.045454545454545456),
(183, 14, 5, 0.045454545454545456),
(184, 5, 11, 0.15384615384615385),
(185, 11, 5, 0.15384615384615385),
(186, 5, 5, 0.5769230769230769),
(187, 5, 5, 0.5833333333333334),
(188, 5, 9, 0.08695652173913043),
(189, 9, 5, 0.08695652173913043),
(190, 6, 13, 0.15789473684210525),
(191, 13, 6, 0.15789473684210525),
(192, 6, 10, 0),
(193, 10, 6, 0),
(194, 6, 15, 0.15),
(195, 15, 6, 0.15),
(196, 6, 8, 0.1111111111111111),
(197, 8, 6, 0.1111111111111111),
(198, 6, 6, 0.5714285714285714),
(199, 6, 6, 0.5454545454545454),
(200, 6, 12, 0.11764705882352941),
(201, 12, 6, 0.11764705882352941),
(202, 6, 14, 0.3125),
(203, 14, 6, 0.3125),
(204, 6, 11, 0.10526315789473684),
(205, 11, 6, 0.10526315789473684),
(206, 6, 9, 0.25),
(207, 9, 6, 0.25),
(208, 7, 13, 0.05555555555555555),
(209, 13, 7, 0.05555555555555555),
(210, 7, 10, 0.16666666666666666),
(211, 10, 7, 0.16666666666666666),
(212, 7, 15, 0.2),
(213, 15, 7, 0.2),
(214, 7, 8, 0.2222222222222222),
(215, 8, 7, 0.2222222222222222),
(216, 7, 12, 0.058823529411764705),
(217, 12, 7, 0.058823529411764705),
(218, 7, 7, 0.7058823529411765),
(219, 7, 7, 0.5454545454545454),
(220, 7, 14, 0.16666666666666666),
(221, 14, 7, 0.16666666666666666),
(222, 7, 11, 0.25),
(223, 11, 7, 0.25),
(224, 7, 9, 0.16666666666666666),
(225, 9, 7, 0.16666666666666666),
(226, 8, 13, 0.1111111111111111),
(227, 13, 8, 0.1111111111111111),
(228, 8, 10, 0.09523809523809523),
(229, 10, 8, 0.09523809523809523),
(230, 8, 15, 0.13043478260869565),
(231, 15, 8, 0.13043478260869565),
(232, 8, 8, 0.5909090909090909),
(233, 8, 8, 0.5454545454545454),
(234, 8, 12, 0.1111111111111111),
(235, 12, 8, 0.1111111111111111),
(236, 8, 14, 0.21052631578947367),
(237, 14, 8, 0.21052631578947367),
(238, 8, 11, 0.25),
(239, 11, 8, 0.25),
(240, 8, 9, 0.1),
(241, 9, 8, 0.1),
(242, 9, 13, 0.16666666666666666),
(243, 13, 9, 0.16666666666666666),
(244, 9, 10, 0.047619047619047616),
(245, 10, 9, 0.047619047619047616),
(246, 9, 15, 0.13043478260869565),
(247, 15, 9, 0.13043478260869565),
(248, 9, 12, 0.1111111111111111),
(249, 12, 9, 0.1111111111111111),
(250, 9, 14, 0.14285714285714285),
(251, 14, 9, 0.14285714285714285),
(252, 9, 11, 0.041666666666666664),
(253, 11, 9, 0.041666666666666664),
(254, 9, 9, 0.5909090909090909),
(255, 9, 9, 0.5454545454545454),
(256, 10, 13, 0.16666666666666666),
(257, 13, 10, 0.16666666666666666),
(258, 10, 10, 0.5909090909090909),
(259, 10, 10, 0.5454545454545454),
(260, 10, 15, 0.13043478260869565),
(261, 15, 10, 0.13043478260869565),
(262, 10, 12, 0.16666666666666666),
(263, 12, 10, 0.16666666666666666),
(264, 10, 14, 0.047619047619047616),
(265, 14, 10, 0.047619047619047616),
(266, 10, 11, 0.18181818181818182),
(267, 11, 10, 0.18181818181818182),
(268, 11, 13, 0),
(269, 13, 11, 0),
(270, 11, 15, 0.2),
(271, 15, 11, 0.2),
(272, 11, 12, 0.1),
(273, 12, 11, 0.1),
(274, 11, 14, 0.08695652173913043),
(275, 14, 11, 0.08695652173913043),
(276, 11, 11, 0.5),
(277, 11, 11, 0.5454545454545454),
(278, 12, 13, 0.2777777777777778),
(279, 13, 12, 0.2777777777777778),
(280, 12, 15, 0.1),
(281, 15, 12, 0.1),
(282, 12, 12, 0.7058823529411765),
(283, 12, 12, 0.5454545454545454),
(284, 12, 14, 0.05555555555555555),
(285, 14, 12, 0.05555555555555555),
(286, 13, 13, 0.5416666666666666),
(287, 13, 13, 0.5454545454545454),
(288, 13, 15, 0.1),
(289, 15, 13, 0.1),
(290, 13, 14, 0.1111111111111111),
(291, 14, 13, 0.1111111111111111),
(292, 14, 15, 0.13636363636363635),
(293, 15, 14, 0.13636363636363635),
(294, 14, 14, 0.5909090909090909),
(295, 14, 14, 0.5454545454545454),
(296, 15, 15, 0.5769230769230769),
(297, 15, 15, 0.5454545454545454),
(298, 16, 3, 0.25),
(299, 3, 16, 0.25),
(300, 16, 13, 0.14285714285714285),
(301, 13, 16, 0.14285714285714285),
(302, 16, 2, 0.07692307692307693),
(303, 2, 16, 0.07692307692307693),
(304, 16, 4, 0),
(305, 4, 16, 0),
(306, 16, 10, 0.16666666666666666),
(307, 10, 16, 0.16666666666666666),
(308, 16, 15, 0.25),
(309, 15, 16, 0.25),
(310, 16, 1, 0.25),
(311, 1, 16, 0.25),
(312, 16, 8, 0.25),
(313, 8, 16, 0.25),
(314, 16, 16, 0.5833333333333334),
(315, 16, 16, 0.5),
(316, 16, 6, 0.36363636363636365),
(317, 6, 16, 0.36363636363636365),
(318, 16, 12, 0.16666666666666666),
(319, 12, 16, 0.16666666666666666),
(320, 16, 7, 0.25),
(321, 7, 16, 0.25),
(322, 16, 14, 0.25),
(323, 14, 16, 0.25),
(324, 16, 11, 0.23076923076923078),
(325, 11, 16, 0.23076923076923078),
(326, 16, 5, 0.15384615384615385),
(327, 5, 16, 0.15384615384615385),
(328, 16, 9, 0.08333333333333333),
(329, 9, 16, 0.08333333333333333),
(330, 17, 3, 0.09090909090909091),
(331, 3, 17, 0.09090909090909091),
(332, 17, 13, 0.15384615384615385),
(333, 13, 17, 0.15384615384615385),
(334, 17, 17, 0.5454545454545454),
(335, 17, 17, 0.5),
(336, 17, 2, 0.08333333333333333),
(337, 2, 17, 0.08333333333333333),
(338, 17, 4, 0.09090909090909091),
(339, 4, 17, 0.09090909090909091),
(340, 17, 10, 0.09090909090909091),
(341, 10, 17, 0.09090909090909091),
(342, 17, 15, 0.18181818181818182),
(343, 15, 17, 0.18181818181818182),
(344, 17, 1, 0.2727272727272727),
(345, 1, 17, 0.2727272727272727),
(346, 17, 8, 0.2727272727272727),
(347, 8, 17, 0.2727272727272727),
(348, 17, 16, 0.18181818181818182),
(349, 16, 17, 0.18181818181818182),
(350, 17, 6, 0.18181818181818182),
(351, 6, 17, 0.18181818181818182),
(352, 17, 12, 0.09090909090909091),
(353, 12, 17, 0.09090909090909091),
(354, 17, 7, 0.2727272727272727),
(355, 7, 17, 0.2727272727272727),
(356, 17, 14, 0.2727272727272727),
(357, 14, 17, 0.2727272727272727),
(358, 17, 11, 0.25),
(359, 11, 17, 0.25),
(360, 17, 5, 0.16666666666666666),
(361, 5, 17, 0.16666666666666666),
(362, 17, 9, 0.09090909090909091),
(363, 9, 17, 0.09090909090909091),
(364, 18, 3, 0.06666666666666667),
(365, 3, 18, 0.06666666666666667),
(366, 18, 13, 0.17647058823529413),
(367, 13, 18, 0.17647058823529413),
(368, 18, 18, 0.5263157894736842),
(369, 18, 18, 0.5),
(370, 18, 17, 0.25),
(371, 17, 18, 0.25),
(372, 18, 2, 0.06666666666666667),
(373, 2, 18, 0.06666666666666667),
(374, 18, 4, 0.058823529411764705),
(375, 4, 18, 0.058823529411764705),
(376, 18, 10, 0),
(377, 10, 18, 0),
(378, 18, 15, 0.16666666666666666),
(379, 15, 18, 0.16666666666666666),
(380, 18, 1, 0.23529411764705882),
(381, 1, 18, 0.23529411764705882),
(382, 18, 8, 0.23529411764705882),
(383, 8, 18, 0.23529411764705882),
(384, 18, 16, 0.3333333333333333),
(385, 16, 18, 0.3333333333333333),
(386, 18, 6, 0.3076923076923077),
(387, 6, 18, 0.3076923076923077),
(388, 18, 12, 0.2),
(389, 12, 18, 0.2),
(390, 18, 7, 0.3333333333333333),
(391, 7, 18, 0.3333333333333333),
(392, 18, 14, 0.29411764705882354),
(393, 14, 18, 0.29411764705882354),
(394, 18, 11, 0.2631578947368421),
(395, 11, 18, 0.2631578947368421),
(396, 18, 5, 0.15),
(397, 5, 18, 0.15),
(398, 18, 9, 0.1111111111111111),
(399, 9, 18, 0.1111111111111111),
(400, 19, 3, 0.16666666666666666),
(401, 3, 19, 0.16666666666666666),
(402, 19, 13, 0.21428571428571427),
(403, 13, 19, 0.21428571428571427),
(404, 19, 18, 0.16666666666666666),
(405, 18, 19, 0.16666666666666666),
(406, 19, 17, 0.18181818181818182),
(407, 17, 19, 0.18181818181818182),
(408, 19, 2, 0.15384615384615385),
(409, 2, 19, 0.15384615384615385),
(410, 19, 19, 0.5833333333333334),
(411, 19, 19, 0.5),
(412, 19, 4, 0.16666666666666666),
(413, 4, 19, 0.16666666666666666),
(414, 19, 10, 0.07692307692307693),
(415, 10, 19, 0.07692307692307693),
(416, 19, 15, 0.15384615384615385),
(417, 15, 19, 0.15384615384615385),
(418, 19, 1, 0.25),
(419, 1, 19, 0.25),
(420, 19, 8, 0.07692307692307693),
(421, 8, 19, 0.07692307692307693),
(422, 19, 16, 0.09090909090909091),
(423, 16, 19, 0.09090909090909091),
(424, 19, 6, 0.07692307692307693),
(425, 6, 19, 0.07692307692307693),
(426, 19, 12, 0.16666666666666666),
(427, 12, 19, 0.16666666666666666),
(428, 19, 7, 0.3333333333333333),
(429, 7, 19, 0.3333333333333333),
(430, 19, 14, 0.16666666666666666),
(431, 14, 19, 0.16666666666666666),
(432, 19, 11, 0.15384615384615385),
(433, 11, 19, 0.15384615384615385),
(434, 19, 5, 0.15384615384615385),
(435, 5, 19, 0.15384615384615385),
(436, 19, 9, 0.16666666666666666),
(437, 9, 19, 0.16666666666666666);

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
(6, 5, 4),
(7, 16, 11),
(12, 16, 18),
(13, 19, 10),
(14, 19, 14),
(15, 19, 7),
(16, 19, 9),
(17, 16, 19);

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
  ADD KEY `descriptionId` (`descriptionId`),
  ADD KEY `searchProfileId` (`searchprofileId`);

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
  ADD CONSTRAINT `infos_ibfk_3` FOREIGN KEY (`descriptionId`) REFERENCES `descriptions` (`id`),
  ADD CONSTRAINT `infos_ibfk_4` FOREIGN KEY (`searchprofileId`) REFERENCES `searchprofiles` (`id`);

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
