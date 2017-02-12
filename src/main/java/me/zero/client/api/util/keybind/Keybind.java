package me.zero.client.api.util.keybind;

import java.util.ArrayList;
import java.util.List;

/**
 * A keybind that is used to create key hooks
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public abstract class Keybind {

    /**
     * The List of all Keybind Objects
     */
    private static List<Keybind> keybinds = new ArrayList<>();

    /**
     * The KeyCode for this Keybind
     */
    private int key;

    public Keybind(int key) {
        this.key = key;
        Keybind.keybinds.add(this);
    }

    /**
     * @since 1.0
     *
     * @param key The key code being set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @since 1.0
     *
     * @return The key code of this bind
     */
    public int getKey() {
        return this.key;
    }

    /**
     * Called when a key's state has been
     * switched from released to pressed
     *
     * @since 1.0
     */
    public abstract void onClick();

    /**
     * Called when a key is pressed
     *
     * @since 1.0
     */
    public abstract void onPress();

    /**
     * Claled when a key is released
     *
     * @since 1.0
     */
    public abstract void onRelease();

    /**
     * @since 1.0
     *
     * @return The list of all Keybind Objects
     */
    public static List<Keybind> getKeybinds() {
        return Keybind.keybinds;
    }

    /**
     * Keybind type
     */
    public enum Type {
        TOGGLE, HOLD
    }
}