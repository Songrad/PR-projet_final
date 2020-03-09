# Objectif principaux

## Liste des éléments importants
* 2 modes de jeu : 
    * invisible
    * visible
    
* Gestion des déplacements au clavier
* Objets identiques pour tout les joueurs
* Une fois tout les objects collectés la partie se termine
* Commenter (beaucoup) le code :
    * Commenter en javadoc
    * Commenter chaque methodes / class

## Fonctionnalité optionnelle

* Afficher les objets dans un rayon prédéfini
* Afficher les objects collectés du joueur
* Ajouter chronomètre
* Principe du meilleur joueur

## Ressources utilisées



## Question / Reponse
1. comment le nombre de joueurs est-il choisi au demarrage du jeu ?
   - Premier niveau: nombre de joueurs *n* fixé (une fois que *n* joueurs ont rejoint la partie, la partie est lancée)
   - Deuxième niveau: nombre de joueur *n* maximum fixé, mais les joueurs peuvent lancer la partie avant d'avoir atteint ce maximum si tout le monde est d'accord (ex: maximum de 10 joueurs, mais si 4 joueurs sont prêts, la partie est lancée)
   - Troisième niveau: liste de salles, parties publiques/privées: le joueur peut sélectionner à quelle partie il se connecte (fonctionnement individuel des salles comme au deuxième niveau)
1. comment decide-t-on que le jeu peut commencer ?
1. lorsque le jeu est démarré, par quel moyen les d'éplacements des joueurs sont-ils visualisables sur l’interface de tous les joueurs ?
