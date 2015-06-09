'use strict';

app.factory("FriendsService", function ($http) {
    return {

        findFriendsFacebook: function (name,age1,age2) {
            return $http.get('rest/friendsFacebook/'+name+"/"+age1+"/"+age2);
        },
        findFriendsTwitter: function (name,age1,age2) {
            return $http.get('rest/friendsTwitter/'+name+"/"+age1+"/"+age2);
        },
        findFriendsLinkedin: function (name,age1,age2) {
            return $http.get('rest/friendsLinkedin/'+name+"/"+age1+"/"+age2);
        },
        findFriendsGoogle: function (name,age1,age2) {
            return $http.get('rest/friendsGoogle/'+name+"/"+age1+"/"+age2);
        },
        searchUsers: function (entity) {
            return $http.post('rest/searchUsers',entity);
        },
        searchUsersDTO: function () {
            return $http.get('rest/userDTO');
        },
        albumsProfile: function () {
            return $http.get('rest/albumsFacebook');
        }
    }
});