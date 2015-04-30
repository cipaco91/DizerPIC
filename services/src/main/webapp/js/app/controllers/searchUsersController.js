function SearchUsersCtrl($scope, $location, FriendsService, ProfileService, MenuService,$controller) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.byNameShow = false;
    $scope.buttonSearch=false;

    $scope.codes = [{code: 'all', value: "All"},{code: 'name', value: "by Name"}];

    $scope.update = function () {
        $scope.searchCode = $scope.selectedItem.code;
        if ($scope.searchCode == 'all') {
            $scope.byNameShow = false;
            $scope.buttonSearch=false;
        } else if ($scope.searchCode == 'name') {
            $scope.byNameShow = true;
            $scope.buttonSearch=true;
        }
    };

    $scope.search= function () {
        console.log($scope.name);
    };
}