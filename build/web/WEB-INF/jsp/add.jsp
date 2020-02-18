<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Spring Boot</title>
    </head>
    <body>
        <div class="container mt-4 col-lg-4">
            <div class="card border-info">
                <div class="card-header bg-info">
                    <h4>Agregar Nuevo Registro</h4>
                </div>
                <div class="card-body">
                    <form method="post">
                        <label>Apellido Paterno</label>
                        <input type="text" name="paterno" class="form-control" value=""/>
                        <label>Apellido Materno</label>
                        <input type="text" name="materno" class="form-control" value=""/>
                        <label>Nombres</label>
                        <input type="text" name="nombre" class="form-control" value=""/>
                        <label>Nacionalidad</label>
                        <input type="text" name="nacionalidad" class="form-control" value=""/>
                        <br/>
                        <input type="submit" value="Agregar" class="btn btn-success"/>
                        <br/>
                        <a href="index.htm">Regresar</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
