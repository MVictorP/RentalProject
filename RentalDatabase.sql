-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2017 at 01:01 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `carID` int(4) UNSIGNED NOT NULL,
  `specID` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`carID`, `specID`) VALUES
(6456, 1),
(9355, 1),
(1298, 2),
(7654, 3),
(1128, 4),
(7772, 4),
(2456, 5),
(8463, 5),
(9362, 5),
(5656, 6),
(7221, 7),
(8342, 7);

-- --------------------------------------------------------

--
-- Stand-in structure for view `cars`
-- (See below for the actual view)
--
CREATE TABLE `cars` (
`carID` int(4) unsigned
,`make` varchar(255)
,`model` varchar(255)
,`year` varchar(255)
,`size` enum('small','midsize','large')
);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `custID` int(10) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`custID`, `name`, `telephone`, `address`) VALUES
(1, 'Samir James', '816-878-1111', '6102 NE Antioch Rd'),
(2, 'Kim Sam', '816-847-8887', '7123 Main Street'),
(3, 'Mehmet Scholl', '816-444-2387', '12 Rockhill Rd'),
(4, 'Delia Diorio', '347-756-6441', '809 Redbud Dr'),
(5, 'Natalie Long', '210-413-0164', '1380 Weekley St'),
(6, 'John Smith', '210-313-4514', '1213 Cookson St');

-- --------------------------------------------------------

--
-- Stand-in structure for view `rentable`
-- (See below for the actual view)
--
CREATE TABLE `rentable` (
`carID` int(4) unsigned
,`make` varchar(255)
,`model` varchar(255)
,`year` varchar(255)
,`size` enum('small','midsize','large')
,`status` enum('rented','returned')
);

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `rentalID` int(10) UNSIGNED NOT NULL,
  `rentDate` date NOT NULL,
  `returnDate` date DEFAULT NULL,
  `status` enum('rented','returned') NOT NULL DEFAULT 'rented',
  `custID` int(10) UNSIGNED NOT NULL,
  `carID` int(4) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`rentalID`, `rentDate`, `returnDate`, `status`, `custID`, `carID`) VALUES
(1, '2017-06-25', NULL, 'rented', 4, 2456),
(2, '2017-06-25', '2017-06-27', 'returned', 5, 7654),
(3, '2017-06-26', '2017-06-28', 'returned', 2, 1128),
(4, '2017-06-27', '2017-06-29', 'returned', 1, 5656),
(5, '2017-06-28', NULL, 'rented', 3, 7654),
(6, '2017-06-29', NULL, 'rented', 5, 9362),
(7, '2017-06-30', '2017-07-02', 'returned', 1, 7772),
(8, '2017-06-30', '2017-07-03', 'returned', 5, 8463),
(9, '2017-07-01', NULL, 'rented', 3, 8342),
(10, '2017-07-01', '2017-07-02', 'returned', 6, 9355),
(11, '2017-07-01', NULL, 'rented', 6, 6456),
(12, '2017-07-01', NULL, 'rented', 6, 1298),
(13, '2017-07-01', NULL, 'rented', 3, 1128);

-- --------------------------------------------------------

