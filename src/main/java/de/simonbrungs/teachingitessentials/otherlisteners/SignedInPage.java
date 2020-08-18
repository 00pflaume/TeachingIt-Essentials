package de.simonbrungs.teachingitessentials.otherlisteners;

import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingit.api.events.WebsiteCallEvent;
import de.simonbrungs.teachingit.api.users.AccountManager;

public class SignedInPage implements Listener<WebsiteCallEvent> {

	@Override
	public void executeEvent(WebsiteCallEvent pEvent) {
		if (pEvent.getUser().getAccount() == null)
			if (pEvent.getUser().getPostRequest("username") != null
					&& pEvent.getUser().getPostRequest("password") != null) {
				pEvent.getUser()
						.setAccount(AccountManager.getInstance().loginUser(
								(String) pEvent.getUser().getPostRequest("username"), AccountManager.getInstance()
										.encryptPassword((String) pEvent.getUser().getPostRequest("password"))));
				if (pEvent.getUser().getAccount() != null) {
					AccountManager.getInstance().setSessionKey(pEvent.getUser().getIPAddress(), "username",
							(String) pEvent.getUser().getPostRequest("username"));
				}
			}
	}

}
