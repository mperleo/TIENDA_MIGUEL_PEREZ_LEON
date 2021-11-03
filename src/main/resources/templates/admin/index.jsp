<!DOCTYPE html>
<html lang="en">
    <%@ include file="js/piezas/head.jsp" %>
    <body>
        <%@ include file="js/piezas/headerYnav.jsp" %>
        
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Static Navigation</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                            <li class="breadcrumb-item active">Static Navigation</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                    This page is an example of using static navigation. By removing the
                                    <code>.sb-nav-fixed</code>
                                    class from the
                                    <code>body</code>
                                    , the top navigation and side navigation will become static on scroll. Scroll down this page to see an example.
                                </p>
                            </div>
                        </div>
                        
                    </div>
                </main>
                
                <%@ include file="js/piezas/footerYscripts.jsp" %>
    
            </div>
            
        </div> <!-- cierre del contenido que se importa del archivo js/piezas/headerYnac.jsp -->
    </body>
</html>
