function FeedCtrl($scope, $location,FeedService,ProfileService,MenuService,$controller,$sce) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.feedTwitter={};
    $scope.feedLinkedin={};
    $scope.feedFacebook={};
    $scope.date = new Date();

    //FeedService.findFeedTwitter().
    //    success(function (users) {
    //        $scope.feedTwitter = users;
    //    })
    //    .error(function (resp) {
    //        console.log("Error with FriendsService.findFriendsTwitter" + resp);
    //    });

    FeedService.findFeedFacebook().
        success(function (users) {
            $scope.feedFacebook = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.feedFacebook" + resp);
        });

    $scope.addLike = function (feedId) {
        console.log(feedId);
        FeedService.addLike(feedId);
    };

    $scope.addComment = function (feedId) {
        console.log(feedId);
        FeedService.addComment(feedId,':)');
    };

    $scope.trustSrc = function(src) {
        return $sce.trustAsResourceUrl(src);
    }

    //FeedService.findFeedLinkedin().
    //    success(function (users) {
    //        $scope.feedLinkedin = users;
    //    })
    //    .error(function (resp) {
    //        console.log("Error with FriendsService.findFriendsTwitter" + resp);
    //    });



}