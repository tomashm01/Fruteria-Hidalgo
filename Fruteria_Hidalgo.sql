-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 26-11-2021 a las 12:52:26
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Fruteria Hidalgo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Fruta`
--

CREATE TABLE `Fruta` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioUnidad` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Fruta`
--

INSERT INTO `Fruta` (`id`, `nombre`, `cantidad`, `precioUnidad`) VALUES
(1, 'Piña', 1, 1.22),
(2, 'Manzana', 142, 0.42),
(4, 'Pera', 146, 0.24),
(5, 'Platano', 350, 0.46),
(6, 'Sandia', 34, 3.65);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FrutasTicket`
--

CREATE TABLE `FrutasTicket` (
  `id` int(11) NOT NULL,
  `idTicket` int(11) NOT NULL,
  `idFruta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `FrutasTicket`
--

INSERT INTO `FrutasTicket` (`id`, `idTicket`, `idFruta`) VALUES
(3, 20, 2),
(7, 18, 7),
(8, 19, 2),
(9, 21, 2),
(10, 22, 2),
(11, 23, 2),
(12, 24, 2),
(13, 25, 5),
(14, 26, 4),
(15, 28, 4),
(16, 29, 1),
(17, 30, 1),
(18, 30, 2),
(19, 30, 4),
(20, 31, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Persona`
--

CREATE TABLE `Persona` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `rol` varchar(25) COLLATE utf8mb4_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Persona`
--

INSERT INTO `Persona` (`id`, `nombre`, `rol`) VALUES
(1, 'Tomas', 'Admin'),
(2, 'Juan', 'Comprador'),
(3, 'Pedro', 'Comprador'),
(4, 'Maria', 'Comprador'),
(6, 'Carla', 'Admin'),
(8, 'Manuel', 'Comprador'),
(10, 'Maria', 'Comprador'),
(12, 'Epi', 'Comprador'),
(13, 'Juanito', 'Comprador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Ticket`
--

CREATE TABLE `Ticket` (
  `id` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL,
  `idFrutas` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `precioTotal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `Ticket`
--

INSERT INTO `Ticket` (`id`, `idPersona`, `idFrutas`, `fecha`, `precioTotal`) VALUES
(17, 2, 6, '2021-11-25', 2.1),
(18, 2, 6, '2021-11-25', 2.1),
(19, 2, 6, '2021-11-25', 4.2),
(20, 2, 6, '2021-11-25', 2.1),
(21, 2, 6, '2021-11-25', 2.1),
(22, 2, 6, '2021-11-25', 4.2),
(23, 2, 6, '2021-11-25', 2.1),
(24, 2, 12, '2021-11-25', 4.2),
(25, 2, 13, '2021-11-26', 4.6),
(26, 2, 14, '2021-11-26', 2.4),
(27, 2, 15, '2021-11-26', 0),
(28, 4, 15, '2021-11-26', 2.4),
(29, 3, 16, '2021-11-26', 12.2),
(30, 2, 19, '2021-11-26', 1.88),
(31, 2, 21, '2021-11-26', 107.36);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Fruta`
--
ALTER TABLE `Fruta`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `FrutasTicket`
--
ALTER TABLE `FrutasTicket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idTicket` (`idTicket`),
  ADD KEY `idFruta` (`idFruta`);

--
-- Indices de la tabla `Persona`
--
ALTER TABLE `Persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Ticket`
--
ALTER TABLE `Ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idPersona` (`idPersona`),
  ADD KEY `idFrutas` (`idFrutas`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Fruta`
--
ALTER TABLE `Fruta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `FrutasTicket`
--
ALTER TABLE `FrutasTicket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `Persona`
--
ALTER TABLE `Persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `Ticket`
--
ALTER TABLE `Ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `FrutasTicket`
--
ALTER TABLE `FrutasTicket`
  ADD CONSTRAINT `fk3` FOREIGN KEY (`idTicket`) REFERENCES `Ticket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk4` FOREIGN KEY (`idFruta`) REFERENCES `Fruta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Ticket`
--
ALTER TABLE `Ticket`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2` FOREIGN KEY (`idFrutas`) REFERENCES `FrutasTicket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
