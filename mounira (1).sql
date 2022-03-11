-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 11 mars 2022 à 14:24
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `mounira`
--

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `total` float NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `etat`, `date`, `total`, `user_id`) VALUES
(25, 'valide', '2022-03-11', 20, 1),
(26, 'valide', '2022-03-11', 19, 1);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `commentaire_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `commentaire_desc` varchar(255) NOT NULL,
  `commentaire_date` varchar(255) NOT NULL,
  `nblike` int(11) NOT NULL DEFAULT 0,
  `nbdislike` int(11) NOT NULL DEFAULT 0,
  `nbscom` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`commentaire_id`, `user_id`, `commentaire_desc`, `commentaire_date`, `nblike`, `nbdislike`, `nbscom`) VALUES
(8, 14, 'aaa', '2022/03/11', 1, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `dislikee`
--

CREATE TABLE `dislikee` (
  `dislikee_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `commentaire_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `nbr_personnes` int(11) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `date`, `nbr_personnes`, `categorie`, `description`) VALUES
(1, 'Vegan4ever', '2022-02-18', 4, 'Vegan', 'Welcome here my friends');

-- --------------------------------------------------------

--
-- Structure de la table `ingredients`
--

CREATE TABLE `ingredients` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ingredients`
--

INSERT INTO `ingredients` (`id`, `nom`, `quantite`) VALUES
(1, 'fdsfsd', 56);

-- --------------------------------------------------------

--
-- Structure de la table `likee`
--

