var app = angular.module('mainApp');

app.controller('loginCtrl', ['$scope', '$location', '$cookies', '$route', 'Logged', 'ReturnUsers', function ($scope, $location, $cookies, $route, Logged, ReturnUsers)
{
    $scope.loginFunc = function ()
    {
        $scope.list = ReturnUsers.getData1();
        $scope.correct = false;

        for (i = 0; i < $scope.list.length; i++)
        {
            if ($scope.list[i].email == $scope.user.email && $scope.list[i].password == $scope.user.password)
            {
                $scope.correct = true;
            }
        }

        if ($scope.correct == false)
        {
            $scope.user.password = "";
            alert("The user and password are incorrect");
        }
        else if ($scope.correct == true)
        {
            $cookies.put('email', $scope.user.email);
            $cookies.put('pass', $scope.user.password);

            Logged.setLog(true);

            $location.path('/airports');
            $route.reload();
        }    
    };
}]);