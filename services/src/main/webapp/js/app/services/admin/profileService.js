'use strict';

app.factory("ProfileService", function ($http) {
    return {

        findProfileImageFacebook: function () {
            return $http.get('rest/profileImageFacebook');
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