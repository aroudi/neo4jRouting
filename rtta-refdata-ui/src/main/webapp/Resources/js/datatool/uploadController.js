/**
 * Created by arash on 16/12/2014.
 */
function UploadController($scope, generalService, fileUploadService,singleFileUploadService, SUCCESS, FAILURE, UPLOAD_STOPS_URI, UPLOAD_TOPOLOGY_URI, UPLOAD_NODES_URI, UPLOAD_NODAL_URI) {

    generalService.setChosenMenuItem('uploadRefData');
    $scope.fileName ='Stops'
    $scope.fileSet = [
        'Stops' ,
        'Nodes' ,
        'Topology',
        'Nodal Geography'
    ];


    $scope.onFileSelect = function(files) {

        switch ($scope.fileName){

            case 'Stops':
                uploadUrl = UPLOAD_STOPS_URI;
                break;
            case 'Nodes':
                uploadUrl = UPLOAD_NODES_URI;
                break;
            case 'Topology':
                uploadUrl = UPLOAD_TOPOLOGY_URI;
                break;
            case 'Nodal Geography':
                uploadUrl = UPLOAD_NODAL_URI;
                break;
            default :
                uploadUrl = UPLOAD_STOPS_URI;
                break;
        }
        fileUploadService.uploadFileToUrl(files, uploadUrl).then(function (response) {
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS) {
            } else {
            }
        });
    };
    /*
    $scope.$watch('files', function() {
        alert('in watch')
        fileUploadService.uploadFileToUrl($scope.files, UPLOAD_STOPS_URI).then(function (response) {
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS) {
                alert('file uploaded successfully')
            } else {
                alert('not able to upload file: ' + serviceResponse.message);
            }
        });
    });
    */
    $scope.uploadFile = function(fileName) {
        var file1 = $scope.myFile;
        singleFileUploadService.uploadFileToUrl(file1, UPLOAD_STOPS_URI).then(function (response) {
            serviceResponse = response.data;
            if (serviceResponse.status == SUCCESS) {
            } else {
            }
        });
    };
}
