-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2020 at 12:29 PM
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
  `ChequeDate` varchar(450) NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`InvoiceNo`, `ChequeNo`, `InvoiceAmount`, `DealerId`, `DealerName`, `InvoiceDate`, `InvoiceDeadline`, `Short`, `ChequeDate`) VALUES
('1000', '0,3500,2500', 2000, 1, 'janet', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:32,2020-02-21'),
('1000', '0,3500,2500', 2000, 3, 'naaf', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:32,2020-02-21'),
('1000', '0,3500,2500', 2000, 3, 'naaf', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:32,2020-02-21'),
('2000', '0,2500,56565', 2000, 3, 'naaf', '2020-02-07', '2020-02-22', 'No Short', '2020-02-22 11:19:46,2020-02-05');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
