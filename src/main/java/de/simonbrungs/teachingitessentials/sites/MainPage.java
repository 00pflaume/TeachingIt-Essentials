package de.simonbrungs.teachingitessentials.sites;

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
			pContentCreateEvent.setContent(Essentials.getMain().getMainPage());
		}
	}

}
