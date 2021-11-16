-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-11-2021 a las 10:06:26
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
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`, `imagen`) VALUES
(1, 'Urbanas', 'bicicletas eléctricas y preparadas para la ciudad', 'cats/1.png'),
(2, 'Carretera', 'Ligeras y agiles', 'cats/2.png'),
(3, 'Gravel', 'Preparadas para la aventura', 'cats/3.png'),
(4, 'Montaña', 'Preparadas para todos los terrenos', 'cats/4.png');

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
(1, 'factura_num', '11', 'numero'),
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
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `descuentos`
--

INSERT INTO `descuentos` (`id`, `codigo`, `descuento`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 'BLKFRIDAY20', 21, '2021-11-11', '2021-11-25');

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
(16, 9, 21, 6000, 10, 24000, 4, 'Orbea orca aero'),
(17, 10, 21, 968, 7, 968, 1, 'Johni-Loco viena'),
(18, 11, 21, 7260, 4, 7260, 1, 'Cube aim ex'),
(19, 13, 21, 7260, 4, 7260, 1, 'Cube aim ex'),
(20, 14, 21, 7260, 4, 7260, 1, 'Cube aim ex'),
(22, 16, 21, 968, 5, 968, 1, 'Excelsior Touring 3 speed'),
(23, 15, 21, 7260, 11, 7260, 1, 'Ortler Van Dyck wave'),
(24, 18, 21, 7260, 11, 7260, 1, 'Ortler Van Dyck wave'),
(25, 19, 21, 7260, 6, 7260, 1, 'Fixie blackheath'),
(27, 20, 21, 7260, 8, 7260, 1, 'Kona operator'),
(28, 21, 21, 7260, 12, 7260, 1, 'Polygon Strattos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `impuestos`
--

INSERT INTO `impuestos` (`id`, `impuesto`) VALUES
(1, 21),
(2, 10),
(3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `metodos_pago`
--

INSERT INTO `metodos_pago` (`id`, `metodo_pago`) VALUES
(1, 'ApplePay'),
(2, 'Tarjeta'),
(3, 'PayPal'),
(8, 'Google Pay');

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
(1, 1, 'Opciones menú', '/admin/opcionesMenu/'),
(2, 2, 'Categorias', '/admin/categorias/'),
(3, 2, 'Usuarios', '/admin/usuarios/'),
(4, 1, 'Configuraciones', '/admin/configuraciones'),
(5, 2, 'Productos', '/admin/productos'),
(6, 2, 'Pedidos', '/admin/pedidos/'),
(7, 3, 'Finalizar pedido', '/cesta/pedido'),
(8, 3, 'Pedidos', '/pedidos/'),
(9, 3, 'Cancelar pedido', '/pedidos/ver'),
(10, 3, 'Ver pedido', '/pedidos/cancelar'),
(11, 3, 'Ver perfil', '/miUsuario/ver'),
(12, 3, 'Modificar perfil', '/miUsuario/modificar'),
(13, 3, 'Modificar contraseña', '/miUsuario/modificarPass'),
(14, 3, 'Cerrar sesión', '/login/cerrarSesion'),
(15, 2, 'Proveedores', '/admin/proveedores/'),
(16, 1, 'Métodos de pago', '/admin/metodosPago/'),
(17, 1, 'Roles', '/admin/roles/'),
(18, 1, 'Impuestos', '/admin/impuestos/'),
(19, 1, 'Descuentos', '/admin/descuentos/');

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
  `total` double DEFAULT NULL,
  `codigo_descuento` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_usuario`, `fecha`, `metodo_pago`, `estado`, `num_factura`, `total`, `codigo_descuento`, `descuento`) VALUES
