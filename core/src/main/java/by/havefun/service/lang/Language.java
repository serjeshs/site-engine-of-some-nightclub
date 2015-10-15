package by.havefun.service.lang;

class Language{

    private String key;
    private String belarus;
    private String english;
    private String russian;


    public Language(String key, String belarus, String english, String russian) {
        super();
        this.key = key;
        this.belarus = belarus;
        this.english = english;
        this.setRussian(russian);
    }

    public String getBelarus() {
        return this.belarus;
    }

    public void setBelarus(String belarus) {
        this.belarus = belarus;
    }

    public String getEnglish() {
        return this.english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }



}