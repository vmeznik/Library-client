package library.utility;

import java.beans.PropertyChangeListener;

public interface UnnamedPropertyChangeSubject {
    // observer

    void addListener(PropertyChangeListener listener);

    void removeListener(PropertyChangeListener listener);
}
