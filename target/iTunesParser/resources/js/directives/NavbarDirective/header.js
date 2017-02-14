angular.module('DistSystems.header',[]).
directive('header', function(){
    return{
        restrict:'AE',
        scope : {

        },
        controller: function ($scope) {
            $scope.home="HOME!!!";
        },
        templateUrl:'resources/js/directives/NavbarDirective/header.html',
        replace:true
    }
});