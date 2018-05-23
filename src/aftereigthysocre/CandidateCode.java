package aftereigthysocre;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CandidateCode {
    Scanner scanner = new Scanner(System.in);
    static int count = 0,count1=0,temp1;
    static int totalCount=0;
    static int flag=0;
    static int temp = 0;
    static int noOfSalmons;

    static Set<Integer> indexesOfSalmons = new HashSet<Integer>()  ;

    static long[] lengthArray ;
    static long[] timeArray;


    public static void main(String[] args) {
        CandidateCode candidateCode = new CandidateCode();

        noOfSalmons = candidateCode.getNumberOfSalmons();
        lengthArray = new long[noOfSalmons];
        timeArray = new long[noOfSalmons];
        lengthArray = candidateCode.getLengthOfSalmons(noOfSalmons);
        timeArray = candidateCode.getTimeOfSalmons(noOfSalmons);

        if (noOfSalmons<3)
            System.out.print(noOfSalmons);
        else {

            indexesOfSalmons = candidateCode.howManyCanCatch();

            System.out.println(totalCount);
        }
    }

    private static int howManyCanCatchSecond(Set<Integer>indexesOfSalmons) {
        temp=0;
        count1=0;
        Set<Integer> tempIndexes = new HashSet<Integer>();

        for (int i=0 ;i< noOfSalmons;i++) {
            tempIndexes.clear();                            //TimeArray
            for (int j = 0; j < noOfSalmons; j++) {         //Fish
                if (0-timeArray[j]+timeArray[i]>=0&&0-timeArray[j]-lengthArray[j]+timeArray[i]<=0&&!indexesOfSalmons.contains(i)&&!indexesOfSalmons.contains(j)&&lengthArray[i]>0&&lengthArray[j]>0)
                {
                    tempIndexes.add(i);
                    tempIndexes.add(j);
                    temp = tempIndexes.size();
                }
            }
            if (temp>count1)
            {
                count1 = temp;

            }

        }
        return count1;
    }




    private Set<Integer>howManyCanCatch() {
        Set<Integer> tempIndexes = new HashSet<Integer>();

        for (int i=0 ;i< noOfSalmons;i++) {
            tempIndexes.clear();                            //TimeArray
            for (int j = 0; j < noOfSalmons; j++) {         //Fish
                if (0-timeArray[j]+timeArray[i]>=0&&0-timeArray[j]-lengthArray[j]+timeArray[i]<=0&&lengthArray[i]>0&&lengthArray[j]>0)
                {
                    tempIndexes.add(i);
                    tempIndexes.add(j);
                    temp = tempIndexes.size();
                }
            }
            count = temp;
            indexesOfSalmons.clear();
            indexesOfSalmons.addAll(tempIndexes);
            count1 = howManyCanCatchSecond(indexesOfSalmons);
            if (totalCount<count+count1)
            {
                totalCount = count+count1;


            }


        }
        return indexesOfSalmons;
    }

    private long[] getTimeOfSalmons(int noOfSalmons) {
        long[] temp = new long[noOfSalmons];
        for(int i = 0 ; i<noOfSalmons ; i++)
        {
            temp[i] = scanner.nextInt();
        }
        return temp;
    }

    private long[] getLengthOfSalmons(int noOfSalmons) {
        long[] temp = new long[noOfSalmons];
        for(int i = 0 ; i<noOfSalmons ; i++)
        {
            temp[i] = scanner.nextInt();
        }
        return temp;
    }

    private int getNumberOfSalmons() {
        return scanner.nextInt();
    }
}


