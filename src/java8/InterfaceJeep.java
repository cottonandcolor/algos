package java8;


public interface InterfaceJeep {
    static void showFeatures() {
        System.out.println("Jeep features");
    }

    default void playSound(){
        System.out.println("Play Jeep Sound");
    }

    default void showColor(){
        System.out.println("Show Jeep color");
    }
}
