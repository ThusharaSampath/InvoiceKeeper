-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 08, 2020 at 07:34 PM
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
  `InvoiceNo` int(255) DEFAULT NULL,
  `DealerId` int(255) DEFAULT NULL,
  `DealerName` varchar(255) NOT NULL,
  `ChequeAmount` int(255) DEFAULT NULL,
  `ChequeDate` date DEFAULT current_timestamp(),
  `ChequeExpireDate` date DEFAULT NULL,
  `ChequeExpireStatus` tinyint(1) DEFAULT NULL,
  `ChequePassStatus` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cheque`
--

INSERT INTO `cheque` (`ChequeNo`, `InvoiceNo`, `DealerId`, `DealerName`, `ChequeAmount`, `ChequeDate`, `ChequeExpireDate`, `ChequeExpireStatus`, `ChequePassStatus`) VALUES
(1, 1, 1, '', 1254, '2020-02-11', '2020-02-20', 1, 1),
(123, 123, 123, '', 13, '2020-02-11', '2020-02-05', 0, 0),
(2133, 123123, 1, '', 21, '2020-02-12', NULL, 0, 0),
(2213, 12321, 13, 'fog', 123, '2020-02-19', NULL, 0, 0),
(12332, 312323, 123123, '', 1232, '2020-02-27', NULL, 0, 1),
(32414, 324, 5455, '', 1254, '2020-02-06', '2020-02-20', 1, 0),
(34234, 34123, 18, 'kes', 34, '2020-02-14', NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `dealer`
--

CREATE TABLE `dealer` (
  `DealerId` int(255) NOT NULL,
  `DealerName` varchar(255) NOT NULL,
  `DealerTel` varchar(255) DEFAULT NULL,
  `DealerPeriod` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dealer`
--

INSERT INTO `dealer` (`DealerId`, `DealerName`, `DealerTel`, `DealerPeriod`) VALUES
(8, 'janett', '12', 122),
(10, 'tidal', '12344', 343),
(12, 'hifi', '12344', 12),
(13, 'fog', '12344', 24),
(14, 'sam', '213', 344),
(15, 'thus', '971361', 28),
(16, 'kas', '1231', 22),
(18, 'kes', '770836898', 11),
(19, 'xfdg', '34234235235', 12),
(20, 'dass', '213442134', 54),
(21, 'janet', '23425345', 454);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `InvoiceNo` int(255) NOT NULL,
  `ChequeNo` int(255) DEFAULT NULL,
  `InvoiceAmount` int(255) DEFAULT NULL,
  `DealerId` int(255) DEFAULT NULL,
  `DealerName` varchar(255) NOT NULL,
  `InvoiceDate` date DEFAULT current_timestamp(),
  `InvoiceDeadline` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`InvoiceNo`, `ChequeNo`, `InvoiceAmount`, `DealerId`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`) VALUES
(1, 213123, 123123, 9, '', '2020-02-08', '2020-03-04'),
(6, 56, 5465, 21, 'janet', '2020-02-12', '2020-02-27'),
(12, 423, 234, 12, 'hifi', '2020-02-08', '2020-03-14'),
(34, 343, 34235, 9, 'janet', '2020-02-10', '2020-02-28'),
(123, 1231, 232, 9, 'janet', '2020-02-08', '2020-03-09'),
(234, 54, 45, 21, 'janet', '2020-02-13', '2020-02-28'),
(1434, 654, 56456, 9, 'janet', '2020-02-14', '2020-02-28'),
(3434, 234, 34, 8, 'janett', '2020-06-10', '2020-02-23'),
(23423, 342, 234, 21, 'janet', '2020-02-12', '2020-03-18'),
(23454, 54, 45, 21, 'janet', '2020-02-13', '2020-02-28'),
(123123, 1233, 213, 9, '', '2020-02-08', '2020-03-04'),
(123234, 1233, 213, 9, '', '2020-02-08', '2020-03-04'),
(23424000, 34, 3, 8, '', '2020-02-08', '2020-03-14');

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
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`InvoiceNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dealer`
--
ALTER TABLE `dealer`
  MODIFY `DealerId` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
