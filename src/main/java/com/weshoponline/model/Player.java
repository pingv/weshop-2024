package com.weshoponline.model;

//As of JDK 14, we can replace our repetitious data classes with records.
// Records are immutable data classes that require only the type and name of fields.
public record Player(Integer playerID, String name, Team team) {
}
