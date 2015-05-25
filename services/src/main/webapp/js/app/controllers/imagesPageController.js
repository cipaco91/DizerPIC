function ImagesPageCtrl($scope, $rootScope,$window,ProfileService) {

    $rootScope.bodylayout = 'bodyImagesPage';

    $scope.direction = 'left';
    $scope.currentIndex = 0;
    $scope.slides={};

    //$scope.setCurrentSlideIndex = function (index) {
    //    $scope.direction = (index > $scope.currentIndex) ? 'left' : 'right';
    //    $scope.currentIndex = index;
    //};

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

    ProfileService.photosProfile().success(function (response) {
        $scope.slides = response;
    });

}