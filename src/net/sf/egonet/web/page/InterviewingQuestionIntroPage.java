package net.sf.egonet.web.page;

import net.sf.egonet.model.Question;
import net.sf.egonet.persistence.Interviewing;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.link.Link;

public class InterviewingQuestionIntroPage extends InterviewingPage {

	private EgonetPage previousPage, nextPage;
	
	private String text;
	
	public InterviewingQuestionIntroPage(
			Long interviewId, String text, EgonetPage previous, EgonetPage next) 
	{
		super(interviewId);

        this.previousPage = previous;
        this.nextPage = next;
        this.text = text;
        
        add(new MultiLineLabel("text", text));

		add(new Link("backwardLink") {
			public void onClick() {
				if(previousPage != null) {
					setResponsePage(previousPage);
				}
			}
		});
		
		add(new Link("forwardLink") {
			public void onClick() {
				if(nextPage != null) {
					setResponsePage(nextPage);
				}
			}
		});
	}

	public String toString() {
		return text.length() < 23 ? text : text.substring(0, 20)+"...";
	}
	
	public static EgonetPage possiblyReplaceNextQuestionPageWithPreface(
			Long interviewId, EgonetPage proposedNextPage,
			Question earlyQuestion, Question lateQuestion,
			EgonetPage earlyPage, EgonetPage latePage)
	{
		String preface =
			Interviewing.getPrefaceBetweenQuestions(earlyQuestion, lateQuestion);
		return preface == null ? proposedNextPage : 
			new InterviewingQuestionIntroPage(interviewId,preface,earlyPage,latePage);
	}
}
