package com.bakerystudios.inventario;

public class Warehouse {

	public static boolean exchangeInventory = false;
	public static boolean exchangeChest = false;
	
	public static int numExchangeInventory = 0;
	public static int numExchangeChest = 0;
	public static int firsSelected = 0; // 0 bau - 1 Inventario
	
	public static int temporaryNum = 0;
	public static boolean temporayNumExist = false;
	
	public static Slot temporary;
	
	public static void resetWareHouse() {
		Warehouse.exchangeInventory = false;
		Warehouse.exchangeChest = false;
		Warehouse.numExchangeInventory = 0;
		Warehouse.numExchangeChest = 0;
		Warehouse.firsSelected = 0;
		Warehouse.temporaryNum = 0;
		Warehouse.temporayNumExist = false;
		Warehouse.temporary = new Slot();
	}
	
}
