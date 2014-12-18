myApp.config([ '$routeProvider', function ($routeProvider, $scope) {

        $routeProvider.when('/network', {
            templateUrl: 'RailNetwork/Network.html',
            controller: NetworkController
        });
        $routeProvider.when('/node', {
            templateUrl: 'RailNetwork/Node.html',
            controller: NodeController
        });
        $routeProvider.when('/line', {
            templateUrl: 'RailNetwork/NetworkLine.html',
            controller: NetworkLineController
        });
        $routeProvider.when('/station', {
            templateUrl: 'RailNetwork/Station.html',
            controller: StationController
        });
        $routeProvider.when('/platform', {
            templateUrl: 'RailNetwork/Platform.html',
            controller: PlatformController
        });
        $routeProvider.when('/uploadStops', {
            templateUrl: 'RailNetwork/FileUpload.html',
            controller: UploadController
        });
    } ]);
