

angular.module('deusSkateShop').controller('EditFormaPagamentoController', function($scope, $routeParams, $location, FormaPagamentoResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.formaPagamento = new FormaPagamentoResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/FormaPagamentos");
        };
        FormaPagamentoResource.get({FormaPagamentoId:$routeParams.FormaPagamentoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.formaPagamento);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.formaPagamento.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/FormaPagamentos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/FormaPagamentos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.formaPagamento.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});