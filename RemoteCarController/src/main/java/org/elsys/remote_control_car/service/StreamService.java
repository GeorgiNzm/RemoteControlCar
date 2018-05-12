package org.elsys.remote_control_car.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

@Component
public class StreamService {

    public StreamService() {}

    private static final String COMMAND = "raspivid -o - -t 0 -hf -w 600 -h 400 -fps 24 " +
                                          "'#standard{access=http,mux=ts,dst=:8164}' :demux=h264";

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StreamService.class);

    public void createVideoStream() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(StreamService.COMMAND);

        process.waitFor();
    }
}
