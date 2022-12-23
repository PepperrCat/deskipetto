package application.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @projectName: oopDZY
 * @package: application
 * @className: Draw
 * @date: 2022/12/17 12:17
 * 调用getLoveBar(int level, int cap) 方法足够了。
 *
 * 使用此方法获取一个BufferedImage对象，该对象就是需要的进度条
 */
public class Draw extends JFrame {
    private static final int sx = 0;//
    private static final int sy = 0;//
    private static final int w = 200;
    private static final int h = 30;
    static BufferedImage srcBuffer;
    static BufferedImage small;
    static BufferedImage targetImg ;

    static Font font = new Font("Dialog", Font.PLAIN, 9);

    static {
        try {
            srcBuffer = ImageIO.read(new File("src/main/resources/bixin.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//
//    Draw() {
//        Container p = getContentPane();
//
//        setBounds(200, 300, 1200, 100);
//
//        setVisible(true);
////        p.setBackground(Color.CYAN);
//        setLayout(null);
////        setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        {
//            try {
//                Thread.sleep(500);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        Graphics g = getGraphics();
//        DrawFrame(g, 1, 2);
//
//    }

//    public static void main(String[] args) throws IOException {
//        getLoveBar(9, 200);
//
//    }


//    public static void main(String[] args) {
//       try {
//           getLoveBar(1,3);
//       }catch (Exception ignored){
//
//       }
//    }
    /**
     * 使用此方法获取一个BufferedImage对象，该对象就是需要的进度条
     * <p>
     * level：当前进度
     * <p>
     * cap：进度上限
     */

    public static javafx.scene.image.Image getLoveBar(int level, int cap) throws Exception {
        BufferedImage small = loveAddNum(level);


        Graphics pen = targetImg.getGraphics();
        DrawFrame(sx + small.getWidth() / 2, sy, pen, level, cap);
        pen.drawImage(small, w * level / cap, h / 2 - small.getHeight() / 2, null);
        ByteArrayOutputStream outStream =new ByteArrayOutputStream();
//        File file=new File("Test.png");
//        FileWriter fileWriter=new FileWriter(file);
        ImageIO.write(targetImg,"png",outStream);
        byte[] b= outStream.toByteArray();
//        FileOutputStream out=new FileOutputStream("Test.png");


        ByteArrayInputStream inputStream=new ByteArrayInputStream(b);


//        fileWriter.write(outStream.toString());
//        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(file);
//
        javafx.scene.image.Image image=new javafx.scene.image.Image(inputStream);
        return image;
//        ImageIO.write(targetImg, "png", new File("testBar.png"));
    }

    public static BufferedImage loveAddNum(int level) throws IOException {
        BufferedImage imageScaled = zoomByScale(srcBuffer, 0.3);
        int w = imageScaled.getWidth();
        int h = imageScaled.getHeight();
        Graphics pen = imageScaled.getGraphics();

        pen.setFont(font);
//        System.out.println(font.getName());
        FontMetrics fm = pen.getFontMetrics();
        String s = String.valueOf(level);

        int fontsize = fm.stringWidth(s);
        int x = imageScaled.getWidth() / 2 - fontsize / 2;
        int y = (int) (imageScaled.getHeight() * 0.83);
        pen.drawString(s, x, y);
        return imageScaled;


    }

    public static void DrawFrame(int sx, int sy, Graphics g, int n, int cap) {
        try {
            g.setColor(Color.RED);
            g.drawRect(sx, sy, w - 1, h - 1);
            g.setColor(Color.pink);
            g.fillRect(sx, sy + 2, w * n / cap, h - 4);
        } catch (Exception e) {
        }
    }

    public static BufferedImage zoomByScale(BufferedImage img, double scale) throws IOException {
        if (small != null) return small;
        //与按比例缩放的不同只在于,不需要获取新的长和宽,其余相同.
        int width = (int) (img.getWidth() * scale);
        int height = (int) (img.getHeight() * scale);
        Image _img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(_img, 0, 0, null);
        small = image;
        targetImg = new BufferedImage(w + small.getWidth(), h, BufferedImage.TYPE_INT_ARGB);
        return image;
    }


}
