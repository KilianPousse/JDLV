# Design Pattern - Jeu de la Vie

Ce projet a été réalisé dans le cadre du module Design Pattern en troisième année au Mans, sous la supervision de Despres Christophe. Le but de ce projet est de tester notre compréhension du module en appliquant ses règles à un logiciel implémentant le Jeu de la Vie de John Conway.

- **Langage:** Java
- **Outils utilisés:** Design Pattern, Java Swing, Maven, Javadoc

## Introduction

Le **Jeu de la Vie** (*ou Game of Life*) est un automate cellulaire créé par *John Conway* en 1970. Un automate cellulaire est un modèle mathématique constitué d'une grille de cellules, où chaque cellule peut prendre un état parmi un ensemble fini et évolue au fil du temps selon des règles prédéfinies dépendant de l'état de ses voisines.

#### Règles du Jeu de la Vie:
 
Chaque cellule d'une grille peut avoir un état : `Mort` ou `Vivant`. Son évolution dépend du nombre de cellules vivantes voisines :
 - Moins de 2 voisins vivants → *Mort*
 - 2 ou 3 voisins vivants → *Survie*
 - Plus de 3 voisins vivants → *Mort*
 - Une cellule morte avec exactement 3 voisins vivants → *Naissance* 

---

## Fonctionnalités ajoutées

 Le but du projet était de vérifier si nous avions compris le principe du Design Pattern. C'est pour cela que des améliorations a été demandées afin de nous pousser à utiliser d'autres Patterns.

### 📏 Nouvelles règles:
Il est maintenant possible de changer de règle du jeu quand la partie est en pause. Cela permet de mettre en avant le Pattern `Visiteur`.
 - **Day & Night:** une cellule naît ou survit non seulement grâce à des voisins vivants, mais aussi grâce à des cellules mortes environnantes, créant ainsi des motifs dynamiques qui alternent entre expansion et contraction.

 - **High Life:** mêmes règles que le jeu classique de Conway, mais avec une différence : une cellule morte naît si elle a exactement 6 voisins vivants, en plus de la règle classique de naissance avec 3 voisins, ce qui permet l'émergence d'un automate auto-réplicant unique.

 - **Reverse Life:** les cellules vivantes meurent sauf si elles ont exactement 2 ou 3 voisins vivants, tandis que les cellules mortes renaissent uniquement avec 3 voisins vivants, inversant ainsi certaines dynamiques du jeu classique.

 - **Random Rainbow:** les cellules vivantes peuvent mourir et les cellules mortes peuvent renaître de manière imprévisible, tout en adoptant des couleurs aléatoires parmi une palette prédéfinie.

