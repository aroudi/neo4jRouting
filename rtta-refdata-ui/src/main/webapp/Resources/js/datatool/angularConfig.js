myApp.config([ '$routeProvider', function ($routeProvider, $scope) {

        $routeProvider.when('/network', {
            templateUrl: 'RailNetwork/Network.html',
            controller: NetworkController
        });
        $routeProvider.when('/station', {
            templateUrl: 'RailNetwork/Station.html',
            controller: StationController
        });
        $routeProvider.when('/platform', {
            templateUrl: 'RailNetwork/Platform.html',
            controller: PlatformController
        });
    } ]);
