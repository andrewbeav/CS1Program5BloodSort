import java.util.*;

class IdComparator implements Comparator<BloodDonor>
{
  public int compare(BloodDonor a, BloodDonor b)
  {
    Integer aId = a.idNum;
    Integer bId = b.idNum;
    int comp = aId.compareTo(bId);
    return comp;
  }
}

class LastNameComparator implements Comparator<BloodDonor>
{
  public int compare(BloodDonor a, BloodDonor b)
  {
    String aLastName = a.lastName;
    String bLastName = b.lastName;
    int comp = aLastName.compareTo(bLastName);
    return comp;
  }
}

class FirstNameComparator implements Comparator<BloodDonor>
{
  public int compare(BloodDonor a, BloodDonor b)
  {
    String aFirstName = a.firstName;
    String bFirstName = b.firstName;
    int comp = aFirstName.compareTo(bFirstName);
    return comp;
  }
}

class TypeComparator implements Comparator<BloodDonor>
{
  public int compare(BloodDonor a, BloodDonor b)
  {
    String aType = a.type;
    String bType = a.type;
    int comp = aType.compareTo(bType);
    return comp;
  }
}

class DonationTimeComparator implements Comparator<BloodDonor>
{
  public int compare(BloodDonor a, BloodDonor b)
  {
    double aDonationTime = a.donationTime;
    double bDonationTime = b.donationTime;
    int comp = aDonationTime.compareTo(bDonationTime);
    return comp;
  }
}

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
