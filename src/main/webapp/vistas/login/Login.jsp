<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Iniciar sesión</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.5.0/semantic.min.css" integrity="sha512-KXol4x3sVoO+8ZsWPFI/r5KBVB/ssCGB5tsv2nVOKwLg33wTFP3fmnXa47FdSVIshVTgsYk/1734xSk9aFIa4A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.5.0/semantic.min.js" integrity="sha512-Xo0Jh8MsOn72LGV8kU5LsclG7SUzJsWGhXbWcYs2MAmChkQzwiW/yTQwdJ8w6UA9C6EVG18GHb/TrYpYCjyAQw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
    <body>
        <div class="ui middle aligned center aligned grid" style="height: 100vh;">
            <div class="column" style="max-width: 450px;">
                <div class="ui container">
                    <div>
                        <img src="https://cmactacna.com.pe/wp-content/themes/cajatacna/img/logo.svg" alt="logo" width="150"/>
                    </div>
                    <h2 class="ui red image header">
                        <div class="content">
                            Iniciar Sesión
                        </div>
                    </h2>
                    <form class="ui large form">
                        <div class="ui stacked segment">
                            <div class="field">
                                <label>Usuario</label>
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="username" placeholder="Usuario">
                                </div>
                            </div>
                            <div class="field">
                                <label>Contraseña</label>
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="password" placeholder="Contraseña">
                                </div>
                            </div>
                            <div class="ui fluid large red submit button">Iniciar Sesión</div>
                        </div>
                        <div class="ui error message">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>