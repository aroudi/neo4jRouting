function PlatformController($scope, generalService, SUCCESS, FAILURE, ALL_PLATFORM_URI, ADD_PLATFORM_URI, EDIT_PLATFORM_URI, DEL_PLATFORM_URI, GET_STATION_REF_URI, PLATFORM_CSV_URI) {

    generalService.setChosenMenuItem('platform');
    $scope.platform = {};
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
            {field:'stationStopId', displayName:'Station Stop Id', enableCellEdit:false},
            {field:'stationShortName', displayName:'Station Code', enableCellEdit:false},
            {field:'platformName', enableCellEdit:false},
            {field:'platformLongName', displayName:'Description', enableCellEdit:false},
            {field:'platformNo', enableCellEdit:false},
            {field:'platformStopId', displayName:'Stop Id', enableCellEdit:false},
            {field:'latitude', enableCellEdit:false},
            {field:'longtitude',enableCellEdit:false}
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
            rowEntity = angular.copy(generalService.getRow());
            $scope.platform.platformName = rowEntity.platformName;
            $scope.platform.platformLongName = rowEntity.platformLongName;
            $scope.platform.platformNo = rowEntity.platformNo;
            $scope.platform.latitude = rowEntity.latitude;
            $scope.platform.longtitude = rowEntity.longtitude;
            $scope.platform.platformStopId = rowEntity.platformStopId;
            defaultRow= generalService.populateSelectList(generalService.getRow().stationId,$scope.stationSet);
            $scope.platform.station = defaultRow;
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
     * retreive platform list from server
     */
    $scope.$on('versionChange', function(event, data) {
        getAllPlatforms();
        getStationRef();
    });

    getAllPlatforms();
    getStationRef();
    function getAllPlatforms() {
        generalService.getAllRows(ALL_PLATFORM_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
        });

    }
    function getStationRef() {
        generalService.getAllRows(GET_STATION_REF_URI).then(function(response){
            $scope.stationSet = response.data;
            $scope.platform.station = $scope.stationSet[0];
        });

    }

    /**
     * Reset platform form.
     */
    function resetPlatformForm() {
        $scope.platform.platformName='';
        $scope.platform.platformLongName='';
        $scope.platform.platformStopId='';
        $scope.platform.latitude='';
        $scope.platform.longtitude='';
        $scope.platform.platformNo='';

    };

    /**
     * Populate platform fields from selected row.
     */

    function populateFormField(row) {

        generalService.setRow(row.entity);
        generalService.setSelectedRow(row.entity);
        generalService.setRowSelected(true);
    };

    /**
     * Adding new platform
     * @param platformObject
     */

    $scope.addPlatformRow = function(platformObject)
    {
        if (generalService.getAddBottonLabel() == 'Add') {
            generalService.setAddBottonLabel('Save')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            defaultRow= generalService.populateSelectList(generalService.getRow().stationId,$scope.stationSet);
            $scope.platform.station = defaultRow;
            return;
        }
        if (generalService.getAddBottonLabel() =='Save') {

            generalService.addRow(platformObject, ADD_PLATFORM_URI).then(function(response) {
                addResponse = response.data;
                if (addResponse.status == SUCCESS ) {
                    // todo: add platform properties from platform object
                    platformRow = {
                        "stationId" : platformObject.station.refDataId,
                        "stationStopId" : addResponse.stationGtfsStopId,
                        "stationShortName" : platformObject.station.refDataCode,
                        "platformName" : platformObject.platformName,
                        "platformLongName" : platformObject.platformLongName,
                        "platformNo" : platformObject.platformNo,
                        "platformStopId" : platformObject.platformStopId,
                        "latitude" : platformObject.latitude,
                        "longtitude" : platformObject.longtitude
                    }
                    $scope.gridOptions.data.push(platformRow);
                    $scope.scrollTo($scope.gridOptions.data.length-2,0);
                } else {
                    alert('Not able to add new platform. '+addResponse.message);
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
    $scope.editPlatformRow = function()
    {
        if (generalService.getEditBottonLabel() == 'Edit') {
            /*
            rowEntity = angular.copy(generalService.getRow());
            $scope.platform.platformName = rowEntity.platformName;
            $scope.platform.platformLongName = rowEntity.platformLongName;
            $scope.platform.platformNo = rowEntity.platformNo;
            $scope.platform.latitude = rowEntity.latitude;
            $scope.platform.longtitude = rowEntity.longtitude;
            $scope.platform.platformStopId = rowEntity.platformStopId;
            defaultRow= generalService.populateSelectList(generalService.getRow().stationId,$scope.stationSet);
            $scope.platform.station = defaultRow;
            */
            generalService.setEditBottonLabel('Save')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
        if (generalService.getEditBottonLabel() == 'Save') {

            generalService.editRow($scope.platform,EDIT_PLATFORM_URI).then(function(response) {
                serviceResponse = response.data;
                platformObject = $scope.platform;
                if (serviceResponse.status == SUCCESS ) {
                    selectedRow = generalService.getSelectedRow();
                    selectedRow.stationId = platformObject.station.refDataId;
                    selectedRow.stationStopId = serviceResponse.stationGtfsStopId;
                    selectedRow.stationShortName = platformObject.station.refDataCode;
                    selectedRow.platformName = platformObject.platformName;
                    selectedRow.platformLongName = platformObject.platformLongName;
                    selectedRow.platformNo = platformObject.platformNo;
                    selectedRow.latitude = platformObject.latitude;
                    selectedRow.longtitude =  platformObject.longtitude;
                    selectedRow.platformStopId =platformObject.platformStopId;
                    generalService.setRow(platformObject);
                } else {
                    alert('edit failed:'+serviceResponse.message);
                }
            });
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
    };

    $scope.deletePlatformRow = function()
    {
        platformObject = generalService.getSelectedRow();
        if (!confirm('Are you sure you want to delete platform: '+platformObject.platformLongName +'?')) {
            return;
        }
        generalService.deleteRow(platformObject.platformName,DEL_PLATFORM_URI).then(function(response){
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS ) {
                rowIndex = $scope.gridOptions.data.indexOf(platformObject);
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

    $scope.cancelPlatformRow = function()
    {
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.setAddBottonLabel('Add')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
        }
        if (generalService.getEditBottonLabel() =='Save') {
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();

        }
        //resetPlatformForm();
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
        generalService.getAllRows(PLATFORM_CSV_URI).then(function(response){
            hiddenElement.href = 'data:attachment/csv,' + encodeURI(response.data);
            hiddenElement.target ='_blank';
            hiddenElement.download = 'PLATFORM_DATA.csv';
            hiddenElement.click();
        });
    };

}

