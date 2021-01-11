package com.example.pokemonlistapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    int id;
    String name;
    String type;
    String skill;
    String weakness;


    public Pokemon(int id, String name, String type, String skill, String weakness) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.skill = skill;
        this.weakness = weakness;
    }


    public Pokemon(Parcel in){
        this.id = id;
        this.name = name;
        this.type = type;
        this.skill = skill;
        this.weakness = weakness;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSkill() {
        return skill;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.skill);
        dest.writeString(this.weakness);
        dest.writeInt(this.id);
    }
}


