package ir.bihooshkernel;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.CvType.*;

public class RetriveImgApp {

    public static Scalar RED = new Scalar(0, 0, 255); // Blue=0, Green=0, Red=255
    public static Scalar GREEN = new Scalar(0, 255, 0); // Blue=0, Green=255, Red=0
    public static Scalar BLUE = new Scalar(255, 0, 0); // Blue=255, Green=0, Red=0
    public static Scalar CYAN = new Scalar(255, 255, 0);
    public static Scalar MAGENTA = new Scalar(255, 0, 255);
    public static Scalar YELLOW = new Scalar(0, 255, 255);

    static {
        OpenCV.loadLocally();
    }

    static void setColors(Mat mat, boolean comp, int row) {
        for(int i = 0 ; i < 3 ; i ++) {
            Mat sub = mat.submat(row*100, row*100+100, i*100, i*100+100);
            if(comp) { // RGB
                if(i==0) sub.setTo(RED);
                if(i==1) sub.setTo(GREEN);
                if(i==2) sub.setTo(BLUE);
            } else { // CMY
                if(i==0) sub.setTo(CYAN);
                if(i==1) sub.setTo(MAGENTA);
                if(i==2) sub.setTo(YELLOW);
            }
        }
    }

    public static void main(String[] args){
        Mat mat = new Mat(200,300,CV_8UC3);
        setColors(mat, false, 1);
        setColors(mat, true, 0);
        Imgcodecs.imwrite("D:/very test/rgbcmy.jpg", mat);
    }
}