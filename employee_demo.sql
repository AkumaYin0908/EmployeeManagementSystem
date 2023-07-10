-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2023 at 10:49 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee_demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--
CREATE DATABASE IF NOT EXISTS employee_demo;
USE employee_demo;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `mid_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `birth_date` date NOT NULL,
  `position` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hr_admin`
--

CREATE TABLE `hr_admin` (
  `hr_id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `mid_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) NOT NULL,
  `birth_date` date NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hr_admin`
--

INSERT INTO `hr_admin` (`hr_id`, `first_name`, `mid_name`, `last_name`, `birth_date`, `user_name`, `password`) VALUES
(1, 'John Philip', NULL, 'Apulog', '1997-08-19', 'philip123', 'admin'),
(10, 'John Philip', '', 'Apulog', '2023-07-09', '123', '213');

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `position_id` int(11) NOT NULL,
  `position` varchar(30) NOT NULL,
  `salary_grade` int(11) NOT NULL,
  `active` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`position_id`, `position`, `salary_grade`, `active`) VALUES
(1001, 'Computer Programmer I', 11, 0),
(1002, 'Computer Programmer II', 15, 0),
(1003, 'Computer Programmer III', 18, 0),
(1004, 'Computer File Librarian I', 8, 0),
(1005, 'Computer File Librarian II', 10, 0),
(1006, 'Computer File Librarian III', 12, 0),
(1007, 'Computer Maintenance Technolog', 11, 0),
(1008, 'Computer Maintenance Technolog', 15, 0),
(1009, 'Computer Maintenance Technolog', 17, 0),
(1010, 'Project Manager I', 25, 0),
(1011, 'Project Manager II', 26, 0),
(1012, 'Project Manager III', 27, 0),
(1013, 'Project Manager IV', 28, 0),
(1014, 'Administrator I', 28, 0),
(1015, 'Administrator II', 29, 0),
(1016, 'Administrator III', 30, 0),
(1017, 'Accountant II', 15, 0),
(1018, 'Accountant III', 18, 0),
(1019, 'Accountant IV', 22, 0),
(1020, 'Accounting Analyst', 11, 0),
(1021, 'Accounting Clerk I', 4, 0),
(1022, 'Accounting Clerk II', 6, 0),
(1023, 'Accounting Clerk III', 8, 0),
(1024, 'Administrative Assistant I', 7, 0),
(1025, 'Administrative Assistant II', 8, 0),
(1026, 'Administrative Assistant III', 9, 0),
(1027, 'Administrative Assistant IV', 10, 0),
(1028, 'Administrative Assistant V', 11, 0);

-- --------------------------------------------------------

--
-- Table structure for table `salary_grade`
--

CREATE TABLE `salary_grade` (
  `salary_grade` int(11) NOT NULL,
  `salary` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `salary_grade`
--

INSERT INTO `salary_grade` (`salary_grade`, `salary`) VALUES
(1, '14309.00'),
(2, '15125.00'),
(3, '15992.00'),
(4, '16906.00'),
(5, '17871.00'),
(6, '18888.00'),
(7, '19963.00'),
(8, '21123.00'),
(9, '22504.00'),
(10, '24570.00'),
(11, '28484.00'),
(12, '30649.00'),
(13, '32833.00'),
(14, '35387.00'),
(15, '38197.00'),
(16, '41288.00'),
(17, '44688.00'),
(18, '48428.00'),
(19, '53296.00'),
(20, '59381.00'),
(21, '66140.00'),
(22, '73777.00'),
(23, '82407.00'),
(24, '92748.00'),
(25, '105566.00'),
(26, '119133.00'),
(27, '134464.00'),
(28, '151789.00'),
(29, '171366.00'),
(30, '193486.00'),
(31, '285072.00'),
(32, '339849.00'),
(33, '432918.00');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status`) VALUES
(1, 'EMPLOYED'),
(2, 'RESIGNED'),
(3, 'RETIRED');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `hr_admin`
--
ALTER TABLE `hr_admin`
  ADD PRIMARY KEY (`hr_id`),
  ADD UNIQUE KEY `user_name` (`user_name`);

--
-- Indexes for table `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`position_id`),
  ADD KEY `position_salary_fk` (`salary_grade`);

--
-- Indexes for table `salary_grade`
--
ALTER TABLE `salary_grade`
  ADD PRIMARY KEY (`salary_grade`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hr_admin`
--
ALTER TABLE `hr_admin`
  MODIFY `hr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `position`
--
ALTER TABLE `position`
  MODIFY `position_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1029;

--
-- AUTO_INCREMENT for table `salary_grade`
--
ALTER TABLE `salary_grade`
  MODIFY `salary_grade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `position`
--
ALTER TABLE `position`
  ADD CONSTRAINT `position_salary_fk` FOREIGN KEY (`salary_grade`) REFERENCES `salary_grade` (`salary_grade`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
