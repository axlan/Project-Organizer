import java.util.Date;


public class LogEntry {
	Date date;
	String text;
	public LogEntry(Date date, String text) {
		this.date = date;
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
