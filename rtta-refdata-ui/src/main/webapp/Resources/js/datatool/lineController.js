function NetworkLineController($scope, generalService, SUCCESS, FAILURE, ALL_LINE_URI, ADD_LINE_URI, EDIT_LINE_URI, DEL_LINE_URI, GET_NETWORK_REF_URI , GET_SERVICETYPE_REF_URI) {

    $scope.line = {};
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
            {field:'lineId', visible:false, enableCellEdit:false},
            {field:'networkName', enableCellEdit:false},
            {field:'name', enableCellEdit:false},
            {field:'longName', enableCellEdit:false},
            {field:'backgroundColourHex',
                cellClass: '{font-weight:bold; text-align: left; color:#B40404;}'
                                    /*
                    function(grid, row, col, rowRenderIndex, colRenderIndex) {
                  return '{background-color:red;}'
                } */, displayName:'Background Colour', enableCellEdit:false},
            {field:'textColourHex', displayName:'Text Colour', enableCellEdit:false},
            {field:'serviceTypeName', displayName:'Service Type', enableCellEdit:false}
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
     * retreive platform list from server
     */
    getAllLines();
    getNetworkRef();
    getServiceTypeRef();

    function getAllLines() {
        generalService.getAllRows(ALL_LINE_URI).then(function(response){
            $scope.gridOptions.data = angular.copy(response.data);
        });

    }
    function getNetworkRef() {
        generalService.getAllRows(GET_NETWORK_REF_URI).then(function(response){
            $scope.networkSet = response.data;
            $scope.line.network = $scope.networkSet[0];
        });

    };
    function getServiceTypeRef() {
        generalService.getAllRows(GET_SERVICETYPE_REF_URI).then(function(response){
            $scope.serviceTypeSet = response.data;
            $scope.line.serviceType = $scope.serviceTypeSet[0];
        });

    }

    /**
     * Reset line form.
     */
    function resetLineForm() {
        $scope.line.name='';
        $scope.line.longName='';
        $scope.line.backgroundColourHex='';
        $scope.line.textColourHex='';

    };

    /**
     * Populate line fields from selected row.
     */

    function populateFormField(row) {

        generalService.setRow(row.entity);
        generalService.setSelectedRow(row.entity);
        generalService.setRowSelected(true);
    }

    /**
     * Adding new line
     * @param lineObject
     */

    $scope.addLineRow = function(lineObject)
    {
        if (generalService.getAddBottonLabel() == 'Add') {
            generalService.setAddBottonLabel('Save')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
            defaultServiceType= generalService.populateSelectListPerName(generalService.getRow().serviceTypeName,$scope.serviceTypeSet);
            defaultNetwork= generalService.populateSelectListPerName(generalService.getRow().networkName,$scope.networkSet);
            $scope.line.serviceType = defaultServiceType;
            $scope.line.network = defaultNetwork;
            return;
        }
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.addRow(lineObject, ADD_LINE_URI).then(function(response) {
                addResponse = response.data;
                if (addResponse.status == SUCCESS ) {
                    lineRow = {
                        "lineId" : addResponse.lineId,
                        "networkName" : lineObject.network.refDataCode,
                        "name" : lineObject.name,
                        "longName" : lineObject.longName,
                        "backgroundColourHex" : lineObject.backgroundColourHex,
                        "textColourHex" : lineObject.textColourHex,
                        "serviceTypeName" : lineObject.serviceType.refDataCode
                    }
                    $scope.gridOptions.data.push(lineRow);
                    $scope.scrollTo($scope.gridOptions.data.length-2,0);
                } else {
                    alert('Not able to add new line. '+addResponse.message);
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
    $scope.editLineRow = function()
    {
        if (generalService.getEditBottonLabel() == 'Edit') {
            rowEntity = angular.copy(generalService.getRow());

            $scope.line.lineId = rowEntity.lineId;
            $scope.line.name = rowEntity.name;
            $scope.line.longName = rowEntity.longName;
            $scope.line.backgroundColourHex = rowEntity.backgroundColourHex;
            $scope.line.textColourHex = rowEntity.textColourHex;
            defaultServiceType= generalService.populateSelectListPerName(generalService.getRow().serviceTypeName,$scope.serviceTypeSet);
            defaultNetwork= generalService.populateSelectListPerName(generalService.getRow().networkName,$scope.networkSet);
            $scope.line.serviceType = defaultServiceType;
            $scope.line.network = defaultNetwork;
            generalService.setEditBottonLabel('Save')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
        if (generalService.getEditBottonLabel() == 'Save') {
            lineObject = $scope.line;
            generalService.editRow(lineObject,EDIT_LINE_URI).then(function(response) {
                serviceResponse = response.data;
                if (serviceResponse.status == SUCCESS ) {
                    selectedRow = generalService.getSelectedRow();
                    selectedRow.name = lineObject.name;
                    selectedRow.longName = lineObject.longName;
                    selectedRow.backgroundColourHex = lineObject.backgroundColourHex;
                    selectedRow.textColourHex = lineObject.textColourHex;
                    selectedRow.networkName = lineObject.network.refDataCode;
                    selectedRow.serviceTypeName = lineObject.serviceType.refDataCode
                    generalService.setRow(lineObject);
                } else {
                    alert('edit failed:'+serviceResponse.message);
                }
            });
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();
            return;
        }
    };

    $scope.deleteLineRow = function()
    {
        lineObject = generalService.getSelectedRow();
        if (!confirm('Are you sure you want to delete line: '+lineObject.longName +'?')) {
            return;
        }
        generalService.deleteRow(lineObject.lineId,DEL_LINE_URI).then(function(response){
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS ) {
                rowIndex = $scope.gridOptions.data.indexOf(lineObject);
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

    $scope.cancelLineRow = function()
    {
        if (generalService.getAddBottonLabel() =='Save') {
            generalService.setAddBottonLabel('Add')
            $scope.addBottonLabel = generalService.getAddBottonLabel();
        }
        if (generalService.getEditBottonLabel() =='Save') {
            generalService.setEditBottonLabel('Edit')
            $scope.editBottonLabel = generalService.getEditBottonLabel();

        }
        resetLineForm();
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

