var myApp = angular.module('rttaRefDataUi', ["stationFormatter","ngAnimate","ui.grid","ui.grid.selection","ui.grid.cellNav","ui.grid.autoResize","ui.grid.edit","ui.grid.resizeColumns","ngRoute","loadDisplay","angularFileUpload","ngVis"]);
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
    'GET_STATION_REF_URI' : 'stations/stationsRef',
    'ALL_NETWORK_URI' : 'networks/all',
    'ADD_NETWORK_URI' : 'networks/add',
    'EDIT_NETWORK_URI' : 'networks/edit',
    'DEL_NETWORK_URI' : 'networks/delete',
    'ALL_LINE_URI' : 'networkLines/all',
    'ADD_LINE_URI' : 'networkLines/add',
    'EDIT_LINE_URI' : 'networkLines/edit',
    'DEL_LINE_URI' : 'networkLines/delete',
    'GET_SERVICETYPE_REF_URI' :'refDatas/serviceTypesRef',
    'GET_NETWORK_REF_URI' : 'refDatas/networksRef',
    'UPLOAD_STOPS_URI' : 'upload/stops',
    'UPLOAD_TOPOLOGY_URI' : 'upload/topology',
    'UPLOAD_NODES_URI' : 'upload/nodes',
    'UPLOAD_NODAL_URI' : 'upload/nodalGeography',
    'UPLOAD_LOCATIONS_URI' : 'upload/locations',
    'ALL_NODE_URI' : 'nodes/all',
    'ADD_NODE_URI' : 'nodes/add',
    'EDIT_NODE_URI' : 'nodes/edit',
    'DEL_NODE_URI' : 'nodes/delete',
    'GET_LINE_REF_URI' : 'refDatas/linesRef',
    'ALL_PATH_URI' : 'linePathStations/all',
    'NETWORK_CSV_URI' : 'export/networkToCsv',
    'LINE_CSV_URI' : 'export/networkLineToCsv',
    'PATH_CSV_URI' : 'export/linePathsToCsv',
    'STATION_CSV_URI' : 'export/stationsToCsv',
    'PLATFORM_CSV_URI' : 'export/platformsToCsv',
    'NODE_CSV_URI' : 'export/nodesToCsv',
    'LOCATION_CSV_URI' : 'export/locationsToCsv',
    'TRIPLET_CSV_URI' : 'export/tripletsToCsv',
    'NODAL_XML_URI' : 'export/nodalGeography/xml',
    'STOPS_XML_URI' : 'export/stops/xml',
    'NODES_XML_URI' : 'export/nodes/xml',
    'TOPOLOGY_XML_URI' : 'export/gtfsTopology/xml',
    'ALL_LOCATION_URI' : 'locations/all',
    'ADD_LOCATION_URI' : 'locations/add',
    'EDIT_LOCATION_URI' : 'locations/edit',
    'DEL_LOCATION_URI' : 'locations/delete',
    'VIS_NETWORK_URI' : 'networks/visualize',
    'TRAVERSAL_URI' : 'nodes/traverse',
    'ALL_VERSION_URI' : 'versions/all',
    'ADD_VERSION_URI' : 'versions/add',
    'EDIT_VERSION_URI' : 'versions/edit',
    'DEL_VERSION_URI' : 'versions/delete',
    'SET_WORKING_VERSION_URI' : 'versions/setWorkingVersion'
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
    var chosenMenuItem = 'network';


    return {

        getChosenMenuItem: function () {
            return chosenMenuItem;
        },
        setChosenMenuItem: function (value) {
            chosenMenuItem = value;
        },
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
        refreshRows: function (allRowUri) {
            serviceUrl = configService.getAddress() + allRowUri;
            var promise = $http({
                url: serviceUrl ,
                method: 'GET'
            }).success(function (data) {
                return data;
            }).error(function (data) {
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
        },
        populateBaseVersion: function (refDataId, sourceData) {
            selectedItem = sourceData[0];
            if (refDataId==undefined )
                return selectedItem;
            arr=sourceData;
            if (angular.isArray(arr)) {
                for (var i = 0; i < arr.length; i++) {
                    if ( arr[i].baseVersion==refDataId  ) {
                        return arr[i];
                    }
                }
            }
            return selectedItem
        },
        populateActiveVersion: function (sourceData) {
            selectedItem = sourceData[0];
            arr=sourceData;
            if (angular.isArray(arr)) {
                for (var i = 0; i < arr.length; i++) {
                    if ( arr[i].active==true  ) {
                        return arr[i];
                    }
                }
            }
            return selectedItem
        },
        populateSelectListPerName: function (refDataName, sourceData) {
            selectedItem = sourceData[0];
            if (refDataName==undefined )
                return selectedItem;
            arr=sourceData;
            if (angular.isArray(arr)) {
                for (var i = 0; i < arr.length; i++) {
                    if ( arr[i].refDataCode==refDataName  ) {
                        return arr[i];
                    }
                }
            }
            return selectedItem
        }



    }
});

