package com.scrypt.sfax.data;

import com.scrypt.sfax.sfaxrepomanager.restservice.model.GetFoldersResponse;

public class Folder {
  private String id;
  private int index;
  private boolean empty;
  private boolean rename;
  private boolean move;
  private boolean delete;
  private boolean editable;
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public boolean isEmpty() {
    return empty;
  }

  public void setEmpty(boolean empty) {
    this.empty = empty;
  }

  public boolean isRename() {
    return rename;
  }

  public void setRename(boolean rename) {
    this.rename = rename;
  }

  public boolean isMove() {
    return move;
  }

  public void setMove(boolean move) {
    this.move = move;
  }

  public boolean isDelete() {
    return delete;
  }

  public void setDelete(boolean delete) {
    this.delete = delete;
  }

  public boolean isEditable() {
    return editable;
  }

  public void setEditable(boolean editable) {
    this.editable = editable;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }


  // convert from retrofit response
  public static Folder fromFoldersResponse(GetFoldersResponse.FolderResponse response) {
    Folder folder = new Folder();

    folder.setId(response.getId());
    folder.setIndex(Integer.parseInt(response.getIndex()));
    folder.setEmpty(response.getEmpty().equals("1"));
    folder.setMove(response.getMove().equals("1"));
    folder.setRename(response.getRename().equals("1"));
    folder.setDelete(response.getDelete().equals("1"));
    folder.setEditable(response.getEditable().equals("1"));
    folder.setName(response.getName());

    return folder;
  }

}
