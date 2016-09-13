
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Thomas Castonguay-Gagnon
 * @codePerm CAST10059303
 */
public class Matrice implements IMatrice {

    private int numColonnes;
    private int numLignes;
    private ArrayList<Double> elements;

    public Matrice(int numLignes, int numColonnes, double valeur) throws MatriceException {
        if (numLignes < 1 || numColonnes < 1) {
            throw new MatriceException();
        } else {
            this.numLignes = numLignes;
            this.numColonnes = numColonnes;
            int max = this.numColonnes * this.numLignes;
            this.elements = new ArrayList<Double>(max);
            for (int i = 0; i < max; i++) {
                elements.add(valeur);
            }
        }

    }

    public Matrice(int numLignes, int numColonnes, double[] elements) throws MatriceException {
        if (numLignes < 1 || numLignes < 1 || elements == null || elements.length != (numLignes * numColonnes)) {
            throw new MatriceException();
        } else {
            this.numLignes = numLignes;
            this.numColonnes = numColonnes;
            int max = this.numColonnes * this.numLignes;
            this.elements = new ArrayList<Double>(max);
            for (int i = 0; i < (numLignes * numColonnes); i++) {
                this.elements.add(elements[i]);
            }
        }
    }

    public Matrice(Matrice autreMatrice) {
        this.numColonnes = autreMatrice.numColonnes;
        this.numLignes = autreMatrice.numLignes;
        int max = numColonnes * numLignes;
        this.elements = new ArrayList<Double>(max);
        for (int i = 0; i < max; i++) {
            this.elements.add(autreMatrice.elements.get(i));
        }
    }

    /**
     * Retourne une representation sous forme de chaine de caracteres de cette
     * matrice. Exemple de la representation d'une matrice de 3 lignes par 2
     * colonnes :
     *
     * [ 9,0 5,0 ] [ 6,0 7,0 ] [ 7,0 4,0 ]
     *
     * NOTE : Cette methode fonctionne pour les matrices dont tous les elements
     * sous forme de chaine de caracteres ne contiennent pas plus de 7
     * caracteres. Les elements sont arrondis a une decimale.
     *
     * @return une representation sous forme de chaine de caracteres de cette
     * matrice.
     */
    @Override
    public String toString() {
        final DecimalFormat DEC_FORMAT = new DecimalFormat("0.0");
        final int ESP = 8;
        int num;
        String sTmp;
        String s = "[";
        for (int i = 0; i < (numLignes * numColonnes); i++) {
            //etendre i sur ESP colonnes
            sTmp = "";
            num = ESP - DEC_FORMAT.format(elements.get(i)).length();
            for (int j = 0; j < num; j++) {
                sTmp = sTmp + " ";
            }
            sTmp = sTmp + DEC_FORMAT.format(elements.get(i));

            if (i != 0 && i % numColonnes == 0) {
                s = s + "  ]\n[" + sTmp;
            } else {
                s = s + sTmp;
            }
        }
        s = s + "  ]";
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        boolean output = true;
        if (this.getClass() == obj.getClass()) {
            Matrice input = (Matrice) obj;
            if (this.numLignes == input.numLignes && this.numColonnes == input.numColonnes) {
                int i = 0;
                int max = input.numLignes * input.numColonnes;
                boolean exit = false;
                while (i < max && !exit) {
                    if (this.elements.get(i) == input.elements.get(i)) {
                        i++;
                    } else {
                        exit = true;
                        output = false;
                    }
                }
            } else {
                output = false;
            }
        } else {
            output = false;
        }
        return output;
    }

    /**
     * Retourne le nombre de lignes de cette matrice.
     *
     * @return le nombre de lignes de cette matrice.
     */
    public int getNumLignes() {
        return numLignes;
    }

    /**
     * Retourne le nombre de colonnes de cette matrice.
     *
     * @return le nombre de colonnes de cette matrice.
     */
    public int getNumColonnes() {
        return numColonnes;
    }

