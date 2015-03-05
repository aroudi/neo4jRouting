function LinePathController($scope, generalService,drawNetworkService, SUCCESS, FAILURE, ALL_PATH_URI, GET_LINE_REF_URI, PATH_CSV_URI) {

    generalService.setChosenMenuItem('linePath');
    $scope.path = {};
    generalService.initBottons();
    /**
     * UI-Grid declaration
     */
    $scope.addBottonLabel = generalService.getAddBottonLabel();
    $scope.editBottonLabel = generalService.getEditBottonLabel();
    $scope.gridOptions = {
        enableFiltering: true,
        columnDefs: [
            //default
            {field:'pathId', visible:false, enableCellEdit:false},
            {field:'lineName', enableCellEdit:false, width:'10%'},
            {field:'name', enableCellEdit:false, width:'5%'},
            {field:'longName', enableCellEdit:false, width:'20%'}/*,
            {name:'linePathStationList', displayName:'Stations', type:'object', cellFilter:'linePathStationList',enableCellEdit:false, width:'65%'}*/
        ]
    }
    $scope.gridOptions.enableRowSelection = true;
    $scope.gridOptions.enableColumnResizing = true;
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
     * retreive path list from server
     */
    $scope.$on('versionChange', function(event, data) {
        getAllPaths();
    });
    getAllPaths();
    function getAllPaths() {
        generalService.getAllRows(ALL_PATH_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
            displayNetwork($scope.gridOptions.data[0]);
        });

    }
    $scope.exportToCsv = function()
    {
        var hiddenElement = document.createElement('a');
        generalService.getAllRows(PATH_CSV_URI).then(function(response){
            hiddenElement.href = 'data:attachment/csv,' + encodeURI(response.data);
            hiddenElement.target ='_blank';
            hiddenElement.download = 'NetworkLinesPaths.csv';
            hiddenElement.click();
        });
    };
    function displayNetwork(row) {
        if ( row == undefined )
            return;
        var nodeList = row.linePathStationList;
        $scope.pathName = row.longName;
        drawNetworkService.setNetworkData(nodeList, row.backgroundColourHex, row.textColourHex );
        $scope.network_data = drawNetworkService.getNetworkData();
        $scope.network_options = drawNetworkService.getNetworkOptions();
    }
}

