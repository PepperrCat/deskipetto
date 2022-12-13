package application;

import javafx.scene.image.Image;

public class ResourcesImage {
    private static Image Relax;
    private static Image Sit;
    private static Image Move;
    private static Image Sleep;
    private static Image Interact;
    private static Image Special;
    private static Image MoveF;

    public static Image getSpecial() {
        return Special;
    }

    public static Image getRelax() {
        if (Relax == null)
            Relax = new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Relax.gif"));
        return Relax;
    }

    public static Image getSit() {
        if (Sit == null)
            Sit = new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Sit.gif"));
        return Sit;
    }

    public static Image getMove() {
        if (Move == null)
            Move = new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Move.gif"));
        return Move;
    }

    public static Image getSleep() {
        if (Sleep == null)
            Sleep = new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Sleep.gif"));
        return Sleep;
    }

    public static Image getInteract() {
        if (Interact == null)
            Interact = new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Interact.gif"));
        return Interact;
    }

    public static Image getMoveF() {
        if (MoveF == null)
            MoveF = new Image(ResourcesImage.class.getResourceAsStream("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/MoveF.gif"));
        return MoveF;
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
            case "MoveF":
                return getMoveF();
        }
        return Relax;
    }
}
