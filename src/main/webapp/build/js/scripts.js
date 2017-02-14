angular.module('DistSystems', [
    'ui.router',
    'DistSystems.LibraryController',
    'DistSystems.LoginController'
]).
config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/library');

    $stateProvider
        .state('library',{
            url:'/library',
            templateUrl:'resources/js/views/library.html',
            controller:'LibraryController'
        })
        .state('login',{
            url:'/login',
            templateUrl:'resources/js/views/login.html',
            controller:'LoginController'
        })
        .state('register',{
            url:'/register',
            templateUrl:'resources/js/views/register.html'
            // controller:'LoginController'
        });
});
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
$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});

$('.error-page').hide(0);

$('.login-button , .no-access').click(function(){
    $('.login').slideUp(500);
    $('.error-page').slideDown(1000);
});

$('.try-again').click(function(){
    $('.error-page').hide(0);
    $('.login').slideDown(1000);
});