/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsgame;

import bsgame.Game.InvalidPlayerTurnException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import static java.lang.Math.random;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author ESTRELLA
 */
public final class GameStage extends javax.swing.JFrame {

    
    configPlayer addPlayers = new configPlayer();
    Game game;
    
    
    ArrayList<String> temp = new ArrayList<>(); // Contendrá el array de nombre de jugadores que pasa desde el constructor
    ArrayList<String> cartasConfirmadas = new ArrayList<>(); // Serán las cartas seleccionadas por los jugadores (Límite de 4 cartas a tirar)
    ArrayList<JButton> cardButtons = new ArrayList<>();// Los botones del interfaz serán registrados en un array
    ArrayList<Boolean> cardButtonsBoolean = new ArrayList<>();
    ArrayList<String> cardIds; // Será la posición en la se encuentre la carta seleccionada de acuerdo al click del botón
    
    private String cardId; // Nombre de la carta
    String[]pids;  // Array de nombre de los jugadores
    int index;  
    int valorCarta = 0; // La carta random que el jugador debe tirar
    int valorCartaAnterior = 0;
    int[][] cpuLevel;  //Matriz que alamcena la posición y nivel de los jugadores
    
    int cantidadTiro;
    
    PopUp window;
    
    public GameStage(){}
   
    public GameStage(ArrayList<String> playerIds, int[][] c) {
        initComponents();
        this.setTitle("Bullshit Game: Partida");
        setIconImage(Toolkit.getDefaultToolkit().getImage((getClass().getResource("/bsgame/img2/iconGame.png"))));
        this.setLocationRelativeTo(null);
        
        temp = playerIds;
        pids = temp.toArray(new String[temp.size()]);
        game = new Game(pids);
        cpuLevel = c;
        
        populateArrayList();
        generarCartaMaster();
        setPidName();
        setButtonIcons();  
    }
    
    public void mostrarCantidad(ArrayList<String> player, ArrayList<ArrayList<Card>> playerHand){
        if(player.size()<3){
            p1.setText(player.get(0));
            p1cards.setText(String.valueOf(playerHand.get(0).size()));
            
            p2.setText(player.get(1));
            p2cards.setText(String.valueOf(playerHand.get(1).size()));
          
        }else if(player.size()<4){
            
            p1.setText(player.get(0));
            p1cards.setText(String.valueOf(playerHand.get(0).size()));
            
            p2.setText(player.get(1));
            p2cards.setText(String.valueOf(playerHand.get(1).size()));

            p3.setText(player.get(2));
            p3cards.setText(String.valueOf(playerHand.get(2).size()));
           
        
        }else{
            p1.setText(player.get(0));
            p1cards.setText(String.valueOf(playerHand.get(0).size()));
            
            p2.setText(player.get(1));
            p2cards.setText(String.valueOf(playerHand.get(1).size()));

            p3.setText(player.get(2));
            p3cards.setText(String.valueOf(playerHand.get(2).size()));
            
            p4.setText(player.get(3));
            p4cards.setText(String.valueOf(playerHand.get(3).size()));
       
        }
    }
    
    public void Botones(){
        for (int i = 0; i < cardButtons.size(); i++) {
            cardButtonsBoolean.set(i, false);
            setBorderReset(cardButtons.get(i));
            
        }
        
        for(int i = cardIds.size(); i<cardButtons.size();i++){
  
             cardButtons.get(i).setBackground(new Color(187,187,187));
             
        }
    }
    
    // Permite agregar una imagen a los jugadores
    public void setButtonIcons(){
        
        
        String listString = game.getPlayerHand(game.getCurrentPlayer()).stream().map(Object::toString).collect(Collectors.joining(","));
        String[] cardNames = listString.split(",");
        cardIds = new ArrayList<>(Arrays.asList(cardNames));
        
        for(int i = 0; i<cardIds.size();i++){
            cardButtons.get(i).setIcon(setIcono("/bsgame/img/"+cardIds.get(i)+".png", cardButtons.get(i))); 
        }
        
        
        
        imgCard.setIcon(setIconoLabel("/bsgame/img/z-cardBack.png",imgCard));
        

         for(int i = cardIds.size(); i<cardButtons.size();i++){
             cardButtons.get(i).setIcon(null);

        }
         
         Botones();
        mostrarCantidad(temp,game.getPlayerHandAll());
        desactivarBoton();
        
    }
    
