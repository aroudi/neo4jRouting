function LocationController($scope, generalService, SUCCESS, FAILURE, ALL_LOCATION_URI, ADD_LOCATION_URI, EDIT_LOCATION_URI, DEL_LOCATION_URI, LOCATION_CSV_URI, uiGridConstants) {

    generalService.setChosenMenuItem('location');
    generalService.initBottons();
    $scope.addBottonLabel = generalService.getAddBottonLabel();
    $scope.editBottonLabel = generalService.getEditBottonLabel();
    /**
     * UI-Grid declaration
     */
    $scope.gridOptions = {
        showFooter: true,
        enableFiltering: true,
        columnDefs: [
            {field:'id', visible:false, enableCellEdit:false},
            {field:'name', aggregationType: uiGridConstants.aggregationTypes.count},
            {field:'nodeName'},
            {field:'systemName'},
            {field:'longtitude', displayName:'Longitude'},
            {field:'latitude'},
            {field:'excludeFringe'}
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
            $scope.location = angular.copy(generalService.getRow());
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
     * retreive location list from server
     */
    $scope.$on('versionChange', function(event, data) {
        getAllLocations();
    });
    getAllLocations();
    function getAllLocations() {
        generalService.getAllRows(ALL_LOCATION_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
        });

    }

    /**
     * Reset location form.
     */
    function resetLocationForm() {
        $scope.location = generalService.getRow();

    };

    /**
     * Populate location fields from selected row.
     */

    function populateFormField(row) {

        generalService.setRow(row.entity);
        generalService.setSelectedRow(row.entity);
        generalService.setRowSelected(true);
    };

    /**
     * Adding new Location
     * @param locationObject
     */

    $scope.addLocationRow = function(locationObject)
    {
        if (generalService.getAddBottonLabel() == 'Add') {
            generalService.setAddBottonLabel('Save')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            return;
        }
        if (generalService.getAddBottonLabel() =='Save') {

            locationObject.id = -1;
                generalService.addRow(locationObject, ADD_LOCATION_URI).then(function(response) {
                addResponse = response.data;
                if (addResponse.status == SUCCESS ) {
                    locationObject.id = addResponse.id;
                    $scope.gridOptions.data.push(angular.copy(locationObject));
                    $scope.scrollTo($scope.gridOptions.data.length-2,0);
                } else {
                    alert('Not able to add new location. ' + addResponse.message);
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
    $scope.editLocationRow = function(locationObject)
    {
        if (generalService.getEditBottonLabel() == 'Edit') {
            //$scope.location = angular.copy(generalService.getRow());
            generalService.setEditBottonLabel('Save')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
        if (generalService.getEditBottonLabel() == 'Save') {
            generalService.editRow(locationObject,EDIT_LOCATION_URI).then(function(response) {
                serviceResponse = response.data;
                if (serviceResponse.status == SUCCESS ) {
                    selectedRow = generalService.getSelectedRow();
                    selectedRow.id = locationObject.id;
                    selectedRow.name = locationObject.name;
                    selectedRow.systemName = locationObject.systemName;
                    selectedRow.nodeName = locationObject.nodeName;
                    selectedRow.longtitude = locationObject.longtitude;
                    selectedRow.latitude = locationObject.latitude;
                    selectedRow.excludeFringe = locationObject.excludeFringe;
                    generalService.setRow(locationObject);
                } else {
                    alert('edit failed:'+serviceResponse.message);
                }
            });
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
    };

    $scope.deleteLocationRow = function()
    {
        locationObject = generalService.getSelectedRow();
        if (!confirm('Are you sure you want to delete location: '+locationObject.name +'?')) {
            return;
        }
        generalService.deleteRow(locationObject.id,DEL_LOCATION_URI).then(function(response){
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS ) {
                rowIndex = $scope.gridOptions.data.indexOf(locationObject);
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

    $scope.cancelLocationRow = function()
    {
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.setAddBottonLabel('Add')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
        }
        if (generalService.getEditBottonLabel() =='Save') {
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();

        }
        resetLocationForm();
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

    $scope.isInEditMode = function()
    {
        return generalService.getEditBottonLabel()=='Save' ? true : false;
    };

    $scope.exportToCsv = function()
    {
        var hiddenElement = document.createElement('a');
        generalService.getAllRows(LOCATION_CSV_URI).then(function(response){
            hiddenElement.href = 'data:attachment/csv,' + encodeURI(response.data);
            hiddenElement.target ='_blank';
            hiddenElement.download = 'Location.csv';
            hiddenElement.click();
        });
    };
}

