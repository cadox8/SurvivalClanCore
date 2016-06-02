package es.projectalpha.scc.utils;

import java.awt.Desktop;
import java.net.URI;

public class PC {

	public static void openURL(String ts) throws Exception{
		Desktop d = Desktop.getDesktop();

		d.browse(new URI(ts));
	}
}
