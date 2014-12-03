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
    'DEL_STATION_URI' : 'stations/delete'
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

myApp.service('generalService', function () {
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
        }
    }
});

myApp.service('stationService', function ($location, $http, $q, configService, ALL_STATION_URI, ADD_STATION_URI,DEL_STATION_URI, EDIT_STATION_URI, SUCCESS, FAILURE, loadDisplay) {

    var station;
    var selectedRow;
    return {

        setStation: function (rowStation) {
            station = angular.copy(rowStation);
        },
        getStation: function () {
            return station;
        },
        setSelectedRow: function (rowStation) {
            selectedRow = rowStation;
        },
        getSelectedRow: function () {
            return selectedRow;
        },
        getAllStations: function () {
            serviceUrl = configService.getAddress() + ALL_STATION_URI;
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
        addStation: function (stationObject) {
            serviceUrl = configService.getAddress() + ADD_STATION_URI;
            var div = $q.defer();
            loadDisplay.addDisplay(div.promise, "Please Wait ...");
            var promise = $http({
                url: serviceUrl ,
                method: 'POST',
                data : stationObject,
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
        editStation: function (stationObject) {
            serviceUrl = configService.getAddress() + EDIT_STATION_URI;
            var div = $q.defer();
            loadDisplay.addDisplay(div.promise, "Please Wait ...");
            var promise = $http({
                url: serviceUrl ,
                method: 'POST',
                data : stationObject,
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
        deleteStation: function (stationId) {
            serviceUrl = configService.getAddress() + DEL_STATION_URI + '/' + stationId;
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
        }


    }

});
