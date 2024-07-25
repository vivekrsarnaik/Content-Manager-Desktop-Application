package sdp_asignment2;
//command interface
import java.util.List;

public class DeleteOfCommand implements Command {
    private final List<ItemDescrip> itemList;
    private final ItemDescrip item;
    private final int index;

    public DeleteOfCommand(List<ItemDescrip> itemList, ItemDescrip item, int index) {
        this.itemList = itemList;
        this.item = item;
        this.index = index;
    }

    @Override
    public void undo() {
        itemList.add(index, item);
    }

    @Override
    public void redo() {
        itemList.remove(index);
    }

    
    public ItemDescrip getItem() {
        return item;
    }
}


