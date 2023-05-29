-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
<<<<<<< HEAD
-- Generation Time: May 28, 2023 at 05:45 PM
=======
-- Generation Time: May 28, 2023 at 04:51 PM
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oopproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `accountID` int(11) NOT NULL,
  `customerID` int(11) DEFAULT NULL,
  `accountNumber` int(6) DEFAULT NULL,
  `currentBal` decimal(37,2) DEFAULT 5000.00,
  `accountTypeID` int(11) DEFAULT 1,
  `creditLimit` decimal(37,2) DEFAULT NULL,
  `accountStatusID` int(11) DEFAULT NULL,
  `interestSavingsRateID` int(11) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateModified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accountID`, `customerID`, `accountNumber`, `currentBal`, `accountTypeID`, `creditLimit`, `accountStatusID`, `interestSavingsRateID`, `dateCreated`, `dateModified`) VALUES
<<<<<<< HEAD
(1, 2, 0, 20012.52, 1, 0.00, NULL, NULL, '2023-05-26 19:16:50', '2023-05-26 19:16:50'),
(6, 2, 0, 46611.00, 2, 50000.00, NULL, NULL, '2023-05-27 02:58:52', '2023-05-27 02:58:52'),
(9, 34, 0, 4412.52, 1, NULL, NULL, NULL, NULL, NULL),
(11, 40, NULL, 4012.52, 1, NULL, NULL, NULL, NULL, NULL),
(12, 41, NULL, 4012.52, 1, NULL, NULL, NULL, NULL, NULL);
=======
(1, 2, 0, 21000.00, 1, 0.00, NULL, NULL, '2023-05-26 19:16:50', '2023-05-26 19:16:50'),
(6, 2, 0, 45000.00, 2, 50000.00, NULL, NULL, '2023-05-27 02:58:52', '2023-05-27 02:58:52'),
(9, 34, 0, 5400.00, 1, NULL, NULL, NULL, NULL, NULL),
(11, 40, NULL, 5000.00, 1, NULL, NULL, NULL, NULL, NULL);
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f

-- --------------------------------------------------------

--
-- Table structure for table `accountstatus`
--

CREATE TABLE `accountstatus` (
  `accountStatusID` int(11) NOT NULL,
  `accountStatusDesc` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `accounttype`
--

CREATE TABLE `accounttype` (
  `accountTypeID` int(11) NOT NULL,
  `accountTypeDesc` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounttype`
--

INSERT INTO `accounttype` (`accountTypeID`, `accountTypeDesc`) VALUES
(1, 'Debit'),
(2, 'Credit'),
(3, 'Checking');

-- --------------------------------------------------------

