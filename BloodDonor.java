public class BloodDonor
{
  public int idNum;
  public String lastName, firstName, type;
  public double donationTime;

  public BloodDonor()
  {

  }

  public void setValues(int idNumber, String nameLast, String nameFirst, String bloodType, double donateTime)
  {
    idNum = idNumber;
    lastName = nameLast;
    firstName = nameFirst;
    type = bloodType;
    donationTime = donateTime;
  }

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
