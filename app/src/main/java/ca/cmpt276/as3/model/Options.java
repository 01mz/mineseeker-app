package ca.cmpt276.as3.model;

public class Options {
    private static Options instance = null;

    private Options(){ }

    public static Options getInstance(){
        if(instance == null){
            instance = new Options();
        }
        return instance;
    }
}
