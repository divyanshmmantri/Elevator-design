import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main{
    static HashMap<Integer,Integer> floorMap = new HashMap<>();
    static List<Integer> floorlist = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    public static String go;
    public static int current = 0;
    public static int myfloor = 0;
    public static int maxfloor = 10;
    public static int minfloor =0;
// main function
    public static void main(String[] args) throws InterruptedException {
        floor();
        passenger();
    }
//define the floors
    public static void floor() {
        for ( int i = 0; i <= maxfloor; i++) {
            floorlist.add(i);
            floorMap.put(i,0);
        }
    }

    public static int count() throws InterruptedException{
        int c=0;
        for ( int i = 0; i <= maxfloor; i++) {
            c=floorMap.get(i)+c;
        }
        return c;
    }
//function is called when doors open of lift
    public static void Open(int j,char t) throws InterruptedException{
      
        System.out.println("doors are opening for "+j+" make space");
        myfloor=j;
        int s=count();
        if(s == 0){
            System.out.println("enter u or d");
            char d=in.next().toLowerCase().charAt(0);
            addPassenger(myfloor,d );
            if(d=='d'){
                goingDown();
                t='d';
            }else{
                goingUp();
                t='u';
            }
        }
        
        
        addPassenger(j,t);
    }
//add passenger in the lift
    public static void addPassenger(int myfloor,char t) throws InterruptedException{
        System.out.println(floorMap);

        
        System.out.println("enter the no of passenger to be boarded");
        int n= in.nextInt();
        current=myfloor;
        System.out.println(current +" "+t);
        // System.out.println("call by typing D or U");
        if(n<=8)
        {
        for(int i = 0 ; i < n ; i++ ){
        
            System.out.println("enter the direction and floor you want for passenger "+(i+1));
            char go=in.next().toLowerCase().charAt(0);
            myfloor=in.nextInt();
            if(go == 'd'){
                if(myfloor>current)
                    System.out.println("wrong direction");
            }else if(t==go)   
                floorMap.put(myfloor,floorMap.get(myfloor)+1);
            else if(go == 'u'){
                if(myfloor<current)
                    System.out.println("wrong direction");
            }else if(t==go)   
                floorMap.put(myfloor,floorMap.get(myfloor)+1);
            
            if(current==myfloor){
                System.out.println("we are on the same floor /n try filling details again");
                // passenger();
            }
            
            // else
            //     System.out.println("sorry wrong direction");

        }
        }
        else{
            System.out.println("lift is overloaded");
        }
    }
//function for initial passengers
    public static void passenger() throws InterruptedException{
        System.out.println("lift is on "+current+" floor");
        System.out.println("enter your floor");
        myfloor=in.nextInt();
        if(current > myfloor)
            System.out.println("lift is coming down");
        else if(current<myfloor) 
            System.out.println("lift is coming up");
        else 
            System.out.println("doors are opening");
        // Thread.sleep(myfloor*1000);
        mechanism();
    }
//check whether up or down 
    public static String check(int m) throws InterruptedException{
        int flag1=0;
        int flag2=0;
        for(int i=0;i<m;i++){
            if (floorMap.get(i)>0){
                flag1=1;
            }
        }
        for(int i=m;i<=maxfloor;i++){
            if (floorMap.get(i)>0){
                flag2=1;
            }
        }
        if(flag1==1)
            return "down";
        else if(flag2==1)
            return "up";
        else return "";
    }
//when lift is going up
    public static void goingDown() throws InterruptedException{
        System.out.println(floorMap);
        System.out.println("hey going down");
        char t ='d';
        for(int i=myfloor;i>=0 ; i--){
            // Thread.sleep(2000);
            if(floorMap.get(i)!=0){
                floorMap.put(i, 0);
                Open(i,t);
            }
        }
    }
// when lift is going down
    public static void goingUp()throws InterruptedException {
        System.out.println(floorMap);
        System.out.println("hey going up");
        char t ='u';
        for(int i=myfloor;i<=maxfloor ; i++){
            // Thread.sleep(2000);
            if(floorMap.get(i)!=0){
                floorMap.put(i, 0);
                Open(i,t);
            }
        }
    }
//function for user data
    public static void mechanism() throws InterruptedException {
        int n = current;
        String s = check(n);
        if(s.length()==4){
            System.out.println("the lift is going down");
            goingDown();
        }else if (s.length()==2){
            System.out.println("the lift is going up");
            goingUp();
        }else{
            System.out.println("we are on the same floor as pressed");
            System.out.println("where do you want to go u or d");
            char r=in.next().toLowerCase().charAt(0);
            if(r=='u'){
                addPassenger(myfloor, r);
                goingUp();
            }else if(r=='d'){
                addPassenger(myfloor, r);
                goingDown();
            }else {
                System.out.println("invalid input");
                mechanism();
            }
        }

    }
}
