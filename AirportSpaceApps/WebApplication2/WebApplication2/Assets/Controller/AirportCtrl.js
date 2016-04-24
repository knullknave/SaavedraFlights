var app = angular.module('mainApp');

app.controller('airportCtrl', ['$scope', '$location', '$cookies', '$route', '$timeout', '$rootScope', 'ReturnAirports', 'ReturnData', function ($scope, $location, $cookies, $route, $timeout, $rootScope, ReturnAirports, ReturnData)
{
    //$route.reload();

    var local_icons =
    {
        default_icon: {},
        plane_icon: {
            iconUrl: 'Resource/test2.png',
            iconSize: [45, 45],
            shadowSize: [50, 64],
            iconAnchor: [22, 94],
            shadowAnchor: [4, 62],
            popupAnchor: [-3, -76]
        }
    };

    angular.extend($scope,
    {
        icons: local_icons
    });

    $scope.windDir = function (dato)
    {
        var value = ((dato / 22.5) + 0.5);

        var valores = ["N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"];

        return valores[value % 16];
    };

    $scope.search = function()
    {
        $scope.markers = [];

        ReturnAirports.getAirports().then(function (data)
        {
            $scope.airports = data;

            $rootScope.city = $scope.center.city;

            for (var i = 0; i < $scope.airports.length; i++)
            {
                $rootScope.latitude = $scope.airports[i].latitud;
                $rootScope.longitude = $scope.airports[i].longitud;

                ReturnData.getData().then(function (data)
                {
                    $scope.listData = data;

                    $scope.markers.push({
                        lat: $rootScope.latitude,
                        lng: $rootScope.longitude,
                        message: "<dl><dt>Ciudad:</dt>" + "<dd>" + $rootScope.city + "</dd>" +
                                 "<dt>Summary:</dt>" + "<dd>" + $scope.listData.currently.summary + "</dd>" +
                                 "<dt>Temperature:</dt>" + "<dd>" + $scope.listData.currently.temperature + "º" + "</dd>" +
                                 "<dt>Wind Speed:</dt>" + "<dd>" + $scope.listData.currently.windSpeed + "m/s" + "</dd>" +
                                 "<dt>Visibility:</dt>" + "<dd>" + $scope.listData.currently.visibility + "km" + "</dd>" +
                                 "<dt>Precipitation Probability:</dt>" + "<dd>" + ($scope.listData.currently.precipProbability) * 100 + "%" + "</dd>" +
                                 "<dt>Cloud Cover:</dt>" + "<dd>" + ($scope.listData.currently.cloudCover) * 100 + "%" + "</dd></dl>",
                        focus: true,
                        draggable: false,
                        icon: local_icons.default_icon
                    })
                        
                });
            }
        });
    }

    angular.extend($scope,
    {
        center:
        {
            lat: 40.095,
            lng: -3.823,
            zoom: 4
        },
        defaults:
        {
            scrollWheelZoom: false
        }
    });
}]);