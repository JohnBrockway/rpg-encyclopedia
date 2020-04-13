package com.johnbrockway.rpgencylopedia.data;

public interface Item {

    enum ItemType {
        CATEGORY,
        ENTRY,
        NOTE
    }

    int getId();

    int getIcon();

    String getTitle();

    ItemType getItemType();
}
