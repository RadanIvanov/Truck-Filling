import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        ArrayList<Container> pallets = new ArrayList<>();
        pallets.add(new Container(10, 10, 10));
        pallets.add(new Container(20, 20, 15));
        pallets.add(new Container(12, 15, 12));
        palletsVolume(pallets);

        /* doesn't remove well
        ArrayList<Container> palletsCopy = new ArrayList<>(pallets);
        pallets.addAll(rotateAroundX(palletsCopy));
        pallets.addAll(rotateAroundY(palletsCopy));
        pallets.addAll(rotateAroundZ(palletsCopy));
        System.out.println();
        for(Container c : pallets){
            c.print();
        }
        System.out.println();
        checkForSimilarity(pallets);
        for(Container c : pallets){
            c.print();
        }
        System.out.println();
*/
        ArrayList<Container> trucks = new ArrayList<>();
        trucks.add(new Container(45, 150, 30, 0, 0, 0));

        ArrayList<Container> palletPosition = new ArrayList<>();
        ArrayList<ContainersMatch> matches = new ArrayList<>();

        int id = 1;
        do{
            matches.clear();
            for(Container truck : trucks){
                for(Container pallet : pallets){
                    ContainersMatch truckPalletMatch = getMatch(truck, pallet);
                    if(truckPalletMatch.getMatchType().getRank() < MatchType.EMPSMALLER.getRank()){
                        matches.add(truckPalletMatch);
                    }
                }
            }

            if(matches.size() > 0){
                Collections.sort(matches, new MatchComparator());
                ContainersMatch firstMatch = matches.get(0);
                ArrayList<Container> splittedContainers = split(firstMatch.getEmptySpace(),firstMatch.getPallet());
                trucks.addAll(splittedContainers);
                palletPosition.add(new Container(firstMatch.getPallet().getWidth(), firstMatch.getPallet().getLength(), firstMatch.getPallet().getHeight(),
                        firstMatch.getEmptySpace().getX(), firstMatch.getEmptySpace().getY(), firstMatch.getEmptySpace().getZ(), id));
                trucks.remove(firstMatch.getEmptySpace());

                int count = 0;
                for(Container t : trucks){
                    if(firstMatch.getPallet().getWidth() > t.getWidth() || firstMatch.getPallet().getLength() > t.getLength()
                            || firstMatch.getPallet().getHeight() > t.getHeight()){
                        count++;
                    }
                    if(count == trucks.size()){
                        pallets.remove(firstMatch.getPallet());
                    }
                }
            }

            id++;
        }while(matches.size() > 0);

       /* for(Container c: palletPosition){
            c.print();
        }
        System.out.println("Empty scape(volume) unable to pack: " + emptySpaceCounter(trucks));*/

    }

    public static ContainersMatch getMatch(Container truck, Container pallet){
        MatchType matchType = new ContainersMatch(truck, pallet).getMatchType();
        ContainersMatch containersMatch = new ContainersMatch(truck, pallet, matchType);
        return containersMatch;
    }

    public static ArrayList<Container> split(Container A, Container B){
        ArrayList<Container> containersList = new ArrayList<>();

        if(A.getLength() > B.getLength()) {
            Container C = new Container(A.getWidth(), A.getLength() - B.getLength(), B.getHeight(), A.getX(), A.getY() + B.getLength(), A.getZ());
            containersList.add(C);
        }

        if(A.getWidth() > B.getWidth()) {
            Container D = new Container(A.getWidth() - B.getWidth(), B.getLength(), B.getHeight(), A.getX()+B.getWidth(), A.getY(), A.getZ());
            containersList.add(D);
        }

        if(A.getHeight() > B.getHeight()) {
            Container E = new Container(A.getWidth(), A.getLength(), A.getHeight() - B.getHeight(), A.getX(), A.getY(), A.getZ() + B.getHeight());
            containersList.add(E);
        }

        return containersList;
    }

    public static int emptySpaceCounter(ArrayList<Container> truck){
        int emptySpaceLeft = 0;
        for(Container t: truck){
            emptySpaceLeft += t.getWidth() * t.getLength() * t.getHeight();
        }
        return emptySpaceLeft;
    }

    public static void palletsVolume(ArrayList<Container> pallets){
        int volume, N = 1;
        for(Container p: pallets){
            volume =  p.getWidth() * p.getLength() * p.getHeight();
            System.out.printf("The volume of pallet %d is: %d\n", N, volume);
            N++;
        }
    }

    public static ArrayList<Container> rotateAroundZ(ArrayList<Container> arr){
        int temp;
        for(Container c : arr){
            temp = c.getLength();
            c.setLength(c.getWidth());
            c.setWidth(temp);
            c.print();
        }
        return arr;
    }

    public static ArrayList<Container> rotateAroundX(ArrayList<Container> arr){
        int temp;
        for(Container c : arr){
            temp = c.getLength();
            c.setLength(c.getHeight());
            c.setHeight(temp);
            c.print();
        }
        return arr;
    }

    public static ArrayList<Container> rotateAroundY(ArrayList<Container> arr){
        int temp;
        for(Container c : arr){
            temp = c.getWidth();
            c.setWidth(c.getHeight());
            c.setHeight(temp);
            c.print();
        }
        return arr;
    }

    public static void checkForSimilarity(ArrayList<Container> pallets){
        for(int i = 0; i < pallets.size() - 1; i++){
            for(int j = i+1; j < pallets.size() - i; j++){
                if(pallets.get(i).getLength() == pallets.get(j).getLength() && pallets.get(i).getWidth() == pallets.get(j).getWidth()
                   && pallets.get(i).getHeight() == pallets.get(j).getHeight()){
                    pallets.remove(pallets.get(j));
                }
            }
        }
    }

    /*public static Container combine(Container c1, Container c2){
        if((c1.getX() + c1.getTop() == c2.getX() && c1.getY() == c2.getY())
                || (c1.getX() == c2.getX() && c1.getY() + c1.getLeft() == c2.getY())){
            return new Container(c1.getTop() + c2.getTop(), c1.getLeft() + c2.getLeft(), c1.getX(), c1.getY());
        }
        return null;
    }*/
}
