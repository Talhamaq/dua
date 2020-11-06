package com.example.dua;

import android.os.Parcel;
import android.os.Parcelable;

public class Dua implements Parcelable {

    private String name;
    private String arabic;
    private String arabish;
    private String translation;
    private String reference;
    private String count;
    private String category;
    private String number;
    private String favorite;
    
    public Dua(String name, String arabic, String arabish, String translation, String reference, String count, String category, String number,String favorite) {
        this.name = name;
        this.arabic = arabic;
        this.arabish = arabish;
        this.translation = translation;
        this.reference = reference;
        this.count = count;
        this.category=category;
        this.number=number;
        this.favorite=favorite;
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
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    public String getFavorite() {
        return favorite;
    }
    
    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
    
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(arabic);
        dest.writeString(arabish);
        dest.writeString(translation);
        dest.writeString(reference);
        dest.writeString(count);
        dest.writeString(category);
        dest.writeString(number);
        dest.writeString(favorite);
        
    }
    public static final Parcelable.Creator<Dua> CREATOR = new Parcelable.Creator<Dua>() {
        public Dua createFromParcel(Parcel in) {
            return new Dua(in);
        }
        
        public Dua[] newArray(int size) {
            return new Dua[size];
        }
    };
    
    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Dua(Parcel in) {
        name = in.readString();
        arabic = in.readString();
        arabish = in.readString();
        translation = in.readString();
        reference = in.readString();
        count = in.readString();
        category=in.readString();
        number=in.readString();
        favorite=in.readString();
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Dua) {
            Dua c = (Dua) o;
            if (this.getName().equals( c.getName()))
                return true;
        }
        return false;
        
    }
    }
