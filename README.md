![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/Jean-Aymeric/Sliding2DArrayStart/main.yml)

# Kata Sliding 2D Array made by JAD

## Objectif de ce Kata

L'objectif est de fournir un composant permettant de gérer simplement et efficacement un
tableau 2D représentant une fraction d'un plus grand tableau.

Vous avez déjà joué à des jeux en 3D isométrique ou vue de dessus. Dans ces jeux, le personnage est
au centre de l'écran. Lorsqu'il se déplace, le monde autour de lui se déplace.
Le monde est trop grand pour être affiché en entier. Il faut donc afficher uniquement une partie du
monde.

Le monde contient généralement 2 types d'éléments :

- des cases (`Tile`) qui sont des éléments fixes
- des pièces (`Pawn`) qui sont des éléments mobiles ou pas.

Les cases sont stockées dans un tableau 2D. Chaque case faisant référence à un Enum spécifique.
Les cases sont donc des éléments immuables et ne connaissent pas leur position dans le monde. Une
case de sable est une case de sable, peu importe où elle se trouve dans le monde. C'est la même
case de sable qui est placée à différents endroits du monde.

Les pièces sont stockées dans une liste (`ArrayList`). Chaque pièce connait sa position dans le
monde. Un monstre est un monstre, mais il peut se trouver à différents endroits du monde. C'est
une instance différente qui est placée à différents endroits du monde.

Pour l'affichage à l'écran les pièces sont affichées au-dessus des cases, il suffit :

- de deux boucles imbriquées pour parcourir le tableau 2D
- d'une boucle pour parcourir la liste des pièces. Le monde ne connait pas l'emplacement des pièces,
  il doit donc interroger chaque pièce pour savoir si elle se trouve sur la case qu'il souhaite
  afficher.

Dans un monde de 1000x1000 cases, il y a 1 000 000 de cases. On peut imaginer qu'il y a environ
entre 1000 et 2000 pièces.
Si l'écran à afficher fait lui 50x50 cases, les deux boucles pour parcourir le tableau de cases vont
être très rapide. On a une complexité de 1 par case. Ce qui est le plus bas que l'on peut obtenir.

La boucle pour parcourir la liste des pièces va être plus longue. Il faut interroger toutes les
pièces pour chaque case. On a une complexité de 1000 par case. Ce qui n'est vraiment pas optimal.

L'`ArrayList` de Java étant performante, il faut des quantités très importantes de pièces pour
que cela devienne un problème. Ainsi dans la plupart des jeux de ce type, il n'y a pas de problème.

Cependant, pour des jeux avec un monde immense, le problème devient critique.
La solution est d'utiliser un `HashMap` pour stocker les pièces.
La clef de cette `HashMap` est une position (stockée dans un objet `Point`) et la donnée est une
liste ou un tableau de pièces. En effet, la clef doit être unique et il peut y avoir plusieurs
pièces sur la même tuile.

La clef ne pouvant être modifiée (y compris ces attributs), il faut que la pièce se supprime dans
la `HashMap` et se recrée à chaque déplacement. Ce traitement peut être très gourmand en ressources
si le nombre de pièces est trop important.

