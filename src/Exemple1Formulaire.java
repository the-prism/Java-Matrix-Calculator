
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Exemple d'un formulaire a remplir avec gestion d'evenements.
 * @author Melanie Lord
 * @novembre 2013
 */
public class Exemple1Formulaire implements ActionListener {
   
   /************************************
    * CONSTANTES DE CLASSE
    ************************************/
   
   //polices de caracteres
   public final static Font POLICE_INFO = new Font("Courier", Font.BOLD, 20);
   public final static Font POLICE_FORM = new Font ("Arial", Font.PLAIN, 14);
   
   /************************************
    * VARIABLES D'INSTANCES
    ************************************/
   //fenetre principale et conteneur pour les etiquettes et champs textes
   private JFrame fenetre; 
   
   //panneaux
   private JPanel panneauSaisie;
   private JPanel panneauInfo;
   private JPanel panneauBoutons;
   
   //zone de texte et panneau de defilement pour cette zone
   private JTextArea zoneTexte;
   private JScrollPane panneauDefil;
   
   //champs texte et etiquettes associees
   private JTextField champNom;
   private JTextField champPrenom;
   private JLabel etiquetteNom;
   private JLabel etiquettePrenom;
   
   //boutons radio, etiquette associee et groupe pour ces boutons
   private JRadioButton radioHomme;
   private JRadioButton radioFemme;
   private JLabel etiquetteGenre;
   private ButtonGroup groupeGenre;
   
   //boites a cocher et etiquette associee et panneau associe
   private JCheckBox bteRouge;
   private JCheckBox bteJaune;
   private JCheckBox bteBleu;
   private JCheckBox bteVert;
   private JCheckBox bteOrange;
   private JCheckBox bteMauve;
   private JLabel etiquetteCouleursPref;
   private JPanel panneauBoitesACocher;
   
   //Liste deroulante et etiquette associee
   private JComboBox listeAnnee;
   private JLabel etiquetteAnneeNaiss;
   
