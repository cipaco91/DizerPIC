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
        },
        photosFromAlbum: function (albumId) {
            return $http.get('rest/photosFromAlbum/'+albumId);
        },
        getWizzardDTO: function () {
            return $http.get('rest/wizzardDTO');
        },
        getCommentFeed: function () {
            return $http.get('rest/commentFeed');
        },
        finishWizzardProfile: function (entity) {
            return $http.post('rest/finishWizzardProfile',entity);
        },
        isLoginActive: function () {
            return $http.get('rest/isLoginActive');
        },
        verifyContainsString: function (s1,s2) {
            return $http.get('rest/verifyContainsString/'+s1+"/"+s2);
        }
    }
});