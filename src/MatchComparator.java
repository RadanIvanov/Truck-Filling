import java.util.Comparator;

public class MatchComparator implements Comparator<ContainersMatch> {

    public int compare(ContainersMatch m1, ContainersMatch m2){
        if(m1.getMatchType().getRank() - m2.getMatchType().getRank() == 0){
            if(m1.surfaceDifference() - m2.surfaceDifference() == 0){
                return m1.perimeterDifference() - m2.perimeterDifference();
            }else{
                return  m1.surfaceDifference() - m2.surfaceDifference();
            }
        }else{
            return m1.getMatchType().getRank() - m2.getMatchType().getRank();
        }
    }
}