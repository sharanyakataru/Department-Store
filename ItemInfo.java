/**This is the item info class that contains information about tbe item
 * @author sharanyakataru
 */
public class ItemInfo {
    String product_name;
    Double price;
    String rfid_tag_number;
    String original_location;
    String current_location;

    /**Constructor of Item Info
     * @param name
     * name of the product
     * @param rfid
     * radio frequency for scanning the item
     * @param originalLocation
     * the original shelf position of the item
     * @param price
     * price of item
     */
    public ItemInfo(String name, String rfid, String originalLocation, double price) {
    this.product_name = name;
    this.price = price;
    this.rfid_tag_number = rfid;
    this.original_location = originalLocation;
    }

    public ItemInfo() {

    }

    /**
     * @return
     * name of the item
     */
    public String getProduct_name(){
        return product_name;
    }

    /**
     * @param product_name
     * sets the name of the product
     */
    public void setProduct_name(String product_name){
    this.product_name = product_name;
    }

    /**
     * @return
     * price of the item
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     * sets the price of the item
     * @throws
     * IllegalArgumentException
     */
    public void setPrice(Double price) {
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    /**
     * @return
     * the rfid tag number of the item
     */
    public String getRfid_tag_number(){
        return rfid_tag_number;
    }

    /**
     * @param rfid_tag_number
     * sets the rfid tag number of the item
     * @throws
     * IllegalArgumentException
     */
    public void setRfid_tag_number(String rfid_tag_number) {
        if(!isValidRfidTag_num(rfid_tag_number)){
            throw new IllegalArgumentException("Invalid RFID Tag Number. It should be 9 characters long.");
        }
        this.rfid_tag_number = rfid_tag_number;
    }

    /**
     * @return
     * the original location of the item
     */
    public String getOriginal_location() {
        return original_location;
    }

    /**
     * @param original_location
     * sets the original location of the item
     * @throws
     * IllegalArgumentException
     */
    public void setOriginal_location(String original_location) {
        if(!isValidLocation(original_location)){
            throw new IllegalArgumentException("Invalid original location format.");
        }
        this.original_location = original_location;
    }

    /**
     * @return
     * the current location of the item
     */
    public String getCurrent_location() {
        return current_location;
    }

    /**
     * @param current_location
     * sets the current location of the item
     * @throws
     * IllegalArgumentException
     */
    public void setCurrent_location(String current_location) {
        if(!isValidCurrentLocation(current_location)){
            throw new IllegalArgumentException("Invalid current location format.");
        }
        this.current_location = current_location;
    }

    private boolean isValidCurrentLocation(String currentLocation) {
        currentLocation = currentLocation.toLowerCase();
        if (currentLocation.equals("out")) {
            return true;
        }

        if (currentLocation.charAt(0) != 's' && currentLocation.length() == 6) {
            for (int i = 1; i < 6; i++) {
                char ch = currentLocation.charAt(i);
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }
            return true;
        } else {
            if (currentLocation.charAt(0) != 'c' && currentLocation.length() == 4) {
                for (int i = 1; i < 4; i++) {
                    char ch = currentLocation.charAt(i);
                    if (!Character.isDigit(ch)) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }
    }

    /**Checks if the rfid tag number is valid
     * @param rfid_tag_number
     * rfid tag number of the item
     * @return
     * true or false depending on if the rfid number is valid
     */
    private boolean isValidRfidTag_num(String rfid_tag_number){
        if ( rfid_tag_number.length() != 9){
            return false;
        }
       for(int i = 0; i < rfid_tag_number.length(); i++){
           char ch = Character.toUpperCase(rfid_tag_number.charAt(i));
           if((ch < '0' || ch > '9') && (ch < 'A' || ch > 'F')) {
               return false;
           }
        }
        return true;
    }

    /**Checks if the location of the item is valid
     * @param original_location
     * location of the product
     * @return
     * true or fasle depending on if the location of the item is valid or not
     */
    private boolean isValidLocation(String original_location){
        if(original_location.length() != 6){
            return false;
        }
        if(original_location.charAt(0) != 's'){
            return false;
        }
        for(int i = 1; i < 6; i++){
            char ch = original_location.charAt(i);
            if(!Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }

    /**
     * @return
     * the information of the item in a clean way
     */
    public String toString(){
        return String.format("%-15s %-15s %-15s %-15s %.2f%n", product_name, rfid_tag_number, original_location,
                current_location, price);
    }

}
