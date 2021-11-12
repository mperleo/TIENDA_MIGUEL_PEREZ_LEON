-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 11-11-2021 a las 13:58:13
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
(2, 2, 'Categorias', '/admin/opcionesMenu/'),
(3, 2, 'Usuarios', '/admin/Usuarios/'),
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
(14, 3, 'Cerrar sesión', '/login/cerrarSesion');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