    /**
     * Retourne l'element de la matrice a la position noLigne, noCol.
     *
     * @param noLigne le numero de la ligne dans cette matrice.
     * @param noCol le numero de la colonne dans cette matrice.
     * @return l'element de la matrice a la position noLigne, noCol.
     * @throws MatriceException si : noLigne ou noCol n'est pas un indice valide
     * dans cette matrice.
     */
    public double getElement(int noLigne, int noCol) throws MatriceException {
        if (noCol < 0 || noCol >= numColonnes || noLigne < 0 || noLigne >= numLignes) {
            throw new MatriceException();
        } else {
            int position = noLigne * numColonnes + noCol;
            return elements.get(position);
        }
    }

    /**
     * Modifie l'element a la position noLigne, noCol par l'element passe en
     * parametre.
     *
     * @param noLigne le numero de la ligne dans cette matrice.
     * @param noCol le numero de la colonne dans cette matrice.
     * @param element le nouvel element a inserer a la position noLigne, noCol
     * dans cette matrice.
     * @throws MatriceException si noLigne ou noCol n'est pas un indice valide
     * dans cette matrice.
     */
    public void setElement(int noLigne, int noCol, double element) throws MatriceException {
        if (noCol < 0 || noCol >= numColonnes || noLigne < 0 || noLigne >= numLignes) {
            throw new MatriceException();
        } else {
            int position = noLigne * numColonnes + noCol;
            elements.set(position, element);
        }
    }

    /**
     * Retourne la ligne de cette matrice specifiee par noLigne.
     *
     * @param noLigne le numero de la ligne a retourner.
     * @return la ligne de cette matrice specifiee par noLigne.
     * @throws MatriceException si noLigne n'est pas une ligne valide dans cette
     * matrice.
     */
    public double[] getLigne(int noLigne) {
        if (noLigne < 0 || noLigne >= numLignes) {
            throw new MatriceException();
        } else {
            int position;
            double[] result = new double[numColonnes];
            for (int i = 0; i < numColonnes; i++) {
                position = noLigne * numColonnes + i;
                result[i] = elements.get(position);
            }
            return result;
        }
    }

    /**
     * Retourne la colonne de cette matrice specifiee par noCol.
     *
     * @param noCol le numero de la colonne a retourner.
     * @return la colonne de cette matrice specifiee par noCol.
     * @throws MatriceException si noCol n'est pas une colonne valide dans cette
     * matrice.
     */
    public double[] getColonne(int noCol) {
        if (noCol >= numColonnes || noCol < 0) {
            throw new MatriceException();
        } else {
            int position;
            double[] result = new double[numLignes];
            for (int i = 0; i < numLignes; i++) {
                position = i * numColonnes + noCol;
                result[i] = elements.get(position);
            }
            return result;
        }
    }

    /**
     * Remplace la ligne specifiee par noLigne de cette matrice par la ligne
     * donnee en parametre.
     *
     * @param noLigne le numero de la ligne a remplacer.
     * @param ligne la nouvelle ligne
     * @throws Matrice Exception si : - noLigne n'est pas une ligne valide dans
     * cette matrice. - ligne est null ou ne contient pas exactement
     * getNumColonnes() valeurs.
     */
    @Override
    public void remplacerLigne(int noLigne, double[] ligne) {
        if (noLigne < 0 || noLigne >= numLignes || ligne == null || ligne.length != numColonnes) {
            throw new MatriceException();
        } else {
            int position;
            for (int i = 0; i < numColonnes; i++) {
                position = noLigne * numColonnes + i;
                elements.set(position, ligne[i]);
            }
        }
    }

    /**
     * Remplace la colonne specifiee par noCol de cette matrice par la colonne
     * donnee en parametre.
     *
     * @param noCol le numero de la colonne a remplacer.
     * @param colonne la nouvelle colonne
     * @throws MatriceException si : - noCol n'est pas une colonne valide dans
     * cette matrice. - colonne est null ou ne contient pas exactement
     * getNumLignes() valeurs.
     */
    @Override
    public void remplacerColonne(int noCol, double[] colonne) {
        if (noCol >= numColonnes || noCol < 0 || colonne == null || colonne.length != numLignes) {
            throw new MatriceException();
        } else {
            int position;
            for (int i = 0; i < numLignes; i++) {
                position = i * numColonnes + noCol;
                elements.set(position, colonne[i]);
            }
        }
    }

