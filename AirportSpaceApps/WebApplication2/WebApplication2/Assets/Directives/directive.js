var app = angular.module('mainApp');

app.directive('ngNavbar', function ()
{
    return{
        restrict: 'A',
        templateUrl: 'Directives/NavBar.html',
        controller: ['$scope', '$location', '$cookies', '$route', 'Logged', function ($scope, $location, $cookies, $route, Logged)
        {
            $scope.connected = Logged.getLog();

            switch ($location.path())
            {
                case "/":
                    $scope.actived = "home";
                    break;
                case "/login":
                    $scope.actived = "login";
                    break;
                case "/register":
                    $scope.actived = "register";
                    break;
                case "/airports":
                    $scope.actived = "airports";
                    break;
            }

            $scope.login = function()
            {
                $location.path("/login");
            }

            $scope.register = function ()
            {
                $location.path("/register");
            }

            $scope.logoff = function ()
            {
                Logged.setLog(false);
                $location.path("/");
                $route.reload();
            }
        }]
    }
});