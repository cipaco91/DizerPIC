function FeedCtrl($scope, $location,FeedService,ProfileService,MenuService) {

    $scope.feedTwitter={};
    $scope.feedLinkedin={};
    $scope.feedFacebook={};

    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible=response;
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible=response;
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinVisible=response;
    });

    ProfileService.findProfileImageFacebook().success(function (response) {
        $scope.profileImage=response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends=response;
    });

    FeedService.findFeedTwitter().
        success(function (users) {
            $scope.feedTwitter = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FeedService.findFeedFacebook().
        success(function (users) {
            $scope.feedFacebook = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FeedService.findFeedLinkedin().
        success(function (users) {
            $scope.feedLinkedin = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });



}