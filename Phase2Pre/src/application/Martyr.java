package application;

import java.time.LocalDate;

public class Martyr {
	private String name;
	private LocalDate dateOfDeath;
	private int age;
	private String district;
	private String location;
	private char gender;

	public Martyr() {
	}

	public Martyr(String name, LocalDate dateOfDeath, int age, String district, String location, char gender) {
		this.name = name;
		this.dateOfDeath = dateOfDeath;
		this.age = age;
		this.district = district;
		this.location = location;
		this.gender = gender;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	// toString() method to represent Martyr object as a string
	@Override
	public String toString() {
		return "Martyr{" + "name='" + name + '\'' + ", dateOfDeath=" + dateOfDeath + ", age=" + age + ", district='"
				+ district + '\'' + ", location='" + location + '\'' + ", gender=" + gender + '}';
	}

	public void update(String location, LocalDate dateOfDeath) {
		this.location = location;
		this.dateOfDeath = dateOfDeath;
	}

}
