function NodeController($scope, generalService, SUCCESS, ALL_NODE_URI, ADD_NODE_URI, EDIT_NODE_URI, DEL_NODE_URI, uiGridConstants) {
    $scope.node = {};
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
            {field:'nodeId', visible:false, enableCellEdit:false},
            {field:'name', aggregationType: uiGridConstants.aggregationTypes.count},
            {field:'longName'},
            {field:'platformName'},
            {field:'dummy'},
            {field:'junction'},
            {field:'workingTimingPoint'},
            {field:'endOfLine'},
            {field:'wellDuration'},
            {field:'upRecoveryDuration'},
            {field:'downRecoveryDuration'},
            {field:'length'},
            {field:'latitude'},
            {field:'longtitude'}
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
            $scope.node = angular.copy(generalService.getRow());
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
     * retreive network list from server
     */
    getAllNodes();
    function getAllNodes() {
        generalService.getAllRows(ALL_NODE_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
        });

    }

    /**
     * Reset platform form.
     */
    function resetNodeForm() {
        $scope.node.name='';
        $scope.node.longName='';
        $scope.node.platformName='';
        $scope.node.dummy=false;
        $scope.node.junction=false;
        $scope.node.workingTimingPoint=false;
        $scope.node.publicTimingPoint=false;
        $scope.node.endOfLine=false;
        $scope.node.wellDuration='';
        $scope.node.upRecoveryDuration='';
        $scope.node.downRecoveryDuration='';
        $scope.node.length=0;
        $scope.node.latitude=0;
        $scope.node.longitude=0;
    };

    /**
     * Populate network fields from selected row.
     */

    function populateFormField(row) {

        generalService.setRow(row.entity);
        generalService.setSelectedRow(row.entity);
        generalService.setRowSelected(true);
    };

    /**
     * Adding new Node
     * @param nodeObject
     */

    $scope.addNodeRow = function(nodeObject)
    {
        if (generalService.getAddBottonLabel() == 'Add') {
            generalService.setAddBottonLabel('Save')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            return;
        }
        if (generalService.getAddBottonLabel() =='Save') {

            nodeObject.nodeId = -1;
            generalService.addRow(nodeObject, ADD_NODE_URI).then(function(response) {
                addResponse = response.data;
                if (addResponse.status == SUCCESS ) {
                    nodeObject.nodeId = addResponse.id;
                    newAddedObject=angular.copy(nodeObject);
                    $scope.gridOptions.data.push(newAddedObject);
                    rowIndex = $scope.gridOptions.data.indexOf(newAddedObject);
                    $scope.scrollTo(rowIndex,0);
                } else {
                    alert('Not able to add new node. ' + addResponse.message);
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
    $scope.editNodeRow = function(nodeObject)
    {
        if (generalService.getEditBottonLabel() == 'Edit') {
            //$scope.node = angular.copy(generalService.getRow());
            generalService.setEditBottonLabel('Save')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
        if (generalService.getEditBottonLabel() == 'Save') {
            generalService.editRow(nodeObject,EDIT_NODE_URI).then(function(response) {
                serviceResponse = response.data;
                if (serviceResponse.status == SUCCESS ) {
                    selectedRow = generalService.getSelectedRow();
                    selectedRow.nodeId = nodeObject.nodeId;
                    selectedRow.name = nodeObject.name;
                    selectedRow.longName = nodeObject.longName;
                    selectedRow.platformName = nodeObject.platformName;

                    selectedRow.dummy = nodeObject.dummy;
                    selectedRow.junction = nodeObject.junction;
                    selectedRow.workingTimingPoint = nodeObject.workingTimingPoint;
                    selectedRow.publicTimingPoint = nodeObject.publicTimingPoint;
                    selectedRow.endOfLine = nodeObject.endOfLine;
                    selectedRow.wellDuration = nodeObject.wellDuration;
                    selectedRow.upRecoveryDuration = nodeObject.upRecoveryDuration;
                    selectedRow.downRecoveryDuration = nodeObject.downRecoveryDuration;
                    selectedRow.length = nodeObject.length;
                    selectedRow.latitude = nodeObject.latitude;
                    selectedRow.longtitude = nodeObject.longtitude;

                    generalService.setRow(nodeObject);
                } else {
                    alert('edit failed:'+serviceResponse.message);
                }
            });
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
    };

    $scope.deleteNodeRow = function()
    {
        nodeObject = generalService.getSelectedRow();
        if (!confirm('Are you sure you want to delete node: '+nodeObject.name +'?')) {
            return;
        }
        generalService.deleteRow(nodeObject.nodeId,DEL_NODE_URI).then(function(response){
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS ) {
                rowIndex = $scope.gridOptions.data.indexOf(nodeObject);
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

    $scope.cancelNodeRow = function()
    {
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.setAddBottonLabel('Add')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
        }
        if (generalService.getEditBottonLabel() =='Save') {
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();

        }
        //resetNodeForm();
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

}

