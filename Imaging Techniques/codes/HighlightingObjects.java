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
        Mat tools = Imgcodecs.imread("D:/very test/aaaa/tools.jpg");
        Imgproc.cvtColor(tools, tools, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(tools, tools, 150.0, 300.0, 3, true);
        Imgcodecs.imwrite("D:/very test/aaaa/tools-01.jpg", tools);
        Mat invertedTools = tools.clone();
        Core.bitwise_not(invertedTools, invertedTools);
        Imgcodecs.imwrite("D:/very test/aaaa/tools-02.jpg", invertedTools);
    }
}