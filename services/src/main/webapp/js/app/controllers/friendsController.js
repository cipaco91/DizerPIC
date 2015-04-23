function FriendsCtrl($scope, $location, FriendsService, ProfileService, MenuService) {

    $scope.friendsTwitter = {};
    $scope.friendsLinkedin = {};
    $scope.friendsFacebook = {};
    $scope.profileImage = {};
    $scope.selectedItem = {};
    $scope.selectedItem.code = 'all';
    $scope.selectedItem.value = 'All';
    $scope.bySocialNetworkShow = false;
    $scope.byNameShow = false;
    $scope.byJobShow = false;
    $scope.byCompanyShow = false;
    $scope.name="";
    $scope.jobName="";
    $scope.companyName="";
    $scope.facebookFriendsShow=true;
    $scope.twitterFriendsShow=true;
    $scope.linkedinFriendsShow=true;


    $scope.codes = [{code: 'all', value: "All"}, {code: 'socialNetwork', value: "by Social Network"}, {code: 'name', value: "by Name"},
        {code: 'job', value: "by Job"}, {code: 'company', value: "by Company"}];

    $scope.socialNetworks = [{code: 'facebook', value: "Facebook"}, {code: 'linkedin', value: "Linkedin"}, {code: 'twitter', value: "Twitter"}];

    $scope.changeSocial= function(){
        $scope.socialNetworkCode = $scope.selectedItemSocial.code;
        if($scope.socialNetworkCode == 'facebook'){
           console.log($scope.socialNetworkCode);
            $scope.facebookFriendsShow=true;
            $scope.twitterFriendsShow=false;
            $scope.linkedinFriendsShow=false;
        }else if($scope.socialNetworkCode == 'linkedin'){
            console.log($scope.socialNetworkCode);
            $scope.facebookFriendsShow=false;
            $scope.twitterFriendsShow=false;
            $scope.linkedinFriendsShow=true;
        }else{
            console.log($scope.socialNetworkCode);
            $scope.facebookFriendsShow=false;
            $scope.twitterFriendsShow=true;
            $scope.linkedinFriendsShow=false;
        }
    };

    $scope.changeName= function () {
        console.log($scope.name);
    };

    $scope.changeJobName= function () {
        console.log($scope.jobName);
    };

    $scope.changeCompanyName= function () {
        console.log($scope.companyName);
    };

    $scope.update = function () {
        $scope.searchCode = $scope.selectedItem.code;
        if ($scope.searchCode == 'all') {
            $scope.bySocialNetworkShow = false;
            $scope.byNameShow = false;
            $scope.byJobShow = false;
            $scope.byCompanyShow = false;
            $scope.facebookFriendsShow=true;
            $scope.twitterFriendsShow=true;
            $scope.linkedinFriendsShow=true;
        } else if ($scope.searchCode == 'socialNetwork') {
            $scope.bySocialNetworkShow = true;
            $scope.byNameShow = false;
            $scope.byJobShow = false;
            $scope.byCompanyShow = false;
        } else if ($scope.searchCode == 'name') {
            $scope.byNameShow = true;
            $scope.bySocialNetworkShow = false;
            $scope.byJobShow = false;
            $scope.byCompanyShow = false;
        } else if ($scope.searchCode == 'job') {
            $scope.byJobShow = true;
            $scope.bySocialNetworkShow = false;
            $scope.byNameShow = false;
            $scope.byCompanyShow = false;
        } else if ($scope.searchCode == 'company') {
            $scope.byJobShow = false;
            $scope.bySocialNetworkShow = false;
            $scope.byNameShow = false;
            $scope.byCompanyShow = true;
        }
    };

    MenuService.isConnectFacebook().success(function (response) {
        $scope.facebookVisible = response;
    });

    MenuService.isConnectTwittter().success(function (response) {
        $scope.twitterVisible = response;
    });

    MenuService.isConnectLinkedin().success(function (response) {
        $scope.linkedinVisible = response;
    });

    ProfileService.findProfileImage().success(function (response) {
        $scope.profileImage = response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends = response;
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

    $scope.filterFunctionLinkedin = function(element) {
       if($scope.name == ""){
           return true;
       }
        return element.name.indexOf($scope.name) >=0 ;
    };

    $scope.filterFunctionTwitter = function(element) {
        if($scope.name == ""){
            return true;
        }
        return element.name.indexOf($scope.name) >=0 ;
    };

    $scope.filterFunctionFacebook = function(element) {
        if($scope.name == ""){
            return true;
        }
        return element.name.indexOf($scope.name) >=0 ;
    };


}