'use strict';

app.factory("FeedService", function ($http) {
    return {

        findFeedFacebook: function () {
            return $http.get('rest/feedsSocialNetwork');
        },
        findFeedTwitter: function () {
            return $http.get('rest/feedsTwitter');
        },
        findFeedLinkedin: function () {
            return $http.get('rest/feedsLinkedin');
        },
        addComment: function (id,message) {
            return $http.post('rest/addComment/'+id+"/"+message);
        },
        addLike: function (id) {
            return $http.post('rest/addLike/'+id);
        },
        postSocialNetwork: function (facebookFlag,twitterFlag,linkedinFlag,googleFlag,postText) {
            return $http.post('rest/postStatus/'+facebookFlag+"/"+twitterFlag+"/"+linkedinFlag+"/"+googleFlag,postText);
        }
    }
});