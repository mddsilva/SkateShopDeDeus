
angular.module('deusSkateShop').controller('NewFormaPagamentoController', function ($scope, $location, locationParser, FormaPagamentoResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.formaPagamento = $scope.formaPagamento || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/FormaPagamentos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        FormaPagamentoResource.save($scope.formaPagamento, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/FormaPagamentos");
    };
});