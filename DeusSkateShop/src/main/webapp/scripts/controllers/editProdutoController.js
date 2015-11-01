

angular.module('deusSkateShop').controller('EditProdutoController', function($scope, $routeParams, $location, ProdutoResource , CategoriaResource, FabricanteResource, VendaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.produto = new ProdutoResource(self.original);
            CategoriaResource.queryAll(function(items) {
                $scope.categoriaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.produto.categoria && item.id == $scope.produto.categoria.id) {
                        $scope.categoriaSelection = labelObject;
                        $scope.produto.categoria = wrappedObject;
                        self.original.categoria = $scope.produto.categoria;
                    }
                    return labelObject;
                });
            });
            FabricanteResource.queryAll(function(items) {
                $scope.fabricanteSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.produto.fabricante && item.id == $scope.produto.fabricante.id) {
                        $scope.fabricanteSelection = labelObject;
                        $scope.produto.fabricante = wrappedObject;
                        self.original.fabricante = $scope.produto.fabricante;
                    }
                    return labelObject;
                });
            });
            VendaResource.queryAll(function(items) {
                $scope.vendasSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.produto.vendas){
                        $.each($scope.produto.vendas, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.vendasSelection.push(labelObject);
                                $scope.produto.vendas.push(wrappedObject);
                            }
                        });
                        self.original.vendas = $scope.produto.vendas;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Produtos");
        };
        ProdutoResource.get({ProdutoId:$routeParams.ProdutoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.produto);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.produto.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Produtos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Produtos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.produto.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("categoriaSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.produto.categoria = {};
            $scope.produto.categoria.id = selection.value;
        }
    });
    $scope.$watch("fabricanteSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.produto.fabricante = {};
            $scope.produto.fabricante.id = selection.value;
        }
    });
    $scope.vendasSelection = $scope.vendasSelection || [];
    $scope.$watch("vendasSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.produto) {
            $scope.produto.vendas = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.produto.vendas.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});