-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-11-2021 a las 19:03:46
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_miguel_perez_leon`
--

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Urbanas', 'bicicletas eléctricas y preparadas para la ciudad'),
(2, 'Carretera', 'Ligeras y agiles'),
(3, 'Gravel', 'Preparadas para la aventura'),
(4, 'Montaña', 'Preparadas para todos los terrenos');

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `id_categoria`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`) VALUES
(1, 1, 'Cannondale TopStone carbon 4', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'cannondale-topstone-carbon-4-champagne-1.jpg'),
(2, 1, 'Cannondale TopStone carbon 6', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'cannondale-topstone-carbon-6-beetle-green-1.jpg'),
(3, 1, 'Cube access ws', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'cube-access-ws-exc-women-stonegreynfern-1.jpg'),
(4, 1, 'Cube aim ex', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'cube-aim-ex-bluenred-1.jpg'),
(5, 1, 'Excelsior Touring 3 speed', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'excelsior-touring-3-speed-tsp-opal-blue-boss-blue-1.jpg'),
(6, 1, 'Fixie blackheath', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'fixie-inc-blackheath-petrol-red-1.jpg'),
(7, 1, 'Johni-Loco viena', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'johnny-loco-vienna-urban-cruiser-step-over-piano-black-1.jpg'),
(8, 1, 'Kona operator', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'kona-operator-cr-gloss-dark-green-metallic-green-2.jpg'),
(9, 1, 'Marin Headlands 2', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'marin-headlands-2-gloss-teal-carbon-magenta-1.jpg'),
(10, 1, 'Orbea orca aero', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'orbea-orca-aero-m20i-team-black-1.jpg'),
(11, 1, 'Ortler Van Dyck wave', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'ortler-van-dyck-wave-red-1.jpg'),
(12, 1, 'Polygon Strattos', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'polygon-strattos-s3-white-1.jpg'),
(13, 1, 'Santa Cruz TallBoy 4', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'santa-cruz-tallboy-4-cc-29-x01-reserve-gloss-black-1.jpg'),
(14, 1, 'Willier GTR team rim', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'wilier-gtr-team-rim-105-black-red-glossy-finish-1.jpg');

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'admin'),
(2, 'usuario registrado'),
(3, 'anonimo');

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`) VALUES
(1, 2, 'perezleon.miguel@gmail.com', '1234', 'Miguel', 'Pérez', 'León', 'Calle Abajo número 6', 'Zamora', 'Bercianos de Aliste', '666666666', '71111111e'),
(2, 1, 'admin@admin.com', 'admin', 'admin', 'admin', 'admin', 'admin', 'admin', 'admn', 'admin', 'admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
