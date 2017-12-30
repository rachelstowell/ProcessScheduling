public class PCB {

    private int ID;
    private String state;
    private int arrivalTime;
    private int cpuBurstTime;
    private int priority;
    private int timeStart = 0;
    private int timeSwitch = 0;

    PCB(int pID, String pState, int pArrivalTime, int pCpuBurstTime, int pPriority){

        ID = pID;
        state = pState;
        arrivalTime = pArrivalTime;
        cpuBurstTime = pCpuBurstTime;
        priority = pPriority;

    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }

    public String getState(){
        return state;
    }

    public int getCpuBurstTime(){
        return cpuBurstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setCpuBurstTime(int cpuBurstTime) {
        this.cpuBurstTime = cpuBurstTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void decreaseCpuTime(){
        this.cpuBurstTime--;
    }

    public void setTimeStart(int t){
        this.timeStart = t;
    }

    public int getTimeStart(){
        return timeStart;
    }

    public void setTimeSwitch(int m){
        this.timeSwitch = m;
    }

    public int getTimeSwitch(){
        return timeSwitch;
    }

}

