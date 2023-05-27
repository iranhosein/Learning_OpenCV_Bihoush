package ir.bihooshkernel;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static org.opencv.core.CvType.*;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

public class RetriveImgApp {

    public static Scalar WHITE = new Scalar(255, 255, 255);

    static {
        OpenCV.loadLocally();
    }

    static List find_contours(Mat image, boolean onBlank) {
        Mat imageBW = new Mat();

        Imgproc.cvtColor(image, imageBW, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(imageBW, imageBW, 100.0, 300.0, 3, true);

        List contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(imageBW, contours, new Mat(),
                Imgproc.RETR_LIST,
                Imgproc.CHAIN_APPROX_SIMPLE);

        return contours;
    }

    static Mat draw_contours(Mat originalMat, List contours, int thickness) {
        Mat target = new Mat(originalMat.height(), originalMat.width(), CV_8UC3, WHITE);

        for (int i=0; i<contours.size(); i++)
            Imgproc.drawContours(target, contours, i, BLACK, thickness);

        return target;
    }

    static Mat mask_on_bg(Mat mask, String backgroundFilePath) {
        Mat target = new Mat(mask.height(), mask.width(), CV_8UC3, WHITE);
        Mat bg = imread(backgroundFilePath);
        Imgproc.resize(bg, bg, target.size());
        bg.copyTo(target, mask);
        return target;
    }

    public static void main(String[] args){
        Mat kittens = imread("D:/very test/aaaa/three_black_kittens.jpg");
        List contours = find_contours(kittens, true);

        Mat target = draw_contours(kittens, contours, 3);
        imwrite("D:/very test/aaaa/kittens-contours-3.jpg", target);

        Mat maskOnBg = mask_on_bg(target, "D:/very test/aaaa/light-blue-gradient.jpg");
        imwrite("D:/very test/aaaa/kitten-blue-bg.jpg", maskOnBg);
    }
}