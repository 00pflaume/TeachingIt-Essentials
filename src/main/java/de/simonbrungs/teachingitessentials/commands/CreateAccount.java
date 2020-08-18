package de.simonbrungs.teachingitessentials.commands;

import de.simonbrungs.teachingit.TeachingIt;
import de.simonbrungs.teachingit.api.Command;
import de.simonbrungs.teachingit.api.users.Account;
import de.simonbrungs.teachingit.api.users.AccountManager;

import java.util.ArrayList;
import java.util.logging.Level;

public class CreateAccount implements Command {

	@Override
	public void executeCommand(String arg0, ArrayList<String> arg1) {
		if (arg1.size() < 3) {
			TeachingIt.getInstance().getLogger().log(Level.INFO,
					"Execute this command like this: createaccount username pEmail pPassword");
			return;
		}
		Account acc = AccountManager.getInstance().createAccount(arg1.get(0), arg1.get(1),
				AccountManager.getInstance().encryptPassword(arg1.get(2)), (byte) 1);
		if (acc != null)
			TeachingIt.getInstance().getLogger().log(Level.INFO, "The Account was created");
		else
			TeachingIt.getInstance().getLogger().log(Level.INFO, "The Account was not created");
	}

}
