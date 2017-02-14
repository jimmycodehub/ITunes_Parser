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