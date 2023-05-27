package ir.bihooshkernel;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.CvType.*;

public class RetriveImgApp {

    static {
        OpenCV.loadLocally();
    }

    public static void main(String[] args){
        int width = 400;
        int height = 400;
        Mat mat = new Mat(height, width, CV_8UC3);
        Mat top = mat.submat(0, height/2,0, width);
        Mat bottom = mat.submat(height/2, height, 0, width);
        Mat small = Imgcodecs.imread("D:/very test/aaaa/kitten.jpg");
        Imgproc.resize(small, small, top.size());
        small.copyTo(top);
        small.copyTo(bottom);
        Imgcodecs.imwrite("D:/very test/aaaa/kii.jpg", mat);
    }
}