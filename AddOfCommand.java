package sdp_asignment2;

import java.util.List;

public class AddOfCommand implements Command {
    private final List<ItemDescrip> itemList;
    private final ItemDescrip item;

    public AddOfCommand(List<ItemDescrip> itemList, ItemDescrip item) {
        this.itemList = itemList;
        this.item = item;
    }

    public void execute() {
        itemList.add(item);
    }

    @Override
    public void undo() {
        itemList.remove(item);
    }

    @Override
    public void redo() {
        execute();
    }
}
