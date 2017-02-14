'use strict';

angular.module('DistSystems.LoginController', []).
controller('LoginController', function($scope, $state, $http) {
    $scope.user = {};

    $scope.loginUser = function () {

        $http.post('http://localhost:8080/rest/api/getUser', JSON.stringify($scope.user))
            .then(function (response) {
                console.log(response.data);
                $state.go("library");

            });
    }
});