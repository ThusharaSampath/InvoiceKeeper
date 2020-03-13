package entities;

public class Dealer {
	String DealerName;
	String dealerCode;
	public long DealerId;
	long DealerTel;
	long DealerTime; // free time period that dealer give for pay to invoice IN DAYS

	public long getDealerId() {
		return DealerId;
	}

	public void setDealerId(int dealerId) {
		DealerId = dealerId;
	}

	public String getDealerName() {
		return DealerName;
	}

	public void setDealerName(String dealerName) {
		DealerName = dealerName;
	}

	public long getDealerTel() {
		return DealerTel;
	}

	public void setDealerTel(long dealerTel2) {
		DealerTel = dealerTel2;
	}

	public long getDealerTime() {
		return DealerTime;
	}

	public void setDealerTime(long dealerTime2) {
		DealerTime = dealerTime2;
	}




	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public void setDealerId(long dealerId) {
		DealerId = dealerId;
	}

	@Override
	public String toString() {
		return "Dealer [DealerName=" + DealerName + ", dealerCode=" + dealerCode + ", DealerId=" + DealerId
				+ ", DealerTel=" + DealerTel + ", DealerTime=" + DealerTime + "]";
	}
}
