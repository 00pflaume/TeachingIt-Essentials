package de.simonbrungs.teachingitessentials.sites;

import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;

public class AboutMe implements Listener<ContentCreateEvent> {

	@Override
	public void executeEvent(ContentCreateEvent pEvent) {
		if (pEvent.getUser().getCalledPath().equals("aboutme"))
			if (pEvent.getUser().getAccount() != null) {
				pEvent.setContent("You are logged in as: " + pEvent.getUser().getAccount().getUserName());
			} else {
				pEvent.setContent("You need to be logged in to do that. Login <a href='/login'>here</a>");
			}
	}

}