(1, 1, '2021-11-08 23:00:00', 'tarjeta', 'cancelado', NULL, 49600, NULL, NULL),
(2, 1, '2021-09-13 22:00:00', 'paypal', 'enviado', '2', 6800, NULL, NULL),
(3, 1, '2021-08-15 22:00:00', 'paypal', 'enviado', '3', 6000, NULL, NULL),
(4, 1, '2021-10-12 22:00:00', 'paypal', 'enviado', '5', 18000, NULL, NULL),
(5, 1, '2021-11-09 23:00:00', 'paypal', 'pendiente cancelación', NULL, 6000, NULL, NULL),
(6, 2, '2021-11-09 23:00:00', 'paypal', 'enviado', '4', 800, NULL, NULL),
(7, 2, '2021-11-09 23:00:00', 'paypal', 'cancelado', NULL, 6000, NULL, NULL),
(8, 2, '2021-07-12 22:00:00', 'paypal', 'enviado', '6', 18000, NULL, NULL),
(9, 2, '2021-11-09 23:00:00', 'paypal', 'pendiente cancelación', NULL, 30000, NULL, NULL),
(10, 1, '2021-11-11 23:00:00', 'PayPal', 'enviado', '7', 968, NULL, NULL),
(12, 1, '2021-11-13 23:00:00', 'PayPal', 'enviado', '8', 7260, NULL, NULL),
(13, 1, '2021-11-13 23:00:00', 'Tarjeta', 'enviado', '9', 7260, NULL, NULL),
(14, 1, '2021-11-13 23:00:00', 'PayPal', 'enviado', '10', 7260, NULL, NULL),
(17, 1, '2021-11-14 23:00:00', 'Tarjeta', 'enviado', '11', 7260, NULL, NULL),
(18, 1, '2021-11-14 23:00:00', 'Tarjeta', 'enviado', '11', 7260, NULL, NULL),
(19, 1, '2021-11-14 23:00:00', 'Tarjeta', 'enviado', '12', 7260, NULL, NULL),
(20, 1, '2021-11-14 23:00:00', 'Tarjeta', 'enviado', '13', 5735.4, 'BLKFRIDAY20', 21),
(21, 1, '2021-11-14 23:00:00', 'Tarjeta', 'enviado', '11', 7260, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date NOT NULL,
  `impuesto` float DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `precio_impuesto` double DEFAULT NULL,
  `proveedor_id` int(11) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`, `precio_impuesto`, `proveedor_id`, `categoria_id`) VALUES
(1, 'Cannondale TopStone carbon 4', 'muy bonita', 800, 12, '2021-10-12', '2021-11-17', 21, 'products/1.jpg', 968, 2, 2),
(3, 'Cube access ws', 'muy bonita', 800, 2, '2021-11-12', '2022-06-17', 21, 'products/cube-access-ws-exc-women-stonegreynfern-1.jpg', 968, 1, 4),
(4, 'Cube aim ex', 'muy cara', 6000, 10, '2021-11-12', '2022-11-23', 21, 'products/cube-aim-ex-bluenred-1.jpg', 7260, 1, 4),
(5, 'Excelsior Touring 3 speed', 'muy bonita', 800, 12, '2021-11-12', '2020-01-12', 21, 'products/excelsior-touring-3-speed-tsp-opal-blue-boss-blue-1.jpg', 968, 1, 1),
(6, 'Fixie blackheath', 'muy cara', 6000, 12, '2021-11-12', '2020-01-12', 21, 'products/fixie-inc-blackheath-petrol-red-1.jpg', 7260, 1, 1),
(7, 'Johni-Loco viena', 'muy bonita', 800, 11, '2021-11-12', '2020-01-12', 21, 'products/johnny-loco-vienna-urban-cruiser-step-over-piano-black-1.jpg', 968, 1, 1),
(8, 'Kona operator', 'muy cara', 6000, 12, '2021-11-12', '2023-11-08', 21, 'products/kona-operator-cr-gloss-dark-green-metallic-green-2.jpg', 7260, 1, 4),
(9, 'Marin Headlands 2', 'muy cara', 6000, 5, '2021-11-12', '2021-11-26', 21, 'products/marin-headlands-2-gloss-teal-carbon-magenta-1.jpg', 7260, 1, 3),
(10, 'Orbea orca aero', 'muy cara', 6000, 8, '2021-11-12', '2021-11-24', 21, 'products/orbea-orca-aero-m20i-team-black-1.jpg', 7260, 1, 2),
(11, 'Ortler Van Dyck wave', 'muy cara', 6000, 11, '2021-11-12', '2020-01-12', 21, 'products/ortler-van-dyck-wave-red-1.jpg', 7260, 1, 1),
(12, 'Polygon Strattos', 'muy cara', 6000, 11, '2021-11-12', '2021-11-24', 21, 'products/polygon-strattos-s3-white-2.jpg', 7260, 1, 2),
(13, 'Santa Cruz TallBoy 4', 'muy cara', 6000, 11, '2021-11-12', '2021-11-24', 21, 'products/santa-cruz-tallboy-4-cc-29-x01-reserve-gloss-black-1.jpg', 7260, 1, 4),
(14, 'Willier GTR team rim', 'muy cara', 6000, 10, '2021-11-12', '2021-11-24', 21, 'products/wilier-gtr-team-rim-105-black-red-glossy-finish-1.jpg', 7260, 1, 2);

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

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id`, `nombre`, `direccion`, `localidad`, `provincia`, `telefono`, `cif`, `email`) VALUES
(1, 'Manolo', 'Calle falsa 123', 'Zamora', 'Zamora', '66666666', '234424D', 'manolo@contactoCube.com'),
(2, 'Jose@empresa.com', 'Calle falsa 123,4', 'Zamora', 'Zamora', '666666666', '898524D', 'Jose@empresa.com');

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
(1, 3, 'perezleon.miguel@gmail.com', 'YWRtaW4=', 'Miguel', 'Pérez', 'León', 'Calle Abajo número 6', 'Zamora', 'Bercianos de Aliste', '666666666', '71111111e'),
(2, 1, 'admin@admin.com', 'YWRtaW4=', 'admin12', 'admin', 'admin', 'admin', 'admin', 'admn', 'admin', 'admin'),
(4, 2, 'empleado@empleado.com', 'YWRtaW4=', 'empleado', 'empleado', 'empleado', 'empleado', 'empleado', 'empleado', '312312313123', '123123123123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `producto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `valoraciones`
--

INSERT INTO `valoraciones` (`id`, `valoracion`, `comentario`, `usuario_id`, `producto_id`) VALUES
(1, 6, 'muy buena pero muy cara', 1, 4),
(3, 7, 'Muy buena calidad precio', 1, 3);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4s80lxlx2fkci25fcx4r0nbex` (`proveedor_id`),
  ADD KEY `FK2fwq10nwymfv7fumctxt9vpgb` (`categoria_id`);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmtbedrv2q0wjdsrvnb57g8whw` (`usuario_id`),
  ADD KEY `FKn14xyxjg57ghtqjjnc20innfh` (`producto_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalles_pedidos`
--
ALTER TABLE `detalles_pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FK2fwq10nwymfv7fumctxt9vpgb` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`),
  ADD CONSTRAINT `FK4s80lxlx2fkci25fcx4r0nbex` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedores` (`id`);

--
-- Filtros para la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD CONSTRAINT `FKmtbedrv2q0wjdsrvnb57g8whw` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `FKn14xyxjg57ghtqjjnc20innfh` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
