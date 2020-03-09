# Objectifs principaux

## Liste des éléments importants
* 2 modes de jeu : 
    * invisible
    * visible
* Gestion des déplacements au clavier
* Objets identiques pour tout les joueurs
* Une fois tout les objects collectés la partie se termine
* Commenter (***beaucoup***) le code :
    * Commenter en javadoc
    * Commenter chaque methodes / class
* Architecture serveur dédié / client

## Fonctionnalités voulues par nous

- Torique (option)
- Collisions entre les joueurs

## Fonctionnalités optionnelles

* Afficher les objets dans un rayon prédéfini
* Afficher les objects collectés du joueur
* Ajouter chronomètre
* Principe du meilleur joueur
* Objets spéciaux appliquant des pouvoirs au joueur (vitesse, etc)
* Différents résultats à la collision entre joueurs en fonction des pouvoirs
* Labyrinthe (basse priorité)

## Suggestions

- Nom
  - Pacman.io

## Ressources utilisées

- Threads / Runnable
- `synchronized` 
- Sockets UDP

## Questions / Réponses

### Questions du sujet

1. **Comment le nombre de joueurs est-il choisi au démarrage du jeu ?**
   - Premier niveau: nombre de joueurs *n* fixé (une fois que *n* joueurs ont rejoint la partie, la partie est lancée)
   - Deuxième niveau: nombre de joueur *n* maximum fixé, mais les joueurs peuvent lancer la partie avant d'avoir atteint ce maximum si tout le monde est d'accord (ex: maximum de 10 joueurs, mais si 4 joueurs sont prêts, la partie est lancée)
   - Troisième niveau: liste de salles, parties publiques/privées: le joueur peut sélectionner à quelle partie il se connecte (fonctionnement individuel des salles comme au deuxième niveau), timer de début pour les parties publiques ; partie privée accessible via mot de passe
   - Quatrième niveau: les parties publiques commencent dès que deux joueurs ont rejoint une salle publique, et des joueurs peuvent rejoindre cette partie en cours de route si il reste plus de la moitié des objets
1. **Comment décide-t-on que le jeu peut commencer ?**
   - cf question 1
1. **Lorsque le jeu est démarré, par quel moyen les déplacements des joueurs sont-ils visualisables sur l’interface de tous les joueurs ?**

### Nos questions

- Est-ce qu'il y a des collisions entre les joueurs ?
  - Option de gameplay