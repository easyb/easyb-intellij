package org.easyb.plugin.remoting;

import org.easyb.domain.Behavior;

import java.io.Serializable;

public class RemotableBehavior implements Serializable {
  private String phrase;

  public RemotableBehavior(Behavior data) {
    this.phrase = data.getPhrase();
  }

  public String getPhrase() {
    return phrase;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RemotableBehavior)) return false;

    RemotableBehavior that = (RemotableBehavior) o;

    if (phrase != null ? !phrase.equals(that.phrase) : that.phrase != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return phrase != null ? phrase.hashCode() : 0;
  }
}
