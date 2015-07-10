package com.kad.util;

public class Word {
	public Word() {
	}
	public Word(String word, String url, String channel) {
		super();
		this.word = word;
		this.url = url;
		this.channel = channel;
	}
	private String word;
	private String url;
	private String channel;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Word)) return false;
		final Word other=(Word)obj;
		 if (this.channel.equals(other.channel)&&this.word.equals(other.word))
		    return true;
		else
		  return false;
		
	}
}
