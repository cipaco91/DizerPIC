function FriendsCtrl($scope, $location, FriendsService, ProfileService, MenuService, $controller) {

    angular.extend(this, $controller('ProfileCtrl', {$scope: $scope}));

    $scope.friendsTwitter = {};
    $scope.friendsLinkedin = {};
    $scope.friendsFacebook = {};
    $scope.friendsGoogle = {};
    $scope.profileImage = {};
    $scope.selectedItem = {};
    $scope.selectedItem.code = 'all';
    $scope.selectedItem.value = 'All';
    $scope.bySocialNetworkShow = false;
    $scope.byNameShow = false;
    $scope.byJobShow = false;
    $scope.byCompanyShow = false;
    $scope.name = "";
    $scope.jobName = "";
    $scope.companyName = "";
    $scope.facebookFriendsShow = true;
    $scope.twitterFriendsShow = true;
    $scope.linkedinFriendsShow = true;
    $scope.googleFriendsShow = true;
    $scope.selectedItemSocial={};

    $scope.codes = [{code: 'all', value: "All"}, {code: 'socialNetwork', value: "by Social Network"}, {
        code: 'name',
        value: "by Name"}];

    $scope.socialNetworks = [{code: 'facebook', value: "Facebook"}, {
        code: 'linkedin',
        value: "Linkedin"
    }, {code: 'twitter', value: "Twitter"},
        {code: 'google', value: "Google Plus"}];


    $scope.changeSocial = function () {
        $scope.socialNetworkCode = $scope.selectedItemSocial.code;
        if ($scope.socialNetworkCode == 'facebook') {
            console.log($scope.socialNetworkCode);
            $scope.facebookFriendsShow = true;
            $scope.twitterFriendsShow = false;
            $scope.linkedinFriendsShow = false;
            $scope.googleFriendsShow = false;
        } else if ($scope.socialNetworkCode == 'linkedin') {
            console.log($scope.socialNetworkCode);
            $scope.facebookFriendsShow = false;
            $scope.twitterFriendsShow = false;
            $scope.linkedinFriendsShow = true;
            $scope.googleFriendsShow = false;
        } else if ($scope.socialNetworkCode == 'google') {
            console.log($scope.socialNetworkCode);
            $scope.facebookFriendsShow = false;
            $scope.twitterFriendsShow = false;
            $scope.linkedinFriendsShow = false;
            $scope.googleFriendsShow = true;
        } else if ($scope.socialNetworkCode == 'twitter'){
            console.log($scope.socialNetworkCode);
            $scope.facebookFriendsShow = false;
            $scope.twitterFriendsShow = true;
            $scope.linkedinFriendsShow = false;
            $scope.googleFriendsShow = false;
        }
    };

    $scope.changeName = function () {
        console.log($scope.name);
    };

    $scope.changeJobName = function () {
        console.log($scope.jobName);
    };

    $scope.changeCompanyName = function () {
        console.log($scope.companyName);
    };

    $scope.update = function () {
        $scope.searchCode = $scope.selectedItem.code;
        if ($scope.searchCode == 'all') {
            $scope.bySocialNetworkShow = false;
            $scope.byNameShow = false;
            $scope.byJobShow = false;
            $scope.byCompanyShow = false;
            $scope.byAgeShow = false;
            $scope.facebookFriendsShow = true;
            $scope.twitterFriendsShow = true;
            $scope.linkedinFriendsShow = true;
            $scope.googleFriendsShow = true;
        } else if ($scope.searchCode == 'socialNetwork') {
            $scope.bySocialNetworkShow = true;
            //$scope.byNameShow = false;
            //$scope.byJobShow = false;
            //$scope.byCompanyShow = false;
        } else if ($scope.searchCode == 'name') {
            $scope.byNameShow = true;
            //$scope.bySocialNetworkShow = false;
            //$scope.byJobShow = false;
            //$scope.byCompanyShow = false;
        } else if ($scope.searchCode == 'age') {
            $scope.byAgeShow = true;
            //$scope.bySocialNetworkShow = false;
            //$scope.byNameShow = false;
            //$scope.byCompanyShow = false;
        } else if ($scope.searchCode == 'company') {
            //$scope.byJobShow = false;
            //$scope.bySocialNetworkShow = false;
            //$scope.byNameShow = false;
            $scope.byCompanyShow = true;
        }

    };

    $scope.reset = function () {
        $scope.bySocialNetworkShow = false;
        $scope.byNameShow = false;
        $scope.byAgeShow = false;
        $scope.byCompanyShow = false;
        $scope.facebookFriendsShow = true;
        $scope.twitterFriendsShow = true;
        $scope.linkedinFriendsShow = true;
        $scope.googleFriendsShow = true;
        $scope.selectedItem.code = 'all';
        $scope.selectedItem.value = 'All';
        $scope.name='';
        $scope.update();
        $scope.search();
    };

    $scope.search = function () {
        $scope.changeSocial();
        $scope.findFriends();
    };


    $scope.findFriends = function () {
        if($scope.name != null && $scope.name != undefined && $scope.name != ''){
            $scope.valueName=$scope.name;
        }else{
            $scope.valueName="null";
        }

        $scope.friendsTwitter={};
        $scope.friendsLinkedin={};
        $scope.friendsFacebook={};
        $scope.friendsGoogle={};

        FriendsService.findFriendsTwitter($scope.valueName).
            success(function (users) {
                $scope.friendsTwitter = users;
            })
            .error(function (resp) {
                console.log("Error with FriendsService.findFriendsTwitter" + resp);
            });

        FriendsService.findFriendsFacebook($scope.valueName).
            success(function (users) {
                $scope.friendsFacebook = users;
            })
            .error(function (resp) {
                console.log("Error with FriendsService.findFriendsFacebook" + resp);
            });

        //FriendsService.findFriendsLinkedin($scope.valueName).
        //    success(function (users) {
        //        $scope.friendsLinkedin = users;
        //    })
        //    .error(function (resp) {
        //        console.log("Error with FriendsService.findFriendsLinkedin" + resp);
        //    });

        FriendsService.findFriendsGoogle($scope.valueName).
            success(function (users) {
                $scope.friendsGoogle = users;
            })
            .error(function (resp) {
                console.log("Error with FriendsService.findFriendsGoogle" + resp);
            });

    };

    $scope.findFriends();

    //$scope.filterFunctionLinkedin = function(element) {
    //   if($scope.name == "" ){
    //       return true;
    //   }
    //
    //   ProfileService.verifyContainsString(element.name,$scope.name).
    //        success(function (response) {
    //            return response;
    //        })
    //        .error(function (resp) {
    //           return false;
    //        });
    //};

    $scope.filterFunctionTwitter = function(element) {
        if($scope.name == "" ){
            return true;
        }

        if(element.name.toLowerCase().indexOf($scope.name.toLowerCase()) >= 0 ){
            return true;
        }
    };

    $scope.filterFunctionGoogle = function(element) {
        if($scope.name == "" ){
            return true;
        }

        if(element.name.toLowerCase().indexOf($scope.name.toLowerCase()) >= 0 ){
            return true;
        }

    };

    $scope.filterFunctionFacebook = function(element) {
        if($scope.name == ""){
            return true;
        }

        if(element.name.toLowerCase().indexOf($scope.name.toLowerCase()) >= 0 ){
            return true;
        }

    };
}