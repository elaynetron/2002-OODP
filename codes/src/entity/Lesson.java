package entity;

import java.util.Calendar;

public class Lesson {
    private String lessonType;
    private String lessonGrp;
    private int lessonDay;
    private String lessonVenue;
    private Calendar lessonStart;
    private Calendar lessonEnd;
    private String lessonRemarks;

    public Lesson(String lessonType, String lessonGrp, int lessonDay, String lessonVenue, Calendar lessonStart, Calendar lessonEnd, String lessonRemarks){
        this.lessonType = lessonType;
        this.lessonGrp = lessonGrp;
        this.lessonDay = lessonDay;
        this.lessonVenue = lessonVenue;
        this.lessonStart = lessonStart;
        this.lessonEnd = lessonEnd;
        this.lessonRemarks = lessonRemarks;
    }

    public String getLessonType(){
        return this.lessonType;
    }
    public void setLessonType(String lessonType){
        this.lessonType = lessonType;
    }

    public String getLessonGrp(){
        return this.lessonGrp;
    }
    public void setLessonGrp(String lessonGrp){
        this.lessonGrp = lessonGrp;
    }

    public int getLessonDay(){
        return this.lessonDay;
    }
    public void setLessonDay(int lessonDay){
        this.lessonDay = lessonDay;
    }

    public String getLessonVenue(){
        return this.lessonVenue;
    }
    public void setLessonVenue(String lessonVenue){
        this.lessonVenue = lessonVenue;
    }

    public Calendar getLessonStart(){
        return this.lessonStart;
    }
    public void setLessonStart(Calendar lessonStart){
        this.lessonStart = lessonStart;
    }

    public Calendar getLessonEnd(){
        return this.lessonEnd;
    }
    public void setLessonEnd(Calendar lessonEnd){
        this.lessonEnd = lessonEnd;
    }

    public String getLessonRemarks(){
        return this.lessonRemarks;
    }
    public void setLessonRemarks(String lessonRemarks){
        this.lessonRemarks = lessonRemarks;
    }
}
