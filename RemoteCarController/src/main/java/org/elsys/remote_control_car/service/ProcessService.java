package org.elsys.remote_control_car.service;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

@Component
public class ProcessService {

	public ProcessService() {
	}

	public int executeCommand(String command) throws Exception {
		int pid = 0;
		Process proc = Runtime.getRuntime().exec(command);
		// proc.waitFor();
		Field f = proc.getClass().getDeclaredField("pid");
		f.setAccessible(true);
		pid = (int) f.get(proc);
		return pid;
	}

}
