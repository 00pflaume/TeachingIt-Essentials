package de.simonbrungs.teachingitessentials.sites;

import de.simonbrungs.teachingit.TeachingIt;
import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.Listener;

public class RegisterSite implements Listener<ContentCreateEvent> {

	@Override
	public void executeEvent(ContentCreateEvent pEvent) {
		if (pEvent.getUser().getCalledPath().equalsIgnoreCase("register")) {
			pEvent.setTitle("TeachingIt - Registration");
			if (pEvent.getUser().getAccount() != null) {
				pEvent.setContent("<h1 style='color: #c0392b'>You are already logged in.</h1>");
				return;
			}
			String content = "<h1>Register now</h1>";
			if (pEvent.getUser().getPostRequest("submited") != null) {
				if (check(pEvent.getUser().getPostRequest("username"))
						&& check(pEvent.getUser().getPostRequest("email"))
						&& check(pEvent.getUser().getPostRequest("password"))) {

				} else {
					content += "<h1 color='#c0392b'>There was an error. Please fill all requird feelds and make also"
							+ " shure your not using some special characters.</h1>";
				}
			}
			content += " <form action='http://" + TeachingIt.getInstance().getHomeDirectory()
					+ "registred' method='POST'>"
					+ "<input type='email' name='email' placeholder='Email' placeholder='Username' value='"
					+ nullToEmpty(pEvent.getUser().getPostRequest("email")) + "'>" + "<br>"
					+ "<input type='text' name='username' placeholder='Username' value='"
					+ nullToEmpty(pEvent.getUser().getPostRequest("username")) + "'>" + "<br>"
					+ "<input type='password' name='password' placeholder='Password' value='"
					+ nullToEmpty(pEvent.getUser().getPostRequest("password")) + "'>" + "<br>"
					+ "<input name='submited' type='submit'>" + "</form> ";
			pEvent.setContent(content);
		}
	}

	private boolean check(Object string) {
		if (string == null)
			return false;
		if (string instanceof String) {
			if ((((String) string).contains("/")) || (((String) string).contains("<"))
					|| (((String) string).contains("\\")) || ((String) string).contains("/"))
				return false;
			return true;
		}
		return false;
	}

	private String nullToEmpty(Object string) {
		if (!check(string))
			return "";
		return (String) string;
	}

}