   //bouton
   private JButton btnInfo;
   private JButton btnForm;
   
   
   /**
    * Constructeur qui initialise tous les composants 
    * graphiques.
    */
   public Exemple1Formulaire() {
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
      fenetre = new JFrame("Formulaire");
      fenetre.setBounds(400, 300, 400, 330);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setResizable(false);
      fenetre.setLayout(null);  //pas de layout manager
      
      /*********************************************************
       * INIT PANNEAUX
       *********************************************************/
      
      //creation et initialisation des proprietes du panneauHaut
      
      panneauSaisie = new JPanel(null);  //sans gestionnaire de dispo 
      panneauSaisie.setBounds(20, 20,
              fenetre.getWidth() - 40, 
              fenetre.getHeight() - 100);  
      
      //ajout d'une bordure noire de 2 pixels au panneauSaisie
      panneauSaisie.setBorder(BorderFactory.createEtchedBorder());
      
      panneauSaisie.setVisible(true);
      
      /***************************************************************/
      
      panneauInfo = new JPanel(null);  //sans gestionnaire de dispo  
      
      //on positionne le panneauInfo exactement au meme endroit que panneauSaisie 
      //avec exactement la même taille.
      panneauInfo.setBounds(panneauSaisie.getX(), panneauSaisie.getY(),
              panneauSaisie.getWidth(), panneauSaisie.getHeight());
      
      //coloration du panneau
      panneauInfo.setBackground(Color.WHITE);
      
      //ajout d'une bordure blanche de 2 pixels au panneauInfo
      panneauInfo.setBorder(BorderFactory.createEtchedBorder());
      
      //non visible a l'initialisation de la fenetre
      panneauInfo.setVisible(false);
      
      /***************************************************************/
      
      panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
      panneauBoutons.setBounds(panneauInfo.getX(), 
                               panneauInfo.getY() + panneauInfo.getHeight() + 10,
                               panneauInfo.getWidth(),
                               fenetre.getHeight() - panneauInfo.getHeight() - 60);
      
      /*********************************************************
       * INIT ZONE TEXTE (JTEXTAREA) ET PANNEAU DEFILEMENT (JSCROLLPANE)
       *********************************************************/
      
      //creation et initialisation des proprietes de la zone de texte
      zoneTexte = new JTextArea();
 
      //passer a la ligne suivante si texte deborde
      zoneTexte.setLineWrap(true);
      
      //Rendre la zone de texte non editable
      zoneTexte.setEditable(false);
      
      zoneTexte.setFont(POLICE_INFO);
      
      //conteneur pour barres de defilement de zoneTexte
      panneauDefil = new JScrollPane(zoneTexte);
      panneauDefil.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      panneauDefil.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      panneauDefil.setBounds(5, 5, panneauInfo.getWidth() - 10 ,panneauInfo.getHeight() - 10);
      panneauDefil.setBorder(null); //enlever la bordure du JScrollPane
      
      //Ajouter la zone de texte au panneau info
      panneauInfo.add(panneauDefil);
      
      /*********************************************************
       * INIT BOUTONS (JBUTTON)
       *********************************************************/
      btnInfo = new JButton("Information");
      btnForm = new JButton("Formulaire");
      btnForm.setEnabled(false);
      
      //ajout des boutons au panneau de boutons
      panneauBoutons.add(btnForm);
      panneauBoutons.add(btnInfo);
      
      /*********************************************************
       * INIT ETIQUETTES ET CHAMPS TEXTE ASSOCIES
       *********************************************************/
     
      etiquetteNom = new JLabel("Nom : ");
      etiquetteNom.setHorizontalAlignment(SwingConstants.RIGHT);
      etiquetteNom.setFont(POLICE_FORM);
      etiquetteNom.setBounds(10, 10, 130, 25);
      
      champNom = new JTextField();
      champNom.setFont(POLICE_FORM);
      champNom.setBounds(etiquetteNom.getX() + etiquetteNom.getWidth() + 10, 
                         etiquetteNom.getY(),
                         170, 25);
      
      etiquettePrenom = new JLabel("Prénom : ");
      etiquettePrenom.setHorizontalAlignment(SwingConstants.RIGHT);
      etiquettePrenom.setFont(POLICE_FORM);
      etiquettePrenom.setBounds(etiquetteNom.getX(), 
              etiquetteNom.getY() + etiquetteNom.getHeight() + 5,
              etiquetteNom.getWidth(), etiquetteNom.getHeight());
      
      champPrenom = new JTextField();
      champPrenom.setFont(POLICE_FORM);
      champPrenom.setBounds(etiquettePrenom.getX() + etiquettePrenom.getWidth() + 10, 
                         etiquettePrenom.getY(),
                         champNom.getWidth(), champNom.getHeight());
      
      //ajout au panneauSaisie
      panneauSaisie.add(etiquetteNom);
      panneauSaisie.add(champNom);
      panneauSaisie.add(etiquettePrenom);
      panneauSaisie.add(champPrenom);
      
      
      /*********************************************************
       * INIT LISTE DEROULANTE (JCOMBOBOX)
       *********************************************************/
      etiquetteAnneeNaiss = new JLabel("Annee naissance : ");
      etiquetteAnneeNaiss.setHorizontalAlignment(SwingConstants.RIGHT);
      etiquetteAnneeNaiss.setFont(POLICE_FORM);
      etiquetteAnneeNaiss.setBounds(etiquettePrenom.getX(), 
                               etiquettePrenom.getY() + etiquettePrenom.getHeight() + 5,
                               etiquettePrenom.getWidth(), etiquettePrenom.getHeight());
      listeAnnee = new JComboBox();
      
      //initialiser la liste avec les annee de 1900 a 20013
      initComboBox();
      
      listeAnnee.setBounds(etiquetteAnneeNaiss.getX() + etiquetteAnneeNaiss.getWidth() + 7,
                           etiquetteAnneeNaiss.getY(),
                           champPrenom.getWidth(), etiquetteAnneeNaiss.getHeight());
      
      //Ajout au panneauSaisie
      panneauSaisie.add(etiquetteAnneeNaiss);
      panneauSaisie.add(listeAnnee);
      
      
      /*********************************************************
       * INIT BOUTONS RADIO (JRADIOBUTTON)
       *********************************************************/
      etiquetteGenre = new JLabel("Genre : ");
      etiquetteGenre.setHorizontalAlignment(SwingConstants.RIGHT);
      etiquetteGenre.setFont(POLICE_FORM);
      etiquetteGenre.setBounds(etiquetteAnneeNaiss.getX(), 
                               etiquetteAnneeNaiss.getY() + etiquetteAnneeNaiss.getHeight() + 5,
                               etiquetteAnneeNaiss.getWidth(), etiquetteAnneeNaiss.getHeight());
      
      radioHomme = new JRadioButton("Homme");
      radioHomme.setSelected(true);
      radioHomme.setBounds(etiquetteGenre.getX() + etiquetteGenre.getWidth() + 5, 
                           etiquetteGenre.getY(),
                           90, 25);
      radioHomme.setFont(POLICE_FORM);       
              
      radioFemme = new JRadioButton("Femme");  
      radioFemme.setBounds(radioHomme.getX() + radioHomme.getWidth() + 5, 
                           radioHomme.getY(),
                           radioHomme.getWidth(), radioHomme.getHeight());
      radioFemme.setFont(POLICE_FORM);
      
      //Ajout des boutons radio a un meme groupe
      groupeGenre = new ButtonGroup();
      groupeGenre.add(radioHomme);
      groupeGenre.add(radioFemme);
      
      //Ajout au panneauSaisie
      panneauSaisie.add(etiquetteGenre);
      panneauSaisie.add(radioHomme);
      panneauSaisie.add(radioFemme);
      
      /*********************************************************
       * INIT BOITES A COCHER (JCHECKBOX)
       *********************************************************/
      etiquetteCouleursPref = new JLabel ("Couleur(s) préférée(s) : ");
      etiquetteCouleursPref.setFont(POLICE_FORM);
      etiquetteCouleursPref.setBounds(etiquetteGenre.getX(), 
                                      etiquetteGenre.getY() + etiquetteGenre.getHeight() + 10,
                                      etiquetteGenre.getWidth() * 2, etiquetteGenre.getHeight());
      
      panneauBoitesACocher = new JPanel(new FlowLayout());
      panneauBoitesACocher.setBounds(etiquetteCouleursPref.getX(), 
                                     etiquetteCouleursPref.getY() + etiquetteCouleursPref.getHeight() + 5,
                                     panneauSaisie.getWidth() - 20, 60);
      
      bteRouge = new JCheckBox("Rouge");
      bteBleu = new JCheckBox("Bleu");
      bteJaune = new JCheckBox("Jaune");
      bteVert = new JCheckBox("Vert");
      bteOrange = new JCheckBox("Orange");
      bteMauve = new JCheckBox("Mauve");
      
      //ajout au panneauBoitesACocher
      panneauBoitesACocher.add(bteRouge);
      panneauBoitesACocher.add(bteBleu);
      panneauBoitesACocher.add(bteJaune);
      panneauBoitesACocher.add(bteVert);
      panneauBoitesACocher.add(bteOrange);
      panneauBoitesACocher.add(bteMauve);
      
      //ajout au panneauSaisie
      panneauSaisie.add(etiquetteCouleursPref);
      panneauSaisie.add(panneauBoitesACocher);
      
      /*************************************************
       * AJOUT DES COMPOSANTS AU CONTENT PANE DU JFRAME
       *************************************************/
      
      //ajout des trois panneaux a la fenetre
      fenetre.getContentPane().add(panneauSaisie);
      fenetre.getContentPane().add(panneauInfo);
      fenetre.getContentPane().add(panneauBoutons);
      
      //afficher la fenetre
      fenetre.setVisible(true);
      
      /*************************************************
       * AJOUT DES ECOUTEURS AUX DEUX BOUTONS
       *************************************************/
      btnForm.addActionListener(this);
      btnInfo.addActionListener(this);

   }
   
