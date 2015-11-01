angular.module('deusSkateShop').factory('FormaPagamentoResource', function($resource){
    var resource = $resource('rest/formapagamentos/:FormaPagamentoId',{FormaPagamentoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});