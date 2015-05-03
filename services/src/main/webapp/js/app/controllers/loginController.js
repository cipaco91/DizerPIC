function LoginCtrl($scope, $rootScope,  $location,$window,UsersService) {
    $rootScope.bodylayout = 'login';

    $scope.messageFlag=false;

    $scope.connectionGoogle = function() {
        console.log("redirect google");
        UsersService.login('google').success(function (response) {
            $window.location.href=response;
        });
    }

    $scope.login = function() {
        console.log("redirect facebook");
        UsersService.login($scope.username,$scope.password).success(function (response) {
           if(response == 'false'){
               console.log("false");
               $scope.messageFlag=true;
           }else{
               $window.location.href="http://localhost:8080/social/#/settings";
           }
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