
import java.util.*;
import java.io.*;


public class Scheduler {

    private static int clockTime;
    private static boolean CPUoccupied = false;
    private static int indexOfcurrentPCB;


    public static void main(String[] args) throws IOException {
        Scanner inFile = new Scanner(new File("C:\\Users\\Rachel Stowell\\OperatingSystems\\src\\input.data"));
        int numberOfPCBS = inFile.nextInt();
        int premptiveState = inFile.nextInt();
        int quantum = inFile.nextInt();



        PCB[] pcbArray = new PCB[numberOfPCBS];

        for (int i = 0; i < numberOfPCBS; i++) {
            int arrivalTime = inFile.nextInt();
            int cpuBurstTime = inFile.nextInt();
            clockTime += cpuBurstTime;
            int priority = inFile.nextInt();

            pcbArray[i] = new PCB(i + 1, "", arrivalTime, cpuBurstTime, priority);


        }

        inFile.close();

        run(pcbArray);


    }


    public static void run(PCB[] pcbArray) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("output.data");
        for (int i = 0; i <= clockTime; i++) {
            for (int j = 0; j < pcbArray.length; j++) {
                if (pcbArray[j].getArrivalTime() == i) {//process arrives here
                    if (!CPUoccupied) {
                        pcbArray[j].setState("Running");
                        indexOfcurrentPCB = j;
                        CPUoccupied = true;
                        writer.print(i + " ");

                    } else { //// cpu is occupied
                        if(pcbArray[indexOfcurrentPCB].getCpuBurstTime() == 0){
                            pcbArray[indexOfcurrentPCB].setState("Terminated");
                            int max = 0;
                            int indexOfMax = -1;
                            for(int k=0; k<pcbArray.length; k++){
                                if(pcbArray[k].getState().equals("Waiting") && (pcbArray[k].getPriority() > max)){
                                    max = pcbArray[k].getPriority();
                                    indexOfMax = k;
                                }
                            }
                            indexOfcurrentPCB = indexOfMax;
                            pcbArray[indexOfcurrentPCB].setState("Running");

                        }
                        else if (pcbArray[indexOfcurrentPCB].getPriority() < pcbArray[j].getPriority()) {
                            writer.println(i + " " + (indexOfcurrentPCB+1));
                            pcbArray[indexOfcurrentPCB].setState("Waiting");
                            indexOfcurrentPCB = j;
                            pcbArray[indexOfcurrentPCB].setState("Running");
                            writer.print(i +" ");
                        }
                        else {
                            pcbArray[j].setState("Waiting");
                        }



                    }
                }
                else if(pcbArray[indexOfcurrentPCB].getCpuBurstTime() == 0){
                    System.out.println(pcbArray[indexOfcurrentPCB].getID() + " terminated at " + i);
                    pcbArray[indexOfcurrentPCB].setState("Terminated");
                    writer.println(i +" " + (indexOfcurrentPCB+1));
                    int max = 0;
                    int indexOfMax = 0;
                    for(int k=0; k<pcbArray.length; k++){
                        if(pcbArray[k].getState().equals("Waiting") && (pcbArray[k].getPriority() > max)){
                            max = pcbArray[k].getPriority();
                            indexOfMax = k;
                        }
                    }
                    indexOfcurrentPCB = indexOfMax;
                    pcbArray[indexOfcurrentPCB].setState("Running");
                    writer.print(i + " ");
                    System.out.println("context switch2 at " + i);

                }
            }
            pcbArray[indexOfcurrentPCB].setCpuBurstTime((pcbArray[indexOfcurrentPCB].getCpuBurstTime()-1));
        }
        writer.close();
    }
}

