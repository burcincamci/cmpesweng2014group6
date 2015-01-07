

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mysql.jdbc.log.Log;
public class User {
	private int UserId;
	private String Name;
	private String Email;
	private Date Birthdate;
	enum Gender {
		Male, Female, Unspecified
	};
	private Gender gender;
	private String Phone;
	private int ExperiencePoint;
	private int Level;
	private int IsDeleted;
	private int PicId;
	private String Bio;
	private String Password;
	private Date CreatedOn;
	private Date UpdatedOn;
	private DatabaseService db;
	public int followerNumber;
	public User()
	{
		
	}

	User getProfile() {
		db = new DatabaseService();
		User user = db.findUserByEmail(Email);
		//System.out.println(sql);
		return user;
	}
	
	Boolean updateProfile(String email) {
		db = new DatabaseService();
		String sql = "UPDATE Users SET ";
		sql += "Name = '" + Name + "'";
		sql += " , Phone = '" + Phone + "'";
		sql += " , Bio = '" + Bio + "'";
		sql += " WHERE Mail = '" + email + "'";
		boolean result = db.executeSql(sql);
		//System.out.println(sql);
		return result;
	}
	
	Boolean Register()
	{
		db = new DatabaseService();
		User temp = db.findUserByEmail(Email);
		if(temp.getEmail() != null)
			return false;
		else
		{
			String sql = "INSERT INTO Users (Name,Mail,IsDeleted,Password, CreationDate, LastUpdate) VALUES(";
			sql += "'" + Name + "',";
			sql += "'" + Email + "',";
			sql += "'" + IsDeleted + "',";
			sql += "'" + Password + "',";
			sql += "'" + CreatedOn + "',";
			sql += "'" + UpdatedOn + "')";
			db.executeSql(sql);
			return true;
		}
	}
	
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Gender getGender() {
		return this.gender;
	}
	
    public void setGender(String s) {
        if(s.toLowerCase().startsWith("m")){
                this.gender = Gender.Male;
        }else if(s.toLowerCase().startsWith("f")){
                this.gender = Gender.Female;
        }else{
                this.gender = Gender.Unspecified;
        }
    }
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getBirthdate() {
		return Birthdate;
	}

	public void setBirthdate(Date birthdate) {
		Birthdate = birthdate;
	}
	
    public void setBirthdate(String s) throws ParseException{
        Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(s);
        //System.out.print(date);
        this.setBirthdate(date);
}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public int getExperiencePoint() {
		return ExperiencePoint;
	}

	public void setExperiencePoint(int experiencePoint) {
		ExperiencePoint = experiencePoint;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		IsDeleted = isDeleted;
	}

	public int getPicId() {
		return PicId;
	}

	public void setPicId(int picId) {
		PicId = picId;
	}

	public String getBio() {
		return Bio;
	}

	public void setBio(String bio) {
		Bio = bio;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Date getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}

	public Date getUpdatedOn() {
		return UpdatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		UpdatedOn = updatedOn;
	}

	public DatabaseService getDb() {
		return db;
	}

	public void setDb(DatabaseService db) {
		this.db = db;
	}

	public int getFollowerNumber() {
		return followerNumber;
	}

	public void setFollowerNumber(int followerNumber) {
		this.followerNumber = followerNumber;
	}

}
