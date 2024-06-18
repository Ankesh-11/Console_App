
import java.util.ArrayList;
import java.util.List;
class User {

    private String name;
    private String email;
    private String mobile;
    private List<String> mobileNumbers;

    User(String name, String email, List<String> mobileNumbers) {
        this.name = name;
        this.email = email;
        this.mobileNumbers = new ArrayList<>(mobileNumbers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public List<String> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public int getMobileNumbersSize() {
        return mobileNumbers.size();
    }
    public void addMobileNumber(List<String> newList) {
        mobileNumbers.addAll(newList);
    }
    public void updateMobileNumbers(List<String> list)
    {
        String num = list.get(0);
        mobileNumbers.set(0,num);
    }

}
