package com.sakal.jsonnews.modul;

public class Sectionmodul {

    private String SectionNAme,Url;

    public Sectionmodul(String sectionNAme, String url) {
        SectionNAme = sectionNAme;
        Url = url;
    }

    public String getSectionNAme() {
        return SectionNAme;
    }

    public String getUrl() {
        return Url;
    }
}
