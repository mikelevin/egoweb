package net.sf.egonet.model;

public class Section implements java.io.Serializable
{
	public static enum Subject {EGO,ALTER_PROMPT,ALTER,ALTER_PAIR};
	public static enum GroupBy {SUBJECT,QUESTION};
	public static enum AskingStyle {SINGLE,LIST};

	public String name;
	public Boolean active;
	public Subject subject;
	public GroupBy groupBy; // irrelevant if subject == EGO
	public AskingStyle askingStyle;
	public String introText; // no intro if this is null or empty
	public Integer maxAlters; // null if not ALTER_PROMPT or if no maximum

	public Section(String name, Subject subject) {
		this.name = name;
		this.active = true;
		this.subject = subject;
		this.groupBy = GroupBy.SUBJECT;
		this.askingStyle = AskingStyle.SINGLE;
		this.introText = "";
	}
}