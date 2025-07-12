package utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenRecorder {

    private static Process ffmpegProcess;
    private static String outputFile;

    public static void startRecording(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath = "videos";
        outputFile = folderPath + "/" + testName + "_" + timestamp + ".mp4";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String cmd = String.format("ffmpeg -y -video_size 1280x720 -f gdigrab -i desktop -framerate 15 -codec:v libx264 -preset ultrafast %s", outputFile);

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmd);
            builder.redirectErrorStream(true);
            ffmpegProcess = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void stopRecording() {
        if (ffmpegProcess != null) {
            try {
                OutputStream os = ffmpegProcess.getOutputStream();
                os.write('q');
                os.flush();
                os.close();

               ffmpegProcess.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
