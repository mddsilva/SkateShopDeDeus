

angular.module('deusSkateShop').controller('EditVendaController', function($scope, $routeParams, $location, VendaResource , ClienteResource, FormaPagamentoResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.venda = new VendaResource(self.original);
            ClienteResource.queryAll(function(items) {
                $scope.clienteSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.venda.cliente && item.id == $scope.venda.cliente.id) {
                        $scope.clienteSelection = labelObject;
                        $scope.venda.cliente = wrappedObject;
                        self.original.cliente = $scope.venda.cliente;
                    }
                    return labelObject;
                });
            });
            FormaPagamentoResource.queryAll(function(items) {
                $scope.formaPagamentoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.venda.formaPagamento && item.id == $scope.venda.formaPagamento.id) {
                        $scope.formaPagamentoSelection = labelObject;
                        $scope.venda.formaPagamento = wrappedObject;
                        self.original.formaPagamento = $scope.venda.formaPagamento;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Vendas");
        };
        VendaResource.get({VendaId:$routeParams.VendaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.venda);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.venda.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Vendas");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Vendas");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.venda.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("clienteSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.venda.cliente = {};
            $scope.venda.cliente.id = selection.value;
        }
    });
    $scope.$watch("formaPagamentoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.venda.formaPagamento = {};
            $scope.venda.formaPagamento.id = selection.value;
        }
    });
    
    $scope.get();
});