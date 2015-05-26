function ImagesPageCtrl($scope, $rootScope,$routeParams, $window, ProfileService, MenuService) {

    $rootScope.bodylayout = 'bodyImagesPage';

    $scope.direction = 'left';
    $scope.currentIndex = 0;
    $scope.slides = {};

    ProfileService.profileSN().success(function (response) {
        $scope.userProfile = response;
    });

    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible = response;
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible = response;
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinVisible = response;
    });

    $scope.isCurrentSlideIndex = function (index) {
        return $scope.currentIndex === index;
    };

    $scope.prevSlide = function () {
        $scope.direction = 'left';
        $scope.currentIndex = ($scope.currentIndex < $scope.slides.length - 1) ? ++$scope.currentIndex : 0;
    };

    $scope.nextSlide = function () {
        $scope.direction = 'right';
        $scope.currentIndex = ($scope.currentIndex > 0) ? --$scope.currentIndex : $scope.slides.length - 1;
    };

    if ($routeParams.albumId == 0) {
        ProfileService.photosProfile().success(function (response) {
            $scope.slides = response;
        });
    } else {
        ProfileService.photosFromAlbum($routeParams.albumId).success(function (response) {
            $scope.slides = response;
        });
    }

}