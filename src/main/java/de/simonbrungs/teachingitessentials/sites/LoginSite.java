package de.simonbrungs.teachingitessentials.sites;

import de.simonbrungs.teachingit.TeachingIt;
import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginSite implements Listener<ContentCreateEvent> {

	@Override
	public void executeEvent(ContentCreateEvent pEvent) {
		if (pEvent.getUser().getCalledPath().equalsIgnoreCase("login")
				|| pEvent.getUser().getCalledPath().equalsIgnoreCase("login.html")
				|| pEvent.getUser().getCalledPath().equalsIgnoreCase("login.php")) {
			pEvent.setTitle(TeachingIt.getInstance().getConfig().getProperty("SiteName") + " - Login");
			if (pEvent.getUser().getAccount() != null) {
				pEvent.setContent(
						"<h1 style='color: #c0392b;'>You are already logged in</h1>" + "You are already logged in.");
				return;
			}
			byte[] encoded = null;
			try {
				encoded = Files.readAllBytes(Paths.get("./plugins/Essentials/login.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			pEvent.setContent(new String(encoded, StandardCharsets.UTF_8));
		}
	}

}
