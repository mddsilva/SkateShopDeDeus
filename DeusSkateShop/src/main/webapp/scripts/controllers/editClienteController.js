

angular.module('deusSkateShop').controller('EditClienteController', function($scope, $routeParams, $location, ClienteResource , UsuarioResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.cliente = new ClienteResource(self.original);
            UsuarioResource.queryAll(function(items) {
                $scope.usuarioSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.cliente.usuario && item.id == $scope.cliente.usuario.id) {
                        $scope.usuarioSelection = labelObject;
                        $scope.cliente.usuario = wrappedObject;
                        self.original.usuario = $scope.cliente.usuario;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Clientes");
        };
        ClienteResource.get({ClienteId:$routeParams.ClienteId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.cliente);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.cliente.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Clientes");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Clientes");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.cliente.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("usuarioSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.cliente.usuario = {};
            $scope.cliente.usuario.id = selection.value;
        }
    });
    
    $scope.get();
});