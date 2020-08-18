package de.simonbrungs.teachingitessentials.otherlisteners;

import de.simonbrungs.teachingit.TeachingIt;
import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingit.api.events.WebsiteCallEvent;

public class Login implements Listener<WebsiteCallEvent> {

	@Override
	public void executeEvent(WebsiteCallEvent pEvent) {
		if (pEvent.getUser().getCalledPath().equals("loggingin")) {
			if (pEvent.getUser().getAccount() != null) {
				pEvent.getUser().setUserVar("de.simonbrungs.teachingitessentials.alreadyloggedin", "true");
				return;
			} else {
				String username = (String) pEvent.getUser().getPostRequest("username");
				String password = TeachingIt.getInstance().getAccountManager()
						.encryptPassword((String) pEvent.getUser().getPostRequest("password"));
				if (username != null && password != null) {
					pEvent.getUser()
							.setAccount(TeachingIt.getInstance().getAccountManager().loginUser(username, password));
					TeachingIt.getInstance().getAccountManager().setSessionKey(pEvent.getUser().getIPAddress(),
							"username", username);
					TeachingIt.getInstance().getAccountManager().setSessionKey(pEvent.getUser().getIPAddress(),
							"password", password);
				}
			}
		}
	}

}
