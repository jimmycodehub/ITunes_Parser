angular.module('DistSystems.footer',[]).
directive('footer', function(){
    return{
        restrict:'AE',
        scope : {

        },
        controller: function ($scope) {
            $scope.home="HOME!!!";
        },
        templateUrl:'resources/js/directives/FooterDirective/footer.html',
        replace:true
    }
});