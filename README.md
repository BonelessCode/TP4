# BIENVENUE À LA TOUR ! 

## Que vous recherchiez richesse, pouvoir absolu, immortalité, magie ou mystère, dirigez-vous vers le sommet : toutes les vérités, gloires et joies de ce monde sont en haut de la Tour.

Ce RPG a été fait dans le cadre d'un projet scolaire d'algorithmique à l'ISEP durant l'année 2021-2022. Il a été conçu exclusivement en Java grâce à JavaFX pour son interface graphique.

## Les Héros

Il y a différents héros qu'on peut choisir dans la tour : 

- le Hunter dispose d'un nombre de flèches limité afin d'attaquer ses adversaires
- le Warrior attaque au corps à corps sans limitations
- Le Healer peut uniquement soigner ses alliés avec sa quantité limitée de mana
- Le Mage peut attaquer l'ennemi avec des sorts avec sa quantité limitée de mana


Au début, les dégâts de l'ensemble des héros sont initializés à 27, l'armure à 0, les points de vie à 70 et 3 potions ainsi que 3 lembas par héros.
Ces stats peuvent être augmentées à la demande lorsqu'on vainc une vague d'ennemi, mais on y reviendra plus tard.

L'armure correspond à un pourcentage de réduction de dégat (allant de 0 à 40%) et lorsqu'un héros choisit de défendre, cela réduit de 30% l'efficacité d'une eventuelle attaque.

## Les Ennemis

Il y a deux types d'ennemis : 
- Boss : 90 PDV et 25 DMG, sa probabilité d'apparaître est 10%.
- Ennemi Basique : 30 PDV et 12 DMG, sa probabilité d'apparaître est 90%.

Lorsqu'on jouera, il y aura 80% de chance d'avoir autant d'ennemis que de héros, 10% de chance pour qu'il y en ait un de moins et 10% de chance qu'il y en ait un de plus.


## Comment jouer ?

Pour lancer le jeu, il faut lancer le main de la classe Game.
Une fois lancé, il vous emmènera sur la page suivante : 

![LA TOUR](https://i.imgur.com/2fKs4ft.png)

Sur cette page, vous pouvez selectionnez le nombre de chaque type héros que vous voulez dans votre équipe. Attention, avoir beaucoup de héros ne rend pas nécessairement le jeu plus facile car le nombre d'
ennemis dépend de votre nombre de héros.

Une fois votre nombre de héros choisi, vous pouvez lancer le jeu en cliquant sur play.


## Phase de Gameplay

### À propos de l'interface graphique,
on arrive maintenant sur la page suivante
![GAMEPLAY](https://i.imgur.com/eqTlkFK.png)

Les héros sont placés à gauche alors que les ennemis sont à droite.
Les colonnes sont désignées par leur titre en haut, la première désigne le mana restant ou les fléches restantes si le héros correspondant est un SpellCaster ou un Hunter.
La deuxième correspond aux points de vie restant du héros.
On peut aussi observer les points de vie des ennemis dans la dernière colonne.

En dessous de la grille désignée par les cases d'herbe, on voit les attributs correspondants au héros selectionné : Ici, rien n'apparaît car on n'en a pas encore selectionné.

À titre d'exemple, selectionnons le premier héros.

On observe le changement suivant : 

![GAMEPLAY2](https://i.imgur.com/EanFFHU.png)

On voit désormais le numéro du héro, ses dégats, son nombre de potions et nourritures restants, son armure et la liste des joueurs qui n'ont pas encore joué pour mieux se retrouver.


Concernant les actions des héros, on peut choisir d'attaquer, défendre ou encore de consommer un consommable.

Il suffit de séléctionner le héros puis de cliquer sur le bouton concerné afin qu'il fasse effet.
Pour l'attaque, il faut ensuite selectionner l'ennemi qu'on veut attaquer.

### Lorsque c'est le tour de l'ennemi, 
ils attaquent de manière aléatoire un des héros.

Concernant la suite du combat : 
- Si on bat l'ensemble des ennemis de la vague, on arrive à une phase de récompenses.
- Si l'ensemble de nos héros périssent, on perd le jeu et on doit recommencer.

## Phase de récompenses

On peut choisir pour chaque héros l'attribut que l'on souhaite renforcer dans la fenêtre suivante : 

![RECOMPENSES](https://i.imgur.com/4JuuO8R.png)

Les améliorations sont les suivantes : 
- L'armure est augmentée de 10 jusqu'au maximum 40
- Les dégâts sont augmentés de 20 (Sans maximum actuellement)
- L'effet des consommables est augmenté de 20
- Le nombre des consommables est augmenté de 1
- Le mana ou les flèches sont augmentées respectivement de 20 et 10. (Augmente les dégâts si le héros est un Warrior)

À l'issu de cette phase de récompenses, les points de vie des héros sont reinitialisés, ainsi que leur nombre de consommable et le nombre de flèche/mana.

On passe à l'étage suivant de la tour et la prochaine vague d'ennemi arrive avec un ennemi de plus.

### Arriverez-vous au bout de la TOUR ? 





