angular.module(localizacionGps) .controller("WebServiceDisplayController", function($rootScope, $scope, wsData) {
	$scope.wsData = wsData.data;
});