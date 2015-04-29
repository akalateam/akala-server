package org.akala.server.eatery.bean;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.Id;

public class AkalaEaterySet extends AkalaEateryItem {

  @Id
  private BigInteger id;
  private List<AkalaEateryItem> items;

  public List<AkalaEateryItem> getItems() {
    return items;
  }

  public void setItems(List<AkalaEateryItem> items) {
    this.items = items;
  }

}
