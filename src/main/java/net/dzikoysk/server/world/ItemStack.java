package net.dzikoysk.server.world;

public class ItemStack {

    private final Material material;
    private final int damage;

    public ItemStack(int id) {
        this(id, 0);
    }

    public ItemStack(Material material) {
        this(material, 0);
    }

    public ItemStack(int id, int damage) {
        this(Material.fromId(id), damage);
    }

    public ItemStack(Material material, int damage) {
        this.material = material;
        this.damage = damage;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getDamage() {
        return this.damage;
    }

}
