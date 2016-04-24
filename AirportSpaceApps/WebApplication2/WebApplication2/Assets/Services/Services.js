var app = angular.module('mainApp');

app.factory('Logged', ['$cookies', function ($cookies)
{
    var log = {};

    log.getLog = function ()
    {
        if ($cookies.get('connected') == undefined)
            $cookies.put('connected', false);
        return $cookies.get('connected');
    }

    log.setLog = function (value)
    {
        $cookies.put('connected', value);
    }

    return log;
}]);

app.factory('Register', ['$http', function ($http)
{
    var log = {};

    log.reg = function(name, surname, email, pass, check)
    {

        $http({
            method: "GET",
            url: "http://10.10.11.48:9090/add?nombre=" + name + "&apellidos=" + surname + "&email=" + email + "&password=" + pass + "&notificaciones=" + check
        })
        .then(function mySucces(response)
        {
            alert(response.status);
        });
    }

    return log;
}]);

app.factory('CheckUsers', ['$http', function ($http)
{
    return {
        getData0: function ()
        {
            return $http.get('http://10.10.11.48:9090/usuarios');
        }
    }
}]);

app.factory('ReturnUsers', ['$http', 'CheckUsers', function ($http, CheckUsers)
{
    var data = {};

    CheckUsers.getData0().then(function (response)
    {
        data = response.data;
    });

    return {
        getData1: function ()
        {
            return data;
        }
    }
}]);

app.factory('GetAirports', ['$http', '$rootScope', function ($http, $rootScope)
{
    return {
        getData0: function ()
        {
            return $http.get('http://10.10.11.48:9090/get_aeropuerto?ciudad=' + $rootScope.city);
        }
    }
}]);

app.factory('ReturnAirports', ['$http', '$rootScope', '$q', 'GetAirports', function ($http, $rootScope, $q, GetAirports)
{
    var data = {};

    return {
        getAirports: function()
        {
            var deferred = $q.defer();

            GetAirports.getData0().then(function (response)
            {
                //console.log('lo tengo');
                data = response.data;
                deferred.resolve(data);

            });

            return deferred.promise;
        }     
    }
}]);

app.factory('GetData', ['$http', '$rootScope', function ($http, $rootScope)
{
    return {
        getData0: function ()
        {
            return $http.get('https://api.forecast.io/forecast/cd578856d111d2772e67f1b6a54b9892/' + $rootScope.latitude + ',' + $rootScope.longitude + '?currently&units=si');
        }
    }
}]);

app.factory('ReturnData', ['$http', 'GetData', '$q', function ($http, GetData, $q)
{
    var data = {};

    return {
        getData: function ()
        {
            var deferred = $q.defer();

            GetData.getData0().then(function (response)
            {
                data = response.data;
                deferred.resolve(data);

            });

            return deferred.promise;
        }
    }
}]);