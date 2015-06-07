function HomeCtrl($scope, $rootScope,$routeParams,$window,UsersService,MenuService,$controller,$location) {

    $rootScope.bodylayout = 'login2';
    $scope.facebookVisible=$routeParams.facebook;
    $scope.twitterVisible=$routeParams.twitter;
    $scope.linkedinVisible=$routeParams.linkedn;
    $scope.googleVisible=$routeParams.google;



    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible=response;
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible=response;
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinHomeVisible=response;
        if(  $scope.linkedinHomeVisible == 'true'){
             $scope.linkedinVisible=false;
        }
    });

    MenuService.isConnectGoogle().success(function (response) {
        $scope.googleVisible=response;

        if($scope.googleVisible=='true') {
            $window.location.href = "http://localhost:8080/social/#/profile";
        }
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