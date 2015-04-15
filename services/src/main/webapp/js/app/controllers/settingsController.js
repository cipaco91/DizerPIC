function SettingsCtrl($scope, $location) {

    $scope.step = 1;

    $scope.setStep = function(step){
        $scope.step = step+1;
    }


}