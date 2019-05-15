package com.scrypt.sfax.sfaxrepomanager.restservice.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

@Root(name = "FaxFolders")
public class GetFoldersResponse extends BaseResponse {
  @Root(name = "i")
  public static class FolderResponse {
    @Attribute
    String id;
    @Attribute
    String index;
    @Attribute(name = "aEmpty")
    String empty;
    @Attribute(name = "aRename")
    String rename;
    @Attribute(name = "aMove")
    String move;
    @Attribute(name = "aDelete")
    String delete;
    @Attribute
    String editable;
    @Text
    String name;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getIndex() {
      return index;
    }

    public void setIndex(String index) {
      this.index = index;
    }

    public String getEmpty() {
      return empty;
    }

    public void setEmpty(String empty) {
      this.empty = empty;
    }

    public String getRename() {
      return rename;
    }

    public void setRename(String rename) {
      this.rename = rename;
    }

    public String getMove() {
      return move;
    }

    public void setMove(String move) {
      this.move = move;
    }

    public String getDelete() {
      return delete;
    }

    public void setDelete(String delete) {
      this.delete = delete;
    }

    public String getEditable() {
      return editable;
    }

    public void setEditable(String editable) {
      this.editable = editable;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  @ElementList(inline = true)
  List<FolderResponse> folders;

  public List<FolderResponse> getFolders() {
    return folders;
  }

  public void setFolders(List<FolderResponse> folders) {
    this.folders = folders;
  }
}
