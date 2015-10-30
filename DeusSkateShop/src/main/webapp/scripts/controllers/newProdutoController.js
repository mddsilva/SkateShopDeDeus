
angular.module('deusSkateShop').controller('NewProdutoController', function ($scope, $location, locationParser, ProdutoResource , CategoriaResource, FabricanteResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produto = $scope.produto || {};
    
    $scope.categoriaList = CategoriaResource.queryAll(function(items){
        $scope.categoriaSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("categoriaSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.produto.categoria = {};
            $scope.produto.categoria.id = selection.value;
        }
    });
    
    $scope.fabricanteList = FabricanteResource.queryAll(function(items){
        $scope.fabricanteSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("fabricanteSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.produto.fabricante = {};
            $scope.produto.fabricante.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Produtos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProdutoResource.save($scope.produto, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Produtos");
    };
});