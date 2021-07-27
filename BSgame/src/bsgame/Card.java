

/*
    CLASE CARD (Carta):
        - La carta tendrá como atribuitos : Valor(Value) y Palo (Suit)
        - Se tiene el tipo de dato ENUM 
            - Suit(Palo): corazon(0), diamante(1), espada(2), trebol(3)
            - Value(Valor): dos(0), tres(1), cuatro(2), cinco(3), seis(4), siete(5), ocho(6), nueve(7), diez(8), jota(9), reina(10), rey(11), as(12)

*/


package bsgame;

public class Card{
    
    enum Suit{
        corazon, diamante, espada, trebol; 

        private static final Suit[] suits = Suit.values();

        public static Suit getSuit(int i){
            return Suit.suits[i];
        }          
    }
    
    
    enum Value{
        dos, tres, cuatro, cinco, seis, siete, ocho, nueve ,diez, jota, reina, rey, as;

        private static final Value[] values = Value.values();

        public static Value getValue(int i){
            return Value.values[i];
        }
    }

    
    private final Suit suit;
    private final Value value;
   
    
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
  
    }
    
    public Suit getSuit(){
        return this.suit;
    }
    
    public Value getValue(){
        return this.value;
    }
    
    public Value getValue(int n){
           return Value.values[n];
    }
    
    /*
    Ejemplo:
        diamante-cinco
        corazon-diez
    */
    public String toString(){
        return suit + "-" + value ;
    }
    

    
}
