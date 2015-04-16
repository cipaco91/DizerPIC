function SettingsCtrl($scope, $location,$window) {

    $scope.step = 1;
    $scope.step1=true;
    $scope.step2=false;
    $scope.step3=false;

    $scope.first='active';
    $scope.social='inactive';
    $scope.personal='inactive';

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
        }else{
            $window.location.href="http://localhost:8080/social/#/profile"
        }
    }

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