--
-- Stand-in structure for view `rented`
-- (See below for the actual view)
--
CREATE TABLE `rented` (
`rentalID` int(10) unsigned
,`carID` int(4) unsigned
,`make` varchar(255)
,`model` varchar(255)
,`year` varchar(255)
,`size` enum('small','midsize','large')
,`status` enum('rented','returned')
,`rentDate` date
,`returnDate` date
,`custID` int(10) unsigned
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `returned`
-- (See below for the actual view)
--
CREATE TABLE `returned` (
`rentalID` int(10) unsigned
,`carID` int(4) unsigned
,`make` varchar(255)
,`model` varchar(255)
,`year` varchar(255)
,`rentDate` date
,`returnDate` date
,`status` enum('rented','returned')
,`custID` int(10) unsigned
);

-- --------------------------------------------------------

--
-- Table structure for table `specification`
--

CREATE TABLE `specification` (
  `specID` int(10) UNSIGNED NOT NULL,
  `make` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `year` varchar(255) NOT NULL,
  `size` enum('small','midsize','large') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specification`
--

INSERT INTO `specification` (`specID`, `make`, `model`, `year`, `size`) VALUES
(1, 'Nissan', 'Altima', '2012', 'small'),
(2, 'Nissan', 'Altima', '2012', 'midsize'),
(3, 'Volkswagen', 'Passat', '2002', 'large'),
(4, 'Mercedes', 'S-Class', '2016', 'large'),
(5, 'Tesla', 'Roadster', '2016', 'small'),
(6, 'Tesla', 'Model S', '2015', 'midsize'),
(7, 'Volkswagen', 'Jetta', '2011', 'midsize');

-- --------------------------------------------------------

--
-- Structure for view `cars`
--
DROP TABLE IF EXISTS `cars`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cars`  AS  select `car`.`carID` AS `carID`,`specification`.`make` AS `make`,`specification`.`model` AS `model`,`specification`.`year` AS `year`,`specification`.`size` AS `size` from (`car` join `specification` on((`car`.`specID` = `specification`.`specID`))) ;

-- --------------------------------------------------------

--
-- Structure for view `rentable`
--
DROP TABLE IF EXISTS `rentable`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rentable`  AS  select `c`.`carID` AS `carID`,`c`.`make` AS `make`,`c`.`model` AS `model`,`c`.`year` AS `year`,`c`.`size` AS `size`,`rented`.`status` AS `status` from (`cars` `c` left join `rented` on((`c`.`carID` = `rented`.`carID`))) where isnull(`rented`.`carID`) ;

-- --------------------------------------------------------

--
-- Structure for view `rented`
--
DROP TABLE IF EXISTS `rented`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rented`  AS  select `rental`.`rentalID` AS `rentalID`,`car`.`carID` AS `carID`,`specification`.`make` AS `make`,`specification`.`model` AS `model`,`specification`.`year` AS `year`,`specification`.`size` AS `size`,`rental`.`status` AS `status`,`rental`.`rentDate` AS `rentDate`,`rental`.`returnDate` AS `returnDate`,`rental`.`custID` AS `custID` from ((`rental` join `car` on((`rental`.`carID` = `car`.`carID`))) join `specification` on((`car`.`specID` = `specification`.`specID`))) where (`rental`.`status` = 'rented') ;

-- --------------------------------------------------------

--
-- Structure for view `returned`
--
DROP TABLE IF EXISTS `returned`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `returned`  AS  select `rental`.`rentalID` AS `rentalID`,`car`.`carID` AS `carID`,`specification`.`make` AS `make`,`specification`.`model` AS `model`,`specification`.`year` AS `year`,`rental`.`rentDate` AS `rentDate`,`rental`.`returnDate` AS `returnDate`,`rental`.`status` AS `status`,`rental`.`custID` AS `custID` from ((`car` join `specification` on((`car`.`specID` = `specification`.`specID`))) join `rental` on((`rental`.`carID` = `car`.`carID`))) where (`rental`.`status` = 'returned') ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`carID`),
  ADD KEY `FK_car_specification_specID` (`specID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`custID`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`rentalID`),
  ADD KEY `FK_rental_car_carID` (`carID`),
  ADD KEY `FK_rental_customer_custID` (`custID`);

--
-- Indexes for table `specification`
--
ALTER TABLE `specification`
  ADD PRIMARY KEY (`specID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `custID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `rental`
--
ALTER TABLE `rental`
  MODIFY `rentalID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `specification`
--
ALTER TABLE `specification`
  MODIFY `specID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `FK_car_specification_specID` FOREIGN KEY (`specID`) REFERENCES `specification` (`specID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `FK_rental_car_carID` FOREIGN KEY (`carID`) REFERENCES `car` (`carID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_rental_customer_custID` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
