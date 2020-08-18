package de.simonbrungs.teachingitessentials.sites;

import de.simonbrungs.teachingit.api.events.HeaderCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;
import de.simonbrungs.teachingitessentials.Essentials;

public class LoginHeader implements Listener<HeaderCreateEvent> {

	@Override
	public void executeEvent(HeaderCreateEvent pEvent) {
		pEvent.setHeader(pEvent.getHeader() + "<style>" + Essentials.getMain().getLoginCSS() + "</style>");
	}

}
