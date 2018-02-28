-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 28 fév. 2018 à 17:43
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mysoulmatefinal`
--

-- --------------------------------------------------------

--
-- Structure de la table `appreciation`
--

DROP TABLE IF EXISTS `appreciation`;
CREATE TABLE IF NOT EXISTS `appreciation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pub` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pub` (`id_pub`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `appreciation`
--

INSERT INTO `appreciation` (`id`, `id_pub`) VALUES
(1, 25),
(2, 25),
(3, 25),
(4, 25),
(5, 25),
(6, 25),
(7, 25),
(8, 25),
(9, 25),
(10, 25),
(11, 25),
(12, 25),
(13, 25),
(14, 25),
(15, 25),
(16, 25),
(17, 25),
(18, 25),
(23, 18),
(24, 18),
(25, 18),
(26, 18),
(27, 18);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pub` int(11) NOT NULL,
  `contenu_com` text,
  `date_com` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`id_pub`),
  KEY `id_pub` (`id_pub`)
) ENGINE=MyISAM AUTO_INCREMENT=169 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `expired` tinyint(1) DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` enum('admin','utilisateur','internaute','responsableEvent') CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Typearray)',
  `credentials_expired` tinyint(1) DEFAULT NULL,
  `credentials_expire_at` datetime DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  `num_tel` int(11) NOT NULL,
  `photo_de_profil` varchar(1000) NOT NULL,
  `date_de_naissance` date NOT NULL,
  `adresse` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_canonical` (`email_canonical`),
  UNIQUE KEY `username_canonical` (`username_canonical`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`, `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`, `credentials_expire_at`, `nom`, `prenom`, `age`, `sexe`, `num_tel`, `photo_de_profil`, `date_de_naissance`, `adresse`) VALUES
(1, 'ffff', NULL, 'kakaka', NULL, NULL, NULL, '1412', NULL, NULL, NULL, NULL, NULL, NULL, 'utilisateur', NULL, NULL, 'mdmmd', 'pmpmp', 20, 'fem', 252525, 'mmmqqm', '2018-02-26', 'msmmd'),
(2, 'kkkk', 'kkk', 'kkk@gmail.Com', 'kkkk', 1, 'ddd', 'ddd', '2018-02-08 00:00:00', 2, 5, '2018-02-21 00:00:00', 'ddd', '2018-02-14 00:00:00', 'admin', 5, '2018-02-15 00:00:00', 'ddd', 'dddd', 5, 'ddd', 52452144, 'dddd', '2018-02-13', '');

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenu_pub` text NOT NULL,
  `date_pub` datetime DEFAULT CURRENT_TIMESTAMP,
  `image` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id`, `contenu_pub`, `date_pub`, `image`, `video`, `id_user`) VALUES
(194, 'video', '2018-02-28 04:13:16', 'null', 'pub5.mp4', 1);

-- --------------------------------------------------------

--
-- Structure de la table `publication_like`
--

DROP TABLE IF EXISTS `publication_like`;
CREATE TABLE IF NOT EXISTS `publication_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pub_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_2` (`user_id`),
  KEY `pub_id` (`pub_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `publication_like`
--

INSERT INTO `publication_like` (`id`, `pub_id`, `user_id`) VALUES
(61, 194, 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `publication_like`
--
ALTER TABLE `publication_like`
  ADD CONSTRAINT `publication_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `publication_like_ibfk_2` FOREIGN KEY (`pub_id`) REFERENCES `publication` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
