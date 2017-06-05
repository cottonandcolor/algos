package java8;


public interface InterfaceCar {
    static void showFeatures() {
        System.out.println("Car features");
    }
    default void playSound(){
        System.out.println("Play Car Sound");
    }
    default void showColor(){
        System.out.println("Show car color");
    }
}
