-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2020 at 08:24 PM
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
(122, '1234', 1, 'hulu', 3000, '2020-03-25', NULL, 0, 0),
(544, '789', 3, 'spoty', 4500, '2020-03-31', NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `dealer`
--

CREATE TABLE `dealer` (
  `DealerId` int(255) NOT NULL,
  `DealerName` varchar(255) NOT NULL,
  `DealerTel` varchar(255) DEFAULT NULL,
  `DealerPeriod` int(255) DEFAULT NULL,
  `DealerCode` varchar(255) DEFAULT NULL,
  `AccountDetail` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dealer`
--

INSERT INTO `dealer` (`DealerId`, `DealerName`, `DealerTel`, `DealerPeriod`, `DealerCode`, `AccountDetail`) VALUES
(1, 'hulu', '45', 25, NULL, 'fineacc'),
(2, 'tidal', '11', 34, NULL, 'd'),
(3, 'spoty', '2333', 22, NULL, 'd3');

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
  `ChequeAmount` double NOT NULL DEFAULT 0,
  `CashAmount` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`InvoiceNo`, `ChequeNo`, `InvoiceAmount`, `DealerId`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`, `Short`, `ChequeDate`, `ChequeAmount`, `CashAmount`) VALUES
('1234', '122', 5000, 1, 'hulu', '2020-03-04', '2020-03-19', 'No Short', '2020-03-14 23:43:15,2020-03-25,2020-03-18', 3000, 2000),
('4321', NULL, 2000, 1, 'hulu', '2020-03-03', '2020-03-18', 'No Short', '2020-03-14 23:45:13', 0, 1000),
('789', '544', 10000, 3, 'spoty', '2020-03-02', '2020-03-17', 'No Short', '2020-03-14 23:47:58,2020-03-31,2020-03-30', 4500, 4999);

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
  MODIFY `DealerId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
