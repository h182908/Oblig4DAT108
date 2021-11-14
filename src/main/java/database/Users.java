package database;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.Password;

@Entity
@Table(schema = "oblig4")
public class Users {
	
	@Id
	private String phone_number;
	
	private String first_name;
	private String last_name;
	private String gender;
	
	@Embedded
	private Password password;
	
	public Users(String first_name, String last_name, String phone_number, Password password, String gender) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_number = phone_number;
		this.password = password;
		this.gender = gender;
	}
	
	public Users() {}
	
	public synchronized String getFirst_name () {
		return first_name;
	}
	
	public synchronized String getLast_name() {
		return last_name;
	}
	
	public synchronized String getPhone_number() {
		return phone_number;
	}
	
	public synchronized String getGender() {
		return gender;
	}
	
	public synchronized Password getPassword() {
		return password;
	}
	
	public synchronized String toString() {
		return String.format("%s, %s, %s, %s, %s", first_name, last_name, phone_number, password, gender);
	}
}
