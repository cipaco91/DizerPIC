function SettingsCtrl($scope, $location,$window,ProfileService) {

    $scope.step = 1;
    $scope.step1=true;
    $scope.step2=false;
    $scope.step3=false;

    $scope.first='active';
    $scope.social='inactive';
    $scope.personal='inactive';

    $scope.wizzardDTO={};

    ProfileService.getWizzardDTO().success(function (response) {
        $scope.wizzardDTO=response;
    });

    $scope.setStep = function(step){
        if(step == 1){
            $scope.step1=false;
            $scope.step2=true;
            $scope.step3=false;
            $scope.first='active';
            $scope.social='active';
            $scope.personal='inactive';
        }else if(step == 2){
            $scope.step1=false;
            $scope.step2=false;
            $scope.step3=true;
            $scope.first='active';
            $scope.social='active';
            $scope.personal='active';
        }
    }

    $scope.finish=function(){
        $scope.wizzardDTO.firstName=$scope.firstName;
        $scope.wizzardDTO.lastName=$scope.lastName;
        $scope.wizzardDTO.email=$scope.email;
        //$scope.wizzardDTO.isFacebook=$scope.isFacebook;
        //$scope.wizzardDTO.isTwitter=$scope.isTwitter;
        //$scope.wizzardDTO.isLinkedin=$scope.isLinkedin;
        $scope.wizzardDTO.isFacebook=true;
        $scope.wizzardDTO.isTwitter=true;
        $scope.wizzardDTO.isLinkedin=true;
        $scope.wizzardDTO.username=$scope.username;
        $scope.wizzardDTO.password=$scope.password;
        $scope.wizzardDTO.repassword=$scope.repassword;
        console.log($scope.firstName);

        ProfileService.finishWizzardProfile($scope.wizzardDTO).success(function (response) {
            $scope.wizzardDTO=response;
        });

        $window.location.href="http://localhost:8080/social/#/profile"
    };

    $scope.setStepBack = function(step){
         if(step == 2){
            $scope.step1=true;
            $scope.step2=false;
            $scope.step3=false;
             $scope.first='active';
             $scope.social='inactive';
             $scope.personal='inactive';
        }else{
             $scope.step1=false;
             $scope.step2=true;
             $scope.step3=false;
             $scope.first='active';
             $scope.social='active';
             $scope.personal='inactive';
        }
    }
}