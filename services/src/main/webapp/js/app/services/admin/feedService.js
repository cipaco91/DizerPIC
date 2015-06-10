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
        unlike: function (id) {
            return $http.post('rest/unlike/'+id);
        },
        postSocialNetwork: function (facebookFlag,twitterFlag,linkedinFlag,googleFlag,postText) {
            return $http.get('rest/postStatus/'+facebookFlag+"/"+twitterFlag+"/"+linkedinFlag+"/"+googleFlag+"/"+postText);
        },
        refreshFeeds: function (socialType) {
            return $http.get('rest/refreshFeeds/'+socialType);
        },
        favoritesTweets: function () {
            return $http.get('rest/favoritesTweets');
        },
        retweet: function (tweetId) {
            return $http.get('rest/retweet/'+tweetId);
        },
        addTweetAtFavorites: function (tweetId) {
            return $http.post('rest/addTweetAtFavorites/'+tweetId);
        },
        removeFromFavorites: function (tweetId) {
            return $http.post('rest/removeFromFavorites/'+tweetId);
        }
    }
});