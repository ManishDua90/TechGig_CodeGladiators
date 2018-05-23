package monkeyjump;

import java.util.*;

public class CandidateCode {
    Scanner scanner = new Scanner(System.in);
    static int noOfTrees;
    static int[][] testCaseData, tempArray;
    static double[][] euclideanArray;
    static int [][] euclideanBinaryArray;
    static double capacity;
    static int flagRowCheck=1, countMonkeyLessThanJump=0;
    static Map<Integer,Integer> canJumpFromTo = new HashMap<Integer, Integer>();
    static int totalNoOfMonkeys =0;

    public static void main(String[] args) {

        CandidateCode candidateCode = new CandidateCode();
        noOfTrees = candidateCode.getNumberOfTrees();
        euclideanBinaryArray = new int[noOfTrees][noOfTrees];
        capacity = candidateCode.getCapacity();
        testCaseData = candidateCode.getTestCaseData();
        euclideanArray = candidateCode.getEuclideanDistancesBetweenTrees();
        countMonkeyLessThanJump = candidateCode.getCountOfMonkeysLessThanJump();
        tempArray = Arrays.copyOf(testCaseData, testCaseData.length);



        for (int i = 0; i < noOfTrees; i++) {
            totalNoOfMonkeys = totalNoOfMonkeys + testCaseData[i][2];
        }
        // System.out.println("***************   OUTPUT   *****************");
        // candidateCode.checkSameNumberOfMonkeysOnAllTrees();
        // candidateCode.checkEuclideanDistances();

        if (noOfTrees == 1) {
            System.out.println("0");
            System.exit(0);
        }

        if (noOfTrees>1)
        {

            for (int i =0 ; i< noOfTrees;i++ )
            {
                flagRowCheck=1;
                for (int j = 0 ; j < noOfTrees ; j++)
                {
                    if (euclideanBinaryArray[i][j]==1) {
                        flagRowCheck = 0;
                        break;
                    }

                }
                if (flagRowCheck==1)
                {
                    System.out.println("-1");
                    System.exit(0);
                }
            }
        }

        if (countMonkeyLessThanJump>1)
        {
            System.out.println("-1");
            System.exit(0);
        }
        else if (countMonkeyLessThanJump==1)
        {
            for (int i=0 ;i < noOfTrees ;i++)
            {
                if (testCaseData[i][2]>testCaseData[i][3])
                {
                    System.out.println(i);
                    System.exit(0);
                }
            }
        }
        else if (countMonkeyLessThanJump==0)
        {
            int flag=0;
            int allHavePaths=1;
            ArrayList<Integer> haveSomeMonkeys = new ArrayList<Integer>();
            ArrayList<Integer> indexes = new ArrayList<Integer>();
          //  int count =0;

            for (int i =0 ; i< noOfTrees;i++)
            {
                haveSomeMonkeys.clear();
                for (int l=i;l<noOfTrees;l++)
                    if (testCaseData[l][2]>0)
                        haveSomeMonkeys.add(l);
                if (totalNoOfMonkeys<=testCaseData[i][3])
                {
                   for (int k :haveSomeMonkeys)
                   {
                       if (euclideanBinaryArray[i][k]==0&&i!=k)
                       {
                           allHavePaths=0;
                       }
                   }
                   if (allHavePaths==1)
                    indexes.add(i);
                  //  count++;
                }
            }
            for (int i =0 ; i< noOfTrees;i++)
            {
                if (flag==1)
                    break;;
                for (int j =0 ; j< noOfTrees;j++)
                {
                    if (euclideanBinaryArray[i][j]==1&&!indexes.contains(j)) {
                        indexes.add(j);
                        flag=1;
                        break;
                    }
                }
            }
            Collections.sort(indexes);
            for (int k : indexes)
                System.out.print(k + " ");
        }











     /*   if (noOfTrees > 1)
            for (int i = 0; i < noOfTrees; i++) {
                if (testCaseData[i][3] < totalNoOfTrees)
                    flag = 0;
            }
        if (flag == 1) {
            for (int j = 0; j < noOfTrees; j++) {
                System.out.print(j + " ");
            }
        } else
            System.out.println("-1");

        for (int i = 0; i < noOfTrees; i++) {
            for (int j = 0; j < noOfTrees; j++) {

                System.out.print(euclideanBinaryArray[i][j] + "  ,  ");
                if (euclideanBinaryArray[i][j]==1)
                    canJumpFromTo.put(i,j);
            }
            System.out.println();
        }*/
    }

    private int getCountOfMonkeysLessThanJump() {
        int count=0;
        for (int i =0 ; i < noOfTrees ; i++)
            if (testCaseData[i][2]>testCaseData[i][3])
                count++;
        return count;
    }


    private int checkEuclideanDistances() {
        int temp = testCaseData[0][2];
        for(int i = 0; i < noOfTrees; i++)
        {
            for(int j =0 ; j < noOfTrees ; j++) {
                if(i!=j && euclideanArray[i][j]<capacity)
                    return 0;
            }
        }
        //System.out.println("Euclidean distances check : -1");
        System.out.println("-1");
        System.exit(0);
        return 1;
    }

    private double[][] getEuclideanDistancesBetweenTrees() {
        double [][] euclideanDistances = new double[noOfTrees][noOfTrees];
        for(int i = 0; i < noOfTrees; i++)
        {
            for(int j =0 ; j < noOfTrees ; j++) {
                euclideanDistances[i][j] = euclideanDistanceOfTwoTrees(testCaseData[i][0],testCaseData[i][1],testCaseData[j][0],testCaseData[j][1]);
                if (euclideanDistances[i][j]!=0 && euclideanDistances [i][j]<capacity)
                    euclideanBinaryArray[i][j]=1;
                else
                    euclideanBinaryArray[i][j]=0;
            }
        }
        return euclideanDistances;
    }

    private int checkSameNumberOfMonkeysOnAllTrees() {
        int temp = testCaseData[0][2];
        for(int i = 0; i < noOfTrees; i++)
        {
            if (testCaseData[i][2]<testCaseData[i][3])
                return 0;
        }
        //System.out.println("Same number of monkeys on all trees : -1");
        System.out.println("-1");
        System.exit(0);
        return 1;
    }

    private int[][] getTestCaseData() {
        int [][] testCaseData = new int[noOfTrees][4];
        for(int i = 0; i < noOfTrees; i++)
        {
            for(int j =0 ; j < 4 ; j++) {
                testCaseData[i][j] = scanner.nextInt();
            }
        }
        return testCaseData;
    }




    private int getNumberOfTrees() {
        return scanner.nextInt();
    }
    private double getCapacity() {
        return scanner.nextDouble();
    }

    private double euclideanDistanceOfTwoTrees(int xa, int ya, int xb, int yb)
    {
        return Math.sqrt((xb-xa)*(xb-xa)+(yb-ya)*(yb-ya));
    }
}