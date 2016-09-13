
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;

//Pour la gestion d'evenements 
import java.awt.event.ActionListener; //ecouteur
import java.awt.event.ActionEvent;    //evenement

/**
 * Exemple avec un bouton auquel on a associe un ecouteur
 * a l'aide d'une classe anonyme interne.
 * 
 * @author Melanie Lord
 * @version novembre 2013
 */
public class Exemple1JButtonAvecActionListener {
   
   /*********************************************************
    * ATTRIBUTS D'INSTANCE
    *********************************************************/
   
   //fenetre principale et conteneur pour les boutons
   private JFrame fenetre; 
   
   //bouton cliquable
   private JButton btnDeplacer;
   
   //ecouteur pour bouton cliquable
   private ActionListener ecouteurBouton;
   
   /**
    * Constructeur qui initialise tous les composants 
    * graphiques.
    */
   public Exemple1JButtonAvecActionListener() {
      init(); 
   }
   
   /**
    * Un bouton dans un JFrame avec gestionnaire de disposition null.
    */
   private void init() {
      
      /*********************************************************
       * INIT FENETRE PRINCIPALE (JFRAME)
       *********************************************************/
      fenetre = new JFrame("Exemple 1");
  
      //Taille de la fenetre
      fenetre.setBounds(400, 300, 300, 160);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //Modifier le gestionnaire de disposition pour un FlowLayout
      fenetre.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
      
      /*********************************************************
       * INIT BOUTON (JBUTTON)
       *********************************************************/
      //creation et initialisation du bouton Deplacer
      btnDeplacer = new JButton("Deplacer");
      
      /*********************************************************
       * AJOUT DES COMPOSANTS A LA FENETRE PRINCIPALE
       *********************************************************/
      fenetre.getContentPane().add(btnDeplacer);
      
      //afficher la fenetre
      fenetre.setVisible(true);
      
      /*********************************************************
       * AJOUT D'UN ECOUTEUR AU BOUTON DEPLACER
       *********************************************************/
      //creation d'un ecouteur ActionListener (param) avec une classe Anonyme
      //interne qui implemente ActionListener
      
      ecouteurBouton = new ActionListener() {            // classe anonyme interne
         
         //implementation de la methode actionPerformed de l'interface ActionListener
         @Override
         public void actionPerformed(ActionEvent evenement) {
            
            //ici, appeler la methode qui doit etre executee lorsqu'un
            //utilisateur clique sur le bouton deplacer.
            deplacer(); //methode privee
         }
         
      };// fin de la classe interne anonyme
      
      //Ajout de l'ecouteur au bouton deplacer
      btnDeplacer.addActionListener(ecouteurBouton);
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
   
   
   public static void main (String [] args)  {
      
      new Exemple1JButtonAvecActionListener();
      
      // OU Exécution d'un thread, MAJ du GUI
      /*
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Exemple1JButtonAvecActionListener();
            }
      });
      */
   }
   
}
