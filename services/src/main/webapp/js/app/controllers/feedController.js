function FeedCtrl($scope, $location, FeedService, ProfileService, $controller, $sce) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.feedTwitter = {};
    $scope.feedLinkedin = {};
    $scope.feedFacebook = {};
    $scope.date = new Date();
    $scope.postVisible = false;
    $scope.postText = "";
    $scope.likeShow = true;

    FeedService.findFeedFacebook().
        success(function (users) {
            $scope.feedFacebook = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.feedFacebook" + resp);
        });

    $scope.addLike = function (feed) {
        console.log(feed.id);
        FeedService.addLike(feed.id);
        $scope.likeShow = false;
        feed.likesCount = feed.likesCount + 1;
    };

    $scope.unlike = function (feed) {
        console.log(feed.id);
        FeedService.unlike(feed.id);
        $scope.likeShow = true;
        feed.likesCount = feed.likesCount - 1;
    };

    $scope.changePost = function () {
        if ($scope.postText != undefined && $scope.postText != "") {
            $scope.postVisible = true;
        } else {
            $scope.postVisible = false;
        }
    };

    $scope.addComment = function (feed) {
        console.log(feed.id);
        //FeedService.addComment(feed.id, $scope.postText);
        feed.commentsCount = feed.commentsCount + 1;
        ProfileService.getCommentFeed().success(function (response) {
            $scope.commentFeed = response;
            feed.commentsFeeds.push($scope.commentFeed);
        });
    };

    $scope.trustSrc = function (src) {
        return $sce.trustAsResourceUrl(src);
    };
    $scope.clickFacebook = function (src) {
        if ($scope.classFacebook == "classFacebook") {
            $scope.classFacebook = "";
        } else {
            $scope.classFacebook = "classFacebook";
        }
    };

    $scope.clickTwitter = function (src) {
        if ($scope.classTwitter == "classTwitter") {
            $scope.classTwitter = "";
        } else {
            $scope.classTwitter = "classTwitter";
        }
    };

    $scope.clickLinkedin = function (src) {
        if ($scope.classLinkedin == "classLinkedin") {
            $scope.classLinkedin = "";
        } else {
            $scope.classLinkedin = "classLinkedin";
        }
    };

    $scope.clickGoogle = function (src) {
        if ($scope.classGoogle == "classGoogle") {
            $scope.classGoogle = "";
        } else {
            $scope.classGoogle = "classGoogle";
        }
    };

    $scope.postAction = function (src) {
        $scope.facebookFlag = $scope.classFacebook == "classFacebook";
        $scope.twitterFlag = $scope.classTwitter == "classTwitter";
        $scope.linkedinFlag = $scope.classLinkedin == "classLinkedin";
        $scope.googleFlag = $scope.classGoogle == "classGoogle";

        FeedService.postSocialNetwork($scope.facebookFlag, $scope.twitterFlag, $scope.linkedinFlag, $scope.googleFlag,
            $scope.postText).success(function (feed) {
                $scope.classGoogle = "";
                $scope.classLinkedin = "";
                $scope.classTwitter = "";
                $scope.classFacebook = "";
                $scope.postText = "";

                $scope.feedFacebook.splice(0, 0, feed);
            })
            .error(function (resp) {
                console.log("Error with FeedService.postSocialNetwork" + resp);
            });
        ;


    };

    //FeedService.findFeedLinkedin().
    //    success(function (users) {
    //        $scope.feedLinkedin = users;
    //    })
    //    .error(function (resp) {
    //        console.log("Error with FriendsService.findFriendsTwitter" + resp);
    //    });


}