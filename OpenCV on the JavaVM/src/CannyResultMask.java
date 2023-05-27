package ir.bihooshkernel;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.CvType.*;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

public class RetriveImgApp {

    public static Scalar WHITE = new Scalar(255, 255, 255);

    static {
        OpenCV.loadLocally();
    }

    public static void main(String[] args){
        Mat kittens = imread("D:/very test/aaaa/three_black_kittens.jpg");
        Imgproc.cvtColor(kittens, kittens, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(kittens, kittens, 100.0, 300.0, 3, true);
        Core.bitwise_not(kittens, kittens);
        Mat target = new Mat(kittens.height(), kittens.width(), CV_8UC3, WHITE);
        Mat bg = imread("D:/very test/aaaa/light-blue-gradient.jpg");
        Imgproc.resize(bg, bg, target.size());
        bg.copyTo(target, kittens);
        imwrite("D:/very test/aaaa/kittens-04.jpg", target);
    }
}