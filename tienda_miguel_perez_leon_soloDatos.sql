-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 10-11-2021 a las 14:25:39
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
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`, `tipo`) VALUES
(1, 'factura_num', '3', 'numero'),
(2, 'contra_por_defecto', '12345Password', 'texto'),
(3, 'img_folder_prods', '/img/products', 'texto'),
(4, 'factura_nombre_empresa', 'BikëMeisters', 'texto'),
(5, 'factura_nif', 'B123124242-ZA', 'texto'),
(6, 'factura_direccion', 'Calle Santa Teresa 28, Zamora, Zamora, 49011', 'texto');

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
(11, 7, 21, 6000, 14, 6000, 1, 'Willier GTR team rim');

--
-- Volcado de datos para la tabla `opciones_menu`
--

INSERT INTO `opciones_menu` (`id`, `id_rol`, `nombre_opcion`, `url_opcion`) VALUES
(1, 1, 'Opciones menú', '/admin/opcionesMenu/');

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_usuario`, `fecha`, `metodo_pago`, `estado`, `num_factura`, `total`) VALUES
(1, 1, '2021-11-08 23:00:00', 'tarjeta', 'cancelado', NULL, 49600),
(2, 1, '2021-11-08 23:00:00', 'paypal', 'enviado', '2', 6800),
(3, 1, '2021-11-08 23:00:00', 'paypal', 'enviado', '3', 6000),
(4, 1, '2021-11-08 23:00:00', 'paypal', 'pendiente', NULL, 18000),
(5, 1, '2021-11-09 23:00:00', 'paypal', 'pendiente cancelación', NULL, 6000),
(6, 2, '2021-11-09 23:00:00', 'paypal', 'pendiente', NULL, 800),
(7, 2, '2021-11-09 23:00:00', 'paypal', 'pendiente', NULL, 6000);

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `id_categoria`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`) VALUES
(1, 2, 'Cannondale TopStone carbon 4', 'muy bonita', 800, 12, '2021-11-08 23:00:00', '2021-10-31 23:00:00', 21, 'cannondale-topstone-carbon-4-champagne-1.jpg'),
(2, 3, 'Cannondale TopStone carbon 6', 'muy cara', 6000, 12, '2021-11-25 23:00:00', '2021-12-23 23:00:00', 21, 'cannondale-topstone-carbon-6-beetle-green-1.jpg'),
(3, 4, 'Cube access ws', 'muy bonita', 800, 8, '2021-11-09 23:00:00', '2022-06-16 22:00:00', 21, 'cube-access-ws-exc-women-stonegreynfern-1.jpg'),
(4, 4, 'Cube aim ex', 'muy cara', 6000, 12, '2021-11-09 23:00:00', '2022-11-22 23:00:00', 21, 'cube-aim-ex-bluenred-1.jpg'),
(5, 1, 'Excelsior Touring 3 speed', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'excelsior-touring-3-speed-tsp-opal-blue-boss-blue-1.jpg'),
(6, 1, 'Fixie blackheath', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'fixie-inc-blackheath-petrol-red-1.jpg'),
(7, 1, 'Johni-Loco viena', 'muy bonita', 800, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'johnny-loco-vienna-urban-cruiser-step-over-piano-black-1.jpg'),
(8, 4, 'Kona operator', 'muy cara', 6000, 12, '2021-11-09 23:00:00', '2023-11-07 23:00:00', 21, 'kona-operator-cr-gloss-dark-green-metallic-green-2.jpg'),
(9, 1, 'Marin Headlands 2', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'marin-headlands-2-gloss-teal-carbon-magenta-1.jpg'),
(10, 1, 'Orbea orca aero', 'muy cara', 6000, 8, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'orbea-orca-aero-m20i-team-black-1.jpg'),
(11, 1, 'Ortler Van Dyck wave', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'ortler-van-dyck-wave-red-1.jpg'),
(12, 1, 'Polygon Strattos', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'polygon-strattos-s3-white-1.jpg'),
(13, 1, 'Santa Cruz TallBoy 4', 'muy cara', 6000, 12, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'santa-cruz-tallboy-4-cc-29-x01-reserve-gloss-black-1.jpg'),
(14, 1, 'Willier GTR team rim', 'muy cara', 6000, 10, '2020-01-11 23:00:00', '2020-01-11 23:00:00', 21, 'wilier-gtr-team-rim-105-black-red-glossy-finish-1.jpg');

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'admin'),
(2, 'empleado'),
(3, 'usuario registrado');

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`) VALUES
(1, 3, 'perezleon.miguel@gmail.com', '1234', 'Miguel', 'Pérez', 'León', 'Calle Abajo número 6', 'Zamora', 'Bercianos de Aliste', '666666666', '71111111e'),
(2, 1, 'admin@admin.com', 'admin1', 'admin12', 'admin', 'admin', 'admin', 'admin', 'admn', 'admin', 'admin'),
(4, 2, 'empleado@empleado.com', '1234', 'empleado', 'empleado', 'empleado', 'empleado', 'empleado', 'empleado', '312312313123', '123123123123'),
(5, 3, '12@12.12', '12', '12', '12', '12', '12', '12', '12', '12', '12');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
