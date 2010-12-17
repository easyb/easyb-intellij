package org.easyb.plugin.remoting;

import java.io.Serializable;

import org.easyb.BehaviorStep;
import org.easyb.domain.Behavior;
import org.easyb.listener.ExecutionListener;
import org.easyb.result.Result;

public class Event implements Serializable {
  private EventType type;

  private Object data;

  public Event(EventType type, BehaviorStep data) {
    this.type = type;
    this.data = new RemotableBehaviorStep(data);
  }

  public Event(EventType type, Result data) {
    this.type = type;
    this.data = data;
  }

  public Event(EventType type, Behavior data) {
    this.type = type;
    this.data = new RemotableBehavior(data);
  }

  public Event(EventType describeStep, String description) {
    this.type = describeStep;
    this.data = description;
  }

  public Event(EventType type) {
    this.type = type;
    this.data = null;
  }

  public void fire(RemotingExecutionListener receiver) {
    type.fire(receiver, data);
  }

  public EventType getType() {
    return type;
  }

  public String toString() {
    return "Event (type: " + type + ", data: " + data + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Event)) return false;

    Event event = (Event) o;

    if (data != null ? !data.equals(event.data) : event.data != null) return false;
    if (type != event.type) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (data != null ? data.hashCode() : 0);
    return result;
  }
}
