function LoginCtrl($scope, $location,$window,UsersService) {
    $scope.showUser = false;
    $scope.showMenu = false;

    $scope.connectionGoogle = function() {
        console.log("redirect google");
        UsersService.login('google').success(function (response) {
            $window.location.href=response;
        });
    }

    $scope.connectionFacebook = function() {
        console.log("redirect facebook");
        UsersService.login('facebook').success(function (response) {
            $scope.showUser = true;
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