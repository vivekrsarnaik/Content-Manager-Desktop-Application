package sdp_asignment2;
//controller interface
public class Main {
    public static void main(String[] args) {
        ItemTableDescrip itemTable = new ItemTableDescrip();
        Controller itemController = new Controller(itemTable);
        itemController.run();
    }
}

