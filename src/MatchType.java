public enum MatchType {
    FUllMATCH(1), TWOSIDEMATCH(2), ONESIDEMATCH(3), EMPBIGGER(4), EMPSMALLER(5);

    private int rank;

    MatchType(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
