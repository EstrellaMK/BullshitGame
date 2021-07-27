

/*
    CLASE DECK (Mesa):
        - Se creará las cartas totales: 52 cartas
        - Barajar las cartas (shuffle)
        - Se repartirán las cartas a los jugadores


*/


package bsgame;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards; //Array de cartas
    private int cardsInDeck; //Cantidad de cartas en mesa
    

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    
    /*
        CREACIÓN DE LA BARAJA COMPLETA
        Se crea las 52 cartas añadiendose en un arraylist, llamando a la clase CARD
    */
    public void FullDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                this.cards.add(new Card(suit, value));
                cardsInDeck++;
            }
        }
    }
    
    
    /*
        BARAJEAR CARTAS
        Usaremos random para obtener una posicion aleatorio del arrayList de las 52 cartas(FullDeck())
        y añadirlas en otro arrayList(Tendrá las cartas aleatorias).
    */
    public void shuffle(){
        FullDeck();
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        
        Random random = new Random();
        int randomCardIndex;
        int originalSize =  this.cards.size();
        
        for (int i = 0; i < originalSize; i++) {
            
            randomCardIndex = random.nextInt((this.cards.size()));
            tmpDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
            
        }       
        
        this.cards = tmpDeck;
    }
    
    
    /*
        REPARTIR LA BARAJA GENERAL (52) CARTAS
        - El parámetro n, indicará la cantidad de cartas que se repartiran de acuerdo a la cantidad de jugadores
        - Creamos un array de tipo cartas llamado ret, el cual almacenará n cartas
    */
    public Card[] drawCard(int n){
        
        Card[] ret = new Card[n];        
        
        for(int i=0; i<n; i++){
           ret[i] = cards.get(--cardsInDeck);
        }
        
        return ret;
    }
}
