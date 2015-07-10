package com.kad.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

public class Queue {
	private static LinkedList<Word> linked = new LinkedList<Word>();
	private static LinkedList<Word> mobilelinked = new LinkedList<Word>();
	private static LinkedHashMap sorecordmap= new LinkedHashMap();
	private static LinkedHashMap baidurecordmap = new LinkedHashMap();
	private static List baidulist = new ArrayList();
	private static List solist = new ArrayList();
	private static List mobilebaidulist = new ArrayList();
	private static List recordbaidulist = new ArrayList();
	private static List recordsolist = new ArrayList();
	public static LinkedList<Word> getLinked() {
		return linked;
	}

	public static void setLinked(LinkedList<Word> linked) {
		Queue.linked = linked;
	}
	
	public void add(Word keyWord){
		linked.add(keyWord);
	}
	
	public void move(Word keyWord){
		linked.remove(keyWord);
	}

	public static List getBaidulist() {
		return baidulist;
	}

	public static void setBaidulist(List baidulist) {
		Queue.baidulist = baidulist;
	}

	public static List getSolist() {
		return solist;
	}

	public static void setSolist(List solist) {
		Queue.solist = solist;
	}

	public static LinkedList<Word> getMobilelinked() {
		return mobilelinked;
	}

	public static void setMobilelinked(LinkedList<Word> mobilelinked) {
		Queue.mobilelinked = mobilelinked;
	}

	public static List getMobilebaidulist() {
		return mobilebaidulist;
	}

	public static void setMobilebaidulist(List mobilebaidulist) {
		Queue.mobilebaidulist = mobilebaidulist;
	}

	public static List getRecordbaidulist() {
		return recordbaidulist;
	}

	public static void setRecordbaidulist(List recordbaidulist) {
		Queue.recordbaidulist = recordbaidulist;
	}

	public static List getRecordsolist() {
		return recordsolist;
	}

	public static void setRecordsolist(List recordsolist) {
		Queue.recordsolist = recordsolist;
	}

	public static LinkedHashMap getSorecordmap() {
		return sorecordmap;
	}

	public static void setSorecordmap(LinkedHashMap sorecordmap) {
		Queue.sorecordmap = sorecordmap;
	}

	public static LinkedHashMap getBaidurecordmap() {
		return baidurecordmap;
	}

	public static void setBaidurecordmap(LinkedHashMap baidurecordmap) {
		Queue.baidurecordmap = baidurecordmap;
	}




}
