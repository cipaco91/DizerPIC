function SearchUsersCtrl($scope, $location, FriendsService, ProfileService, MenuService,$controller) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.byNameShow = false;
    $scope.buttonSearch=false;
    $scope.searchUserDTO={};
    $scope.usersShow=false;
    $scope.selectedItem.code='name';
    $scope.selectedItem.value='by Name';


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

    FriendsService.searchUsersDTO().success(function (response) {
        $scope.searchUserDTO = response;
    });

    $scope.search= function () {
        $scope.searchUserDTO.name=$scope.name;
        FriendsService.searchUsers($scope.searchUserDTO).success(function (response) {
            $scope.users=response;
            $scope.usersShow=true;
        });
    };
}