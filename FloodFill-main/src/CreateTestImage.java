import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CreateTestImage {
    public static void main(String[] args) {
        int width = 200;
        int height = 200;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                img.setRGB(x, y, Color.WHITE.getRGB());
            }
        }

        int lineThickness = 3;
        int x1 = width / 3;
        int x2 = 2 * width / 3;

        for (int dx = -lineThickness/2; dx <= lineThickness/2; dx++){
            for (int y = 0; y < height; y++){
                int px1 = x1 + dx;
                int px2 = x2 + dx;
                if (px1 >= 0 && px1 < width) {
                    img.setRGB(px1, y, Color.BLACK.getRGB());
                }
                if (px2 >= 0 && px2 < width) {
                    img.setRGB(px2, y, Color.BLACK.getRGB());
                }
            }
        }

        try {
            File output = new File("src/resources/parallel.png");
            ImageIO.write(img, "png", output);
            System.out.println("Imagem com linhas paralelas criada: src/resources/parallel.png");
        } catch (Exception e) {
            System.err.println("Erro ao salvar a imagem de linhas paralelas:");
            e.printStackTrace();
        }
    }
}
