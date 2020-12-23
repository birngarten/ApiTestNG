package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.platform.commons.util.ToStringBuilder;

public class Employee {


    @JsonProperty("id")
    private String id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private String employeeSalary;
    @JsonProperty("employee_age")
    private String employeeAge;
    @JsonProperty("profile_image")
    private String profileImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public Employee() {
    }

    /**
     *
     * @param employeeName
     * @param employeeAge
     * @param id
     * @param profileImage
     * @param employeeSalary
     */
    public Employee(String id, String employeeName, String employeeSalary, String employeeAge, String profileImage) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeAge = employeeAge;
        this.profileImage = profileImage;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("employee_name")
    public String getEmployeeName() {
        return employeeName;
    }

    @JsonProperty("employee_name")
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @JsonProperty("employee_salary")
    public String getEmployeeSalary() {
        return employeeSalary;
    }

    @JsonProperty("employee_salary")
    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @JsonProperty("employee_age")
    public String getEmployeeAge() {
        return employeeAge;
    }

    @JsonProperty("employee_age")
    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    @JsonProperty("profile_image")
    public String getProfileImage() {
        return profileImage;
    }

    @JsonProperty("profile_image")
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("employeeName", employeeName).append("employeeSalary", employeeSalary).append("employeeAge", employeeAge).append("profileImage", profileImage).toString();
    }

}
