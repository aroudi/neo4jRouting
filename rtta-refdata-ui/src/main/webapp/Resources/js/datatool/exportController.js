/**
 * Created by arash on 19/01/2015.
 */
    function ExportController($scope, generalService, SUCCESS, FAILURE, NETWORK_CSV_URI, LINE_CSV_URI, PATH_CSV_URI, STATION_CSV_URI, PLATFORM_CSV_URI, NODE_CSV_URI, NODAL_XML_URI, TRIPLET_CSV_URI, STOPS_XML_URI, NODES_XML_URI) {
    generalService.setChosenMenuItem('downloadRefData');
    $scope.exportToCsv = function(fileName)
    {
        fileFormat='csv';
        switch (fileName){

            case 'network':
                downloadUrl = NETWORK_CSV_URI;
                outPutFile ='Network.csv'
                break;
            case 'line':
                downloadUrl = LINE_CSV_URI;
                outPutFile ='NetworkLine.csv'
                break;
            case 'path':
                downloadUrl = PATH_CSV_URI
                outPutFile ='NetworkLinesPaths.csv'
                break;
            case 'station':
                downloadUrl = STATION_CSV_URI
                outPutFile ='STATION_DATA.csv'
                break;
            case 'stopLink':
                downloadUrl = TRIPLET_CSV_URI
                outPutFile ='StopLinks.csv'
                break;
            case 'platform':
                downloadUrl = PLATFORM_CSV_URI
                outPutFile ='PLATFORM_DATA.csv'
                break;
            case 'node':
                downloadUrl = NODE_CSV_URI
                outPutFile ='NODE_DATA.csv'
                break;
            case 'nodalGeography':
                downloadUrl = NODAL_XML_URI
                outPutFile ='NodalGeography.xml'
                fileFormat='xml'
                break;
            case 'stops':
                downloadUrl = STOPS_XML_URI
                outPutFile ='RttaStops.xml'
                fileFormat='xml'
                break;
            case 'nodesXml':
                downloadUrl = NODES_XML_URI
                outPutFile ='RttaNodes.xml'
                fileFormat='xml'
                break;
            default :
                downloadUrl = NETWORK_CSV_URI;
                outPutFile ='Network.csv'
                break;
        }
        var hiddenElement = document.createElement('a');
        generalService.getAllRows(downloadUrl).then(function(response){
            var blob = new Blob([response.data], {'type': 'application/octet-stream'});
            //saveAs (blob , outPutFile);
            hiddenElement.href = window.URL.createObjectURL(blob);//'data:attachment/'+fileFormat+',' + encodeURI(response.data);
            //hiddenElement.target ='_blank';
            hiddenElement.download = outPutFile;
            hiddenElement.click();
        });
    };

}