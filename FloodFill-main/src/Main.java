import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        System.out.println("Teste da lista ligada:");
        LinkedList<Integer> lnk = new LinkedList<>();
        lnk.addNode(1);
        lnk.addNode(2);
        lnk.addNode(5);
        Node<Integer> pointer = lnk.head.previous;
        System.out.println(pointer.value);
        System.out.println(pointer.next.value);
        System.out.println(pointer.previous.value);

        try {
            File input = new File("src/resources/parallel.png");
            BufferedImage img = ImageIO.read(input);

            FloodFill ff = new FloodFill(img);

            int redRGB = Color.RED.getRGB();

            int startX = img.getWidth() / 2;
            int startY = img.getHeight() / 2;

            ff.floodFillComPilha(startX, startY, redRGB);

            // ff.floodFillComFila(startX, startY, redRGB);

            File output = new File("src/resources/output.png");
            ImageIO.write(ff.getImage(), "png", output);

            System.out.println("Flood Fill concluído. Imagem salva como output.png");
        } catch (Exception e) {
            System.err.println("Erro ao carregar ou salvar a imagem:");
            e.printStackTrace();
        }
    }
}
