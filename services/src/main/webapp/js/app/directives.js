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
            $scope.choiceNbResults = [ 10, 50, 100, 1000, 10000 ];
            $scope.nbResults = 50
        }
    }
});