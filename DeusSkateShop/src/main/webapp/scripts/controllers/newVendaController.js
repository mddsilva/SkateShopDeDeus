
angular.module('deusSkateShop').controller('NewVendaController', function ($scope, $location, locationParser, VendaResource , ClienteResource, FormaPagamentoResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.venda = $scope.venda || {};
    
    $scope.clienteList = ClienteResource.queryAll(function(items){
        $scope.clienteSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("clienteSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.venda.cliente = {};
            $scope.venda.cliente.id = selection.value;
        }
    });
    
    $scope.formaPagamentoList = FormaPagamentoResource.queryAll(function(items){
        $scope.formaPagamentoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("formaPagamentoSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.venda.formaPagamento = {};
            $scope.venda.formaPagamento.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Vendas/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        VendaResource.save($scope.venda, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Vendas");
    };
});