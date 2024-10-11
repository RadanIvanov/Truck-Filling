public class ContainersMatch {

    private Container emptySpace;
    private Container pallet;
    private MatchType matchType;

    public ContainersMatch(Container emptySpace, Container pallet) {
        this.emptySpace = emptySpace;
        this.pallet = pallet;
    }

    public ContainersMatch(Container emptySpace, Container pallet, MatchType matchType) {
        this.emptySpace = emptySpace;
        this.pallet = pallet;
        this.matchType = getMatchType();
    }

    public Container getEmptySpace() {
        return emptySpace;
    }

    public Container getPallet() {
        return pallet;
    }

    // MatchComparator
    public MatchType getMatchType() {
        if(emptySpace.getWidth() == pallet.getWidth() && emptySpace.getLength() == pallet.getLength() && emptySpace.getHeight() == pallet.getHeight()){
            return MatchType.FUllMATCH;
        }else if((emptySpace.getWidth() >= pallet.getWidth() && emptySpace.getLength() == pallet.getLength() && emptySpace.getHeight() == pallet.getHeight()) ||
                (emptySpace.getWidth() == pallet.getWidth() && emptySpace.getLength() >= pallet.getLength() && emptySpace.getHeight() == pallet.getHeight()) ||
                (emptySpace.getWidth() == pallet.getWidth() && emptySpace.getLength() == pallet.getLength() && emptySpace.getHeight() >= pallet.getHeight())){
            return MatchType.TWOSIDEMATCH;
        }else if((emptySpace.getWidth() >= pallet.getWidth() && emptySpace.getLength() >= pallet.getLength() && emptySpace.getHeight() == pallet.getHeight()) ||
                (emptySpace.getWidth() == pallet.getWidth() && emptySpace.getLength() >= pallet.getLength() && emptySpace.getHeight() >= pallet.getHeight()) ||
                (emptySpace.getWidth() >= pallet.getWidth() && emptySpace.getLength() == pallet.getLength() && emptySpace.getHeight() >= pallet.getHeight())){
            return MatchType.ONESIDEMATCH;
        }else if(emptySpace.getWidth() >= pallet.getWidth() && emptySpace.getLength() >= pallet.getLength() && emptySpace.getHeight() >= pallet.getHeight()){
            return MatchType.EMPBIGGER;
        }else{
            return MatchType.EMPSMALLER;
        }
    }

    // MatchComparator
    public int surfaceDifference(){
        return (emptySpace.getWidth() * emptySpace.getLength() * emptySpace.getHeight()) - (pallet.getWidth() * pallet.getLength() * pallet.getHeight());
    }

    // MatchComparator
    public int perimeterDifference(){
        return (emptySpace.getWidth() + emptySpace.getLength() + emptySpace.getHeight()) * 2 - (pallet.getWidth() + pallet.getLength() + pallet.getHeight()) * 2;
    }

}
