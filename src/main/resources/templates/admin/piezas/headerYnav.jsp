<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.jsp">BikëMesiters Admin</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="/ProyectoTienda/Login?logout=true">Logout</a></li>
                    </ul>
                </li>
            </ul>   
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Usuarios</div>
                            <a class="nav-link" href="usuarios.jsp">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="user-group" class="svg-inline--fa fa-user-group" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path fill="currentColor" d="M224 256c70.7 0 128-57.31 128-128S294.7 0 224 0C153.3 0 96 57.31 96 128S153.3 256 224 256zM274.7 304H173.3c-95.73 0-173.3 77.6-173.3 173.3C0 496.5 15.52 512 34.66 512H413.3C432.5 512 448 496.5 448 477.3C448 381.6 370.4 304 274.7 304zM479.1 320h-73.85C451.2 357.7 480 414.1 480 477.3C480 490.1 476.2 501.9 470 512h138C625.7 512 640 497.6 640 479.1C640 391.6 568.4 320 479.1 320zM432 256C493.9 256 544 205.9 544 144S493.9 32 432 32c-25.11 0-48.04 8.555-66.72 22.51C376.8 76.63 384 101.4 384 128c0 35.52-11.93 68.14-31.59 94.71C372.7 243.2 400.8 256 432 256z"></path></svg></div>
                                Usuarios
                            </a>
                            <a class="nav-link" href="roles.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-dice"></i></div>
                                Roles
                            </a>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="star-half-stroke" class="svg-inline--fa fa-star-half-stroke" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="currentColor" d="M528.5 171.5l-146.4-21.29l-65.43-132.4c-5.873-11.83-17.31-17.81-28.78-17.81c-11.39 0-22.82 5.89-28.7 17.8l-65.43 132.4L47.47 171.5C21.2 175.3 10.68 207.6 29.72 226.1l105.9 102.1l-25.04 145.5C107 495.3 123.6 512 142.2 512c4.932 0 10.01-1.172 14.88-3.75L288 439.6l130.9 68.7c4.865 2.553 9.926 3.713 14.85 3.713c18.61 0 35.21-16.61 31.65-37.41l-25.05-145.5l105.9-102.1C565.3 207.6 554.8 175.3 528.5 171.5zM406.9 294.7L388.8 312.3l23.83 138.4L288 385.4V68l62.31 126.1l139.2 20.25L406.9 294.7z"></path></svg></div>
                                Valoraciones
                            </a>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="box" class="svg-inline--fa fa-box" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="currentColor" d="M447.6 158.8l-56.38-104.9C386.9 40.75 374.8 32 360.9 32H240v128h206.8C447.1 159.5 447.3 159.3 447.6 158.8zM0 192v240C0 458.5 21.49 480 48 480h352c26.51 0 48-21.49 48-48V192H0zM208 160V32H87.13c-13.88 0-26 8.75-30.38 21.88L.375 158.8C.75 159.3 .875 159.5 1.25 160H208z"></path></svg></div>
                                Pedidos
                            </a>

                            <div class="sb-sidenav-menu-heading">Productos</div>
                            <a class="nav-link" href="usuarios.jsp">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="bicycle" class="svg-inline--fa fa-bicycle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path fill="currentColor" d="M347.2 32C358.1 32 369.8 38.44 375.4 48.78L473.3 229.1C485.5 226.1 498.5 224 512 224C582.7 224 640 281.3 640 352C640 422.7 582.7 480 512 480C441.3 480 384 422.7 384 352C384 311.1 402.4 276.3 431.1 252.8L409.4 212.7L324.7 356.2C320.3 363.5 312.5 368 304 368H255C247.1 431.1 193.3 480 128 480C57.31 480 0 422.7 0 352C0 281.3 57.31 224 128 224C138.7 224 149.2 225.3 159.2 227.8L185.8 174.7L163.7 144H120C106.7 144 96 133.3 96 120C96 106.7 106.7 96 120 96H176C183.7 96 190.1 99.71 195.5 105.1L222.9 144H372.3L337.7 80H311.1C298.7 80 287.1 69.25 287.1 56C287.1 42.75 298.7 32 311.1 32H347.2zM440 352C440 391.8 472.2 424 512 424C551.8 424 584 391.8 584 352C584 312.2 551.8 280 512 280C508.2 280 504.5 280.3 500.8 280.9L533.1 340.6C539.4 352.2 535.1 366.8 523.4 373.1C511.8 379.4 497.2 375.1 490.9 363.4L458.6 303.7C447 316.5 440 333.4 440 352V352zM108.8 328.6L133.1 280.2C131.4 280.1 129.7 280 127.1 280C88.24 280 55.1 312.2 55.1 352C55.1 391.8 88.24 424 127.1 424C162.3 424 190.9 400.1 198.2 368H133.2C112.1 368 99.81 346.7 108.8 328.6H108.8zM290.3 320L290.4 319.9L217.5 218.7L166.8 320H290.3zM257.4 192L317 274.8L365.9 192H257.4z"></path></svg></div>
                                Productos
                            </a>
                            
                            <a class="nav-link" href="usuarios.jsp">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="piggy-bank" class="svg-inline--fa fa-piggy-bank" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="currentColor" d="M255.1 96c0 0 138.8 .375 143.9 .75c0-.25 .125-.5 .125-.75c0-53-42.1-96-95.1-96S207.1 43 207.1 96c0 2.125 .5 4.125 .625 6.25C223.7 98.25 239.6 96 255.1 96zM559.1 224l-29.53 .0035c-8.75-20-21.6-37.73-37.35-52.48L511.1 96l-32 .0063c-29.38 0-55.38 13.53-73 34.28C399.4 129.3 391.9 128 383.1 128H255.1C178.6 128 114.2 183 99.3 256l-43.32 .0039c-14.75 0-26.5-13.51-23.5-28.76C34.74 215.7 45.36 208 56.99 208h.9998c3.25 0 6-2.75 6-5.999v-20C63.99 178.8 61.24 176 57.99 176c-28.5 0-53.89 20.38-57.51 48.63c-4.375 34.13 22.26 63.38 55.51 63.38L95.99 288c0 52.25 25.36 98.13 63.99 127.3L159.1 480c0 17.6 14.4 32 32 32H223.1c17.6 0 32-14.4 32-32l.0026-32h128l-.0033 32c0 17.6 14.4 32 32 32h32c17.6 0 32-14.4 32-32l-.0002-64.73c11.75-8.875 22.32-19.39 31.32-31.26L559.1 384C568.8 384 576 376.8 576 368v-128C576 231.2 568.8 224 559.1 224zM431.1 288c-8.75 0-16-7.25-16-16S423.2 256 431.1 256s16 7.25 16 16S440.7 288 431.1 288z"></path></svg></div>
                                Ofertas
                            </a>

                            <a class="nav-link" href="usuarios.jsp">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="dolly" class="svg-inline--fa fa-dolly" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="currentColor" d="M294.2 277.8c17.1 5 34.62 13.38 49.5 24.62l161.5-53.75c8.375-2.875 12.88-11.88 10-20.25L454.8 47.25c-2.748-8.502-11.88-13-20.12-10.12l-61.13 20.37l33.12 99.38l-60.75 20.13l-33.12-99.38L251.2 98.13c-8.373 2.75-12.87 11.88-9.998 20.12L294.2 277.8zM574.4 309.9c-5.594-16.75-23.67-25.91-40.48-20.23l-202.5 67.51c-17.22-22.01-43.57-36.41-73.54-36.97L165.7 43.75C156.9 17.58 132.5 0 104.9 0H32C14.33 0 0 14.33 0 32s14.33 32 32 32h72.94l92.22 276.7C174.7 358.2 160 385.3 160 416c0 53.02 42.98 96 96 96c52.4 0 94.84-42.03 95.82-94.2l202.3-67.44C570.9 344.8 579.1 326.6 574.4 309.9zM256 448c-17.67 0-32-14.33-32-32c0-17.67 14.33-31.1 32-31.1S288 398.3 288 416C288 433.7 273.7 448 256 448z"></path></svg></div>
                                Proveedores
                            </a>

                            <a class="nav-link" href="categorias.jsp">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="box-archive" class="svg-inline--fa fa-box-archive" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="currentColor" d="M32 432C32 458.5 53.49 480 80 480h352c26.51 0 48-21.49 48-48V160H32V432zM160 236C160 229.4 165.4 224 172 224h168C346.6 224 352 229.4 352 236v8C352 250.6 346.6 256 340 256h-168C165.4 256 160 250.6 160 244V236zM480 32H32C14.31 32 0 46.31 0 64v48C0 120.8 7.188 128 16 128h480C504.8 128 512 120.8 512 112V64C512 46.31 497.7 32 480 32z"></path></svg></div>
                                Categorias
                            </a>

                            <div class="sb-sidenav-menu-heading">Configuración del sitio</div>
                            <a class="nav-link" href="charts.html">
                                <div class="sb-nav-link-icon"><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="cash-register" class="svg-inline--fa fa-cash-register" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="currentColor" d="M511 377.3l-26.53-158.6c-2.569-15.37-15.99-26.63-31.56-26.63L208.1 192.1v-64L288.1 128.1c17.6 0 32.01-14.4 32.01-32V32c0-17.6-14.4-32-32-32h-224c-17.6 0-32 14.4-32 32v63.96c0 17.6 14.4 32 32 32L144.1 128v64L59.24 192c-15.65 0-29.03 11.31-31.61 26.75c0 0-27.33 166.1-27.62 170.5V480c0 17.67 14.33 32 31.1 32H479.1c17.67 0 31.1-14.33 31.1-32l-.0052-90.75C511.7 385.1 511.3 380.6 511 377.3zM280.1 248c0-8.75 7.249-16 15.1-16h15.1c8.75 0 15.1 7.25 15.1 16v16c0 8.75-7.249 16-15.1 16h-15.1c-8.75 0-15.1-7.25-15.1-16V248zM248.1 312h15.1c8.75 0 15.1 7.25 15.1 16v16c0 8.75-7.249 16-15.1 16h-15.1c-8.75 0-15.1-7.25-15.1-16v-16C232.1 319.2 239.4 312 248.1 312zM216.1 232c8.75 0 15.1 7.25 15.1 16v16c0 8.75-7.249 16-15.1 16h-15.1c-8.75 0-15.1-7.25-15.1-16v-16c0-8.75 7.249-16 15.1-16H216.1zM80.12 80v-32h191.1v32H80.12zM120.1 280h-15.1c-8.75 0-15.1-7.25-15.1-16v-16c0-8.75 7.249-16 15.1-16h15.1c8.75 0 15.1 7.25 15.1 16v16C136.1 272.8 128.9 280 120.1 280zM136.1 344v-16c0-8.75 7.249-16 15.1-16h15.1c8.75 0 15.1 7.25 15.1 16v16c0 8.75-7.249 16-15.1 16h-15.1C143.4 360 136.1 352.8 136.1 344zM352.1 448c0 8.8-7.2 16-15.1 16H176.1c-8.8 0-15.1-7.2-15.1-16s7.2-16 15.1-16h159.1C344.9 432 352.1 439.2 352.1 448zM376.1 344c0 8.75-7.249 16-15.1 16h-15.1c-8.75 0-15.1-7.25-15.1-16v-16c0-8.75 7.249-16 15.1-16h15.1c8.75 0 15.1 7.25 15.1 16V344zM424.1 264c0 8.75-7.249 16-15.1 16h-15.1c-8.75 0-15.1-7.25-15.1-16v-16c0-8.75 7.249-16 15.1-16h15.1c8.75 0 15.1 7.25 15.1 16V264z"></path></svg></div>
                                Métodos de pago
                            </a>
                            <a class="nav-link" href="tables.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Opciones menu
                            </a>
                            <a class="nav-link" href="tables.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Configuración
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Usuario:</div>
                        ${sessionScope['usuarioNombre'] } <br>
                        ${sessionScope['usuarioDateLogin'] }
                    </div>
                </nav>
            </div>