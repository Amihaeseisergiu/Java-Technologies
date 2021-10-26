package abstraction;

import java.util.List;
import org.primefaces.event.SelectEvent;

public abstract class Autocompletable<T> {
    T text;
    
    public abstract List<T> completeText(String query);
    public abstract void onItemSelect(SelectEvent<T> event);

    public T getText() {
        return text;
    }

    public void setText(T text) {
        this.text = text;
    }
}
