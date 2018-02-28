package org.elsys.remote_control_car.service;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StreamService {

    public StreamService() {}

    private static final String COMMAND = "raspivid -o - -t 0 -hf -w 600 -h 400 -fps 24 " +
                                          "|cvlc -vvv stream:///dev/stdin --sout " +
                                          "'#standard{access=http,mux=ts,dst=:8164}' :demux=h264";

    public void createVideoStream() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(StreamService.COMMAND);
        process.waitFor();
    }
}
