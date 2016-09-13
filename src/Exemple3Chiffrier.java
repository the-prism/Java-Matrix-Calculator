
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


/**
 * Exemple d'un chiffrier avec GridLayout.
 * @author Melanie Lord
 * @version novembre 2013
 */
public class Exemple3Chiffrier implements ActionListener {
   
   /************************************
    * VARIABLES D'INSTANCES
    ************************************/
   private JFrame fenetre;
   private JTextField [] cellules;
   private JTextField [] resultats;
   private JButton btnCalculer;
   private JPanel pChiffrier;
   private JPanel pResultats;
   private JPanel pBoutons;

   public Exemple3Chiffrier() {
      init();
   }
   
   /**
    * Initialisation des composants graphiques.
    */
   private void init() {
      
      /************************************
       * INIT FENETRE PRINCIPALE (JFRAME)
       ************************************/
      fenetre = new JFrame("Exemple chiffrier");
      fenetre.setBounds(460, 200, 430, 440);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setResizable(false);
      fenetre.setLayout(null);  
      
      /*********************************************************
       * INIT PANNEAUX (JPANEL)
       *********************************************************/
      pChiffrier = new JPanel(new GridLayout(8, 5, 20, 5));  //(int rows, int cols, int hgap, int vgap) 
      pChiffrier.setBounds(20, 20,
              fenetre.getWidth() - 40, 
              fenetre.getHeight() - 200);  

      /***************************************************************/
      pResultats = new JPanel(new GridLayout(1, 5, 20, 5));
      pResultats.setBounds(pChiffrier.getX(), 
                           pChiffrier.getY() + pChiffrier.getHeight() + 20,
                           pChiffrier.getWidth(),
                           35);
      
      /***************************************************************/
      pBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
      pBoutons.setBounds(pResultats.getX(), 
                         pResultats.getY() + pResultats.getHeight() + 30,
                         pResultats.getWidth(),
                         40);
        
      /************************************
       * INIT BOUTON (JBUTTON)
       ************************************/
      btnCalculer = new JButton("Calculer");
      
      //Ajout du bouton au panneau pBoutons
      pBoutons.add(btnCalculer);
      
      /************************************
       * INIT CHAMPS TEXTE (JTEXTFIELD)
       ************************************/
      cellules = new JTextField[40];
      for (int i = 0; i < cellules.length ; i++) {
         cellules[i] = new JTextField();
         cellules[i].setFont(new Font("Courier", Font.PLAIN, 12));
         cellules[i].setText("0.0");
         cellules[i].setHorizontalAlignment(SwingConstants.RIGHT);
         
         //Ajout du champ au panneau pChiffrier
         pChiffrier.add(cellules[i]);
      }
      
      resultats = new JTextField [5];
      for (int i = 0; i < resultats.length ; i++) {
         resultats[i] = new JTextField();
         resultats[i].setFont(new Font("Courier", Font.BOLD, 12));
         resultats[i].setEditable(false);
         resultats[i].setBackground(Color.YELLOW);
         resultats[i].setText("0.0");
         resultats[i].setHorizontalAlignment(SwingConstants.RIGHT);
         
         //Ajout du champ au panneau pResultats
         pResultats.add(resultats[i]);
      }
      
      /************************************
       * AJOUT DES COMPOSANTS A LA FENETRE
       ************************************/
      fenetre.getContentPane().add(pChiffrier);
      fenetre.getContentPane().add(pResultats);
      fenetre.getContentPane().add(pBoutons);
      
      //afficher la fenetre
      fenetre.setVisible(true);
      
      /*************************************************
       * AJOUT DES ECOUTEURS
       *************************************************/
      btnCalculer.addActionListener(this);

   }
   
   /*************************************************
    * IMPLEMENTATION DE ACTIONLISTENER
    *************************************************/
   @Override
   public void actionPerformed (ActionEvent evenement) {
      calculer();
   }
   
   
   /**
    * calcule la somme des cellules de chaque colonne et affiche le resultat 
    * dans le champ non editable sous chacune des colonnes.
    */
   private void calculer () {
      double [] colonnes = new double [5];
      int rangee = 0;
      
      //Mettre des 0.0 partout où il y a des champs vides
      for (int i = 0 ; i < cellules.length ; i++) {
         if (cellules[i].getText().trim().isEmpty()) {
            cellules[i].setText("0.0");
         }
      }
      
      //faire la somme par colonne
      try {
         for (int j = 0 ; j < cellules.length ; j = j + 5) {
            rangee++;
            colonnes[0] = colonnes[0] + Double.parseDouble(cellules[j].getText().trim());
            colonnes[1] = colonnes[1] + Double.parseDouble(cellules[j + 1].getText().trim());
            colonnes[2] = colonnes[2] + Double.parseDouble(cellules[j + 2].getText().trim());
            colonnes[3] = colonnes[3] + Double.parseDouble(cellules[j + 3].getText().trim());
            colonnes[4] = colonnes[4] + Double.parseDouble(cellules[j + 4].getText().trim());
         }
         
         //afficher le resultat de chaque colonne 
         for (int i = 0 ; i < resultats.length ; i++) {
            resultats[i].setText(new Double(colonnes[i]).toString());
         }
         
      //Levee si le texte d'une des cellules n'est pas un nombre
      } catch (NumberFormatException nfe) {
         //Afficher un message d'erreur
         JOptionPane.showMessageDialog(fenetre, 
                 "Erreur, une des cellules dans la rangée no " + (rangee) 
                 + "\nne contient pas un nombre", 
                 "Erreur", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   
   public static void main (String [] args)  {
      
      new Exemple3Chiffrier();
      
      //OU : Exécution d'un thread, MAJ du GUI
      /*
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Exemple3Chiffrier();
            }
      });
      */
   }
   
}
