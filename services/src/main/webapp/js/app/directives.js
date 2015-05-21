app.directive('appShowresults', function () {
    return {
        restrict: 'E',
        replace: true,
        templateUrl: "partials/common/friendDirective.html",
        link: function(scope, element, attrs) {
            attrs.$observe('friend', function(friend) {
                scope.friend = friend;
            });
        },
        controller: function ($scope) {
            $scope.screenName=$scope.friend.name;
            $scope.profileImageUrl = $scope.friend.profileImageUrl;
            $scope.profileURL=$scope.friend.profileURL;
            $scope.socialNetworkType=$scope.friend.socialNetworkType;
            $scope.socialNetworkTypePicture=$scope.friend.socialNetworkTypePicture;
        }
    }
});