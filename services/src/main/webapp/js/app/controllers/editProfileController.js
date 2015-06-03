function EditProfileCtrl($scope, $rootScope,$routeParams,$window,ProfileService,MenuService,$controller) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.wizzardDTO={};

    ProfileService.getWizzardDTO().success(function (response) {
        $scope.wizzardDTO = response;
        $scope.firstName= $scope.wizzardDTO.firstName;
        $scope.lastName= $scope.wizzardDTO.lastName;
        $scope.email= $scope.wizzardDTO.email;
        $scope.profileImage= $scope.wizzardDTO.profileImage;
        $scope.profileAbout= $scope.wizzardDTO.profileAbout;
        $scope.profileFriend= $scope.wizzardDTO.profileFriend;
        $scope.profileName= $scope.wizzardDTO.profileName;
        $scope.profileCover= $scope.wizzardDTO.profileCover;

        $scope.yesFacebook= $scope.wizzardDTO.isFacebook;
        $scope.noFacebook= !$scope.wizzardDTO.isFacebook;
        $scope.yesTwitter= $scope.wizzardDTO.isTwitter;
        $scope.noTwitter= !$scope.wizzardDTO.isTwitter;
        $scope.yesLinkedin= $scope.wizzardDTO.isLinkedin;
        $scope.noLinkedin= !$scope.wizzardDTO.isLinkedin;
        $scope.yesGoogle= $scope.wizzardDTO.isGoogle;
        $scope.noGoogle= !$scope.wizzardDTO.isGoogle;
    });

    ProfileService.finishWizzardProfile($scope.wizzardDTO).success(function (response) {
        $scope.wizzardDTO = response;
    });

    $scope.save = function () {
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

        console.log($scope.firstName);

        ProfileService.finishWizzardProfile($scope.wizzardDTO).success(function (response) {
            $scope.wizzardDTO = response;
        });
    };


}