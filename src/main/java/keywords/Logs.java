package keywords;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.logging.LogEntries;

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
	AppiumDriver driver;

	@Inject
	MobileActions mobileActions;

	final LogEntries logEntries;

	@Inject
	public Logs(AppiumDriver driver) {
		this.driver = driver;
		logEntries = driver.manage().logs().get("logcat");
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
