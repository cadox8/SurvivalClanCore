package es.projectalpha.scc.utils.npc;

import javax.annotation.Nullable;

public class StringUtils {

	public static String normalizeString(@Nullable String str){
		if (str == null) {
			return null;
		}
		return str.replaceAll("[- ]*", "_").toLowerCase();
	}
}
