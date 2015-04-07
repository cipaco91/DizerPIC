app.directive('appShowresults', function () {
    return {
        scope: {
            screenName: '='
        },
        restrict: 'E',
        replace: true,
        templateUrl: "partials/common/friendDirective.html",
        controller: function ($scope) {
            $scope.choiceNbResults = [ 10, 50, 100, 1000, 10000 ];
            $scope.nbResults = 50
        }
    }
});