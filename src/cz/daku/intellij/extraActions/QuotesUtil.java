package cz.daku.intellij.extraActions;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;

public class QuotesUtil {

	final static char DQ = '"';
	final static char SQ = '\'';
	final static char BS = '\\';

	public static PsiElement findNearbyQuotedElement(PsiElement element) {
		for (int i = 0; i < 4; i++) {
			if (StringUtil.isQuotedString(element.getText())) {
				return element;
			}

			element = element.getParent();
			if (element == null) {
				break;
			}
		}

		return null;
	}

	public static boolean isSingelQuoted(String text) {
		return StringUtil.isQuotedString(text) && text.indexOf(SQ) == 0;
	}

	public static String convertSingleToDoubleQuoted(String text) {
		StringBuilder result = new StringBuilder();
		char[] chars = StringUtil.unquoteString(text).toCharArray();
		boolean escaping = false;

		for (int i = 0; i != chars.length; i++) {
			char ch = chars[i];

			if (ch == BS) {
				escaping = !escaping;
				if (i == chars.length - 1 || chars[i + 1] != SQ) {
					result.append(ch);
				}
			} else if (ch == DQ) {
				result.append(escaping ? "" + BS + BS + DQ : "" + BS + DQ);
				escaping = false;
			} else {
				result.append(ch);
				escaping = false;
			}
		}

		return DQ + result.toString() + DQ;
	}

	public static String convertDoubleToSingleQuoted(String text) {
		StringBuilder result = new StringBuilder();
		char[] chars = StringUtil.unquoteString(text).toCharArray();
		boolean escaping = false;

		for (int i = 0; i != chars.length; i++) {
			char ch = chars[i];

			if (ch == BS) {
				escaping = !escaping;
				if (i == chars.length - 1 || chars[i + 1] != DQ) {
					result.append(ch);
				}
			} else if (ch == SQ) {
				result.append(escaping ? SQ : "" + BS + SQ);
				escaping = false;
			} else {
				result.append(ch);
				escaping = false;
			}
		}

		return SQ + result.toString() + SQ;
	}

}
