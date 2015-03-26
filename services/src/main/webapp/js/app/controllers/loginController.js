function LoginCtrl($scope, $location,$window,UsersService) {

    $scope.login = function() {
        console.log("login");

        UsersService.login().success(function (response) {
            $window.location.href=response;
        });
    }

    $scope.connectionFacebook = function() {
        console.log("redirect");
        UsersService.loginFacebook().then(function (response) {
            $location.path('siteVisitList');
        }, function (err) {
            console.log("");
        });
        //$window.location.href='https://www.facebook.com/v1.0/dialog/oauth?client_id=631469100286834&response_type=code&_csrf=cc8c4e4e-813c-4527-8e59-f8cef55c7df0&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fsocial&scope=publish_stream%2Cuser_photos%2Coffline_access&state=820b1e7c-9f43-476c-a9c8-8a725b7b5e05';
    }

    $scope.connectionLinkedin = function() {
        console.log("redirect");
        $window.location.href='https://www.linkedin.com/uas/oauth2/authorization?client_id=754fvz2njoxqfm&response_type=code&_csrf=404718ce-4ad9-4cfb-a52d-ec40a14aec93&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fsocial&scope=r_basicprofile+r_emailaddress+r_network+r_fullprofile+rw_nus&state=d1cbf845-3300-427c-a87f-8d719a3b0498';
    }

    $scope.connectionTwitter = function() {
        console.log("redirect");
        $window.location.href='https://www.linkedin.com/uas/oauth2/authorization?client_id=754fvz2njoxqfm&response_type=code&_csrf=404718ce-4ad9-4cfb-a52d-ec40a14aec93&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fsocial&scope=r_basicprofile+r_emailaddress+r_network+r_fullprofile+rw_nus&state=d1cbf845-3300-427c-a87f-8d719a3b0498';
    }
}