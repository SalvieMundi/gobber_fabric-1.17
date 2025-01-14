package com.kwpugh.gobber2.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "gobber2")
public class Gobber2Config extends PartitioningSerializer.GlobalData 
{
    public Ores ORES = new Ores();
    public General GENERAL = new General();
    
    @Config(name = "ores")
    public static class Ores implements ConfigData 
    {
	    @Comment("\n"
	    		+"\n"
	    		+ "***********************"    		
	    		+"\nGobber Ore"
	    		+"\n***********************") 
	    public boolean gobberEnable = true;
	    public int gobberVeinSize = 4;
    	public int gobberMaxLevel = 30;
    	public int gobberPerChunk = 20;
    	
        @Comment("\n"
        		+"\n"
        		+ "***********************"
	    		+"\nNether Gobber Ore"
	    		+"\n***********************") 
	    public boolean netherGobberEnable = true;
	    public int netherGobberVeinSize = 9;
    	public int netherGobberMaxLevel = 120;
    	public int netherGobberPerChunk = 25;
    	
        @Comment("\n"
        		+"\n"
        		+ "***********************"
 	    		+"\nEnd Gobber Ore"
 	    		+"\n***********************") 
 	    public boolean endGobberEnable = true;
 	    public int endGobberVeinSize = 9;
     	public int endGobberMaxLevel = 180;
     	public int endGobberPerChunk = 25;
     	
