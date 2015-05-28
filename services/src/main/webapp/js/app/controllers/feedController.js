function FeedCtrl($scope, $location, FeedService, ProfileService, MenuService, $controller, $sce) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.feedTwitter = {};
    $scope.feedLinkedin = {};
    $scope.feedFacebook = {};
    $scope.date = new Date();
    $scope.postVisible = false;
    $scope.postText = "";

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

    $scope.changePost = function () {
        if ($scope.postText != undefined && $scope.postText != "") {
            $scope.postVisible = true;
        } else {
            $scope.postVisible = false;
        }
    };

    $scope.addComment = function (feedId) {
        console.log(feedId);
        FeedService.addComment(feedId, ':)');
    };

    $scope.trustSrc = function (src) {
        return $sce.trustAsResourceUrl(src);
    };
    $scope.clickFacebook = function (src) {
        if ($scope.classFacebook == "classFacebook") {
            $scope.classFacebook = "";
        }
        $scope.classFacebook = "classFacebook";
    };

    $scope.clickTwitter = function (src) {
        if ($scope.classTwitter == "classTwitter") {
            $scope.classTwitter = "";
        }
        $scope.classTwitter = "classTwitter";
    };

    $scope.clickLinkedin = function (src) {
        if ($scope.classLinkedin == "classLinkedin") {
            $scope.classLinkedin = "";
        }
        $scope.classLinkedin = "classLinkedin";
    };

    $scope.clickGoogle = function (src) {
        if ($scope.classGoogle == "classGoogle") {
            $scope.classGoogle = "";
        }
        $scope.classGoogle = "classGoogle";
    };

    $scope.postAction = function (src) {
        $scope.classGoogle = "";
        $scope.classLinkedin = "";
        $scope.classTwitter = "";
        $scope.classFacebook = "";
        $scope.postText="";
    };

    //FeedService.findFeedLinkedin().
    //    success(function (users) {
    //        $scope.feedLinkedin = users;
    //    })
    //    .error(function (resp) {
    //        console.log("Error with FriendsService.findFriendsTwitter" + resp);
    //    });


}