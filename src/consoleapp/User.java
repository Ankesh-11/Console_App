package consoleapp;

import java.util.ArrayList;
import java.util.List;
class User {

    private String name;
    private final String email;
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
    public String getMobileNumbers() {
        StringBuilder mobileNumber= new StringBuilder();
        mobileNumber.append(mobileNumbers.getFirst());
        for(int i=1;i< mobileNumbers.size();i++) {
             mobileNumber.append(", ").append(mobileNumbers.get(i));
        }
        return mobileNumber.toString();
    }
    public void addMobileNumber(List<String> newList) {
        mobileNumbers.addAll(newList);
    }
    public void updateMobileNumbers(List<String> list)
    {
        String num = list.getFirst();
        mobileNumbers.set(0,num);
    }

}
