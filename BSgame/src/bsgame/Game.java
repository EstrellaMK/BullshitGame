



/*
    CLASE GAME
        - 
 */
package bsgame;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Game {
    
    private Deck deck;
    
    private int currentPlayer; //El índice del jugador que está jugado 
    private String[] playerIds; // Array que almacena los nombre de los jugadores
    private boolean[] playerS = new boolean[4]; //Almacenará si miente o no el jugador de acuerdo a su posición
    private int cantidadRepartir; // La cantidad de cartas que se reparten de acuerdo a la cantidad de jugadores 
    
    private ArrayList<ArrayList<Card>> playerHand; // Será un arrayList insertado dentro de otro (Número de jugador -> baraja del jugador)
    private ArrayList<Card> stockPile; // Acumulación de cartas que los jugadores tiran

    
    public Game(String[] pids){
           deck = new Deck();
           deck.shuffle();
           stockPile = new ArrayList<Card>();
           
           playerIds = pids;
           currentPlayer = 0;
           playerHand = new ArrayList<ArrayList<Card>>();
           
           cantidadRepartir = 52/(pids.length); // La cantidad repartida de cartas depende de la cantidad de jugadores
           
           
           // Se separata las barajas de cada jugador y se alamcena en el arrayList PLAYERHAND
           for(int i=0; i<pids.length;i++){
               ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(cantidadRepartir)));
               playerHand.add(hand);
           }
              
    }
    
    
    //Retorna el índice del jugador actual
    public int getIdPlayer(){
        return currentPlayer;
    }
    
    //Retorna el nombre del jugador que se encuentra en el turno actual
    public String getCurrentPlayer(){
        return this.playerIds[this.currentPlayer];
    }
    
    
    //Retorna el índice del jugador (turno anterior)
    public int getPreviusId(int n){
        if(n == 0){
                return playerIds.length-1;
        }
        
        return n-1;
    }
    
    
    //Retorna el nombre del jugador (turno anterior)
    public String getPreviusPlayer(int i){
            int index = this.currentPlayer;
            if(index == -1){
                index = playerIds.length-1;
            }
            
            return this.playerIds[index];
    }
    
    
    //Retorna el array donde se contiene el nonmbre de los jugadores
    public String[] getPlayer(){
        return playerIds;
    }
    
    public ArrayList<ArrayList<Card>> getPlayerHandAll(){
        return playerHand;
    }
    
    //Retorna la baraja del jugador de acuerdo con su nombre
    public ArrayList<Card> getPlayerHand(String pid){
        int index = Arrays.asList(playerIds).indexOf(pid);
        return playerHand.get(index);
    }
    
    //Retorna la baraja de acuerdo con su índice
    public ArrayList<Card> getPlayerHandInt(int pid){       
        return playerHand.get(pid);
    }
    
    //Retorna la baraja de la mesa
    public ArrayList<Card> getStockPile(){
        return stockPile;
    
    }
    
    // Retorna el tamaño de la baraja de un jugador según su nombre
    public int getPlayerHandSize(String pid){
        return getPlayerHand(pid).size();
    }
    

    //Retorna si el jugador está mintiendo o no
    public boolean getBullshit(int pid){
        return playerS[pid];
    }
    
    //Verifica si la baraja del jugador se encuentra vacía
    public boolean hasEmptyHand(String pid){
        return getPlayerHand(pid).isEmpty();
    }
    

    
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException{
        if(this.playerIds[this.currentPlayer] != pid){
            throw new InvalidPlayerTurnException ("No es el turno del jugado", pid);
        }
    }
    
    public String convertirNumString(int n){
           switch (n){
                case 2:
                   return "dos";
                case 3:
                   return "tres";
                case 4:
                   return "cuatro";
                case 5:
                   return "cinco";
                case 6:
                   return "seis";
                case 7:
                   return "siete";
                case 8:
                   return "ocho";
                case 9:
                   return "nueve";
                case 10:
                   return "diez";
                case 11:
                   return "jota";
                case 12:
                   return "reina";
                case 13:
                   return "rey";
                case 14:
                   return "as";
                default:
                    return "cero";               
            }
    }
    
    public int validarNumero(String n){
           switch (n){
                case "dos":
                   return 2;
                case "tres":
                   return 3;
                case "cuatro":
                   return 4;
                case "cinco":
                   return 5;
                case "seis":
                   return 6;
                case "siete":
                   return 7;
                case "ocho":
                   return 8;
                case "nueve":
                   return 9;
                case "diez":
                   return 10;
                case "jota":
                   return 11;
                case "reina":
                   return 12;
                case "rey":
                   return 13;
                case "as":
                   return 14;
                default:
                    return 0;               
            }
    }
    
    public int validarPalo(String palo){
    
        switch(palo){
            case "corazon":
                return 0;
            case "diamante":
                return 1;
            case "espada":
                return 2;
            case "trebol":
                return 3;
            default:
                return 4;
        
        }
    
    }
    
    
    //Verifica que las cartas tiradas con el jugador estén acuerdo con la carta random asignada
    public boolean estaMintiendo(ArrayList<String> card,int value){
        
           for (int i = 0; i < card.size(); i++) {
                int posicion = card.get(i).indexOf("-");
                 String numero = card.get(i).substring(posicion+1);
             if(validarNumero(numero)!=value){
                return false;
             }  
         }
        return true;
    }
    
    
    //Confirmar cartas a tirar
     public void submitPlayerCard(String pid, ArrayList<String> card,int value)throws InvalidPlayerTurnException{

        checkPlayerTurn(pid);
        int contador = 0;
        int[] posiciones = new int[card.size()];
        ArrayList<Card> temp = new ArrayList<Card>();//Almacenarás las cartas tiradas por el jugador
        
        
       
        //Pasamos los elementos del arrayList tipo string a Card 
        for (int i = 0; i < card.size(); i++) {
             int pos = card.get(i).indexOf("-");
             String numero = card.get(i).substring(pos+1);
             String palo = card.get(i).substring(0,pos);
             int index = validarPalo(palo);
             int index2 = validarNumero(numero);           
             temp.add(new Card(Card.Suit.getSuit(index), Card.Value.getValue(index2-2)));
         }
        
        //Las cartas tiradas del jugador se tienen que remover de su baraja, para ello obtenemos la posicion de cada una de ellas
         for (int i = 0; i < this.playerHand.get(currentPlayer).size(); i++) {
             for (int j = 0; j < temp.size(); j++) {
                   if(playerHand.get(currentPlayer).get(i).getSuit() == temp.get(j).getSuit() && playerHand.get(currentPlayer).get(i).getValue() == temp.get(j).getValue()){           
                        posiciones[contador] = i;
                        contador++;
                  }  
             }
         }
        
        
        //Verificamos si el jugador está mintiedo
        this.playerS[currentPlayer] = estaMintiendo(card,value);

        
         //Quitamos las cartas de la baraja del jugador
          for (int i = 0; i < posiciones.length; i++) {
              
                this.playerHand.get(currentPlayer).remove(posiciones[i]-i);
            }
            
            
         //Lo añadimos a la pila de cartas   
            for (int i = 0; i < temp.size(); i++) {
                  stockPile.add(temp.get(i));
                  
            }
            
           
        //Luego de retirar las cartas, verificamos si todavía tiene cartas en su baraja
        if(hasEmptyHand(this.playerIds[currentPlayer])){
            String winner = this.playerIds[currentPlayer];
         
            new winnerFrame(this.playerIds,this.playerHand,winner).setVisible(true);
         
        }
        
    }
    

    //Permitirá avanzar un turno, luego de tirar las cartas
    public void submitDraws(String pid) throws InvalidPlayerTurnException{
        currentPlayer = (currentPlayer + 1)%playerIds.length;
    }

    class InvalidPlayerTurnException extends Exception {
        String playerId;

        public InvalidPlayerTurnException(String message,String pid) {
            super(message);
            playerId = pid;
            
        }
        
        
        public String getPid(){
            return playerId;
        }   
    }
}
