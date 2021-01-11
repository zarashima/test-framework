package keywords.common;

import com.google.inject.Inject;
import keywords.mobile.MobileActions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.StreamSupport;

public class Logs {
	@Inject
	RemoteWebDriver driver;

	@Inject
	MobileActions mobileActions;

	LogEntries logEntries;

	@Inject
	public Logs(RemoteWebDriver driver, LogEntries logEntries) {
		this.driver = driver;
		this.logEntries = logEntries;
	}

	public void initLogEntries(String logType) {
		logEntries = driver.manage().logs().get(logType);
	}

	public void printLogcat(int limitSize) {
		StreamSupport.stream(logEntries.spliterator(), false)
				.limit(limitSize).forEach(System.out::println);
	}

	public void writeLogCat() throws FileNotFoundException {
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		File file = new File(reportDate + ".txt");
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.println(logEntries);
	}
}
