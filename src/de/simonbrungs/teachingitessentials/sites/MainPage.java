package de.simonbrungs.teachingitessentials.sites;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingitessentials.Essentials;

public class MainPage implements Listener<ContentCreateEvent> {

	@Override
	public void executeEvent(ContentCreateEvent pContentCreateEvent) {
		if (pContentCreateEvent.getUser().getCalledPath().equalsIgnoreCase("/")
				|| pContentCreateEvent.getUser().getCalledPath().equalsIgnoreCase("index.php")
				|| pContentCreateEvent.getUser().getCalledPath().equalsIgnoreCase("index.html")
				|| pContentCreateEvent.getUser().getCalledPath().equalsIgnoreCase("")) {
			pContentCreateEvent.setTitle("MainPage");
			String file = getFileContent();
			pContentCreateEvent.setContent(file);
		}
	}

	private String getFileContent() {
		Essentials.getMain().createHTMLPages();
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get("./plugins/Essentials/index.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(encoded, StandardCharsets.UTF_8);
	}

}
