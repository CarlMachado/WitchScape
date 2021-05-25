package com.hatchetstudios.inventario;

public class Warehouse {

	public static boolean exchangeInventory = false;
	public static boolean exchangeChest = false;
	
	public static int numExchangeInventory = 0;
	public static int numExchangeChest = 0;
	//public static int firsSelected = 0; // 0 bau - 1 Inventario
	
	public static Slot temporaryInventory;
	public static Slot temporaryChest;
	
	public static void resetWareHouse() {
		Warehouse.exchangeInventory = false;
		Warehouse.exchangeChest = false;
		Warehouse.numExchangeInventory = 0;
		Warehouse.numExchangeChest = 0;
		//Warehouse.firsSelected = 0;
		Warehouse.temporaryInventory = new Slot();
		Warehouse.temporaryChest = new Slot();
	}
	
}
