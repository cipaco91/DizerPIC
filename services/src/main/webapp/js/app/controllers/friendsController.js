function FriendsCtrl($scope, $location,FriendsService,ProfileService,MenuService) {

    $scope.friendsTwitter={};
    $scope.friendsLinkedin={};
    $scope.friendsFacebook={};
    $scope.profileImage={};
    //$scope.bySocialNetworkShow=false;
    //$scope.byNameShow=false;
    //$scope.byJobShow=false;

    $scope.codes = [{code: 'all', value: "All" },{ code: 'socialNetwork', value: "by Social Network" },{ code: 'name', value: "by Name" },
                   { code: 'job', value: "by Job" },{ code: 'company', value: "by Company" }];

    $scope.update = function() {
        $scope.searchCode = $scope.selectedItem.code
        if($scope.searchCode == 'all'){
            $scope.bySocialNetworkShow=false;
            $scope.byNameShow=false;
            $scope.byJobShow=false;
        }else if($scope.searchCode == 'socialNetwork'){
            $scope.bySocialNetworkShow=true;
        }else if($scope.searchCode == 'name'){
            $scope.byNameShow=true;
        }else if($scope.searchCode == 'job'){
            $scope.byJobShow=true;
        }else if($scope.searchCode == 'company'){
            $scope.byJobShow=true;
        }
    };



    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible=response;
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible=response;
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinVisible=response;
    });

    ProfileService.findProfileImageFacebook().success(function (response) {
        $scope.profileImage=response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends=response;
    });

    FriendsService.findFriendsTwitter().
        success(function (users) {
            $scope.friendsTwitter = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FriendsService.findFriendsFacebook().
        success(function (users) {
            $scope.friendsFacebook = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FriendsService.findFriendsLinkedin().
        success(function (users) {
            $scope.friendsLinkedin = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });



}