function ProfileCtrl($scope, $location,ProfileService) {

    $scope.profileImage={};
    $scope.userProfile={};
    $scope.friends={};

        ProfileService.findProfileImageFacebook().success(function (response) {
            $scope.profileImage=response;
        });

    ProfileService.findProfileFacebook().success(function (response) {
        $scope.userProfile=response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends=response;
    });



}