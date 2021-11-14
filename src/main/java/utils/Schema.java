package utils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Schema {
	public String first_name;
	public String last_name;
	public String phone_number;
	public String password;
	public String password2;
	public String gender;

	public Schema(HttpServletRequest request) {
		this.first_name = EscapeHTML.escape(request.getParameter("fornavn"));
		this.last_name = EscapeHTML.escape(request.getParameter("etternavn"));
		this.phone_number = EscapeHTML.escape(request.getParameter("mobil"));
		this.password = EscapeHTML.escape(request.getParameter("passord"));
		this.password2 = EscapeHTML.escape(request.getParameter("passordRepetert"));
		this.gender = EscapeHTML.escape(request.getParameter("kjonn"));
	}

	public synchronized Boolean isFirstNameValid() {
		Pattern p = Pattern.compile("\\s*\\p{Lu}\\p{Ll}+(\\s+\\p{Lu}\\p{Ll}+)*\\s*");
		Matcher m = p.matcher(this.first_name);
		return m.matches();
	}

	public synchronized Boolean isLastNameValid() {
		Pattern p = Pattern.compile("\\s*\\p{Lu}\\p{Ll}+\\s*");
		Matcher m = p.matcher(this.last_name);
		return m.matches();
	}

	public synchronized Boolean isPhoneNumberValid() {
		Pattern p = Pattern.compile("\\s*\\+?\\s*(\\d\\s*){8}\\s*");
		Matcher m = p.matcher(this.phone_number);
		return m.matches();
	}

	public synchronized Boolean isPasswordValid() {
		if(this.password.length() >= 8)
			return true;
		
		return false;
	}

	public synchronized Boolean isRepeatedPasswordValid() {
		return this.password.equals(this.password2);
	}
	
	public synchronized Boolean isGenderValid() {
		if(gender.equals("mann") || gender.equals("kvinne"))
			return true;
		return false;
	}

	public synchronized Boolean isValidated() {
		return isFirstNameValid() && isLastNameValid() && isPhoneNumberValid() && isPasswordValid()
				&& isRepeatedPasswordValid() && isGenderValid();
	}
}
