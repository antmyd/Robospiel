package model;

import java.io.Serializable;

/**
 * Repräsentiert ein Artefakt, das im Verlauf des Spiels gesammelt werden kann.
 * Ein Artefakt hat einen Namen und kann gefunden sowie installiert werden.
 * 
 * Beispielhafte Artefakte sind Navigationsmodul, Steuerungssystem und Energiekristall.
 * 
 * @author Anton Mydlach
 */

public class Artifact implements Serializable {

    private static final long serialVersionUID = 3743681153808233500L;

    public static final String NAVIGATION_MODULE = "Navigationsmodul";
    public static final String CONTROL_SYSTEM = "Steuerungssystem";
    public static final String ENERGY_CRYSTAL = "Energiekristall";

    private final String name;
    private boolean found;
    private boolean installed;

    public Artifact(String name) {
        this.name = name;
        this.found = false;
        this.installed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void install() {
        if (found) {
            this.installed = true;
        }
    }
}