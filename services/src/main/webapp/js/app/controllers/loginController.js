function LoginCtrl($scope, $rootScope, $location, $window, UsersService,ProfileService) {
    $rootScope.bodylayout = 'login';

    $scope.messageFlag = false;
    $scope.messageFlagSignUp = false;

    $scope.loginForm = true;
    $scope.signUpForm = false;
    $scope.messageSign = "Sign In";

    ProfileService.isLoginActive().success(function (response) {
        if(response == "false") {
            $location.path("login");
        }else{
            $location.path("profile");
        }
    });

    $scope.connectionGoogle = function () {
        console.log("redirect google");
        UsersService.login('google').success(function (response) {
            $window.location.href = response;
        });
    };

    $scope.login = function () {
        console.log("redirect facebook");
        UsersService.login($scope.username, $scope.password).success(function (response) {
            if (response == 'notOK') {
                console.log("false");
                $scope.messageFlag = true;
            } else if (response == 'okLoginSocialNetwork') {
                $window.location.href = "http://localhost:8080/social/#/home";
            } else if (response == 'okProfile') {
                $window.location.href = "http://localhost:8080/social/#/profile";
            } else {
                $window.location.href = "http://localhost:8080/social/#/settings";
            }
        });
    };

    $scope.signUp = function () {
        $scope.loginForm = false;
        $scope.signUpForm = true;
        $scope.messageSign = "Sign Up";
    };

    $scope.signUp2 = function () {
        if ($scope.passwordSignUp != $scope.rePasswordSignUp) {
            $scope.messageFlagSignUp = true;
        } else {
            UsersService.signUp($scope.usernameSignUp, $scope.passwordSignUp,$scope.firstName,$scope.lastName).success(function (response) {
                if (response == 'okSignUp') {
                    console.log("okSignUp");
                    $scope.loginForm = true;
                    $scope.signUpForm = false;
                    $scope.messageSign = "Sign In";
                }
            });
        }
    };

    $scope.connectionLinkedin = function () {
        console.log("redirect linkedin");
        UsersService.login('linkedin').success(function (response) {
            $window.location.href = response;
        });
    };
    $scope.connectionTwitter = function () {
        console.log("redirect twitter");
        UsersService.login('twitter').success(function (response) {
            $window.location.href = response;
        });
    };
}