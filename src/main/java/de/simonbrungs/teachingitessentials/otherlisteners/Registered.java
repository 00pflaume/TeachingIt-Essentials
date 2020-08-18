package de.simonbrungs.teachingitessentials.otherlisteners;

import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingit.api.events.WebsiteCallEvent;
import de.simonbrungs.teachingit.api.users.Account;
import de.simonbrungs.teachingit.api.users.AccountManager;

public class Registered implements Listener<WebsiteCallEvent> {

	@Override
	public void executeEvent(WebsiteCallEvent pHCE) {
		if (pHCE.getUser().getCalledPath().equals("registered")) {
			if (pHCE.getUser().getAccount() == null) {
				String email = (String) pHCE.getUser().getPostRequest("email");
				if (email != null) {
					if (!email.startsWith("@") && email.contains("@") && !email.contains(" ")) {
						String password = (String) pHCE.getUser().getPostRequest("password");
						if (password != null) {
							String username = (String) pHCE.getUser().getPostRequest("username");
							if (username != null)
								if (!username.contains(" ") && !username.contains("@")) {
									Account acc = AccountManager.getInstance().createAccount(username, email,
											AccountManager.getInstance().encryptPassword(password), (byte) 1);
									if (acc != null)
										AccountManager.getInstance().setSessionKey(pHCE.getUser().getIPAddress(),
												"registered", "true");
								}
						}
					}
				}
			}
		}
	}

}
