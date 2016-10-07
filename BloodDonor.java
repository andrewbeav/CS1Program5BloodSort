import java.util.*;

public class BloodDonor
{
  // Creating public variables to hold values
  public int idNum;
  public String lastName, firstName, type;
  public double donationTime;

  // Constructor
  public BloodDonor(int idNumber, String nameLast, String nameFirst, String bloodType, double donateTime)
  {
    idNum = idNumber;
    lastName = nameLast;
    firstName = nameFirst;
    type = bloodType;
    donationTime = donateTime;
  }

  /*
    "Getter" methods to get the values
  */
  public int getIdNum()
  {
    return idNum;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getType()
  {
    return type;
  }

  public double getDonationTime()
  {
    return donationTime;
  }
}