        @Comment("\n"
        		+"\n"
        		+ "***********************"
 	    		+"\nLucky Block"
 	    		+"\n***********************") 
 	    public boolean luckyEnable = true;
 	    public int luckyVeinSize = 4;
     	public int luckyMaxLevel = 120;
     	public int luckyPerChunk = 15;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nLucky Block - Drops"
				+ "\n- only use values between .01 to .99"
				+ "\n- values cannot overlap"
				+ "\n- values should be in decending order from"
				+ "\ncutoff, common, uncommon, and rare"
				+ "\n- values above cutoff threshold get nothing"
				+ "\n***********************")
		public boolean enableExtraLoot = false;
		public boolean enableFortune = false;
		public double cutoffThreshold = .20;
		public double commonThreshold = .10;
		public double uncommonThreshold = .04;
		public double rareThreshold = .01;
    }
    
    @Config(name = "general")
    public static class General implements ConfigData 
    {
		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEnable-Disable Rings"
				+"\n***********************")
		public boolean enableRingAbove = true;
		public boolean enableRingAirWalking = true;
		public boolean enableRingAscent = true;
		public boolean enableRingAttraction = true;
		public boolean enableRingBlink = true;
		public boolean enableRingCuring = true;
		public boolean enableRingDismissal = true;
		public boolean enableRingEnderchest = true;
		public boolean enableRingExplorer = true;
		public boolean enableRingFarmer = true;
		public boolean enableRingHaste = true;
		public boolean enableRingMiner = true;
		public boolean enableRingPhoenix = true;
		public boolean enableRingRepair = true;
		public boolean enableRingReturn = true;
		public boolean enableRingStealth = true;
		public boolean enableRingSunshine = true;
		public boolean enableRingSwiftness = true;
		public boolean enableRingTeleport = true;
		public boolean enableRingTraveler = true;
		public boolean enableRingVision = true;
		public boolean enableRingVoid = true;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEnable-Disable Staffs"
				+"\n***********************")
		public boolean enableStaffClearing = true;
		public boolean enableStaffEnsnarement = true;
		public boolean enableStaffFarmer = true;
		public boolean enableStaffNature = true;
		public boolean enableStaffSniper = true;
		public boolean enableStaffStars = true;
		public boolean enableStaffTransformation = true;
		public boolean enableStaffHostileEnsnarement = true;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEnable-Disable Medallions"
				+"\n***********************")
		public boolean enableMedallionBreathing = true;
		public boolean enableMedallionLesserHealing = true;
		public boolean enableMedallionHealing = true;
		public boolean enableMedallionGreaterHealing = true;
		public boolean enableMedallionExp = true;
		public boolean enableMedallionHero = true;
		public boolean enableMedallionSea = true;

    	 @Comment("\n"
    			+"\n"
    	 		+ "***********************"
 	    		+"\nRing Settings"
 	    		+"\n***********************") 
 		public int ringAttractionRange = 8;
 		public int ringMinerCooldown = 120;
 		public int ringMinerRange = 5;
 		public int ringMinerVerticalRange = 4;
 		public boolean ringMinerDelayedBreak = false;
 		public int ringBlinkDistance = 100;
 		public int ringBlinkCooldown = 120;
 		public int ringCuringCooldown = 120;
 		public int ringExplorerCooldown = 240;
 		public boolean ringExplorerUsePlayerPos = false;
 		public int ringExplorerMin = 5000;
		public int ringExplorerMax = 20000;
 		public int ringFarmerHorizRange = 12;
		public int ringFarmerVertRange = 6;
 		public int ringFarmerInterval = 60;
 		public int ringFarmerIntervalCactus = 20;
 		public int ringAboveCooldown = 120;
 		public int ringRepairInterval = 90;
 		public double ringDismissalRange = 8;
 		public double ringDismissalVelocity = 0.2D;
 		public double ringDismissalLift = 1.5D;
 		public double ringTravelerLaunch = 4.0;
 		public double ringTravelerCruising = 0.2;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEffect Setting"
				+"\n-used with optimization mods"
				+"\nthat break things"
				+"\nValue of 300 works"
				+"\n***********************")
		public int effectDuration = 8;
		public int effectDurationKnowledge = 120;

 		 @Comment("\n"
 				+"\n"
 				+ "***********************"
  	    		+"\nMedallion Settings"
  	    		+"\n***********************") 
 		public int medallionExpMultiplier = 5;
		public int medallionLesserHealingAmount = 1;
		public int medallionHealingAmount = 2;
		public int medallionGreaterHealingAmount = 3;
		public boolean medallionBreathingWaterCheck = true;
		
 		 @Comment("\n"
 				+"\n"
 		 		+ "***********************"
   	    		+"\nStaff Settings"
   	    		+"\n***********************") 	 
 		public int staffTransformationDurability = 1025;
 		public int staffClearingRange = 11;
 		public int staffSniperCooldown = 240;
		public int staffFarmerHorizRange = 12;
		public int staffFarmerVertRange = 6;
		public int staffFarmerInterval = 60;
		public int staffFarmerIntervalCactus = 20;
 		public boolean staffFarmerReplant = true;
 		public boolean staffEnsnarementHotileMobs = false;
		
		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nTool Settings"
 	    		+"\n***********************")	
		public int swordSniperCooldoown = 240;
		public boolean enableEndHammer5x5 = true;
		public boolean enableEndExcavator5x5 = true;
		public boolean enableTreeAxe = true;
		public boolean enableHammers = true;
		public boolean enableExcavators = true;
		public boolean enablePaxels = true;
		public boolean enableNetherSwordPerks = true;
	
	   	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nBow Durability"
    			+"\n******************************")
    	public int gobberBowDurability = 3800;
	   	public int gobberNetherBowDurability = 5200;
	   	public int gobberEndBowDurability = 8000;

      	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Tool Material Stats"
    			+"\n******************************")
    	public int gobberDurability = 3800;
       	public float gobberMiningSpeed = 9.0F;
       	public float gobberAttackDamage = 9.0F;
       	public float gobberSwordAttackSpeed = 2.0F;
       	public int gobberMiningLevel = 4;
       	public int gobberEnchantability = 20;
       	
      	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Nether Tool Material Stats"
    			+"\n******************************")
    	public int gobberNetherDurability = 5200;
       	public float gobberNetherMiningSpeed = 12.0F;
       	public float gobberNetherAttackDamage = 9.0F;
       	public float gobberNetherSwordAttackSpeed = 2.2F;
       	public int gobberNetherMiningLevel = 5;
       	public int gobberNetherEnchantability = 25;

      	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber End Tool Material Stats"
    			+"\n******************************")
      	public boolean unbreakableEndTools = true;
    	public int gobberEndDurability = 8000;
       	public float gobberEndMiningSpeed = 14.0F;
       	public float gobberEndAttackDamage = 9.0F;
       	public float gobberEndSwordAttackSpeed = 2.4F;
       	public int gobberEndMiningLevel = 5;
       	public int gobberEndEnchantability = 30;
       	    
		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nArmor Settings"
 	    		+"\n***********************")	
		public boolean enableGobberAllPerks = true;
		public boolean enableGobberHealthPerks = true;
		public int gobberGobberArmorHealingPoints = 2;
		public boolean enableNetherAllPerks = true;
		public boolean enableNetherHealthPerks = true;
		public int gobberNetherArmorHealingPoints = 2;
		public boolean enableEndAllPerks = true;
		public boolean enableEndHealthPerks = true;
		public int gobberEndArmorHealingPoints = 3;
		public boolean unbreakableEndArmor = true;
		public boolean enableEndArmorGliding = true;
		public boolean enablePhantomProtection = true;
		public boolean enableDragonArmorWithoutFlight = false;
		public boolean enableDragonArmorWithFlight = true;
		public boolean enableDragonAllPerks = true;
		public boolean enableDragonHealthPerks = true;
		public int gobberDragonArmorHealingPoints = 4;
		public boolean enableDragonProtectiveEffects = true;
		public boolean enableDragonNoFallDamage = true;
		public boolean unbreakableDragonArmor = true;

		@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Armor Material Stats"
    			+"\n******************************")
    	public int gobberDurabilityMultiplier = 71;
      	public int gobberArmorEnchantability = 25;
      	public float gobberToughness = 2.5F;
      	public float gobberKnockbackResistance = 0.0F;
      	public int gobberHeadProtection = 6;
      	public int gobberChestProtecction = 11;
      	public int gobberLeggingsProtection = 9;
      	public int gobberBootsProtection = 6;
       	
       	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Nether Armor Material Stats"
    			+"\n******************************")
       	public int gobberNetherDurabilityMultiplier = 83;
      	public int gobberNetherArmorEnchantability = 30;
      	public float gobberNetherToughness = 2.75F;
      	public float gobberNetherKnockbackResistance = 0.1F;
		public int netherHeadProtection = 7;
		public int netherChestProtecction = 12;
		public int netherLeggingsProtection = 10;
		public int netherBootsProtection = 7;
       	
       	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber End Armor Material Stats"
    			+"\n******************************")
       	public int gobberEndDurabilityMultiplier = 100;
      	public int gobberEndArmorEnchantability = 30;
      	public float gobberEndToughness = 3.0F;
      	public float gobberEndKnockbackResistance = 0.2F;
		public int endHeadProtection = 8;
		public int endChestProtecction = 13;
		public int endLeggingsProtection = 11;
		public int endBootsProtection = 8;

		@Comment("\n"
				+"\n"
				+"\n"
				+"******************************"
				+"\nDragon Armor Material Stats"
				+"\n******************************")
		public int gobberDragonDurabilityMultiplier = 100;
		public int gobberDragonArmorEnchantability = 30;
		public float gobberDragonToughness = 3.0F;
		public float gobberDragonKnockbackResistance = 0.3F;
		public int dragonHeadProtection = 8;
		public int dragonChestProtecction = 13;
		public int dragonLeggingsProtection = 11;
		public int dragonBootsProtection = 8;
       	
		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nEnchantments"
 	    		+"\n***********************")	
		public boolean enableSmithblade = true;
		public int smithbladeAttackAmount = 2 ;
		public int smithbladeMaxLevel = 5;
		public boolean enableRebuffing = true;
		public int rebuffingMaxLevel = 3;
		public boolean enableFasterObsidian = true;
		public boolean enableUntouchable = true;
		public int untouchableDamage = 8;
		public int untouchableMaxLevel = 3;
		public boolean enableQuickUse = true;
		public boolean enableBlinders = true;
		public boolean enableSolidFooting = true;

		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nStatus Effects"
 	    		+"\n***********************")	
		public int KnowledgeBoostXPPerTick = 1 ;
	
		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nLoot Chests"
 	    		+"\n***********************")
		public boolean lootEnable = true;
		public float lootChance = .04F;
		
		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nSpecial Item"
				+"\n"
				+ "\nItem provides a one-time random teleport"
	            +"\nand is consumed after successful teleport."
	            +"\nMin/Max range from world spawn to search"
				+"\nor from the player's current position"
		    	+"\nItem does NOT have a standard recipe"
		    	+ "\n***********************") 
		public boolean enableSpecialItem = true;
		public int specialItemMin = 20000;
		public int specialItemMax = 75000;
		public boolean specialItemUsePlayerPos = false;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nNemesis Settings"
				+"\n"
				+ "\n***********************")
		public boolean enableNemesis = true;
		public double dropChanceNemesis = 0.15;
		public double attackDamageNemesis = 4.0;
		public double healthNemesis = 20.0;
		public double movementSpeedNemesis = 0.3;
		public double attackKnockbackNemesis = 0.7;
		public double knockbackResistanceNemesis = 1.0;
    }
}