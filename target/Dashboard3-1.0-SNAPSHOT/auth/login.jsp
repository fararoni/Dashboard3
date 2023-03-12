<!DOCTYPE html>
<html lang="en">
    <head>
        <%! String titulo = "Iniciar sesión";
        %>
        <%@include file="/layout/head.jsp" %>
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">

            <div class="card card-outline card-primary">
                <div class="card-header text-center">
                    <a href="../../index2.html" class="h1"><b>Tese</b>ADMIN</a>
                </div>
                <div class="card-body">
                    <p class="login-box-msg">Ingresar para iniciar sesión</p>
                    <form action="../../index3.html" method="post">
                        <div class="input-group mb-3">
                            <input type="email" class="form-control" placeholder="Email">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-3">
                            <input type="password" class="form-control" placeholder="Password">
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-lock"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-8">
                                <div class="icheck-primary">
                                    <input type="checkbox" id="remember">
                                    <label for="remember">
                                        Mantener sesión iniciada
                                    </label>
                                </div>
                            </div>

                            <div class="col-4">
                                <button type="submit" class="btn btn-primary btn-block">Ingresar</button>
                            </div>

                        </div>
                    </form>

                    <p class="mb-1">
                        <a href="recuperar.jsp">Recuperar contraseña</a>
                    </p>
                    <p class="mb-0">
                        <a href="registro.jsp" class="text-center">Registrar nuevo usuario</a>
                    </p>
                </div>

            </div>

        </div>
        <%@include file="/layout/libraries.jsp" %>
    </body>
</html>
