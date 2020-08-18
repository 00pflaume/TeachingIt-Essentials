package de.simonbrungs.teachingitessentials.commands;

import java.util.ArrayList;
import java.util.logging.Level;

import de.simonbrungs.teachingit.TeachingIt;
import de.simonbrungs.teachingit.api.Command;
import de.simonbrungs.teachingitessentials.Essentials;

public class Reload implements Command {

	@Override
	public void executeCommand(String arg0, ArrayList<String> arg1) {
		Essentials.getMain().reload();
		TeachingIt.getInstance().getLogger().log(Level.INFO, Essentials.PREFIX + "Essentials was reloaded.");
	}

}
