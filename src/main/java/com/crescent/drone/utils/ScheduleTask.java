package com.crescent.drone.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crescent.drone.model.Drone;
import com.crescent.drone.repository.DroneRepository;

@Component
public class ScheduleTask {

	@Autowired
	DroneRepository droneRepository;

	List<Drone> drones;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");	
	Logger logger = Logger.getLogger("DroneTask");

	public ScheduleTask() {
		
		logger.setUseParentHandlers(false);
		FileHandler fh;

		try {

			// This block configure the logger with handler and formatter
			fh = new FileHandler("DroneTask.log", true);
			logger.addHandler(fh);
			fh.setFormatter(new Formatter() {

				@Override
				public String format(LogRecord record) {
					StringBuilder sb = new StringBuilder();
					sb.append(record.getLevel()).append(':');
					sb.append(record.getMessage()).append('\n');
					return sb.toString();
				}
			});

		} catch (SecurityException e) {
			e.printStackTrace();
			logger.warning(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning(e.getMessage());
		}

	}

	@Scheduled(fixedRate = 60000)
	public void getDroneBatteryLevels() {
		drones = droneRepository.findAll();
		logger.info("Battery Levels as of: "+dtf.format(LocalDateTime.now()));
		drones.forEach(d->logger.info("Serial Number:"+d.getSerialNumber()+" Battery:"+d.getBattery()+"%"));
		logger.info("---------------------------------");
		
	}
}
