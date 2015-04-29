-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 29 Apr 2015 la 14:08
-- Versiune server: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dizerpic`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `PROFILE_CONFIGURE` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `active`, `PROFILE_CONFIGURE`) VALUES
(2, 'Ciprian', 'Paraschivescu', 'admin', '1', 1, 1);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `userconnection`
--

CREATE TABLE IF NOT EXISTS `userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `userconnection`
--

INSERT INTO `userconnection` (`userId`, `providerId`, `providerUserId`, `rank`, `displayName`, `profileUrl`, `imageUrl`, `accessToken`, `secret`, `refreshToken`, `expireTime`) VALUES
('ciprian', 'facebook', '1065655326783317', 1, NULL, 'http://facebook.com/profile.php?id=1065655326783317', 'http://graph.facebook.com/v1.0/1065655326783317/picture', 'CAAIZBUVtow3IBAKqBXWvIREB0Lu6TgbQHZANvLgxwl3XDZCyUG5oObZC1ZBoqtnFZC5hlQdBQ2Y84mLC8HOYLPlC6KskNd9UnarUUzPJwZARLGVBF1rnRObyOy0VABzJ4cnlwwfFvLigUcsnnqfnnbbFsbIwTWEGkqIlwCDGLS02ZAt6AbIJzvLyoZBmDGbOg7TAHGDW71BxyDNNENLwhOCnf', NULL, NULL, 1434204836930),
('ciprian', 'linkedin', 'c7zTqvDqdd', 1, 'Ciprian Paraschivescu', 'https://www.linkedin.com/pub/ciprian-paraschivescu/63/93a/1a', 'https://media.licdn.com/mpr/mprx/0_5P24iIt1WydUyIb4LzjIieitIxuVKe94FN4bie3aqsZI723ZdvpqSHvhLA2X17cqkKa62fxrUMXP', 'AQVVFc4y75PpEiLjOzean3iuyb0iHlsQpfxdp9dtrBYr7-nLVdBv714OfWDpFEV868sRkgCho-Jt9QXc6gMSBW6v0SWg7rl7qlrD5PlwdTGtmjJ9GTiYxOoBv5ODBtTIG2cZecXcXh5Mqve_kNEZy238oc_5urETdwqEMH42WoYl8gxf5Co', NULL, NULL, 1434204862498),
('ciprian', 'twitter', '2328423380', 1, '@CiprianCi', 'http://twitter.com/CiprianCi', 'http://pbs.twimg.com/profile_images/585375832023007235/x0SXsC30_normal.jpg', '2328423380-dme23l6LuXOrwxyocdSVF1ZysIooT0xWiVkBzeq', 'c4DxQpepAu6CPx8nwUIGGXXOtlVJ5hkMBSvVih1FpPQ3V', NULL, NULL);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `user_profile`
--

CREATE TABLE IF NOT EXISTS `user_profile` (
`id` int(11) NOT NULL,
  `FK_USER_ID` int(11) DEFAULT NULL,
  `FROM_PROFILE_IMAGE` varchar(20) DEFAULT NULL,
  `FROM_PROFILE_ABOUT` varchar(20) DEFAULT NULL,
  `FROM_PROFILE_FRIENDS` varchar(20) DEFAULT NULL,
  `FROM_PROFILE_NAME` varchar(20) DEFAULT NULL,
  `FROM_PROFILE_COVER` varchar(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `user_profile`
--

INSERT INTO `user_profile` (`id`, `FK_USER_ID`, `FROM_PROFILE_IMAGE`, `FROM_PROFILE_ABOUT`, `FROM_PROFILE_FRIENDS`, `FROM_PROFILE_NAME`, `FROM_PROFILE_COVER`) VALUES
(1, 2, 'facebook', 'facebook', 'facebook', 'facebook', 'facebook');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userconnection`
--
ALTER TABLE `userconnection`
 ADD PRIMARY KEY (`userId`,`providerId`,`providerUserId`), ADD UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`);

--
-- Indexes for table `user_profile`
--
ALTER TABLE `user_profile`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_users` (`FK_USER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user_profile`
--
ALTER TABLE `user_profile`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Restrictii pentru tabele sterse
--

--
-- Restrictii pentru tabele `user_profile`
--
ALTER TABLE `user_profile`
ADD CONSTRAINT `FK_users` FOREIGN KEY (`FK_USER_ID`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
