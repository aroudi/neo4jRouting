function VisNetworkController($scope, generalService,drawNetworkService, SUCCESS, FAILURE, VIS_NETWORK_URI) {

    generalService.setChosenMenuItem('visNetwork');
    /**
     * UI-Grid declaration
     */


    /**
     * retreive network list from server
     */
    getAllVisNetworks();
    function getAllVisNetworks() {
        generalService.getAllRows(VIS_NETWORK_URI).then(function(response){
            displayNetwork(response.data);
        });

    }
    function displayNetwork(networkData) {
        if ( networkData == undefined )
            return;
        drawNetworkService.drawAllNetwork(networkData);
        $scope.network_data = drawNetworkService.getNetworkData();
        $scope.network_options = drawNetworkService.getNetworkOptions();
    }
}

