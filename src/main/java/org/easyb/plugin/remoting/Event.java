package org.easyb.plugin.remoting;

import java.io.Serializable;

public class Event implements Serializable {
    private EventType type;
    private Object data;

    public Event(EventType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String toString() {
        return "Event (type: " + type + ", data: " + data + ")";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event event = (Event) o;

        return !(data != null ? !data.equals(event.data) : event.data != null) && type == event.type;
    }

    public int hashCode() {
        int result;
        result = (type != null ? type.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
