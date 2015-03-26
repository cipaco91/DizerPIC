function LoginCtrl($scope, $location,$window,UsersService) {
    //
    //$scope.login = function() {
    //    console.log("login");
    //    UsersService.login().success(function (response) {
    //        $window.location.href=response;
    //    });
    //}

    $scope.connectionFacebook = function() {
        console.log("redirect facebook");
        UsersService.login('facebook').success(function (response) {
            $window.location.href=response;
        });
    }

    $scope.connectionLinkedin = function() {
        console.log("redirect linkedin");
        UsersService.login('linkedin').success(function (response) {
            $window.location.href = response;
        });
    }
    $scope.connectionTwitter = function() {
        console.log("redirect twitter");
        UsersService.login('twitter').success(function (response) {
            $window.location.href = response;
        });
    }
}