--
-- Stand-in structure for view `account_balance_view`
-- (See below for the actual view)
--
CREATE TABLE `account_balance_view` (
`accountID` int(11)
,`customerID` int(11)
,`accountNumber` int(6)
,`accountTypeID` int(11)
,`currentBalance` decimal(38,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `balanceview`
-- (See below for the actual view)
--
CREATE TABLE `balanceview` (
`accountID` int(11)
,`balance` decimal(37,2)
);

<<<<<<< HEAD
=======
-- --------------------------------------------------------

--
-- Table structure for table `billpayments`
--

CREATE TABLE `billpayments` (
  `customerID` int(11) NOT NULL,
  `biller` varchar(255) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `date` date NOT NULL,
  `payment_method` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `billpayments`
--

INSERT INTO `billpayments` (`customerID`, `biller`, `amount`, `date`, `payment_method`) VALUES
(1, 'Globe Telecom', 12000.20, '2023-05-27', 'Debit'),
(2, 'VECO', 1500.50, '2023-05-27', 'Credit'),
(3, 'VECO', 1500.51, '2023-05-27', 'Credit');

>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f
-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `companyID` int(11) NOT NULL,
  `companyName` varchar(30) DEFAULT NULL,
  `companyType` varchar(30) DEFAULT NULL,
  `sharePrice` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `creditcards`
--

CREATE TABLE `creditcards` (
  `card_id` int(11) NOT NULL,
  `remaining_balance` double NOT NULL,
  `current_limit` double NOT NULL,
  `payment_due_date` date NOT NULL,
  `card_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `creditcards`
--

INSERT INTO `creditcards` (`card_id`, `remaining_balance`, `current_limit`, `payment_due_date`, `card_type`) VALUES
(3, 500, 1000, '2023-12-31', ''),
(4, 0, 5000, '2023-12-31', ''),
(5, 500, 1000, '2023-12-31', ''),
(6, 500, 1000, '2023-12-31', 'Visa');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerID` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `middleInitial` varchar(4) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `zipcode` varchar(30) DEFAULT NULL,
  `emailAddress` varchar(50) DEFAULT NULL,
  `contactNumber` varchar(30) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateModified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerID`, `username`, `password`, `firstName`, `lastName`, `middleInitial`, `province`, `zipcode`, `emailAddress`, `contactNumber`, `birthday`, `dateCreated`, `dateModified`) VALUES
(2, '', '', 'Keath', 'Lavador', 'A', 'Cebu', '6014', 'keath.ian@gmail.com', '09569858219', '2023-05-18', '2023-05-26 19:52:37', '2023-05-26 19:52:39'),
(34, 'ket', 'ket', 'Keath', 'Lavador', 'A.', 'cebu', '6014', 'keath.ian@gmail.com', '09569858219', '2002-03-07', NULL, NULL),
<<<<<<< HEAD
(40, 'alizaB', 'bataluna', 'aliza', 'Bataluna', 'P.', 'cebu', '6014', 'aliza@gmail.com', '09291121763', '2001-01-01', NULL, NULL),
(41, 'asd', 'asd1', 'asd', 'asd', 'a', 'asd', '6000', 'asd@gmail.com', '09123456789', '2001-01-01', NULL, NULL);
=======
(40, 'alizaB', 'bataluna', 'aliza', 'Bataluna', 'P.', 'cebu', '6014', 'aliza@gmail.com', '09291121763', '2001-01-01', NULL, NULL);
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f

-- --------------------------------------------------------

--
-- Table structure for table `interestsavingsrate`
--

CREATE TABLE `interestsavingsrate` (
  `interestSavingsRateID` int(11) NOT NULL,
  `interestRateDesc` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `investment`
--

CREATE TABLE `investment` (
  `investmentID` int(11) NOT NULL,
  `companyID` int(11) DEFAULT NULL,
  `amount` decimal(15,2) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateModiefied` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `loanID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `startDATE` date NOT NULL,
  `endDATE` date NOT NULL,
  `interestRate` float NOT NULL DEFAULT 3,
  `amount` double NOT NULL,
  `duration` int(11) NOT NULL,
  `monthlyDue` double(10,2) GENERATED ALWAYS AS (round(`amount` * (`interestRate` / 100 / 12) * pow(1 + `interestRate` / 100 / 12,`duration`) / (pow(1 + `interestRate` / 100 / 12,`duration`) - 1),2)) VIRTUAL,
  `loanBal` double(10,2) GENERATED ALWAYS AS (round(`monthlyDue` * `duration`,2)) VIRTUAL,
  `remainingBal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`loanID`, `customerID`, `startDATE`, `endDATE`, `interestRate`, `amount`, `duration`, `remainingBal`) VALUES
(91, 41, '2023-05-28', '2023-06-28', 3, 123, 1, 123.31);

--
-- Triggers `loan`
--
DELIMITER $$
CREATE TRIGGER `update_end_date` BEFORE INSERT ON `loan` FOR EACH ROW BEGIN
    SET NEW.endDATE = DATE_ADD(NEW.startDATE, INTERVAL 1 YEAR);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `loanstatus`
--

CREATE TABLE `loanstatus` (
  `statusID` int(11) NOT NULL,
  `statusDesc` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transactionlog`
--

CREATE TABLE `transactionlog` (
  `transactionID` int(11) NOT NULL,
  `transactionDate` datetime DEFAULT NULL,
  `transactionAmount` decimal(15,2) DEFAULT NULL,
  `TransactionDesc` varchar(255) NOT NULL,
  `transactionFee` decimal(15,2) DEFAULT NULL,
  `accountID` int(11) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  `investmentID` int(11) DEFAULT NULL,
  `companyID` int(11) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateModified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactionlog`
--

INSERT INTO `transactionlog` (`transactionID`, `transactionDate`, `transactionAmount`, `TransactionDesc`, `transactionFee`, `accountID`, `customerID`, `investmentID`, `companyID`, `dateCreated`, `dateModified`) VALUES
(1, '2023-05-26 20:06:26', 12000.00, 'deposit', 0.00, 1, 2, NULL, NULL, '2023-05-26 19:16:50', '2023-05-26 19:16:50'),
(2, '2023-05-26 20:06:26', -5000.00, 'withdraw', 0.00, 1, 2, NULL, NULL, '2023-05-26 19:16:50', '2023-05-26 19:16:50'),
(3, '2023-05-30 03:11:49', 5000.00, 'withdraw', 0.00, 6, 2, NULL, NULL, NULL, NULL),
(4, '2023-05-28 16:01:40', 100.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(5, '2023-05-28 16:09:01', 200.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(6, '2023-05-28 16:19:56', 1000.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(7, '2023-05-28 16:21:43', -200.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(8, '2023-05-28 16:22:04', 5000.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(9, '2023-05-28 16:30:38', -8000.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(10, '2023-05-28 17:33:19', 200.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(11, '2023-05-28 17:33:25', -200.00, 'deposit', NULL, 9, 34, NULL, NULL, NULL, NULL),
(12, '2023-05-28 18:39:17', 2000.00, 'Funds Transferred to aliza@gmail.com', NULL, 11, 40, NULL, NULL, NULL, NULL),
(13, '2023-05-28 18:39:17', 2000.00, 'Funds Sent to aliza@gmail.com', NULL, 9, 34, NULL, NULL, NULL, NULL),
(14, '2023-05-28 18:40:46', 2000.00, 'Funds Transferred to aliza@gmail.com', NULL, 11, 40, NULL, NULL, NULL, NULL),
(15, '2023-05-28 18:40:46', 2000.00, 'Funds Sent to aliza@gmail.com', NULL, 9, 34, NULL, NULL, NULL, NULL),
(16, '2023-05-28 18:41:58', 2000.00, 'Funds Transferred to aliza@gmail.com', NULL, 11, 40, NULL, NULL, NULL, NULL),
(17, '2023-05-28 18:42:23', -2000.00, 'Funds Sent to aliza@gmail.com', NULL, 9, 34, NULL, NULL, NULL, NULL),
(18, '2023-05-28 18:43:23', 2000.00, 'Funds Transferred to aliza@gmail.com', NULL, 11, 40, NULL, NULL, NULL, NULL),
<<<<<<< HEAD
(19, '2023-05-28 18:43:23', -2000.00, 'Funds Sent to aliza@gmail.com', NULL, 9, 34, NULL, NULL, NULL, NULL),
(20, '2023-05-28 20:57:22', 100.00, 'deposit', NULL, 12, 41, NULL, NULL, NULL, NULL),
(21, '2023-05-28 20:57:28', -100.00, 'deposit', NULL, 12, 41, NULL, NULL, NULL, NULL),
(22, '2023-05-28 20:58:55', 100.00, 'Funds Transferred to aliza@gmail.com', NULL, 11, 40, NULL, NULL, NULL, NULL),
(23, '2023-05-28 20:58:55', -100.00, 'Funds Sent to aliza@gmail.com', NULL, 12, 41, NULL, NULL, NULL, NULL);
=======
(19, '2023-05-28 18:43:23', -2000.00, 'Funds Sent to aliza@gmail.com', NULL, 9, 34, NULL, NULL, NULL, NULL);
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f

-- --------------------------------------------------------

--
-- Table structure for table `transactiontype`
--

CREATE TABLE `transactiontype` (
  `transactionTypeID` int(11) NOT NULL,
  `transactionTypeName` varchar(30) DEFAULT NULL,
  `transactionTypeDesc` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure for view `account_balance_view`
--
DROP TABLE IF EXISTS `account_balance_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `account_balance_view`  AS SELECT `a`.`accountID` AS `accountID`, `a`.`customerID` AS `customerID`, `a`.`accountNumber` AS `accountNumber`, `a`.`accountTypeID` AS `accountTypeID`, CASE WHEN `a`.`accountTypeID` = 1 THEN `a`.`currentBal`+ coalesce(`t`.`TransactionAmount`,0) WHEN `a`.`accountTypeID` = 2 THEN `a`.`creditLimit`- coalesce(`t`.`TransactionAmount`,0) END AS `currentBalance` FROM (`account` `a` left join (select `transactionlog`.`accountID` AS `accountID`,sum(`transactionlog`.`transactionAmount`) AS `TransactionAmount` from `transactionlog` group by `transactionlog`.`accountID`) `t` on(`a`.`accountID` = `t`.`accountID`)) ;

-- --------------------------------------------------------

--
-- Structure for view `balanceview`
--
DROP TABLE IF EXISTS `balanceview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `balanceview`  AS SELECT `transactionlog`.`accountID` AS `accountID`, sum(`transactionlog`.`transactionAmount`) AS `balance` FROM `transactionlog` GROUP BY `transactionlog`.`accountID` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`accountID`),
  ADD KEY `customerID` (`customerID`),
  ADD KEY `accountTypeID` (`accountTypeID`),
  ADD KEY `accountStatusID` (`accountStatusID`),
  ADD KEY `interestSavingsRateID` (`interestSavingsRateID`);

--
-- Indexes for table `accountstatus`
--
ALTER TABLE `accountstatus`
  ADD PRIMARY KEY (`accountStatusID`);

--
-- Indexes for table `accounttype`
--
ALTER TABLE `accounttype`
  ADD PRIMARY KEY (`accountTypeID`);

--
-- Indexes for table `billpayments`
--
ALTER TABLE `billpayments`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`companyID`);

--
-- Indexes for table `creditcards`
--
ALTER TABLE `creditcards`
  ADD PRIMARY KEY (`card_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `interestsavingsrate`
--
ALTER TABLE `interestsavingsrate`
  ADD PRIMARY KEY (`interestSavingsRateID`);

--
-- Indexes for table `investment`
--
ALTER TABLE `investment`
  ADD PRIMARY KEY (`investmentID`),
  ADD KEY `companyID` (`companyID`),
  ADD KEY `customerID` (`customerID`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`loanID`),
  ADD KEY `fk_accountID` (`customerID`);

--
-- Indexes for table `loanstatus`
--
ALTER TABLE `loanstatus`
  ADD PRIMARY KEY (`statusID`);

--
-- Indexes for table `transactionlog`
--
ALTER TABLE `transactionlog`
  ADD PRIMARY KEY (`transactionID`),
  ADD KEY `accountID` (`accountID`),
  ADD KEY `customerID` (`customerID`),
  ADD KEY `investmentID` (`investmentID`),
  ADD KEY `companyID` (`companyID`);

--
-- Indexes for table `transactiontype`
--
ALTER TABLE `transactiontype`
  ADD PRIMARY KEY (`transactionTypeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
<<<<<<< HEAD
  MODIFY `accountID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
=======
  MODIFY `accountID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f

--
-- AUTO_INCREMENT for table `accountstatus`
--
ALTER TABLE `accountstatus`
  MODIFY `accountStatusID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `accounttype`
--
ALTER TABLE `accounttype`
  MODIFY `accountTypeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
<<<<<<< HEAD
=======

--
-- AUTO_INCREMENT for table `billpayments`
--
ALTER TABLE `billpayments`
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `companyID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `creditcards`
--
ALTER TABLE `creditcards`
  MODIFY `card_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
<<<<<<< HEAD
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
=======
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f

--
-- AUTO_INCREMENT for table `interestsavingsrate`
--
ALTER TABLE `interestsavingsrate`
  MODIFY `interestSavingsRateID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `investment`
--
ALTER TABLE `investment`
  MODIFY `investmentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `loanID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- AUTO_INCREMENT for table `loanstatus`
--
ALTER TABLE `loanstatus`
  MODIFY `statusID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transactionlog`
--
ALTER TABLE `transactionlog`
<<<<<<< HEAD
  MODIFY `transactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
=======
  MODIFY `transactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
>>>>>>> 100770fe923463691f687e8bd6efe5f96f294a0f

--
-- AUTO_INCREMENT for table `transactiontype`
--
ALTER TABLE `transactiontype`
  MODIFY `transactionTypeID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE,
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`accountTypeID`) REFERENCES `accounttype` (`accountTypeID`),
  ADD CONSTRAINT `account_ibfk_3` FOREIGN KEY (`accountStatusID`) REFERENCES `accountstatus` (`accountStatusID`),
  ADD CONSTRAINT `account_ibfk_4` FOREIGN KEY (`interestSavingsRateID`) REFERENCES `interestsavingsrate` (`interestSavingsRateID`);

--
-- Constraints for table `investment`
--
ALTER TABLE `investment`
  ADD CONSTRAINT `investment_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`),
  ADD CONSTRAINT `investment_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`);

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `fk_customerID` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`);

--
-- Constraints for table `transactionlog`
--
ALTER TABLE `transactionlog`
  ADD CONSTRAINT `transactionlog_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`),
  ADD CONSTRAINT `transactionlog_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`),
  ADD CONSTRAINT `transactionlog_ibfk_4` FOREIGN KEY (`investmentID`) REFERENCES `investment` (`investmentID`),
  ADD CONSTRAINT `transactionlog_ibfk_5` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
