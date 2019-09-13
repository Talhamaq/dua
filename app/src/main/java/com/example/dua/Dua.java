package com.example.dua;

public class Dua {

    private String name;
    private String arabic;
    private String arabish;
    private String translation;
    private String reference;
    private String count;
    
    public Dua(String name, String arabic, String arabish, String translation, String reference, String count) {
        this.name = name;
        this.arabic = arabic;
        this.arabish = arabish;
        this.translation = translation;
        this.reference = reference;
        this.count = count;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getArabic() {
        return arabic;
    }
    
    public void setArabic(String arabic) {
        this.arabic = arabic;
    }
    
    public String getArabish() {
        return arabish;
    }
    
    public void setArabish(String arabish) {
        this.arabish = arabish;
    }
    
    public String getTranslation() {
        return translation;
    }
    
    public void setTranslation(String translation) {
        this.translation = translation;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public String getCount() {
        return count;
    }
    
    public void setCount(String count) {
        this.count = count;
    }
    
    
}