    /**
     * Ajoute a cette matrice la ligne donnee au noLigne donne. Apres cet ajout,
     * la matrice contient une ligne de plus et la nouvelle ligne se trouve a
     * l'indice noLigne (le numero des lignes suivantes aura augmente de 1).
     *
     * @param noLigne l'indice de la nouvelle ligne apres l'ajout.
     * @param ligne la ligne a ajouter dans cette matrice, a l'indice noLigne.
     * @throws MatriceException si : - noLigne n'est pas entre 0 et
     * getNumLignes() inclusivement - ligne est null ou ne contient pas
     * exactement getNumColonnes() valeurs.
     */
    @Override
    public void ajouterLigne(int noLigne, double[] ligne) {
        if (noLigne < 0 || noLigne > numLignes || ligne == null || ligne.length != numColonnes) {
            throw new MatriceException();
        } else {
            int position;
            numLignes++;
            for (int i = 0; i < numColonnes; i++) {
                position = noLigne * numColonnes + i;
                elements.add(position, ligne[i]);
            }
        }
    }

    /**
     * Ajoute a cette matrice la colonne donnee au noCol donne. Apres cet ajout,
     * la matrice contient une colonne de plus et la nouvelle colonne se trouve
     * a l'indice noCol (le numero des colonnes suivantes aura augmente de 1).
     *
     * @param noCol l'indice de la nouvelle colonne apres l'ajout.
     * @param colonne la colonne a ajouter.
     * @throws MatriceException si : - noCol n'est pas entre 0 et
     * getNumColonnes() inclusivement. - colonne est null ou ne contient pas
     * exactement getNumLignes() valeurs.
     */
    @Override
    public void ajouterColonne(int noCol, double[] colonne) {
        if (noCol > numColonnes || noCol < 0 || colonne == null || colonne.length != numLignes) {
            throw new MatriceException();
        } else {
            int position;
            numColonnes++;
            for (int i = 0; i < numLignes; i++) {
                position = i * numColonnes + noCol;
                elements.add(position, colonne[i]);
            }
        }
    }

    /**
     * Supprime la ligne de cette matrice correspondant au noLigne donne. Apres
     * l'appel de cette methode, cette matrice contient une ligne de moins. Note
     * : on ne peut pas supprimer une ligne dans une matrice qui ne contient
     * qu'une seule ligne.
     *
     * @param noLigne le numero de la ligne a supprimer.
     * @return la ligne supprimee.
     * @throws MatriceException si : - noLigne n'est pas une ligne valide dans
     * cette matrice ou - si cette matrice ne contient qu'une seule ligne avant
     * la suppression.
     */
    public double[] supprimerLigne(int noLigne) throws MatriceException {
        if (numLignes == 1 || noLigne < 0 || noLigne >= numLignes) {
            throw new MatriceException();
        } else {
            int position;
            double[] result = new double[numColonnes];
            for (int i = 0; i < numColonnes; i++) {
                position = noLigne * numColonnes;
                result[i] = elements.remove(position);
            }
            numLignes--;
            return result;
        }
    }

    /**
     * Supprime la colonne de cette matrice correspondant au noCol donne. Apres
     * l'appel de cette methode, cette matrice contient une colonne de moins.
     * Note : on ne peut pas supprimer une colonne dans une matrice qui ne
     * contient qu'une seule colonne.
     *
     * @param noCol le numero de la colonne a supprimer.
     * @return la colonne supprimee.
     * @throws MatriceException si : - noCol n'est pas une colonne valide dans
     * cette matrice - si cette matrice ne contient qu'une seule colonne avant
     * la suppression.
     */
    public double[] supprimerColonne(int noCol) throws MatriceException {
        if (numColonnes == 1 || noCol >= numColonnes || noCol < 0) {
            throw new MatriceException();
        } else {
            int position;
            double[] result = new double[numLignes];
            for (int i = 0; i < numLignes; i++) {
                position = i * numColonnes + noCol - i;
                result[i] = elements.remove(position);
            }
            numColonnes--;
            return result;
        }
    }

