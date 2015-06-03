/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yesnault.sag;

import com.yesnault.sag.interceptors.PostToWallAfterConnectInterceptor;
import com.yesnault.sag.interceptors.TweetAfterConnectInterceptor;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Spring Social Configuration.
 * This configuration demonstrates the use of the simplified Spring Social configuration options from Spring Social 1.1.
 * 
 * @author Craig Walls
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Inject
	private DataSource dataSource;

	private boolean flag=true;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		if(flag) {
		    cfConfig.addConnectionFactory(new TwitterConnectionFactory("YR571S2JiVBOFyJS5MEg", "Kb8hS0luftwCJX3qVoyiLUMfZDtK1EozFoUkjNLUMx4"));
			FacebookConnectionFactory facebookConnectionFactory=new FacebookConnectionFactory("631469100286834", "41e4754bf5b580707709eb00168a329f", "dizerpicnew");
			facebookConnectionFactory.setScope("publish_actions,user_birthday, user_religion_politics, user_relationships, user_relationship_details, user_hometown, user_location, user_likes, user_education_history, user_work_history, user_website, user_groups, user_managed_groups, user_events, user_photos, user_videos, user_friends, user_about_me, user_status, user_games_activity, user_tagged_places, user_posts, read_stream, read_mailbox, email, read_custom_friendlists, user_actions.books, user_actions.music, user_actions.video, user_actions.news, user_actions.fitness, public_profile");
			cfConfig.addConnectionFactory(facebookConnectionFactory);
			LinkedInConnectionFactory linkedInConnectionFactory=new LinkedInConnectionFactory("77f94iq6kehtaz", "GHoKSQzyrnMRMliR");
			GoogleConnectionFactory googleConnectionFactory=new GoogleConnectionFactory("228405855270-fej1hiuj0vcc1nmb650o9nkprtn2bm4e.apps.googleusercontent.com","J7A5_WcixV7not4AHwnUoyrF");
//			LinkedInConnectionFactory linkedInConnectionFactory=new LinkedInConnectionFactory("754fvz2njoxqfm", "rolyKemRYmBXxlDJ");
//			linkedInConnectionFactory.setScope("r_fullprofile,r_basicprofile,rw_groups,w_share,r_network,rw_nus,r_emailaddress,rw_company_admin,w_messages");
			cfConfig.addConnectionFactory(linkedInConnectionFactory);
			googleConnectionFactory.setScope("https://www.googleapis.com/auth/plus.login");
//					"https://www.googleapis.com/auth/plus.stream.write," +
//					"https://www.googleapis.com/auth/plus.circles.read," +
//					"https://www.googleapis.com/auth/plus.circles.write,"+
//					"https://www.googleapis.com/auth/plus.stream.read," +
//					"https://www.googleapis.com/auth/plus.media.upload");
			cfConfig.addConnectionFactory(googleConnectionFactory);
			flag=false;
		}
	}
	
	@Override
	public UserIdSource getUserIdSource() {
		return new UserIdSource() {
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					return "ciprian";
//					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		UsersConnectionRepository usersConnectionRepository= new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		return usersConnectionRepository;
	}

	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Facebook facebook(ConnectionRepository repository) {
		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
		return connection != null ? connection.getApi() : null;
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Twitter twitter(ConnectionRepository repository) {
		Connection<Twitter> connection = repository.findPrimaryConnection(Twitter.class);
		return connection != null ? connection.getApi() : null;
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public LinkedIn linkedin(ConnectionRepository repository) {
		Connection<LinkedIn> connection = repository.findPrimaryConnection(LinkedIn.class);
		return connection != null ? connection.getApi() : null;
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Google google(ConnectionRepository repository) {
		Connection<Google> connection = repository.findPrimaryConnection(Google.class);
		return connection != null ? connection.getApi() : null;
	}
	
	//
	// Web Controller and Filter Beans
	//
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
		connectController.addInterceptor(new TweetAfterConnectInterceptor());
		return connectController;
	}

	@Bean
	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
		ProviderSignInController providerSignInController=new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(new HttpSessionRequestCache()));
		return providerSignInController;
	}

	
//	@Bean
//	public DisconnectController disconnectController(UsersConnectionRepository usersConnectionRepository, Environment env) {
//		return new DisconnectController(usersConnectionRepository, env.getProperty("facebook.appSecret"));
//	}

	@Bean
	public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
		return new ReconnectFilter(usersConnectionRepository, userIdSource);
	}

}
