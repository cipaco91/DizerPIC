'use strict';

app.factory("ProfileService", function ($http) {
    return {

        findProfileImage: function () {
            return $http.get('rest/profileImage');
        },
        profileSN: function () {
            return $http.get('rest/profileSN');
        },
        friendsProfile: function () {
            return $http.get('rest/friendsProfile');
        },
        photosProfile: function () {
            return $http.get('rest/photosProfile');
        }
    }
});