package es.projectalpha.scc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class AirDrop {

	public static ArrayList<Player> cSmall = new ArrayList<Player>();
	public static ArrayList<Player> cMedium = new ArrayList<Player>();
	public static ArrayList<Player> cBig = new ArrayList<Player>();
	public static HashMap<Location, Long> lSmall = new HashMap<Location, Long>();
	public static HashMap<Location, Long> lMedium = new HashMap<Location, Long>();
	public static HashMap<Location, Long> lBig = new HashMap<Location, Long>();

	public static long getCrateKey(){
		Random r = new Random();

		long n = r.nextLong();

		return n;
	}

	public static void spawnFirework(Location l){

		Firework fw = l.getWorld().spawn(l, Firework.class);
		FireworkMeta fwm = fw.getFireworkMeta();
		fw.playEffect(EntityEffect.FIREWORK_EXPLODE);
		fwm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(Color.FUCHSIA).withColor(Color.ORANGE).withColor(Color.WHITE).trail(false).flicker(false).withFade(Color.NAVY).build());
		fwm.setPower(0);
		fw.setFireworkMeta(fwm);

	}

	public static ArrayList<ItemStack> getItems(int type, boolean lucky, boolean unlucky, int lvlLucky, int lvlUnlucky){
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		Random r = new Random();
		int add = 0;

		items.clear();

		if (lucky) {
			add += lvlLucky;
		}
		if (unlucky) {
			add -= lvlUnlucky;
		}

		switch (type) {
		case 1:
			add += r.nextInt(2);
			if (add <= -1) {
				add = 0;
			}
			for (int x = 0; x < 1 + add; x++) {
				items.add(getItemsSmall());
			}
			return items;
		case 2:
			add += r.nextInt(3);
			if (add <= -1) {
				add = 0;
			}
			for (int x = 0; x < 3 + add; x++) {
				items.add(getItemsMedium());
			}
			return items;
		case 3:
			add += r.nextInt(4);
			if (add <= -1) {
				add = 0;
			}
			for (int x = 0; x < 5 + add; x++) {
				items.add(getItemsBig());
			}
			return items;
		}
		items.add(new ItemStack(Material.AIR));
		return items;
	}

	private static ItemStack getItemsSmall(){
		Random r = new Random();

		ItemStack i = new ItemStack(Material.DIRT, r.nextInt(32) + 1);
		ItemStack i2 = new ItemStack(Material.STONE, r.nextInt(32) + 1);
		ItemStack i3 = new ItemStack(Material.STONE_SWORD);
		ItemStack i4 = new ItemStack(Material.SAND, r.nextInt(32) + 1);
		ItemStack i5 = new ItemStack(Material.LOG, r.nextInt(14) + 1);
		ItemStack i6 = new ItemStack(Material.STONE_HOE);
		ItemStack i7 = new ItemStack(Material.DIAMOND, r.nextInt(2) + 1);
		ItemStack i8 = new ItemStack(Material.WOOD_DOOR, r.nextInt(3) + 1);
		ItemStack i9 = new ItemStack(Material.WOOD_SWORD);
		ItemStack i10 = new ItemStack(Material.YELLOW_FLOWER, r.nextInt(32) + 1);
		ItemStack i11 = new ItemStack(Material.APPLE, r.nextInt(5) + 1);
		ItemStack i12 = new ItemStack(Material.BEETROOT_SOUP, r.nextInt(2) + 1);

		ItemStack[] items = { i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12 };

		return items[r.nextInt(items.length)];
	}

	private static ItemStack getItemsMedium(){
		Random r = new Random();

		ItemStack i = new ItemStack(Material.DIRT, r.nextInt(32) + 1);
		ItemStack i7 = new ItemStack(Material.DIAMOND, r.nextInt(2) + 1);
		ItemStack i8 = new ItemStack(Material.WOOD_DOOR, r.nextInt(3) + 1);
		ItemStack i13 = new ItemStack(Material.BLAZE_ROD, r.nextInt(3) + 1);
		ItemStack i14 = new ItemStack(Material.VINE, r.nextInt(4) + 1);
		ItemStack i15 = new ItemStack(Material.WEB, r.nextInt(31) + 1);
		ItemStack i16 = new ItemStack(Material.LONG_GRASS, r.nextInt(31) + 1);
		ItemStack i17 = new ItemStack(Material.PISTON_BASE, r.nextInt(3) + 1);
		ItemStack i18 = new ItemStack(Material.MELON_BLOCK, r.nextInt(32) + 1);
		ItemStack i19 = new ItemStack(Material.MYCEL, r.nextInt(7) + 1);
		ItemStack i20 = new ItemStack(Material.NETHER_WARTS, r.nextInt(63) + 1);
		ItemStack i21 = new ItemStack(Material.ENCHANTMENT_TABLE, r.nextInt(1) + 1);
		ItemStack i22 = new ItemStack(Material.REDSTONE_LAMP_OFF, r.nextInt(6) + 1);
		ItemStack i23 = new ItemStack(Material.EMERALD_BLOCK, r.nextInt(2) + 1);
		ItemStack i24 = new ItemStack(Material.SANDSTONE_STAIRS, r.nextInt(4) + 1);
		ItemStack i25 = new ItemStack(Material.TRIPWIRE_HOOK, r.nextInt(10) + 1);
		ItemStack i26 = new ItemStack(Material.WOOD_BUTTON, r.nextInt(63) + 1);
		ItemStack i27 = new ItemStack(Material.DAYLIGHT_DETECTOR, r.nextInt(1) + 1);
		ItemStack i28 = new ItemStack(Material.QUARTZ_ORE, r.nextInt(63) + 1);
		ItemStack i29 = new ItemStack(Material.QUARTZ_BLOCK, r.nextInt(32) + 1);
		ItemStack i30 = new ItemStack(Material.QUARTZ_BLOCK, r.nextInt(32) + 1, (short) 1);
		ItemStack i31 = new ItemStack(Material.QUARTZ_BLOCK, r.nextInt(32) + 1, (short) 2);
		ItemStack i32 = new ItemStack(Material.QUARTZ_STAIRS, r.nextInt(32) + 1);
		ItemStack i33 = new ItemStack(Material.DROPPER, r.nextInt(2) + 1);
		ItemStack i34 = new ItemStack(Material.ACACIA_FENCE, r.nextInt(5) + 1);
		ItemStack i35 = new ItemStack(Material.SLIME_BALL, r.nextInt(6) + 1);
		ItemStack i36 = new ItemStack(Material.PRISMARINE, r.nextInt(17) + 1);
		ItemStack i37 = new ItemStack(Material.HAY_BLOCK, r.nextInt(5) + 1);
		ItemStack i38 = new ItemStack(Material.SEA_LANTERN, r.nextInt(19) + 1);
		ItemStack i39 = new ItemStack(Material.PACKED_ICE, r.nextInt(20) + 1);
		ItemStack i40 = new ItemStack(Material.FLOWER_POT, r.nextInt(5) + 1);

		ItemStack[] items = { i, i7, i8, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, i32, i33, i34, i35, i36, i37, i38, i39, i40 };

		return items[r.nextInt(items.length)];
	}

	private static ItemStack getItemsBig(){
		Random r = new Random();

		ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack i2 = new ItemStack(Material.GLASS, r.nextInt(32) + 1);
		ItemStack i3 = new ItemStack(Material.BED, r.nextInt(2) + 1);
		ItemStack i4 = new ItemStack(Material.CAULDRON);
		ItemStack i5 = new ItemStack(Material.ENDER_CHEST, 2);
		ItemStack i6 = new ItemStack(Material.BEACON);
		ItemStack i7 = new ItemStack(Material.DIAMOND, r.nextInt(7) + 1);
		ItemStack i8 = new ItemStack(Material.WORKBENCH);
		ItemStack i9 = new ItemStack(Material.BOOK);
		ItemStack i10 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack i11 = new ItemStack(Material.ELYTRA);
		ItemStack i12 = new ItemStack(Material.TNT, r.nextInt(4) + 1);
		ItemStack i13 = new ItemStack(Material.COOKIE, 64);
		ItemStack i14 = new ItemStack(Material.BUCKET, r.nextInt(2) + 1);
		ItemStack i15 = new ItemStack(Material.SPONGE, r.nextInt(5) + 1);
		ItemStack i16 = new ItemStack(Material.CHORUS_PLANT, r.nextInt(6) + 1);
		ItemStack i17 = new ItemStack(Material.GOLD_BLOCK, r.nextInt(2) + 1);
		ItemStack i18 = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short) 1560);
		ItemStack i19 = new ItemStack(Material.COAL_BLOCK, r.nextInt(7) + 1);
		ItemStack i20 = new ItemStack(Material.VINE, r.nextInt(7) + 1);
		ItemStack i21 = new ItemStack(Material.BREWING_STAND, 2);
		ItemStack i22 = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
		ItemStack i23 = new ItemStack(Material.EXP_BOTTLE, r.nextInt(32) + 1);
		ItemStack i24 = new ItemStack(Material.EMERALD, r.nextInt(5) + 1);
		ItemStack i25 = new ItemStack(Material.EMPTY_MAP, r.nextInt(4) + 1);
		ItemStack i26 = new ItemStack(Material.ANVIL, 1);
		ItemStack i27 = new ItemStack(Material.DRAGON_EGG, 1);
		ItemStack i28 = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack i29 = new ItemStack(Material.BONE, r.nextInt(10) + 1);
		ItemStack i30 = new ItemStack(Material.TRIPWIRE_HOOK, r.nextInt(10) + 1);
		ItemStack i31 = new ItemStack(Material.ACTIVATOR_RAIL, r.nextInt(6) + 1);
		ItemStack i32 = new ItemStack(Material.COOKED_RABBIT, r.nextInt(10) + 1);
		ItemStack i33 = new ItemStack(Material.STRING, r.nextInt(64) + 1);
		ItemStack i34 = new ItemStack(Material.CHEST, r.nextInt(6) + 1);
		ItemStack i35 = new ItemStack(Material.STRING, r.nextInt(64) + 1);
		ItemStack i36 = new ItemStack(Material.WOOL, r.nextInt(10) + 1, (short) r.nextInt(16));
		ItemStack i37 = new ItemStack(Material.CLAY, r.nextInt(10) + 1, (short) r.nextInt(16));
		ItemStack i38 = new ItemStack(Material.END_CRYSTAL, r.nextInt(4) + 1);
		ItemStack i39 = new ItemStack(Material.LAPIS_ORE, r.nextInt(6) + 1);
		ItemStack i40 = new ItemStack(Material.DIAMOND_PICKAXE);

		i40.addUnsafeEnchantment(Enchantment.DIG_SPEED, 6);
		i40.addUnsafeEnchantment(Enchantment.LUCK, 4);
		i40.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		i.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		i.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);

		ItemStack[] items = { i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, i32, i33, i34, i35, i36, i37, i38, i39, i40, ItemUtils.getBone(), ItemUtils.getPoppy(), ItemUtils.getRing() };

		return items[r.nextInt(items.length)];
	}
}
