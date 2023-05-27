package ir.bihooshkernel;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import static org.opencv.core.CvType.*;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

class MatPanel extends JPanel {
    public Mat mat;
    public void paint(Graphics g) {
        g.drawImage(RetriveImgApp.MatToBufferedImage(mat), 0, 0, this);
    }
}

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

    public static BufferedImage MatToBufferedImage(Mat frame){
        int type = 0;
        if (frame==null)
            return null;
        if (frame.channels() == 1){
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);
        return image;
    }

    public static void main(String[] args){
        ir.bihooshkernel.MatPanel t = new ir.bihooshkernel.MatPanel();
        JFrame frameO = new JFrame();
        frameO.getContentPane().add(t);
        frameO.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameO.setSize(1280, 720);
        frameO.setVisible(true);
        frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VideoCapture camera = new VideoCapture(0);
        camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 1280);
        camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 720);
        Mat frame = new Mat();

        while (true){
            if (camera.read(frame)){
                Imgproc.cvtColor(frame,frame, Imgproc.COLOR_RGB2GRAY);
                Mat target = new Mat();
                Imgproc.Canny(frame,target,100.0,150.0,3,true);
                t.mat=target;
                t.repaint();
            }
        }
}