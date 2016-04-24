var app = angular.module('mainApp');

app.controller('registerCtrl', ['$scope', '$location', '$cookies', 'Register', function ($scope, $location, $cookies, Register) {
    $scope.loginFunc = function ()
    {
        
        if ($scope.user.checked == true)
            $scope.user.remember = 1;
        else
            $scope.user.remember = 0;
            
        Register.reg($scope.user.name, $scope.user.surname, $scope.user.email, $scope.user.password, $scope.user.remember);

        $location.path('/');

        //alert("WELCOME!");
    };
}]);