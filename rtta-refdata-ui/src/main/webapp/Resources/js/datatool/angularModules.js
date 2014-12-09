var myApp = angular.module('rttaRefDataUi', ["ui.grid","ui.grid.selection","ui.grid.cellNav","ui.grid.autoResize","ui.grid.edit","ngRoute","loadDisplay"]);
var config_data = {
    'SERVER' : 'localhost',
    'PORT'   : '8082',
    'WEBAPP' :'rtta-refdata-tool'
}

var service_uri = {
    'ALL_STATION_URI' : 'stations/all',
    'ADD_STATION_URI' : 'stations/add',
    'EDIT_STATION_URI' : 'stations/edit',
    'DEL_STATION_URI' : 'stations/delete',
    'ALL_PLATFORM_URI' : 'platforms/all',
    'ADD_PLATFORM_URI' : 'platforms/add',
    'EDIT_PLATFORM_URI' : 'platforms/edit',
    'DEL_PLATFORM_URI' : 'platforms/delete',
    'GET_STATION_REF_URI' : 'stations/stationsRef'
}

var response_status = {
    'SUCCESS' : 1,
    'FAILURE' : 0
}

angular.forEach(config_data, function(key, value) {
    myApp.constant(value, key);
});

angular.forEach(service_uri, function(key, value) {
    myApp.constant(value, key);
});

angular.forEach(response_status, function(key, value) {
    myApp.constant(value, key);
});

myApp.service('configService', function (SERVER,PORT,WEBAPP) {
      var address = 'http://' + SERVER+':'+ PORT +'/' +WEBAPP + '/rest/'

    return {
        getAddress: function () {
            return address
        }
    };
});

myApp.service('generalService', function ($location, $http, $q, configService, loadDisplay) {

    var row;
    var selectedRow;
    var addBottonLabel ='Add' ;
    var editBottonLabel ='Edit' ;
    var rowSelected = false;

    return {

        isRowSelected: function () {
            return rowSelected;
        },
        setRowSelected: function (selected) {
            rowSelected = selected;
        },

        initBottons: function () {
            addBottonLabel = 'Add';
            editBottonLabel = 'Edit';
        },
        setAddBottonLabel: function (label) {
            return addBottonLabel=label;
        },
        getAddBottonLabel: function () {
            return addBottonLabel;
        },
        setEditBottonLabel: function (label) {
            return editBottonLabel=label;
        },
        getEditBottonLabel: function () {
            return editBottonLabel;
        },
        isAddBottonDisable: function () {
            if (editBottonLabel == 'Save') {
                return true;
            }
            return false;
        },
        isEditBottonDisable: function () {
            if (addBottonLabel == 'Save') {
                return true;
            }
            if(this.isRowSelected()) {
                return false;
            }
            else
                return true;
        },
        isCancelBottonDisable: function () {
            return this.formFieldDisabled()
        },
        formFieldDisabled: function () {
            if (addBottonLabel == 'Save' || editBottonLabel == 'Save') {
                return false;
            }
            return true;
        },
        setRow: function (myRow) {
            row = angular.copy(myRow);
        },
        getRow: function () {
            return row;
        },
        setSelectedRow: function (rowStation) {
            selectedRow = rowStation;
        },
        getSelectedRow: function () {
            return selectedRow;
        },
        getAllRows: function (allRowUri) {
            serviceUrl = configService.getAddress() + allRowUri;
            var div = $q.defer();
            loadDisplay.addDisplay(div.promise, "Please Wait ...");
            var promise = $http({
                url: serviceUrl ,
                method: 'GET'
            }).success(function (data) {
                div.resolve();
                return data;
            }).error(function (data) {
                div.resolve();
            });
            return promise;
        },
        addRow: function (rowObject, addRowUri) {
            serviceUrl = configService.getAddress() + addRowUri;
            var div = $q.defer();
            loadDisplay.addDisplay(div.promise, "Please Wait ...");
            var promise = $http({
                url: serviceUrl ,
                method: 'POST',
                data : rowObject,
                headers : {
                    "Content-Type" : "application/json; charset=utf-8",
                    "Accept" : "application/json"
                }
            }).success(function (data) {
                div.resolve();
                return data;
            }).error(function (data) {
                div.resolve();
            });
            return promise;
        },
        editRow: function (rowObject, editRowUri) {
            serviceUrl = configService.getAddress() + editRowUri;
            var div = $q.defer();
            loadDisplay.addDisplay(div.promise, "Please Wait ...");
            var promise = $http({
                url: serviceUrl ,
                method: 'POST',
                data : rowObject,
                headers : {
                    "Content-Type" : "application/json; charset=utf-8",
                    "Accept" : "application/json"
                }
            }).success(function (data) {
                div.resolve();
                return data;
            }).error(function (data) {
                div.resolve();
            });
            return promise;
        },
        deleteRow: function (rowId, delRowUri) {
            serviceUrl = configService.getAddress() + delRowUri + '/' + rowId;
            var div = $q.defer();
            loadDisplay.addDisplay(div.promise, "Please Wait ...");
            var promise = $http({
                url: serviceUrl ,
                method: 'GET'
            }).success(function (data) {
                div.resolve();
                return data;
            }).error(function (data) {
                div.resolve();
            });
            return promise;
        },
        populateSelectList: function (refDataId, sourceData) {
            selectedItem = sourceData[0];
            if (refDataId==undefined )
                return selectedItem;
            arr=sourceData;
            if (angular.isArray(arr)) {
                for (var i = 0; i < arr.length; i++) {
                    if ( arr[i].refDataId==refDataId  ) {
                        return arr[i];
                    }
                }
            }
            return selectedItem
        }


    }
});

