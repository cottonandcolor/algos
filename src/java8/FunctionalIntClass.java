package java8;

/**
 * Created by predave on 6/1/17.
 */
public class FunctionalIntClass {
    Integer val;
    public FunctionalIntClass(Integer val){
        this.val = val;
    }
    public String showStatus() {
        if(val == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    public Boolean doOperations( FunctionalExampleIntf intf) {
        return intf.getStatus(this);
    }

    public static void main(String[] args) {
        FunctionalIntClass f = new FunctionalIntClass(1);
        FunctionalExampleIntf fintfsucess = (t) -> t.showStatus().equals("success");
        FunctionalExampleIntf fintffail = (t) -> t.showStatus().equals("failure");

        System.out.println(f.doOperations(fintfsucess));
        System.out.println(f.doOperations(fintffail));

    }

}
