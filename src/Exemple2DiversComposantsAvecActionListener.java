
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

//Pour la gestion d'evenements 
import java.awt.event.ActionListener; //ecouteur
import java.awt.event.ActionEvent;    //evenement

import java.awt.Color;                //couleurs
import java.awt.Font;                 //polices de caracteres
import javax.swing.JOptionPane;       //fenetre surgissante

/**
 * Exemple avec differents composants graphiques auxquels on a associe un ecouteur
 * a l'aide d'une classe anonyme interne.
 * 
 * @author Melanie Lord
 * @version novembre 2013
 */
public class Exemple2DiversComposantsAvecActionListener {
   
   /*********************************************************
    * CONSTANTES
    *********************************************************/
   //couleurs
   public final static Color VERT = new Color(76, 147, 0);    //R = 76, G = 147, B = 0
   public final static Color ORANGE = new Color(233, 143, 0); //R = 233, G = 143, B = 0
   
   //polices de caracteres
   public final static Font ARIAL_GRAS = new Font("Arial", Font.BOLD, 18);
   public final static Font COURRIER = new Font("Courrier", Font.PLAIN, 10);
   public final static Font TIMES_ITALIQUE = new Font("Times", Font.ITALIC, 14);
   
   /*********************************************************
    * ATTRIBUTS D'INSTANCE
    *********************************************************/
   //fenetre principale et conteneur pour les boutons
   private JFrame fenetre; 
   
   //champs texte
   private JTextField champArial;
   private JTextField champCourrier;
   private JTextField champTimes;
   
   //bouton cliquable
   private JButton btnDeplacer;
   
   //boutons radio et groupe pour ces boutons
   private JRadioButton radioVert;
   private JRadioButton radioOrange;
   private ButtonGroup groupeCouleur;
   
   //ecouteur
   private ActionListener ecouteur;
   
   /**
    * Constructeur qui initialise tous les composants 
    * graphiques.
    */
   public Exemple2DiversComposantsAvecActionListener() {
      init(); 
   }
   
