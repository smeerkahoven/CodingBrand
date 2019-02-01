
var app = angular.module('jsLibro', ["jsLibro.controllers"]);

angular.module('jsLibro.controllers', []).controller('frmLibro',
        ['$scope', '$http', '$window', function ($scope, $http, $window) {

                $scope.saludo = 'Hola Mundo';
                $scope.search = {};
                $scope.showListarLibros = true;
                $scope.showLibroForm = false;
                
                $scope.cancelar = function () {
                    $scope.data = {} ;
                    $scope.showLibroForm = false ;
                    $scope.showListarLibros = true ;
                }

                $scope.editar = function (row) {
                    $scope.formulario = "EDITAR";
                    $scope.data = row;
                    $scope.showLibroForm = true;
                    $scope.showListarLibros = false;

                    if ($scope.search.id !== undefined) {

                        $http({
                            method: 'GET',
                            url: `http://localhost:8080/CodingBrandWeb/ws-api/${row.idLibro}`,
                            headers: {'Content-Type': 'application/json'}
                        }).then(
                                function (response) {
                                    console.log(response);
                                    $scope.data = response.data.user;
                                },
                                function (error) {
                                    console.log(error);
                                }

                        );

                    }

                }

                $scope.buscar = function () {
                    $http({
                        method: 'POST',
                        url: `http://localhost:8080/CodingBrandWeb/ws-api/libro/`,
                        headers: {'Content-Type': 'application/json'},
                        data: {search: $scope.search}
                    }).then(
                            function (response) {
                                $scope.libros = response.data;
                                $scope.showResultTable = true;

                            },
                            function (error) {
                                console.log(error);
                            }
                    );
                }

                // guardar
                $scope.guardar = function () {

                    return $http({
                        method: 'POST',
                        url: 'http://localhost:8080/ModuloWeb/ws-api/user',
                        headers: {'Content-Type': 'application/json'},
                        data: {user: $scope.data}
                    }
                    ).then(function (response) {
                        console.log(response)
                        $scope.data = {};
                    }, function (error) {
                        console.log(error)
                    });

                }

            }]
        );

