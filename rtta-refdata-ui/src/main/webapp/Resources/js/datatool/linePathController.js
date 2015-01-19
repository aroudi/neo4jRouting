function LinePathController($scope, generalService, SUCCESS, FAILURE, ALL_PATH_URI, GET_LINE_REF_URI, PATH_CSV_URI) {

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
            {field:'longName', enableCellEdit:false, width:'20%'},
            {name:'linePathStationList', displayName:'Stations', type:'object', cellFilter:'linePathStationList',enableCellEdit:false, width:'65%'}
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
     * retreive path list from server
     */
    getAllPaths();
    function getAllPaths() {
        generalService.getAllRows(ALL_PATH_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
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

}

