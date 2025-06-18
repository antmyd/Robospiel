package model;

import challenges.Challenge;
import java.io.Serializable;

/**
 * Repräsentiert Verschlossene Räume, die sich nur durch das Absolvieren spezieller Challenges
 * öffnen lassen. Nur wenn er diese meistert, kann er Zugang zu den benötigten 
 * Ersatzteilen erhalten.
 * 
 * @author Paul Schottstädt
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 3947234501234725063L;

    private final int number;
    private final Challenge challenge;
    private final String artifact;
    private boolean open;

    /**
     * Konstruktor für einen Raum.
     * 
     * @param number Nummer des Raumes (eindeutig, unveränderlich)
     * @param challenge Herausforderung, die im Raum gelöst werden muss
     * @param artifact Artefakt, das im Raum gefunden werden kann
     */
    public Room(int number, Challenge challenge, String artifact) {
        this.number = number;
        this.challenge = challenge;
        this.artifact = artifact;
        this.open = false;
    }

    /**
     * Gibt die Nummer des Raumes zurück.
     * @return Nummer des Raumes
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gibt die im Raum befindliche Herausforderung zurück.
     * @return Challenge-Objekt
     */
    public Challenge getChallenge() {
        return challenge;
    }

    /**
     * Gibt das im Raum enthaltene Artefakt zurück.
     * @return Name des Artefakts
     */
    public String getArtifact() {
        return artifact;
    }

    /**
     * Prüft, ob der Raum geöffnet ist.
     * @return true, wenn der Raum geöffnet ist, sonst false
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Öffnet den Raum, sofern dieser noch nicht geöffnet wurde.
     * Ein erneutes Schließen ist nicht vorgesehen.
     * @param open Soll der Raum geöffnet werden?
     */
    public void setOpen(boolean open) {
        if (!this.open && open) {
            this.open = true;
        }
        // Falls bereits geöffnet, keine Änderung
    }
}
