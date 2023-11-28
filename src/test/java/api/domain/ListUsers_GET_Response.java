package api.domain;

public class ListUsers_GET_Response {
    public ListUsers_GET_Response() {
    }
    /* JSON deserialization with Jackson:
    - creating an instance of UserData using empty constructor,
    - using the setter methods to set the values of the fields. */
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public Integer getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
