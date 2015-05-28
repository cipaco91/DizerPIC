function HomeCtrl($scope, $rootScope,$routeParams,$window,UsersService,MenuService,$controller) {

    $rootScope.bodylayout = 'login2';
    $scope.facebookVisible=$routeParams.facebook;
    $scope.twitterVisible=$routeParams.twitter;
    $scope.linkedinVisible=$routeParams.linkedn;
    $scope.googleVisible=$routeParams.google;

    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible=response;
        //if($scope.facebookVisible=='true'&&$scope.twitterVisible=='true'&&$scope.linkedinVisible=='true'
        //    &&$scope.googleVisible=='true'){
        //    UsersService.setLoginActive().success(function (response) {
        //        $window.location.href="http://localhost:8080/social/#/profile";
        //    });
        //}
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible=response;
        //if( $scope.facebookVisible=='true'&&$scope.twitterVisible=='true'&&$scope.linkedinVisible=='true'
        //    &&$scope.googleVisible=='true'){
        //    UsersService.setLoginActive().success(function (response) {
        //        $window.location.href="http://localhost:8080/social/#/profile";
        //    });
        //}
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinVisible=response;
        //if( $scope.facebookVisible=='true'&&$scope.twitterVisible=='true'&&$scope.linkedinVisible=='true'
        //    &&$scope.googleVisible=='true'){
        //    UsersService.setLoginActive().success(function (response) {
        //        $window.location.href="http://localhost:8080/social/#/profile";
        //    });
        //}
    });

    MenuService.isConnectGoogle().success(function (response) {
        $scope.googleVisible=response;
        //if($scope.facebookVisible=='true'&&$scope.twitterVisible=='true'&&$scope.linkedinVisible=='true'
        //    &&$scope.googleVisible=='true'){
        //    UsersService.setLoginActive().success(function (response) {
        //        $window.location.href="http://localhost:8080/social/#/profile";
        //    });
        //}
        //if($scope.googleVisible2=='true') {
        //    $window.location.href = "http://localhost:8080/social/#/profile";
        //}
    });

    $scope.connectionFacebook = function() {
        console.log("redirect facebook");
        UsersService.loginSocialNetwork('facebook').success(function (response) {
            $window.location.href=response;
        });
    };

    $scope.connectionGoogle = function() {
        console.log("redirect google");
        UsersService.loginSocialNetwork('google').success(function (response) {
            $window.location.href=response;
        });
    };

    $scope.connectionLinkedin = function() {
        console.log("redirect linkedin");
        UsersService.loginSocialNetwork('linkedin').success(function (response) {
            $window.location.href = response;
        });
    };
    $scope.connectionTwitter = function() {
        console.log("redirect twitter");
        UsersService.loginSocialNetwork('twitter').success(function (response) {
            $window.location.href = response;
        });
    };
}