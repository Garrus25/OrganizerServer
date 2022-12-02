-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 02 Gru 2022, 13:33
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `organizer`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE `groups` (
  `ID_GROUP` int(5) NOT NULL,
  `NAME` text NOT NULL,
  `GROUP_CODE` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `groups`
--

INSERT INTO `groups` (`ID_GROUP`, `NAME`, `GROUP_CODE`) VALUES
(10, 'Grupa1', 'G1'),
(20, 'Grupa2', 'G2'),
(30, 'Grupa3', 'G3');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups_users`
--

CREATE TABLE `groups_users` (
  `ID_USER` int(5) NOT NULL,
  `ID_GROUP` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `groups_users`
--

INSERT INTO `groups_users` (`ID_USER`, `ID_GROUP`) VALUES
(2, 20),
(3, 30);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `task`
--

CREATE TABLE `task` (
  `ID_TASK` varchar(10) NOT NULL,
  `NAME` text NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `CREATE_DATE` date NOT NULL,
  `DATE_OF_NOTIFICATION` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `task`
--

INSERT INTO `task` (`ID_TASK`, `NAME`, `DESCRIPTION`, `CREATE_DATE`, `DATE_OF_NOTIFICATION`) VALUES
('T1', 'Hubert', 'napraw telefon', '2022-11-27', '2022-11-29'),
('T2', 'Kamil', 'wyślij listy do naszych partnerów.', '2022-11-15', '2022-11-22'),
('T3', 'alibaba', 'dostarcz dostawe', '2022-11-07', '2022-11-14');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tasks_users`
--

CREATE TABLE `tasks_users` (
  `ID_USER` int(5) NOT NULL,
  `ID_TASK` varchar(255) NOT NULL,
  `IS_DISPLAY` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `tasks_users`
--

INSERT INTO `tasks_users` (`ID_USER`, `ID_TASK`, `IS_DISPLAY`) VALUES
(1, 'T1', 'purple'),
(2, 'T2', 'yellow'),
(3, 'T3', 'red'),
(4, 'T4', 'black');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `ID_USER` varchar(10) NOT NULL,
  `LOGIN` text NOT NULL,
  `PASSWORD` text NOT NULL,
  `EMAIL` text NOT NULL,
  `NAME` text NOT NULL,
  `SURNAME` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`ID_USER`, `LOGIN`, `PASSWORD`, `EMAIL`, `NAME`, `SURNAME`) VALUES
('1', 'hgalanty', '1qazxsw2!', 'hgalanty@op.pl', 'Hubert', 'Galanty'),
('2', 'kkowalski', '1234qaz', 'kkowalski@op.pl', 'Kamil', 'Kowalski'),
('3', 'alibaba', 'aliali123', 'alibaba@poczta.onet.pl', 'Alibaba', 'Scott');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`ID_GROUP`);

--
-- Indeksy dla tabeli `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`ID_TASK`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID_USER`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
