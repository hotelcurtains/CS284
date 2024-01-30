/* 
Name: Daniel Detore
Section: CS 284-E / CS 284-RJ
Pledge: I pledge my honor that I have abided by the Stevens Honor System.
*/

public class BinaryNumber{
    private int[] value;
    private int length;
    public BinaryNumber(int length){
        value = new int[length];
        this.length = length;
    }
    public BinaryNumber(String str){
        value = new int[str.length()];
        for (int i = 0; i<str.length(); i++){
            int current = Character.getNumericValue(str.charAt(i));
            if (current != 1 && current != 0){
                throw new IllegalArgumentException();
            }        
            value[i] = current;
        }
        length = str.length();
    }
    public int getLength(){
        return length;
    }
    public int[] getInnerArray(){
        return value;
    }
    public int getDigit(int index){
        try {
            return value[index];
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }
    public int toDecimal(){
        int output = 0;
        for (int i = 0; i < value.length; i++){
            output += value[i]*Math.pow(2,value.length-i-1);
        }
        return output;
    }
    public void bitShift(int direction, int amount){
        if (direction == 1){
            int[] neo = new int[value.length-amount];
            for (int i = 0; i < neo.length; i++){
                neo[i] = value[i];
            }
            value = neo;
            length -= amount;
        } else if (direction == -1){
            int[] neo = new int[value.length+amount];
            for (int i = 0; i < value.length-1; i++){
                neo[i] = value[i];
            }
            value = neo;
            length += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2){
        int end = bn1.getLength();
        if (end != bn2.getLength()){
            throw new IllegalArgumentException();
        }
        int[] out = new int[end];
        for (int i = 0; i < end; i++){
            if (bn1.getDigit(i) + bn2.getDigit(i) >= 1){
                out[i] = 1;
            }
        }
        return out;
    }
    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2){
        int end = bn1.getLength();
        if (end != bn2.getLength()){
            throw new IllegalArgumentException();
        }
        int[] out = new int[end];
        for (int i = 0; i < bn1.getLength(); i++){
            if (bn1.getDigit(i) + bn2.getDigit(i) == 2){
                out[i] = 1;
            }
        }
        return out;
    }
    public String toString(){
        String out = "";
        for (int i=0; i<value.length; i++){
            out+=value[i];
        }
        return out;
    }
    public void add(BinaryNumber aBinaryNumber){
        int[] current = value;
        int[] other = aBinaryNumber.getInnerArray();
        int size = Math.max(current.length, other.length);
        current = contain(current, size);
        other = contain(other, size);

        int carry = 0;
        for (int i = size-1; i >= 0; i--) {
            if (current[i] + other[i] + carry == 0){
                current[i] = 0;
                carry = 0;
            } else if (current[i] + other[i] + carry == 1){
                current[i] = 1;
                carry = 0;
            } else if (current[i] + other[i] + carry == 2){
                current[i] = 0;
                carry = 1;
            } else if (current[i] + other[i] + carry == 3){
                current[i] = 1;
                carry = 1;
            }
        }
        if(carry == 1){
            current = contain(current, size+1); 
            current[0] = 1;
            length++;
        }
        value = current;
    }
    // truncates byte or adds leading zeros to set the given BinaryNumber to the byte size given
    public static int[] contain(int[] input, int bits){
        int[] neo = new int[bits];
        int stop = 0;
        if (bits < input.length){
            stop = bits;
        } else {
            stop = input.length;
        }
        for (int i = 0; i < stop; i++){
            neo[bits-i-1] = input[input.length-i-1];
        }
        return neo;
    }
}