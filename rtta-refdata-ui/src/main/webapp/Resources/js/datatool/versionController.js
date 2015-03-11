function VersionController($scope, $interval, generalService, drawNetworkService, SUCCESS, FAILURE, ALL_VERSION_URI, ADD_VERSION_URI, EDIT_VERSION_URI, DEL_VERSION_URI, uiGridConstants) {

    generalService.setChosenMenuItem('version');
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
            //default
            {field:'id', visible:false, enableCellEdit:false},
            {field:'name', aggregationType: uiGridConstants.aggregationTypes.count},
            {field:'description'},
            {field:'createDate', displayName:'Created'},
            {field:'commenceDate'},
            {field:'baseVersion'},
            {field:'active',
                cellClass:
                 function(grid, row, col, rowRenderIndex, colRenderIndex) {
                     if (grid.getCellValue(row, col) === true) {
                         return 'green';
                     } else {
                         return 'red'
                     }
                 },
                enableCellEdit:false}
        ]
    }
    $scope.gridOptions.enableRowSelection = true;
    $scope.gridOptions.enableRowHeaderSelection = false;
    $scope.gridOptions.enableSelectAll = false;
    $scope.gridOptions.multiSelect = false;
    $scope.gridOptions.modifierKeysToMultiSelect = false;
    $scope.gridOptions.noUnselect= true;
    //$scope.gridOptions.data=[];

    /**
     * Define gridAPI and capture required events
     */
    $scope.gridOptions.onRegisterApi = function (gridApi) {
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope, function(row) {
            populateFormField(row);
            $scope.dataVersion = angular.copy(generalService.getRow());
            defaultRow= generalService.populateBaseVersion(generalService.getRow().baseVersion,$scope.baseVersionSet);
            $scope.baseVersion = defaultRow;

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

    $interval(function refresh() {
        generalService.refreshRows(ALL_VERSION_URI).then(function(response) {
            $scope.gridOptions.columnDefs[6] =             {field:'active',
                cellClass:
                    function(grid, row, col, rowRenderIndex, colRenderIndex) {
                        if (grid.getCellValue(row, col) === true) {
                            return 'green';
                        } else {
                            return 'red'
                        }
                    },
                enableCellEdit:false};
            $scope.gridOptions.data = response.data;
            displayNetwork($scope.gridOptions.data);

        })
    }, 30000);

    /**
     * retreive network list from server
     */
    getAllVersions();
    function getAllVersions() {
        generalService.getAllRows(ALL_VERSION_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
            $scope.baseVersionSet = response.data;
            $scope.baseVersion= generalService.populateActiveVersion($scope.baseVersionSet);
            displayNetwork($scope.gridOptions.data);

        });

    }

    /**
     * Reset version form.
     */
    function resetDataVersionForm() {
        $scope.dataVersion.name='';
        $scope.dataVersion.description='';
        $scope.dataVersion.commenceDate='';
    };

    /**
     * Populate version fields from selected row.
     */

    function populateFormField(row) {

        generalService.setRow(row.entity);
        generalService.setSelectedRow(row.entity);
        generalService.setRowSelected(true);
    };

    /**
     * Adding new DataVersion
     * @param VersionObject
     */

    $scope.addDataVersionRow = function(dataVersionObject)
    {
        if (generalService.getAddBottonLabel() == 'Add') {
            generalService.setAddBottonLabel('Save')
            getAllVersions();
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            $scope.baseVersion= generalService.populateActiveVersion($scope.baseVersionSet);
            return;
        }
        if (generalService.getAddBottonLabel() =='Save') {

            dataVersionObject.id = -1;

            commenceDate = document.getElementById('commenceDate').value;
            dataVersionObject.commenceDate =commenceDate;
            dataVersionObject.active = false;
            dataVersionObject.createDate ='';
            if ($scope.baseVersion != undefined && $scope.baseVersion.name != undefined) {
                dataVersionObject.baseVersion = $scope.baseVersion.name;
            } else {
                dataVersionObject.baseVersion = '';
            }

            generalService.addRow(dataVersionObject, ADD_VERSION_URI).then(function(response) {
                addResponse = response.data;
                if (addResponse.status == SUCCESS ) {
                    dataVersionObject.id = addResponse.id;
                    dataVersionObject.createDate = addResponse.createDate;
                    dataVersionObject.active = addResponse.active;
                    $scope.gridOptions.data.push(angular.copy(dataVersionObject));
                    $scope.scrollTo($scope.gridOptions.data.length-1,0);
                    $scope.$emit('versionListChange');
                    displayNetwork($scope.gridOptions.data);
                } else {
                    alert('Not able to add new data version. ' + addResponse.message);
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
    $scope.editDataVersionRow = function(dataVersionObject)
    {
        if (generalService.getEditBottonLabel() == 'Edit') {
            //$scope.network = angular.copy(generalService.getRow());
            generalService.setEditBottonLabel('Save')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
        if (generalService.getEditBottonLabel() == 'Save') {
            commenceDate = document.getElementById('commenceDate').value;
            dataVersionObject.commenceDate =commenceDate;
            if ($scope.baseVersion != undefined) {
                dataVersionObject.baseVersion = $scope.baseVersion.name;
            } else {
                dataVersionObject.baseVersion = '';
            }

            generalService.editRow(dataVersionObject,EDIT_VERSION_URI).then(function(response) {
                serviceResponse = response.data;
                if (serviceResponse.status == SUCCESS ) {
                    selectedRow = generalService.getSelectedRow();
                    selectedRow.id = dataVersionObject.id;
                    selectedRow.name = dataVersionObject.name;
                    selectedRow.description = dataVersionObject.description;
                    selectedRow.createDate = serviceResponse.createDate;
                    selectedRow.commenceDate = dataVersionObject.commenceDate;
                    selectedRow.active =serviceResponse.active;
                    generalService.setRow(dataVersionObject);
                    $scope.$emit('versionListChange');
                    displayNetwork($scope.gridOptions.data);
                } else {
                    alert('edit failed:'+serviceResponse.message);
                }
            });
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
    };

    $scope.deleteDataVersionRow = function()
    {
        dataVersionObject = generalService.getSelectedRow();
        if (!confirm('Are you sure you want to delete version: '+dataVersionObject.name +'?')) {
            return;
        }
        generalService.deleteRow(dataVersionObject.id,DEL_VERSION_URI).then(function(response){
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS ) {
                rowIndex = $scope.gridOptions.data.indexOf(dataVersionObject);
                if (rowIndex>-1) {
                    $scope.gridOptions.data.splice(rowIndex,1);
                    generalService.setRowSelected(false);
                    $scope.$emit('versionListChange');
                }
                displayNetwork($scope.gridOptions.data);
            } else {
                alert('Not able to delete: '+serviceResponse.message);
            }
        });
    }

    /**
     * cancel Adding or Editing
     */

    $scope.cancelDataVersionRow = function()
    {
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.setAddBottonLabel('Add')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
        }
        if (generalService.getEditBottonLabel() =='Save') {
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();

        }
        resetDataVersionForm();
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
    function displayNetwork(dataVersions) {
        if ( dataVersions == undefined )
            return;
        drawNetworkService.drawVersions(dataVersions)
        $scope.network_data = drawNetworkService.getNetworkData();
        $scope.network_options = drawNetworkService.getNetworkOptions();
    }
}