![Menu déroulant du choix des règles](https://raw.githubusercontent.com/KilianPousse/JDLV/refs/heads/main/doc/img_readme/regles.png)

---

### 📁 Sauvegardes:

Le projet permet de sauvegarder et de charger une partie afin de reprendre une session. Il est également possible de créer une nouvelle partie à partir de zéro en initialisant la grille.

#### Fonctionnalités de sauvegarde et chargement :

- **Sauvegarde:** En cours de partie, il est possible d'enregistrer l'état actuel de la grille et les paramètres en cours dans un fichier. Cela permet de conserver une progression et de la restaurer plus tard.

- **Chargement:** Un fichier de sauvegarde peut être chargé à tout moment, restaurant ainsi l'état exact de la grille et des paramètres configurés lors de la sauvegarde.

- **Nouveau départ:** L'utilisateur peut réinitialiser la grille pour démarrer une nouvelle simulation.

![Menu d'initialisation](https://raw.githubusercontent.com/KilianPousse/JDLV/refs/heads/main/doc/img_readme/init.png)

---

### 💻 Affichage:

Dans la partie de gauche, il est possible de zoomer avec la molette ainsi que d'utiliser *Click & Drag* pour se déplacer sur le rendu de la simulation.

De plus, la couleur des cellules varie en fonction de la règle sélectionnée, offrant ainsi un repère visuel sur leur état et leur évolution.

---

### ⚙️ Panneau de contrôle:

Dans le logiciel, un panneau de contrôle est disponible afin de manipuler la simulation en cours.

#### Fonctionnalités :

- **Bouton Démarrer/Arrêter:** Permet de lancer ou de mettre en pause la simulation.
- **Bouton Avancer d'une génération:** Fait évoluer la grille d'une seule génération lorsque la simulation est en pause.
- **Curseur de délai:** Modifie le temps entre chaque génération pour accélérer ou ralentir la simulation.
- **Menu déroulant de règles:** Permet de choisir une règle lorsque la simulation est en pause.
- **Curseur de zoom:** Ajuste le niveau de zoom sur la grille pour une meilleure visualisation.
- **Bouton Recentrer:** Recentre l'affichage de la grille pour un meilleur confort d'utilisation.

![Panneau de contrôle](https://raw.githubusercontent.com/KilianPousse/JDLV/refs/heads/main/doc/img_readme/p2c.png)

---

### 📈 Écran des Données:

Un écran dédié permet de visualiser des informations sur la simulation en cours :
 - **Numéro de la génération courante:** Indique à quelle étape de l'évolution se trouve la simulation.
 - **Nombre d'individus vivants:** Affiche le nombre total de cellules vivantes dans la génération actuelle.
 - **Graphique d'évolution:** Un graphique interactif montre la variation du nombre de cellules vivantes au fil des générations, permettant ainsi de mieux observer les tendances et dynamiques du jeu.

![Écran des Données](https://raw.githubusercontent.com/KilianPousse/JDLV/refs/heads/main/doc/img_readme/e2d.png)

---

### 🛠️ Barre de Menu:

L'interface dispose d'une barre de menu permettant d'accéder rapidement aux principales fonctionnalités du logiciel.

#### Menus et options disponibles :

- **Fichier:**
     - **Nouveau:** Crée une nouvelle simulation en réinitialisant la grille.
     - **Enregistrer:** Sauvegarde la simulation en cours.
     - **Charger:** Permet de charger une simulation enregistrée.
     - **Quitter:** Ferme l'application.

- **Simulation:**
     - **Démarrer/Arrêter:** Lance ou met en pause la simulation.
     - **Avancer:** Fait évoluer la simulation d'une seule génération.

- **À Propos:**
     - **GitHub:** Redirige vers le dépôt du projet pour consulter le code source et la documentation.

La barre de menu est dynamique et s'adapte à l'état actuel de la simulation. Par exemple, si aucune simulation n'est en cours, certaines options (comme "Démarrer" ou "Enregistrer") sont désactivées pour éviter les actions non valides.

![Barre de menu](https://raw.githubusercontent.com/KilianPousse/JDLV/refs/heads/main/doc/img_readme/menu.png)

---

### ⚠️ Fenêtres d'avertissement:

Des fenêtres d'avertissement sont affichées dans différentes situations pour informer l'utilisateur et éviter des erreurs.

#### Types d'avertissements :

- **Erreur:** Une boîte de dialogue s'affiche en cas d'exception (exemple : erreur lors de l'initialisation d'une nouvelle partie, problème lors de l'enregistrement ou du chargement d'une sauvegarde, ...).

![Fenêtre d'erreur](https://raw.githubusercontent.com/KilianPousse/JDLV/refs/heads/main/doc/img_readme/err.png)

- **Avertissement de fermeture:** Lors de la fermeture du logiciel, une confirmation est demandée si la partie en cours n'a pas été sauvegardée, permettant à l'utilisateur de sauvegarder avant de quitter.

![Fenêtre de fermeture](https://raw.githubusercontent.com/KilianPousse/JDLV/refs/heads/main/doc/img_readme/quit.png)

Ces fenêtres permettent d'améliorer l'expérience utilisateur en réduisant les risques de perte de progression et en informant clairement des éventuels problèmes rencontrés.

---

## Arborescence
L'arborescence suit la structure du projet avec `Maven`:
```bash
   \JDLV
    ├── \doc 
    │    └── Jeu de la Vie.html         # Document des instructions du projet
    ├── \src
    │    └── \main\java\kpss\jdlv
    │         ├── \commande             # Sous-package avec les commandes
    │         ├── \exception            # Sous-package avec les exceptions 
    |         ├── \ui                   # Sous-package avec les composants de l'interface 
    |         └── ...                   # Scripts du package principal
    ├── \target
    |    └── POUSSE-JDLV-{version}.jar  # Exécutable java (est présent si compilation Maven)
    ├── LICENSE                         # License du projet
    ├── pom.xml                         # Fichier de configuration Maven
    └── README.md                       # Ce fichier
```

## Installation

Afin d'utiliser ce projet, il est nécessaire d'avoir une version de Java. Vous pouvez en télécharger une sur le site d'[Oracle](https://www.oracle.com/java/technologies/downloads).
De plus, si vous voulez compiler le projet, il vous faudra Maven sur votre machine.

Pour cloner **JDVL**, il voudra lancer cette comment depuis votre terminal:
```bash
    git clone https://github.com/KilianPousse/JDLV.git
```
Si il n'y a pas d'exécutable dans le répertoires `\target`, il faudra faire une compilation avec **Maven**:
```bash
    mvn clean package
```
Maintenait tout est près ! Il ne vous reste plus que lancer le *jar* avec cette commande:
```bash
    java -jar {chemin-vers-le-jar}/POUSSE-JDLV-{version}.jar
```


## Contributeurs
#### Auteurs:
 - Kilian POUSSE: [Kilian.POUSSE.Etu@univ-lemans.fr](mailto:Kilian.POUSSE.Etu@univ-lemans.fr)
#### Encadrants:
 - Christophe DESPRES: [Christophe.Despres@univ-lemans.fr](mailto:Christophe.Despres@univ-lemans.fr)

## Licence
Ce projet est sous licence GPL-3.0. Voir le fichier [LICENSE](LICENSE) pour plus d’informations
