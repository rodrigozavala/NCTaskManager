package mx.tc.j2se.evaluation;

public class Evaluation1 {

    /**
     *
     * @param circles an array of circles
     * @return the index of the bigger circle in the array
     */
    public static int biggestCircle(Circle[] circles){
        double biggestRadius=0;//the bigger the radius the bigger the area
        int biggest=0;
        for (int i=0;i<circles.length;i++){
            if(circles[i].getRadius()>biggestRadius){
                biggest=i;
                biggestRadius=circles[i].getRadius();
            }
        }
        return biggest;
    }

    public static void main(String [] args){
        try{
            Circle circle=new Circle(-1);
        }catch (IllegalArgumentException e){
            System.out.println("An exception has occurred:");
            System.out.println(e.toString());
            System.out.println("Try to enter a positive radius value");
        }

        Circle circles[]={new Circle(),new Circle(7),new Circle(5)};

        int maxRadiusIndex=biggestCircle(circles);
        System.out.println("The radius of the largest circle is: ");
        System.out.println(circles[maxRadiusIndex].getRadius());

    }
}
