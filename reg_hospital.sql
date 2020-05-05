-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 06:06 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf`
--

-- --------------------------------------------------------

--
-- Table structure for table `reg_hospital`
--

CREATE TABLE `reg_hospital` (
  `Hospital_ID` int(11) NOT NULL,
  `H_Name` varchar(30) CHARACTER SET latin1 NOT NULL,
  `H_Address` varchar(40) CHARACTER SET latin1 NOT NULL,
  `H_City` varchar(20) CHARACTER SET latin1 NOT NULL,
  `H_phonenumber` int(10) NOT NULL,
  `H_Desc` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reg_hospital`
--

INSERT INTO `reg_hospital` (`Hospital_ID`, `H_Name`, `H_Address`, `H_City`, `H_phonenumber`, `H_Desc`) VALUES
(56, 'General Hospital Colombo.', 'Colobo 7.', 'Colombo', 712456789, 'This is the largest hospital in Colombo.'),
(58, 'Asiri Hospital', 'Galle Road Colombo 7.', 'Colombo ', 671230848, 'This is the largest hospital in Colombo.'),
(59, 'Navaloka Hospital', 'Malabe, Sri Lanka.', 'Colombo', 719992221, 'The largest hospital in Colombo.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reg_hospital`
--
ALTER TABLE `reg_hospital`
  ADD PRIMARY KEY (`Hospital_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reg_hospital`
--
ALTER TABLE `reg_hospital`
  MODIFY `Hospital_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
