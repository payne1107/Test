package test.wd.com.demo.bean;

/**
 * Created by 92457 on 2018/5/9.
 */

public class Contacts {
    private String name;
    private String pinyin;

    public Contacts() {}

    public Contacts(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
