import java.io.File;


public class Project {
	Project children[];
	String name;

	LogEntry logs[];
	TodoEntry todos[];
	File files[];
	
	public Project[] getChildren() {
		return children;
	}
	public void setChildren(Project[] children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LogEntry[] getLogs() {
		return logs;
	}
	public void setLogs(LogEntry logs[]) {
		this.logs = logs;
	}
	public TodoEntry[] getTodos() {
		return todos;
	}
	public void setTodos(TodoEntry todos[]) {
		this.todos = todos;
	}
	public File[] getFiles() {
		return files;
	}
	public void setFiles(File files[]) {
		this.files = files;
	}
	public Project(Project[] children, String name, LogEntry logs[],
			TodoEntry todos[], File files[]) {
		this.children = children;
		this.name = name;
		this.logs = logs;
		this.todos = todos;
		this.files = files;
	}
	public Project( String name, LogEntry logs[],
			TodoEntry todos[]) {
		this.name = name;
		this.logs = logs;
		this.todos = todos;
	}
}