CREATE TABLE `likee` (
  `likee_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `commentaire_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `likee`
--

INSERT INTO `likee` (`likee_id`, `user_id`, `commentaire_id`) VALUES
(7, 14, 8);

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `livreur_id` int(11) NOT NULL,
  `comande_id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livraison`
--

INSERT INTO `livraison` (`id`, `user_id`, `livreur_id`, `comande_id`, `nom`, `etat`) VALUES
(6, 1, 13, 25, 'awel livraison', 'Livrée');

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `prix` float NOT NULL,
  `ingredients` varchar(255) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `menu`
--

INSERT INTO `menu` (`id`, `titre`, `description`, `prix`, `ingredients`, `categorie`, `image`) VALUES
(1, 'Pizza Marinera', 'Pizza Marinera', 8, 'Thon, oignons, olives noires, champignons, fromage, pizza sauce', 'Normal', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\pizza_marinera_22503_600_square.jpg'),
(2, 'Pizza Chicken BBQ', 'Pizza Chicken BBQ', 15, 'Poulet grillé, champignons frais, oignons doux, fromage, sauce BBQ', 'Normal', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\BBQ-Chicken-Pizza-one-slice.jpg'),
(3, 'Pizza The Hawaiien', 'Pizza The Hawaiien', 9, 'Jambon de dinde, ananas, fromage, pizza sauce', 'Normal', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\hawaiian-pizza-16-1200.jpg'),
(4, 'Garden Salad', 'Garden Salad', 10, 'Tomates, oignons, olives noires, poivrons, concombre, sur un lit de laitue fraiche, dressée avec la sauce Ranch', 'Vegan', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\Garden-Salad_47-SQ.jpg'),
(5, 'Bouddha bowl au falafels', 'Bouddha bowl au falafels', 19, 'Falafel,riz basmati,sauce soja', 'Vegan', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\904d0e97-0221-4d82-9ea7-a035b476863b.jpg'),
(6, 'Burger classic Vegan', 'Burger classic Vegan', 12, 'Steak végan,tomate,oignons,salade de saison', 'Vegan', 'C:\\Users\\Nayrouz\\OneDrive\\Desktop\\Intégration\\pidev\\src\\ressources\\Burger-31LGH-a296a356-020c-4969-86e8-d8c26139f83f-0-1400x919.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `menu_commande`
--

CREATE TABLE `menu_commande` (
  `id` int(11) NOT NULL,
  `command_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `menu_commande`
--

INSERT INTO `menu_commande` (`id`, `command_id`, `menu_id`) VALUES
(73, 26, 5);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `raison` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id` int(11) NOT NULL,
  `reponse` varchar(255) NOT NULL,
  `reclamation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `user_id`, `event_id`, `nom`) VALUES
(2, 2, 1, 'Smayar Reservation');

-- --------------------------------------------------------

--
-- Structure de la table `scommentaire`
--

CREATE TABLE `scommentaire` (
  `Scommentaire_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `commentaire_id` int(11) NOT NULL,
  `Scommentaire_desc` varchar(255) NOT NULL,
  `Scommentaire_date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `num_tel` varchar(8) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `date`, `num_tel`, `adresse`, `role`) VALUES
(1, 'dhia', 'Bejaoui', 'dhiabejaoui@gmail.com', '61959959', '2022-02-18', '53404720', '20 Mars', 'Client'),
(2, 'Ahlem', 'Mart Smayar', 'yyy', '47bce5c74f589f4867dbd57e9ca9f808', '2022-03-24', '22510165', 'aaa', 'Client'),
(3, 'Smayer', 'Samir', 'aaa', '47bce5c74f589f4867dbd57e9ca9f808', '2022-02-18', '22510164', 'marsa', 'Admin'),
(13, 'fatheya', 'mounira', 'eee', '47bce5c74f589f4867dbd57e9ca9f808', '2022-03-23', '22510144', 'tunis', 'Livreur'),
(14, 'aaa', 'aaa', 'nairouza.shili@gmail.com', '47bce5c74f589f4867dbd57e9ca9f808', '2022-03-16', '22510114', 'aaa', 'Client'),
(15, 'oussama', 'boussetta', 'boussettaoussama7@gmail.com', '502e4a16930e414107ee22b6198c578f', '2022-03-04', '12345678', 'aaaaaa', 'Client'),
(18, 'amir', 'kallel', 'amir.kallel@esprit.tn', '202cb962ac59075b964b07152d234b70', '2022-03-12', '12345678', 'Ariana', 'Client');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`commentaire_id`),
  ADD KEY `fk_userid` (`user_id`);

--
-- Index pour la table `dislikee`
--
ALTER TABLE `dislikee`
  ADD PRIMARY KEY (`dislikee_id`),
  ADD KEY `fk_userrid` (`user_id`),
  ADD KEY `fk_commid` (`commentaire_id`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `likee`
--
ALTER TABLE `likee`
  ADD PRIMARY KEY (`likee_id`),
  ADD KEY `fk_useriddd` (`user_id`),
  ADD KEY `fk_comiddd` (`commentaire_id`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `livreur_id` (`livreur_id`),
  ADD KEY `comande_id` (`comande_id`);

--
-- Index pour la table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `titre` (`titre`);

--
-- Index pour la table `menu_commande`
--
ALTER TABLE `menu_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `command_id` (`command_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `reclamation_id` (`reclamation_id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `event_id` (`event_id`);

--
-- Index pour la table `scommentaire`
--
ALTER TABLE `scommentaire`
  ADD PRIMARY KEY (`Scommentaire_id`),
  ADD KEY `fk_useridd` (`user_id`),
  ADD KEY `fk_comidd` (`commentaire_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `commentaire_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `dislikee`
--
ALTER TABLE `dislikee`
  MODIFY `dislikee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `likee`
--
ALTER TABLE `likee`
  MODIFY `likee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `menu_commande`
--
ALTER TABLE `menu_commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `scommentaire`
--
ALTER TABLE `scommentaire`
  MODIFY `Scommentaire_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fk_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `dislikee`
--
ALTER TABLE `dislikee`
  ADD CONSTRAINT `fk_commid` FOREIGN KEY (`commentaire_id`) REFERENCES `commentaire` (`commentaire_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_userrid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `likee`
--
ALTER TABLE `likee`
  ADD CONSTRAINT `fk_comiddd` FOREIGN KEY (`commentaire_id`) REFERENCES `commentaire` (`commentaire_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_useriddd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `livraison_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `livraison_ibfk_2` FOREIGN KEY (`livreur_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `livraison_ibfk_3` FOREIGN KEY (`comande_id`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `menu_commande`
--
ALTER TABLE `menu_commande`
  ADD CONSTRAINT `menu_commande_ibfk_1` FOREIGN KEY (`command_id`) REFERENCES `commande` (`id`),
  ADD CONSTRAINT `menu_commande_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`reclamation_id`) REFERENCES `reclamation` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `evenement` (`id`);

--
-- Contraintes pour la table `scommentaire`
--
ALTER TABLE `scommentaire`
  ADD CONSTRAINT `fk_comidd` FOREIGN KEY (`commentaire_id`) REFERENCES `commentaire` (`commentaire_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_useridd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