    //Redenrizar las imágenes de acuerdo al tamaño de los botones y label
     public Icon setIconoLabel(String url, JLabel label){
            ImageIcon icon = new ImageIcon(getClass().getResource(url));

           int ancho = label.getWidth();
           int alto = label.getHeight();
           
           ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto,Image.SCALE_DEFAULT));
           return icono;
         
     }
    
    public Icon setIcono(String url, JButton boton){
           ImageIcon icon = new ImageIcon(getClass().getResource(url));
           
         
           int ancho = jButton1.getWidth();
           int alto = jButton1.getHeight();
           
           ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto,Image.SCALE_DEFAULT));
           return icono;
           
    
    }
    
    // Agregará los botonoes a un arrayList
    public void populateArrayList(){
        cardButtons.add(jButton1);cardButtons.add(jButton2);cardButtons.add(jButton3);cardButtons.add(jButton4);cardButtons.add(jButton5);cardButtons.add(jButton6);cardButtons.add(jButton7);cardButtons.add(jButton8);cardButtons.add(jButton9);cardButtons.add(jButton10);
        cardButtons.add(jButton11);cardButtons.add(jButton12);cardButtons.add(jButton13);cardButtons.add(jButton14);cardButtons.add(jButton15);cardButtons.add(jButton16);cardButtons.add(jButton17);cardButtons.add(jButton18);cardButtons.add(jButton19);cardButtons.add(jButton20);
        cardButtons.add(jButton21);cardButtons.add(jButton22);cardButtons.add(jButton23);cardButtons.add(jButton24);cardButtons.add(jButton25);cardButtons.add(jButton26);cardButtons.add(jButton27);cardButtons.add(jButton28);cardButtons.add(jButton29);cardButtons.add(jButton30);
        cardButtons.add(jButton31);cardButtons.add(jButton32);cardButtons.add(jButton33);cardButtons.add(jButton34);cardButtons.add(jButton35);cardButtons.add(jButton36);cardButtons.add(jButton37);cardButtons.add(jButton38);cardButtons.add(jButton39);cardButtons.add(jButton40);
        cardButtons.add(jButton41);cardButtons.add(jButton42);cardButtons.add(jButton43);cardButtons.add(jButton44);cardButtons.add(jButton45);cardButtons.add(jButton46);cardButtons.add(jButton47);cardButtons.add(jButton48);cardButtons.add(jButton49);cardButtons.add(jButton50);
         for (int i = 0; i < cardButtons.size(); i++) {
            cardButtonsBoolean.add(false);
            setBorderReset(cardButtons.get(i));
            
        }

    }
    
    
    public void setBorder(JButton btn){
        btn.setBorder(BorderFactory.createLineBorder(new Color(255,193,7),3));
    }
    
    public void setBorderReset(JButton btn){
        btn.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),0));
    }
    
    //Estabelce el label con el nombre del jugador en el turno actual
    public void setPidName(){
        String currentPlayer = game.getCurrentPlayer();
        pidNameLabel.setText("Jugador:"+currentPlayer);
    }
    
    public void setPidName(String currentPlayer){
        pidNameLabel.setText("Jugador:"+currentPlayer);
    }
    
    
    //Generamos la carta random para el jugador
    public int generarCartaMaster(){
        
        cartaMasterPrevia.setText(Integer.toString(valorCarta));
        valorCartaAnterior = valorCarta;
        valorCarta= (int) Math.floor(Math.random()*(14-2+1)+2);
        cartaMaster1.setText(Integer.toString(valorCarta));
        
        return valorCarta;
    }
    
    //Verificamos si el jugador el una máquina o humano
    public void verificarJugador() throws InvalidPlayerTurnException{
        boolean activar = true;
        
        if(temp.get(game.getIdPlayer()).contains("CPU")){
 
            CardsPanel.setVisible(!activar);
            Mentira.setVisible(!activar);
            drawCardBtn.setVisible(!activar);
            JButtonPasar.setVisible(!activar);
            CPUlabel.setText("El CPU está analizando");
            
            metodoNivel();
                   
        }else{
            CardsPanel.setVisible(activar);
            Mentira.setVisible(activar);
            drawCardBtn.setVisible(activar);
            JButtonPasar.setVisible(activar);
        }  
    }

    //Verificamos el nivel de la máquina
    public int verificarNivel(int[][] n){
        int k = 0;        
        for (int i = 0; i < 3; i++) {
            if (n[i][0] == game.getIdPlayer()) {
                k = n[i][1];
            }
        }
        return k+1;
    }
    
    //De acuerdo a su nivel, se retornará un método
    public void metodoNivel() throws InvalidPlayerTurnException{
        switch(verificarNivel(cpuLevel)){
            case 1:
                    nivel1();
                break;
            case 2:
                    nivel2();
                break;
            case 3:
                    nivel3();
                break;
        }
    }

    /*Método Nivel 1
        - La computadora, a fin de poder ganar, botará 4 cartas de su baraja 
        - Ya que la máquina tiene un nivel 1, tendrá un aleatorio MENTIR O PASAR 
        - Cabe existe una mínima posibilidad de que la máquina gane, pero no por error de ella sino por el error del jugador humano.
    
    */
    
    
    public boolean validarRandom(int random, int indices[]){
        for (int i = 0; i < indices.length; i++) {
            if(random == indices[i]){
                return false;
            }
        }
        
        return true;
    };
    
    public void metodoPasar() throws InvalidPlayerTurnException{
        /*
            Opción: PASAR
                - Escoger cualquier carta
                - Tirar 4 cartas

        */
                int indices[] = new int[4];
                int contador = 0;
                 for (int i = 0; i < 15 && contador <4; i++) {
                    int k = (int) Math.floor(Math.random()*((game.getPlayerHand(game.getCurrentPlayer()).size()-1)-0+1)+0);
                     if (validarRandom(k,indices)) {
                         if(cardIds.get(k) != null){
                            index = k;
                            cardId =  cardIds.get(k);
                            cartasConfirmadas.add(cardId);
                             
                            indices[contador] = k;
                            contador++;
                        }
                    }
                }
                 
               
                 
                 cantidadTiro =cartasConfirmadas.size();
                 game.submitPlayerCard(game.getCurrentPlayer(),cartasConfirmadas,valorCarta);
                 cartasConfirmadas.clear();

                    try{
                     
                    game.submitDraws(game.getCurrentPlayer());
                    cartasDeck.setText( Integer.toString(game.getStockPile().size())); 
                    }catch(InvalidPlayerTurnException e){

                     }

                      this.setPidName(game.getCurrentPlayer());
                      generarCartaMaster();
                      cardButtons.clear();
                      populateArrayList();
                      try {
                          verificarJugador();
                      } catch (InvalidPlayerTurnException ex) {
                          java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                      }
                      this.setButtonIcons();
    }
    
    
    public void metodoAcusar(){
         boolean mentira = game.getBullshit(game.getPreviusId(game.getIdPlayer()));

                if(mentira){
            
                    for (int i = 0 ; i<game.getStockPile().size();i++){
                           game.getPlayerHandInt(game.getIdPlayer()).add(game.getStockPile().get(i));
                    }
                    
                }else{

                    for (int i = 0 ; i<game.getStockPile().size();i++){
                         game.getPlayerHandInt(game.getPreviusId(game.getIdPlayer())).add(game.getStockPile().get(i));

                    }
               
                }
                

                try{
                    game.getStockPile().clear();
                    game.submitDraws(game.getCurrentPlayer());
                    cartasDeck.setText( Integer.toString(game.getStockPile().size())); 
                }catch(InvalidPlayerTurnException e){

                 }

                  this.setPidName(game.getCurrentPlayer());
                  generarCartaMaster();
                  cardButtons.clear();
                  populateArrayList();
                  try {
                      verificarJugador();
                  } catch (InvalidPlayerTurnException ex) {
                      java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                  }
                  this.setButtonIcons();
    }
    
   
    
    public void nivel1() throws InvalidPlayerTurnException{
        
        String listString = game.getPlayerHand(game.getCurrentPlayer()).stream().map(Object::toString).collect(Collectors.joining(","));
        String[] cardNames = listString.split(",");
        cardIds = new ArrayList<>(Arrays.asList(cardNames));
        
        switch((int) ( Math.random() * 2 + 1)){
            case 1:
                    
                    metodoPasar();
                break;
                
            case 2:
                    
                    metodoAcusar();
             break;
        }    
    }
    
    //Método Nivel 2
    //n: cantidadCRandomBaraja
    
    public void metodoPasar2(int n) throws InvalidPlayerTurnException{
        /*
                    Opción: PASAR
                        - Escoger cualquier carta
                        - Tirar 4 cartas
                
                */
                this.setButtonIcons();
              
                
                
                if (game.getPlayerHandSize(game.getCurrentPlayer()) <=4) {
                    for (int k  = 0; k < game.getPlayerHandSize(game.getCurrentPlayer()); k++) {
                        
                        if(cardIds.get(k) != null){
                            index = k;
                            cardId =  cardIds.get(k);
                            cartasConfirmadas.add(cardId);
                        }
                    }
                }else{
                
                        if(n != 0 ){
                        for (int i = 0; i < game.getPlayerHandSize(game.getCurrentPlayer()); i++) {
                            if(game.validarNumero(game.getPlayerHand(game.getCurrentPlayer()).get(i).getValue().toString()) == valorCarta){  

                                    cardId =  cardIds.get(i);
                                    cartasConfirmadas.add(cardId);
                            }  
                        }
                    }else{
                         int k = (int) Math.floor(Math.random()*(( game.getPlayerHandSize(game.getCurrentPlayer())-1)-0+1)+0);
                            if(cardIds.get(k) != null){
                                index = k;
                                cardId =  cardIds.get(k);
                                cartasConfirmadas.add(cardId);
                            }
                    }


                
                
                }
                

                 cantidadTiro =cartasConfirmadas.size();
                 game.submitPlayerCard(game.getCurrentPlayer(),cartasConfirmadas,valorCarta);
                 cartasConfirmadas.clear();

                    try{
                     
                    game.submitDraws(game.getCurrentPlayer());
                    cartasDeck.setText( Integer.toString(game.getStockPile().size())); 
                    }catch(InvalidPlayerTurnException e){

                     }

                      this.setPidName(game.getCurrentPlayer());
                      generarCartaMaster();
                      cardButtons.clear();
                      populateArrayList();
                      try {
                          verificarJugador();
                      } catch (InvalidPlayerTurnException ex) {
                          java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                      }
                      this.setButtonIcons();
    }
    
    public int Pasar2(int cantidadRandom, int cartasTiradas, int barajaTotal){
        if (cantidadRandom >0) {
            return barajaTotal - cantidadRandom;
        }else{
            return barajaTotal-1;
        }
    }
    
    public int Acusar2(int cantidadRandom, int cartasTiradas, int barajaTotal){
        if (cantidadRandom==0 && cartasTiradas != 0) {
            return barajaTotal + game.getStockPile().size();
        }else if(cantidadRandom+cartasTiradas<=4){
           return barajaTotal + game.getStockPile().size();

        }else{
            // #numCartasTiro(JA) + #cantidadCRandomBaraja > 4
            return barajaTotal;
        }
        
    }
    
    public double funcionEvaluadora(double metodo, double a){
       
        return 1/metodo + a;
    }

    public void nivel2() throws InvalidPlayerTurnException{
        //valorCartaAnterior
        
        //Función Evaluadora: 1/x + a
        
        int Total = game.getPlayerHandSize(game.getCurrentPlayer());
        int cantidadCRandomBarajaAnterior = 0;
        int cantidadCRandomBarajaActual = 0;
        double alpha = 0;


        
        for (int i = 0; i < game.getPlayerHandSize(game.getCurrentPlayer()); i++) {
            if(game.validarNumero(game.getPlayerHand(game.getCurrentPlayer()).get(i).getValue().toString()) == valorCartaAnterior){           
               cantidadCRandomBarajaAnterior++;
            }  
         }
        
        for (int i = 0; i < game.getPlayerHandSize(game.getCurrentPlayer()); i++) {
            if(game.validarNumero(game.getPlayerHand(game.getCurrentPlayer()).get(i).getValue().toString()) == valorCarta){           
               cantidadCRandomBarajaActual++;
            }  
         }
        
        double Tirar = Pasar2(cantidadCRandomBarajaActual, cantidadTiro, Total);
        double Acusar = Acusar2(cantidadCRandomBarajaAnterior, cantidadTiro, Total);
        
        double nodoTirar = funcionEvaluadora(Tirar,0);
        
        if(Acusar == Total){
           alpha = 1.0;
        }
        double nodoAcusar = funcionEvaluadora(Acusar, alpha);

        if (nodoTirar > nodoAcusar) {
            metodoPasar2(cantidadCRandomBarajaActual);
        }else{
            metodoAcusar();
        }
        
  
        
    }
    
    //Método Nivel 3
    
 
    public int Acusar3(int cantidadRandom, int cartasTiradas, int barajaTotal){

        if (cantidadRandom==0 && cartasTiradas != 0) {
            return barajaTotal + game.getStockPile().size();
            
        }else if(cantidadRandom+cartasTiradas<=4){
           switch((int)(Math.random() * 2 + 1)){
               case 1:
                   return barajaTotal + game.getStockPile().size();
               case 2:
                   return barajaTotal;
               
               default:
                   return 0;
                   
           }

        }else{
            // #numCartasTiro(JA) + #cantidadCRandomBaraja > 4
            return barajaTotal;
        }
  
        
    }

    public void nivel3() throws InvalidPlayerTurnException{
        //valorCartaAnterior
        
        //Función Evaluadora: 1/x + a
        
        int Total = game.getPlayerHandSize(game.getCurrentPlayer());
        int cantidadCRandomBarajaAnterior = 0;
        int cantidadCRandomBarajaActual = 0;
        double alpha = 0;


        
        for (int i = 0; i < game.getPlayerHandSize(game.getCurrentPlayer()); i++) {
            if(game.validarNumero(game.getPlayerHand(game.getCurrentPlayer()).get(i).getValue().toString()) == valorCartaAnterior){           
               cantidadCRandomBarajaAnterior++;
            }  
         }
        
        for (int i = 0; i < game.getPlayerHandSize(game.getCurrentPlayer()); i++) {
            if(game.validarNumero(game.getPlayerHand(game.getCurrentPlayer()).get(i).getValue().toString()) == valorCarta){           
               cantidadCRandomBarajaActual++;
            }  
         }
        
        double Tirar = Pasar2(cantidadCRandomBarajaActual, cantidadTiro, Total);
        double Acusar = Acusar3(cantidadCRandomBarajaAnterior, cantidadTiro, Total);
        
        double nodoTirar = funcionEvaluadora(Tirar,0);
        
        if(Acusar == Total){
           alpha = 1.0;
        }
        double nodoAcusar = funcionEvaluadora(Acusar, alpha);

        if (nodoTirar > nodoAcusar) {
            metodoPasar2(cantidadCRandomBarajaActual);
        }else{
            metodoAcusar();
        }
        
    }
    
 

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cartaMasterPrevia = new javax.swing.JLabel();
        cartasDeck = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        imgCard = new javax.swing.JLabel();
        cartaMaster1 = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        p1cards = new javax.swing.JLabel();
        p2 = new javax.swing.JLabel();
        p2cards = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();
        p3cards = new javax.swing.JLabel();
        p4 = new javax.swing.JLabel();
        p4cards = new javax.swing.JLabel();
        CPULabel = new javax.swing.JPanel();
        pidNameLabel = new javax.swing.JLabel();
        Mentira = new javax.swing.JButton();
        drawCardBtn = new javax.swing.JButton();
        JButtonPasar = new javax.swing.JButton();
        CardsPanel = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        CPUlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bullshit Game");
        setMaximumSize(new java.awt.Dimension(1500, 800));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        cartaMasterPrevia.setBackground(new java.awt.Color(255, 255, 255));
        cartaMasterPrevia.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        cartaMasterPrevia.setForeground(new java.awt.Color(0, 0, 0));
        cartaMasterPrevia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaMasterPrevia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carta Previa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        cartasDeck.setBackground(new java.awt.Color(153, 204, 0));
        cartasDeck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cartasDeck.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("cartas en mesa");

        imgCard.setBorder(new javax.swing.border.MatteBorder(null));

        cartaMaster1.setBackground(new java.awt.Color(102, 0, 0));
        cartaMaster1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        cartaMaster1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaMaster1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        p1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        p1.setForeground(new java.awt.Color(0, 0, 0));

        p1cards.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p1cards.setForeground(new java.awt.Color(0, 0, 0));
        p1cards.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        p2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        p2.setForeground(new java.awt.Color(0, 0, 0));

        p2cards.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p2cards.setForeground(new java.awt.Color(0, 0, 0));
        p2cards.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        p3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        p3.setForeground(new java.awt.Color(0, 0, 0));

        p3cards.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p3cards.setForeground(new java.awt.Color(0, 0, 0));
        p3cards.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        p4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        p4.setForeground(new java.awt.Color(0, 0, 0));

        p4cards.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p4cards.setForeground(new java.awt.Color(0, 0, 0));
        p4cards.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p3cards, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p4cards, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p1cards, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p2cards, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104)
                .addComponent(cartaMasterPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184)
                .addComponent(imgCard, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(cartasDeck, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 417, Short.MAX_VALUE)
                .addComponent(cartaMaster1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cartaMaster1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cartasDeck, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(cartaMasterPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p1cards, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p2cards, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p3cards, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p4cards, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(imgCard, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        CPULabel.setBackground(new java.awt.Color(52, 58, 64));
        CPULabel.setMaximumSize(new java.awt.Dimension(1480, 294));
        CPULabel.setPreferredSize(new java.awt.Dimension(1480, 294));

        pidNameLabel.setFont(new java.awt.Font("DialogInput", 1, 36)); // NOI18N
        pidNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        pidNameLabel.setText("jLabel1");

        Mentira.setBackground(new java.awt.Color(255, 193, 7));
        Mentira.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        Mentira.setForeground(new java.awt.Color(0, 0, 0));
        Mentira.setText("Acusar");
        Mentira.setFocusPainted(false);
        Mentira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MentiraActionPerformed(evt);
            }
        });

        drawCardBtn.setBackground(new java.awt.Color(255, 193, 7));
        drawCardBtn.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        drawCardBtn.setForeground(new java.awt.Color(0, 0, 0));
        drawCardBtn.setText("Tirar Cartas");
        drawCardBtn.setFocusPainted(false);
        drawCardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawCardBtnActionPerformed(evt);
            }
        });

        JButtonPasar.setBackground(new java.awt.Color(255, 193, 7));
        JButtonPasar.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        JButtonPasar.setForeground(new java.awt.Color(0, 0, 0));
        JButtonPasar.setText("Pasar");
        JButtonPasar.setFocusPainted(false);
        JButtonPasar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonPasarActionPerformed(evt);
            }
        });

        CardsPanel.setBackground(new java.awt.Color(187, 187, 187));
        CardsPanel.setMaximumSize(new java.awt.Dimension(1457, 327));
        CardsPanel.setMinimumSize(new java.awt.Dimension(1457, 327));

        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CardsPanelLayout = new javax.swing.GroupLayout(CardsPanel);
        CardsPanel.setLayout(CardsPanelLayout);
        CardsPanelLayout.setHorizontalGroup(
            CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardsPanelLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CardsPanelLayout.createSequentialGroup()
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CardsPanelLayout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CardsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15, jButton16, jButton17, jButton18, jButton19, jButton2, jButton20, jButton21, jButton22, jButton23, jButton24, jButton25, jButton26, jButton27, jButton28, jButton29, jButton3, jButton30, jButton31, jButton32, jButton33, jButton34, jButton35, jButton36, jButton37, jButton38, jButton39, jButton4, jButton40, jButton41, jButton42, jButton43, jButton44, jButton45, jButton46, jButton47, jButton48, jButton49, jButton5, jButton50, jButton6, jButton7, jButton8, jButton9});

        CardsPanelLayout.setVerticalGroup(
            CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CardsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton24, jButton25, jButton26, jButton27, jButton28, jButton29, jButton30, jButton31, jButton32, jButton33, jButton34, jButton35, jButton36, jButton37, jButton38, jButton39, jButton40, jButton41, jButton42, jButton43, jButton44, jButton45, jButton46, jButton47, jButton48, jButton49, jButton50});

        CardsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton5});

        CardsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton20, jButton22});

        CPUlabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout CPULabelLayout = new javax.swing.GroupLayout(CPULabel);
        CPULabel.setLayout(CPULabelLayout);
        CPULabelLayout.setHorizontalGroup(
            CPULabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CPULabelLayout.createSequentialGroup()
                .addGap(526, 526, 526)
                .addComponent(CPUlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(599, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CPULabelLayout.createSequentialGroup()
                .addGroup(CPULabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CPULabelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CardsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CPULabelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pidNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Mentira, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JButtonPasar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawCardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)))
                .addGap(55, 55, 55))
        );
        CPULabelLayout.setVerticalGroup(
            CPULabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CPULabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CPULabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pidNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CPULabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JButtonPasar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(drawCardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Mentira, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(CardsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(CPUlabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(CPULabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(CPULabel, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
         int k=39;
       condicionCarta(k);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        int k=37;
        condicionCarta(k);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
         int k=35;
        condicionCarta(k);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
         int k=17;
        condicionCarta(k);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
         int k=14;
        condicionCarta(k);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
         int k=11;
        condicionCarta(k);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         int k=8;
        condicionCarta(k);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         int k=5;
        condicionCarta(k);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         int k=2;
        condicionCarta(k);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         int k=1;
        condicionCarta(k);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
         int k=23;
        condicionCarta(k);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
         int k=26;
        condicionCarta(k);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
         int k=27;
        condicionCarta(k);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
         int k=28;
        condicionCarta(k);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
         int k=29;
        condicionCarta(k);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
         int k=30;
        condicionCarta(k);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
         int k=31;
       condicionCarta(k);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
         int k=40;
        condicionCarta(k);
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
         int k=41;
        condicionCarta(k);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
         int k=42;
        condicionCarta(k);
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
         int k=44;
        condicionCarta(k);
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
         int k=46;
        condicionCarta(k);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
         int k=47;
        condicionCarta(k);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
         int k=48;
        condicionCarta(k);
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int k=0;
        condicionCarta(k);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         int k=3;
        condicionCarta(k);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         int k=4;
        condicionCarta(k);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         int k=6;
       condicionCarta(k);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         int k=7;
        condicionCarta(k);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         int k=9;
       condicionCarta(k);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
         int k=10;
       condicionCarta(k);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
         int k=12;
        condicionCarta(k);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        int k=19;
        condicionCarta(k);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
       int k=20;
        condicionCarta(k);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
       int k=22;
        condicionCarta(k);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        int k=18;
       condicionCarta(k);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        int k=25;
       condicionCarta(k);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int k=13;
       condicionCarta(k);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       int k=15;
        condicionCarta(k);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       int k=16;
        condicionCarta(k);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        int k=21;
        condicionCarta(k);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        int k=24;
       condicionCarta(k);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        int k=32;
       condicionCarta(k);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
       int k=33;
        condicionCarta(k);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
      int k=34;
        condicionCarta(k);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
       int k=36;
       condicionCarta(k);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
       int k=38;
       condicionCarta(k);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
       int k=43;
        condicionCarta(k);
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
       int k=45;
        condicionCarta(k);
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        int k=49;
       condicionCarta(k);
    }//GEN-LAST:event_jButton50ActionPerformed

    private void MentiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MentiraActionPerformed
        activarBoton2();  
        boolean mentira = game.getBullshit(game.getPreviusId(game.getIdPlayer()));

        if(mentira){
            
             for (int i = 0 ; i<game.getStockPile().size();i++){
                    game.getPlayerHandInt(game.getIdPlayer()).add(game.getStockPile().get(i));
            }
            
        }else{
               for (int i = 0 ; i<game.getStockPile().size();i++){
                    game.getPlayerHandInt(game.getPreviusId(game.getIdPlayer())).add(game.getStockPile().get(i));
                 
                }
               
 
        }
          try{
                game.getStockPile().clear();
               game.submitDraws(game.getCurrentPlayer());
               cartasDeck.setText( Integer.toString(game.getStockPile().size())); 
          }catch(InvalidPlayerTurnException e){

           }

            this.setPidName(game.getCurrentPlayer());
            generarCartaMaster();
            cardButtons.clear();
            populateArrayList();
            try {
                verificarJugador();
            } catch (InvalidPlayerTurnException ex) {
                java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            this.setButtonIcons();

        
       
    }//GEN-LAST:event_MentiraActionPerformed


    public void condicionCarta(int k){
      
        if(k < cardIds.size()){
            if(cartasConfirmadas.size()<4){
                if(cardIds.get(k) != null && cardButtonsBoolean.get(k) == false){
                    index = k;
                    cardId =  cardIds.get(k);
                    cartasConfirmadas.add(cardId);
                    cardButtonsBoolean.set(k, true);
                    setBorder(cardButtons.get(k));
                }else{
                    verificarCarta();
                }
            }else{
                verificarCantidadCartas();

            }
        }
    
    }
    
    
    public void verificarCarta(){
        JLabel message = new JLabel("Carta ya seleccionada");
                message.setFont(new Font("Arial", Font.BOLD,14));
                JOptionPane.showMessageDialog(null,message);
    }
    
    public void verificarCantidadCartas(){
         JLabel message = new JLabel("Ya tiene 4 cartas seleccionadas");
                message.setFont(new Font("Arial", Font.BOLD,14));
                JOptionPane.showMessageDialog(null,message);
    }
    
    
    
//PASAR - TIRAR CARTA - MENTIR
    
    public void desactivarBoton2(){
         drawCardBtn.setEnabled(false);
         drawCardBtn.setVisible(false);
        
    }
    
    public void activarBoton2(){
         drawCardBtn.setEnabled(true);
         drawCardBtn.setVisible(true);
        
    }

    public void activarBoton(){
        JButtonPasar.setEnabled(true);
        JButtonPasar.setVisible(true);
    }
    
    public void desactivarBoton(){
        JButtonPasar.setEnabled(false);
        JButtonPasar.setVisible(false);
    }
     
    private void JButtonPasarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonPasarActionPerformed
         activarBoton2();     
         
        
        try{
            game.submitDraws(game.getCurrentPlayer());

            cartasDeck.setText( Integer.toString(game.getStockPile().size())); 
        }catch(InvalidPlayerTurnException e){

        }

        this.setPidName(game.getCurrentPlayer());
        generarCartaMaster();
        try {
            verificarJugador();
        } catch (InvalidPlayerTurnException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.setButtonIcons();
    }//GEN-LAST:event_JButtonPasarActionPerformed

    private void drawCardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawCardBtnActionPerformed
        if(cartasConfirmadas.isEmpty()){
            JLabel message = new JLabel("Tienes que seleccionar al menos una carta");
            message.setFont(new Font("Arial", Font.BOLD,36));
            JOptionPane.showMessageDialog(null,message);
        }else{
            cantidadTiro = cartasConfirmadas.size();
            window = new PopUp(cartasConfirmadas,game,cardButtons,this,valorCarta);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cartasConfirmadas.clear();
            
        }
        
    }//GEN-LAST:event_drawCardBtnActionPerformed

    
  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GameStage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CPULabel;
    private javax.swing.JLabel CPUlabel;
    private javax.swing.JPanel CardsPanel;
    private javax.swing.JButton JButtonPasar;
    private javax.swing.JButton Mentira;
    private javax.swing.JLabel cartaMaster1;
    private javax.swing.JLabel cartaMasterPrevia;
    private javax.swing.JLabel cartasDeck;
    private javax.swing.JButton drawCardBtn;
    private javax.swing.JLabel imgCard;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p1cards;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p2cards;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p3cards;
    private javax.swing.JLabel p4;
    private javax.swing.JLabel p4cards;
    private javax.swing.JLabel pidNameLabel;
    // End of variables declaration//GEN-END:variables
}
