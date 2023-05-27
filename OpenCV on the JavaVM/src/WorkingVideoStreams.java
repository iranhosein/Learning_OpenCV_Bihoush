package ir.bihooshkernel;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import static org.opencv.core.CvType.*;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

public class RetriveImgApp {

    static {
        OpenCV.loadLocally();
    }

    static void do_still_captures(int frames, int lapse, int camera_id) {

        VideoCapture camera = new VideoCapture(camera_id);
        camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 1280);
        camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 960);

        Mat frame = new Mat();

        for (int i=0; i<frames; i++){
            if (camera.read(frame)){
                String filename = "video/" + i + ".jpg";
                Imgcodecs.imwrite(filename, frame);
                try {Thread.sleep(lapse*1000);}
                catch (Exception e) {e.printStackTrace();}
            }
        }
        camera.release();
    }

    public static void main(String[] args){
        do_still_captures(5, 1, 0);
    }
}