/**This is the Department Store class that interacts with the program and updates the store inventory
 * @author sharanyakataru
 */
import java.util.Scanner;
public class DepartmentStore {
    public static void main(String[] args) {
        ItemList list = new ItemList();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome!");
        while(true){
            String userOptions = " C - Clean store\n" +
                    " I - Insert an item into the list\n" +
                    " L - List by location\n" +
                    " M - Move an item in the store\n" +
                    " O - Checkout\n" +
                    " P - Print all items in store\n" +
                    " R - Print by RFID tag number \n" +
                    " U - Update inventory system\n" +
                    " Q - Exit the program. ";
            System.out.println(userOptions + "\nPlease select an option: ");
            String letter = input.nextLine();
            letter = letter.toUpperCase();

            switch(letter){
                case"C":
                    try{
                        System.out.println("The following item(s) have been moved back to their original locations:");
                        list.cleanStore();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "I":
                    try{
                        ItemInfo info = new ItemInfo();
                        System.out.println("Enter the name:");
                        String name = input.nextLine();
                        System.out.println("Enter the RFID:");
                        String RFID = input.nextLine();
                        System.out.println("Enter the original location:");
                        String original_location = input.nextLine();
                        System.out.println("Enter the price:");
                        double price = input.nextDouble();
                        input.nextLine();

                        info.setProduct_name(name);
                        info.setRfid_tag_number(RFID);
                        info.setOriginal_location(original_location);
                        info.setPrice(price);

                        list.insertInfo(name,RFID, original_location, price);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case "L":
                    try{
                        System.out.println("Enter the location:");
                        String location = input.nextLine();
                        list.printByLocation(location);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case"M":
                    try{
                        System.out.println("Enter the RFID:");
                        String RFID = input.nextLine();
                        System.out.println("Enter the original location:");
                        String original_location = input.nextLine();
                        System.out.println("Enter the new location:");
                        String new_location = input.nextLine();


                        list.moveItem(RFID, original_location, new_location);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "O":
                    try{
                        System.out.println("Enter the cart number:");
                        String cart_number = input.nextLine();
                        list.checkOut(cart_number);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "P":
                    try{
                        list.printAll();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "R":
                    try{
                        System.out.println("Enter the RFID tag number:");
                        String rfid_tag = input.nextLine();
                        list.printByRfid(rfid_tag);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "U":
                    try{
                        System.out.println("The following item(s) have removed from the system:");
                        list.removeAllPurchased();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Q":
                    try{
                        System.out.println("Goodbye!");
                        System.exit(0);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }
}