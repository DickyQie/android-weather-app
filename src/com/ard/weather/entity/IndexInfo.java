package com.ard.weather.entity;

import java.util.List;

public class IndexInfo {

	private ClothesInfo clothes;
	public static final class ClothesInfo
	{
		private String desc;//选择的衣服
		private String title;//标题
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	private ColdInfo cold;
	public static final class ColdInfo
	{
		private String desc;//天气状态
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	private DiaoYuInfo dy;
	public static final class DiaoYuInfo
	{
		private String desc;//天气状态
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	private ComfortInfo comfort;
	public static final class ComfortInfo
	{
		private String desc;//天气如何
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	private GlassInfo glass;
	public static final class GlassInfo
	{
		private String desc;//佩戴
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	private SportsInfo sports;
	public static final class SportsInfo
	{
		private String desc;//推荐进行室内运动
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	
	private TravelInfo travel;
	public static final class TravelInfo
	{
		private String desc;//风稍大，但仍可尽情享受大自然风光
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	private UvInfo uv;
	public static final class UvInfo
	{
		private String desc;//涂擦SPF大于15、PA+防晒护肤品
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	private Wash_carInfo ls;
	public static final class Wash_carInfo
	{
		private String desc;//风力较大，洗车后会蒙上灰尘
		private String title;//
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
/*	public BeautyInfo getBeauty() {
		return beauty;
	}
	public void setBeauty(BeautyInfo beauty) {
		this.beauty = beauty;
	}
	public ClThesInfo getCl() {
		return cl;
	}
	public void setCl(ClThesInfo cl) {
		this.cl = cl;
	}*/
	public ClothesInfo getClothes() {
		return clothes;
	}
	public void setClothes(ClothesInfo clothes) {
		this.clothes = clothes;
	}
	public ColdInfo getCold() {
		return cold;
	}
	public void setCold(ColdInfo cold) {
		this.cold = cold;
	}
	public DiaoYuInfo getDy() {
		return dy;
	}
	public void setDy(DiaoYuInfo dy) {
		this.dy = dy;
	}
	public ComfortInfo getComfort() {
		return comfort;
	}
	public void setComfort(ComfortInfo comfort) {
		this.comfort = comfort;
	}
	public GlassInfo getGlass() {
		return glass;
	}
	public void setGlass(GlassInfo glass) {
		this.glass = glass;
	}
	public SportsInfo getSports() {
		return sports;
	}
	public void setSports(SportsInfo sports) {
		this.sports = sports;
	}
	public TravelInfo getTravel() {
		return travel;
	}
	public void setTravel(TravelInfo travel) {
		this.travel = travel;
	}
	public UvInfo getUv() {
		return uv;
	}
	public void setUv(UvInfo uv) {
		this.uv = uv;
	}
	public Wash_carInfo getLs() {
		return ls;
	}
	public void setLs(Wash_carInfo ls) {
		this.ls = ls;
	}
}
