package net.sf.egonet.web.page;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import net.sf.egonet.model.Section;
import net.sf.egonet.model.Study;

public class EditStudyPage extends EgonetPage
{
	private final Study study;

	public EditStudyPage(Study study)
    {
		super("Study: "+study.getName());
		this.study = study;
		build();
	}

	private void build()
    {
		add(new FeedbackPanel("feedback"));
		Form form = new Form("sectionForm");

		final TextField sectionNameField = new TextField("sectionNameField", new Model(""));
		sectionNameField.setRequired(true);
		form.add(sectionNameField);

		final Model sectionSubjectModel = new Model(Section.Subject.EGO); // Could also leave this null.
		ArrayList<Section.Subject> subjectOptions = new ArrayList<Section.Subject>();
		for (Section.Subject subject : Section.Subject.values()) {
			subjectOptions.add(subject);
		}
		form.add(new DropDownChoice("sectionSubjectField",sectionSubjectModel,subjectOptions));

		form.add(
			new Button("createSection")
            {
				@Override
				public void onSubmit()
                {
					String name = (String) sectionNameField.getModelObject();
					Section.Subject subject = (Section.Subject) sectionSubjectModel.getObject();
					study.addSection(name,subject);
				}
			}
        );
		add(form);

		ListView sectionsView = new ListView("sections", study.getSections())
        {
			protected void populateItem(ListItem item) {
				final Section section = (Section) item.getModelObject();

				Link sectionLink = new Link("sectionLink")
                {
					public void onClick() {
						// setResponsePage(new EditSectionPage(section));
					}
				};

				sectionLink.add(new Label("sectionName", section.name));
				item.add(sectionLink);
				item.add(new Label("sectionSubject", section.subject.toString()));
			}
		};
		add(sectionsView);
	}
}