   /**
    * Initialisation des composants graphiques.
    */
   private void init() {
      
      /*********************************************************
       * INIT FENETRE PRINCIPALE (JFRAME)
       *********************************************************/
      fenetre = new JFrame("Exemple 2");
      
      //Taille de la fenetre
      fenetre.setBounds(400, 300, 800, 160);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //Modifier le gestionnaire de disposition pour un FlowLayout
      fenetre.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
      
      //couleur de la fenetre
      fenetre.getContentPane().setBackground(VERT);  //fenetre initiale verte
      
      /*********************************************************
       * INIT CHAMPS TEXTE (JTEXTFIELD)
       *********************************************************/
      champArial = new JTextField("arial 18 bold");
      champArial.setFont(ARIAL_GRAS); //change la police pour ce champ
      
      champCourrier = new JTextField("courrier 10 plain");
      champCourrier.setFont(COURRIER); //change la police pour ce champ
      
      champTimes = new JTextField("times 14 italique");
      champTimes.setFont(TIMES_ITALIQUE); //change la police pour ce champ
      
      /*********************************************************
       * INIT BOUTONS RADIO (JRADIONBUTTON)
       *********************************************************/
      radioVert = new JRadioButton("vert");
      radioVert.setSelected(true);
      
      radioOrange = new JRadioButton("orange");
      
      groupeCouleur = new ButtonGroup();
      groupeCouleur.add(radioVert);
      groupeCouleur.add(radioOrange);
      
      /*********************************************************
       * INIT BOUTONS (JBUTTON)
       *********************************************************/
      //creation et initialisation du bouton Deplacer
      btnDeplacer = new JButton("Deplacer");
      
      /*********************************************************
       * AJOUT DES COMPOSANTS A LA FENETRE PRINCIPALE
       *********************************************************/
      
      //Pour les champs texte, l'evenement sera transmis lorsqu'on entre 
      //ENTER dans le champ texte.
      fenetre.getContentPane().add(champArial);
      fenetre.getContentPane().add(champCourrier);
      fenetre.getContentPane().add(champTimes);
      
      //Pour les boutons cliquables ou radio, l'evenement est transmis
      //lorsqu'on clique sur le bouton ou selectionne le bouton radio.
      fenetre.getContentPane().add(radioVert);
      fenetre.getContentPane().add(radioOrange);
      fenetre.getContentPane().add(btnDeplacer);
      
      //afficher la fenetre
      fenetre.setVisible(true);
      
      /*********************************************************
       * AJOUT D'UN ECOUTEUR AU BOUTON DEPLACER
       *********************************************************/
      
      //creation d'un ecouteur ActionListener (param) avec une classe Anonyme
      //interne qui implemente ActionListener
      
      ecouteur = new ActionListener() {            // classe anonyme interne
         
         //implementation de la methode actionPerformed de l'interface ActionListener
         @Override
         public void actionPerformed(ActionEvent evenement) {
            //NOTE : L'evenement peut provenir de n'importe quel des composants 
            //graphiques auquel on a ajoute cet ecouteur a l'aide de la 
            //methode addActionListener (voir plus bas)
            
            //L'evenement provient d'un Enter dans le champ texte champArial
            if (evenement.getSource() == champArial) {
               afficherTexte(champArial.getText());
               
            //L'evenement provient d'un Enter dans le champ texte champCourrier
            } else if (evenement.getSource() == champCourrier) {
               afficherTexte(champCourrier.getText());
               
            //L'evenement provient d'un Enter dans le champ texte champTimes
            } else if (evenement.getSource() == champTimes) {
               afficherTexte(champTimes.getText());
               
            //L'evenement provient de la selection du bouton radio radioVert
            } else if (evenement.getSource() == radioVert) {
               colorerVert();
               
            //L'evenement provient de la selection du bouton radio radioOrange
            } else if (evenement.getSource() == radioOrange) {
               colorerOrange();
               
            //L'evenement provient du bonton btnDeplacer
            } else {
               deplacer();
            }
         }
         
      };// fin de la classe interne anonyme
      
      //Ajout de l'ecouteur aux composants dont on veut surveiller les actions.
      champArial.addActionListener(ecouteur);
      champCourrier.addActionListener(ecouteur);
      champTimes.addActionListener(ecouteur);
      btnDeplacer.addActionListener(ecouteur);
      radioOrange.addActionListener(ecouteur);
      radioVert.addActionListener(ecouteur);  
   }
   
   /**
    * Deplace la fenetre en position (0,0) si elle ne l'est pas deja ou deplace
    * la fenetre en position (400, 300) si elle ne l'est pas deja.
    */
   private void deplacer() {
      if (fenetre.getX() != 0) {
         fenetre.setLocation(0, 0);
      } else {
         fenetre.setLocation(400, 300);
      }
   } 
   
   /**
    * Rend la fenetre orange.
    */
   private void colorerOrange () {
      fenetre.getContentPane().setBackground(ORANGE);
   } 
   
   /**
    * Rend la fenetre verte.
    */
   private void colorerVert () {
      fenetre.getContentPane().setBackground(VERT);
   } 
   
   /**
    * Affiche, dans une fenetre surgissante, le texte donne en parametre.
    * @param texte le texte a afficher dans la fenetre sugissante.
    */
   private void afficherTexte (String texte) {
      //affiche le message texte dans une fenetre surgissante (JOptionPane)
      //centree au milieu de fenetre.
      JOptionPane.showMessageDialog(fenetre, texte);
   }
   

   public static void main (String [] args)  {
      
      new Exemple2DiversComposantsAvecActionListener();
      
      //OU : Exécution d'un thread, MAJ du GUI
      /*
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Exemple2DiversComposantsAvecActionListener();
            }
      });
      */
   }
 
}
