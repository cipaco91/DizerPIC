'use strict';

app.factory("MenuService", function ($http) {
    return {

        isConnectFacebook: function () {
            return $http.get('rest/isConnectFacebook');
        },
        isConnectTwittter: function () {
            return $http.get('rest/isConnectTwitter');
        },
        isConnectLinkedin: function () {
            return $http.get('rest/isConnectLinkedin');
        },
        isConnectLinkedinMenu: function () {
            return $http.get('rest/isConnectLinkedinMenu');
        },
        isConnectGoogle: function () {
            return $http.get('rest/isConnectGoogle');
        }
    }
});