function FriendsCtrl($scope, $location,FriendsService,ProfileService) {

    $scope.friendsTwitter={};
    $scope.friendsLinkedin={};
    $scope.friendsFacebook={};
    $scope.profileImage={};

    ProfileService.findProfileImageFacebook().success(function (response) {
        $scope.profileImage=response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends=response;
    });

    FriendsService.findFriendsTwitter().
        success(function (users) {
            $scope.friendsTwitter = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FriendsService.findFriendsFacebook().
        success(function (users) {
            $scope.friendsFacebook = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FriendsService.findFriendsLinkedin().
        success(function (users) {
            $scope.friendsLinkedin = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });



}