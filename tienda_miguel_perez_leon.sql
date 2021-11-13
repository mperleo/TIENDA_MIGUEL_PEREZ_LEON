-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 11-11-2021 a las 12:54:36
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
CREATE DATABASE IF NOT EXISTS `tienda_miguel_perez_leon` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tienda_miguel_perez_leon`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Urbanas', 'bicicletas eléctricas y preparadas para la ciudad'),
(2, 'Carretera', 'Ligeras y agiles'),
(3, 'Gravel', 'Preparadas para la aventura'),
(4, 'Montaña', 'Preparadas para todos los terrenos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`, `tipo`) VALUES
(1, 'factura_num', '4', 'numero'),
(2, 'contra_por_defecto', '12345Password', 'texto'),
(3, 'img_folder_prods', '/img/products', 'texto'),
(4, 'factura_nombre_empresa', 'BikëMeisters', 'texto'),
(5, 'factura_nif', 'B123124242-ZA', 'texto'),
(6, 'factura_direccion', 'Calle Santa Teresa 28, Zamora, Zamora, 49011', 'texto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `fecha_inicio` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_fin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pedidos`
--

CREATE TABLE `detalles_pedidos` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL,
  `precio_unidad` double DEFAULT NULL,
  `producto` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `producto_nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalles_pedidos`
--

INSERT INTO `detalles_pedidos` (`id`, `id_pedido`, `impuesto`, `precio_unidad`, `producto`, `total`, `unidades`, `producto_nombre`) VALUES
(1, 1, 21, 800, 3, 1600, 2, 'Cube access ws'),
(2, 1, 21, 6000, 9, 30000, 5, 'Marin Headlands 2'),
(3, 1, 21, 6000, 11, 18000, 3, 'Ortler Van Dyck wave'),
(5, 2, 21, 800, 3, 800, 1, 'Cube access ws'),
(6, 2, 21, 6000, 10, 6000, 1, 'Orbea orca aero'),
(7, 3, 21, 6000, 14, 6000, 1, 'Willier GTR team rim'),
(8, 4, 21, 6000, 9, 18000, 3, 'Marin Headlands 2'),
(9, 5, 21, 6000, 9, 6000, 1, 'Marin Headlands 2'),
(10, 6, 21, 800, 3, 800, 1, 'Cube access ws'),
(11, 7, 21, 6000, 14, 6000, 1, 'Willier GTR team rim'),
(12, 8, 21, 6000, 11, 6000, 1, 'Ortler Van Dyck wave'),
(13, 8, 21, 6000, 12, 6000, 1, 'Polygon Strattos'),
(14, 8, 21, 6000, 13, 6000, 1, 'Santa Cruz TallBoy 4'),
(15, 9, 21, 6000, 4, 6000, 1, 'Cube aim ex'),
(16, 9, 21, 6000, 10, 24000, 4, 'Orbea orca aero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones_menu`
--

CREATE TABLE `opciones_menu` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `nombre_opcion` varchar(255) DEFAULT NULL,
  `url_opcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `opciones_menu`
--

INSERT INTO `opciones_menu` (`id`, `id_rol`, `nombre_opcion`, `url_opcion`) VALUES
(1, 1, 'Opciones menú', '/admin/opcionesMenu/');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `metodo_pago` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `num_factura` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_usuario`, `fecha`, `metodo_pago`, `estado`, `num_factura`, `total`) VALUES
(1, 1, '2021-11-08 23:00:00', 'tarjeta', 'cancelado', NULL, 49600),
(2, 1, '2021-11-08 23:00:00', 'paypal', 'enviado', '2', 6800),
(3, 1, '2021-11-08 23:00:00', 'paypal', 'enviado', '3', 6000),
(4, 1, '2021-11-08 23:00:00', 'paypal', 'pendiente', NULL, 18000),
(5, 1, '2021-11-09 23:00:00', 'paypal', 'pendiente cancelación', NULL, 6000),
(6, 2, '2021-11-09 23:00:00', 'paypal', 'enviado', '4', 800),
(7, 2, '2021-11-09 23:00:00', 'paypal', 'cancelado', NULL, 6000),
(8, 2, '2021-11-09 23:00:00', 'paypal', 'pendiente', NULL, 18000),
(9, 2, '2021-11-09 23:00:00', 'paypal', 'pendiente cancelación', NULL, 30000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `fecha_alta` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_baja` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `impuesto` float DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `id_categoria`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`) VALUES
(1, 2, 'Cannondale TopStone carbon 4', 'muy bonita', 800, 12, '2021-11-08 23:00:00', '2021-10-31 23:00:00', 21, 'cannondale-topstone-carbon-4-champagne-1.jpg'),
(2, 3, 'Cannondale TopStone carbon 6', 'muy cara', 6000, 12, '2021-11-25 23:00:00', '2021-12-23 23:00:00', 21, 'cannondale-topstone-carbon-6-beetle-green-1.jpg'),
(3, 4, 'Cube access ws', 'muy bonita', 800, 7, '2021-11-09 23:00:00', '2022-06-16 22:00:00', 21, 'cube-access-ws-exc-women-stonegreynfern-1.jpg'),
(4, 4, 'Cube aim ex', 'muy cara', 6000, 12, '2021-11-09 23:00:00', '2022-11-22 23:00:00', 21, 'cube-aim-ex-bluenred-1.jpg'),
(5, 1, 'Excelsior Touring 3 speed', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'excelsior-touring-3-speed-tsp-opal-blue-boss-blue-1.jpg'),
(6, 1, 'Fixie blackheath', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'fixie-inc-blackheath-petrol-red-1.jpg'),
(7, 1, 'Johni-Loco viena', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'johnny-loco-vienna-urban-cruiser-step-over-piano-black-1.jpg'),
(8, 4, 'Kona operator', 'muy cara', 6000, 12, '2021-11-09 23:00:00', '2023-11-07 23:00:00', 21, 'kona-operator-cr-gloss-dark-green-metallic-green-2.jpg'),
(9, 3, 'Marin Headlands 2', 'muy cara', 6000, 12, '2021-11-09 23:00:00', '2021-11-25 23:00:00', 21, 'marin-headlands-2-gloss-teal-carbon-magenta-1.jpg'),
(10, 2, 'Orbea orca aero', 'muy cara', 6000, 8, '2021-11-09 23:00:00', '2021-11-23 23:00:00', 21, 'orbea-orca-aero-m20i-team-black-1.jpg'),
(11, 1, 'Ortler Van Dyck wave', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'ortler-van-dyck-wave-red-1.jpg'),
(12, 2, 'Polygon Strattos', 'muy cara', 6000, 12, '2021-11-09 23:00:00', '2021-11-23 23:00:00', 21, 'polygon-strattos-s3-white-1.jpg'),
(13, 4, 'Santa Cruz TallBoy 4', 'muy cara', 6000, 12, '2021-11-09 23:00:00', '2021-11-23 23:00:00', 21, 'santa-cruz-tallboy-4-cc-29-x01-reserve-gloss-black-1.jpg'),
(14, 2, 'Willier GTR team rim', 'muy cara', 6000, 10, '2021-11-09 23:00:00', '2021-11-23 23:00:00', 21, 'wilier-gtr-team-rim-105-black-red-glossy-finish-1.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'admin'),
(2, 'empleado'),
(3, 'usuario registrado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`) VALUES
(1, 3, 'perezleon.miguel@gmail.com', '1234', 'Miguel', 'Pérez', 'León', 'Calle Abajo número 6', 'Zamora', 'Bercianos de Aliste', '666666666', '71111111e'),
(2, 1, 'admin@admin.com', 'admin1', 'admin12', 'admin', 'admin', 'admin', 'admin', 'admn', 'admin', 'admin'),
(4, 2, 'empleado@empleado.com', '1234', 'empleado', 'empleado', 'empleado', 'empleado', 'empleado', 'empleado', '312312313123', '123123123123'),
(5, 3, '12@12.12', '12', '12', '12', '12', '12', '12', '12', '12', '12'),
(6, 3, 'correo@12.212', '12345Password', '1232', '312312', '313212', '123131', '3131', '131', '1313', '13123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pedidos`
--
ALTER TABLE `detalles_pedidos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles_pedidos`
--
ALTER TABLE `detalles_pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
