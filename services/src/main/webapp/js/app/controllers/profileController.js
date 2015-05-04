function ProfileCtrl($scope, $location,ProfileService,MenuService,FriendsService) {

    $scope.profileImage={};
    $scope.userProfile={};
    $scope.photosProfile={};
    $scope.photosAlbums={};
    $scope.friends={};
    $scope.albums={};
    $scope.firstTab=true;
    $scope.secondTab=false;

    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible=response;
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible=response;
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinVisible=response;
    });

        ProfileService.findProfileImage().success(function (response) {
            $scope.profileImage=response;
        });

    ProfileService.profileSN().success(function (response) {
        $scope.userProfile=response;
    });

    ProfileService.photosProfile().success(function (response) {
        $scope.photosProfile=response;
        $scope.photosAlbums=response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends=response;
    });

    FriendsService.albumsProfile().success(function (response) {
        $scope.albums=response;
    });

    $scope.clickFIrstTab = function () {
       $scope.firstTab=true;
        $scope.secondTab=false;
        ProfileService.photosProfile().success(function (response) {
            $scope.photosAlbums=response;
        });
    };

    $scope.clickSecondTab = function () {
        $scope.firstTab=false;
        $scope.secondTab=true;
    };

    $scope.showPhotosFromAlbum = function (albumId) {
       console.log(albumId);
        ProfileService.photosFromAlbum(albumId).success(function (response) {
            $scope.photosAlbums=response;
        });
        $scope.firstTab=true;
        $scope.secondTab=false;
    };
}