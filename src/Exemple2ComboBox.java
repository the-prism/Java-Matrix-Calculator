
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Exemple avec une liste deroulante et gestion d'evenements sur selection.
 * @author Melanie Lord
 * @version novembre 2013
 */
public class Exemple2ComboBox implements ActionListener {
   
   /************************************
    * VARIABLES D'INSTANCES
    ************************************/
   private JFrame fenetre;
   private JComboBox liste;
   private JButton btnAjouterItem;
   private JButton btnSupprimerItem;
   private JButton btnViderListe;
   private JPanel panneauBoutons;

   public Exemple2ComboBox() {
      init();
   }

   /**
    * Initialisation des composants graphiques.
    */
   private void init() {
      
      /************************************
       * INIT FENETRE PRINCIPALE (JFRAME)
       ************************************/
      
      //creation et initialisation des proprietes de la fenetre principale
      fenetre = new JFrame("Exemple avec liste deroulante");
      fenetre.setBounds(400, 300, 430, 250);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setResizable(false);
      fenetre.setLayout(null);  
      
      /************************************
       * INIT PANNEAU BOUTONS (JPANEL)
       ************************************/
      
      panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
      panneauBoutons.setBounds(10, fenetre.getHeight() - 70, 
              fenetre.getWidth() - 20, 30);
      
      /************************************
       * INIT BOUTONS (JBUTTON)
       ************************************/
      btnAjouterItem = new JButton("Ajouter item");
      btnSupprimerItem = new JButton("Supprimer item");
      btnViderListe = new JButton("Vider liste");
      
      //Ajout au panneauBoutons
      panneauBoutons.add(btnAjouterItem);
      panneauBoutons.add(btnSupprimerItem);
      panneauBoutons.add(btnViderListe);
      
      /************************************
       * INIT LISTE DEROULANTE (JCOMBOBOX)
       ************************************/
      liste = new JComboBox();
      liste.setBounds(fenetre.getWidth() / 2 - 100, 
              20, 200, 50);
      
      /************************************
       * AJOUT DES COMPOSANTS A LA FENETRE
       ************************************/
      fenetre.getContentPane().add(panneauBoutons);
      fenetre.getContentPane().add(liste);
      
      //afficher la fenetre
      fenetre.setVisible(true);
      
      /*************************************************
       * AJOUT DES ECOUTEURS
       *************************************************/
      btnAjouterItem.addActionListener(this);
      btnSupprimerItem.addActionListener(this);
      btnViderListe.addActionListener(this);
      liste.addActionListener(this); 
   }
   
   /*************************************************
    * IMPLEMENTATION DE ACTIONLISTENER
    *************************************************/
   
   public void actionPerformed (ActionEvent evenement) {
      
      if (evenement.getSource() == liste) {
         afficherInfoItemSelectionne();
      
      } else if (evenement.getSource() == btnAjouterItem) {
         ajouterItem();
      
      } else if (evenement.getSource() == btnSupprimerItem){ 
         supprimerItem();
         
      } else {   //source est btnViderListe
         viderListe();
      }
   }
   
   /**
    * Affiche, dans une fenetre surgissante, l'item selectionne 
    * ainsi que l'indice de cet item dans la liste deroulante.
    */
   private void afficherInfoItemSelectionne () {
      String message;
      message = "Item selectionne : " + liste.getSelectedItem() 
                 + "\nIndex de l'item  : " + liste.getSelectedIndex();
         
      JOptionPane.showMessageDialog(fenetre, message, "INFO", 
              JOptionPane.INFORMATION_MESSAGE);
   }
   
   /**
    * Demande a l'utilisateur d'entrer un item  et un indice où inserer
    * cet item dans la liste deroulante.  Si l'item n'est pas null ou vide,
    * et si l'indice donne est un indice valide pour inserer dans la liste,
    * la methode insere cet item dans la liste a l'indice specifie.
    */
   private void ajouterItem() {
      String item;
      String sIndice;
      
      item = JOptionPane.showInputDialog(fenetre, "Item a ajouter : ");
      sIndice = JOptionPane.showInputDialog(fenetre, "Indice de l'item : ");

      if (item != null && !item.trim().isEmpty() 
              && estIndiceValide(sIndice)) {
         
         //ATTENTION : enlever l'écouteur avant de modifier la liste 
         liste.removeActionListener(this);
         
         liste.insertItemAt(item, Integer.parseInt(sIndice));
         liste.setSelectedIndex(Integer.parseInt(sIndice));
         
         //Note : la derniere instruction pourrait etre remplacee par :
         //liste.setSelectedItem(item);
         
         //ATTENTION : Ne pas oublier de remetre l'ecouteur sur la liste
         //apres le traitement
         liste.addActionListener(this);
      }
   }
   
   /**
    * Verifie si sIndice peut etre converti en un entier et si cet entier
    * est un indice valide pour inserer un item dans la liste deroulante.
    * @param sIndice l'indice a verifier
    * @return vrai si l'indice est valide, faux sinon.
    */
   private boolean estIndiceValide(String sIndice) {
      int indice;
      boolean valide = true;
      try {
         indice = Integer.parseInt(sIndice);
         if (indice < 0 || indice > liste.getItemCount()) {
            JOptionPane.showMessageDialog(fenetre, "L'indice donne est invalide !", 
                    "ERREUR", JOptionPane.ERROR_MESSAGE);
            valide = false;
         }
      } catch (NumberFormatException nfe) {
         JOptionPane.showMessageDialog(fenetre, "L'indice donne est invalide !", 
                 "ERREUR", JOptionPane.ERROR_MESSAGE);
         valide = false;
      }
      return valide;
   }
   
   /**
    * Supprime de liste l'item qui y est selectionne.
    */
   private void supprimerItem () {
      
      //ATTENTION : enlever l'ecouteur avant de modifier la liste 
      liste.removeActionListener(this);
      
      //retirer l'item de la liste
      liste.removeItem(liste.getSelectedItem());
      
      //ATTENTION : Ne pas oublier de remetre l'ecouteur sur la liste
      //apres le traitement
      liste.addActionListener(this);
   }
   
   /**
    * Supprime tous les elements de liste.
    */
   private void viderListe () {
      
      //ATTENTION : enlever l'ecouteur avant de modifier la liste 
      liste.removeActionListener(this);
      
      //retirer l'item de la liste
      liste.removeAllItems();
      
      //ATTENTION : Ne pas oublier de remetre l'ecouteur sur la liste
      //apres le traitement
      liste.addActionListener(this);
   }
   
  
   public static void main (String [] args)  {
      
      new Exemple2ComboBox();
      
      //OU : Exécution d'un thread, MAJ du GUI
      /*
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Exemple2ComboBox();
            }
      });
      */
   }
}
