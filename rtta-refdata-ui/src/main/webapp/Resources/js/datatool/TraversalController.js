function TraversalController($scope, generalService, drawNetworkService, SUCCESS, FAILURE, TRAVERSAL_URI, ALLPATH_URI) {

    generalService.setChosenMenuItem('traversal');
    /**
     * UI-Grid declaration
     */
    $scope.gridOptions = {
        columnDefs: [
            //default
            {field:'pathName', enableCellEdit:false}
        ]
    }
    $scope.gridOptions.enableRowSelection = true;
    $scope.gridOptions.enableRowHeaderSelection = false;
    $scope.gridOptions.enableSelectAll = false;
    $scope.gridOptions.multiSelect = false;
    $scope.gridOptions.modifierKeysToMultiSelect = false;
    $scope.gridOptions.noUnselect= true;

    /**
     * Define gridAPI and capture required events
     */
    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope, function(row) {
            displayNetwork(row.entity);
        });
        gridApi.cellNav.on.navigate($scope, function(newRowCol, oldRowCol){
        });
    };

    /**
     * retreive station list from server
     */
    $scope.traverse = function(traverseForm) {
        var servicePath = TRAVERSAL_URI + '/' + traverseForm.fromNode + '/' + traverseForm.toNode;
        generalService.getAllRows(servicePath).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
            displayNetwork($scope.gridOptions.data[0]);
        });

    }

    /**
     * find all valid path between 2 nodes
     */
    $scope.findAllPaths = function(traverseForm) {
        var servicePath = ALLPATH_URI + '/' + traverseForm.fromNode + '/' + traverseForm.toNode;
        generalService.getAllRows(servicePath).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
            displayNetwork($scope.gridOptions.data[0]);
        });

    }


    function displayNetwork(row) {
        if ( row == undefined )
            return;
        var nodeList = row.nodes;
        drawNetworkService.drawPath(nodeList);
        $scope.network_data = drawNetworkService.getNetworkData();
        $scope.network_options = drawNetworkService.getNetworkOptions();
    }

}
