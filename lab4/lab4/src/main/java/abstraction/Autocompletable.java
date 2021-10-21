package abstraction;

import java.util.List;
import org.primefaces.event.SelectEvent;

public abstract class Autocompletable {
    String text;
    
    public abstract List<String> completeText(String query);
    public abstract void onItemSelect(SelectEvent<String> event);

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
