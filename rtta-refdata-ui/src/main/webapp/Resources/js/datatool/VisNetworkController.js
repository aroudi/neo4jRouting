function VisNetworkController($scope, generalService,drawNetworkService, SUCCESS, FAILURE, VIS_NETWORK_URI) {
    //$scope.networkList = [];
    generalService.setChosenMenuItem('visNetwork');
    /**
     * UI-Grid declaration
     */


    /**
     * retreive network list from server
     */
    $scope.$on('versionChange', function(event, data) {
        getAllVisNetworks();
    });

    getAllVisNetworks();

    function getAllVisNetworks() {
        generalService.getAllRows(VIS_NETWORK_URI).then(function(response){
            $scope.networkList = response.data;
            displayNetwork(response.data[2]);
            $scope.networkName = response.data[2].networkName;
        });

    }
    $scope.displayNetwork = function(networkData) {
        displayNetwork(networkData);
    }
    function displayNetwork(networkData) {
        $scope.networkName = networkData.networkName;
        if ( networkData == undefined )
            return;
        drawNetworkService.drawAllNetwork(networkData);
        $scope.network_data = drawNetworkService.getNetworkData();
        $scope.network_options = drawNetworkService.getNetworkOptions();
    }
}

