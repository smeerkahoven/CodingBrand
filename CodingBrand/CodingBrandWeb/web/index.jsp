<%-- 
    Document   : index
    Created on : Feb 1, 2019, 3:44:55 PM
    Author     : xeio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="jsLibro">
    <head>
        <link rel="stylesheet" href="lib/css/bootstrap.min.css" />
        <script type="text/javascript" src="lib/js/angular.min.js"></script>
        <script type="text/javascript" src="js/libro.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body ng-controller="frmLibro">

        <form>
            <div>
                <button type="button" class="btn btn-success" ng-click="nuevo()" > Nuevo</button>
            </div>
            <!-- PANEL LISTAR LIBROS -->
            <div class="panel panel-default" ng-show="showListarLibros">
                <div class="panel-heading">
                    <h3 class="panel-title">Listar Libros</h3>
                </div>
                <div class="panel-body">
                    <div class="container">
                        <div class="form-group row">
                            <label class="col-2">Titulo</label>
                            <input type="text" class="form-control col-4" ng-model="search.titulo" />

                            <label class="col-2">Autores</label>
                            <input type="number" class="form-control col-4" ng-model="search.numeroAutores" max="100" min="1" />

                        </div>

                        <div class="form-group row">
                            <label class="col-2">Edicion</label>
                            <input type="text" class="form-control col-4" ng-model="search.fechaEdicion" />

                            <div class="col-2"></div>
                            <button type="button" class="btn btn-primary" ng-click="buscar()" >Buscar</button>
                        </div>
                    </div>
                    

                    <table ng-show="showResultTable">
                        <thead>
                        <th>Titulo</th>
                        <th>Edicion</th>
                        <th>Autores</th>
                        </thead>
                        <tbody>
                            <tr ng-repeat="row in libros.data">
                                <td>{{row.titulo}}</td>
                                <td>{{row.fechaEdicion}}</td>
                                <td>{{row.numeroAutores}}</td>
                                <td>
                                    <button type="button" title="Editar" class="btn btn-sm btn-info" ng-click="editar(row)">Editar</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
            <!-- ./PANEL LISTAR LIBROS -->

            <!-- PANEL AGREGAR/EDITAR LIBRO -->
            <div class="panel panel-default" ng-show="showLibroForm">
                <div class="panel-heading">
                    <h3 class="panel-title">{{formulario}}</h3>
                </div>
                <div class="panel-body">
                    <div class="container">
                        <div class="form-group row">
                            <label class="col-2">Titulo</label>
                            <input type="text" class="form-control col-4" ng-model="data.titulo" />

                        </div>

                        <div class="form-group row">
                            <label class="col-2">Edicion</label>
                            <input type="text" class="form-control col-4" ng-model="data.fechaEdicion" />

                            <div class="col-2"></div>
                            <button type="button" class="btn btn-light" ng-click="cancelar()" >Cancelar</button>
                            <button type="button" class="btn btn-primary" ng-click="Guardar()" >Guardar</button>
                        </div>


                    </div>


                </div>
            </div>

            <!-- PANEL AGREGAR/EDITAR LIBRO -->
        </form>
    </body>
</html>