![Diagramme de classe d'un exemple d'implémentation de jeu 2D](https://github.com/Jean-Aymeric/Sliding2DArrayStart/blob/master/img/classdiagramboardgamesample.png)

La solution est d'utiliser une portion du monde et non le monde complet. Ainsi la `HashMap` est
moins sollicitée.

Pour gérer cette portion, le mieux est d'utiliser un tableau 2D et une `ArrayList`. Le tableau 2D
stockera les tuiles et l'`ArrayList` les pièces. Comme vu plus haut, sur un nombre de pièces limité,
cette solution est trés efficace. Seul le monde contenu dans cette portion sera "vivant", le reste
sera "gelé".

La portion de monde est définie par la position du joueur et son champ de vision.

Voici une petite illustration pour mieux comprendre le fonctionnement :
![Illustration du tableau 2D glissant](https://github.com/Jean-Aymeric/Sliding2DArrayStart/blob/master/img/sliding2darray1.png)
Seuls les éléments dans le cadre vert sont "vivants". Les autres sont "gelés". Le personnage joueur
est lui toujours au centre du carré vert. Lorsqu'il se déplace le carré vert se déplace avec lui.

Ce fonctionnement permet de gérer des mondes de taille infinie avec une complexité de N par case, N
étant le nombre de pièces dans le champ de vision du joueur. Il a aussi un autre avantage. Il
pertmet de gérer automatiquement les bords du monde (s'ils existent).
![Illustration du tableau 2D glissant](https://github.com/Jean-Aymeric/Sliding2DArrayStart/blob/master/img/sliding2darray2.png)

Mais l'utilisation de ce tableau glissant soulève un nouveau problème. Il doit être régénéré à
chaque déplacement du joueur. Un tableau étant à dimension fixe en mémoire, il faut déplacer chaque
case verticalement ou horizontalement en fonction du déplacement du joueur. Pour une ligne ou une
colonne à mettre à jour, c'est tout le tableau qui va être rafraichi. C'est très gourmand en temps.
Surtout que ce tableau est censé nous en faire gagner.

Pour éviter ce problème, le tableau doit être rotatif. C'est-à-dire que les cases ne sont pas
déplacées. C'est le tableau qui tourne autour des cases. Exactement comme un buffer rotatif, mais
cette fois-ci en 2D. En se déplaçant d'une case sur la droite, seules la colonne de gauche sera
remplacée par la nouvelle colonne de droite que le joueur voit. Les autres colonnes ne bougeront
pas.

Une autre illustration pour bien comprendre le principe :
![Illustration du tableau 2D glissant](https://github.com/Jean-Aymeric/Sliding2DArrayStart/blob/master/img/sliding2darray3.png)
La nouvelle colonne de droite est stockée à gauche du tableau glissant.

C'est ce tableau glissant que vous allez devoir implémenter dans ce Kata.

## Voici le diagramme de classe de la situation initiale :

![Diagramme de classe de la situation initiale](https://github.com/Jean-Aymeric/Sliding2DArrayStart/blob/master/img/classdiagramsliding2darray.png)

Comme vous pouvez le voir, il n'y a pas de `Main`, mais il y a des tests unitaires.

Si vous lancez les tests, vous devez obtenir ceci.

```
java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.sliding2darray.Sliding2DArray.slideUp(Sliding2DArray.java:56)
	at com.jad.sliding2darray.Sliding2DArrayTest.slideUp(Sliding2DArrayTest.java:61)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.sliding2darray.Sliding2DArray.slideDown(Sliding2DArray.java:61)
	at com.jad.sliding2darray.Sliding2DArrayTest.slideDown(Sliding2DArrayTest.java:71)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.sliding2darray.Sliding2DArray.slideLeft(Sliding2DArray.java:66)
	at com.jad.sliding2darray.Sliding2DArrayTest.slideLeft(Sliding2DArrayTest.java:81)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.sliding2darray.Sliding2DArray.get(Sliding2DArray.java:46)
	at com.jad.sliding2darray.Sliding2DArrayTest.get(Sliding2DArrayTest.java:40)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.sliding2darray.Sliding2DArray.slide(Sliding2DArray.java:51)
	at com.jad.sliding2darray.Sliding2DArrayTest.slide(Sliding2DArrayTest.java:55)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.sliding2darray.Sliding2DArray.slideRight(Sliding2DArray.java:71)
	at com.jad.sliding2darray.Sliding2DArrayTest.slideRight(Sliding2DArrayTest.java:91)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


Process finished with exit code -1
```

Normal, toutes les méthodes sont présentes, mais elles génèrent une
exception `UnsupportedOperationException`. Comme je suis gentil, j'ai déjà implémenté les
accesseurs `getNbColumns()` et `getNbRows()`.

## Conseils pour bien réussir ce Kata

Pour bien réussir ce Kata, je vous conseille de suivre ces étapes :

- commencez par implémenter un tableau non rotatif. Pour cela, créez une méthode `load()`, qui
  remplira votre tableau glissant avec le monde en fonction de la position du tableau dans le
  monde (`arrayColumnInRealArray` et `arrayRowInRealArray`).
- ajouter la gestion des bords du monde. Si le tableau sort du monde, les cases
  correspondantes doivent être remplies avec `null`. Une petite méthode `isOutOfBounds()` rendra la
  chose plus lisible et plus pratique. Les tests devraient tous passer. Ils ne
  vérifient pas la rotation.
- implémentez ensuite la rotation vers le bas ou la droite. Ils sont, à mon avis plus simples que
  les
  deux autres.
- implémentez les deux dernières rotations.
- factorisez les rotations. Il doit y avoir du code dupliqué entre vos rotations. 
