function LoginCtrl($scope, $rootScope,  $location,$window,UsersService) {
    $rootScope.bodylayout = 'login';

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
               $('#modal-slide-down').modal('hide');
               console.log("false");
           }else{
               //$window.location.href="http://localhost:8080/social/#/home"
              //$location.path("/home");
               $('#dialog').modal('show');
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