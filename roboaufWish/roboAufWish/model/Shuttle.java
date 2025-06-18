package model;

import java.io.Serializable;


/**
 * Die Klasse Shuttle modelliert ein Raumschiff mit einem Namen und drei benötigten Artefakten
 * das Shuttle muss vom Roboter repariert werden um die wichtigen Forschungsergebnisse zurückzubringen und das Spiel erfolgreich abzuschließen
 * @author Anton Mydlach
 */
public class Shuttle implements Serializable {

    private static final long serialVersionUID = 3230694418L;

    private final String name;

    private final Artifact[] artifacts = new Artifact[] {
            new Artifact(Artifact.NAVIGATION_MODULE),
            new Artifact(Artifact.CONTROL_SYSTEM),
            new Artifact(Artifact.ENERGY_CRYSTAL)
    };

    public Shuttle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Artifact[] getArtifacts() {
        return artifacts;
    }

    /**
     * Gibt ein Artefakt anhand des übergebenen Namens zurück.
     * Die Suche soll unabhängig von Groß- und Kleinschreibung erfolgen.
     * Wenn kein Artefakt mit dem Namen gefunden wird, soll null zurückgegeben werden.
     */
     public Artifact getArtifactByName(String artifactName) {
        for (Artifact artifact : artifacts) {
            if (artifact.getName().equalsIgnoreCase(artifactName)) {
                return artifact;
            }
        }
        return null; // Kein passendes Artefakt gefunden
    }

    /**
     * Prüft, ob alle Artefakte installiert sind.
     * Das Shuttle ist nur startbereit, wenn jedes Artefakt installiert wurde.
     * Gibt true zurück, wenn alle installiert sind, sonst false.
     */
    public boolean isReadyToLaunch() {
        int count = 0;
       for (Artifact artifact : artifacts) {
            if (artifact.isInstalled()) {
                count++;
            }
        }
        if (count == 3) {
            return true;
        }
        // TODO: Überprüfe für jedes Artefakt im Array 'artifacts', ob es installiert ist.
        // Sobald ein Artefakt nicht installiert ist, gib false zurück.
        // Falls alle installiert sind, gib true zurück.
        return false;
    }

     public int countArtifacts() {
        int count = 0;
       for (Artifact artifact : artifacts) {
            if (artifact.isInstalled()) {
                count++;
            }
        }
        return count;
    }

    /**
 * Gibt die Namen aller fehlenden (nicht installierten) Artefakte als Zeichenkette zurück.
 * Wenn alle Artefakte installiert sind, wird ein leerer String zurückgegeben.
 */
/**
 * Gibt die Namen aller fehlenden (nicht installierten) Artefakte als kommaseparierte Zeichenkette zurück.
 * Wenn alle Artefakte installiert sind, wird "keine" zurückgegeben.
 */
public String nameMissingArtifacts() {
    String result = "";
    
    for (int i = 0; i < artifacts.length; i++) {
        Artifact artifact = artifacts[i];
        if (!artifact.isInstalled()) {
            if (!result.equals("")) {
                result += ", ";
            }
            result += artifact.getName();
        }
    }

    if (result.equals("")) {
        return "keine";
    } else {
        return result;
    }
}


    }

   

