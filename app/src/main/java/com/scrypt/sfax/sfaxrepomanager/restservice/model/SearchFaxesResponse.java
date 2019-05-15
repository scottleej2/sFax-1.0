package com.scrypt.sfax.sfaxrepomanager.restservice.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "Faxes")
public class SearchFaxesResponse extends BaseResponse {
  @Root(name = "i")
  public static class FaxResponse {
    @Attribute
    String id;
    @Attribute
    String isNew;
    @Attribute
    String aReply;
    @Attribute
    String aForward;
    @Attribute
    String aDelete;
    @Attribute
    String aMove;
    @Attribute
    String aNotes;
    @Attribute
    String aInfo;
    @Attribute
    String aPrint;
    @Attribute
    String aView;
    @Element(name = "a", required = false)
    String displayName;
    @Element(name = "b", required = false)
    String date;
    @Element(name = "c", required = false)
    String faxNumber;
    @Element(name = "d", required = false)
    String extra;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getIsNew() {
      return isNew;
    }

    public void setIsNew(String isNew) {
      this.isNew = isNew;
    }

    public String getaReply() {
      return aReply;
    }

    public void setaReply(String aReply) {
      this.aReply = aReply;
    }

    public String getaForward() {
      return aForward;
    }

    public void setaForward(String aForward) {
      this.aForward = aForward;
    }

    public String getaDelete() {
      return aDelete;
    }

    public void setaDelete(String aDelete) {
      this.aDelete = aDelete;
    }

    public String getaMove() {
      return aMove;
    }

    public void setaMove(String aMove) {
      this.aMove = aMove;
    }

    public String getaNotes() {
      return aNotes;
    }

    public void setaNotes(String aNotes) {
      this.aNotes = aNotes;
    }

    public String getaInfo() {
      return aInfo;
    }

    public void setaInfo(String aInfo) {
      this.aInfo = aInfo;
    }

    public String getaPrint() {
      return aPrint;
    }

    public void setaPrint(String aPrint) {
      this.aPrint = aPrint;
    }

    public String getaView() {
      return aView;
    }

    public void setaView(String aView) {
      this.aView = aView;
    }

    public String getDisplayName() {
      return displayName;
    }

    public void setDisplayName(String displayName) {
      this.displayName = displayName;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getFaxNumber() {
      return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
      this.faxNumber = faxNumber;
    }

    public String getExtra() {
      return extra;
    }

    public void setExtra(String extra) {
      this.extra = extra;
    }
  }

  @Element
  String loadMore;
  @Element(required = false)
  String nextID;
  @ElementList(inline = true)
  List<FaxResponse> faxResponses;

  public String getLoadMore() {
    return loadMore;
  }

  public void setLoadMore(String loadMore) {
    this.loadMore = loadMore;
  }

  public String getNextID() {
    return nextID;
  }

  public void setNextID(String nextID) {
    this.nextID = nextID;
  }

  public List<FaxResponse> getFaxResponses() {
    return faxResponses;
  }

  public void setFaxResponses(List<FaxResponse> faxResponses) {
    this.faxResponses = faxResponses;
  }
}
