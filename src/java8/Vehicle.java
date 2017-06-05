package java8;


public class Vehicle implements InterfaceCar, InterfaceJeep {

    @Override
    public void playSound() {
        System.out.println("play vehicle sound");
    }

    @Override
    public void showColor() {
        System.out.println("show vehicle color");
    }

    public void useStaticMethods(){
        InterfaceCar.showFeatures();
        InterfaceJeep.showFeatures();
    }
}
