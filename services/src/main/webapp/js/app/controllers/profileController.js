function ProfileCtrl($scope, $rootScope, $location, $window, ProfileService, MenuService, FriendsService, UsersService) {

    $rootScope.bodylayout = 'login2';
    $scope.profileImage = {};
    $scope.userProfile = {};
    $scope.photosProfile = {};
    $scope.photosAlbums = {};
    $scope.friends = {};
    $scope.albums = {};
    $scope.firstTab = true;
    $scope.secondTab = false;
    $scope.isLoginActive = false;

    ProfileService.isLoginActive().success(function (response) {
        if (response == "false") {
            $scope.isLoginActive = false;
            $location.path("/login");
        }else{
            $scope.isLoginActive = true;
        }
    });

        MenuService.isConnectFacebook().success(function (response) {
            $scope.facebookVisible = response;
            //if ($scope.facebookVisible == 'false' && $scope.twitterVisible == 'false' && $scope.linkedinVisible == 'false') {
            //    $location.path("/home");
            //}
        });

        MenuService.isConnectTwittter().success(function (response) {
            $scope.twitterVisible = response;
            //if ($scope.facebookVisible == 'false' && $scope.twitterVisible == 'false' && $scope.linkedinVisible == 'false') {
            //    $location.path("/home");
            //}
        });

        MenuService.isConnectLinkedin().success(function (response) {
            $scope.linkedinVisible = response;
            //if ($scope.facebookVisible == 'false' && $scope.twitterVisible == 'false' && $scope.linkedinVisible == 'false') {
            //    $location.path("/home");
            //}
        });

        ProfileService.findProfileImage().success(function (response) {
            $scope.profileImage = response;
        });

        ProfileService.profileSN().success(function (response) {
            $scope.userProfile = response;
        });

        ProfileService.photosProfile().success(function (response) {
            $scope.photosProfile = response;
            $scope.photosAlbums = response;
        });

        ProfileService.friendsProfile().success(function (response) {
            $scope.friends = response;
        });

        FriendsService.albumsProfile().success(function (response) {
            $scope.albums = response;
        });

    $scope.clickFIrstTab = function () {
        $scope.firstTab = true;
        $scope.secondTab = false;
        ProfileService.photosProfile().success(function (response) {
            $scope.photosAlbums = response;
        });
    };

    $scope.clickSecondTab = function () {
        $scope.firstTab = false;
        $scope.secondTab = true;
    };

    $scope.logout = function () {
        UsersService.logout().success(function (response) {
            if (response == 'okLogout') {
                $window.location.href = "http://localhost:8080/social/#/login";
            }
        });
    };

    $scope.showPhotosFromAlbum = function (albumId) {
        console.log(albumId);
        ProfileService.photosFromAlbum(albumId).success(function (response) {
            $scope.photosAlbums = response;
        });
        $scope.firstTab = true;
        $scope.secondTab = false;
    };
}