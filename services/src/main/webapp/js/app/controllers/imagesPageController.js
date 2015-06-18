function ImagesPageCtrl($scope, $rootScope,$routeParams, $window, ProfileService, MenuService,FeedService) {

    $rootScope.bodylayout = 'bodyImagesPage';

    $scope.direction = 'left';
    $scope.currentIndex = 0;
    $scope.slides = {};

    //$scope.setCurrentSlideIndex = function (index) {
    //    $scope.direction = (index > $scope.currentIndex) ? 'left' : 'right';
    //    $scope.currentIndex = index;
    //};

    $scope.isCurrentSlideIndex = function (index) {
        return $scope.currentIndex === index;
    };

    $scope.prevSlide = function (slide) {
        $scope.direction = 'left';
        //FeedService.comments(slide.id).success(function (response) {
        //    $scope.commentsFeeds = response;
        //});
        $scope.currentIndex = ($scope.currentIndex < $scope.slides.length - 1) ? ++$scope.currentIndex : 0;
    };

    $scope.nextSlide = function (slide) {
        $scope.direction = 'right';
        //FeedService.comments(slide.id).success(function (response) {
        //    $scope.commentsFeeds = response;
        //});
        $scope.currentIndex = ($scope.currentIndex > 0) ? --$scope.currentIndex : $scope.slides.length - 1;
    };

    if ($routeParams.albumId == 0) {
        ProfileService.imagesPageFacebook().success(function (response) {
            $scope.slides = response;
        });
    } else {
        ProfileService.photosFromAlbum($routeParams.albumId).success(function (response) {
            $scope.slides = response;
        });
    }

    $scope.addCommentFacebook = function (slide) {
        console.log(slide.id);
        //FeedService.addComment(slide.id, $scope.postText);
        ProfileService.getCommentFeed(slide.postText).success(function (response) {
            $scope.commentFeed = response;
            if(slide.commentFeeds == null) slide.commentFeeds={};
            slide.commentFeeds.push($scope.commentFeed);
            slide.postText="";
        });
    };

    $scope.addLike = function (feed) {
        console.log(feed.id);
        FeedService.addLike(feed.photo.id);
        feed.likeShow = false;
        feed.likesCount = feed.likesCount + 1;
    };

    $scope.unlike = function (feed) {
        console.log(feed.id);
        FeedService.unlike(feed.photo.id);
        feed.likeShow = true;
        feed.likesCount = feed.likesCount - 1;
    };

}