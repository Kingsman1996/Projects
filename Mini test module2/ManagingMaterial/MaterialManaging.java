import java.time.LocalDate;
import java.util.*;

public class MaterialManaging {
    private List<Material> materials;
    private int materialAmount;

    public MaterialManaging() {
        materials = new ArrayList<>();
        materialAmount = 0;
    }

    public int getMaterialAmount() {
        return materialAmount;
    }

    public List<Material> getAllMaterials() {
        return materials;
    }

    public List<Material> getMaterialsByName(String name) {
        boolean found = false;
        List<Material> foundedItems = new ArrayList<>();
        for (Material item : materials) {
            if (item.getName().equals(name)) {
                foundedItems.add(item);
                found = true;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Material with name " + name + " not found");
        } else {
            return foundedItems;
        }
    }

    public List<Material> getMaterialsById(String id) {
        boolean found = false;
        List<Material> foundedItems = new ArrayList<>();
        for (Material item : materials) {
            if (item.getId().equals(id)) {
                foundedItems.add(item);
                found = true;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Material with id " + id + " not found");
        } else {
            return foundedItems;
        }
    }

    public void addCrispyFlour(String id, String name, int cost, LocalDate manufacturingDate, int quantity) {
        materials.add(new CrispyFlour(id, name, cost, manufacturingDate, quantity));
        materialAmount++;
    }

    public void addMeat(String id, String name, int cost, LocalDate manufacturingDate, double weight) {
        materials.add(new Meat(id, name, cost, manufacturingDate, weight));
        materialAmount++;
    }

    public void fixMaterialName(String oldName, String newName) {
        List<Material> fixingList = getMaterialsByName(oldName);
        if (!fixingList.isEmpty()) {
            for (Material item : fixingList) {
                item.setName(newName);
            }
        }
    }

    public void fixMaterialId(String oldId, String newId) {
        List<Material> fixingList = getMaterialsById(oldId);
        if (!fixingList.isEmpty()) {
            for (Material item : fixingList) {
                item.setId(newId);
            }
        }
    }

    public void removeMaterialByName(String name) {
        Iterator<Material> iterator = materials.iterator();
        while (iterator.hasNext()) {
            Material item = iterator.next();
            if (item.getName().equals(name)) {
                iterator.remove();
                materialAmount--;
            }
        }
    }

    public void removeMaterialById(String id) {
        Iterator<Material> iterator = materials.iterator();
        while (iterator.hasNext()) {
            Material item = iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
                materialAmount--;
            }
        }
    }

    public double sumMaterialBasicMoney() {
        double sum = 0;
        for (Material item : materials) {
            sum += item.getBasicMoney();
        }
        return sum;
    }

    public double sumMaterialRealMoney() {
        double sum = 0;
        for (Material item : materials) {
            if (item instanceof Discountable) {
                sum += ((Discountable) item).getRealMoney();
            }
        }
        return sum;
    }

    public void sortMaterialsByCost() {
        Collections.sort(materials);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Material item : materials) {
            sb.append(item.toString());
        }
        return sb.toString();
    }
}
