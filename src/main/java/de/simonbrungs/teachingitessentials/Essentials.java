package de.simonbrungs.teachingitessentials;

import de.simonbrungs.teachingit.TeachingIt;
import de.simonbrungs.teachingit.api.Console;
import de.simonbrungs.teachingit.api.events.ContentCreateEvent;
import de.simonbrungs.teachingit.api.events.EventExecutor;
import de.simonbrungs.teachingit.api.events.HeaderCreateEvent;
import de.simonbrungs.teachingit.api.events.WebsiteCallEvent;
import de.simonbrungs.teachingit.api.plugin.Plugin;
import de.simonbrungs.teachingitessentials.commands.CreateAccount;
import de.simonbrungs.teachingitessentials.commands.Reload;
import de.simonbrungs.teachingitessentials.otherlisteners.Login;
import de.simonbrungs.teachingitessentials.otherlisteners.Registered;
import de.simonbrungs.teachingitessentials.otherlisteners.SignedInPage;
import de.simonbrungs.teachingitessentials.sites.*;

import java.io.*;
import java.util.logging.Level;

public class Essentials extends Plugin {
	public static final String PREFIX = "[Essentials] ";
	private static Essentials main = null;
	private String loginCSS;
	private String loginHTML;
	private String mainPage;

	public static Essentials getMain() {
		return main;
	}

	@Override
	public void onDisable() {
		System.out.println(PREFIX + " Disabling Essentials");
	}

	@Override
	public void onEnable() {
		main = this;
		Console.getInstance().registerCommand(new Reload(), "reload");
		Console.getInstance().registerCommand(new CreateAccount(), "createaccount");
		TeachingIt.getInstance().getLogger().log(Level.INFO, Essentials.PREFIX + "Enabling Essentials");
		reload();
		TeachingIt.getInstance().getEventExecutor().registerListener(new RegisterSite(), ContentCreateEvent.class,
				1000);
		TeachingIt.getInstance().getEventExecutor().registerListener(new Registered(), WebsiteCallEvent.class, 1000);
		TeachingIt.getInstance().getEventExecutor().registerListener(new RegisteredSite(), ContentCreateEvent.class,
				1000);
		TeachingIt.getInstance().getEventExecutor().registerListener(new MainPage(), ContentCreateEvent.class, 1000);
		TeachingIt.getInstance().getEventExecutor().registerListener(new LoginSite(), ContentCreateEvent.class, 1000);
		TeachingIt.getInstance().getEventExecutor().registerListener(new Login(), WebsiteCallEvent.class, 1000);
		TeachingIt.getInstance().getEventExecutor().registerListener(new LoginHeader(), HeaderCreateEvent.class, 1000);
		EventExecutor.getInstance().registerListener(new SignedInPage(), WebsiteCallEvent.class, 1000);
		EventExecutor.getInstance().registerListener(new AboutMe(), ContentCreateEvent.class, 1000);

	}

	public void reload() {
		createPages();
		loginCSS = read(new File("./plugins/Essentials/login.css"));
		loginHTML = read(new File("./plugins/Essentials/login.html"));
		mainPage = read(new File("./plugins/Essentials/index.html"));
	}

	public String getMainPage() {
		return mainPage;
	}

	public String getLoginHTML() {
		return loginHTML;
	}

	private String read(File pFile) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(pFile));
			StringBuilder content = new StringBuilder();
			while (br.ready())
				content.append(br.readLine());
			return content.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public String getLoginCSS() {
		return loginCSS;
	}

	public void createPages() {
		File folder = new File("./plugins/Essentials");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File("./plugins/Essentials/index.html");
		try {
			if (file.createNewFile()) {
				PrintWriter writer = new PrintWriter(file);
				writer.println("<h1>This is the main page</h1>");
				writer.println("Hello world. You can register your self <a href='/register'>here</a> "
						+ "or login <a href='/login'>here</a>. You can replace this text by "
						+ "editing the index.html in the " + "plugin folder of essentials");
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		file = new File("./plugins/Essentials/login.html");
		try {
			if (file.createNewFile()) {
				PrintWriter writer = new PrintWriter(file);
				writer.println(
						"<h1>Login</h1><form action='aboutme' method='POST' style='margin: 0 auto; width:250px;'>"
								+ "<div class='input'>" + "<input type='hidden' value='checked' name='check'>"
								+ "<input class='input' name='username' placeholder='username' pattern='.{1,128}' "
								+ "required='true' type='text'>" + "</div><div class='input'>"
								+ "<input class='input' name='password' placeholder='password' pattern='.{1,}' "
								+ "required='true' type='password'>" + "</div>" + "<div class='button-row'>"
								+ "<button  type='submit' class='input submit'>login</button></div></form>");
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		file = new File("./plugins/Essentials/login.css");
		try {
			if (file.createNewFile()) {
				PrintWriter writer = new PrintWriter(file);
				writer.println(".input{" + "text-align: center;" + "position: relative;" + "width: 100%;"
						+ "margin-bottom: 12px;" + "box-sizing: border-box;" + "padding:20px;" + ""
						+ "-webkit-appearance: none;-moz-appearance: " + "border-radius: 4px;"
						+ "textfield;background-color: #fff;"
						+ "border: 1px solid #8a9ba8;box-shadow: inset 0 1px 1px #f2f2f2;color: #424f59;"
						+ "outline: 0;position: relative;-webkit-transition-duration: 150ms;transition-duration: 150ms;"
						+ "-webkit-transition-property: border-color;transition-property: border-color; font-size: 18px;"
						+ "}" + ".submit{" + "color: #ecf0f1;background-color:#2980b9;" + "font-size: 24px;"
						+ "height: 63px;" + "padding: 15px 0;" + "}");
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
