function ProfileCtrl($scope, $location,ProfileService,MenuService) {

    $scope.profileImage={};
    $scope.userProfile={};
    $scope.photosProfile={};
    $scope.friends={};

    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible=response;
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible=response;
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinVisible=response;
    });

        ProfileService.findProfileImage().success(function (response) {
            $scope.profileImage=response;
        });

    ProfileService.profileSN().success(function (response) {
        $scope.userProfile=response;
    });

    ProfileService.photosProfile().success(function (response) {
        $scope.photosProfile=response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends=response;
    });



}