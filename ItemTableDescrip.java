package sdp_asignment2;

import java.util.ArrayList;
import java.util.List;
//implementing command and list 
public class ItemTableDescrip {
    private final List<ItemDescrip> itemList;
    private final List<Command> undoList;
    private final List<Command> redoList;

    public ItemTableDescrip() {
        itemList = new ArrayList<>();
        undoList = new ArrayList<>();
        redoList = new ArrayList<>();
    }

    public void addItem(ItemDescrip item) {
        itemList.add(item);
        Command command = new AddOfCommand(itemList, item);
        undoList.add(command);
        redoList.clear();
    }

    public void deleteItem(int index) {
        ItemDescrip item = itemList.remove(index);
        Command command = new DeleteOfCommand(itemList, item, index);
        undoList.add(command);
        redoList.clear();
    }

    public List<ItemDescrip> getItemList() {
        return itemList;
    }

    public void undo() {
        if (!undoList.isEmpty()) {
            Command command = undoList.remove(undoList.size() - 1);
            command.undo();
            redoList.add(command);
        }
    }
    public boolean canUndo() {
        return !undoList.isEmpty();
    }
    
    public boolean canRedo() {
        return !redoList.isEmpty();
    }
    
    public void redo() {
        if (!redoList.isEmpty()) {
            Command command = redoList.remove(redoList.size() - 1);
            command.redo();
            undoList.add(command);
        }
    }
}
