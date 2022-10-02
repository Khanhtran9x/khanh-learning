package internet.com.dto.reponse_body;

public class ReponseBody {
    private String id;
    private String status;
    private Integer value;
    private String email;
    private String fullName;
    private String address;
    private String area1;
    private String area2;
    private Integer postalCode;
    private String countryCode;

    public ReponseBody () {
    }

    public ReponseBody (String id , String status , Integer value , String email , String fullName , String address , String area1 , String area2 , Integer postalCode , String countryCode) {
        this.id = id;
        this.status = status;
        this.value = value;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.area1 = area1;
        this.area2 = area2;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public Integer getValue () {
        return value;
    }

    public void setValue (Integer value) {
        this.value = value;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getFullName () {
        return fullName;
    }

    public void setFullName (String fullName) {
        this.fullName = fullName;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getArea1 () {
        return area1;
    }

    public void setArea1 (String area1) {
        this.area1 = area1;
    }

    public String getArea2 () {
        return area2;
    }

    public void setArea2 (String area2) {
        this.area2 = area2;
    }

    public Integer getPostalCode () {
        return postalCode;
    }

    public void setPostalCode (Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode () {
        return countryCode;
    }

    public void setCountryCode (String countryCode) {
        this.countryCode = countryCode;
    }
}
