function NetworkController($scope, generalService, SUCCESS, FAILURE, ALL_NETWORK_URI, ADD_NETWORK_URI, EDIT_NETWORK_URI, DEL_NETWORK_URI) {

    $scope.platform = {};
    generalService.initBottons();
    $scope.addBottonLabel = generalService.getAddBottonLabel();
    $scope.editBottonLabel = generalService.getEditBottonLabel();
    /**
     * UI-Grid declaration
     */
    $scope.gridOptions = {
        enableFiltering: true,
        columnDefs: [
            //default
            {field:'networkId', visible:false, enableCellEdit:false},
            {field:'name'},
            {field:'description'},
            {field:'url'},
            {field:'lang', displayName:'Language'},
            {field:'phone'},
            {field:'fareUrl'},
            {field:'timeZone'}
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
    getAllNetworks();
    function getAllNetworks() {
        generalService.getAllRows(ALL_NETWORK_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
        });

    }

    /**
     * Reset platform form.
     */
    function resetNetworkForm() {
        $scope.network.name='';
        $scope.network.description='';
        $scope.network.url='';
        $scope.network.lang='';
        $scope.network.phone='';
        $scope.network.fareUrl='';
        $scope.network.timeZone='';

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
     * Adding new Network
     * @param networkObject
     */

    $scope.addNetworkRow = function(networkObject)
    {
        if (generalService.getAddBottonLabel() == 'Add') {
            generalService.setAddBottonLabel('Save')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            return;
        }
        if (generalService.getAddBottonLabel() =='Save') {

            networkObject.networkId = -1;
            generalService.addRow(networkObject, ADD_NETWORK_URI).then(function(response) {
                addResponse = response.data;
                if (addResponse.status == SUCCESS ) {
                    networkObject.networkId = addResponse.networkId;
                    $scope.gridOptions.data.push(angular.copy(networkObject));
                    $scope.scrollTo($scope.gridOptions.data.length-2,0);
                } else {
                    alert('Not able to add new network. ' + addResponse.message);
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
    $scope.editNetworkRow = function(networkObject)
    {
        if (generalService.getEditBottonLabel() == 'Edit') {
            $scope.network = angular.copy(generalService.getRow());
            generalService.setEditBottonLabel('Save')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
        if (generalService.getEditBottonLabel() == 'Save') {
            alert('networkId ='+ networkObject.networkId);
            generalService.editRow(networkObject,EDIT_NETWORK_URI).then(function(response) {
                serviceResponse = response.data;
                if (serviceResponse.status == SUCCESS ) {
                    selectedRow = generalService.getSelectedRow();
                    selectedRow.networkId = networkObject.networkId;
                    selectedRow.name = networkObject.name;
                    selectedRow.description = networkObject.description;
                    selectedRow.url = networkObject.url;
                    selectedRow.lang = networkObject.lang;
                    selectedRow.phone =networkObject.phone;
                    selectedRow.fareUrl =networkObject.fareUrl;
                    selectedRow.timeZone =networkObject.timeZone;
                    generalService.setRow(networkObject);
                } else {
                    alert('edit failed:'+serviceResponse.message);
                }
            });
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
    };

    $scope.deleteNetworkRow = function()
    {
        networkObject = generalService.getSelectedRow();
        if (!confirm('Are you sure you want to delete station: '+networkObject.name +'?')) {
            return;
        }
        generalService.deleteRow(networkObject.networkId,DEL_NETWORK_URI).then(function(response){
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS ) {
                rowIndex = $scope.gridOptions.data.indexOf(networkObject);
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

    $scope.cancelNetworkRow = function()
    {
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.setAddBottonLabel('Add')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
        }
        if (generalService.getEditBottonLabel() =='Save') {
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();

        }
        resetNetworkForm();
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