   /*************************************************
    * IMPLEMENTATION DE ACTIONLISTENER
    *************************************************/
   @Override
   public void actionPerformed (ActionEvent evenement) {
      if (evenement.getSource() == btnForm) {
         afficherFormulaire();
         
      } else { //c'est le bouton Information
         afficherInformations();
      }  
   }
   
   /**
    * Remplit la liste deroulante avec la valeur vide suivie des valeurs
    * de 1950 a 2013.
    */
   private void initComboBox() {
      //ajout d'une valeur blanche (vide)
      listeAnnee.addItem("");
      listeAnnee.setSelectedItem("");
      for (int i = 1950 ; i <= 2013 ; i++) {
         listeAnnee.addItem(i);
      }
   }
   
   /**
    * Affiche l'information formatee.
    */
   private void afficherInformations() {
      String info;
      
      if (formulaireValide()) {
         //formater les informations du formulaire
         info = formaterInfo();
         
         //ajouter les informations formatees dans la zone de texte.
         zoneTexte.setText(info);
         
         btnInfo.setEnabled(false);
         btnForm.setEnabled(true);
         panneauInfo.setVisible(true);
         panneauSaisie.setVisible(false);
      }  
      
   }
   
   /**
    * Affiche le formulaire.
    */
   private void afficherFormulaire() {
      btnInfo.setEnabled(true);
      btnForm.setEnabled(false);
      panneauInfo.setVisible(false);
      panneauSaisie.setVisible(true);
   }
   
