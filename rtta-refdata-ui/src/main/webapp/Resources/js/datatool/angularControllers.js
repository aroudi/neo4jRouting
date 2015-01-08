function NetworkController($scope) {

}

function StationController($scope, generalService, SUCCESS, FAILURE, ALL_STATION_URI, ADD_STATION_URI, EDIT_STATION_URI, DEL_STATION_URI, STATION_CSV_URI, TRIPLET_CSV_URI) {

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
            {field:'stationId', enableCellEdit:false},
            {field:'shortName', enableCellEdit:false},
            {field:'longName', enableCellEdit:false},
            {field:'gtfsStopId', enableCellEdit:false},
            {field:'latitude', enableCellEdit:false},
            {field:'longtitude',enableCellEdit:false},
            {field:'interchangePoint', enableCellEdit:false}
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
            populateFormField(row);
            $scope.station = angular.copy(generalService.getRow());
        });
        gridApi.cellNav.on.navigate($scope, function(newRowCol, oldRowCol){
        });
    };

    /**
     * Scroll to the specific row.
     */
    $scope.scrollTo = function (rowIndex, colIndex) {
      $scope.gridApi.cellNav.scrollTo($scope.gridApi.grid, $scope, $scope.gridOptions.data[rowIndex], $scope.gridOptions.data[colIndex]);
    };

    /**
     * retreive station list from server
     */
    getAllStations();
    function getAllStations() {
        generalService.getAllRows(ALL_STATION_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
        });

    }

    /**
     * Reset station form.
     */
    function resetStationForm() {
        $scope.station.shortName='';
        $scope.station.longName='';
        $scope.station.gtfsStopId='';
        $scope.station.latitude='';
        $scope.station.longtitude='';
        $scope.station.interchangePoint=false;

    };

    /**
     * Populate station fields from selected row.
     */

    function populateFormField(row) {

        generalService.setRow(row.entity);
        generalService.setSelectedRow(row.entity);
        generalService.setRowSelected(true);
    }

    /**
     * Adding new station
     * @param stationObject
     */

    $scope.addStationRow = function(stationObject)
    {
        if (generalService.getAddBottonLabel() == 'Add') {
            generalService.setAddBottonLabel('Save')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            return;
        }
        if (generalService.getAddBottonLabel() =='Save') {

            stationObject.stationId = -1;
            generalService.addRow(stationObject, ADD_STATION_URI).then(function(response) {
                addResponse = response.data;
                if (addResponse.status == SUCCESS ) {
                    stationObject.stationId = addResponse.stationId;
                    $scope.gridOptions.data.push(angular.copy(stationObject));
                    $scope.scrollTo($scope.gridOptions.data.length-2,0);
                } else {
                    alert('Not able to add new station. ' + addResponse.message);
                }
            });

            generalService.setAddBottonLabel('Add');
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            return;

        }
    };

    /**
     * Editing specific row
     */
    $scope.editStationRow = function(stationObject)
    {
        if (generalService.getEditBottonLabel() == 'Edit') {

            //$scope.station = angular.copy(generalService.getRow());
            generalService.setEditBottonLabel('Save')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
        if (generalService.getEditBottonLabel() == 'Save') {

            generalService.editRow(stationObject,EDIT_STATION_URI).then(function(response) {
                serviceResponse = response.data;
                if (serviceResponse.status == SUCCESS ) {
                    selectedRow = generalService.getSelectedRow();
                    selectedRow.shortName = stationObject.shortName;
                    selectedRow.longName = stationObject.longName;
                    selectedRow.gtfsStopId = stationObject.gtfsStopId;
                    selectedRow.latitude = stationObject.latitude;
                    selectedRow.longtitude = stationObject.longtitude;
                    selecteRow.interchangePoint =stationObject.interchangePoint;
                    generalService.setRow(stationObject);
                } else {
                    alert('edit failed:'+serviceResponse.message);
                }
            });
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
    };

    $scope.deleteStationRow = function()
    {
        stationObject = generalService.getSelectedRow();
        if (!confirm('Are you sure you want to delete station: '+stationObject.longName +'?')) {
            return;
        }
        generalService.deleteRow(stationObject.stationId,DEL_STATION_URI).then(function(response){
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS ) {
                rowIndex = $scope.gridOptions.data.indexOf(stationObject);
                if (rowIndex>-1) {
                    $scope.gridOptions.data.splice(rowIndex,1);
                    generalService.setRowSelected(false);
                }
            } else {
                alert('Not able to delete: '+serviceResponse.message);
            }
        });
    }
    /**
     * cancel Adding or Editing
     */

    $scope.cancelStationRow = function()
    {
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.setAddBottonLabel('Add')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
        }
        if (generalService.getEditBottonLabel() =='Save') {
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();

        }
        //resetStationForm();
    };

    /**
     * Enable or Disable editing mode.
     * @returns {*}
     */
    $scope.formFieldDisabled = function()
    {
        return generalService.formFieldDisabled();
    };
    $scope.isCancelBottonDisable = function()
    {
        var disabled = generalService.isCancelBottonDisable();
        return disabled;


    };
    $scope.isAddBottonDisable = function()
    {
        return generalService.isAddBottonDisable();
    };
    $scope.isDeleteBottonDisable = function()
    {
        if (generalService.isRowSelected()){
            return !generalService.formFieldDisabled();
        } else {
            return true;
        }
    };
    $scope.isEditBottonDisable = function()
    {
        return generalService.isEditBottonDisable();
    };
    $scope.exportStationToCsv = function()
    {
        var hiddenElement = document.createElement('a');
        generalService.getAllRows(STATION_CSV_URI).then(function(response){
            hiddenElement.href = 'data:attachment/csv,' + encodeURI(response.data);
            hiddenElement.target ='_blank';
            hiddenElement.download = 'STATION_DATA.csv';
            hiddenElement.click();
        });
    };

    $scope.exportTripletToCsv = function()
    {
        var hiddenElement = document.createElement('a');
        generalService.getAllRows(TRIPLET_CSV_URI).then(function(response){
            hiddenElement.href = 'data:attachment/csv,' + encodeURI(response.data);
            hiddenElement.target ='_blank';
            hiddenElement.download = 'StopLinks.csv';
            hiddenElement.click();
        });
    };
}

