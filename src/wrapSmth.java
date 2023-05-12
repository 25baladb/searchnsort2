public class wrapSmth {

    private int d;
    private String a;

    public wrapSmth(int k, String b){
        super();
        d = k;
        a = b;
    }
    public int toInt(){
        return Integer.valueOf(d);
    }

    public String getAnimal(){
        return a;
    }

    public int getDepth(){
        return d;
    }

    public String toString(){
        return "You found " + a + " at " + d + " meters!";
    }

}
