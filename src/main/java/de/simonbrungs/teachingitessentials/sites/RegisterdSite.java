package de.simonbrungs.teachingitessentials.sites;

import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingit.api.users.AccountManager;

public class RegisterdSite implements Listener<ContentCreateEvent> {

	@Override
	public void executeEvent(ContentCreateEvent cce) {
		if (cce.getUser().getCalledPath().equals("registred")) {
			if (AccountManager.getInstance().getSessionKey(cce.getUser().getIPAddress(), "registerd") != null) {
				cce.setContent("<h1>You are now registred</h1>");
				AccountManager.getInstance().removeSessionKey(cce.getUser().getIPAddress(), "registerd");
			} else {
				cce.setContent("<h1>You could not be registerd</h1>");
			}
		}
	}

}
