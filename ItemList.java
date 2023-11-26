/**This is the item list class which contains references to the head and tail of the nodes
 * @author sharanyakataru
 */
public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;
public ItemList(){
    this.head = null;
    this.tail = null;
}

    /**Inserts information into the list, in its correct position
     *
     * @param name
     * name of the item
     * @param rfidTag
     * rfid tag number of the item
     * @param initPosition
     * position of the item
     * @param price
     * price of the item
     * Complexity: O(n) - Traverse through the list to find the correct position
     */
    public void insertInfo(String name, String rfidTag,String initPosition,double price
                           ) {
        //new node to store the info
        ItemInfoNode newNode = new ItemInfoNode();
        // info for new node
        newNode.setInfo(new ItemInfo(name, rfidTag, initPosition, price));
        newNode.getInfo().setCurrent_location(initPosition);
        //List is empty so, new node is set as head and tail
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        ItemInfoNode currentNode = head;
        ItemInfoNode prevNode = null;
            //Traverse through linked list
            while (currentNode != null){
                int compareRfid = currentNode.getInfo().getRfid_tag_number().compareTo(newNode.getInfo().getRfid_tag_number());
               if(compareRfid >= 0){
                   newNode.setNext(currentNode);
                   if(prevNode == null){
                       head = newNode;
                   }else{
                       prevNode.setNext(newNode);
                   }
                   newNode.setPrev(prevNode);
                   currentNode.setPrev(newNode);
                   return;
               }
               prevNode = currentNode;
                currentNode = currentNode.getNext();
            }
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            }

    /**Removes all the nodes in the list that have the current location as "out"
     * and displays the items that have been removed
     *Complexity: O(n) - Iterates through the list and removes all the nodes with the location as out
      */

    public void removeAllPurchased(){
        System.out.println("                                   Original       Current ");
        System.out.println("\n  Item Name         RFID           Location       Location      Price" +
                "\n -----------    ------------     ------------    ----------    -------");
        // there is only one item that you are removing
        if(head == tail){
            head = null;
            tail = null;
        }

        ItemInfoNode current = head;
        ItemInfoNode prev = null;

    while(current != null){
        if(current.getInfo().getCurrent_location().equalsIgnoreCase("out")){
            ItemInfoNode next = current.getNext();
            removeNode(current);

            ItemInfo info = current.getInfo();
            System.out.println( info.toString());

            current = next;

        }else{
            prev = current;
            current = current.getNext();
        }
        }
    }


    /**helper method that removes a specific node from the list
     * @param removal_node
     * node that is removed
     * Complexity: O(n) - traverse the linked list to find the node that needs to be removed
     */
    public void removeNode(ItemInfoNode removal_node){
    if(removal_node == head){
        head = head.getNext();
    }else{
        ItemInfoNode prev = head;
        while(prev.getNext() != removal_node){
            prev = prev.getNext();
        }
        prev.setNext(removal_node.getNext());
    }
    if(removal_node == tail){
        tail = tail.getPrev();
    }
    }

    /**Moves an item with the given rfid tag number
     * @param rfidTag
     * rfid tag number of the item
     * @param source
     * location of the item
     * @param dest
     * destination of the item
     * @return
     * if the given rfid was found at the given source location
     * Complexity: O(n) - Iterates through the list to find the item with the given rfid and source location
     */
    public boolean moveItem(String rfidTag, String source, String
            dest){
        if(source.equals("out") || dest.equals("out")){
            throw new IllegalArgumentException("Invalid source or destination location.");
        }
        if(!dest.startsWith("c") && !dest.startsWith("s")){
            throw new IllegalArgumentException("Invalid destination location.");
        }

        ItemInfoNode current = head;
        while(current != null){
            if(current.getInfo().rfid_tag_number.equals(rfidTag) && current.getInfo().current_location.equals(source)) {
                current.getInfo().setCurrent_location(dest);
                return true;
            }
            current = current.getNext();
    }
        System.out.println("item not found at given source location.");
        return false;
    }

    /**Prints the list of all items currently in the list
     *Complexity: O(n) - Traverses through the list once and prints
     * all the items in a sorted order
     */
    public void printAll(){
    System.out.println("                                   Original       Current ");
    System.out.println("\n  Item Name         RFID           Location       Location      Price" +
                     "\n -----------    ------------     ------------    ----------    -------");
    ItemInfoNode current = head;
    while(current != null){
        ItemInfo info = current.getInfo();
        System.out.println( info.toString());
        current = current.getNext();
    }
    }

    /**Prints the items of the list by location
     * @param location
     * location of the items
     * Complexity: O(n) - Traverses the list once and prints items with
     * the specified current location
     */
    public void printByLocation(String location){
        System.out.println("                                   Original       Current ");
        System.out.println("\n  Item Name         RFID           Location       Location      Price" +
                "\n -----------    ------------     ------------    ----------    -------");

    ItemInfoNode current = head;
    while(current != null){
        ItemInfo info = current.getInfo();
        if(info.getCurrent_location().equals(location)){
            System.out.println(info);
        }
        current = current.getNext();
    }
    }

    /**Takes items in the store from the wrong shelf and places it back in its original location
     * Complexity: O(n) - Traverse through the list and moves items back into their original location
     */
    public void cleanStore(){
    ItemInfoNode current = head;

        System.out.println("                                   Original       Current ");
        System.out.println("\n  Item Name         RFID           Location       Location      Price" +
                "\n -----------    ------------     ------------    ----------    -------");

    while(current != null){
        ItemInfo info = current.getInfo();
        String currentLocation = info.getCurrent_location();
        String originalLocation = info.getOriginal_location();

        if(!currentLocation.equals("out") && !currentLocation.startsWith("c") && !currentLocation.equals(originalLocation)) {
           System.out.println(info.toString());
            if (!currentLocation.equals(originalLocation)) {
                info.setCurrent_location(originalLocation);
            }
        }
            current = current.getNext();
        }
    }


    /**Goes through the iven cart ad changes each item's location to "out"
     * @param cartNumber
     * cart number of the checkout items
     * @return
     * total cost of the items that were in the cart
     */
    public double checkOut(String cartNumber){
        double total = 0.0;
        ItemInfoNode current = head;

        System.out.println("                                   Original       Current ");
        System.out.println("\n  Item Name         RFID           Location       Location      Price" +
                "\n -----------    ------------     ------------    ----------    -------");


        while(current != null){
            ItemInfo info = current.getInfo();
            String currentLocation = info.getCurrent_location();

            if(currentLocation.equals(cartNumber)){
                info.setCurrent_location("out");
                total += info.getPrice();
                System.out.println(info.toString());
            }
            current = current.getNext();

        }
        System.out.println("The total cost for all the merchandise in cart " + cartNumber + " was " + total);
        return total;
    }

    /**Prints the items in the list by Rfid
     * @param rfidTag
     * rfid number of the items needed
     * Complexity: O(n) - Iterates through the linked list comparing the rfid number with the input
     */
    public void printByRfid(String rfidTag) {
    ItemInfoNode current = head;

        System.out.println("                                   Original       Current ");
        System.out.println("\n  Item Name         RFID           Location       Location      Price" +
                "\n -----------    ------------     ------------    ----------    -------");

    boolean found = false;
    while(current != null){
        if(current.getInfo().getRfid_tag_number().equalsIgnoreCase(rfidTag)){
            System.out.println(current.getInfo());
            current = current.getNext();
        }

    }
        if(!found){
            System.out.println("No items found with RFID tag number: " + rfidTag);
        }
    }
}
