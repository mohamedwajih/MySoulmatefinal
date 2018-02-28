-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  ven. 23 fév. 2018 à 00:58
-- Version du serveur :  10.1.28-MariaDB
-- Version de PHP :  7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mysoulmate`
--

-- --------------------------------------------------------

--
-- Structure de la table `appreciation`
--

CREATE TABLE `appreciation` (
  `id_app` int(11) NOT NULL,
  `id_pub` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `appreciation`
--

INSERT INTO `appreciation` (`id_app`, `id_pub`) VALUES
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

CREATE TABLE `commentaire` (
  `id_com` int(11) NOT NULL,
  `id_pub` int(11) NOT NULL,
  `contenu_com` text,
  `date_com` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_com`, `id_pub`, `contenu_com`, `date_com`) VALUES
(135, 0, 'll', '2018-02-22 10:32:46');

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

CREATE TABLE `fos_user` (
  `id` int(11) NOT NULL,
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
  `adresse` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`, `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`, `credentials_expire_at`, `nom`, `prenom`, `age`, `sexe`, `num_tel`, `photo_de_profil`, `date_de_naissance`, `adresse`) VALUES
(1, 'ffff', NULL, 'kakaka', NULL, NULL, NULL, '1412', NULL, NULL, NULL, NULL, NULL, NULL, 'utilisateur', NULL, NULL, 'mdmmd', 'pmpmp', 20, 'fem', 252525, 'mmmqqm', '2018-02-26', 'msmmd');

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

CREATE TABLE `publication` (
  `id_pub` int(11) NOT NULL,
  `contenu_pub` text NOT NULL,
  `date_pub` datetime NOT NULL,
  `image` varchar(255) NOT NULL,
  `video` varchar(255) DEFAULT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id_pub`, `contenu_pub`, `date_pub`, `image`, `video`, `id_user`) VALUES
(143, 'lol', '2018-02-19 11:41:53', 'bluewave.png', '', 0),
(173, 'new', '2018-02-22 08:03:55', 'null', 'null', 0),
(174, 'this is my first test', '2018-02-22 08:04:09', 'null', 'null', 0),
(167, 'hhhhh', '2018-02-22 07:38:29', 'null', 'null', 0),
(152, 'jj', '2018-02-19 11:47:29', 'bluewave.png', '', 0),
(168, 'new publication', '2018-02-22 08:02:16', 'null', 'null', 0),
(169, 'new publication', '2018-02-22 08:02:20', 'null', 'null', 0),
(170, 'new pub', '2018-02-22 08:02:57', 'null', 'null', 0),
(171, 'new pub', '2018-02-22 08:03:00', 'null', 'null', 0),
(172, 'new pub', '2018-02-22 08:03:03', 'null', 'null', 0),
(178, 'test', '2018-02-23 12:56:42', 'module_table_bottom.png', 'a.mp4', 1),
(177, 'ttt', '2018-02-23 12:30:35', 'back2.jpg', 'a.mp4', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `appreciation`
--
ALTER TABLE `appreciation`
  ADD PRIMARY KEY (`id_app`),
  ADD KEY `id_pub` (`id_pub`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_com`,`id_pub`),
  ADD KEY `id_pub` (`id_pub`);

--
-- Index pour la table `fos_user`
--
ALTER TABLE `fos_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_canonical` (`email_canonical`),
  ADD UNIQUE KEY `username_canonical` (`username_canonical`);

--
-- Index pour la table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`id_pub`),
  ADD KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `appreciation`
--
ALTER TABLE `appreciation`
  MODIFY `id_app` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_com` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT pour la table `fos_user`
--
ALTER TABLE `fos_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `publication`
--
ALTER TABLE `publication`
  MODIFY `id_pub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=179;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
