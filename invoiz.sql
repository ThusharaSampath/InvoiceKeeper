-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2020 at 07:05 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `invoiz`
--

-- --------------------------------------------------------

--
-- Table structure for table `cheque`
--

CREATE TABLE `cheque` (
  `ChequeNo` int(255) NOT NULL,
  `InvoiceNo` varchar(400) DEFAULT NULL,
  `DealerId` int(255) DEFAULT NULL,
  `DealerName` varchar(255) NOT NULL,
  `ChequeAmount` double NOT NULL DEFAULT 0,
  `ChequeDate` date DEFAULT current_timestamp(),
  `ChequeExpireDate` date DEFAULT NULL,
  `ChequeExpireStatus` tinyint(1) DEFAULT NULL,
  `ChequePassStatus` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cheque`
--

INSERT INTO `cheque` (`ChequeNo`, `InvoiceNo`, `DealerId`, `DealerName`, `ChequeAmount`, `ChequeDate`, `ChequeExpireDate`, `ChequeExpireStatus`, `ChequePassStatus`) VALUES
(2500, '2000,1000,1000,1000,1000,1000,1000,1000,1000', 1, 'janet,janet,janet,janet,janet,janet,janet,janet,janet', 6500, '2020-02-21', NULL, 0, 0),
(3500, '1000,1000', 3, 'naaf,naaf', 2500, '2020-02-21', NULL, 0, 0),
(56565, '2000', 3, 'naaf', 554, '2020-02-05', NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `dealer`
--

CREATE TABLE `dealer` (
  `DealerId` int(255) NOT NULL,
  `DealerName` varchar(255) NOT NULL,
  `DealerTel` varchar(255) DEFAULT NULL,
  `DealerPeriod` int(255) DEFAULT NULL,
  `DealerCode` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dealer`
--

INSERT INTO `dealer` (`DealerId`, `DealerName`, `DealerTel`, `DealerPeriod`, `DealerCode`) VALUES
(1, 'janet', '71367919', 15, 'NOT SET'),
(2, 'nature', '71367945', 20, 'NOT SET'),
(3, 'naaf', '71367941', 20, 'NOT SET'),
(4, 'janet coorparation private limited', '324', 234, 'NOT SET');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `InvoiceNo` varchar(255) NOT NULL,
  `ChequeNo` varchar(450) DEFAULT NULL,
  `InvoiceAmount` double NOT NULL DEFAULT 0,
  `DealerId` int(255) NOT NULL,
  `DealerName` varchar(255) NOT NULL,
  `InvoiceDate` date NOT NULL DEFAULT current_timestamp(),
  `InvoiceDeadline` date NOT NULL DEFAULT current_timestamp(),
  `Short` varchar(255) DEFAULT NULL,
  `ChequeDate` varchar(450) NOT NULL DEFAULT current_timestamp(),
  `ChequeAmount` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`InvoiceNo`, `ChequeNo`, `InvoiceAmount`, `DealerId`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`, `Short`, `ChequeDate`, `ChequeAmount`) VALUES
('1000', '0,3500,2500', 2000, 1, 'janet', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:32,2020-02-21', 0),
('1000', '0,3500,2500', 2000, 3, 'naaf', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:32,2020-02-21', 56858),
('1000', '0,3500,2500', 2000, 3, 'naaf', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:32,2020-02-21', 56858),
('2000', '0,2500,56565', 2000, 3, 'naaf', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:46,2020-02-05', 0);

-- --------------------------------------------------------

--
-- Table structure for table `maindb`
--

CREATE TABLE `maindb` (
  `InvoiceNo` varchar(255) NOT NULL,
  `ChequeNo` varchar(400) NOT NULL DEFAULT '00000',
  `InvoiceAmount` double NOT NULL,
  `DealerId` int(255) NOT NULL,
  `DealerName` varchar(255) NOT NULL,
  `InvoiceDate` date NOT NULL DEFAULT current_timestamp(),
  `InvoiceDeadline` date NOT NULL,
  `Short` varchar(255) DEFAULT 'NO Short',
  `ChequeDate` varchar(400) NOT NULL DEFAULT current_timestamp(),
  `ChequeAmount` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cheque`
--
ALTER TABLE `cheque`
  ADD PRIMARY KEY (`ChequeNo`);

--
-- Indexes for table `dealer`
--
ALTER TABLE `dealer`
  ADD PRIMARY KEY (`DealerId`),
  ADD UNIQUE KEY `UNIQUE` (`DealerName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dealer`
--
ALTER TABLE `dealer`
  MODIFY `DealerId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
