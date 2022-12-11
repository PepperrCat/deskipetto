package application;

import javafx.scene.image.Image;

public class ResourcesImage {
    private static Image Relax;
    private static Image Sit;
    private static Image Move;
    private static Image Sleep;
    private static Image Interact;
    private static Image Special;

    public static Image getSpecial() {
        return Special;
    }

    public static Image getRelax() {
        if (Relax == null)
            return new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Relax.gif"));
        else return Relax;
    }

    public static Image getSit() {
        if (Sit == null)
            return new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Sit.gif"));
        else return Sit;
    }

    public static Image getMove() {
        if (Move == null)
            return new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Move.gif"));
        else return Move;
    }

    public static Image getSleep() {
        if (Sleep == null)
            return new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Sleep.gif"));
        else return Sleep;
    }

    public static Image getInteract() {
        if (Interact == null)
            return new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Interact.gif"));
        else return Interact;
    }

    public static Image getImage(String behavior) {
        switch (behavior) {
            case "Relax":
                return getRelax();
            case "Interact":
                return getInteract();
            case "Move":
                return getMove();
            case "Sit":
                return getSit();
            case "Sleep":
                return getSleep();
            case "Special":
                return getSpecial();
        }
        return Relax;
    }
}
