var app = angular.module('mainApp');

app.config(function ($routeProvider)
{
    $routeProvider
    .when('/',
    {
        resolve:
        {
            "check": function ()
            {

            }
        },
        templateUrl: 'Template/Dashboard.html',
        controller: 'dashboardCtrl'
    })
    .when('/news',
    {
        resolve:
        {
            "check": function ()
            {
                
            }
        },
        templateUrl: 'Template/News.html',
        controller: 'loginCtrl'
    })
    .when('/login',
    {
        resolve:
        {
            "check": function ()
            {

            }
        },
        templateUrl: 'Template/Login.html',
        controller: 'loginCtrl'
    })
    .when('/register',
    {
        resolve:
        {
            "check": function ()
            {

            }
        },
        templateUrl: 'Template/Register.html',
        controller: 'registerCtrl'
    })
    .when('/airports',
    {
        resolve:
        {
            "check": function ($location, Logged)
            {
                if(Logged.getLog() == 'false')
                   $location.path('/')
            }
        },
        templateUrl: 'Template/Airport.html',
        controller: 'airportCtrl'
    })
    .when('/favorites',
    {
        resolve:
        {
            "check": function ($location, Logged)
            {
                if (Logged.getLog() == 'false')
                    $location.path('/')
            }
        },
        templateUrl: 'Template/Favorites.html'
    })
    .otherwise(
    {
        redirectTo: '/'
    });
});