public class Container  {

    private int width;
    private int length;
    private int height;
    private int x;
    private int y;
    private int z;
    private int id;

    public Container(int width, int length, int height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public Container(int width, int length, int height, int x, int y, int z) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Container(int width, int length, int height, int x, int y, int z, int id) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }

    // sides
    public int getWidth() { return width; }

    public int getLength() { return length; }

    public int getHeight() { return height; }

    public void setWidth(int width) { this.width = width; }

    public void setLength(int length) { this.length = length; }

    public void setHeight(int height) { this.height = height; }

    public int getId() { return id; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() { return z; }

    public void print() {
        System.out.println("Container:  " + getId() + ", Sizes(w,l,h)(" + width + ", " + length + ", " + height + ")," +
                " Position(x,y,z): " + getX() + ", " + getY() + ", " + getZ());
    }
}
