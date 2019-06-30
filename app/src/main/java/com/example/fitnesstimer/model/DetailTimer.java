package com.example.fitnesstimer.model;

public class DetailTimer {

    private String time;
    private String name;
    private String preparation;
    private String job;
    private String rest;
    private String repeat;
    private String set;
    private String restSet;
    private String restAll;

    public DetailTimer() {
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public void setRestSet(String restSet) {
        this.restSet = restSet;
    }

    public void setRestAll(String restAll) {
        this.restAll = restAll;
    }
   /*
    public int textToInt(String txt){
        return Integer.valueOf(txt);
    }
*/

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getJob() {
        return job;
    }

    public String getRest() {
        return rest;
    }

    public String getRepeat() {
        return repeat;
    }

    public String getSet() {
        return set;
    }

    public String getRestSet() {
        return restSet;
    }

    public String getRestAll() {
        return restAll;
    }


    public int getTimeInt() {
        return Integer.parseInt(time);
    }

    public int getNameInt() {
        return Integer.parseInt(name);
    }

    public int getPreparationInt() {
        return Integer.parseInt(preparation);
    }

    public int getJobInt() {
        return Integer.parseInt(job);
    }

    public int getRestInt() {
        return Integer.parseInt(rest);
    }

    public int getRepeatInt() {
        return Integer.parseInt(repeat);
    }

    public int getSetInt() {
        return Integer.parseInt(set);
    }

    public int getRestSetInt() {
        return Integer.parseInt(restSet);
    }

    public int getRestAllInt() {
        return Integer.parseInt(restAll);
    }
}
