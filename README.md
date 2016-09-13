# Java-Matrix-Calculator
Matrix Calculator with GUI using Java Swing

*Implémentation de l'interface IMatrice à l'aide d'une interface graphique.*

Matrice avec arraylist, plus simple.

À l'ouverture du programe, scanner le fichier texte.  
Itilialiser les matrices.

**Stocker les matrices dans un arraylist**  
Les positions *paires* contiennent une string contenant le nom de la matrice. Les positions *impaires* contiennent la matrice.

**Affichage du GUI**  
Une fois les matrices initialisées le GUI s'affiche.  
Le sélecteur de matrice de la zone 1 affiche tout les noms de matrices *(Positions paires de l'Arraylist)*  

Lorsque l'utilisateur choisit une matrice *(action lisener)*, initilisation de la matrice en zone1. Grid layout avec x nbLignes y nbColones.

Chaque element de la matrice est dans une case qui est pour l'instant non éditable. **action lisener sur les boutons en dessous**. Lorsque l'utilisateur activera le mode édition les boutons deviendront actifs et pourront être utilisés.  

Ajouter une colone ou ligne causera une réinitialisation de la matrice zone1 qui sera modifiée à l'aide de ajouter ligne ou ajouter colone de IMatrice.

En mode *édition* chaque champ *on focus loss* modifie la valeur à cet endroit dans la matrice.

**Structure des textfields**  
Afin que l'on puisse utiliser la méthode modifier élément (x,y), le tableau des cellules doit être bi-dimentionnel

	tab[x][y]

Ainsi les x et y corresponderont au x et y de la matrice avec < nbCol et < nbLignes.
