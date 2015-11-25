
angular.module('deusSkateShop').controller('NewItemVendaController', function ($scope, $location, locationParser, ItemVendaResource , VendaResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.itemVenda = $scope.itemVenda || {};
    
    $scope.vendasList = VendaResource.queryAll(function(items){
        $scope.vendasSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("vendasSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.itemVenda.vendas = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.itemVenda.vendas.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/ItemVendas/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ItemVendaResource.save($scope.itemVenda, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/ItemVendas");
    };
});