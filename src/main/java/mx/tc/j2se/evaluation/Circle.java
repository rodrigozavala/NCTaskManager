package mx.tc.j2se.evaluation;
import java.lang.Math;
public class Circle {
    private int radius;
    private static final double PI=Math.PI;

    /**
     * Default constructor, initializes radius to 1
     */
    public Circle(){
        this.radius=1;
    }

    /**
     *Alternative constructor, throws an exception if radius value is invalid: equal or lees than 0
     * @param radius
     */
    public Circle(int radius){
        if(radius<=0){
            throw new IllegalArgumentException("Radius value cannot be equal or less than 0");
        }else{
            this.radius=radius;
        }
    }

    /**
     * Setter method to set the radius value
     * @param radius
     */
    public void setRadius(int radius){
        if(radius<=0){
            throw new IllegalArgumentException("Radius value cannot be equal or less than 0");
        }else {
            this.radius = radius;
        }
    }

    /**
     * Getter method
     * @return radius value
     */
    public int getRadius(){
        return this.radius;
    }


    /**
     * Getter method
     * @return  circle's area
     */
    public double getArea(){
        return PI*Math.pow(radius,2);
    }

}
