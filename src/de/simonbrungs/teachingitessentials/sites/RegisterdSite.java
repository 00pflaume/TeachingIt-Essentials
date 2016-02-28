package de.simonbrungs.teachingitessentials.sites;

import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;

public class RegisterdSite implements Listener<ContentCreateEvent> {

	@Override
	public void executeEvent(ContentCreateEvent cce) {
		if (cce.getUser().getCalledPath().equals("registred")) {
			if (cce.getUser().getUserVar("").equals("true")) {
				cce.setContent("<h1>You are now registred</h1>");
			} else {
				cce.setContent("<h1>You could not be registerd</h1>");
			}
		}
	}

}