myApp.service('singleFileUploadService', function ($location, $http, $q, configService, loadDisplay) {
    return {
        uploadFileToUrl : function (file1, uploadUrl) {
            serviceUrl = configService.getAddress() + uploadUrl;
            var fd = new FormData();
            fd.append('file', file1);
            var div = $q.defer();
            loadDisplay.addDisplay(div.promise, "Uploading file ...");
            var promise = $http.post(serviceUrl,fd,{
                trnsformRequest:angular.identity,
                headers: {'Content-Type': multipart/form-data}
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


myApp.service('fileUploadService', function ($location, $upload, $http, $q, configService, loadDisplay) {
    return {
        uploadFileToUrl : function (files, uploadUrl) {
            serviceUrl = configService.getAddress() + uploadUrl;
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                var div = $q.defer();
                loadDisplay.addDisplay(div.promise, "Uploading file ...");
                $upload.upload({
                    url: serviceUrl, // upload.php script, node.js route, or servlet url
                    method: 'POST',
                    //headers: {'Authorization': 'xxx'}, // only for html5
                    //withCredentials: true,
                    //data: {myObj: $scope.myModelObj},
                    file: file // single file or a list of files. list is only for html5
                    //fileName: 'doc.jpg' or ['1.jpg', '2.jpg', ...] // to modify the name of the file(s)
                    //fileFormDataName: myFile, // file formData name ('Content-Disposition'), server side request form name
                    // could be a list of names for multiple files (html5). Default is 'file'
                    //formDataAppender: function(formData, key, val){}  // customize how data is added to the formData.
                    // See #40#issuecomment-28612000 for sample code

                }).progress(function(evt) {
                    console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total) + '% file :'+ evt.config.file.name);
                }).success(function(data, status, headers, config) {
                    // file is uploaded successfully
                    console.log('file ' + config.file.name + 'is uploaded successfully. Response: ' + data);
                    div.resolve();
                    return data;
                })
                //.error(...)
                //.then(success, error, progress); // returns a promise that does NOT have progress/abort/xhr functions
                //.xhr(function(xhr){xhr.upload.addEventListener(...)}) // access or attach event listeners to
                //the underlying XMLHttpRequest
                .error(function (data) {
                        div.resolve();
                });
                //return promise;
            }
        }
    }
});

myApp.service('drawNetworkService', function ($q, loadDisplay  ) {
    var networkData;
    var networkOptions;
    var myNodes ;
    var myEdges ;
    return {
        getNetworkData: function () {
            return networkData;
        },
        getNetworkOptions: function () {
            return networkOptions;
        },

        drawAllNetwork: function (visNetworkData) {

            if (visNetworkData == undefined) {
                return;
            }
            myNodes = new vis.DataSet();
            myEdges = new vis.DataSet();
            networkData = {
                nodes: myNodes,
                edges: myEdges
            };
            networkOptions = {
                hierarchicalLoyout: {
                    direction :"UD"
                },
                edges : {
                    color: '#00205b',
                    width :2,
                    style:'arrow'
                },
                nodes : {
                    color : {
                        background : 'white',
                        border : '#00205b',
                        width :2,
                        highlight : {
                            background: '#a6192e',
                            border : '#00205b',
                            fontFill :'white'
                        }
                    },
                    fontSize : 20,
                    fontColor:'#00205b',
                    radius: 14
                }
            };
            nodes = visNetworkData.nodes;
            for (var i = 0; i < nodes.length; i++) {
                myTitle = nodes[i].longName + "(Latt: " + nodes[i].latitude + ", Long: " + nodes[i].longtitude + ")";
                myNodes.add([{id:nodes[i].stationId, label:nodes[i].longName , title: myTitle} ])
            }
            edges = visNetworkData.arcs;
            for (var i = 0; i < edges.length; i++) {
                myEdges.add([
                    {id:i, from:edges[i].fromNode, to:edges[i].toNode}
                ]);
            }
        },

        setNetworkData : function (nodeData, backgroundColour, textColour) {
            if (nodeData == undefined )
                return;

            myNodes = new vis.DataSet();
            myEdges = new vis.DataSet();
            networkData = {
                nodes: myNodes,
                edges: myEdges
            };
            networkOptions = {
                hierarchicalLoyout: {
                    direction :"UD"
                },
                edges : {
                    color: '#00205b',
                    width :2,
                    style:'arrow'
                },
                nodes : {
                    color : {
                        background : 'white',
                        border : '#00205b',
                        width :2,
                        highlight : {
                            background: '#a6192e',
                            border : '#00205b',
                            fontFill :'white'
                        }
                    },
                    fontSize : 20,
                    fontColor:'#00205b',
                    radius: 14
                }
            }
            nodeSetLength = nodeData.length;
            for (var i = 0; i < nodeSetLength; i++) {
                myTitle = nodeData[i].longName + "(Latt: " + nodeData[i].latitude + ", Long: " + nodeData[i].longtitude + ")"
                var sequence;
                if (nodeData[i].sequence != undefined) {
                    sequence = nodeData[i].sequence
                } else if (nodeData[i].nodeId != undefined) {
                    sequence = nodeData[i].nodeId
                }
                myNodes.add([{id:sequence, label:nodeData[i].longName , title: myTitle} ])
            }
            for (var i = 0; i < nodeSetLength-2; i++) {
                var sequence1, sequence2;
                if (nodeData[i].sequence != undefined) {
                    sequence1 = nodeData[i].sequence
                    sequence2 = nodeData[i+1].sequence
                } else if (nodeData[i].nodeId != undefined) {
                    sequence1 = nodeData[i].nodeId
                    sequence2 = nodeData[i+1].nodeId
                }
                myEdges.add([
                    {id:i+1, from:sequence1, to:sequence2}
                ]);
            }
            myEdges.add([
                {id:nodeSetLength, from:nodeData[nodeSetLength-2].sequence, to:nodeData[nodeSetLength-1].sequence}
            ]);
        },
        drawPath : function (nodeData) {
            if (nodeData == undefined )
                return;

            myNodes = new vis.DataSet();
            myEdges = new vis.DataSet();
            networkData = {
                nodes: myNodes,
                edges: myEdges
            };
            networkOptions = {
                hierarchicalLoyout: {
                    direction :"UD"
                },
                edges : {
                    color: '#00205b',
                    width :2,
                    style:'arrow'
                },
                nodes : {
                    color : {
                        background : 'white',
                        border : '#00205b',
                        width :2,
                        highlight : {
                            background: '#a6192e',
                            border : '#00205b',
                            fontFill :'white'
                        }
                    },
                    fontSize : 20,
                    fontColor:'#00205b',
                    radius: 14
                }
            }
            nodeSetLength = nodeData.length;
            for (var i = 0; i < nodeSetLength; i++) {
                myTitle = nodeData[i].longName + "(Latt: " + nodeData[i].latitude + ", Long: " + nodeData[i].longtitude + ")"
                myNodes.add([{id:nodeData[i].nodeId, label:nodeData[i].name , title: myTitle} ])
            }
            for (var i = 0; i < nodeSetLength-2; i++) {
                myEdges.add([
                    {id:i+1, from:nodeData[i].nodeId, to:nodeData[i+1].nodeId}
                ]);
            }
            myEdges.add([
                {id:nodeSetLength, from:nodeData[nodeSetLength-2].nodeId, to:nodeData[nodeSetLength-1].nodeId}
            ]);
        }
    }
});



myApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

angular.module('stationFormatter',[]).filter('linePathStationList', function() {
    return function (stations) {
        var arr = stations;
        var arrayContent = '';
        for (var j = 0; j < arr.length-1; j++) {
            arrayContent = arrayContent + arr[j].name +' -> ';
        }
        if (arr.length>0) {
            arrayContent = arrayContent + arr[arr.length - 1].name;
        }
        return arrayContent;

    };
});
