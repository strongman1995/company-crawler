package cn.thu.info.model;


public enum Status {
    SUCC(0),
    FAIL(1),
    NOT_FINISH(2),
    NOT_FOUND(3),
    MISS_SESSION(4),
    CONTENT_FINISHED(5);
    public int id;
    Status(int id){
        this.id = id;
    }

}
