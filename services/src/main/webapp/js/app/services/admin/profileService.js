'use strict';

app.factory("ProfileService", function ($http) {
    return {

        findProfileImage: function () {
            return $http.get('rest/profileImage');
        },
        findProfileFacebook: function () {
            return $http.get('rest/profileFacebook');
        },
        friendsProfile: function () {
            return $http.get('rest/friendsProfile');
        },
        photosProfile: function () {
            return $http.get('rest/photosProfile');
        }
    }
});