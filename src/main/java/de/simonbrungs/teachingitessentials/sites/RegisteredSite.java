package de.simonbrungs.teachingitessentials.sites;

import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingit.api.users.AccountManager;

public class RegisteredSite implements Listener<ContentCreateEvent> {

	@Override
	public void executeEvent(ContentCreateEvent cce) {
		if (cce.getUser().getCalledPath().equals("registered")) {
			if (AccountManager.getInstance().getSessionKey(cce.getUser().getIPAddress(), "registered") != null) {
				cce.setContent("<h1>You are now registered</h1>");
				AccountManager.getInstance().removeSessionKey(cce.getUser().getIPAddress(), "registered");
			} else {
				cce.setContent("<h1>You could not be registered</h1>");
			}
		}
	}

}