   /**
    * Retourne vrai si les entrees du formulaires sont valides, faux sinon.
    * Un formulaire est considere valide si :
    *    - Les champs Nom et Prénom ne sont pas vides
    *    - Un genre est selectionne
    *    - Une année de naissance est sélectionnée
    *    - Au moins une couleur preferee est selectionnee.
    * @return vrai si le formulaire est valide, faux sinon.
    */
   private boolean formulaireValide() {
      String message = null;
      
      //verifier que le champ nom n'est pas vide
      if (champNom.getText().trim().isEmpty()) {
         //message erreur
         message = "Erreur, le champ Nom doit contenir une valeur !";
         
      } else {
         //verifier que le champ prenom n'est pas vide
         if (champPrenom.getText().trim().isEmpty()) {
            //message erreur
            message = "Erreur, le champ Prénom doit contenir une valeur !";
            
         } else {
            if (listeAnnee.getSelectedItem().toString().trim().isEmpty()) {
               //message erreur
               message = "Erreur, vous devez sélectionner votre année de naissance !";
            } else {
               //verifier qu'au moins une des boites a cocher est cochee
               if (!bteRouge.isSelected() && !bteJaune.isSelected() && 
                    !bteBleu.isSelected() && !bteVert.isSelected() && 
                    !bteOrange.isSelected() && !bteMauve.isSelected()) {
               //message erreur
               message = "Erreur, vous devez sélectionner au moins une couleur !";
            }
            
            }
         }
      }
      
      //afficher message d'erreur s'il y a lieu
      if (message != null) {
         JOptionPane.showMessageDialog(fenetre, message, "ERREUR", 
                 JOptionPane.ERROR_MESSAGE);
      }
      
      return message == null;
   }
   
   /**
    * Recupere toutes les informations du formulaire et les retourne formatees
    * dans une chaine de caracteres.
    * @return une chaine contenant les informations du formulaire.
    */
   private String formaterInfo() {
      String info = "";
      String nom = champNom.getText().trim();
      String prenom = champPrenom.getText().trim();
      int annee = (Integer)listeAnnee.getSelectedItem();
      String genre;
      ArrayList<String> couleursPref = new ArrayList<String>();
      
      if (radioHomme.isSelected()) {
         genre = radioHomme.getText();
      } else {
         genre = radioFemme.getText();
      }
      
      if (bteRouge.isSelected()) {
         couleursPref.add(bteRouge.getText());  
      }
         
      if (bteBleu.isSelected()) {
         couleursPref.add(bteBleu.getText());   
      } 
      
      if (bteJaune.isSelected()) {
         couleursPref.add(bteJaune.getText());
      }
         
      if (bteOrange.isSelected()) {
         couleursPref.add(bteOrange.getText());   
      } 
      
      if (bteVert.isSelected()) {
         couleursPref.add(bteVert.getText());  
      } 
         
      if (bteMauve.isSelected()) {
         couleursPref.add(bteMauve.getText());
      } 
      
      info = info + "NOM    : " + nom + "\n\n" + 
                    "PRÉNOM : " + prenom + "\n\n" +
                    "ANNÉE NAISSANCE : " + annee + "\n\n" +
                    "GENRE : " + genre + "\n\n" +
                    "COULEUR(S) PRÉFÉRÉE(S) :\n";
      
      for (String c : couleursPref) {
         info = info + "- " + c + "\n";
      }
      return info;
   }
   
   
   public static void main (String [] args)  {
      new Exemple1Formulaire();
      
      //OU : Exécution d'un thread, MAJ du GUI
      /*
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Exemple1Formulaire();
            }
      });
      */
   }
   
   
   
   
}
