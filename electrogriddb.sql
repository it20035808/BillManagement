-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 07:52 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogriddb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `billID` int(11) NOT NULL,
  `billDate` date NOT NULL,
  `unitCost` int(11) NOT NULL,
  `unitsUsed` int(11) NOT NULL,
  `serviceCharge` int(11) NOT NULL,
  `totalCost` int(11) NOT NULL,
  `settled` tinyint(1) NOT NULL DEFAULT 0,
  `userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`billID`, `billDate`, `unitCost`, `unitsUsed`, `serviceCharge`, `totalCost`, `settled`, `userID`) VALUES
(1, '2022-02-01', 10, 15, 1000, 1150, 1, 1),
(11, '2022-02-01', 10, 16, 1000, 1160, 0, 3),
(12, '2022-03-01', 10, 13, 1000, 1130, 0, 1),
(14, '2022-03-01', 10, 13, 1000, 1130, 0, 1),
(17, '2022-05-01', 10, 13, 1000, 1130, 0, 4),
(21, '2020-01-01', 10, 13, 1000, 1130, 0, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`billID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `billID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
