package org.easyb.plugin.event;

public class SpecResultEvent implements EasybEvent {
    private String message;

    public SpecResultEvent(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpecResultEvent event = (SpecResultEvent) o;

        return !(message != null ? !message.equals(event.message) : event.message != null);
    }

    public int hashCode() {
        return (message != null ? message.hashCode() : 0);
    }
}
