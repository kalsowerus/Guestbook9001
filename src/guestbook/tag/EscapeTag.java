package guestbook.tag;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class EscapeTag extends SimpleTagSupport {
	private String value;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.write(getEscapedValue());
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEscapedValue() {
		return Jsoup.clean(getValue(), Whitelist.basic());
	}
}
