package kadai12;

public class NegativeImageFilter implements ImageFilter {
    @Override
    public void process(GrayImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int threshold = 128;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int l = img.getGray(i, j);
                l = 255 - l;
                if (l <= threshold) {
                    l = 0;
                } else {
                    l = 255;
                }
                img.setGray(i, j, l);
            }
        }
    }
}
