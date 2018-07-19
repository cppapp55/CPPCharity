package cpp.app.xps.charity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Organization {

    private String id;
    private Long Phone;
    private String address;
    private String description;
    private String age;
    private String name;
    private Long zipcode;
    private String image;


    public Long getPhone() {
        return Phone;
    }

    public void setPhone(Long phone) {
        Phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Organization(String id, Long phone, String aAddress, String description, String age, String name, Long zip) {
        this.id = id;
        this.Phone = phone;
        this.address = aAddress;
        this.description = description;
        this.age = age;
        this.name = name;
        this.zipcode = zip;
    }

    public Organization() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

