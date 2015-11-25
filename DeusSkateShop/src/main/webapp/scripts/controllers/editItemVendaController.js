

angular.module('deusSkateShop').controller('EditItemVendaController', function($scope, $routeParams, $location, ItemVendaResource , VendaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.itemVenda = new ItemVendaResource(self.original);
            VendaResource.queryAll(function(items) {
                $scope.vendasSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.itemVenda.vendas){
                        $.each($scope.itemVenda.vendas, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.vendasSelection.push(labelObject);
                                $scope.itemVenda.vendas.push(wrappedObject);
                            }
                        });
                        self.original.vendas = $scope.itemVenda.vendas;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/ItemVendas");
        };
        ItemVendaResource.get({ItemVendaId:$routeParams.ItemVendaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.itemVenda);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.itemVenda.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/ItemVendas");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/ItemVendas");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.itemVenda.$remove(successCallback, errorCallback);
    };
    
    $scope.vendasSelection = $scope.vendasSelection || [];
    $scope.$watch("vendasSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.itemVenda) {
            $scope.itemVenda.vendas = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.itemVenda.vendas.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});