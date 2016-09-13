
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;


/**
 * Classe implémentant l'interface IMatrice qui effectue des opérations
 * sur une matrice implémentée avec une liste chainée.
 * @author Thomas Castonguay-Gagnon
 * @author Arnaud Dupré
 * @author CAST10059303
 * @author DUPA10029407
 * @version 1.0
 */
public class TP3 extends WindowAdapter implements ActionListener {

	/************************************
	 * CONSTANTES DE CLASSE
	 ************************************/
	//largeur et hauteur de l'ecran de l'ordinateur
	public final static int LARG_ECRAN = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int HAUT_ECRAN = Toolkit.getDefaultToolkit().getScreenSize().height;
	//largeur et hauteur de la fenetre principale
	public final static int LARG_FENETRE = 1000;
	public final static int HAUT_FENETRE = 630;

	//fichier d'enregistrement des matrices (à la racine du projet)
	public final static String FIC_MATRICES = "matrices.txt";

	//titre de la fenêtre principale
	public final static String TITRE_FENETRE = "CALCULATEUR MATRICES";

	// Instructions
	private final static String INSTRUCTIONS = "Créez une nouvelle matrice en cliquant sur le bouton [Nouvelle] ci-dessous ou choisissez une matrice existante dans la liste déroulante ci-dessus.";


	/************************************
	 * VARIABLES D'INSTANCE
	 ************************************/
	//Liste de matrices + nom
	private ArrayList<Object> matrices;
	private String nameMatriceZone1;
	private Matrice matriceZone1;
	private String nameMatriceZone2;
	private Matrice matriceZone2;
	private String nameMatriceZone4;
	private Matrice matriceZone4;

	private int nColZone1;
	private int nColZone2;
	private int nLignesZone1;
	private int nLignesZone2;



	/************************************
	 * COMPOSANTS GRAPHIQUES
	 ************************************/
	//Fenetre principale
	private JFrame fenetre;
	//Contenu de la fenetre
	private Container contenuFenetre;
	//Zone matrice de gauche
	private JPanel zone1;
	//Zone matrice de groite
	private JPanel zone2;
	//Zone de calcul entre 2 matrices
	private JPanel zone3;
	//Zone de résultat de l'opération entre 2 matrices
	private JPanel zone4;
	//Panneau de boutons Zone1
	private JPanel panel1BoutonsZone1;
	private JPanel panel2BoutonsZone1;
	//Panneau de boutons Zone2
	private JPanel panel1BoutonsZone2;
	private JPanel panel2BoutonsZone2;
	//Sélection de matrice de gauche
	private JPanel selecteurZone1;
	//Sélection de matrice de droite
	private JPanel selecteurZone2;
	//Selecteur de matrice de gauche
	private JComboBox selectMatriceZone1;
	//Selecteur de matrice de droite
	private JComboBox selectMatriceZone2;

	// Composants graphiques
	private JButton supprimerZone1;
	private JButton supprimerZone2;
	private JButton addition;
	private JButton multiplication;
	private JButton[] boutonsTabZone1;
	private JButton[] boutonsTabZone2;
	private JLabel multLabelZone1;
	private JLabel multLabelZone2;
	private JTextField multScalaireZone1;
	private JTextField multScalaireZone2;
	private JTextArea instructionsZone1;
	private JTextArea instructionsZone2;

	private JPanel panelMatriceZone1;
	private JPanel panelMatriceZone2;
	private JTextField[][] fieldsMatriceZone1;
	private JTextField[][] fieldsMatriceZone2;

	private JPanel resultZone4;
	private JTextArea nameZone4;
	private JTextArea matriceAffZone4;
	private JScrollPane justePourToi;
	private JButton saveZone4;

	// État de la matrice en mode édition
	private boolean saveZone1;
	private boolean saveZone2;
	private boolean opZone1;
	private boolean opZone2;


	/*****************************************************************
	 * Nouvelle Matrice
	 *****************************************************************/

	// Zone 1
	private JLabel nouvMatriceLabelLignesZone1;   
	private JComboBox nouvMatriceBoxLignesZone1;
	private JLabel nouvMatriceLabelColonnesZone1;
	private JComboBox nouvMatriceBoxColonnesZone1;
	private JPanel nouvMatricePanelZone1;
	private JPanel nouvMatriceSousPanelZone1;
	private JButton nouvMatriceButtonZone1;

	// Zone 2
	private JLabel nouvMatriceLabelLignesZone2;   
	private JComboBox nouvMatriceBoxLignesZone2;
	private JLabel nouvMatriceLabelColonnesZone2;
	private JComboBox nouvMatriceBoxColonnesZone2;
	private JPanel nouvMatricePanelZone2;
	private JPanel nouvMatriceSousPanelZone2;
	private JButton nouvMatriceButtonZone2;

	/**
	 * Constructeur qui initialise l'application.
	 */
	public TP3() {
		init(); 
	}

	/**
	 * Initialisation des composants graphiques.
	 */
	private void init() {
		fileScanner();
		//FENETRE JFRAME
		fenetre = new JFrame(TITRE_FENETRE);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		fenetre.setLayout(null);
		//centrer la fenetre principale dans l'écran
		fenetre.setBounds(LARG_ECRAN / 2 - LARG_FENETRE / 2, HAUT_ECRAN / 2 - HAUT_FENETRE / 2, LARG_FENETRE, HAUT_FENETRE);

		contenuFenetre = fenetre.getContentPane();

		/*
		 * Initialisation des zones
		 */
		zone1 = zone(0, 0, 460, 375);
		zone3 = zone(460, 0, 80, 375);
		zone2 = zone(540, 0, 460, 375);
		zone4 = zone(0, 375, 1000, 255);

		/*
		 * Initialisation des boutons.
		 */
		initButtonsZone1();
		initButtonsZone2();
		initSelectionZone1();
		initSelectionZone2();
		initInstructionsZone1();
		initInstructionsZone2();
		addition = bouton("+", 15, 130, 50, 25);
		addition.setEnabled(false);
		addition.addActionListener(this);
		multiplication = bouton("X", 15, 180, 50, 25);
		multiplication.setEnabled(false);
		multiplication.addActionListener(this);

		/*
		 * Ajout des composants dans zone1
		 */
		zone1.add(selecteurZone1);
		zone1.add(panel1BoutonsZone1);
		zone1.add(panel2BoutonsZone1);

		/*
		 * Ajout des composants dans la zone2
		 */
		zone2.add(selecteurZone2);
		zone2.add(panel1BoutonsZone2);
		zone2.add(panel2BoutonsZone2);

		/*
		 * Ajout des composants dans la zone3
		 */
		zone3.add(addition);
		zone3.add(multiplication);

		initZone4();

		/*
		 * Ajout des zones à la fenètre
		 */
		contenuFenetre.add(zone1);
		contenuFenetre.add(zone3);
		contenuFenetre.add(zone2);
		contenuFenetre.add(zone4);

		//Laisser cette instruction à la fin de l'initialisation des composants
		//graphiques.
		fenetre.setVisible(true);


		/*************************
		 * ÉCOUTEURS
		 *************************/
		//ajout d'un ecouteur sur la fenetre
		fenetre.addWindowListener(this); //voir redef methode windowClosing

	}  