    /**
     * Effectue la somme de cette Matrice avec autreMatrice donnee en parametre.
     *
     * @param autreMatrice la matrice a additionner avec cette matrice.
     * @return la matrice resultante de la somme de cette matrice avec
     * autreMatrice.
     * @throws MatriceException si autreMatrice est null ou n'est pas de meme
     * dimension que cette matrice(meme nombre de lignes et meme nombre de
     * colonnes).
     */
    @Override
    public IMatrice somme(IMatrice autreMatrice) throws MatriceException {
        if (autreMatrice == null || autreMatrice.getNumLignes() != numLignes || autreMatrice.getNumColonnes() != numColonnes) {
            System.out.println("ICI");
            throw new MatriceException();
        } else {
            IMatrice matrice = new Matrice(this);
            double add;
            for (int i = 0; i < matrice.getNumLignes(); i++) {
                for (int j = 0; j < matrice.getNumColonnes(); j++) {
                    add = matrice.getElement(i, j) + autreMatrice.getElement(i, j);
                    matrice.setElement(i, j, add);
                }
            }
            return matrice;
        }
    }

    /**
     * Calcul le produit de cette matrice par la valeur donnee.
     *
     * @param valeur la valeur de multiplication de cette matrice.
     * @return la matrice resultante du produit de cette matrice par la valeur
     * donnee.
     */
    @Override
    public IMatrice produit(double valeur) {
        IMatrice matrice = new Matrice(this);
        double mult;
        for (int i = 0; i < matrice.getNumLignes(); i++) {
            for (int j = 0; j < matrice.getNumColonnes(); j++) {
                mult = matrice.getElement(i, j) * valeur;
                matrice.setElement(i, j, mult);
            }
        }
        return matrice;
    }

    /**
     * Calcule le produit de cette Matrice (A) par autreMatrice (B) = A X B.
     *
     * @param autreMatrice la matrice a multiplier avec cette matrice.
     * @return la matrice resultante du produit de cette matrice par
     * autreMatrice. La dimension de la matrice resultante sera
     * this.getNumLignes() X autreMatrice.getNumColonnes().
     * @throws MatriceException si : - autreMatrice est null - le nombre de
     * colonnes de cette matrice n'est pas egal au nombre de lignes de
     * autreMatrice.
     */
    @Override
    public IMatrice produit(IMatrice autreMatrice) throws MatriceException {
        if (autreMatrice == null || numColonnes != autreMatrice.getNumLignes()) {
            throw new MatriceException();
        } else {
            IMatrice mult = new Matrice(numLignes, autreMatrice.getNumColonnes() , 0);
            for (int i = 0; i < mult.getNumColonnes(); i++) {
                for (int j = 0; j < mult.getNumLignes(); j++) {
                    mult.setElement(j, i, multTab(this.getLigne(j), autreMatrice.getColonne(i)));
                }
            }
            return mult;
        }
    }

    /**
     * Construit la transposee de cette matrice.
     *
     * @return la transposee de cette matrice.
     */
    @Override
    public IMatrice transposee() {
        IMatrice matrice = new Matrice(this.numColonnes, this.numLignes, 0);
        double mult;
        for (int i = 0; i < matrice.getNumLignes(); i++) {
            for (int j = 0; j < matrice.getNumColonnes(); j++) {
                mult = this.getElement(j, i);
                matrice.setElement(i, j, mult);
            }
        }
        return matrice;
    }
    
    private double multTab(double[] tab1, double[] tab2){
        double result = 0;
        for (int i = 0; i < tab1.length; i++) {
            result += tab1[i] * tab2[i];
        }
        return result;
    }
}
