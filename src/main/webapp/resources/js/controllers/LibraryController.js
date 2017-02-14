angular.module('DistSystems.LibraryController', []).
    controller('LibraryController', function ($scope, $state, $http) {
    $scope.loginUser = {};


    $scope.trackList = "Here is a list of all your tracks!!";


    $http.get('http://localhost:8080/rest/api/getAllTracks')
        .then(function (response) {
               $scope.tracks = response.data;
                console.log($scope.tracks, "ITS IN");

        })
        .catch(function (error) {
            alert("An error occured")
        });
});