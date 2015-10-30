'use strict';

angular.module('deusSkateShop',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Categoria',{templateUrl:'views/Categoria/search.html',controller:'SearchCategoriaController'})
      .when('/Categoria/new',{templateUrl:'views/Categoria/detail.html',controller:'NewCategoriaController'})
      .when('/Categoria/edit/:CategoriaId',{templateUrl:'views/Categoria/detail.html',controller:'EditCategoriaController'})
      .when('/Clientes',{templateUrl:'views/Cliente/search.html',controller:'SearchClienteController'})
      .when('/Clientes/new',{templateUrl:'views/Cliente/detail.html',controller:'NewClienteController'})
      .when('/Clientes/edit/:ClienteId',{templateUrl:'views/Cliente/detail.html',controller:'EditClienteController'})
      .when('/Fabricantes',{templateUrl:'views/Fabricante/search.html',controller:'SearchFabricanteController'})
      .when('/Fabricantes/new',{templateUrl:'views/Fabricante/detail.html',controller:'NewFabricanteController'})
      .when('/Fabricantes/edit/:FabricanteId',{templateUrl:'views/Fabricante/detail.html',controller:'EditFabricanteController'})
      .when('/Produtos',{templateUrl:'views/Produto/search.html',controller:'SearchProdutoController'})
      .when('/Produtos/new',{templateUrl:'views/Produto/detail.html',controller:'NewProdutoController'})
      .when('/Produtos/edit/:ProdutoId',{templateUrl:'views/Produto/detail.html',controller:'EditProdutoController'})
      .when('/Usuarios',{templateUrl:'views/Usuario/search.html',controller:'SearchUsuarioController'})
      .when('/Usuarios/new',{templateUrl:'views/Usuario/detail.html',controller:'NewUsuarioController'})
      .when('/Usuarios/edit/:UsuarioId',{templateUrl:'views/Usuario/detail.html',controller:'EditUsuarioController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
