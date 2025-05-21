import java.io.*;
import java.util.*;

public class ObjectPlus implements Serializable{
    private static Map<Class, List> extent = new HashMap<>();
    public ObjectPlus(){
        addExtent();
    }


    public <T> void addExtent(){
        extent.computeIfAbsent(this.getClass(), k -> new ArrayList<>());
        extent.get(this.getClass()).add(this);
    }
    public static <T> List<T> getExtent(Class<T> p){
        extent.computeIfAbsent(p, c -> new ArrayList<>());
        return Collections.unmodifiableList(extent.get(p));

    }
    public void removeFromExtent(){
        extent.get(this.getClass()).remove(this);

    }

    public static void saveExtent() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("extent.ser"))){
            oos.writeObject(extent);
            oos.writeInt(Produkt.getMaxLiczbaSkładników());
        }

    }
    public static void loadExtent() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("extent.ser"))){
            extent = (Map<Class, List>) ois.readObject();
            Produkt.setMaxLiczbaSkładników(ois.readInt());
        }
    }
}

