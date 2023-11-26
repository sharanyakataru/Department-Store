/**This is the item info node class which contains refernce to an item info object
 * @author sharanyakataru
 */
public class ItemInfoNode {
    private ItemInfo info;
    private ItemInfoNode next;
    private ItemInfoNode prev;

    /**constructor for item info node
     *
     */
    public ItemInfoNode(){
    this.info = null;
    this.next = null;
    this.prev = null;

}

    /**
     * @param info
     * info od the item
     */
    public void setInfo(ItemInfo info){
    this.info = info;

    }

    /**
     * @return
     * info of the product
     */
    public ItemInfo getInfo(){
        return info;
    }

    /**
     * @param next
     * next node reference
     */
    public void setNext(ItemInfoNode next){
    this.next = next;
    }

    /**
     * @param prev
     * prev node reference
     */
    public void setPrev(ItemInfoNode prev){
    this.prev = prev;
    }

    /**
     * @return
     * next node
     */
    public ItemInfoNode getNext(){
    return next;
    }

    /**
     * @return
     * prev node reference
     */

    public ItemInfoNode getPrev(){
    return prev;

    }
}
