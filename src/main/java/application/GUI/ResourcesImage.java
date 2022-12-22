package application.GUI;

import javafx.scene.image.Image;

public class ResourcesImage {
    private Image Relax;
    private Image Sit;
    private Image Move;
    private Image LoveBar;
    private Image Sleep;
    private Image Interact;
    private Image Special;  // �øжȽ���������Ƥ������Գ��ִ˶���
    private Image MoveF;
    public Image getSpecial() {
        if (Special == null)
            Special = new Image(ResourcesImage.class.getResource("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Special.gif").toExternalForm());
        return Special;
    }

    public Image getRelax() {
        if (Relax == null) {
            System.out.println(Main.getPetName() + " \n" + Main.getPetSkin());
            Relax = new Image(ResourcesImage.class.getResource("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Relax.gif").toExternalForm());
        }
        return Relax;
    }

    public Image getSit() {
        if (Sit == null)
            Sit = new Image(ResourcesImage.class.getResource("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Sit.gif").toExternalForm());
        return Sit;
    }

    public Image getMove() {
        if (Move == null)
            Move = new Image(ResourcesImage.class.getResource("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Move.gif").toExternalForm());
        return Move;
    }

    public Image getSleep() {
        if (Sleep == null)
            Sleep = new Image(ResourcesImage.class.getResource("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Sleep.gif").toExternalForm());
        return Sleep;
    }

    public Image getInteract() {
        if (Interact == null)
            Interact = new Image(ResourcesImage.class.getResource("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/Interact.gif").toExternalForm());
        return Interact;
    }

    public Image getMoveF() {
        if (MoveF == null)
            MoveF = new Image(ResourcesImage.class.getResource("/" + Main.getPetName() + "/" + Main.getPetSkin() + "/MoveF.gif").toExternalForm());
        return MoveF;
    }

    public void switchSkin() {
        Relax = null;
        Sit = null;
        Move = null;
        MoveF = null;
        Sleep = null;
        Interact = null;
        Special = null;
        if (Main.getPetSkin().equals("default")) {
            Main.setPetSkin("winter");
        } else if (Main.getPetSkin().equals("winter")) {
            Main.setPetSkin("epoque");
        } else {
            Main.setPetSkin("default");
        }
        System.out.println(Main.getPetSkin());
        Main.getImageView().setImage(getImage("Relax"));
    }

    /*
     *ע������������Դ��δ���룬�л����﹦����ʱ����ʹ�ã���Ϊ���ø�biu��lxh��gif������
     */
    public void switchPet() {
        Relax = null;
        Sit = null;
        Move = null;
        MoveF = null;
        Sleep = null;
        Interact = null;
        Special = null;
        if (Main.getPetName().equals("002_amiya")) {
            Main.setPetName("102_texas");
        } else {
            Main.setPetName("002_amiya");
        }
        Main.setPetSkin("default");
        System.out.println(Main.getPetName());
        Main.getImageView().setImage(getImage("Relax"));
    }

    /**
     * catch��������󣬽���Relax��Interact�����ͼƬ��Դȫ���ͷŵ�
     * @param behavior
     * @return
     */
    public Image getImage(String behavior) {
        try {
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
        } catch (OutOfMemoryError e) {
            Sit = null;
            Move = null;
            MoveF = null;
            Sleep = null;
            Interact = null;
            Special = null;
            Relax = null;
            System.gc();    // ��һ��������У�������д�˱�֤���ٿ�ʼ����
            return getImage(behavior);
        }
        return Relax;
    }
}