	/** 
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		/*For testing now */
		if (event == boutonsTabZone1[0]) {
			initNouvelleMatriceZone1();
		} else if (event == boutonsTabZone1[1] && saveZone1) {
			saveZone1();
		} else if (event == boutonsTabZone1[1]) {
			editionZone1();
		} else if (event == boutonsTabZone2[0]) {
			initNouvelleMatriceZone2();
		} else if (event == boutonsTabZone2[1] && saveZone2) {
			saveZone2();
		} else if (event == boutonsTabZone2[1]) {
			editionZone2();
		} else if (event == selectMatriceZone1) {
			selectionnerMatriceZone1();
		} else if (event == selectMatriceZone2) {
			selectionnerMatriceZone2();
		} else if (event == supprimerZone1) { 
            deleteMatriceZone1();
        } else if (event == supprimerZone2) { 
            deleteMatriceZone2();
		} else if (event == nouvMatriceButtonZone1) {
			creerNouvMatriceZone1();
		} else if (event == nouvMatriceButtonZone2) {
			creerNouvMatriceZone2();
		} else if (event == boutonsTabZone1[2]) {
			ajoutLigneZone1();
		} else if (event == boutonsTabZone2[2]) {
			ajoutLigneZone2();
		} else if (event == boutonsTabZone1[3]) {
			ajoutColonneZone1();
		} else if (event == boutonsTabZone2[3]) {
			ajoutColonneZone2();
		} else if (event == boutonsTabZone1[4]){
			suppLigneZone1();
		} else if (event == boutonsTabZone2[4]){
			suppLigneZone2();
		} else if (event == boutonsTabZone1[5]){
			suppColonneZone1();
		} else if (event == boutonsTabZone2[5]){
			suppColonneZone2();
		} else if (event == multScalaireZone1){
			multValeurZone1();
		} else if (event == multScalaireZone2){
			multValeurZone2();
		} else if (event == boutonsTabZone1[6]){
			transposeZone1();
		} else if (event == boutonsTabZone2[6]){
			transposeZone2();
		} else if (event == addition){
			addMatrice();
		} else if (event == multiplication){
			multMatrice();
		} else if (event == saveZone4){
			sauvegarderZone4();
		}
	}

	/**
	 * A la fermeture de la fenetre, enregistrement des toutes les matrices
	 * dans le fichier FIC_MATRICES.
	 * @param e l'evenement de fermeture de fenetre.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			FileWriter fw = new FileWriter(FIC_MATRICES);
			BufferedWriter br = new BufferedWriter(fw);
			String ligne = "";
			Matrice matrice;
			for (int d = 0; d < matrices.size(); d = d+2) {
				ligne += matrices.get(d);
				ligne += ":";
				matrice = (Matrice) matrices.get(d+1);
				ligne += matrice.getNumLignes();
				ligne += ";";
				ligne += matrice.getNumColonnes();
				ligne += ";";
				for (int i = 0; i < matrice.getNumLignes(); i++) {
					for (int j = 0; j < matrice.getNumColonnes(); j++) {
						ligne += matrice.getElement(i, j);
						ligne += ";";
					}
				}
				br.write(ligne);
				ligne = "";
				br.newLine();
			}
			br.close();
			fw.close();
		} catch (IOException e1) { }
	}


	public static void main (String [] args) {
		new TP3();
	}
	/**
	 * Lecture du fichier texte matrice.txt
	 */
	private void fileScanner() {
		try {
			FileReader fr = new FileReader(FIC_MATRICES);
			BufferedReader br = new BufferedReader(fr);
			matrices = new ArrayList<>();
			String ligneLue;
			while (br.ready()){
				ligneLue = br.readLine();
				traiterLigne(ligneLue);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			matrices = new ArrayList<>();
			// A supprimer
			System.out.println("FNF Erreur"); 
		} catch (IOException e){
			System.out.println("IO ERROR");
		}

	}

	/**
	 * Méthode qui traite la ligne lue dans le fichier texte. 
	 * La méthode ajoute le nom de la matrice en position paire dans l'arraylist matrices.
	 * L'objet matrice est ajouté en position impaire.
	 * @param ligneLue dans le fichier texte
	 */
	private void traiterLigne(String ligneLue) {
		String name = "";
		double[] valeurs = null;
		int nbCol = -1;
		int nbLignes = -1;
		Matrice matrice;
		String[] bufferName = ligneLue.split(":");
		name = bufferName[0];
		String[] buffer = bufferName[1].split(";");
		valeurs = new double[buffer.length-2];
		for (int i = 0; i < buffer.length; i++) {
			if (i == 0){
				nbLignes = Integer.parseInt(buffer[i]);
			} else if (i == 1){
				nbCol = Integer.parseInt(buffer[i]);
			} else {
				valeurs[i-2] = Double.parseDouble(buffer[i]);
			}
		}
		matrice = new Matrice(nbLignes, nbCol, valeurs);
		matrices.add(name);
		matrices.add(matrice);
	}

	/**
	 * Crontruit un pannel avec layout null et les positions et dimentions écrites
	 * @param x position du panel en X
	 * @param y position du panel en Y
	 * @param largeur Largeur du panel
	 * @param hauteur Hauteur du panel
	 * @return un JPanel selon les tailles et la position en paramètre.
	 */
	private JPanel zone(int x, int y, int largeur, int hauteur){
		JPanel pannel = new JPanel(null);
		pannel.setSize(largeur, hauteur);
		pannel.setLocation(x, y);
		return pannel;
	}

	/**
	 * Crée un bouton avec le label donné.
	 * @param name Label du bouton
	 * @param x Position en x du bouton
	 * @param y Position en y du bouton
	 * @param largeur Largeur du bouton
	 * @param hauteur Hauteur du bouton
	 * @return Un JButton avec les paramètres donnés.
	 */
	private JButton bouton(String name, int x, int y, int largeur, int hauteur){
		JButton bouton = new JButton(name);
		bouton.setSize(largeur, hauteur);
		bouton.setLocation(x, y);
		return bouton;
	}

	/**
	 * Initialise les bouttons de la zone 1
	 */
	private void initButtonsZone1(){
		// Panel des boutons
		panel1BoutonsZone1 = zone(0, 300, 460, 40);
		panel2BoutonsZone1 = zone(0, 335, 460, 40);
		panel1BoutonsZone1.setLayout(new FlowLayout());
		panel2BoutonsZone1.setLayout(new FlowLayout());

		// Tableau de boutons
		boutonsTabZone1 = new JButton[7];

		// Bouton nouvelle matrice
		boutonsTabZone1[0] = new JButton("Nouvelle");
		panel1BoutonsZone1.add(boutonsTabZone1[0]);

		// Bouton mode édition / sauvegarder
		boutonsTabZone1[1] = new JButton("Éditer");
		boutonsTabZone1[1].setEnabled(false);
		panel1BoutonsZone1.add(boutonsTabZone1[1]);

		// Mode edition, ajouter une ligne
		boutonsTabZone1[2] = new JButton("+ Ligne");
		boutonsTabZone1[2].setEnabled(false);

		// Mode edition, ajouter une colonne
		panel1BoutonsZone1.add(boutonsTabZone1[2]);
		boutonsTabZone1[3] = new JButton("+ Colonne");
		boutonsTabZone1[3].setEnabled(false);
		panel1BoutonsZone1.add(boutonsTabZone1[3]);

		// Mode edition, supprimer une ligne
		boutonsTabZone1[4] = new JButton("- Ligne");
		boutonsTabZone1[4].setEnabled(false);
		panel2BoutonsZone1.add(boutonsTabZone1[4]);

		// Mode edition, supprimer une colonne
		boutonsTabZone1[5] = new JButton("- Colonne");
		boutonsTabZone1[5].setEnabled(false);
		panel2BoutonsZone1.add(boutonsTabZone1[5]);

		// Mode opération, tansposée de la matrice
		boutonsTabZone1[6] = new JButton("Transposée");
		boutonsTabZone1[6].setEnabled(false);
		panel2BoutonsZone1.add(boutonsTabZone1[6]);

		// Mode opération, multiplication par un scalaire.
		multLabelZone1 = new JLabel("Mult. Par");
		multLabelZone1.setEnabled(false);
		panel2BoutonsZone1.add(multLabelZone1);
		multScalaireZone1 = new JTextField(4);
		multScalaireZone1.setEnabled(false);
		panel2BoutonsZone1.add(multScalaireZone1);

		// Action listener
		multScalaireZone1.addActionListener(this);
		for (int i = 0; i < boutonsTabZone1.length; i++) {
			boutonsTabZone1[i].addActionListener(this);
		}
	}

	/**
	 * Initialise les bouttons de la zone 3
	 */
	private void initButtonsZone2(){
		// Panel des boutons
		panel1BoutonsZone2 = zone(0, 300, 460, 40);
		panel2BoutonsZone2 = zone(0, 335, 460, 40);
		panel1BoutonsZone2.setLayout(new FlowLayout());
		panel2BoutonsZone2.setLayout(new FlowLayout());

		// Tableau de boutons
		boutonsTabZone2 = new JButton[7];

		// Bouton nouvelle matrice
		boutonsTabZone2[0] = new JButton("Nouvelle");
		panel1BoutonsZone2.add(boutonsTabZone2[0]);

		// Bouton mode édition / sauvegarder
		boutonsTabZone2[1] = new JButton("Éditer");
		boutonsTabZone2[1].setEnabled(false);
		panel1BoutonsZone2.add(boutonsTabZone2[1]);

		// Mode edition, ajouter une ligne
		boutonsTabZone2[2] = new JButton("+ Ligne");
		boutonsTabZone2[2].setEnabled(false);
		panel1BoutonsZone2.add(boutonsTabZone2[2]);

		// Mode edition, ajouter une colonne
		boutonsTabZone2[3] = new JButton("+ Colonne");
		boutonsTabZone2[3].setEnabled(false);
		panel1BoutonsZone2.add(boutonsTabZone2[3]);

		// Mode edition, supprimer une ligne
		boutonsTabZone2[4] = new JButton("- Ligne");
		boutonsTabZone2[4].setEnabled(false);
		panel2BoutonsZone2.add(boutonsTabZone2[4]);

		// Mode edition, supprimer colonne
		boutonsTabZone2[5] = new JButton("- Colonne");
		boutonsTabZone2[5].setEnabled(false);
		panel2BoutonsZone2.add(boutonsTabZone2[5]);

		// Mode opération, transposée
		boutonsTabZone2[6] = new JButton("Transposée");
		boutonsTabZone2[6].setEnabled(false);
		panel2BoutonsZone2.add(boutonsTabZone2[6]);

		// Mode opération, multiplication par un scalaire
		multLabelZone2 = new JLabel("Mult. Par");
		multLabelZone2.setEnabled(false);
		panel2BoutonsZone2.add(multLabelZone2);
		multScalaireZone2 = new JTextField(4);
		multScalaireZone2.setEnabled(false);
		panel2BoutonsZone2.add(multScalaireZone2);

		// Action listener
		multScalaireZone2.addActionListener(this);
		for (int i = 0; i < boutonsTabZone1.length; i++) {
			boutonsTabZone2[i].addActionListener(this);
		}
	}

	/**
	 * Initialise le sélecteur de matrice dans la zone1 selon les matrices présentes dans l'arraylist
	 */
	private void initMatriceSelectZone1(){
		selectMatriceZone1 = new JComboBox();
		selectMatriceZone1.setLocation(135, 12);
		selectMatriceZone1.setSize(100,25);
		selectMatriceZone1.addItem("");
		selectMatriceZone1.setSelectedItem("");
		for (int i = 0; i < matrices.size(); i++) {
			if (i%2 == 0) {
				selectMatriceZone1.addItem(matrices.get(i));
			}
		}
		selectMatriceZone1.addActionListener(this);
	}

	private void refreshSelectorALL(){
		selecteurZone1.setVisible(false);
		selecteurZone2.setVisible(false);
		initSelectionZone1();
		initSelectionZone2();
		zone1.add(selecteurZone1);
		zone2.add(selecteurZone2);
		fenetre.revalidate();
	}

	/**
	 * Initialise la sélection de la matrice dans la zone1. Bouton supprimer + Sélecteur
	 */
	private void initSelectionZone1(){
		selecteurZone1 = zone(0, 0, 460, 49);
		supprimerZone1 = bouton("Supprimer", 250, 12, 95, 25);
		supprimerZone1.setEnabled(false);
		supprimerZone1.addActionListener(this);
		initMatriceSelectZone1();
		selecteurZone1.add(supprimerZone1);
		selecteurZone1.add(selectMatriceZone1);
		selecteurZone1.revalidate();
		zone1.revalidate();
	}

	/**
	 * Initialise le sélecteur de matrice dans la zone2 selon les matrices présentes dans l'arraylist
	 */
	private void initMatriceSelectZone2(){
		selectMatriceZone2 = new JComboBox();
		selectMatriceZone2.setLocation(135, 12);
		selectMatriceZone2.setSize(100,25);
		selectMatriceZone2.addItem("");
		selectMatriceZone2.setSelectedItem("");
		for (int i = 0; i < matrices.size(); i++) {
			if (i%2 == 0) {
				selectMatriceZone2.addItem(matrices.get(i));
			}
		}
		selectMatriceZone2.addActionListener(this);
	}

	/**
	 * Initialise la sélection de la matrice dans la zone2. Boutton supprimer + sélecteur
	 */
	private void initSelectionZone2(){
		selecteurZone2 = zone(0, 0, 460, 49);
		supprimerZone2 = bouton("Supprimer", 250, 12, 95, 25);
		supprimerZone2.setEnabled(false);
		supprimerZone2.addActionListener(this);
		initMatriceSelectZone2();
		selecteurZone2.add(supprimerZone2);
		selecteurZone2.add(selectMatriceZone2);
		selecteurZone2.revalidate();
		zone2.revalidate();
	}

	/**
	 * Initialise les instructions de la Zone1
	 */
	private void initInstructionsZone1(){
		instructionsZone1 = new JTextArea();
		instructionsZone1.setText(INSTRUCTIONS);
		instructionsZone1.setEditable(false);
		instructionsZone1.setLineWrap(true);
		instructionsZone1.setWrapStyleWord(true);
		Border border = BorderFactory.createEmptyBorder(5, 25, 5, 25);
		instructionsZone1.setBorder(border);
		instructionsZone1.setBounds(80, 125, 300, 80);
		zone1.add(instructionsZone1);
		zone1.revalidate();
	}

	/**
	 * Initialise les instructions de la zone2
	 */
	private void initInstructionsZone2(){
		instructionsZone2 = new JTextArea();
		instructionsZone2.setText(INSTRUCTIONS);
		instructionsZone2.setEditable(false);
		instructionsZone2.setLineWrap(true);
		instructionsZone2.setWrapStyleWord(true);
		Border border = BorderFactory.createEmptyBorder(5, 25, 5, 25);
		instructionsZone2.setBorder(border);
		instructionsZone2.setBounds(80, 125, 300, 80);
		zone2.add(instructionsZone2);
		zone2.revalidate();
	}

	/*********************************************************************************
	 * MATRICE 1
	 *********************************************************************************/

	/**
	 * Intialise la représentation graphique de la matrice dans la zone 1
	 */
	private void initMatriceZone1(){
		if(panelMatriceZone1 != null){
			resetMatriceZone1();
		}
		int largeur = 55 * nColZone1;
		int hauteur = 30 * nLignesZone1;
		int positionX = 5 + ((440-largeur)/2);
		int positionY = 50 + ((240-hauteur)/2);
		panelMatriceZone1 = new JPanel(new GridLayout(nLignesZone1, nColZone1 , 5 , 5));
		panelMatriceZone1.setBounds(positionX, positionY, largeur, hauteur);
		panelMatriceZone1.setVisible(true);
		fieldsMatriceZone1(nColZone1, nLignesZone1);
		panelMatriceZone1.repaint();
		zone1.repaint();
	}

	/**
	 * Initialise les champs de la matrice avec les valeurs de celle-ci
	 */
	private void fieldsMatriceZone1(int nCol, int nLignes){
		fieldsMatriceZone1 = new JTextField[nLignes][nCol];
		for (int i = 0; i < nLignes; i++) {
			for (int j = 0; j < nCol; j++) {
				fieldsMatriceZone1[i][j] = new JTextField("" + matriceZone1.getElement(i, j));
				fieldsMatriceZone1[i][j].setEditable(false);
				fieldsMatriceZone1[i][j].setBackground(Color.white);
				panelMatriceZone1.add(fieldsMatriceZone1[i][j]);

			}
		}
	}  

	/*********************************************************************************
	 * MATRICE 2
	 *********************************************************************************/

	/**
	 * Intialise la représentation graphique de la matrice dans la zone 1
	 */
	private void initMatriceZone2(){
		if(panelMatriceZone2 != null){
			resetMatriceZone2();
		}
		int largeur = 55 * nColZone2;
		int hauteur = 30 * nLignesZone2;
		int positionX = 5 + ((440-largeur)/2);
		int positionY = 50 + ((240-hauteur)/2);
		panelMatriceZone2 = new JPanel(new GridLayout(nLignesZone2, nColZone2 , 5 , 5));
		panelMatriceZone2.setBounds(positionX, positionY, largeur, hauteur);
		fieldsMatriceZone2(nColZone2, nLignesZone2);
		panelMatriceZone2.revalidate();
		zone2.revalidate();
	}

	/**
	 * Initialise les champs de la matrice avec les valeurs de celle-ci
	 */
	private void fieldsMatriceZone2(int nCol, int nLignes){
		fieldsMatriceZone2 = new JTextField[nLignes][nCol];
		for (int i = 0; i < nLignes; i++) {
			for (int j = 0; j < nCol; j++) {
				fieldsMatriceZone2[i][j] = new JTextField("" + matriceZone2.getElement(i, j));
				fieldsMatriceZone2[i][j].setEditable(false);
				fieldsMatriceZone2[i][j].setBackground(Color.white);
				panelMatriceZone2.add(fieldsMatriceZone2[i][j]);
			}
		}
	}

	/*********************************************************************************
	 * Actions
	 *********************************************************************************/

	/**
	 * Action lorsqu'une matrice est selectionnée en zone1
	 */
	private void selectionnerMatriceZone1() {
		if(nouvMatriceSousPanelZone1 != null){
			resetNouvMatriceZone1();
		}
		String select = (String) selectMatriceZone1.getSelectedItem();
		if (select.equals("")){
			instructionsZone1.setVisible(false);
			matriceZone1 = null;
			nameMatriceZone1 = null;
			supprimerZone1.setEnabled(false);
			resetMatriceZone1();
			initInstructionsZone1();
			resetOperationZone1();
			resetEditionZone1();
		}else {
			for (int i = 0; i < matrices.size(); i++) {
				if(select.equals(matrices.get(i))){
					nameMatriceZone1 = (String) matrices.get(i);
					matriceZone1 = (Matrice) matrices.get(i+1);
					nColZone1 = matriceZone1.getNumColonnes();
					nLignesZone1 = matriceZone1.getNumLignes();
					supprimerZone1.setEnabled(true);
					operationZone1();
				}
			}
		}
	}

	/**
	 * Action lorsqu'une matrice est selectionnée en zone2
	 */
	private void selectionnerMatriceZone2() {
		if(nouvMatriceSousPanelZone2 != null){
			resetNouvMatriceZone2();
		}
		String select = (String) selectMatriceZone2.getSelectedItem();
		if (select.equals("")){
			instructionsZone2.setVisible(false);
			matriceZone2 = null;
			nameMatriceZone2 = null;
			supprimerZone2.setEnabled(false);
			resetMatriceZone2();
			initInstructionsZone2();
			resetOperationZone2();
			resetEditionZone2();
		}else {
			for (int i = 0; i < matrices.size(); i++) {
				if(select.equals(matrices.get(i))){
					matriceZone2 = (Matrice) matrices.get(i+1);
					nameMatriceZone2 = (String) matrices.get(i);
					nColZone2 = matriceZone2.getNumColonnes();
					nLignesZone2 = matriceZone2.getNumLignes();
					supprimerZone2.setEnabled(true);
					operationZone2();
				}
			}
		}
	}

	/**
	 * Une fois la matrice selectionnée en zone1, la zone entre en mode opération
	 */
	private void operationZone1(){
		resetMatriceZone1();
		resetEditionZone1();
		boutonsTabZone1[1].setEnabled(true);
		multLabelZone1.setEnabled(true);
		multScalaireZone1.setBackground(Color.yellow);
		multScalaireZone1.setEnabled(true);
		boutonsTabZone1[6].setEnabled(true);
		instructionsZone1.setVisible(false);
		initMatriceZone1();
		zone1.add(panelMatriceZone1);
		fenetre.revalidate();
		fenetre.repaint();
		opZone1 = true;
		if (opZone1 && opZone2){
			addition.setEnabled(true);
			multiplication.setEnabled(true);
		}
	}

	private void resetOperationZone1(){
		boutonsTabZone1[1].setEnabled(false);
		multLabelZone1.setEnabled(false);
		multScalaireZone1.setBackground(Color.white);
		multScalaireZone1.setEnabled(false);
		boutonsTabZone1[6].setEnabled(false);
		opZone1 = false;
		addition.setEnabled(false);
		multiplication.setEnabled(false);
	}
	/**
	 * Une fois la matrice selectionnée en zone2, la zone entre en mode opération
	 */
	private void operationZone2(){
		resetMatriceZone2();
		resetEditionZone2();
		boutonsTabZone2[1].setEnabled(true);
		multLabelZone2.setEnabled(true);
		multScalaireZone2.setBackground(Color.yellow);
		multScalaireZone2.setEnabled(true);
		boutonsTabZone2[6].setEnabled(true);
		instructionsZone2.setVisible(false);
		initMatriceZone2();
		zone2.add(panelMatriceZone2);
		zone2.repaint();
		opZone2 = true;
		if (opZone1 && opZone2){
			addition.setEnabled(true);
			multiplication.setEnabled(true);
		}
	}

	private void resetOperationZone2(){
		boutonsTabZone2[1].setEnabled(false);
		multLabelZone2.setEnabled(false);
		multScalaireZone2.setBackground(Color.white);
		multScalaireZone2.setEnabled(false);
		boutonsTabZone2[6].setEnabled(false);
		opZone2 = false;
		addition.setEnabled(false);
		multiplication.setEnabled(false);
	}
	/**
	 * Mode d'édition de la zone1, permet d'effectuer des modifications sur la matrice en zone1
	 */
	private void editionZone1(){
		resetOperationZone1();
		boutonsTabZone1[1].setText("Sauvegarder");
		for (int i = 1; i < 6; i++) {
			boutonsTabZone1[i].setEnabled(true);
		}
		saveZone1 = true;
		for (int i = 0; i < nLignesZone1; i++) {
			for (int j = 0; j < nColZone1; j++) {
				fieldsMatriceZone1[i][j].setBackground(Color.yellow);
				fieldsMatriceZone1[i][j].setEditable(true);
			}
		}
	}

	private void resetEditionZone1(){
		boutonsTabZone1[1].setText("Éditer");
		for (int i = 2; i < 6; i++) {
			boutonsTabZone1[i].setEnabled(false);
		}
		saveZone1 = false;

	}

	/**
	 * Lorsque le bouton sauvegarder de la zone1 est appuyé, sauvegarde les modifications effectuées dans la matrice.
	 * La zone retourne en mode opération
	 */
	private void saveZone1(){
		try {
			String buffer;
			String name;
			double[] elements = new double[nLignesZone1*nColZone1];
			for (int i = 0; i < nLignesZone1; i++) {
				for (int j = 0; j < nColZone1; j++) {
					buffer = fieldsMatriceZone1[i][j].getText();
					elements[i*nColZone1 +j] = Double.parseDouble(buffer);
				}
			}
			Matrice save = new Matrice(nLignesZone1, nColZone1, elements);
			do {
				name =  JOptionPane.showInputDialog("Nom matrice :"); 
				if (name == null){
					throw new MatriceException();
				}
				if(matrices.contains(name)){
					JOptionPane.showMessageDialog(null, "Nom de matrice existant", "Erreur", JOptionPane.ERROR_MESSAGE);
					name = null;
				} else if (name.length()>5 || name.isEmpty()){
					JOptionPane.showMessageDialog(fenetre, "Le nom de la matrice doit contenir entre 1 et 5 caractères !", "Erreur", JOptionPane.ERROR_MESSAGE);
					name = null;
				}
			} while (name == null );

			matrices.add(name);
			matrices.add(save);

			refreshSelectorALL();

			if(nameMatriceZone2 !=null && opZone2){
				selectMatriceZone2.setSelectedItem(nameMatriceZone2);
			}
			selectMatriceZone1.setSelectedItem(name);

			matriceZone1 = save;
			nColZone1 = save.getNumColonnes();
			nLignesZone1 = save.getNumLignes();

			boutonsTabZone1[1].setText("Éditer");
			for (int i = 1; i < 6; i++) {
				boutonsTabZone1[i].setEnabled(false);
			}
			saveZone1 = false;
			operationZone1();
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Un ou plusieurs champs sont invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (MatriceException e){

		}
	}

	/**
	 * Mode d'édition de la zone2, permet d'effectuer des modifications sur la matrice en zone2
	 */
	private void editionZone2(){
		resetOperationZone2();
		boutonsTabZone2[1].setText("Sauvegarder");
		for (int i = 1; i < 6; i++) {
			boutonsTabZone2[i].setEnabled(true);
		}
		saveZone2 = true;
		for (int i = 0; i < nLignesZone2; i++) {
			for (int j = 0; j < nColZone2; j++) {
				fieldsMatriceZone2[i][j].setBackground(Color.yellow);
				fieldsMatriceZone2[i][j].setEditable(true);
			}
		}
	}

	private void resetEditionZone2(){
		boutonsTabZone2[1].setText("Éditer");
		for (int i = 2; i < 6; i++) {
			boutonsTabZone2[i].setEnabled(false);
		}
		saveZone2 = false;
	}

	/**
	 * Lorsque le bouton sauvegarder de la zone2 est appuyé, sauvegarde les modifications effectuées dans la matrice.
	 * La zone retourne en mode opération
	 */
	private void saveZone2(){
		try {
			String buffer;
			String name;
			double[] elements = new double[nLignesZone2*nColZone2];
			for (int i = 0; i < nLignesZone2; i++) {
				for (int j = 0; j < nColZone2; j++) {
					buffer = fieldsMatriceZone2[i][j].getText();
					elements[i*nColZone2 + j] = Double.parseDouble(buffer);
				}
			}
			Matrice save = new Matrice(nLignesZone2, nColZone2, elements);
			do {
				name =  JOptionPane.showInputDialog("Nom matrice :"); 
				if (name == null){
					throw new MatriceException();
				}
				if(matrices.contains(name)){
					JOptionPane.showMessageDialog(null, "Nom de matrice existant", "Erreur", JOptionPane.ERROR_MESSAGE);
					name = null;
				} else if (name.length()>5 || name.isEmpty()){
					JOptionPane.showMessageDialog(fenetre, "Le nom de la matrice doit contenir entre 1 et 5 caractères !", "Erreur", JOptionPane.ERROR_MESSAGE);
					name = null;
				}
			} while (name == null );

			matrices.add(name);
			matrices.add(save);

			refreshSelectorALL();

			if(nameMatriceZone1 !=null && opZone1){
				selectMatriceZone1.setSelectedItem(nameMatriceZone1);
			}
			selectMatriceZone2.setSelectedItem(name);

			matriceZone2 = save;
			nColZone2 = save.getNumColonnes();
			nLignesZone2 = save.getNumLignes();

			boutonsTabZone2[1].setText("Éditer");
			for (int i = 1; i < 6; i++) {
				boutonsTabZone2[i].setEnabled(false);
			}
			saveZone2 = false;
			operationZone2();
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Un ou plusieurs champs sont invalides", "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (MatriceException e){

		}

	}

	/**
	 * Vide le panel de la matrice de la zone 1
	 */
	private void resetMatriceZone1(){
		if(panelMatriceZone1 != null){
			panelMatriceZone1.setVisible(false);
			panelMatriceZone1.revalidate();
			panelMatriceZone1 = null;
			zone1.revalidate();
		}
	}

	private void deleteMatriceZone1(){
		resetMatriceZone1();
		resetEditionZone1();
		resetOperationZone1();
		String name = (String) selectMatriceZone1.getSelectedItem();
		int index = matrices.indexOf(name);
		matriceZone1 = null;
		nameMatriceZone1 = null;
		matrices.remove(index);
		matrices.remove(index);
		refreshSelectorALL();
		if(nameMatriceZone2 !=null){
			selectMatriceZone2.setSelectedItem(nameMatriceZone2);
		}
		resetMatriceZone1();
		initInstructionsZone1();
		resetOperationZone1();
		resetEditionZone1();
	}
	/**
	 * Vide le panel de la matrice de la zone 2
	 */
	private void resetMatriceZone2(){
		if(panelMatriceZone2 != null){
			panelMatriceZone2.setVisible(false);
			panelMatriceZone2.revalidate();
			panelMatriceZone2 = null;
			zone2.revalidate();
		}
	}

	private void deleteMatriceZone2(){
		resetMatriceZone2();
		resetEditionZone2();
		resetOperationZone2();
		String name = (String) selectMatriceZone2.getSelectedItem();
		int index = matrices.indexOf(name);
		matriceZone2 = null;
		nameMatriceZone2 = null;
		matrices.remove(index);
		matrices.remove(index);
		refreshSelectorALL();
		if(nameMatriceZone1 !=null){
			selectMatriceZone1.setSelectedItem(nameMatriceZone1);
		}
		resetMatriceZone1();
		initInstructionsZone1();
		resetOperationZone1();
		resetEditionZone1();
	}

	private void initNouvelleMatriceZone1() {
		int[]tab = {1,2,3,4,5,6,7,8};                
		instructionsZone1.setVisible(false);
		selectMatriceZone1.setSelectedItem("");
		resetMatriceZone1();

		nouvMatriceSousPanelZone1 = new JPanel (null);
		nouvMatriceSousPanelZone1.setBounds(105,125,250,95);

		nouvMatricePanelZone1 = new JPanel ();
		nouvMatricePanelZone1.setLayout(new GridLayout(2, 2, 0, 10));
		nouvMatricePanelZone1.setBounds(0,0,250,60);

		instructionsZone1.setVisible(false);

		nouvMatriceLabelLignesZone1 = new JLabel("Nombre de lignes : ");
		nouvMatriceBoxLignesZone1 = new JComboBox ();        

		nouvMatriceLabelColonnesZone1 = new JLabel("Nombre de colonnes : ");
		nouvMatriceBoxColonnesZone1 = new JComboBox ();

		for(int i = 0 ; i < tab.length ; i++){
			nouvMatriceBoxLignesZone1.addItem(tab[i]);
			nouvMatriceBoxColonnesZone1.addItem(tab[i]);
		}

		nouvMatriceButtonZone1 = new JButton("OK");
		nouvMatriceButtonZone1.setBounds(195, 70, 55, 25);
		nouvMatriceButtonZone1.addActionListener(this);
		nouvMatriceSousPanelZone1.add(nouvMatriceButtonZone1);


		nouvMatricePanelZone1.add(nouvMatriceLabelLignesZone1);
		nouvMatricePanelZone1.add(nouvMatriceBoxLignesZone1);

		nouvMatricePanelZone1.add(nouvMatriceLabelColonnesZone1);
		nouvMatricePanelZone1.add(nouvMatriceBoxColonnesZone1);               

		nouvMatriceSousPanelZone1.add(nouvMatricePanelZone1);


		zone1.add(nouvMatriceSousPanelZone1);
		zone1.revalidate();

	}

	private void initNouvelleMatriceZone2() {
		int[]tab = {1,2,3,4,5,6,7,8};                
		instructionsZone2.setVisible(false);
		selectMatriceZone2.setSelectedItem("");
		resetMatriceZone2();      

		nouvMatriceSousPanelZone2 = new JPanel (null);
		nouvMatriceSousPanelZone2.setBounds(105,125,250,95);

		nouvMatricePanelZone2 = new JPanel ();
		nouvMatricePanelZone2.setLayout(new GridLayout(2, 2, 0, 10));
		nouvMatricePanelZone2.setBounds(0,0,250,60);

		instructionsZone2.setVisible(false);

		nouvMatriceLabelLignesZone2 = new JLabel("Nombre de lignes : ");
		nouvMatriceBoxLignesZone2 = new JComboBox ();        

		nouvMatriceLabelColonnesZone2 = new JLabel("Nombre de colonnes : ");
		nouvMatriceBoxColonnesZone2 = new JComboBox ();

		for(int i = 0 ; i < tab.length ; i++){
			nouvMatriceBoxLignesZone2.addItem(tab[i]);
			nouvMatriceBoxColonnesZone2.addItem(tab[i]);
		}

		nouvMatriceButtonZone2 = new JButton("OK");
		nouvMatriceButtonZone2.setBounds(195, 70, 55, 25);
		nouvMatriceButtonZone2.addActionListener(this);
		nouvMatriceSousPanelZone2.add(nouvMatriceButtonZone2);


		nouvMatricePanelZone2.add(nouvMatriceLabelLignesZone2);
		nouvMatricePanelZone2.add(nouvMatriceBoxLignesZone2);

		nouvMatricePanelZone2.add(nouvMatriceLabelColonnesZone2);
		nouvMatricePanelZone2.add(nouvMatriceBoxColonnesZone2);               

		nouvMatriceSousPanelZone2.add(nouvMatricePanelZone2);


		zone2.add(nouvMatriceSousPanelZone2);
		zone2.revalidate();

	}  


	private void creerNouvMatriceZone1() {


		nLignesZone1 = (int) nouvMatriceBoxLignesZone1.getSelectedItem();
		nColZone1 = (int) nouvMatriceBoxColonnesZone1.getSelectedItem();

		matriceZone1 = new Matrice(nLignesZone1, nColZone1, 0);

		resetNouvMatriceZone1();

		initMatriceZone1();
		zone1.add(panelMatriceZone1);
		zone1.revalidate();

		editionZone1();
		boutonsTabZone1[1].setEnabled(true);
	}
	private void creerNouvMatriceZone2() {

		nLignesZone2 = (int) nouvMatriceBoxLignesZone2.getSelectedItem();
		nColZone2 = (int) nouvMatriceBoxColonnesZone2.getSelectedItem();

		matriceZone2 = new Matrice(nLignesZone2, nColZone2, 0);

		resetNouvMatriceZone2();

		initMatriceZone2();
		zone2.add(panelMatriceZone2);
		zone2.revalidate();

		editionZone2();
		boutonsTabZone2[1].setEnabled(true);
	}

	private void resetNouvMatriceZone1(){
		nouvMatriceSousPanelZone1.setVisible(false);
		nouvMatriceSousPanelZone1.revalidate();
		nouvMatriceSousPanelZone1 = null;
		zone1.revalidate(); 
	}
	private void resetNouvMatriceZone2(){
		nouvMatriceSousPanelZone2.setVisible(false);
		nouvMatriceSousPanelZone2.revalidate();
		nouvMatriceSousPanelZone2 = null;
		zone2.revalidate();
	}

	private void ajoutLigneZone1() {
		if (nLignesZone1 < 8) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone1; i++) {
				for (int j = 0; j < nColZone1; j++) {
					buffer = fieldsMatriceZone1[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone1.getElement(i, j);
					}
					matriceZone1.setElement(i,j,valeur);
				}
			}

			double[] ligne = new double[nColZone1];
			for (int i = 0; i < ligne.length; i++) {
				ligne[i] = 0;
			}
			matriceZone1.ajouterLigne(nLignesZone1, ligne);
			nLignesZone1++;

			resetMatriceZone1();
			resetOperationZone1();
			resetEditionZone1();
			initMatriceZone1();
			editionZone1();
			zone1.add(panelMatriceZone1);
			zone1.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Impossible d'ajouter plus de 8 lignes", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}


	private void ajoutLigneZone2() {
		if (nLignesZone2 < 8) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone2; i++) {
				for (int j = 0; j < nColZone2; j++) {
					buffer = fieldsMatriceZone2[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone2.getElement(i, j);
					}
					matriceZone2.setElement(i,j,valeur);
				}
			}

			double[] ligne = new double[nColZone2];
			for (int i = 0; i < ligne.length; i++) {
				ligne[i] = 0;
			}
			matriceZone2.ajouterLigne(nLignesZone2, ligne);
			nLignesZone2++;

			resetMatriceZone2();
			resetOperationZone2();
			resetEditionZone2();
			initMatriceZone2();
			editionZone2();
			zone2.add(panelMatriceZone2);
			zone2.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Impossible d'ajouter plus de 8 lignes", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ajoutColonneZone1() {
		if (nColZone1 < 8) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone1; i++) {
				for (int j = 0; j < nColZone1; j++) {
					buffer = fieldsMatriceZone1[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone1.getElement(i, j);
					}
					matriceZone1.setElement(i,j,valeur);
				}
			}

			double[] colonne = new double[nLignesZone1];
			for (int i = 0; i < colonne.length; i++) {
				colonne[i] = 0;
			}
			matriceZone1.ajouterColonne(nColZone1, colonne);
			nColZone1++;

			resetMatriceZone1();
			resetOperationZone1();
			resetEditionZone1();
			initMatriceZone1();
			editionZone1();
			zone1.add(panelMatriceZone1);
			zone1.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Impossible d'ajouter plus de 8 colonnes", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ajoutColonneZone2() {
		if (nColZone2 < 8) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone2; i++) {
				for (int j = 0; j < nColZone2; j++) {
					buffer = fieldsMatriceZone2[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone2.getElement(i, j);
					}
					matriceZone2.setElement(i,j,valeur);
				}
			}

			double[] colonne = new double[nLignesZone2];
			for (int i = 0; i < colonne.length; i++) {
				colonne[i] = 0;
			}
			matriceZone2.ajouterColonne(nColZone2, colonne);
			nColZone2++;

			resetMatriceZone2();
			resetOperationZone2();
			resetEditionZone2();
			initMatriceZone2();
			editionZone2();
			zone2.add(panelMatriceZone2);
			zone2.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Impossible d'ajouter plus de 8 colonnes", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void suppColonneZone1() {
		if (nColZone1 != 1) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone1; i++) {
				for (int j = 0; j < nColZone1; j++) {
					buffer = fieldsMatriceZone1[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone1.getElement(i, j);
					}
					matriceZone1.setElement(i,j,valeur);
				}
			}

			double[]colonne = matriceZone1.supprimerColonne(nColZone1-1);
			nColZone1--;

			resetMatriceZone1();
			resetOperationZone1();
			resetEditionZone1();
			initMatriceZone1();
			editionZone1();
			zone1.add(panelMatriceZone1);
			zone1.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Une matrice doit contenir au moins une colonne", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void suppColonneZone2() {
		if (nColZone2 != 1) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone2; i++) {
				for (int j = 0; j < nColZone2; j++) {
					buffer = fieldsMatriceZone2[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone2.getElement(i, j);
					}
					matriceZone2.setElement(i,j,valeur);
				}
			}

			double[]colonne = matriceZone2.supprimerColonne(nColZone2-1);
			nColZone2--;

			resetMatriceZone2();
			resetOperationZone2();
			resetEditionZone2();
			initMatriceZone2();
			editionZone2();
			zone2.add(panelMatriceZone2);
			zone2.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Une matrice doit contenir au moins une colonne", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addMatrice() {
		try {
			nameMatriceZone4 = nameMatriceZone1 + " + " + nameMatriceZone2 + "  =  ";
			matriceZone4 = (Matrice) matriceZone1.somme(matriceZone2);
			resetZone4();
			populateZone4();
		} catch (MatriceException e) {
			JOptionPane.showMessageDialog(null, "Les matrices n'ont pas le bon format pour s'additionner", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void multMatrice() {
		try {
			nameMatriceZone4 = nameMatriceZone1 + " x " + nameMatriceZone2 + " =  ";
			matriceZone4 = (Matrice) matriceZone1.produit(matriceZone2);
			resetZone4();
			populateZone4();
		} catch (MatriceException e) {
			JOptionPane.showMessageDialog(null, "Les matrices n'ont pas le bon format pour se multiplier", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void multValeurZone1() {           
		String scalaireText = multScalaireZone1.getText();
		try {
			double scalaire = Double.parseDouble(scalaireText);
			nameMatriceZone4 = nameMatriceZone1 + " * " + scalaireText + " =  ";
			matriceZone4 = (Matrice) matriceZone1.produit(scalaire);
			resetZone4();
			populateZone4();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Le scalaire saisi n'est pas un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
		}                   
	}

	private void multValeurZone2() {           
		String scalaireText = multScalaireZone2.getText();
		try {
			double scalaire = Double.parseDouble(scalaireText);
			nameMatriceZone4 = nameMatriceZone2 + " * " + scalaireText + " =  ";
			matriceZone4 = (Matrice) matriceZone2.produit(scalaire);
			resetZone4();
			populateZone4();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Le scalaire saisi n'est pas un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
		}                    
	}	

	private void suppLigneZone1() {
		if (nLignesZone1 != 1) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone1; i++) {
				for (int j = 0; j < nColZone1; j++) {
					buffer = fieldsMatriceZone1[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone1.getElement(i, j);
					}
					matriceZone1.setElement(i,j,valeur);
				}
			}

			double[] ligne = matriceZone1.supprimerLigne(nLignesZone1 - 1);
			nLignesZone1--;

			resetMatriceZone1();
			resetOperationZone1();
			resetEditionZone1();
			initMatriceZone1();
			editionZone1();
			zone1.add(panelMatriceZone1);
			zone1.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Une matrice doit contenir au moins une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void suppLigneZone2() {
		if (nLignesZone2 != 1) {
			double valeur;
			String buffer;
			for (int i = 0; i < nLignesZone2; i++) {
				for (int j = 0; j < nColZone2; j++) {
					buffer = fieldsMatriceZone2[i][j].getText();
					try {
						valeur = Double.parseDouble(buffer);
					} catch (NumberFormatException e) {
						valeur = matriceZone2.getElement(i, j);
					}
					matriceZone2.setElement(i,j,valeur);
				}
			}

			double[] ligne = matriceZone2.supprimerLigne(nLignesZone2 - 1);
			nLignesZone2--;

			resetMatriceZone2();
			resetOperationZone2();
			resetEditionZone2();
			initMatriceZone2();
			editionZone2();
			zone2.add(panelMatriceZone2);
			zone2.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Une matrice doit contenir au moins une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void transposeZone1(){
		nameMatriceZone4 = "T (" + nameMatriceZone1 + ")  =  ";
		matriceZone4 = (Matrice) matriceZone1.transposee();
		resetZone4();
		populateZone4();
	}

	private void transposeZone2(){
		nameMatriceZone4 = "T (" + nameMatriceZone2 + ")  =  ";
		matriceZone4 = (Matrice) matriceZone2.transposee();
		resetZone4();
		populateZone4();
	}

	private void initZone4(){
		resultZone4 = new JPanel(null);
		resultZone4.setBounds(10, 10, 970, 210);
		resultZone4.setBackground(Color.white);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		resultZone4.setBorder(border);
		zone4.add(resultZone4);
		fenetre.revalidate();
	}

	private void populateZone4(){
		nameZone4 = new JTextArea(nameMatriceZone4);
		nameZone4.setBounds(80,20,60,25);
		nameZone4.setEditable(false);
		Font font = new Font("Courrier New", Font.BOLD, 12);
		nameZone4.setFont(font);
		matriceAffZone4 = new JTextArea(matriceZone4.toString());
		
		matriceAffZone4.setFont(font);
		matriceAffZone4.setEditable(false);
		
		justePourToi = new JScrollPane();
		justePourToi.setBounds(180, 20, 400, 140);
		justePourToi.setViewportView(matriceAffZone4);
		
		saveZone4 = new JButton("Sauvegarder");
		saveZone4.setBounds(600, 20, 120, 25);
		saveZone4.addActionListener(this);
		resultZone4.add(nameZone4);
		resultZone4.add(justePourToi);
		resultZone4.add(saveZone4);
		resultZone4.revalidate();
		zone4.revalidate();
		fenetre.revalidate();
		fenetre.repaint();
	}

	private void resetZone4(){
		if(nameZone4 != null){
			nameZone4.setVisible(false);
			matriceAffZone4.setVisible(false);
			justePourToi.setVisible(false);
			saveZone4.setVisible(false);                           
			fenetre.revalidate();
		}
	}
	
	private void sauvegarderZone4(){
		String buffer;
		String name;
		int nLignes = matriceZone4.getNumLignes();
		int nCol = matriceZone4.getNumColonnes();
		double[] elements = new double[nLignes*nCol];
		for (int i = 0; i < nLignes; i++) {
			for (int j = 0; j < nCol; j++) {
				elements[i*nCol +j] = matriceZone4.getElement(i, j);
			}
		}
		Matrice save = new Matrice(nLignes, nCol, elements);
		do {
			name =  JOptionPane.showInputDialog("Nom matrice :"); 
			if (name == null){
				throw new MatriceException();
			}
			if(matrices.contains(name)){
				JOptionPane.showMessageDialog(null, "Nom de matrice existant", "Erreur", JOptionPane.ERROR_MESSAGE);
				name = null;
			} else if (name.length()>5 || name.isEmpty()){
				JOptionPane.showMessageDialog(fenetre, "Le nom de la matrice doit contenir entre 1 et 5 caractères !", "Erreur", JOptionPane.ERROR_MESSAGE);
				name = null;
			}
		} while (name == null );

		matrices.add(name);
		matrices.add(save);

		refreshSelectorALL();

		if(nameMatriceZone2 !=null){
			selectMatriceZone2.setSelectedItem(nameMatriceZone2);
		}
		if(nameMatriceZone1 !=null){
			selectMatriceZone1.setSelectedItem(nameMatriceZone1);
		}
		resetZone4();
	}

}

