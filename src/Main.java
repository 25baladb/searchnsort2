import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
//dhsglhkfahsdkfj


public class Main extends PApplet {

    private ArrayList<wrapSmth> list = new ArrayList <wrapSmth>();
    int bottom = 0;
    int top = list.size() - 1;
    int mid;
    String target = "";
    int targetN;
    private boolean startingScreen = true;
    public static PApplet app;
    Table table;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main(){
        super();
        app = this;
    }

    public void settings(){
        size(995, 500);
    }

    public void setup(){
        table = loadTable("data/marineLife.csv", "header");
        for (TableRow row : table.rows()) {
            int depth = row.getInt("depth");
            String animal = row.getString("animal");
            list.add(new wrapSmth(depth, animal));
        }
        top = list.size();
    }

    public void draw(){

        if(startingScreen){
            int bottom = 0;
            int top = list.size() - 1;
            background(40, 80, 160);
            textSize(30);
            text("Enter in the name of a marine animal then press /", 200, 200);
            text("Instructions: Press 2 to play, 3 to search, and 1 to start over again", 100, 250);
            int x = 200;
            int y = 400;
        }

    }

    public void keyPressed(){
        if(key != '1' && key != '2' && key != '3'){
            target += key;
            if(key == '/'){
                target = target.substring(0, target.length() - 1);
                System.out.println(target);
                int j = 0;
                while(j < list.size()){
                    if(list.get(j).getAnimal().indexOf(target) != -1){
                        target = list.get(j).getAnimal();
                        System.out.println(target);
                    }
                    j++;
                }
                int i = 0;
                while(i < list.size()){
                    if(list.get(i).getAnimal().equals(target)){
                        targetN = list.get(i).getDepth();
                        System.out.println(targetN);
                    }
                    i++;
                }
            }
        }
        if(key == '1'){
            startingScreen = true;
        }
        if(key == '2'){
            startingScreen = false;
            playGame();
        }
        if(key == '3'){
            binarySearchIterative(target, list, targetN);
        }
    }

    public void playGame(){
        background(41, 86, 158);
        int y = 5;
        int x = 5;
        int s = 94;
        int yY = 58;
        int xX = 45;
        for(int i = 0; i < list.size(); i++){
            if(i < 13){
                fill(82, 175, 204);
            } else if(i < 19){
                fill(82, 131, 204);
            } else if( i < 26){
                fill(45, 100, 181);
            } else if (i < 34){
                fill(45, 68, 181);
            } else if(i < 44){
                fill(26, 44, 135);
            }else{
                fill(12, 25, 89);
            }
            rect(x, y, s, s);
            x += 99;
            fill(0);
            textSize(30);
            text("", xX, yY);
            xX += 99;
            if(x > 896){
                y += 99;
                x = 5;
                xX = 45;
                yY += 99;
            }
        }
    }

    public void foundTarget(){
        if(list.get(mid).getDepth() > 4000){
            background(8, 17, 41);
        } else if(list.get(mid).getDepth() > 1100){
            background(14, 33, 87);
        }  else if(list.get(mid).getDepth() > 600){
            background(29, 56, 150);
        } else if(list.get(mid).getDepth() > 200){
            background(52, 93, 207);
        } else{
            background(95, 130, 227);
        }
        int b = 0;
        int B = (int)(Math.random() * 135) + 120;
        while(b < 996){
            textSize(100);
            fill(30, 20, B);
            text("~", b, 500);
            text("~", b, 480);
            text("~", b, 460);
            text("~", b, 440);
            b += 33;
        }
        bottom = 0;
        top = list.size() - 1;
        stroke(80);
        fill(255);
        textSize(40);
        text(list.get(mid).toString(), 50, 200);
        target = "";
    }

    private int binarySearchIterative(String target, ArrayList<wrapSmth> list, int targetN) {
        int x = 5;
        int y = 5;
        if(bottom <= top) {
            mid = (bottom + top) / 2;
            if (target.equals(list.get(mid).getAnimal())) {
                foundTarget();
            } else if (targetN > list.get(mid).getDepth()) {
                bottom = mid + 1;
                playGame();
                textSize(40);
                fill(255);
                text(list.get(mid).getAnimal() + " are too shallow...", 300, 70);
            } else if (targetN < list.get(mid).getDepth()){
                top = mid - 1;
                playGame();
                textSize(40);
                fill(255);
                text(list.get(mid).getAnimal() + " are too deep...", 300, 460);
            }
        }
        return -1;
    }


























    private static int binarySearchRecursive(int target, int [] list, int top, int bottom){
        if (bottom > top) {
            return -1;
        }
        int mid = (bottom + top) / 2;
        if (target == list[mid]) {
            return mid;
        } else if (target < list[mid]) {
            return binarySearchRecursive(target, list,  mid - 1, bottom);
        } else {
            return binarySearchRecursive(target, list, top, mid + 1);
        }
    }
}