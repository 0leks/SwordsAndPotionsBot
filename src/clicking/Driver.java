package clicking;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Driver {
  
  public static final boolean DEBUG = true;

  public static int highlanders = 7;
  public static int templarblades = 1;

  public static final long TIMEINDAY = 70000;
  
  public static final Action NEXT = new Action(900, 540);
  public static final Action SELECTCAPCHA = new Action(490, 450);
  public static final Action OKAYCAPCHA = new Action(440, 500);
  public static final Action DONEUPGRADE = new Action(750, 400);
  public static final Action OKAYACHIEVEMENT = new Action(490, 540);
  public static final Action FINISHCRAFTINGX = new Action(670, 255);

  
  public static final Action WORKER1 = new Action(40, 280);
  public static final Action WORKER2 = new Action(40, 350);
  public static final Action WORKER3 = new Action(40, 410);
  public static final Action WORKER4 = new Action(40, 470);

  public static final Action TABSWORDS = new Action(330, 130);
  public static final Action TABAXES = new Action(360, 130);
  public static final Action TABSPEARS = new Action(390, 130);
  public static final Action TABSTAFFS = new Action(455, 130);
  public static final Action TABMACES = new Action(490, 130);
  public static final Action TABMUSIC = new Action(524, 130);
  public static final Action TABTHROWN = new Action(555, 130);
  public static final Action TABGUNS = new Action(588, 130);
  public static final Action TABBOWS = new Action(618, 130);
  public static final Action TABHEAVYARMOR = new Action(648, 130);
  public static final Action TABARMOR = new Action(678, 130);
  public static final Action TABCLOTHES = new Action(715, 130);
  public static final Action TABHEAVYHELMETS = new Action(745, 130);

  public static final Action TABHELMETS = new Action(320, 175);
  public static final Action TABGAUNTLETS = new Action(380, 175);
  public static final Action TABGLOVES = new Action(410, 175);
  public static final Action TABHEAVYBOOTS = new Action(480, 175);
  public static final Action TABBOOTS = new Action(510, 175);
  public static final Action TABSHIELDS = new Action(575, 175);
  public static final Action TABPOTIONS = new Action(605, 175);
  public static final Action TABHERBS = new Action(645, 175);
  public static final Action TABSCROLLS = new Action(675, 175);
  public static final Action TABRINGS = new Action(705, 175);
  public static final Action TABAMULETS = new Action(735, 175);

  public static final Action TEST = null;

  public static final Action RIGHTARROW = new Action(835, 345);
  
  public static final Action ITEM1 = new Action(300, 270);
  public static final Action ITEM2 = new Action(410, 270);
  public static final Action ITEM3 = new Action(520, 270);
  public static final Action ITEM4 = new Action(620, 270);
  public static final Action ITEM5 = new Action(730, 270);
  public static final Action ITEM6 = new Action(300, 400);
  public static final Action ITEM7 = new Action(410, 400);
  public static final Action ITEM8 = new Action(520, 400);
  public static final Action ITEM9 = new Action(620, 400);
  public static final Action ITEM10 = new Action(730, 400);

  public static final Item SHARPSHOOTER = new Item(new Action[] {TABGUNS, ITEM1}, Item.ORANGE, "Sharpshooter");
  public static final Item CELESTIALHARP = new Item(new Action[] {TABMUSIC, ITEM1}, Item.YELLOW + 20000, "Celestial Harp");
  public static final Item CLERICALMARK = new Item(new Action[] {TABAMULETS, ITEM1}, Item.GREEN + 30000, "Clerical Mark");
  public static final Item STRONGHERBS = new Item(new Action[] {TABHERBS, ITEM2}, Item.YELLOW, "Strong Herbs");
  public static final Item HEALINGHERBS = new Item(new Action[] {TABHERBS, ITEM1}, Item.GREEN*2, "Healing Herbs");
  public static final Item SOFTBOOTS = new Item(new Action[] {TABBOOTS, ITEM1}, Item.ORANGE*3, "Soft Boots");
  public static final Item SMITHSGLOVES = new Item(new Action[] {TABGLOVES, ITEM1}, Item.PINK*3, "Smith's Gloves");
  public static final Item CLERICSROBE = new Item(new Action[] {TABCLOTHES, ITEM1}, Item.PURPLE*3, "Cleric's Robe");
  public static final Item RINGOFSORCERY = new Item(new Action[] {TABRINGS, ITEM1}, Item.GREEN, "Ring of Sorcery");
  public static final Item TEMPLARBLADE = new Item(new Action[] {TABSWORDS, ITEM1}, Item.GREEN, "Templar Blade");
  public static final Item HEAVYHELMET = new Item(new Action[] {TABHEAVYHELMETS, ITEM1}, Item.YELLOW, "Heavy Helmet");

  public static final Item GLADIATORSTRIDENT = new Item(new Action[] {TABSPEARS, ITEM1}, Item.ORANGE+1000, "Gladiator's Trident");
  public static final Item TRIDENT = new Item(new Action[] {TABSPEARS, ITEM8}, Item.ORANGE-2000, "Trident");
  public static final Item FLOATINGLANCE = new Item(new Action[] {TABSPEARS, ITEM10}, Item.ORANGE, "Floating Lance");
  public static final Item RANSEUR = new Item(new Action[] {TABSPEARS, RIGHTARROW, ITEM1}, Item.ORANGE, "Ranseur");
  public static final Item KNIGHTSRANSEUR = new Item(new Action[] {TABSPEARS, RIGHTARROW, ITEM2}, Item.YELLOW, "Knight's Ranseur");
  public static final Item FINELANCEOFTHEWOLF = new Item(new Action[] {TABSPEARS, RIGHTARROW, ITEM3}, Item.ORANGE, "Fine Lance of the Wolf");
  public static final Item TRANSMUNDANEPIKE = new Item(new Action[] {TABSPEARS, RIGHTARROW, ITEM5}, Item.ORANGE, "Transmuundane Pike");
  public static final Item POSEIDON = new Item(new Action[] {TABSPEARS, RIGHTARROW, ITEM3}, Item.PINK, "Poseidon");
  public static final Item DOLPHINBUCKLER = new Item(new Action[] {TABSHIELDS, ITEM3}, Item.ORANGE, "Dolphin Buckler");
  public static final Item BONESHIELD = new Item(new Action[] {TABSHIELDS, ITEM4}, Item.PINK, "Bone Shield");
  public static final Item MEDUSASHIELD = new Item(new Action[] {TABSHIELDS, ITEM5}, Item.YELLOW, "Medusa Shield");
  public static final Item SLEDGEHAMMER = new Item(new Action[] {TABMACES, ITEM3}, Item.GREEN, "Sledgehammer");
  public static final Item SOLDIERSMACE = new Item(new Action[] {TABMACES, ITEM5}, Item.YELLOW, "Soldier's Mace");
  public static final Item THORNMACE = new Item(new Action[] {TABMACES, ITEM6}, Item.YELLOW, "Thorn Mace");
  public static final Item FLAIL = new Item(new Action[] {TABMACES, ITEM9}, Item.YELLOW, "Flail");
  public static final Item STEELMACE = new Item(new Action[] {TABMACES, ITEM2}, Item.YELLOW, "Steel Mace");
  public static final Item ORANGEHAMMER = new Item(new Action[] {TABMACES, ITEM3}, Item.YELLOW, "Orange Hammer");
  public static final Item SKULLCRUSHER = new Item(new Action[] {TABMACES, ITEM4}, Item.ORANGE, "Skull Crusher");
  public static final Item DARKPALADINHAMMER = new Item(new Action[] {TABMACES, ITEM5}, Item.ORANGE, "Dark Paladin Hammer");
  public static final Item CRUSHINGFIST = new Item(new Action[] {TABMACES, ITEM6}, Item.ORANGE, "Crushing Fist");
  public static final Item WARPRIESTSMACE = new Item(new Action[] {TABMACES, ITEM7}, Item.GREEN, "War Priest's Mace");
  
  public static final Item THROWINGKNIVES = new Item(new Action[] {TABTHROWN, ITEM3}, Item.YELLOW, "Throwing Knives");
  public static final Item BREEZESLING = new Item(new Action[] {TABTHROWN, ITEM4}, Item.YELLOW, "Breeze Sling");
  public static final Item BOOMERANG = new Item(new Action[] {TABTHROWN, ITEM5}, Item.YELLOW, "Boomerang");
  public static final Item FINEBOOMERANG = new Item(new Action[] {TABTHROWN, ITEM6}, Item.ORANGE, "Fine Boomerang");
  public static final Item STORMKNIVES = new Item(new Action[] {TABTHROWN, ITEM9}, Item.ORANGE, "Storm Knives");
  public static final Item WOUNDINGKNIVES = new Item(new Action[] {TABTHROWN, ITEM8}, Item.ORANGE, "Wounding Knives");
  public static final Item BLACKBOMBS = new Item(new Action[] {TABTHROWN, ITEM8}, Item.GREEN, "Black Bombs");
  public static final Item CIRCUSKNIVES = new Item(new Action[] {TABTHROWN, ITEM9}, Item.ORANGE, "Circus Knives");
  public static final Item SNIPERRIFLE = new Item(new Action[] {TABGUNS, RIGHTARROW, ITEM3}, Item.GREEN, "Sniper Rifle");
  public static final Item BLUELAGOON = new Item(new Action[] {TABGUNS, ITEM4}, Item.ORANGE, "Blue Lagoon");
  public static final Item SOULSHOOTER = new Item(new Action[] {TABGUNS, ITEM5}, Item.ORANGE + 5000, "Soul Shooter");
  public static final Item GREENDRAGON = new Item(new Action[] {TABGUNS, ITEM6}, Item.PINK, "Green Dragon");
  public static final Item DRAGON = new Item(new Action[] {TABGUNS, RIGHTARROW, ITEM1}, Item.ORANGE , "Dragon");
  
  public static final Item ENCHANTEDSTAFF = new Item(new Action[] {TABSTAFFS, ITEM9}, Item.YELLOW, "Enchanted Staff");
  public static final Item WOODELFSTAFF = new Item(new Action[] {TABSTAFFS, ITEM5}, Item.YELLOW, "Wood Elf Staff");
  public static final Item EARTHSTAFF = new Item(new Action[] {TABSTAFFS, ITEM9}, Item.ORANGE, "Earth Staff");
  public static final Item SKELETONSTAFF = new Item(new Action[] {TABSTAFFS, ITEM1}, Item.ORANGE, "Skeleton Staff");
  public static final Item HEALERSTAFF = new Item(new Action[] {TABSTAFFS, RIGHTARROW, ITEM2}, Item.YELLOW, "Healerstaff");
  public static final Item ELEMENTALROD = new Item(new Action[] {TABSTAFFS, ITEM2}, Item.PURPLE, "Elemental Rod");
  public static final Item CLEANSINGSTAFF = new Item(new Action[] {TABSTAFFS, RIGHTARROW, ITEM4}, Item.ORANGE, "Cleansing Staff");
  public static final Item REGENHERBS = new Item(new Action[] {TABHERBS, ITEM4}, Item.YELLOW, "Regen Herbs");
  public static final Item SALVEOFPROTECTION = new Item(new Action[] {TABHERBS, ITEM3}, Item.ORANGE+3000, "Salve of Protection");
  public static final Item MAGICDUST = new Item(new Action[] {TABHERBS, ITEM2}, Item.ORANGE, "Magic Dust");
  public static final Item RESTORATIONPOWDER = new Item(new Action[] {TABHERBS, RIGHTARROW, ITEM1}, Item.ORANGE, "Restoration Powder");
  public static final Item CLEANSINGHERBS = new Item(new Action[] {TABHERBS, RIGHTARROW, ITEM1}, Item.PINK, "Cleansing Herbs");
  
  public static final Item LUCKYAMULET = new Item(new Action[] {TABAMULETS, ITEM1}, Item.GREEN, "Lucky Amulet");
  public static final Item PENDANTOFSAFETY = new Item(new Action[] {TABAMULETS, ITEM3}, Item.ORANGE, "Pendant of Safety");
  public static final Item PENDANTOFSAFETYNEEDED = new Item(new Action[] {TABAMULETS, ITEM3}, Item.ORANGE, "Pendant of Safety left to make");
  public static final Item AMULETOFLIFE = new Item(new Action[] {TABAMULETS, ITEM10}, Item.ORANGE, "Amulet of Life");
  public static final Item AMULETOFDARKNESS = new Item(new Action[] {TABAMULETS, ITEM8}, Item.ORANGE, "Amulet of Darkness");
  public static final Item AMULETOFLIGHT = new Item(new Action[] {TABAMULETS, RIGHTARROW, ITEM1}, Item.PINK, "Amulet of Light");
  public static final Item CLAIRVOYANCEAMULET = new Item(new Action[] {TABAMULETS, RIGHTARROW, ITEM2}, Item.PURPLE, "Clairvoyance Amulet");
  public static final Item SAGEAMULET = new Item(new Action[] {TABAMULETS, RIGHTARROW, ITEM5}, Item.PURPLE, "Sage Amulet");
  public static final Item ELEMENTALPENDANT = new Item(new Action[] {TABAMULETS, RIGHTARROW, ITEM4}, Item.ORANGE, "Elemental Pendant");
  public static final Item MAJESTICRING = new Item(new Action[] {TABRINGS, RIGHTARROW, ITEM3}, Item.ORANGE, "Majestic Ring");
  public static final Item ARCHMAGESMARK = new Item(new Action[] {TABRINGS, RIGHTARROW, ITEM5}, Item.ORANGE, "Archmage's Mark");
  public static final Item THIEFSMARK = new Item(new Action[] {TABRINGS, ITEM4}, Item.ORANGE-2000, "Thiefs Mark");
  public static final Item SERPENTBAND = new Item(new Action[] {TABRINGS, RIGHTARROW, ITEM2}, Item.YELLOW, "Serpent Band");

  public static final Item FLUTE = new Item(new Action[] {TABMUSIC, RIGHTARROW, ITEM5}, Item.GREEN, "Flute");
  public static final Item LUTE = new Item(new Action[] {TABMUSIC,ITEM4}, Item.YELLOW, "Lute");
  public static final Item DRUMS = new Item(new Action[] {TABMUSIC,ITEM6}, Item.YELLOW, "Drums");
  public static final Item PERFORMERSLUTE = new Item(new Action[] {TABMUSIC,ITEM7}, Item.ORANGE, "Performer's Lute");
  public static final Item ELVENFLUTE = new Item(new Action[] {TABMUSIC, ITEM3}, Item.GREEN, "Elven Flute");
  public static final Item BUGLE = new Item(new Action[] {TABMUSIC, ITEM10}, Item.PINK, "Bugle");
  public static final Item CELESTIALFLUTE = new Item(new Action[] {TABMUSIC, RIGHTARROW, ITEM3}, Item.PINK, "Celestial Flute");
  public static final Item DOUBLEFLUTE = new Item(new Action[] {TABMUSIC, RIGHTARROW, ITEM1}, Item.YELLOW, "Double Flute");
  public static final Item FLUTEOFWONDER = new Item(new Action[] {TABMUSIC, RIGHTARROW, ITEM2}, Item.ORANGE, "Flute of Wonder");
  public static final Item BAGPIPES = new Item(new Action[] {TABMUSIC, RIGHTARROW, ITEM5}, Item.YELLOW, "Bagpipes");
  public static final Item BARDSLUTE = new Item(new Action[] {TABMUSIC, RIGHTARROW, ITEM4}, Item.YELLOW, "Bard's Lute");
  public static final Item CELESTIALPIPES = new Item(new Action[] {TABMUSIC, RIGHTARROW, ITEM5}, Item.GREEN, "Celestial Pipes");

  public static final Item MAGICSCROLL = new Item(new Action[] {TABSCROLLS, ITEM1}, Item.GREEN, "Magic Scroll");
  public static final Item LUCKSCROLL = new Item(new Action[] {TABSCROLLS, ITEM4}, Item.YELLOW, "Luck Scroll");
  public static final Item EARTHSCROLL = new Item(new Action[] {TABSCROLLS, ITEM3}, Item.ORANGE, "Earth Scroll");
  public static final Item FIREBALLSCROLL = new Item(new Action[] {TABSCROLLS, ITEM2}, Item.YELLOW, "Fireball Scroll");
  public static final Item PROTECTSCROLL = new Item(new Action[] {TABSCROLLS, ITEM5}, Item.YELLOW, "Protect Scroll");
  public static final Item EARTHQUAKESCROLL = new Item(new Action[] {TABSCROLLS, ITEM7}, Item.ORANGE, "Earthquake Scroll");
  public static final Item FORTUNESCROLL = new Item(new Action[] {TABSCROLLS, ITEM9}, Item.ORANGE, "Fortune Scroll");
  public static final Item BLAZESCROLL = new Item(new Action[] {TABSCROLLS, RIGHTARROW, ITEM1}, Item.ORANGE, "Blaze Scroll");
  public static final Item SHIELDINGSCROLL = new Item(new Action[] {TABSCROLLS, ITEM10}, Item.GREEN, "Shielding Scroll");
  public static final Item FIRESTORMSCROLL = new Item(new Action[] {TABSCROLLS, ITEM7}, Item.YELLOW, "Firestorm Scroll");
  public static final Item FIRESTORMNEEDED = new Item(new Action[] {}, Item.YELLOW, "Amount of Firestorms left to make: ");
  public static final Item FIRESHIELDSCROLL = new Item(new Action[] {TABSCROLLS, RIGHTARROW, ITEM4}, Item.PINK, "Fireshield Scroll");
  public static final Item BRISKBEVERAGE = new Item(new Action[] {TABPOTIONS, ITEM4}, Item.GREEN, "Brisk Beverage");
  public static final Item REBIRTHPOTION = new Item(new Action[] {TABPOTIONS, ITEM9}, Item.PINK, "Rebirth Potion");
  public static final Item SUPERPOTION = new Item(new Action[] {TABPOTIONS, RIGHTARROW, ITEM7}, Item.PURPLE, "Super Potion");

  public static final Item CROSSBOW = new Item(new Action[] {TABBOWS, ITEM3}, Item.YELLOW, "Crossbow");
  public static final Item PIERCER = new Item(new Action[] {TABBOWS, ITEM6}, Item.ORANGE, "Piercer");
  public static final Item CALAMITOUSBOW = new Item(new Action[] {TABBOWS, RIGHTARROW, ITEM2}, Item.YELLOW, "Calamitous Bow");
  public static final Item HUNTINGCROSSBOW = new Item(new Action[] {TABBOWS, RIGHTARROW, ITEM4}, Item.PINK, "Hunting Crossbow");
  public static final Item HEAVYCROSSBOW = new Item(new Action[] {TABBOWS, RIGHTARROW, ITEM10}, Item.PINK, "Heavy Crossbow");

  public static final Item SCALEMAIL = new Item(new Action[] {TABHEAVYARMOR, ITEM2}, Item.GREEN, "Scale Mail");
  public static final Item BRIGANDINE = new Item(new Action[] {TABHEAVYARMOR, ITEM3}, Item.YELLOW, "Brigandine");
  public static final Item CHAINMAIL = new Item(new Action[] {TABHEAVYARMOR, RIGHTARROW, ITEM1}, Item.GREEN, "Chain Mail");
  public static final Item LORICASEGMENTATA = new Item(new Action[] {TABHEAVYARMOR, ITEM1}, Item.YELLOW, "Lorica Segmentata");
  public static final Item HALFPLATE = new Item(new Action[] {TABHEAVYARMOR, ITEM3}, Item.YELLOW, "Half Plate");
  public static final Item IRONPLATEARMOR = new Item(new Action[] {TABHEAVYARMOR, ITEM4}, Item.YELLOW, "Iron Plate Armor");
  public static final Item FLEXIBLEPLATE = new Item(new Action[] {TABHEAVYARMOR, ITEM5}, Item.YELLOW, "Flexible Plate");
  public static final Item GUARDMAIL = new Item(new Action[] {TABHEAVYARMOR, ITEM6}, Item.ORANGE, "Guard Mail");
  public static final Item FULLPLATE = new Item(new Action[] {TABHEAVYARMOR, ITEM9}, Item.ORANGE, "Full Plate");
  public static final Item GREATARMOR = new Item(new Action[] {TABHEAVYARMOR, ITEM7}, Item.ORANGE, "Great Armor");
  public static final Item GREATARMORNEEDED = new Item(new Action[] {TABHEAVYARMOR, ITEM7}, Item.ORANGE, "Great Armor Left to Make:");
  public static final Item BLESSEDHELM = new Item(new Action[] {TABHEAVYHELMETS, ITEM7}, Item.ORANGE, "Blessed Helm");
  public static final Item WARMASK = new Item(new Action[] {TABHEAVYHELMETS, ITEM9}, Item.PINK, "War Mask");
  public static final Item TRANSMUNDANEFULLHELMET = new Item(new Action[] {TABHEAVYHELMETS, RIGHTARROW, ITEM1}, Item.PINK, "Transmundane Full Helmet");
  public static final Item STEELHAUBERK = new Item(new Action[] {TABHEAVYHELMETS, ITEM1}, Item.ORANGE, "Steel Hauberk");
  public static final Item STEELHAUBERKNEEDED = new Item(new Action[] {TABHEAVYHELMETS, ITEM1}, Item.ORANGE, "Steel Hauberk Left to Make:");
  public static final Item IRONGAUNTLETS = new Item(new Action[] {TABGAUNTLETS, ITEM1}, Item.GREEN, "Iron Gauntlets");
  public static final Item FINEGAUNTLETS = new Item(new Action[] {TABGAUNTLETS, ITEM2}, Item.GREEN, "Fine Gauntlets");
  public static final Item WARGAUNTLETS = new Item(new Action[] {TABGAUNTLETS, ITEM3}, Item.YELLOW, "War Gauntlets");
  public static final Item SPECTACULARGAUNTLETS = new Item(new Action[] {TABGAUNTLETS, ITEM5}, Item.YELLOW, "Spectacular Gauntlets");
  public static final Item GRANDGAUNTLETS = new Item(new Action[] {TABGAUNTLETS, ITEM7}, Item.ORANGE, "Grand Gauntlets");
  public static final Item MANHANDLERS = new Item(new Action[] {TABGAUNTLETS, ITEM6}, Item.PINK, "Man Handlers");
  public static final Item IRONBOOTS = new Item(new Action[] {TABHEAVYBOOTS, ITEM2}, Item.GREEN, "Iron Boots");
  public static final Item FINESABATON = new Item(new Action[] {TABHEAVYBOOTS, ITEM5}, Item.YELLOW, "Fine Sabaton");
  public static final Item KNIGHTSSABATONS = new Item(new Action[] {TABHEAVYBOOTS, ITEM3}, Item.YELLOW, "Knight's Sabatons");
  public static final Item KNIGHTSSABATONSNEEDED = new Item(new Action[] {TABHEAVYBOOTS, ITEM3}, Item.YELLOW, "Knight's Sabatons Left to Make:");
  public static final Item COPPERSHIELD = new Item(new Action[] {TABSHIELDS, ITEM2}, Item.GREEN, "Copper Shield");
  public static final Item KITESHIELD = new Item(new Action[] {TABSHIELDS, ITEM2}, Item.ORANGE, "Kite Shield");
  public static final Item HEATERSHIELD = new Item(new Action[] {TABSHIELDS, ITEM3}, Item.YELLOW, "Heater Shield");
  public static final Item SCUTUM = new Item(new Action[] {TABSHIELDS, ITEM4}, Item.YELLOW, "Scutum");
  public static final Item DEFENDER = new Item(new Action[] {TABSHIELDS, ITEM5}, Item.ORANGE, "Defender");

  public static final Item HIGHLANDER = new Item(new Action[] {TABSWORDS, ITEM7}, Item.ORANGE, "Highlander");
  public static final Item MURASAME = new Item(new Action[] {TABSWORDS, RIGHTARROW, ITEM4}, Item.PINK, "Murasame");
  public static final Item DEMONSBANE = new Item(new Action[] {TABSWORDS, RIGHTARROW, ITEM2}, Item.PURPLE, "Demonsbane");
  public static final Item PALADINSWORD = new Item(new Action[] {TABSWORDS, RIGHTARROW, ITEM4}, Item.PURPLE, "Paladin Sword");
  public static final Item POLEAXE = new Item(new Action[] {TABAXES, ITEM9}, Item.PINK, "Pole-axe");
  public static final Item WARLABRYS = new Item(new Action[] {TABAXES, RIGHTARROW, ITEM3}, Item.YELLOW, "War Labrys");

  public static final Item ANTIVENOM = new Item(new Action[] {TABPOTIONS, ITEM1}, Item.GREEN, "Antivenom");
  public static final Item SWIFTIRONBOOTS = new Item(new Action[] {TABHEAVYBOOTS, ITEM1}, Item.GREEN, "Swift Iron Boots");
  public static final Item EPICDEFENDER = new Item(new Action[] {TABSHIELDS, ITEM4}, Item.PURPLE, "Epic Defender");

  public static final Item GLADIATORSHELMET = new Item(new Action[] {TABHELMETS, ITEM7}, Item.GREEN, "Gladiator's Helmet");
  public static final Item CAPTAINSHELMET = new Item(new Action[] {TABHELMETS, ITEM8}, Item.YELLOW, "Captain's Helmet");
  public static final Item CAPTAINSHELMETNEEDED = new Item(new Action[] {TABHELMETS}, Item.YELLOW, "Captain's Helmet Left to Make:");
  public static final Item FURARMOR = new Item(new Action[] {TABARMOR, ITEM1}, Item.GREEN, "Fur Armor");
  public static final Item LEATHERCUIRASS = new Item(new Action[] {TABARMOR, ITEM2}, Item.GREEN, "Leather Cuirass");
  public static final Item GAMBESON = new Item(new Action[] {TABARMOR, ITEM6}, Item.GREEN, "Gambeson");
  public static final Item BARBARIANCHIEFARMOR = new Item(new Action[] {TABARMOR, ITEM7}, Item.ORANGE, "Barbarian Chief Armor");
  public static final Item COWLEATHERARMOR = new Item(new Action[] {TABARMOR, ITEM8}, Item.YELLOW, "Cow Leather Armor");
  public static final Item PERFECTLEATHER = new Item(new Action[] {TABARMOR, ITEM9}, Item.YELLOW, "Perfect Leather");
  public static final Item NOBLELEATHER = new Item(new Action[] {TABARMOR, ITEM10}, Item.ORANGE, "Noble Leather");
  public static final Item NOBLELEATHERNEEDED = new Item(new Action[] {TABARMOR, ITEM10}, Item.ORANGE, "Noble Leather Left to Make");
  public static final Item LEATHERGLOVES = new Item(new Action[] {TABGLOVES, ITEM2}, Item.GREEN, "Leather Gloves");
  public static final Item LONGGLOVES = new Item(new Action[] {TABGLOVES, ITEM3}, Item.YELLOW, "Long Gloves");
  public static final Item NOBLESGLOVES = new Item(new Action[] {TABGLOVES, ITEM5}, Item.ORANGE, "Noble's Gloves");
  public static final Item NOBLESGLOVESNEEDED = new Item(new Action[] {TABGLOVES, ITEM5}, Item.ORANGE, "Noble's Gloves left to make");
  public static final Item LONGGLOVESNEEDED = new Item(new Action[] {TABGLOVES, ITEM3}, Item.YELLOW, "Long Gloves Left to Make");
  public static final Item FURBOOTS = new Item(new Action[] {TABBOOTS, ITEM1}, Item.GREEN, "Fur Boots");
  public static final Item RIDINGBOOTS = new Item(new Action[] {TABBOOTS, ITEM2}, Item.GREEN, "Riding Boots");
  public static final Item LOOSEBOOTS = new Item(new Action[] {TABBOOTS, ITEM4}, Item.GREEN, "Loose Boots");
  public static final Item LOOSEBOOTSNEEDED = new Item(new Action[] {TABBOOTS, ITEM4}, Item.GREEN, "Loose Boots Left to Make");
  
  
  public Resource ironore = new Resource( 161, 0, 60 );
  public Resource steel = new Resource( 57, 0, 22 );
  public Resource mithril = new Resource( 3, 0, 15 );
  
  public Resource wood = new Resource( 161, 0, 60 );
  public Resource elfwood = new Resource( 151, 0, 36 );
  public Resource ironwood = new Resource( 52, 0, 21 );

  public Resource herbs = new Resource( 141, 0, 68 );
  public Resource glass = new Resource( 135, 0, 29 );
  public Resource oil = new Resource( 42, 0, 21 );
  
  public Resource leather = new Resource( 162, 0, 58 );
  public Resource fabric = new Resource( 152, 0, 49 );
  public Resource dyes = new Resource( 152, 0, 15 );
  
  public Resource gems = new Resource( 3, 0, 10 );
  public Resource powder = new Resource( 136, 0, 15 );
  public Resource crystals = new Resource( 4, 0, 11 );
  
  private static List<ResourceScreenLocation> resources;
  
  public static int itemsCrafted = 0;
  
  private static Robot robot;
  private List<Worker> workers;
  
  private JFrame exitFrame;
  private boolean started;
  private Thread thread;
  
//  private static Rectangle screenRect = new Rectangle(0, 0, 1900, 1100);
  private static Rectangle screenRect = new Rectangle(-1900, 0, 1900, 1100);

  
  
  public static void solveFinishCrafting() {
    FINISHCRAFTINGX.execute(robot);
  }
  
  public static void solveUnlock() {
    DONEUPGRADE.execute(robot);
  }
  
  public static void solveUpgrade() {
    DONEUPGRADE.execute(robot);
  }
  
  public static void solveAchievement() {
    OKAYACHIEVEMENT.execute(robot);
  }
  
  public static void solveCapcha() {
    
    int value = 0;
    boolean capcha = true;
    do {
      SELECTCAPCHA.execute(robot);
      typeNumber(robot, value++);
      OKAYCAPCHA.execute(robot);
      sleep(500, false);
      capcha = checkForCapcha();
      
    } while( capcha );
    
  }
  public static void typeNumber(Robot r, int number) {
    int tens = number/10;
    int ones = number%10;
    int keyone = tens + KeyEvent.VK_0;
    int keytwo = ones + KeyEvent.VK_0;
    r.keyPress(keyone);
    r.keyRelease(keyone);
    r.keyPress(keytwo);
    r.keyRelease(keytwo);
  }
  
  public void initializeImageProcessing() {

    
    BufferedImage image = robot.createScreenCapture(screenRect);
    ImageProcessing ip = new ImageProcessing(image);
//    ip.original = ip.readFromFile("tempsave");
//    ip.copy = ip.readFromFile("tempsave");
    screenRect = ip.getGameScreen();
    screenRect.x -= 1900;
    screenRect.width -= 310;
    Action.XOFFSET = screenRect.x;
    Action.YOFFSET = screenRect.y;
    if( TEST != null ) {
      TEST.move(robot);
      System.exit(0);
    }
    System.out.println("Game screen is " + screenRect.x + "," + screenRect.y + " width=" + screenRect.width + ", height=" + screenRect.height);
    ip.saveCopy("gamescreen");
    //ip.saveCopy("tempsave");

//     image = robot.createScreenCapture(screenRect);
//     ip = new ImageProcessing(image);
//     ip.processNumbers();
//    if( ip.detectFinishCrafting() ) {
//      System.out.println("Finish Crafting Now");
//    }
//    else {
//      System.out.println("not finish crafting");
//    }
//    ip.saveCopy("afinishstuff");
//    System.exit(0);
    
    ip.processNumbers();
    
    
  }
  
  public void saveScreenShot() {
    BufferedImage image = robot.createScreenCapture(screenRect);
    ImageProcessing ip = new ImageProcessing(image);
    
//    if( ip.detectCapcha() ) {
//      System.out.println("capcha");
//    }
//    else {
//      System.out.println("NO capcha");
//    }
    
    ip.saveCopy("copy" + (System.currentTimeMillis()%1000000) / 1000);
    ip.saveOriginal("og" + (System.currentTimeMillis()%1000000) / 1000);
  }
  public static void processScreen() {
    BufferedImage image = robot.createScreenCapture(screenRect);
    ImageProcessing ip = new ImageProcessing(image);
    
    while( ip.readResources(resources) ) {
      ip.processNumbers();
    }
    
//    ip.saveCopy("copy" + (System.currentTimeMillis()%1000000) / 1000);
//    ip.saveOriginal("og" + (System.currentTimeMillis()%1000000) / 1000);
  }
  
  public static boolean checkForCapcha() {
    ImageProcessing ip = new ImageProcessing(robot.createScreenCapture(screenRect));
    boolean capcha =  ip.detectCapcha();
    return capcha;
  }
  
  public boolean checkForNight() {
    ImageProcessing ip = new ImageProcessing(robot.createScreenCapture(screenRect));
    boolean night =  ip.detectNightTime();
//    ip.saveCopy("Nighttime");
    return night;
  }
  
  public static void checkBlock() {

    boolean blocked = true;
    boolean wasBlocked = false;
    boolean startedTrading = false;
    boolean startedLagging = false;
    do {
      ImageProcessing ip = new ImageProcessing(robot.createScreenCapture(screenRect));
      boolean capcha = ip.detectCapcha();
      if( capcha ) {
        System.out.println("Solving Capcha");
        solveCapcha();
        continue;
      }
      
      if( ip.detectUpgrade() ) {
        System.out.println("Finished upgrading something");
        solveUpgrade();
        sleep(1000, false);
        continue;
      }
      
      if( ip.detectAchievement() ) {
        System.out.println("Got an achievement");
        solveAchievement();
        sleep(1000, false);
        continue;
      }
      
      if( ip.detectUnlock() ) {
        System.out.println("Unlocked an item");
        solveUnlock();
        sleep(1000, false);
        continue;
      }
      
      if( ip.detectFinishCrafting() ) {
        System.out.println("Finish Crafting Now");
        solveFinishCrafting();
        sleep(1000, false);
        continue;
      }
      
      boolean lag = ip.detectLag();
      if( lag && !startedLagging ) {
        startedLagging = true;
        System.out.println("Game is lagging!");
      }
      if( !lag && startedLagging ) {
        System.out.println("Game stopped lagging");
        startedLagging = false;
      }
      boolean trading = ip.detectTrading();
      if( trading && !startedTrading ) {
        startedTrading = true;
        System.out.println("You are trading with a customer");
      }
      if( !trading && startedTrading ) {
        System.out.println("You finished trading with a customer");
        startedTrading = false;
      }
//      ip.saveCopy("lagging");
      blocked = lag || trading;
      if( blocked )  {
        wasBlocked = true;
        sleep(1000, false);
      }
    } while( blocked );
    
    if( wasBlocked ) {
      sleep(1000, false);
    }
  }
  
  public void printResources() {
    System.out.println("   Iron: " + ironore.getAmount() + "\t    Wood: " + wood.getAmount()     + "\tHerbs: " + herbs.getAmount() + "\tLeather: " + leather.getAmount() + "\t    Gems: " + gems.getAmount());
    System.out.println("  Steel: " + steel.getAmount()   + "\t Elfwood: " + elfwood.getAmount()  + "\tGlass: " + glass.getAmount() + "\t Fabric: " + fabric.getAmount()  + "\t  Powder: " + powder.getAmount());
    System.out.println("Mithril: " + mithril.getAmount() + "\tIronwood: " + ironwood.getAmount() + "\t  Oil: " + oil.getAmount()   + "\t   Dyes: " + dyes.getAmount()    + "\tCrystals: " + crystals.getAmount());
  }
  
  
  long timeStarted;
  
  public void startPlaying() {
    
    //glass.debug = true;
    timeStarted = System.currentTimeMillis();
    initializeItems();
    initializeResourceLocations();
    initializeImageProcessing();
    saveScreenShot();
//    System.exit(0);
    
    if( started ) {
      System.out.println("Quitting thread");
      return;
    }
    else {
      started = true;
    }
    
    if( DEBUG )
      System.out.println("Started playing");
    
    while( true ) {

      long run = System.currentTimeMillis() - timeStarted;
      System.err.println("Ran for " + run/60000 + " minutes.");
//      if( run > 50000000 ) {
//        System.err.println("Shutting down.");
//        System.exit(0);
//      }
      
      if( DEBUG ) {
        System.out.println("Starting day");
      }
      
      checkBlock();
      NEXT.execute(robot);
      sleep(8000);
      
      if( DEBUG) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printResources();
        System.out.println("Crafted " + itemsCrafted + " items so far.");
      }
      for( Worker worker : workers ) {
        checkBlock();
        worker.startNextItem(robot);
      }

      checkBlock();
      boolean night = checkForNight();
      while( !night) {
        
//        if( DEBUG) {
//          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//          printResources();
//        }
        
        for( Worker worker : workers ) {
          checkBlock();
          if( worker.work(1000) ) {
            worker.startNextItem(robot);
          }
        }
        sleep(1000);
        checkBlock();
        night = checkForNight();
        //System.out.println("Night time:" + night);
      }
      if( DEBUG ) {
        System.out.println("Day ended");
      }
      
      sleep(8000, true);
      checkBlock();
      NEXT.execute(robot);
      sleep(8000, true);
    
    }
    
  }
  
  private static long timeOfLastUpdate = 0;
  public static final long UPDATEFREQUENCY = 10000;
  
  public static void sleep(long milliseconds,  boolean processScreen) {
    
    long currentTime = System.currentTimeMillis();
    long timeSinceLastUpdate = currentTime - timeOfLastUpdate;
    if( timeSinceLastUpdate > UPDATEFREQUENCY && processScreen) {
      processScreen();
      timeOfLastUpdate = currentTime;
    }
    long timeUsed = System.currentTimeMillis() - currentTime;
    long timeLeft = milliseconds - timeUsed;
    if( timeLeft > 0 ) {
      try {
        Thread.sleep(timeLeft);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  
  public void initializeResourceLocations() {

    resources = new ArrayList<ResourceScreenLocation>();
    resources.add( new ResourceScreenLocation(ironore, 35, 5));
    resources.add( new ResourceScreenLocation(steel, 36, 32));
    resources.add( new ResourceScreenLocation(mithril, 36, 60));

    resources.add( new ResourceScreenLocation(wood, 98, 5));
    resources.add( new ResourceScreenLocation(elfwood, 98, 32));
    resources.add( new ResourceScreenLocation(ironwood, 99, 60));
    
    resources.add( new ResourceScreenLocation(herbs, 161, 5));
    resources.add( new ResourceScreenLocation(glass, 162, 32));
    resources.add( new ResourceScreenLocation(oil, 162, 60));

    resources.add( new ResourceScreenLocation(leather, 224, 5));
    resources.add( new ResourceScreenLocation(fabric, 224, 32));
    resources.add( new ResourceScreenLocation(dyes, 225, 60));

    resources.add( new ResourceScreenLocation(gems, 288, 5));
    resources.add( new ResourceScreenLocation(powder, 288, 32));
    resources.add( new ResourceScreenLocation(crystals, 288, 60));
  }

  public void sleep(long milliseconds) {
    
    sleep(milliseconds, true);
  }
  
  public void initializeItems() {
//    SHARPSHOOTER.addCost(new ResourceCost(wood, 15));
//    SHARPSHOOTER.addCost(new ResourceCost(elfwood, 15));
//    SHARPSHOOTER.addCost(new ResourceCost(powder, 10));
    
    int ADDED = 10;
    
    SHARPSHOOTER.addCost(new ResourceCost(wood, 5));
    SHARPSHOOTER.addCost(new ResourceCost(elfwood, 4));
    SHARPSHOOTER.addCost(new ResourceCost(powder, 3));
    CELESTIALHARP.addCost(new ResourceCost(elfwood, 5));
    CELESTIALHARP.addCost(new ResourceCost(leather, 5));
    CELESTIALHARP.addCost(new ResourceCost(glass, 2));
    CLERICALMARK.addCost(new ResourceCost(ironore, 2));
    CLERICALMARK.addCost(new ResourceCost(wood, 2));
    STRONGHERBS.addCost(new ResourceCost(herbs, 2));
    HEALINGHERBS.addCost(new ResourceCost(herbs, 1 + ADDED));
    CLERICSROBE.addCost(new ResourceCost(fabric, 8));
    CLERICSROBE.addCost(new ResourceCost(dyes, 8));
    CLERICSROBE.addCost(new ResourceCost(glass, 2 + ADDED));
    SMITHSGLOVES.addCost(new ResourceCost(leather, 4));
    SMITHSGLOVES.addCost(new ResourceCost(fabric, 4));
    SMITHSGLOVES.addCost(new ResourceCost(dyes, 4));
    SOFTBOOTS.addCost(new ResourceCost(dyes, 4));
    SOFTBOOTS.addCost(new ResourceCost(leather, 10));
    TEMPLARBLADE.addCost(new ResourceCost(steel, 10));
    TEMPLARBLADE.addCost(new ResourceCost(ironore, 8));
    TEMPLARBLADE.addCost(new ItemCost(HIGHLANDER, 3));
    RINGOFSORCERY.addCost(new ResourceCost(ironore, 5 + 3*ADDED));
    RINGOFSORCERY.addCost(new ResourceCost(glass, 4 + 3*ADDED));
    HEAVYHELMET.addCost(new ResourceCost(ironore, 8 + ADDED));

    TRIDENT.addCost(new ResourceCost(ironore, 2));
    TRIDENT.addCost(new ResourceCost(wood, 6));
    GLADIATORSTRIDENT.addCost(new ResourceCost(elfwood, 4));
    GLADIATORSTRIDENT.addCost(new ResourceCost(steel, 2));
    FLOATINGLANCE.addCost(new ResourceCost(wood, 6));
    FLOATINGLANCE.addCost(new ResourceCost(elfwood, 6));
    FLOATINGLANCE.addCost(new ResourceCost(ironore, 2));
    FLOATINGLANCE.addCost(new ResourceCost(steel, 1));
    RANSEUR.addCost(new ResourceCost(wood, 8  ));
    RANSEUR.addCost(new ResourceCost(ironore, 3  ));
    KNIGHTSRANSEUR.addCost(new ResourceCost(wood, 12  ));
    KNIGHTSRANSEUR.addCost(new ResourceCost(ironore, 6  ));
    KNIGHTSRANSEUR.addCost(new ResourceCost(steel, 2  ));
    FINELANCEOFTHEWOLF.addCost(new ResourceCost(wood, 7  ));
    FINELANCEOFTHEWOLF.addCost(new ResourceCost(elfwood, 5  ));
    FINELANCEOFTHEWOLF.addCost(new ResourceCost(steel, 4  ));
    FINELANCEOFTHEWOLF.addCost(new ResourceCost(mithril, 1  ));
    TRANSMUNDANEPIKE.addCost(new ResourceCost(mithril, 2  ));
    TRANSMUNDANEPIKE.addCost(new ResourceCost(ironwood, 3  ));
    POSEIDON.addCost(new ResourceCost(elfwood, 7 ));
    POSEIDON.addCost(new ResourceCost(ironwood, 4 ));
    POSEIDON.addCost(new ResourceCost(wood, 8 ));
    POSEIDON.addCost(new ResourceCost(mithril, 1 ));
    POSEIDON.addCost(new ResourceCost(crystals, 1 ));
    DOLPHINBUCKLER.addCost(new ResourceCost(elfwood, 4  ));
    DOLPHINBUCKLER.addCost(new ResourceCost(wood, 2  ));
    DOLPHINBUCKLER.addCost(new ResourceCost(ironore, 3  ));
    BONESHIELD.addCost(new ResourceCost(elfwood, 3  ));
    BONESHIELD.addCost(new ResourceCost(ironwood, 2));
    BONESHIELD.addCost(new ResourceCost(dyes, 5));
    MEDUSASHIELD.addCost(new ResourceCost(ironwood, 10));
    MEDUSASHIELD.addCost(new ResourceCost(gems, 2));
    MEDUSASHIELD.addCost(new ResourceCost(mithril, 2));
    MEDUSASHIELD.addCost(new ResourceCost(wood, 6));
    SLEDGEHAMMER.addCost(new ResourceCost(wood, 5));
    SLEDGEHAMMER.addCost(new ResourceCost(ironore, 2));
    SOLDIERSMACE.addCost(new ResourceCost(ironore, 2));
    SOLDIERSMACE.addCost(new ResourceCost(wood, 7));
    THORNMACE.addCost(new ResourceCost(wood, 5));
    THORNMACE.addCost(new ResourceCost(elfwood, 2));
    THORNMACE.addCost(new ResourceCost(ironore, 1));
    FLAIL.addCost(new ResourceCost(wood, 8));
    FLAIL.addCost(new ResourceCost(elfwood, 3));
    FLAIL.addCost(new ResourceCost(ironore, 2));
    STEELMACE.addCost(new ResourceCost(wood, 4));
    STEELMACE.addCost(new ResourceCost(elfwood, 3));
    STEELMACE.addCost(new ResourceCost(steel, 2));
    ORANGEHAMMER.addCost(new ResourceCost(wood, 6));
    ORANGEHAMMER.addCost(new ResourceCost(elfwood, 5));
    ORANGEHAMMER.addCost(new ResourceCost(steel, 2));
    SKULLCRUSHER.addCost(new ResourceCost(wood, 6));
    SKULLCRUSHER.addCost(new ResourceCost(elfwood, 5));
    SKULLCRUSHER.addCost(new ResourceCost(steel, 2));
    SKULLCRUSHER.addCost(new ResourceCost(oil, 2));
    DARKPALADINHAMMER.addCost(new ResourceCost(wood, 8));
    DARKPALADINHAMMER.addCost(new ResourceCost(elfwood, 6));
    DARKPALADINHAMMER.addCost(new ResourceCost(steel, 3));
    DARKPALADINHAMMER.addCost(new ResourceCost(oil, 3));
    CRUSHINGFIST.addCost(new ResourceCost(wood, 10));
    CRUSHINGFIST.addCost(new ResourceCost(elfwood, 10));
    CRUSHINGFIST.addCost(new ResourceCost(ironore, 5));
    CRUSHINGFIST.addCost(new ResourceCost(steel, 4));
    WARPRIESTSMACE.addCost(new ResourceCost(elfwood, 4));
    WARPRIESTSMACE.addCost(new ResourceCost(ironwood, 2));
    
    THROWINGKNIVES.addCost(new ResourceCost(ironore, 6));
    BREEZESLING.addCost(new ResourceCost(wood, 6));
    BREEZESLING.addCost(new ResourceCost(fabric, 2));
    BOOMERANG.addCost(new ResourceCost(wood, 6));
    BOOMERANG.addCost(new ResourceCost(elfwood, 2));
    FINEBOOMERANG.addCost(new ResourceCost(elfwood, 2));
    FINEBOOMERANG.addCost(new ResourceCost(wood, 3));
    STORMKNIVES.addCost(new ResourceCost(ironore, 6));
    STORMKNIVES.addCost(new ResourceCost(steel, 3));
    STORMKNIVES.addCost(new ResourceCost(oil, 2));
    WOUNDINGKNIVES.addCost(new ResourceCost(ironwood, 8 + ADDED));
    WOUNDINGKNIVES.addCost(new ResourceCost(steel, 4 + ADDED));
    WOUNDINGKNIVES.addCost(new ResourceCost(oil, 3 + ADDED));
    BLACKBOMBS.addCost(new ResourceCost(wood, 8));
    BLACKBOMBS.addCost(new ResourceCost(elfwood, 7));
    BLACKBOMBS.addCost(new ResourceCost(steel, 4));
    BLACKBOMBS.addCost(new ResourceCost(powder, 4));
    CIRCUSKNIVES.addCost(new ResourceCost(ironore, 8));
    CIRCUSKNIVES.addCost(new ResourceCost(steel, 5));
    CIRCUSKNIVES.addCost(new ResourceCost(mithril, 2));
    CIRCUSKNIVES.addCost(new ResourceCost(oil, 5));
    SNIPERRIFLE.addCost(new ResourceCost(wood, 5));
    SNIPERRIFLE.addCost(new ResourceCost(elfwood, 2));
    SNIPERRIFLE.addCost(new ResourceCost(ironore, 1));
    SNIPERRIFLE.addCost(new ResourceCost(powder, 2));
    BLUELAGOON.addCost(new ResourceCost(elfwood, 8));
    BLUELAGOON.addCost(new ResourceCost(ironwood, 6));
    BLUELAGOON.addCost(new ResourceCost(steel, 5));
    BLUELAGOON.addCost(new ResourceCost(powder, 8));
    BLUELAGOON.addCost(new ResourceCost(ironore, 8));
    SOULSHOOTER.addCost(new ResourceCost(ironwood, 8));
    SOULSHOOTER.addCost(new ResourceCost(mithril, 1));
    SOULSHOOTER.addCost(new ResourceCost(crystals, 1 + ADDED));
    SOULSHOOTER.addCost(new ResourceCost(powder, 8));
    SOULSHOOTER.addCost(new ItemCost(SNIPERRIFLE, 3));
    GREENDRAGON.addCost(new ResourceCost(ironwood, 10));
    GREENDRAGON.addCost(new ResourceCost(mithril, 7));
    GREENDRAGON.addCost(new ResourceCost(powder, 10));
    GREENDRAGON.addCost(new ResourceCost(elfwood, 6));
    GREENDRAGON.addCost(new ResourceCost(ironore, 8));
    GREENDRAGON.addCost(new ItemCost(DRAGON, 3));
    DRAGON.addCost(new ResourceCost(ironore, 2));
    DRAGON.addCost(new ResourceCost(wood, 4));
    
    ENCHANTEDSTAFF.addCost(new ResourceCost(elfwood, 3));
    ENCHANTEDSTAFF.addCost(new ResourceCost(glass, 3));
    WOODELFSTAFF.addCost(new ResourceCost(wood, 1));
    WOODELFSTAFF.addCost(new ResourceCost(elfwood, 4));
    EARTHSTAFF.addCost(new ResourceCost(powder, 2));
    HEALERSTAFF.addCost(new ResourceCost(elfwood, 5));
    ELEMENTALROD.addCost(new ItemCost(ENCHANTEDSTAFF, 1));
    ELEMENTALROD.addCost(new ItemCost(SALVEOFPROTECTION, 1));
    CLEANSINGSTAFF.addCost(new ResourceCost(ironwood, 5));
    CLEANSINGSTAFF.addCost(new ResourceCost(glass, 6));
    CLEANSINGSTAFF.addCost(new ResourceCost(gems, 3));
    CLEANSINGSTAFF.addCost(new ResourceCost(wood, 6));
    CLEANSINGSTAFF.addCost(new ResourceCost(elfwood, 4));
    REGENHERBS.addCost(new ResourceCost(herbs, 4));
    REGENHERBS.addCost(new ResourceCost(leather, 2));
    SALVEOFPROTECTION.addCost(new ResourceCost(herbs, 3));
    SALVEOFPROTECTION.addCost(new ResourceCost(powder, 3));
    SALVEOFPROTECTION.addCost(new ResourceCost(elfwood, 4));
    MAGICDUST.addCost(new ResourceCost(elfwood, 5));
    MAGICDUST.addCost(new ResourceCost(powder, 2));
    MAGICDUST.addCost(new ResourceCost(herbs, 5));
    RESTORATIONPOWDER.addCost(new ResourceCost(herbs, 7));
    RESTORATIONPOWDER.addCost(new ResourceCost(powder, 7));
    CLEANSINGHERBS.addCost(new ResourceCost(powder, 8));
    CLEANSINGHERBS.addCost(new ResourceCost(herbs, 6));
    
    LUCKYAMULET.addCost(new ResourceCost(ironore, 2));
    LUCKYAMULET.addCost(new ResourceCost(wood, 1));
    PENDANTOFSAFETY.addCost(new ResourceCost(ironore, 4));
    PENDANTOFSAFETY.addCost(new ResourceCost(wood, 3));
    PENDANTOFSAFETY.addCost(new ResourceCost(glass, 1));
    PENDANTOFSAFETY.addCost(new ItemCost(PENDANTOFSAFETYNEEDED, 1));
    AMULETOFLIFE.addCost(new ResourceCost(steel, 2));
    AMULETOFLIFE.addCost(new ResourceCost(glass, 4));
    AMULETOFLIGHT.addCost(new ResourceCost(glass, 5));
    AMULETOFLIGHT.addCost(new ResourceCost(ironore, 6));
    AMULETOFLIGHT.addCost(new ResourceCost(steel, 3));
    AMULETOFDARKNESS.addCost(new ResourceCost(ironore, 5));
    AMULETOFDARKNESS.addCost(new ResourceCost(glass, 5));
    CLAIRVOYANCEAMULET.addCost(new ResourceCost(glass, 5));
    CLAIRVOYANCEAMULET.addCost(new ResourceCost(ironore, 6));
    CLAIRVOYANCEAMULET.addCost(new ResourceCost(steel, 2));
    CLAIRVOYANCEAMULET.addCost(new ResourceCost(gems, 2));
    CLAIRVOYANCEAMULET.addCost(new ResourceCost(wood, 5));
    SAGEAMULET.addCost(new ResourceCost(ironore, 7));
    SAGEAMULET.addCost(new ResourceCost(steel, 3));
    SAGEAMULET.addCost(new ResourceCost(gems, 3));
    SAGEAMULET.addCost(new ResourceCost(glass, 3));
    SAGEAMULET.addCost(new ItemCost(CLERICALMARK, 1));
    ELEMENTALPENDANT.addCost(new ResourceCost(ironwood, 6));
    ELEMENTALPENDANT.addCost(new ResourceCost(gems, 3));
    ELEMENTALPENDANT.addCost(new ResourceCost(crystals, 1));
    ELEMENTALPENDANT.addCost(new ResourceCost(elfwood, 5));
    MAJESTICRING.addCost(new ResourceCost(mithril, 3));
    MAJESTICRING.addCost(new ResourceCost(gems, 4));
    MAJESTICRING.addCost(new ResourceCost(crystals, 2));
    MAJESTICRING.addCost(new ItemCost(THIEFSMARK, 3));
    ARCHMAGESMARK.addCost(new ResourceCost(mithril, 5));
    ARCHMAGESMARK.addCost(new ResourceCost(gems, 4));
    ARCHMAGESMARK.addCost(new ResourceCost(crystals, 5));
    SERPENTBAND.addCost(new ResourceCost(ironore, 10));
    SERPENTBAND.addCost(new ResourceCost(steel, 4));
    SERPENTBAND.addCost(new ResourceCost(mithril, 3));
    SERPENTBAND.addCost(new ResourceCost(gems, 4));
    THIEFSMARK.addCost(new ResourceCost(ironore, 8));
//    THIEFSMARK.addCost(new ResourceCost(ironore, 40));

    FLUTE.addCost(new ResourceCost(wood, 4));
    LUTE.addCost(new ResourceCost(wood, 5));
    LUTE.addCost(new ResourceCost(elfwood, 2));
    LUTE.addCost(new ResourceCost(leather, 3));
    DRUMS.addCost(new ResourceCost(leather, 3));
    DRUMS.addCost(new ResourceCost(elfwood, 3));
    DRUMS.addCost(new ResourceCost(dyes, 1));
    PERFORMERSLUTE.addCost(new ResourceCost(elfwood, 2));
    ELVENFLUTE.addCost(new ResourceCost(elfwood, 2));
    ELVENFLUTE.addCost(new ResourceCost(glass, 1));
    BUGLE.addCost(new ResourceCost(ironore, 8));
    BUGLE.addCost(new ResourceCost(leather, 5));
    BUGLE.addCost(new ResourceCost(glass, 6));
    CELESTIALFLUTE.addCost(new ResourceCost(glass, 3));
    CELESTIALFLUTE.addCost(new ResourceCost(leather, 3));
    CELESTIALFLUTE.addCost(new ResourceCost(ironore, 4));
    DOUBLEFLUTE.addCost(new ResourceCost(wood, 3));
    DOUBLEFLUTE.addCost(new ResourceCost(elfwood, 5));
    DOUBLEFLUTE.addCost(new ResourceCost(glass, 3));
    FLUTEOFWONDER.addCost(new ResourceCost(elfwood, 5));
    FLUTEOFWONDER.addCost(new ResourceCost(ironwood, 5 + ADDED));
    FLUTEOFWONDER.addCost(new ResourceCost(wood, 6));
    FLUTEOFWONDER.addCost(new ResourceCost(leather, 6 + ADDED));
    BAGPIPES.addCost(new ResourceCost(elfwood, 6));
    BAGPIPES.addCost(new ResourceCost(dyes, 4));
    BAGPIPES.addCost(new ResourceCost(ironwood, 3));
    BAGPIPES.addCost(new ResourceCost(leather, 8));
    BARDSLUTE.addCost(new ResourceCost(leather, 8));
    BARDSLUTE.addCost(new ResourceCost(fabric, 8));
    BARDSLUTE.addCost(new ResourceCost(elfwood, 8));
    BARDSLUTE.addCost(new ResourceCost(ironwood, 6));
    CELESTIALPIPES.addCost(new ResourceCost(mithril, 5));
    CELESTIALPIPES.addCost(new ResourceCost(fabric, 5));
    CELESTIALPIPES.addCost(new ResourceCost(glass, 5));
    CELESTIALPIPES.addCost(new ResourceCost(wood, 8));
    CELESTIALPIPES.addCost(new ItemCost(CELESTIALHARP, 1));

    MAGICSCROLL.addCost(new ResourceCost(leather, 3 + ADDED));
    FIREBALLSCROLL.addCost(new ResourceCost(elfwood, 1));
    EARTHSCROLL.addCost(new ResourceCost(powder, 1));
    LUCKSCROLL.addCost(new ResourceCost(powder, 2));
    EARTHQUAKESCROLL.addCost(new ResourceCost(elfwood, 2));
    EARTHQUAKESCROLL.addCost(new ResourceCost(powder, 3));
    PROTECTSCROLL.addCost(new ResourceCost(powder, 2));
    PROTECTSCROLL.addCost(new ResourceCost(dyes, 2));
    PROTECTSCROLL.addCost(new ItemCost(MAGICSCROLL, 1));
    FORTUNESCROLL.addCost(new ResourceCost(ironwood, 2));
    FORTUNESCROLL.addCost(new ResourceCost(gems, 1));
    FORTUNESCROLL.addCost(new ResourceCost(crystals, 1));
    BLAZESCROLL.addCost(new ResourceCost(ironwood, 6));
    BLAZESCROLL.addCost(new ResourceCost(crystals, 2));
    BLAZESCROLL.addCost(new ResourceCost(gems, 2));
    SHIELDINGSCROLL.addCost(new ResourceCost(crystals, 4));
    SHIELDINGSCROLL.addCost(new ResourceCost(ironwood, 4));
    SHIELDINGSCROLL.addCost(new ItemCost(PROTECTSCROLL, 3));
    FIRESTORMSCROLL.addCost(new ResourceCost(elfwood, 4));
    FIRESTORMSCROLL.addCost(new ResourceCost(dyes, 4));
    FIRESTORMSCROLL.addCost(new ItemCost(FIREBALLSCROLL, 2));
    FIRESTORMSCROLL.addCost(new ItemCost(FIRESTORMNEEDED, 1));
    FIRESHIELDSCROLL.addCost(new ResourceCost(ironwood, 6));
    FIRESHIELDSCROLL.addCost(new ResourceCost(crystals, 6));
    FIRESHIELDSCROLL.addCost(new ResourceCost(gems, 3));
    FIRESHIELDSCROLL.addCost(new ResourceCost(dyes, 8));
    FIRESHIELDSCROLL.addCost(new ItemCost(FIRESTORMSCROLL, 1));
    BRISKBEVERAGE.addCost(new ResourceCost(herbs, 5));
    BRISKBEVERAGE.addCost(new ResourceCost(glass, 2));
    REBIRTHPOTION.addCost(new ResourceCost(oil, 2));
    REBIRTHPOTION.addCost(new ResourceCost(powder, 6));
    REBIRTHPOTION.addCost(new ResourceCost(glass, 6));
    REBIRTHPOTION.addCost(new ItemCost(BRISKBEVERAGE, 1));
    SUPERPOTION.addCost(new ResourceCost(oil, 10));
    SUPERPOTION.addCost(new ResourceCost(crystals, 5));
    SUPERPOTION.addCost(new ResourceCost(glass, 8));
    SUPERPOTION.addCost(new ResourceCost(herbs, 8));
    SUPERPOTION.addCost(new ResourceCost(dyes, 9));
    SUPERPOTION.addCost(new ItemCost(REBIRTHPOTION, 1));

    CROSSBOW.addCost(new ResourceCost(wood, 2));
    CROSSBOW.addCost(new ResourceCost(ironore, 2));
    CROSSBOW.addCost(new ResourceCost(leather, 3));
    PIERCER.addCost(new ResourceCost(leather, 2));
    PIERCER.addCost(new ResourceCost(wood, 7));
    PIERCER.addCost(new ResourceCost(elfwood, 3));
    CALAMITOUSBOW.addCost(new ResourceCost(wood, 8));
    CALAMITOUSBOW.addCost(new ResourceCost(elfwood, 6));
    CALAMITOUSBOW.addCost(new ResourceCost(ironwood, 3));
    CALAMITOUSBOW.addCost(new ResourceCost(steel, 2));
    CALAMITOUSBOW.addCost(new ResourceCost(fabric, 4));
    HUNTINGCROSSBOW.addCost(new ResourceCost(ironwood, 8));
    HUNTINGCROSSBOW.addCost(new ResourceCost(mithril, 2));
    HUNTINGCROSSBOW.addCost(new ResourceCost(fabric, 7));
    HUNTINGCROSSBOW.addCost(new ResourceCost(elfwood, 7));
    HUNTINGCROSSBOW.addCost(new ResourceCost(leather, 7));
    HEAVYCROSSBOW.addCost(new ResourceCost(elfwood, 6));
    HEAVYCROSSBOW.addCost(new ResourceCost(ironwood, 8));
    HEAVYCROSSBOW.addCost(new ResourceCost(steel, 4));
    HEAVYCROSSBOW.addCost(new ResourceCost(mithril, 4));
    HEAVYCROSSBOW.addCost(new ResourceCost(fabric, 10));

    SCALEMAIL.addCost(new ResourceCost(leather, 2));
    SCALEMAIL.addCost(new ResourceCost(ironore, 3));
    BRIGANDINE.addCost(new ResourceCost(ironore, 4));
    BRIGANDINE.addCost(new ResourceCost(leather, 3));
    CHAINMAIL.addCost(new ResourceCost(ironore, 6));
    CHAINMAIL.addCost(new ResourceCost(leather, 4));
    LORICASEGMENTATA.addCost(new ResourceCost(ironore, 4));
    LORICASEGMENTATA.addCost(new ResourceCost(steel, 2));
    HALFPLATE.addCost(new ResourceCost(steel, 2));
    HALFPLATE.addCost(new ResourceCost(oil, 2));
    HALFPLATE.addCost(new ResourceCost(leather, 4));
    HALFPLATE.addCost(new ResourceCost(fabric, 4));
    IRONPLATEARMOR.addCost(new ResourceCost(ironore, 8));
    IRONPLATEARMOR.addCost(new ResourceCost(steel, 4));
    IRONPLATEARMOR.addCost(new ResourceCost(oil, 3));
    IRONPLATEARMOR.addCost(new ResourceCost(leather, 2));
    FLEXIBLEPLATE.addCost(new ResourceCost(steel, 3));
    FLEXIBLEPLATE.addCost(new ResourceCost(oil, 6));
    FLEXIBLEPLATE.addCost(new ResourceCost(leather, 4));
    GUARDMAIL.addCost(new ResourceCost(steel, 3));
    GUARDMAIL.addCost(new ResourceCost(leather, 5));
    GUARDMAIL.addCost(new ResourceCost(oil, 5));
    FULLPLATE.addCost(new ItemCost(HALFPLATE, 1));
    FULLPLATE.addCost(new ResourceCost(steel, 4));
    FULLPLATE.addCost(new ResourceCost(oil, 5));
    FULLPLATE.addCost(new ResourceCost(ironore, 6));
    GREATARMOR.addCost(new ResourceCost(mithril, 4));
    GREATARMOR.addCost(new ResourceCost(steel, 5));
    GREATARMOR.addCost(new ResourceCost(leather, 4));
    GREATARMOR.addCost(new ResourceCost(oil, 7));
    GREATARMOR.addCost(new ItemCost(GREATARMORNEEDED, 1));
    BLESSEDHELM.addCost(new ResourceCost(ironore, 6));
    BLESSEDHELM.addCost(new ResourceCost(steel, 4));
    BLESSEDHELM.addCost(new ResourceCost(oil, 3));
    WARMASK.addCost(new ResourceCost(ironore, 6));
    WARMASK.addCost(new ResourceCost(steel, 4));
    WARMASK.addCost(new ResourceCost(oil, 5));
    TRANSMUNDANEFULLHELMET.addCost(new ResourceCost(mithril, 4));
    TRANSMUNDANEFULLHELMET.addCost(new ResourceCost(ironore, 9));
    TRANSMUNDANEFULLHELMET.addCost(new ResourceCost(steel, 4));
    TRANSMUNDANEFULLHELMET.addCost(new ResourceCost(oil, 8));
    STEELHAUBERK.addCost(new ResourceCost(ironore, 7));
    STEELHAUBERK.addCost(new ResourceCost(steel, 5));
    STEELHAUBERK.addCost(new ResourceCost(oil, 6));
    STEELHAUBERK.addCost(new ItemCost(HEAVYHELMET, 1));
    STEELHAUBERK.addCost(new ItemCost(STEELHAUBERKNEEDED, 1));
    IRONGAUNTLETS.addCost(new ResourceCost(ironore, 4));
    IRONGAUNTLETS.addCost(new ResourceCost(leather, 1));
    FINEGAUNTLETS.addCost(new ResourceCost(ironore, 4));
    FINEGAUNTLETS.addCost(new ResourceCost(leather, 4));
    FINEGAUNTLETS.addCost(new ResourceCost(fabric, 2));
    WARGAUNTLETS.addCost(new ResourceCost(steel, 3));
    WARGAUNTLETS.addCost(new ResourceCost(leather, 4));
    SPECTACULARGAUNTLETS.addCost(new ResourceCost(glass, 3));
    GRANDGAUNTLETS.addCost(new ResourceCost(steel, 3));
    GRANDGAUNTLETS.addCost(new ResourceCost(glass, 5));
    GRANDGAUNTLETS.addCost(new ResourceCost(oil, 5));
    MANHANDLERS.addCost(new ResourceCost(oil, 5));
    MANHANDLERS.addCost(new ResourceCost(steel, 5));
    MANHANDLERS.addCost(new ResourceCost(leather, 7));
    MANHANDLERS.addCost(new ResourceCost(ironore, 7));
    MANHANDLERS.addCost(new ItemCost(WARGAUNTLETS, 1));
    IRONBOOTS.addCost(new ResourceCost(leather, 4));
    IRONBOOTS.addCost(new ResourceCost(ironore, 4));
    FINESABATON.addCost(new ResourceCost(ironore, 6));
    FINESABATON.addCost(new ResourceCost(leather, 4));
    FINESABATON.addCost(new ResourceCost(fabric, 2));
    KNIGHTSSABATONS.addCost(new ResourceCost(steel, 4));
    KNIGHTSSABATONS.addCost(new ResourceCost(fabric, 5));
    KNIGHTSSABATONS.addCost(new ResourceCost(leather, 8));
    KNIGHTSSABATONS.addCost(new ItemCost(KNIGHTSSABATONSNEEDED, 1));
    COPPERSHIELD.addCost(new ResourceCost(ironore, 4));
    COPPERSHIELD.addCost(new ResourceCost(leather, 2));
    KITESHIELD.addCost(new ResourceCost(ironore, 8));
    KITESHIELD.addCost(new ResourceCost(steel, 1));
    KITESHIELD.addCost(new ResourceCost(wood, 6));
    HEATERSHIELD.addCost(new ResourceCost(steel, 4));
    HEATERSHIELD.addCost(new ResourceCost(dyes, 1));
    SCUTUM.addCost(new ResourceCost(steel, 5));
    SCUTUM.addCost(new ResourceCost(leather, 6));
    SCUTUM.addCost(new ResourceCost(ironore, 6));
    DEFENDER.addCost(new ResourceCost(mithril, 5));
    DEFENDER.addCost(new ResourceCost(gems, 5));
    DEFENDER.addCost(new ResourceCost(crystals, 5));
    DEFENDER.addCost(new ResourceCost(leather, 8));
    DEFENDER.addCost(new ResourceCost(ironwood, 5));

    HIGHLANDER.addCost(new ResourceCost(steel, 2));
    HIGHLANDER.addCost(new ResourceCost(ironore, 5));
    HIGHLANDER.addCost(new ResourceCost(leather, 2));
    MURASAME.addCost(new ResourceCost(mithril, 2));
    MURASAME.addCost(new ResourceCost(steel, 5));
    MURASAME.addCost(new ResourceCost(oil, 5));
    MURASAME.addCost(new ResourceCost(leather, 7));
    DEMONSBANE.addCost(new ResourceCost(oil, 6));
    DEMONSBANE.addCost(new ResourceCost(steel, 6));
    DEMONSBANE.addCost(new ResourceCost(mithril, 2));
    PALADINSWORD.addCost(new ResourceCost(steel, 3));
    PALADINSWORD.addCost(new ResourceCost(oil, 4));
    PALADINSWORD.addCost(new ResourceCost(mithril, 2));
    PALADINSWORD.addCost(new ItemCost(TEMPLARBLADE, 1));
    POLEAXE.addCost(new ResourceCost(steel, 2));
    POLEAXE.addCost(new ResourceCost(elfwood, 4));
    WARLABRYS.addCost(new ResourceCost(steel, 3));

    ANTIVENOM.addCost(new ResourceCost(herbs, 4));
    ANTIVENOM.addCost(new ResourceCost(powder, 4));
    ANTIVENOM.addCost(new ResourceCost(oil, 4));
    ANTIVENOM.addCost(new ResourceCost(glass, 2));
    SWIFTIRONBOOTS.addCost(new ResourceCost(ironore, 10));
    SWIFTIRONBOOTS.addCost(new ResourceCost(leather, 9));
    SWIFTIRONBOOTS.addCost(new ResourceCost(fabric, 6));
    SWIFTIRONBOOTS.addCost(new ResourceCost(steel, 6));
    EPICDEFENDER.addCost(new ResourceCost(mithril, 5));
    EPICDEFENDER.addCost(new ResourceCost(gems, 5));
    EPICDEFENDER.addCost(new ResourceCost(crystals, 5));
    EPICDEFENDER.addCost(new ResourceCost(leather, 20));
    EPICDEFENDER.addCost(new ResourceCost(ironwood, 10));
    EPICDEFENDER.addCost(new ItemCost(DEFENDER, 3));

    GLADIATORSHELMET.addCost(new ResourceCost(steel, 3));
    GLADIATORSHELMET.addCost(new ResourceCost(fabric, 3));
    GLADIATORSHELMET.addCost(new ResourceCost(leather, 6 + 5));
    GLADIATORSHELMET.addCost(new ResourceCost(elfwood, 3));
    CAPTAINSHELMET.addCost(new ResourceCost(steel, 6));
    CAPTAINSHELMET.addCost(new ResourceCost(leather, 8));
    CAPTAINSHELMET.addCost(new ResourceCost(fabric, 5));
    CAPTAINSHELMET.addCost(new ResourceCost(elfwood, 6));
    CAPTAINSHELMET.addCost(new ItemCost(CAPTAINSHELMETNEEDED, 1));
    FURARMOR.addCost(new ResourceCost(leather, 3));
    FURARMOR.addCost(new ResourceCost(ironore, 2));
    GAMBESON.addCost(new ResourceCost(leather, 6));
    LEATHERCUIRASS.addCost(new ResourceCost(leather, 4));
    LEATHERCUIRASS.addCost(new ResourceCost(wood, 2));
    LEATHERCUIRASS.addCost(new ResourceCost(fabric, 1));
    GAMBESON.addCost(new ResourceCost(wood, 4));
    GAMBESON.addCost(new ResourceCost(dyes, 2));
    BARBARIANCHIEFARMOR.addCost(new ResourceCost(dyes, 3));
    BARBARIANCHIEFARMOR.addCost(new ResourceCost(ironore, 3));
    COWLEATHERARMOR.addCost(new ResourceCost(leather, 5));
    PERFECTLEATHER.addCost(new ResourceCost(leather, 5 + ADDED));
    PERFECTLEATHER.addCost(new ResourceCost(fabric, 4));
    PERFECTLEATHER.addCost(new ResourceCost(dyes, 3));
    NOBLELEATHER.addCost(new ResourceCost(fabric, 3));
    NOBLELEATHER.addCost(new ResourceCost(leather, 8)); // FAKE for defenders
    NOBLELEATHER.addCost(new ResourceCost(dyes, 2));
    NOBLELEATHER.addCost(new ItemCost(LEATHERCUIRASS, 2));
    NOBLELEATHER.addCost(new ItemCost(NOBLELEATHERNEEDED, 1));
    LEATHERGLOVES.addCost(new ResourceCost(leather, 6));
    LONGGLOVES.addCost(new ResourceCost(leather, 6));
    LONGGLOVES.addCost(new ResourceCost(fabric, 2));
    LONGGLOVES.addCost(new ResourceCost(dyes, 2));
    NOBLESGLOVES.addCost(new ResourceCost(fabric, 4));
    NOBLESGLOVES.addCost(new ResourceCost(gems, 1));
    NOBLESGLOVES.addCost(new ResourceCost(dyes, 3));
    NOBLESGLOVES.addCost(new ItemCost(NOBLESGLOVESNEEDED, 1));
    FURBOOTS.addCost(new ResourceCost(leather, 2 + ADDED));
    RIDINGBOOTS.addCost(new ResourceCost(fabric, 3));
    RIDINGBOOTS.addCost(new ResourceCost(wood, 2));
    LOOSEBOOTS.addCost(new ResourceCost(leather, 5));
    LOOSEBOOTS.addCost(new ResourceCost(dyes, 2));
    LOOSEBOOTS.addCost(new ItemCost(LOOSEBOOTSNEEDED, 1));
  }
  
  public Driver() {
    
    started = false;
    try {
      robot = new Robot();
      robot.setAutoDelay(40);
    } catch (AWTException e) {
      e.printStackTrace();
    }
    workers = new ArrayList<Worker>();

    TEMPLARBLADE.amount = 0;
    
    FIRESTORMSCROLL.amount = 121;
    FIREBALLSCROLL.amount = 0;
    MAGICSCROLL.amount = 0;
    
    HEAVYHELMET.amount = 64;
    
    LEATHERCUIRASS.amount = 0;
    NOBLELEATHERNEEDED.amount = 39;
    NOBLESGLOVESNEEDED.amount = 29;
    PENDANTOFSAFETYNEEDED.amount = 500;
    DEFENDER.amount = 35;

//    workers.add( new Worker( WORKER1, new Item[][]{{ HEAVYHELMET }}, "Shop Keeper"));
    workers.add( new Worker( WORKER4, new Item[][]{{ EPICDEFENDER }}, "Enchanter"));
//    workers.add( new Worker( WORKER3, new Item[][]{{TRANSMUNDANEFULLHELMET},{DEFENDER}}, "Armoror"));
    workers.add( new Worker( WORKER2, new Item[][]{{NOBLELEATHER}, {LEATHERCUIRASS}}, "Leather Worker"));
    workers.add( new Worker( WORKER3, new Item[][]{{PENDANTOFSAFETY}}, "Jeweler"));

    
    
    
    exitFrame = new JFrame("Swords And Potions 2 Bot V3.0");
    exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    exitFrame.setSize(200, 200);
    exitFrame.setAlwaysOnTop(true);
    exitFrame.addMouseListener(new MouseListener() { @Override public void mouseClicked(MouseEvent arg0) {} @Override public void mouseEntered(MouseEvent arg0) {} @Override public void mouseExited(MouseEvent arg0) {}
      @Override
      public void mousePressed(MouseEvent e) {
        if( started ) {
          System.exit(0);
        }
        else {
          Thread thread2 = new Thread(new FakeThread());
          thread2.start();
          thread = new Thread(new RunThread());
          thread.start();
        }
      }
      @Override
      public void mouseReleased(MouseEvent e) {}
    });
//    exitFrame.setVisible(true);

    thread = new Thread(new RunThread());
    thread.start();
  }
  
  public class RunThread implements Runnable {
    @Override
    public void run() {
      startPlaying();
    }
  }
  public class FakeThread implements Runnable {
    @Override
    public void run() {
      int asdf = 0;
      while( true ) {
        System.out.println(++asdf);
        try {
          thread.sleep(10000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    };
    new Driver();
  }

}
