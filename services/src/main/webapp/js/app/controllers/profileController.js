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

    $scope.indexPhoto={};

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

    $scope.setIndexPhoto = function (src) {
        $scope.index=src;
        $('#myModal').modal('show').css('display', 'grid');
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

    function centerModals(){
        $('.modal').each(function(i){
            var $clone = $(this).clone().css('display', 'block').appendTo('body');
            var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
            top = top > 0 ? top : 0;
            $clone.remove();
            $(this).find('.modal-content').css("margin-top", top);
        });
    }
    $('.modal').on('show.bs.modal', centerModals);
    $(window).on('resize', centerModals);

    $scope.showPhotosFromAlbum = function (albumId) {
        console.log(albumId);
        ProfileService.photosFromAlbum(albumId).success(function (response) {
            $scope.photosAlbums = response;
        });
        $scope.firstTab = true;
        $scope.secondTab = false;
    };
}