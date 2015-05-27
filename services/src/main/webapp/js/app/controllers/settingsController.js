function SettingsCtrl($scope, $location, $window, ProfileService, $controller) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.step = 1;
    $scope.step1 = true;
    $scope.step2 = false;
    $scope.step3 = false;
    $scope.step4 = false;

    $scope.first = 'active';
    $scope.social = 'inactive';
    $scope.profile = 'inactive';

    $scope.wizzardDTO = {};

    ProfileService.getWizzardDTO().success(function (response) {
        $scope.wizzardDTO = response;
    });

    $scope.setStep = function (step) {
        if (step == 1) {
            $scope.step1 = false;
            $scope.step2 = true;
            $scope.step3 = false;
            $scope.first = 'active';
            $scope.social = 'active';
            $scope.profile = 'inactive';
        } else if (step == 2) {
            $scope.step1 = false;
            $scope.step2 = false;
            $scope.step3 = true;
            $scope.first = 'active';
            $scope.social = 'active';
            $scope.profile = 'active';
        }
    };

    $scope.finish = function () {
        $scope.wizzardDTO.firstName = $scope.firstName;
        $scope.wizzardDTO.lastName = $scope.lastName;
        $scope.wizzardDTO.email = $scope.email;
        $scope.wizzardDTO.profileImage = $scope.profileImage;
        $scope.wizzardDTO.profileAbout = $scope.profileAbout;
        $scope.wizzardDTO.profileFriend = $scope.profileFriend;
        $scope.wizzardDTO.profileName = $scope.profileName;
        $scope.wizzardDTO.profileCover = $scope.profileCover;

        if (angular.equals($scope.yesTwitter, true)) {
            $scope.wizzardDTO.isTwitter = true;
        } else if (angular.equals($scope.noTwitter, true)) {
            $scope.wizzardDTO.isTwitter = false;
        }

        if (angular.equals($scope.yesFacebook, true)) {
            $scope.wizzardDTO.isFacebook = true;
        } else if (angular.equals($scope.noFacebook, true)) {
            $scope.wizzardDTO.isFacebook = false;
        }

        if (angular.equals($scope.yesLinkedin, true)) {
            $scope.wizzardDTO.isLinkedin = true;
        } else if (angular.equals($scope.noLinkedin, true)) {
            $scope.wizzardDTO.isLinkedin = false;
        }

        if (angular.equals($scope.yesGoogle, true)) {
            $scope.wizzardDTO.isGoogle = true;
        } else if (angular.equals($scope.noGoogle, true)) {
            $scope.wizzardDTO.isGoogle = false;
        }

        $scope.wizzardDTO.username = $scope.username;
        $scope.wizzardDTO.password = $scope.password;
        $scope.wizzardDTO.repassword = $scope.repassword;
        console.log($scope.firstName);

        ProfileService.finishWizzardProfile($scope.wizzardDTO).success(function (response) {
            $scope.wizzardDTO = response;
        });

        $location.path("/home");
        //
        //$location.path("/home/" + $scope.wizzardDTO.isFacebook + "/" +
        //$scope.wizzardDTO.isTwitter + "/" + $scope.wizzardDTO.isLinkedin + "/" +
        //$scope.wizzardDTO.isGoogle);
    };

    $scope.setStepBack = function (step) {
        if (step == 2) {
            $scope.step1 = true;
            $scope.step2 = false;
            $scope.step3 = false;
            $scope.first = 'active';
            $scope.social = 'inactive';
            $scope.profile = 'inactive';
        } else if (step == 3) {
            $scope.step1 = false;
            $scope.step2 = true;
            $scope.step3 = false;
            $scope.first = 'active';
            $scope.social = 'active';
            $scope.profile = 'inactive';
        } else {
            $scope.step1 = false;
            $scope.step2 = false;
            $scope.step3 = true;
            $scope.first = 'active';
            $scope.social = 'active';
            $scope.profile = 'inactive';

        }
    };
}