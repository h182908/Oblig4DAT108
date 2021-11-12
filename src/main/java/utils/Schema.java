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
		this.first_name = request.getParameter("fornavn");
		this.last_name = request.getParameter("etternavn");
		this.phone_number = request.getParameter("mobil");
		this.password = request.getParameter("passord");
		this.password2 = request.getParameter("passordRepetert");
		this.gender = request.getParameter("kjonn");
	}

	public Boolean isFirstNameValid() {
		Pattern p = Pattern.compile("\\s*\\p{Lu}\\p{Ll}+(\\s+\\p{Lu}\\p{Ll}+)*\\s*");
		Matcher m = p.matcher(this.first_name);
		return m.matches();
	}

	public Boolean isLastNameValid() {
		Pattern p = Pattern.compile("\\s*\\p{Lu}\\p{Ll}+\\s*");
		Matcher m = p.matcher(this.first_name);
		return m.matches();
	}

	public Boolean isPhoneNumberValid() {
		Pattern p = Pattern.compile("\s*\\+?\s*(\\d\s*){8}\s*");
		Matcher m = p.matcher(this.first_name);
		return true;
	}

	public Boolean isPasswordValid() {
		if(this.password.length() >= 8)
			return true;
		
		return false;
	}

	public Boolean isRepeatedPasswordValid() {
		return this.password.equals(this.password2);
	}
	
	public Boolean isGenderValid() {
		return true;
	}

	public Boolean isValidated() {
		return isFirstNameValid() && isLastNameValid() && isPhoneNumberValid() && isPasswordValid()
				&& isRepeatedPasswordValid() && isGenderValid();
	}
}
