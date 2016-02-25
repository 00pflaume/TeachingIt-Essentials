package de.simonbrungs.teachingitessentials.sites;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.simonbrungs.teachingit.api.events.HeaderCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingitessentials.Essentials;

public class LoginHeader implements Listener<HeaderCreateEvent> {

	@Override
	public void executeEvent(HeaderCreateEvent pEvent) {
		if (pEvent.getUser().getCalledPath().equals("login")) {
			Essentials.getMain().createHTMLPages();
			byte[] encoded = null;
			try {
				encoded = Files.readAllBytes(Paths.get("./plugins/Essentials/login.css"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			pEvent.setHeader(pEvent.getHeader() + "<style>" + new String(encoded, StandardCharsets.UTF_8) + "</style>");
		}

	}

}
