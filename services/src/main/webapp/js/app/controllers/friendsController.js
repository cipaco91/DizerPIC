function FriendsCtrl($scope, $location,FriendsService) {

    $scope.friendsTwitter={};
    $scope.friendsLinkedin={};
    $scope.friendsFacebook={};

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