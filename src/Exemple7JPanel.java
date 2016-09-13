


import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Melanie Lord
 * @version novembre 2013
 */
public class Exemple7JPanel {
   
   /************************************
    * CONSTANTES DE CLASSE
    ************************************/
   //hauteur est largeur des composants
   public final static int LARG = 200;
   public final static int HAUT = 230;
   
   /************************************
    * VARIABLES D'INSTANCES
    ************************************/
   //fenetre principale et conteneur pour les etiquettes et champs textes
   private JFrame fenetre; 
   
   //Panneaux
   private JPanel panneauHaut;
   private JPanel panneauBas;
   
   //zone de texte
   private JTextArea zone1;
   
   //bouton
   private JButton btn1;
   
   
   /**
    * Constructeur qui initialise tous les composants 
    * graphiques.
    */
   public Exemple7JPanel() {
      init(); 
   }
   
   /**
    * Initialise une fenetre contenant deux panneaux (JPanel) avec dans 
    * le premier panneau, une zone de texte et dans le deuxieme panneau, 
    * un bouton. 
    */
   private void init() {
      
      /************************************
       * INIT FENETRE PRINCIPALE (JFRAME)
       ************************************/
      
      //creation et initialisation des proprietes de la fenetre principale
      fenetre = new JFrame("Exemple avec 2 panneaux");
      fenetre.setBounds(400, 300, 500, 300);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setResizable(false);
      fenetre.setLayout(null);  //pas de layout manager
      
      /*********************************************************
       * INIT PANNEAUX
       *********************************************************/
      
      //creation et initialisation des proprietes du panneauHaut
      
      panneauHaut = new JPanel(null);  //pas de layout manager
      panneauHaut.setBounds(20, 20,
              fenetre.getWidth() - 40, 
              fenetre.getHeight() - 140);  //on laisse un espace de 140 pour 
                                           //le panneauBas
      
      //coloration du panneau pour qu'on puisse le visualiser
      panneauHaut.setBackground(Color.GRAY);
      
      //ajout d'une bordure rouge de 2 pixels au panneau
      panneauHaut.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
      
      
      /***************************************************************/
      
      //creation et initialisation des proprietes du panneauBas
      
      panneauBas = new JPanel(null);  //JPanel avec un layout manager null
      panneauBas.setBounds(panneauHaut.getX(), 
              panneauHaut.getY() + panneauHaut.getHeight() + 20,
              panneauHaut.getWidth(), 
              fenetre.getHeight() - panneauHaut.getHeight() - 80);  
      
      //ajout d'une bordure noire de 1 pixel au panneau bas
      panneauBas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      
      /***************************************************************/
      
      //creation et initialisation des proprietes de la zone de texte
      zone1 = new JTextArea("Ceci est une zone de texte initialisée avec "
              + "le texte que vous êtes en  train de lire...");

      zone1.setBounds(10, 10, 
              panneauHaut.getWidth() - 20, 
              panneauHaut.getHeight() - 20);
      
      //passer à la ligne suivante si texte deborde et couper aux mots
      zone1.setLineWrap(true);
      zone1.setWrapStyleWord(true);
      
      //Rendre la zone de texte non editable
      zone1.setEditable(false);
      
      //Ajouter la zone de texte au panneau du haut
      panneauHaut.add(zone1);
      
      /***************************************************************/
      
      //creation et initialisation des proprietes du bouton
      
      btn1 = new JButton("BOUTON");
      
      //aligner le bouton dans le mileu du panneau du bas
      btn1.setBounds(panneauBas.getWidth() / 2 - 45, 
              panneauBas.getHeight() / 2 - 20,
              90, 40);
      
      //ajout du btn1 dans le panneau du bas
      panneauBas.add(btn1);
      
      /*************************************************
       * AJOUT DES COMPOSANTS AU CONTENT PANE DU JFRAME
       *************************************************/
      
      //Ajout des deux panneaux a la fenetre
      fenetre.getContentPane().add(panneauHaut);
      fenetre.getContentPane().add(panneauBas);
      
      //afficher la fenetre
      fenetre.setVisible(true);

   }
   
   
   public static void main (String [] args)  {
      
      new Exemple7JPanel();
      
      //OU Exécution d'un thread, MAJ du GUI
      /*
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Exemple7JPanel();
            }
      });
      */
   }
   
}



